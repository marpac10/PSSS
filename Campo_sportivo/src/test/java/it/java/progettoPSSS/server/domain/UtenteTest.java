package it.java.progettoPSSS.server.domain;

import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.Assert;

public class UtenteTest {

	@Test
	public void utenteTest() {
		Utente u = new Utente();
		
		
		String username = new String("Mariooo98");
		String password = new String("qwerty");
		String email = new String("marioooo@gmail.com");
		int id = 22;
		
		
		

		assertTrue("Registrazione fallita",u.registrazione(username, password, email));
		
		assertNotEquals("Login fallito", 0, u.login(username, password));
		
		u.setId(id);
		
		assertEquals("Verifica punti premium fallita", 20, u.visualizzaPuntiPremium());
		
		assertNotNull("Verifica ticket fallita", u.listaTicket());
		
		
	}

	
	
}


