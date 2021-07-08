package it.java.progettoPSSS.externalservice;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ISistemaPagamentoProxy extends Remote {

	/**
	 * 
	 * @param carta
	 * @param cvv
	 */
	boolean pagamento(String titolare, String carta, String mese, String anno, String cvv) throws RemoteException;

}