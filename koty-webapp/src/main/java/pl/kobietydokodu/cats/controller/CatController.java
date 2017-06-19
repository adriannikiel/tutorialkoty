package pl.kobietydokodu.cats.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

import pl.kobietydokodu.cats.dao.CatDAO;
import pl.kobietydokodu.cats.dao.PhotoDAO;
import pl.kobietydokodu.cats.dao.ToyDAO;
import pl.kobietydokodu.cats.domain.Cat;
import pl.kobietydokodu.cats.domain.Photo;
import pl.kobietydokodu.cats.dto.CatDTO;
import pl.kobietydokodu.cats.dto.PhotoDTO;

@Controller
public class CatController {

	@Autowired
	private CatDAO catDao;
	@Autowired
	private ToyDAO toyDao;
	@Autowired
	private PhotoDAO photoDao;

	private final String BUCKET_NAME = "*** Provide bucket name ***";
	private final String ACCESS_KEY = "*** Provide access key ***";
	private final String SECRET_KEY = "*** Provide secret key ***";

	protected Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * This method displays list of cats (default page of application).
	 */
	@Secured("ROLE_USER")
	@RequestMapping({ "/catList", "/" })
	public String catsList(Model model) {
		model.addAttribute("cats", catDao.findAll());
		return "catList";
	}

	/**
	 * This method displays the form to add new cat.
	 */
	@Secured("ROLE_USER")
	@RequestMapping(value = "/addCat", method = RequestMethod.GET)
	public String addCatForm(@ModelAttribute("catDto") @Valid CatDTO catDto, BindingResult result) {
		return "addCat";
	}

	@Secured("ROLE_USER")
	@RequestMapping(value = "/addCat", method = RequestMethod.POST)
	public String addCat(@ModelAttribute("catDto") @Valid CatDTO catDto, BindingResult result) {
		if (!result.hasErrors()) {
			// form filled correctly
			Cat cat = new Cat();
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

			try {
				cat.setBirthday(sdf.parse(catDto.getBirthday()));
			} catch (ParseException e) {
				e.printStackTrace();
			}

			cat.setName(catDto.getName());
			cat.setGuardianName(catDto.getGuardianName());
			cat.setWeight(catDto.getWeight());
			catDao.save(cat);

			return "redirect:/catList";
		}

		// form filled incorrectly
		return "addCat";

	}

	/**
	 * This method displays details of given cat.
	 */
	@Secured("ROLE_USER")
	@RequestMapping("/cat-{id}")
	public String catDetails(@PathVariable("id") Long id, Model model) {
		model.addAttribute("cat", catDao.findById(id));
		model.addAttribute("toys", toyDao.findByKitten_id(id));

		return "catDetails";
	}

	/**
	 * This method displays the form to add photo to given cat.
	 */
	@Secured("ROLE_USER")
	@RequestMapping(value = "/cat-{id}/photo/add", method = RequestMethod.GET)
	public String addPhotoForm(@ModelAttribute("photoDto") @Valid PhotoDTO photoDto, BindingResult result,
			@PathVariable("id") Long id, Model model) {
		model.addAttribute("cat", catDao.findById(id));
		return "addPhoto";
	}

	@Secured("ROLE_USER")
	@RequestMapping(value = "/cat-{id}/photo/add", method = RequestMethod.POST)
	public String addPhoto(@RequestParam("file") MultipartFile file, @PathVariable("id") Long id, Model model,
			@ModelAttribute("photoDto") @Valid PhotoDTO photoDto, BindingResult result) {

		if (!result.hasErrors()) {
			// form filled correctly

			String fileName = handleFileUpload(file);

			if (fileName != null) {

				Photo photo = new Photo();

				photo.setKitten(catDao.findById(id));
				photo.setFileDescription(photoDto.getFileDescription());
				photo.setOriginalFileName(file.getOriginalFilename());
				photo.setFileName(fileName);
				photo.setFileType(file.getContentType());
				photo.setFileSize((int) file.getSize());

				photoDao.save(photo);

				return "redirect:/cat-{id}";
			}
		}
		// form filled incorrectly
		return "redirect:/cat-{id}/photo/add";
	}

