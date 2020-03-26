package com.rados.model;

public class Film {
	private int idFilma;
	private String naslov;
	private int trajanje;
	private int godina;
	private String zanr;
	private String aka;
	private String trailer;
	private String opis;

	public Film() {
		// TODO Auto-generated constructor stub
	}

	



	public Film(int idFilma, String naslov, int trajanje, int godina, String zanr, String aka, String trailer,
			String opis) {
		super();
		this.idFilma = idFilma;
		this.naslov = naslov;
		this.trajanje = trajanje;
		this.godina = godina;
		this.zanr = zanr;
		this.aka = aka;
		this.trailer = trailer;
		this.opis = opis;
	}





	public Film(int idFilma, String naslov, int trajanje, int godina, String zanr, String aka, String trailer) {
		super();
		this.idFilma = idFilma;
		this.naslov = naslov;
		this.trajanje = trajanje;
		this.godina = godina;
		this.zanr = zanr;
		this.aka = aka;
		this.trailer = trailer;
	}





	public Film(int idFilma, String naslov, int trajanje, int godina, String zanr, String aka) {
		super();
		this.idFilma=idFilma;
		this.naslov = naslov;
		this.trajanje = trajanje;
		this.godina = godina;
		this.zanr = zanr;
		this.aka = aka;
	}
	public Film(String naslov, int trajanje, int godina, String zanr, String aka) {
		super();
		
		this.naslov = naslov;
		this.trajanje = trajanje;
		this.godina = godina;
		this.zanr = zanr;
		this.aka = aka;
	}
	public String getNaslov() {
		return naslov;
	}

	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}

	public int getTrajanje() {
		return trajanje;
	}

	public void setTrajanje(int trajanje) {
		this.trajanje = trajanje;
	}

	public int getGodina() {
		return godina;
	}

	public void setGodina(int godina) {
		this.godina = godina;
	}

	public String getZanr() {
		return zanr;
	}

	public void setZanr(String zanr) {
		this.zanr = zanr;
	}

	public String getAka() {
		return aka;
	}

	public void setAka(String aka) {
		this.aka = aka;
	}
	

	public int getIdFilma() {
		return idFilma;
	}
	

	public void setIdFilma(int idFilma) {
		this.idFilma = idFilma;
	}
	
	

	public String getTrailer() {
		return trailer;
	}





	public void setTrailer(String trailer) {
		this.trailer = trailer;
	}
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}





	@Override
	public String toString() {
		return "Film [idFilma=" + idFilma + ", naslov=" + naslov + ", trajanje=" + trajanje + ", godina=" + godina
				+ ", zanr=" + zanr + ", aka=" + aka + ", trailer=" + trailer + "]";
	}

}
