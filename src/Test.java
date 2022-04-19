import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

public class Test implements Serializable {
	private static Scanner input = new Scanner(System.in);

	private static int menu() {
		int risposta;
		System.out.println("Menù:");
		System.out.println("1) inserisci nuovo cliente");
		System.out.println("2) memorizza spedizione");
		System.out.println("3) informazioni su una spedizione.");
		System.out.println("4) salva dati clienti");
		System.out.println("5) salva dati spedizioni");
		System.out.println("6) dati clienti");
		System.out.println("7) dati spedizioni");
		System.out.println("0) fine programma.");
		System.out.println("cosa vuoi fare?");
		risposta = input.nextInt();
		return risposta;
	}

	private static Cliente nuovoCliente() {
		String cod;
		String denominazione;
		String nome;
		String cognome;
		String indirizzo;
		String città;
		String telefono;
		System.out.println("Codice fiscale: ");
		cod = input.next();
		System.out.println("Nome: ");
		nome = input.next();
		System.out.println("Cognome: ");
		cognome = input.next();
		denominazione = nome + " " + cognome;
		System.out.println("Indirizzo: ");
		indirizzo = input.nextLine(); //ho messo 2 nextLine perche altrimenti il programma salta l'input
		indirizzo = input.nextLine();
		System.out.println("Città: ");
		città = input.next();
		System.out.println("telefono: ");
		telefono = input.next();
		

		return new Cliente(cod, denominazione, indirizzo, città, telefono);

	}

	private static Spedizione memSpedizione(Cliente mittente, Cliente destinatario) {
		String id;
		String descrizione;
		int giorno;
		int mese;
		int anno;
		System.out.print("codice: ");
		id = input.next();
		System.out.print("Descrizione: ");
		descrizione = input.next();
		System.out.println("Data di consegna: ");
		System.out.print("giorno: ");
		giorno = input.nextInt();
		System.out.print("mese: ");
		mese = input.nextInt();
		System.out.print("anno: ");
		anno = input.nextInt();

		return new Spedizione(id, descrizione, LocalDate.of(anno, mese, giorno), mittente, destinatario);

	}

	public static void salvaClienti(TreeMap<String, Cliente> clienti) {
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream("clienti.bin"));
			oos.writeObject(clienti);
			oos.close();
			System.out.println("Dati salvati nel file clienti.bin");
		} catch (IOException e) {
			System.out.println("Errore nella scrittura del file");
		}
	}

	public static TreeMap<String, Cliente> caricaClienti() {
		ObjectInputStream ois = null;
		TreeMap<String, Cliente> clienti;
		try {
			ois = new ObjectInputStream(new FileInputStream("clienti.bin"));
			clienti = (TreeMap<String, Cliente>) ois.readObject();
			ois.close();
			System.out.println("Lettura dati dal file clienti.bin");
			return clienti;
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Errore nella lettura da file");
		}
		return null;
	}

	public static void salvaSpedizioni(HashMap<String, Spedizione> spedizioni) {
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream("spedizioni.bin"));
			oos.writeObject(spedizioni);
			oos.close();
			System.out.println("Dati salvati nel file spedizioni.bin");
		} catch (IOException e) {
			System.out.println("Errore nella scrittura del file");
		}
	}

	public static HashMap<String, Spedizione> caricaSpedizioni() {
		ObjectInputStream ois = null;
		HashMap<String, Spedizione> spedizioni;
		try {
			ois = new ObjectInputStream(new FileInputStream("spedizioni.bin"));
			spedizioni = (HashMap<String, Spedizione>) ois.readObject();
			ois.close();
			System.out.println("Lettura dati dal file spedizioni.bin");
			return spedizioni;
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Errore nella lettura da file");
		}
		return null;
	}

	public static void main(String[] args) {
		TreeMap<String, Cliente> clienti = new TreeMap<>();
		HashMap<String, Spedizione> spedizioni = new HashMap<>();
		int ris;

			clienti = caricaClienti();
			spedizioni = caricaSpedizioni();
		

		do {
			ris = menu();

			switch (ris) {
			case 1:
				Cliente c = nuovoCliente();
				clienti.put(c.getCodFiscale(), c);
				break;

			case 2:
				String codMittente;
				String codDestinatario;
				System.out.print("codice fiscale mittente: ");
				codMittente = input.next();
				System.out.print("codice fiscale destinatario: ");
				codDestinatario = input.next();
				if (clienti.containsKey(codMittente) && clienti.containsKey(codDestinatario)) {
					Spedizione s = memSpedizione(clienti.get(codMittente), clienti.get(codDestinatario));
					spedizioni.put(s.getId(), s);
				} else {
					System.out.println("Mittente o destinatario errati");
				}

				break;
			case 3:
				String id;
				System.out.print("codice spedizione: ");
				id = input.next();
				if (spedizioni.containsKey(id)) {
					System.out.println(spedizioni.get(id).toString());
				} else {
					System.out.println("Spedizione non trovata");
				}
				break;
			case 4:
				salvaClienti(clienti);
				break;
			case 5:
				salvaSpedizioni(spedizioni);
				break;
			case 6:
				for (Cliente cl : clienti.values()) {
					System.out.println(cl.toString());
				}
				break;
			case 7:
				for (Spedizione sp : spedizioni.values()) {
					System.out.println(sp.toString());
				}
				break;
			}

		} while (ris != 0);

	}
}
