package it.java.progettoPSSS.server.control;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import it.java.progettoPSSS.externalservice.ISistemaPagamentoProxy;
import it.java.progettoPSSS.server.domain.*;

public class ControllerUtente {

	/**
	 * 
	 * @param username
	 * @param password
	 */
	
	private static ControllerUtente istance = null;
	
	private ControllerUtente() {
		super();
	}

	public static ControllerUtente getIstance() {
		if (istance==null) {
			istance = new ControllerUtente();
		}
		return istance;
	}

	
	
	
	public int login(String username, String password) {
		Utente u = new Utente();
		return u.login(username, password);
	}

	
	public boolean registrazione(String username, String password, String email) {
		Utente u = new Utente();
		return u.registrazione(username, password, email);
	}
	
	public boolean pagamento(String titolare, String codice, String mese, String anno, String cvv) {
		Utente u = new Utente();
		return u.pagamento(titolare, codice, mese, anno, cvv);
	}
	
	
	public ArrayList<Campo> listaCampiDisponibili() {
		CentroSportivo c = new CentroSportivo();
		return c.listaCampiDisponibili();
	}

	/**
	 * 
	 * @param campo
	 */
	public ArrayList<Prenotazione> listaPrenotazioniDisponibili(int id) {
		Campo c = new Campo();
		c.setId(id);
		return c.listaPrenotazioniDisponibili();
	}

	/**
	 * 
	 * @param utente
	 * @param prenotazione
	 */
	public boolean prenotaCampo(Utente utente, Prenotazione prenotazione) {
		Prenotazione p = new Prenotazione();
		return p.prenotaCampo(utente,prenotazione);
	}

	/**
	 * 
	 * @param utente
	 */
	public ArrayList<Prenotazione> prenotazioniEffettuate(int id_utente) {
	Utente u = new Utente();
	u.setId(id_utente);
	return u.prenotazioniEffettuate();
		
		
	}

	/**
	 * 
	 * @param utente
	 * @param prenotazione
	 */
	public boolean annullaPrenotazione(Prenotazione prenotazione, Utente utente) {
		Prenotazione p = new Prenotazione();
		return p.annullaPrenotazione(prenotazione, utente);
	}

	/**
	 * 
	 * @param utente
	 */
	public ArrayList<Ticket> listaTicket(int id_utente) {
		Utente u = new Utente();
		u.setId(id_utente);
		return u.listaTicket();
	}

	/**
	 * 
	 * @param utente
	 */
	public int visualizzaPuntiPremium(int id_utente) {
		Utente u = new Utente();
		u.setId(id_utente);
		return u.visualizzaPuntiPremium();
	}

	/**
	 * 
	 * @param ticket
	 */
	public void stampaTicket(Ticket ticket) {
		// TODO - implement ContollerUtente.stampaTicket
		throw new UnsupportedOperationException();
	}

}