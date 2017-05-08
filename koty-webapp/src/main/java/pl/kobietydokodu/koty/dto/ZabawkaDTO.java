package pl.kobietydokodu.koty.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

public class ZabawkaDTO {
	
	@NotBlank
	private String nazwa;
	
	@NotBlank
	@Pattern(regexp="[0-3]?[0-9]\\.[0-1]?[0-9]\\.[1-2][0-9]{3}")
	private String dataProdukcji;
	
	@NotNull
	private Float waga;

	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public String getDataProdukcji() {
		return dataProdukcji;
	}

	public void setDataProdukcji(String dataProdukcji) {
		this.dataProdukcji = dataProdukcji;
	}

	public Float getWaga() {
		return waga;
	}

	public void setWaga(Float waga) {
		this.waga = waga;
	}

}
