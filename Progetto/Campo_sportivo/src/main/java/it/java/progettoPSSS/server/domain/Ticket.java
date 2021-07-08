package it.java.progettoPSSS.server.domain;

import java.io.Serializable;
//import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.*;


@Entity
@Table (name = "ticket")
public class Ticket implements Serializable{
	
	private static final long serialVersionUID = 23L;

	private int id;
	private LocalDate data;
	private LocalTime ora;
	private double valore;
	
	@ManyToOne
	@JoinColumn(name="id_utente")
	Utente utente;
	
	public Ticket() {
		super();
		this.utente = new Utente();
		// TODO Auto-generated constructor stub
	}



	


	public Ticket(int id, LocalDate data, LocalTime ora, double valore, Utente utente) {
		super();
		this.id = id;
		this.data = data;
		this.ora = ora;
		this.valore = valore;
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


	@Column
	public LocalDate getData() {
		return data;
	}



	public void setData(LocalDate data) {
		this.data = data;
	}

	@Column
	public LocalTime getOra() {
		return ora;
	}



	public void setOra(LocalTime ora) {
		this.ora = ora;
	}


	@Column
	public double getValore() {
		return valore;
	}



	public void setValore(double valore) {
		this.valore = valore;
	}

	
	
	public int getid_utente() {
		return utente.getId();
	}






	public void setid_utente(int id) {
		this.utente.setId(id);
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}