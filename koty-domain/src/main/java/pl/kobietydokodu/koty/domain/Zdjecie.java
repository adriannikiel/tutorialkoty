package pl.kobietydokodu.koty.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "zdjecia")
public class Zdjecie {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "nazwa_oryginalna_pliku", nullable = false)
	private String nazwaOryginalnaPliku;

	@Column(name = "nazwa_pliku", nullable = false)
	private String nazwaPliku;

	@Column(name = "opis_pliku", length = 512)
	private String opisPliku;

	@Column(name = "rozmiar_pliku")
	private int rozmiarPliku;

	@Column(name = "typ_pliku")
	private String typPliku;

	@OneToOne
	@JoinColumn(name = "kotek_id")
	private Kot kotek;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNazwaOryginalnaPliku() {
		return nazwaOryginalnaPliku;
	}

	public void setNazwaOryginalnaPliku(String nazwaOryginalnaPliku) {
		this.nazwaOryginalnaPliku = nazwaOryginalnaPliku;
	}

	public String getNazwaPliku() {
		return nazwaPliku;
	}

	public void setNazwaPliku(String nazwaPliku) {
		this.nazwaPliku = nazwaPliku;
	}

	public String getOpisPliku() {
		return opisPliku;
	}

	public void setOpisPliku(String opisPliku) {
		this.opisPliku = opisPliku;
	}

	public int getRozmiarPliku() {
		return rozmiarPliku;
	}

	public void setRozmiarPliku(int rozmiarPliku) {
		this.rozmiarPliku = rozmiarPliku;
	}

	public String getTypPliku() {
		return typPliku;
	}

	public void setTypPliku(String typPliku) {
		this.typPliku = typPliku;
	}

	public Kot getKotek() {
		return kotek;
	}

	public void setKotek(Kot kotek) {
		this.kotek = kotek;
	}

}
