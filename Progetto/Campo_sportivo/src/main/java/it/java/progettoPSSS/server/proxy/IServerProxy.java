package it.java.progettoPSSS.server.proxy;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import it.java.progettoPSSS.server.domain.*;
public interface IServerProxy extends Remote {

	/**
	 * 
	 * @param username
	 * @param password
	 */
	int login(String username, String password) throws RemoteException;
	
	boolean registrazione(String username, String password, String email) throws RemoteException;

	/**
	 * 
	 * @param sport
	 */
	ArrayList<Campo> listaCampiDisponibili() throws RemoteException;

	/**
	 * 
	 * @param campo
	 */
	ArrayList<Prenotazione> listaPrenotazioniDisponibili(int id) throws RemoteException;

	/**
	 * 
	 * @param utente
	 * @param prenotazione
	 */
	boolean prenotaCampo(Utente utente, Prenotazione prenotazione) throws RemoteException;

	/**
	 * 
	 * @param utente
	 */
	ArrayList<Prenotazione> prenotazioniEffettuate(int id_utente) throws RemoteException;

	/**
	 * 
	 * @param utente
	 * @param prenotazione
	 */
	boolean annullaPrenotazione(Prenotazione prenotazione, Utente utente) throws RemoteException;

	/**
	 * 
	 * @param utente
	 */
	ArrayList<Ticket> listaTicket(int id_utente) throws RemoteException;

	/**
	 * 
	 * @param utente
	 */
	int visualizzaPuntiPremium(int id_utente) throws RemoteException;

	/**
	 * 
	 * @param ticket
	 */
	void stampaTicket(Ticket ticket) throws RemoteException;
	
	public boolean pagamento(String titolare, String codice, String mese, String anno, String cvv) throws RemoteException;

}