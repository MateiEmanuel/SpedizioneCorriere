import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Spedizione implements Serializable{

	private String id;
	private String descrizione;
	private LocalDate dataConsegna;
	private Cliente mittente;
	private Cliente destinatario;

	public Spedizione() {

	}
	

	

	public Spedizione(String id, String descrizione, LocalDate dataConsegna, Cliente mittente, Cliente destinatario) {
		super();
		this.id = id;
		this.descrizione = descrizione;
		this.dataConsegna = dataConsegna;
		this.mittente = mittente;
		this.destinatario = destinatario;
	}




	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public LocalDate getDataConsegna() {
		return dataConsegna;
	}

	public void setDataConsegna(LocalDate dataConsegna) {
		this.dataConsegna = dataConsegna;
	}
	
	


	private String toStringDateTime(LocalDate ldt) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String formattedDateTime = ldt.format(formatter);
		return formattedDateTime;
	}
	
	public String toString() {
		return  id + ", " + descrizione + ", " + toStringDateTime(dataConsegna) + "\n" +
	"mittente: " + this.mittente.toString() + "\n" +
				"destinatario: " + this.destinatario.toString();
	}

	
	
}
