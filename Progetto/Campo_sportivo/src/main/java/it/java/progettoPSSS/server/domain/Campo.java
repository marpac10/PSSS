package it.java.progettoPSSS.server.domain;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import it.java.progettoPSSS.server.db.HibernateUtil;



@Entity
@Table (name = "campo")
public class Campo implements Serializable{
	
	private static final long serialVersionUID = 20L;

	private int id;
	private String sport;
	private String tipologia;
	private double prezzo;
	
	@ManyToOne
	@JoinColumn(name="nome_centro")
	CentroSportivo centroSportivo;
	
	@OneToMany
	@JoinColumn(name="id_campo")
	ArrayList<Prenotazione> prenotazioni;
	
	
	
	public Campo() {
		super();
		centroSportivo = new CentroSportivo();
		// TODO Auto-generated constructor stub
	}


	
	public Campo(int id, String sport, String tipologia, CentroSportivo centroSportivo,
			ArrayList<Prenotazione> prenotazioni) {
		super();
		this.id = id;
		this.sport = sport;
		this.tipologia = tipologia;
		this.centroSportivo = centroSportivo;
		this.prenotazioni = prenotazioni;
	}

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column
	public String getSport() {
		return sport;
	}

	public void setSport(String sport) {
		this.sport = sport;
	}
	
	@Column
	public String getTipologia() {
		return tipologia;
	}

	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}

	
	@Column
	public double getPrezzo() {
		return prezzo;
	}


	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	
	public ArrayList<Prenotazione> listaPrenotazioniDisponibili() {
		HibernateUtil h= new HibernateUtil();
		h.setup();
			
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session session = sf.openSession();
		    session.beginTransaction();
		 
		    Query<Prenotazione> query = session.createQuery("from Prenotazione as p where p.id_campo = :parametro1 and p.disponibile = :parametro2");
		    query.setParameter("parametro1", this.id);
		    query.setParameter("parametro2",true);
		    prenotazioni = (ArrayList<Prenotazione>) query.list();
		
	
		
		session.getTransaction().commit();
	    session.close();
	    
	    h.exit();
			
			
			return prenotazioni;
	}
	
	@Override
	public String toString() {
		return String.format("Id: %d -- Sport: %s -- Tipologia: %s -- Prezzo: %f", id, sport, tipologia, prezzo);
	}

}