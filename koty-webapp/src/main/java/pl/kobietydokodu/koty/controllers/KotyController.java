package pl.kobietydokodu.koty.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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

		// if (kotDao.findById(id).getFotka() != null) {
		// model.addAttribute("fotka",
		// kotDao.findById(id).getFotka().getNazwaPliku());
		// }

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
		return "kot-{id}/zdjecie/dodaj";
	}

	@RequestMapping(value = "/kot-{id1}/zdjecie/zdjecie-{zdjecieId}", method = RequestMethod.GET)
	public void pobierzFotke(@PathVariable("id1") Long id1, @PathVariable("zdjecieId") Long zdjecieId,
			HttpServletResponse response) throws IOException {

		final int BUFFER_SIZE = 1000000;
		Zdjecie zdjecie = zdjecieDao.findById(zdjecieId);

		String foldername = "D:\\Programming\\Eclipse\\workspace\\kobietyDoKodu\\AplikacjeWeboweSpring\\tutorialkoty\\koty-webapp\\resources\\uploads\\";
		FileInputStream inputStream = new FileInputStream(foldername + zdjecie.getNazwaPliku());

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
	}

	@Secured("ROLE_USER")
	@RequestMapping(value = "/kot-{id1}/zdjecie/zdjecie-{zdjecieId}/usun", method = RequestMethod.GET)
	public String usunFotke(@PathVariable("id1") Long id1, @PathVariable("zdjecieId") Long zdjecieId) {

		Zdjecie zdjecie = zdjecieDao.findById(zdjecieId);

		String foldername = "D:\\Programming\\Eclipse\\workspace\\kobietyDoKodu\\AplikacjeWeboweSpring\\tutorialkoty\\koty-webapp\\resources\\uploads\\";
		File file = new File(foldername + zdjecie.getNazwaPliku());

		if (file.delete()) {
			logger.info("File has been successfully deleted");
		} else {
			logger.error("Delete operation is failed.");
		}

		zdjecieDao.delete(zdjecieId);

		return "redirect:/kot-{id1}";
	}

	public void pokazFotke() {

	}

	private String handleFileUpload(MultipartFile file) {
		if (!file.isEmpty()) {
			try {
				UUID uuid = UUID.randomUUID();

				String originalFilename = file.getOriginalFilename();
				String extension = "";

				int i = file.getOriginalFilename().lastIndexOf('.');
				if (i > 0) {
					extension = originalFilename.substring(i + 1);
				}

				String filename = "D:\\Programming\\Eclipse\\workspace\\kobietyDoKodu\\AplikacjeWeboweSpring\\tutorialkoty\\koty-webapp\\resources\\uploads\\upload_"
						+ uuid.toString() + "." + extension;
				byte[] bytes = file.getBytes();
				File fsFile = new File(filename);
				fsFile.createNewFile();
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(fsFile));
				stream.write(bytes);
				stream.close();

				logger.info("File {} has been successfully uploaded as {}",
						new Object[] { file.getOriginalFilename(), filename });

				return fsFile.getName();

			} catch (Exception e) {
				logger.error("File has not been uploaded", e);
			}
		} else {
			logger.error("Uploaded file is empty");
		}
		return null;
	}

}
