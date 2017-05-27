package pl.kobietydokodu.koty.controllers;

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

import pl.kobietydokodu.koty.InterfejsDAO;
import pl.kobietydokodu.koty.ZabawkaDAO;
import pl.kobietydokodu.koty.ZdjecieDAO;
import pl.kobietydokodu.koty.domain.Kot;
import pl.kobietydokodu.koty.domain.Zdjecie;
import pl.kobietydokodu.koty.dto.KotDTO;
import pl.kobietydokodu.koty.dto.ZdjecieDTO;

@Controller
public class KotyController {

	@Autowired
	private InterfejsDAO kotDao;
	@Autowired
	private ZabawkaDAO zabawkaDao;
	@Autowired
	private ZdjecieDAO zdjecieDao;

	private Long kotCount = 0L;
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Secured("ROLE_USER")
	@RequestMapping({ "/lista", "/" })
	public String listaKotow(Model model) {
		model.addAttribute("koty", kotDao.findAll());
		return "lista";
	}

	@Secured("ROLE_USER")
	@RequestMapping(value = "/dodaj", method = RequestMethod.GET)
	public String dodajKotaFormularz(@ModelAttribute("kotDto") @Valid KotDTO kotDto, BindingResult result) {
		return "dodaj";
	}

	@Secured("ROLE_USER")
	@RequestMapping(value = "/dodaj", method = RequestMethod.POST)
	public String dodajKota(@ModelAttribute("kotDto") @Valid KotDTO kotDto, BindingResult result) {
		if (!result.hasErrors()) {
			// form filled correctly
			Kot kot = new Kot();
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

			try {
				kot.setDataUrodzenia(sdf.parse(kotDto.getDataUrodzenia()));
			} catch (ParseException e) {
				e.printStackTrace();
			}

			kot.setId(kotCount);
			kot.setImie(kotDto.getImie());
			kot.setImieOpiekuna(kotDto.getImieOpiekuna());
			kot.setWaga(kotDto.getWaga());
			kotDao.save(kot);

			kotCount++;

			return "redirect:/lista";
		}

		// form filled incorrectly
		return "dodaj";

	}

	@Secured("ROLE_USER")
	@RequestMapping("/kot-{id}")
	public String szczegolyKota(@PathVariable("id") Long id, Model model) {
		model.addAttribute("kot", kotDao.findById(id));
		model.addAttribute("zabawki", zabawkaDao.findByKotek_id(id));

		return "szczegoly";
	}

	@Secured("ROLE_USER")
	@RequestMapping(value = "/kot-{id}/zdjecie/dodaj", method = RequestMethod.GET)
	public String dodajFotkeFormularz(@ModelAttribute("fotkaDto") @Valid ZdjecieDTO zdjecieDto, BindingResult result,
			@PathVariable("id") Long id, Model model) {
		model.addAttribute("kot", kotDao.findById(id));
		return "dodajZdjecie";
	}

	@Secured("ROLE_USER")
	@RequestMapping(value = "/kot-{id}/zdjecie/dodaj", method = RequestMethod.POST)
	public String dodajFotke(@RequestParam("plik") MultipartFile file, @PathVariable("id") Long id, Model model,
			@ModelAttribute("fotkaDto") @Valid ZdjecieDTO zdjecieDto, BindingResult result) {

		if (!result.hasErrors()) {
			// form filled correctly

			String nazwaPliku = handleFileUpload(file);

			if (nazwaPliku != null) {

				Zdjecie zdjecie = new Zdjecie();

				zdjecie.setKotek(kotDao.findById(id));
				zdjecie.setOpisPliku(zdjecieDto.getOpisPliku());
				zdjecie.setNazwaOryginalnaPliku(file.getOriginalFilename());
				zdjecie.setNazwaPliku(nazwaPliku);
				zdjecie.setTypPliku(file.getContentType());
				zdjecie.setRozmiarPliku((int) file.getSize());

				zdjecieDao.save(zdjecie);

				return "redirect:/kot-{id}";
			}
		}
		// form filled incorrectly
		return "redirect:/kot-{id}/zdjecie/dodaj";
	}

