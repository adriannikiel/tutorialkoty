package pl.kobietydokodu.koty.domain;

import java.util.Date;

public class Kot {

	private int id;
    private String imie;
    private Date dataUrodzenia;
    private Float waga;
    private String imieOpiekuna;

    public Kot(int id, String imie, Date dataUrodzenia, Float waga, String imieOpiekuna) {
    	this.id = id;
		this.imie = imie;
		this.dataUrodzenia = dataUrodzenia;
		this.waga = waga;
		this.imieOpiekuna = imieOpiekuna;
	}

	public Kot() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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
