package pl.kobietydokodu.koty.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="zabawki")
public class Zabawka {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="nazwa", nullable=false)
    private String nazwa;
	
	@Column(name="data_produkcji", nullable=false)
    private Date dataProdukcji;
	
	@Column(name="waga", nullable=false)
    private Float waga;

	@ManyToOne
	@JoinColumn(name = "kotek_id")
	Kot kotek;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public Date getDataProdukcji() {
		return dataProdukcji;
	}

	public void setDataProdukcji(Date dataProdukcji) {
		this.dataProdukcji = dataProdukcji;
	}

	public Float getWaga() {
		return waga;
	}

	public void setWaga(Float waga) {
		this.waga = waga;
	}

	public Kot getKotek() {
		return kotek;
	}

	public void setKotek(Kot kotek) {
		this.kotek = kotek;
	}

	

}