	@RequestMapping(value = "/kot-{id1}/zdjecie/zdjecie-{zdjecieId}", method = RequestMethod.GET)
	public void pokazFotke(@PathVariable("id1") Long id1, @PathVariable("zdjecieId") Long zdjecieId,
			HttpServletResponse response) {

		String bucketName = "anikiel.tutorial";
		String accessKey = "AKIAJFDXOVEICJ2K444A";
		String secretKey = "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";

		Zdjecie zdjecie = zdjecieDao.findById(zdjecieId);
		String key = zdjecie.getNazwaPliku();

		try {
			AmazonS3 s3client = new AmazonS3Client(new BasicAWSCredentials(accessKey, secretKey));
			S3Object object = s3client.getObject(new GetObjectRequest(bucketName, key));
			InputStream is = object.getObjectContent();

			response.setContentType(zdjecie.getTypPliku());
			response.setContentLength(zdjecie.getRozmiarPliku());
			org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
			response.flushBuffer();

		} catch (IOException ex) {
			logger.info("B³¹d przy pobieraniu pliku", ex);
			response.setStatus(404);
		}

	}

	@RequestMapping(value = "/kot-{id1}/zdjecie/zdjecie-{zdjecieId}/pobierz", method = RequestMethod.GET)
	public void pobierzFotke(@PathVariable("id1") Long id1, @PathVariable("zdjecieId") Long zdjecieId,
			HttpServletResponse response) throws IOException {

		final int BUFFER_SIZE = 1000000;

		String bucketName = "anikiel.tutorial";
		String accessKey = "AKIAJFDXOVEICJ2K444A";
		String secretKey = "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";

		Zdjecie zdjecie = zdjecieDao.findById(zdjecieId);
		String key = zdjecie.getNazwaPliku();

		try {
			AmazonS3 s3client = new AmazonS3Client(new BasicAWSCredentials(accessKey, secretKey));
			S3Object object = s3client.getObject(new GetObjectRequest(bucketName, key));
			InputStream inputStream = object.getObjectContent();

			response.setContentType(zdjecie.getTypPliku());
			response.setContentLength(zdjecie.getRozmiarPliku());

			String headerValue = String.format("attachment; filename=\"%s\"", zdjecie.getNazwaOryginalnaPliku());
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
			logger.info("B³¹d przy pobieraniu pliku", ex);
			response.setStatus(404);
		}
	}

	@Secured("ROLE_USER")
	@RequestMapping(value = "/kot-{id1}/zdjecie/zdjecie-{zdjecieId}/usun", method = RequestMethod.GET)
	public String usunFotke(@PathVariable("id1") Long id1, @PathVariable("zdjecieId") Long zdjecieId) {

		String bucketName = "anikiel.tutorial";
		String accessKey = "AKIAJFDXOVEICJ2K444A";
		String secretKey = "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";

		Zdjecie zdjecie = zdjecieDao.findById(zdjecieId);
		String key = zdjecie.getNazwaPliku();

		try {
			AmazonS3 s3client = new AmazonS3Client(new BasicAWSCredentials(accessKey, secretKey));
			s3client.deleteObject(new DeleteObjectRequest(bucketName, key));

			zdjecieDao.delete(zdjecieId);
		} catch (Exception e) {
			logger.error("Delete operation is failed.");
		}

		return "redirect:/kot-{id1}";
	}

	private String handleFileUpload(MultipartFile file) {
		if (!file.isEmpty()) {
			try {
				UUID uuid = UUID.randomUUID();

				String filename = "uploads/upload_" + uuid.toString();
				String bucketName = "anikiel.tutorial";
				String accessKey = "AKIAJFDXOVEICJ2K444A";
				String secretKey = "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";

				byte[] bytes = file.getBytes();
				InputStream inputStream = new ByteArrayInputStream(bytes);
				AmazonS3 s3client = new AmazonS3Client(new BasicAWSCredentials(accessKey, secretKey));
				s3client.putObject(new PutObjectRequest(bucketName, filename, inputStream, new ObjectMetadata()));

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
