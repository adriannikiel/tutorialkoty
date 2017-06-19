package pl.kobietydokodu.cats.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Represents a toy that belongs to the cat.
 */
@Entity
@Table(name="toys")
public class Toy {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="name", nullable=false)
    private String name;
	
	@Column(name="production_date", nullable=false)
    private Date productionDate;
	
	@Column(name="weight", nullable=false)
    private Float weight;

	@ManyToOne
	@JoinColumn(name = "cat_id")
	Cat kitten;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getProductionDate() {
		return productionDate;
	}

	public void setProductionDate(Date productionDate) {
		this.productionDate = productionDate;
	}

	public Float getWeight() {
		return weight;
	}

	public void setWeight(Float weight) {
		this.weight = weight;
	}

	public Cat getKitten() {
		return kitten;
	}

	public void setKitten(Cat kitten) {
		this.kitten = kitten;
	}

	

}
