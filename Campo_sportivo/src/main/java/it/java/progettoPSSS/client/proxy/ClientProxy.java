package it.java.progettoPSSS.client.proxy;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import it.java.progettoPSSS.server.proxy.*;
import it.java.progettoPSSS.externalservice.ISistemaPagamentoProxy;
import it.java.progettoPSSS.server.domain.*;


public class ClientProxy {

	/**
	 * 
	 * @param username
	 * @param password
	 */
	public int login(String username, String password) {
		String host = "localhost";
		/*
		 * if(!hostname.equals("NULL")) host = hostname;
		 */

		int result = 0;

		try {
			Registry registry = LocateRegistry.getRegistry(host);
			IServerProxy stub = (IServerProxy) registry.lookup("Server");

			// Chiamate al server tipo:
			result = stub.login(username,password);

		} catch (Exception e) {
			e.printStackTrace();

		}

		return result;
	}

	public boolean registrazione(String username, String password, String email) {
		String host = "localhost";
		/*
		 * if(!hostname.equals("NULL")) host = hostname;
		 */

		boolean result = false;

		try {
			Registry registry = LocateRegistry.getRegistry(host);
			IServerProxy stub = (IServerProxy) registry.lookup("Server");

			// Chiamate al server tipo:
			result = stub.registrazione(username,password,email);

		} catch (Exception e) {
			e.printStackTrace();

		}

		return result;
		
	}
	
	
	/**
	 * 
	 * @param sport
	 */
	public ArrayList<Campo> listaCampiDisponibili() {
		String host = "localhost";
		/*
		 * if(!hostname.equals("NULL")) host = hostname;
		 */

		ArrayList<Campo> result = new ArrayList<Campo>();

		try {
			Registry registry = LocateRegistry.getRegistry(host);
			IServerProxy stub = (IServerProxy) registry.lookup("Server");

			// Chiamate al server tipo:
			result = stub.listaCampiDisponibili();

		} catch (Exception e) {
			e.printStackTrace();

		}

		return result;
	}

	/**
	 * 
	 * @param campo
	 */
	public ArrayList<Prenotazione> listaPrenotazioniDisponibili(int id) {
		String host = "localhost";
		/*
		 * if(!hostname.equals("NULL")) host = hostname;
		 */

		ArrayList<Prenotazione> result = new ArrayList<Prenotazione>();

		try {
			Registry registry = LocateRegistry.getRegistry(host);
			IServerProxy stub = (IServerProxy) registry.lookup("Server");

			// Chiamate al server tipo:
			result = stub.listaPrenotazioniDisponibili(id);

		} catch (Exception e) {
			e.printStackTrace();

		}

		return result;
	}

	/**
	 * 
	 * @param utente
	 * @param prenotazione
	 */
	public boolean prenotaCampo(Utente utente, Prenotazione prenotazione) {
		String host = "localhost";
		/*
		 * if(!hostname.equals("NULL")) host = hostname;
		 */

		boolean result = false;
		try {
			Registry registry = LocateRegistry.getRegistry(host);
			IServerProxy stub = (IServerProxy) registry.lookup("Server");

			// Chiamate al server tipo:
			result=stub.prenotaCampo(utente, prenotazione);

		} catch (Exception e) {
			e.printStackTrace();

		}
		
		return result;
	}
	
	public boolean pagamento(String titolare, String codice, String mese, String anno, String cvv) {
		String host = "localhost";
		/*
		 * if(!hostname.equals("NULL")) host = hostname;
		 */

		boolean result = false;
		try {
			Registry registry = LocateRegistry.getRegistry(host);
			IServerProxy stub = (IServerProxy) registry.lookup("Server");

			// Chiamate al server tipo:
			result=stub.pagamento(titolare,codice,mese,anno,cvv);

		} catch (Exception e) {
			e.printStackTrace();

		}
		
		return result;
	}

	/**
	 * 
	 * @param utente
	 */
	public ArrayList<Prenotazione> prenotazioniEffettuate(int id_utente) {
		String host = "localhost";
		/*
		 * if(!hostname.equals("NULL")) host = hostname;
		 */

		ArrayList<Prenotazione> result = new ArrayList<Prenotazione>();
		try {
			Registry registry = LocateRegistry.getRegistry(host);
			IServerProxy stub = (IServerProxy) registry.lookup("Server");

			// Chiamate al server tipo:
			result=stub.prenotazioniEffettuate(id_utente);

		} catch (Exception e) {
			e.printStackTrace();

		}
		
		return result;
	
	}

	/**
	 * 
	 * @param utente
	 * @param prenotazione
	 */
	public boolean annullaPrenotazione(Prenotazione prenotazione, Utente utente) {
		String host = "localhost";
		/*
		 * if(!hostname.equals("NULL")) host = hostname;
		 */

		boolean result = false;

		try {
			Registry registry = LocateRegistry.getRegistry(host);
			IServerProxy stub = (IServerProxy) registry.lookup("Server");

			// Chiamate al server tipo:
			result = stub.annullaPrenotazione(prenotazione, utente);

		} catch (Exception e) {
			e.printStackTrace();

		}

		return result;
	}

	/**
	 * 
	 * @param utente
	 */
	public ArrayList<Ticket> listaTicket(int id_utente) {
		String host = "localhost";
		/*
		 * if(!hostname.equals("NULL")) host = hostname;
		 */

		ArrayList<Ticket> result = new ArrayList<Ticket>();
		try {
			Registry registry = LocateRegistry.getRegistry(host);
			IServerProxy stub = (IServerProxy) registry.lookup("Server");

			// Chiamate al server tipo:
			result=stub.listaTicket(id_utente);

		} catch (Exception e) {
			e.printStackTrace();

		}
		
		return result;
	}

	/**
	 * 
	 * @param utente
	 */
	public int visualizzaPuntiPremium(int id_utente) {
		String host = "localhost";
		/*
		 * if(!hostname.equals("NULL")) host = hostname;
		 */

		int result = 0;
		try {
			Registry registry = LocateRegistry.getRegistry(host);
			IServerProxy stub = (IServerProxy) registry.lookup("Server");

			// Chiamate al server tipo:
			result=stub.visualizzaPuntiPremium(id_utente);

		} catch (Exception e) {
			e.printStackTrace();

		}
		
		return result;
	}

	/**
	 * 
	 * @param ticket
	 */
	public void stampaTicket(Ticket ticket) {
		// TODO - implement ClientProxy.stampaTicket
		throw new UnsupportedOperationException();
	}

}