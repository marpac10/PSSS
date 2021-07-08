package it.java.progettoPSSS.client.control;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import it.java.progettoPSSS.client.proxy.*;
import it.java.progettoPSSS.server.domain.*;

public class ControllerUtente {
	
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

	/**
	 * 
	 * @param username
	 * @param password
	 */
	public int login(String username, String password) {
		ClientProxy clientProxy = new ClientProxy();
		return clientProxy.login(username,password);
	}
	
	
	public boolean registrazione(String username, String password, String email) {
		ClientProxy clientProxy = new ClientProxy();
		return clientProxy.registrazione(username,password,email);
	}
	
	public int checkRegistrazione(String user, String pass, String email) {
		if(Pattern.matches(("[a-zA-Z[0-9]]+"),user) == false || user.length()<1)
			return 0;
		else if(Pattern.matches(("[\\s]+"),pass) == true || pass.length()<6)
			return 1;
		else if((Pattern.matches(("[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}"),email) == false))
			return 2;
		else return 3;
	}
	
	
	public int checkPagamento(String titolare, String codice, String mese, String anno, String cvv) {
		if(Pattern.matches(("[a-zA-Z[\\s]]+"),titolare) == false)
			return 0;
		else if(Pattern.matches(("[0-9]+"),codice) == false || codice.length()!=16)
			return 1;
		else if((Pattern.matches(("[0-9]+"),mese) == false || mese.length()!=2) || (Integer.parseInt(mese)<1 || Integer.parseInt(mese)>12))
			return 2;
		else if((Pattern.matches(("[0-9]+"),anno) == false || anno.length()!=2) || (Integer.parseInt(anno)+2000) < LocalDate.now().getYear())
			return 3;
		else if((Pattern.matches(("[0-9]+"),cvv) == false || cvv.length()!=3))
			return 4;
		else return 5;
	}

	
	public boolean pagamento(String titolare, String codice, String mese, String anno, String cvv) {
		ClientProxy clientProxy = new ClientProxy();
		return clientProxy.pagamento(titolare,codice,mese,anno,cvv);
	}
	/**
	 * 
	 * @param sport
	 */
	public ArrayList<Campo> listaCampiDisponibili() {
		ClientProxy clientProxy = new ClientProxy();
		return clientProxy.listaCampiDisponibili();
	}

	/**
	 * 
	 * @param campo
	 */
	public ArrayList<Prenotazione> listaPrenotazioniDisponibili(int id) {
		ClientProxy clientProxy = new ClientProxy();
		ArrayList<Prenotazione> prenotazioni = clientProxy.listaPrenotazioniDisponibili(id);
		ArrayList<Prenotazione> new_prenotazioni = new ArrayList<Prenotazione>();
		
		for (Prenotazione prenotazione : prenotazioni) {
			if((prenotazione.getData().getDayOfYear() > LocalDate.now().getDayOfYear()) || (prenotazione.getData().getDayOfYear() == LocalDate.now().getDayOfYear() && prenotazione.getOra().getHour() > LocalTime.now().getHour()))
				new_prenotazioni.add(prenotazione);
		}
		return new_prenotazioni;
	}

	/**
	 * 
	 * @param utente
	 * @param prenotazione
	 */
	public boolean prenotaCampo(Utente utente, Prenotazione prenotazione) {
		ClientProxy clientProxy = new ClientProxy();
		return clientProxy.prenotaCampo(utente, prenotazione);
	}

	/**
	 * 
	 * @param utente
	 */
	public ArrayList<Prenotazione> prenotazioniEffettuate(int id_utente) {
		ClientProxy clientProxy = new ClientProxy();
		ArrayList<Prenotazione> prenotazioni = clientProxy.prenotazioniEffettuate(id_utente);
		ArrayList<Prenotazione> new_prenotazioni = new ArrayList<Prenotazione>();
		
		for (Prenotazione prenotazione : prenotazioni) {
			if((prenotazione.getData().getDayOfYear() > LocalDate.now().getDayOfYear()) || (prenotazione.getData().getDayOfYear() == LocalDate.now().getDayOfYear() && prenotazione.getOra().getHour() > LocalTime.now().getHour()))
				new_prenotazioni.add(prenotazione);
		}
		return new_prenotazioni;
	}

	/**
	 * 
	 * @param utente
	 * @param prenotazione
	 */
	public boolean annullaPrenotazione(Prenotazione prenotazione, Utente utente) {
		ClientProxy clientProxy = new ClientProxy();
		return clientProxy.annullaPrenotazione(prenotazione, utente);
	}

	/**
	 * 
	 * @param utente
	 */
	public ArrayList<Ticket> listaTicket(int id_utente) {
		ClientProxy clientProxy = new ClientProxy();
		return clientProxy.listaTicket(id_utente);
	}

	/**
	 * 
	 * @param utente
	 */
	public int visualizzaPuntiPremium(int id_utente) {
		ClientProxy clientProxy = new ClientProxy();
		return clientProxy.visualizzaPuntiPremium(id_utente);
	}

	/**
	 * 
	 * @param ticket
	 */
	public void stampaTicket(Ticket ticket) {
		// TODO - implement ControllerCliente.stampaTicket
		throw new UnsupportedOperationException();
	}

}