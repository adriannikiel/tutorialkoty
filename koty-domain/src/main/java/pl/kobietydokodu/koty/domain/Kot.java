package pl.kobietydokodu.koty.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="koty")
public class Kot {

	@Id
	private Long id;
	
	@Column(name="imie", nullable=false)
    private String imie;
	
	@Column(name="data_urodzenia", nullable=false)
    private Date dataUrodzenia;
	
	@Column(name="waga", nullable=false)
    private Float waga;
	
	@Column(name="imie_opiekuna", nullable=false)
    private String imieOpiekuna;
	
	@JsonIgnore
	@OneToMany(mappedBy="kotek")
	List<Zabawka> zabawki;
	
	@JsonIgnore
	@OneToOne(mappedBy="kotek")
	Zdjecie fotka;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public Date getDataUrodzenia() {
        return dataUrodzenia;
    }

    public void setDataUrodzenia(Date dataUrodzenia) {
        this.dataUrodzenia = dataUrodzenia;
    }

    public Float getWaga() {
        return waga;
    }

    public void setWaga(Float waga) {
        this.waga = waga;
    }

    public String getImieOpiekuna() {
        return imieOpiekuna;
    }

    public void setImieOpiekuna(String imieOpiekuna) {
        this.imieOpiekuna = imieOpiekuna;
    }

	public List<Zabawka> getZabawki() {
		return zabawki;
	}

	public void setZabawki(List<Zabawka> zabawki) {
		this.zabawki = zabawki;
	}

	public Zdjecie getFotka() {
		return fotka;
	}

	public void setFotka(Zdjecie fotka) {
		this.fotka = fotka;
	}
	
	@Override
	public String toString() {
		return "{\"id\":" + id + 
				",\"imie\":\"" + imie + 
				"\",\"dataUrodzenia\":" + dataUrodzenia.getTime() +
				",\"waga\":" + waga +
				",\"imieOpiekuna\":\"" + imieOpiekuna + "\"}";
	}
	
}
