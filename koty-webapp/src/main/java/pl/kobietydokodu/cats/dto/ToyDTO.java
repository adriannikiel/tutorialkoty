package pl.kobietydokodu.cats.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

public class ToyDTO {
	
	@NotBlank
	private String name;
	
	@NotBlank
	@Pattern(regexp = "[0-3]?[0-9]\\.[0-1]?[0-9]\\.[1-2][0-9]{3}")
	private String productionDate;
	
	@NotNull
	private Float weight;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProductionDate() {
		return productionDate;
	}

	public void setProductionDate(String productionDate) {
		this.productionDate = productionDate;
	}

	public Float getWeight() {
		return weight;
	}

	public void setWeight(Float weight) {
		this.weight = weight;
	}

}
