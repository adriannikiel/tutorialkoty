package pl.kobietydokodu.cats.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Represent the cat. Cat can have many toys and one photo.
 */
@Entity
@Table(name = "cats")
public class Cat {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "birthday", nullable = false)
	private Date birthday;

	@Column(name = "weight", nullable = false)
	private Float weight;

	@Column(name = "guardian_name", nullable = false)
	private String guardianName;
/**
 * For Rest webservices it is neccessary to ignore that field during converting to JSON
 */
	@JsonIgnore
	@OneToMany(mappedBy = "kitten")
	List<Toy> toys;
	/**
	 * For Rest webservices it is neccessary to ignore that field during converting to JSON
	 */
	@JsonIgnore
	@OneToOne(mappedBy = "kitten")
	Photo photo;

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

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Float getWeight() {
		return weight;
	}

	public void setWeight(Float weight) {
		this.weight = weight;
	}

	public String getGuardianName() {
		return guardianName;
	}

	public void setGuardianName(String guardianName) {
		this.guardianName = guardianName;
	}

	public List<Toy> getToys() {
		return toys;
	}

	public void setToys(List<Toy> toys) {
		this.toys = toys;
	}

	public Photo getPhoto() {
		return photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}

	@Override
	public String toString() {
		return "{\"id\":" + id + ",\"name\":\"" + name + "\",\"birthday\":" + birthday.getTime() + ",\"weight\":"
				+ weight + ",\"guardianName\":\"" + guardianName + "\"}";
	}

}
