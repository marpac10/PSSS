package it.java.progettoPSSS.server.domain;

//import java.io.Serializable;
import java.io.Serializable;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import javax.persistence.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import it.java.progettoPSSS.externalservice.ISistemaPagamentoProxy;
import it.java.progettoPSSS.server.db.*;





@Entity
@Table (name = "utente")
public class Utente implements Serializable{
	
	private static final long serialVersionUID = 24L;

	private int id;
	private String username;
	private String password;
	private String email;
	private int puntipremium;
	
	@OneToMany
	@JoinColumn(name="id_utente")
	ArrayList<Prenotazione> prenotazioni;
	
	@OneToMany
	@JoinColumn(name="id_utente")
	ArrayList<Ticket> ticket;
	

	public Utente() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	




	public Utente(int id, String username, String password, ArrayList<Prenotazione> prenotazioni,
			ArrayList<Ticket> ticket) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.prenotazioni = prenotazioni;
		this.ticket = ticket;
	}








	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}


	@Column
	public String getUsername() {
		return username;
	}




	public void setUsername(String username) {
		this.username = username;
	}



	@Column
	public String getPassword() {
		return password;
	}

	@Column
	public String getEmail() {
		return email;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}



	@Column
	public int getPuntipremium() {
		return puntipremium;
	}


	public void setPuntipremium(int puntipremium) {
		this.puntipremium = puntipremium;
	}



	public ArrayList<Prenotazione> prenotazioniEffettuate() {
		HibernateUtil h= new HibernateUtil();
		h.setup();
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
	    session.beginTransaction();
	 
	    Query<Prenotazione> query = session.createQuery("from Prenotazione as p where p.id_utente = :parametro and p.disponibile = :parametro1");
		query.setParameter("parametro", this.getId());
	    query.setParameter("parametro1", false); 
	
	ArrayList<Prenotazione> result = (ArrayList<Prenotazione>) query.list();
	
	session.getTransaction().commit();
    session.close();
    
    h.exit();
    
    return result;
    

	}
	
	
	public ArrayList<Ticket> listaTicket() {
		
		HibernateUtil h= new HibernateUtil();
		h.setup();
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
	    session.beginTransaction();
	 
	    Query<Ticket> query = session.createQuery("from Ticket as t where t.id_utente = :parametro");
		query.setParameter("parametro", this.getId());
	
	ArrayList<Ticket> ticket = (ArrayList<Ticket>) query.list();
	
	session.getTransaction().commit();
    session.close();
    
    h.exit();
    
		return ticket;
	}
	
	
	public int login(String username, String password) {
		
		HibernateUtil h= new HibernateUtil();
		h.setup();
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
	    session.beginTransaction();
	 
	    Query<Utente> query = session.createQuery("from Utente as u where u.username = :parametro1 and u.password = :parametro2");
	    query.setParameter("parametro1", username); 
		query.setParameter("parametro2", password); 
	
	Utente result = query.getSingleResult();
	
	session.getTransaction().commit();
    session.close();
     
    h.exit();
    
   return result.getId();
	
	
		
	}
	
	
	public boolean pagamento(String titolare, String codice, String mese, String anno, String cvv) {
		String host = "localhost";
		/*
		 * if(!hostname.equals("NULL")) host = hostname;
		 */

		boolean result = false;
		try {
			Registry registry = LocateRegistry.getRegistry(host);
			ISistemaPagamentoProxy stub = (ISistemaPagamentoProxy) registry.lookup("Payment");

			// Chiamate al server tipo:
			result=stub.pagamento(titolare,codice,mese,anno,cvv);

		} catch (Exception e) {
			e.printStackTrace();

		}
		
		return result;
	}
	
public boolean registrazione(String username, String password, String email) {
		
		HibernateUtil h= new HibernateUtil();
		h.setup();
		
		Utente u = new Utente();
		
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
	    session.beginTransaction();
	    
	    Query<Utente> query1 = session.createQuery("from Utente as u where u.username = :parametro1");
	    query1.setParameter("parametro1", username); 
	    
	    ArrayList<Utente> l1 = (ArrayList<Utente>) query1.list();
	    
	    Query<Utente> query2 = session.createQuery("from Utente as u where u.email = :parametro2");
	    query2.setParameter("parametro2", email);  
	    
	    ArrayList<Utente> l2 = (ArrayList<Utente>) query2.list();
	    
	    if(l1.isEmpty() && l2.isEmpty()) {
		    u.setUsername(username);
		    u.setPassword(password);
		    u.setEmail(email);
		    u.setPuntipremium(0);
		    session.save(u);
		    session.getTransaction().commit();
		    session.close();
		    h.exit();
		    return true;
	    }   
	
	session.getTransaction().commit();
    session.close();
     
    h.exit();
    
   return false;
	
	
		
	}

	
	public int visualizzaPuntiPremium() {
		
		HibernateUtil h= new HibernateUtil();
		h.setup();
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
	    session.beginTransaction();
	 
	    Query<Utente> query = session.createQuery("from Utente as u where u.id = :parametro");
		query.setParameter("parametro", this.getId()); 
	
		Utente result = query.getSingleResult();
	
	session.getTransaction().commit();
    session.close();
    
    h.exit();
    
    return result.getPuntipremium();
		
		
		
	}

}