package pl.kobietydokodu.koty.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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

	public Kot(Long id, String imie, Date dataUrodzenia, Float waga, String imieOpiekuna) {
    	this.id = id;
		this.imie = imie;
		this.dataUrodzenia = dataUrodzenia;
		this.waga = waga;
		this.imieOpiekuna = imieOpiekuna;
	}

	public Kot() {
	}

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

}
