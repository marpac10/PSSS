package it.java.progettoPSSS.server.proxy;

import java.rmi.RemoteException;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import it.java.progettoPSSS.server.domain.Campo;
import it.java.progettoPSSS.server.domain.CentroSportivo;
import it.java.progettoPSSS.server.domain.Prenotazione;
import it.java.progettoPSSS.server.domain.Ticket;
import it.java.progettoPSSS.server.domain.Utente;
import it.java.progettoPSSS.server.control.*;
import it.java.progettoPSSS.server.db.HibernateUtil;


public class ServerProxy implements IServerProxy {
	
	public static Registry registry = null;
	

	public int login(String username, String password) throws RemoteException {
		
		return ControllerUtente.getIstance().login(username, password);
	}

	public boolean registrazione(String username, String password, String email) {
		
		return ControllerUtente.getIstance().registrazione(username, password, email);
	}
	
	public boolean pagamento(String titolare, String codice, String mese, String anno, String cvv) {
		
		return ControllerUtente.getIstance().pagamento(titolare, codice, mese, anno, cvv);
	}
	
	public ArrayList<Campo> listaCampiDisponibili() throws RemoteException {
			return ControllerUtente.getIstance().listaCampiDisponibili();
	}

	public ArrayList<Prenotazione> listaPrenotazioniDisponibili(int id) throws RemoteException {
		return ControllerUtente.getIstance().listaPrenotazioniDisponibili(id);
		
	}

	public boolean prenotaCampo(Utente utente, Prenotazione prenotazione) throws RemoteException {
		return	 ControllerUtente.getIstance().prenotaCampo(utente, prenotazione);
		
	}

	public ArrayList<Prenotazione> prenotazioniEffettuate(int id_utente) throws RemoteException {
		return  ControllerUtente.getIstance().prenotazioniEffettuate(id_utente);
		
	}

	public boolean annullaPrenotazione(Prenotazione prenotazione, Utente utente) throws RemoteException {
		return ControllerUtente.getIstance().annullaPrenotazione(prenotazione, utente);
		
	}

	public ArrayList<Ticket> listaTicket(int id_utente) throws RemoteException {
	
		return ControllerUtente.getIstance().listaTicket(id_utente);
	}

	public int visualizzaPuntiPremium(int id_utente) throws RemoteException {
		return ControllerUtente.getIstance().visualizzaPuntiPremium(id_utente);
		
	}

	public void stampaTicket(Ticket ticket) throws RemoteException {
		// TODO Auto-generated method stub
		
	}
	
	
public static void main(String[] args) {
	
	ServerProxy s = new ServerProxy();
	ServerProxyFacade f = new ServerProxyFacade(s);
	f.startServer();
	
	
	
	}
	


	public void startServer () {

	try {
		registry = LocateRegistry.createRegistry(1099);
	
		ServerProxy server = new ServerProxy();
		IServerProxy serverStub = (IServerProxy) UnicastRemoteObject.exportObject(server, 0);
		Registry registry = LocateRegistry.getRegistry();
		registry.rebind("Server", serverStub);
		System.out.println("Server ready");
	
	} catch(Exception e) {
		
		e.printStackTrace();
		
	}
	}
	
	
	public void loadPrenotazioni () {
		
		LocalDate oggi = LocalDate.now();
		LocalTime ora = LocalTime.now();
		
		int da_creare = 23 - ora.getHour(); // alle 23 è l'ultimo orario valido per la prenotazione
		
		CentroSportivo c = new CentroSportivo();
		
		ArrayList<Campo> campi = new ArrayList<Campo>();
		
		campi = c.listaCampiDisponibili();
		
		for (Campo campo : campi) {
			
		
		
		for (int i = 0; i < da_creare ; i++)
		{
				Prenotazione p = new Prenotazione();
				LocalTime t = LocalTime.of(ora.getHour()+i+1, 0);
				p.LoadPrenotazione(oggi,t,campo);
	}
	}
	}
	
	
	public void deletePrenotazioni () {
		LocalDate oggi = LocalDate.now();
		LocalTime ora = LocalTime.now();
		
			
		CentroSportivo c = new CentroSportivo();
		
		ArrayList<Campo> campi = new ArrayList<Campo>();
		
		campi = c.listaCampiDisponibili();
		
		System.out.println("Campi trovati : " + campi.size());
		
		for (Campo campo : campi) {
			
		ArrayList<Prenotazione> pren = campo.listaPrenotazioniDisponibili();
		
		System.out.println("Prenotazioni trovati : " + pren.size());
		
		for (Prenotazione prenotazione : pren) {
			
			
			
			if (prenotazione.getData().getDayOfYear() < oggi.getDayOfYear() || (prenotazione.getData().getDayOfYear() == oggi.getDayOfYear() && prenotazione.getOra().getHour() <= ora.getHour())) {
			prenotazione.DeletePrenotazione(prenotazione.getId());
		}
		
	}
		
		
	}
	
	}
}