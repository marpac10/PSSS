package it.java.progettoPSSS.server.proxy;

public class ServerProxyFacade {
	
	private final ServerProxy serverProxy;
	
	public ServerProxyFacade (ServerProxy serverProxy) {
		this.serverProxy = serverProxy;
	}
	
	public void startServer() {
		serverProxy.startServer();
		serverProxy.loadPrenotazioni();
		serverProxy.deletePrenotazioni();
	}
	
}