	/**
	 * This method allows to show photo on webpage.
	 */
	@RequestMapping(value = "/cat-{id1}/photo/photo-{photoId}", method = RequestMethod.GET)
	public void showPhoto(@PathVariable("id1") Long id1, @PathVariable("photoId") Long photoId,
			HttpServletResponse response) {

		Photo photo = photoDao.findById(photoId);
		String key = photo.getFileName();

		try {
			AmazonS3 s3client = new AmazonS3Client(new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY));
			S3Object object = s3client.getObject(new GetObjectRequest(BUCKET_NAME, key));
			InputStream is = object.getObjectContent();

			response.setContentType(photo.getFileType());
			response.setContentLength(photo.getFileSize());
			org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
			response.flushBuffer();

		} catch (IOException ex) {
			logger.info("An error occurred while downloading file", ex);
			response.setStatus(404);
		}

	}

	/**
	 * This method allows to download photo to local disk.
	 */
	@RequestMapping(value = "/cat-{id1}/photo/photo-{photoId}/download", method = RequestMethod.GET)
	public void downloadPhoto(@PathVariable("id1") Long id1, @PathVariable("photoId") Long photoId,
			HttpServletResponse response) throws IOException {

		final int BUFFER_SIZE = 2000000;

		Photo photo = photoDao.findById(photoId);
		String key = photo.getFileName();

		try {
			AmazonS3 s3client = new AmazonS3Client(new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY));
			S3Object object = s3client.getObject(new GetObjectRequest(BUCKET_NAME, key));
			InputStream inputStream = object.getObjectContent();

			response.setContentType(photo.getFileType());
			response.setContentLength(photo.getFileSize());

			String headerValue = String.format("attachment; filename=\"%s\"", photo.getOriginalFileName());
			response.setHeader("Content-Disposition", headerValue);

			OutputStream outStream = response.getOutputStream();

			byte[] buffer = new byte[BUFFER_SIZE];
			int bytesRead = -1;

			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, bytesRead);
			}

			inputStream.close();
			outStream.close();

		} catch (IOException ex) {
			logger.info("An error occurred while downloading file", ex);
			response.setStatus(404);
		}
	}

	/**
	 * This method delete given photo.
	 */
	@Secured("ROLE_USER")
	@RequestMapping(value = "/cat-{id1}/photo/photo-{photoId}/delete", method = RequestMethod.GET)
	public String deletePhoto(@PathVariable("id1") Long id1, @PathVariable("photoId") Long photoId) {

		Photo photo = photoDao.findById(photoId);
		String key = photo.getFileName();

		try {
			AmazonS3 s3client = new AmazonS3Client(new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY));
			s3client.deleteObject(new DeleteObjectRequest(BUCKET_NAME, key));

			photoDao.delete(photoId);
		} catch (Exception e) {
			logger.error("Delete operation is failed.");
		}

		return "redirect:/cat-{id1}";
	}

	/**
	 * This method upload photo to Amazon S3.
	 */
	private String handleFileUpload(MultipartFile file) {
		if (!file.isEmpty()) {
			try {
				UUID uuid = UUID.randomUUID();

				String filename = "uploads/upload_" + uuid.toString();

				byte[] bytes = file.getBytes();
				InputStream inputStream = new ByteArrayInputStream(bytes);
				AmazonS3 s3client = new AmazonS3Client(new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY));
				s3client.putObject(new PutObjectRequest(BUCKET_NAME, filename, inputStream, new ObjectMetadata()));

				logger.info("File {} has been successfully uploaded as {}",
						new Object[] { file.getOriginalFilename(), filename });

				return filename;

			} catch (Exception e) {
				logger.error("File has not been uploaded", e);
			}
		} else {
			logger.error("Uploaded file is empty");
		}
		return null;
	}

}
