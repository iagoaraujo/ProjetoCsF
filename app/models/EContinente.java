package models;

public enum EContinente {

	AFRICA("África"),
	AMERICA_DO_NORTE("América do Norte"),
	AMERICA_LATINA("América Latina"),
	ASIA("Ásia"),
	EUROPA("Europa"),
	OCEANIA("Oceania");
	
    private final String continente;
	
	private EContinente(String continente) {
		this.continente = continente;
	}
	
	public String getContinente() {
		return continente;
	}
}
