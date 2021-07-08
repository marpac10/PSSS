package it.java.progettoPSSS.server.domain;

import java.io.Serializable;
//import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.persistence.*;
import javax.swing.JOptionPane;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import it.java.progettoPSSS.server.db.HibernateUtil;



@Entity
@Table (name = "prenotazione")
public class Prenotazione implements Serializable{
	

	private static final long serialVersionUID = 22L;
	
	private int id;
	private LocalDate data;
	private LocalTime ora;
	
	private boolean disponibile;
	private boolean pagata;
	
	@ManyToOne
	@JoinColumn(name="id_campo")
	Campo campo;
	
	
	@ManyToOne
	@JoinColumn(name="id_utente")
	Utente utente;

	
	public Prenotazione() {
		super();
		this.campo = new Campo();
		this.utente = new Utente();
		// TODO Auto-generated constructor stub
	}


	



	public Prenotazione(int id, LocalDate data, LocalTime ora, boolean disponibile, Campo campo,
			Utente utente) {
		super();
		this.id = id;
		this.data = data;
		this.ora = ora;
		this.disponibile = disponibile;
		this.campo = campo;
		this.utente = utente;
	}






	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	
	public LocalDate getData() {
		return data;
	}


	public void setData(LocalDate data) {
		this.data = data;
	}

	
	public LocalTime getOra() {
		return ora;
	}


	public void setOra(LocalTime ora) {
		this.ora = ora;
	}

	

	
	public boolean isDisponibile() {
		return disponibile;
	}


	public void setDisponibile(boolean disponibile) {
		this.disponibile = disponibile;
	}

	
	public boolean isPagata() {
		return pagata;
	}


	public void setPagata(boolean pagata) {
		this.pagata = pagata;
	}
	
	
	public int getid_campo() {
		return campo.getId();
	}






	public void setid_campo(int id) {
		this.campo.setId(id);
	}



	
	public int getid_utente() {
		return utente.getId();
	}






	public void setid_utente(int id) {
		this.utente.setId(id);
	
	}
	
	

	

	
	public void LoadPrenotazione (LocalDate d, LocalTime t, Campo c) {
		
		Prenotazione pr = new Prenotazione();
		
		System.out.println(c.getId());
		
		pr.setData(d);
		pr.setOra(t);
		pr.setDisponibile(true);
		pr.setid_campo(c.getId());
		pr.setPagata(false);
		pr.setid_utente(5);
		
		
		HibernateUtil h= new HibernateUtil();
		h.setup();
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
	    session.beginTransaction();
	 
	   
	    session.save(pr);
	    
	    session.getTransaction().commit();
	    session.close();
    
	    h.exit();
		
	}


	
public void DeletePrenotazione (int id) {
		
		Prenotazione p = new Prenotazione();
		
		    p.setId(id);
		 
		    HibernateUtil h= new HibernateUtil();
			h.setup();
		    
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session session = sf.openSession();
		    session.beginTransaction();
		 
		    session.delete(p);
		 
		    session.getTransaction().commit();
		    session.close();
		    
		    h.exit();
		
	}


	public boolean prenotaCampo(Utente utente, Prenotazione prenotazione) {
	
		
		HibernateUtil h= new HibernateUtil();
		h.setup();
		
		
	    
	   // System.out.println(id_utente);
	    //System.out.println(this.getid_utente() + "   " + this.isDisponibile());
	 
	    SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
	    session.beginTransaction();
	    
	    prenotazione.setDisponibile(false);
	    prenotazione.setid_utente(utente.getId());
	    
	    
	    Query<Utente> query2 = session.createQuery("from Utente as u where u.id = :parametro");
	    query2.setParameter("parametro", utente.getId());
	    Utente result2= (Utente) query2.getSingleResult();
	    
	    
	    result2.setPuntipremium(result2.getPuntipremium()+10);
	    
	 
	    session.saveOrUpdate(prenotazione);
	    session.saveOrUpdate(result2);
	    
	    
	 
	    session.getTransaction().commit();
	    session.close();
	    
	    //if (result2.getPuntipremium()==100)
	    //JOptionPane.showMessageDialog(null, "Complimenti, hai raggiunto lo stato di utente PREMIUM!!");
	    
	    h.exit();
		
	    return true;
	}

public boolean annullaPrenotazione(Prenotazione prenotazione, Utente utente) {

	
		HibernateUtil h= new HibernateUtil();
		h.setup();
		
		
	 SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
	    session.beginTransaction();
	    
	    boolean genera = prenotazione.isPagata();
	    
	    prenotazione.setDisponibile(true);
	    prenotazione.setPagata(false);
	  
	    session.saveOrUpdate(prenotazione);
	    
	    Query<Campo> query = session.createQuery("from Campo as c where c.id = :parametro1");
	    query.setParameter("parametro1", prenotazione.getid_campo());
	    Campo result= (Campo) query.getSingleResult();
	    
	    Query<Utente> query2 = session.createQuery("from Utente as u where u.id = :parametro");
	    query2.setParameter("parametro", utente.getId());
	    Utente result2= (Utente) query2.getSingleResult();
	    
	    if (genera) {
	    	Ticket t = new Ticket();
	    	t.setData(LocalDate.now());
	    	t.setOra(LocalTime.now());
	    	t.setValore(result.getPrezzo());
	    	t.setid_utente(prenotazione.getid_utente());
	    	session.save(t);
	    	
	    }
	    
	    result2.setPuntipremium(result2.getPuntipremium()-10);
	    session.saveOrUpdate(result2);
	    
	    session.getTransaction().commit();
	    session.close();
	    
	    h.exit();
	    
	   // if (result2.getPuntipremium()==90)
		 //   JOptionPane.showMessageDialog(null, "Accidenti, hai perso lo stato di utente PREMIUM!!");
	
	    return true;
	    
}
	
	

}