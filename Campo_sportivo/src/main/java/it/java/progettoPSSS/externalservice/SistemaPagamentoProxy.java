package it.java.progettoPSSS.externalservice;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;



public class SistemaPagamentoProxy implements ISistemaPagamentoProxy {
	
	public static Registry registry = null;

	public boolean pagamento(String titolare, String carta, String mese, String anno, String cvv) {
		return true;
	}
	
	
	public static void main(String[] args) {
		try {
			//registry = LocateRegistry.createRegistry(1099);
		
			SistemaPagamentoProxy payment = new SistemaPagamentoProxy();
			ISistemaPagamentoProxy serverStub = (ISistemaPagamentoProxy) UnicastRemoteObject.exportObject(payment, 0);
			Registry registry = LocateRegistry.getRegistry();
			registry.rebind("Payment", serverStub);
			System.out.println("Payment ready");
		
		} catch(Exception e) {
			
			e.printStackTrace();
			
		}
	}
}