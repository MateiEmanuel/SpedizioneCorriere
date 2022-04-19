import java.io.Serializable;

public class Cliente implements Serializable{

	private String codFiscale;
	private String denominazione;
	private String indirizzo;
	private String città;
	private String telefono;

	
	
	public Cliente(String codFiscale, String denominazione, String indirizzo, String città, String telefono) {
		super();
		this.codFiscale = codFiscale;
		this.denominazione = denominazione;
		this.indirizzo = indirizzo;
		this.città = città;
		this.telefono = telefono;
	}

	public Cliente() {

	}

	public Cliente(Cliente c) {
		this.codFiscale = c.codFiscale;
		this.denominazione = c.denominazione;
		this.indirizzo = c.indirizzo;
		this.città = c.città;
		this.telefono = c.telefono;
	}

	public String getCodFiscale() {
		return codFiscale;
	}

	public void setCodFiscale(String codFiscale) {
		this.codFiscale = codFiscale;
	}

	public String getDenominazione() {
		return denominazione;
	}

	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getCittà() {
		return città;
	}

	public void setCittà(String città) {
		this.città = città;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String toString() {
		return codFiscale + ", " + denominazione + ", " + indirizzo + ", " + città + ", " + telefono;
	}

}
