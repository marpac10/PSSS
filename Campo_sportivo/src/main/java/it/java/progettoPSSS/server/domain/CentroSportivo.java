package it.java.progettoPSSS.server.domain;


import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import it.java.progettoPSSS.server.db.HibernateUtil;

@Entity
@Table (name = "centrosportivo")
public class CentroSportivo implements Serializable {
	
	private static final long serialVersionUID = 21L;

	private String nome;
	private String indirizzo;

	@OneToMany
	@JoinColumn(name="nome_centro")
	ArrayList<Campo> campi;
	
	
	
		
	public CentroSportivo() {
		super();
		// TODO Auto-generated constructor stub
	}




	







	public CentroSportivo(String nome, String indirizzo, ArrayList<Campo> campi) {
		super();
		this.nome = nome;
		this.indirizzo = indirizzo;
		this.campi = campi;
	}












	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public String getNome() {
		return nome;
	}




	public void setNome(String nome) {
		this.nome = nome;
	}


	@Column
	public String getIndirizzo() {
		return indirizzo;
	}




	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}




	/**
	 * 
	 * @param sport
	 *
	 */
	
	
	public ArrayList<Campo> listaCampiDisponibili() {
		HibernateUtil h= new HibernateUtil();
		h.setup();
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
	    session.beginTransaction();
	 
	    Query<Campo> query = session.createQuery("from Campo");
	
	campi = (ArrayList<Campo>) query.list();
	
	
	session.getTransaction().commit();
    session.close();
    
    h.exit();
		
		
		return campi;
	}

}