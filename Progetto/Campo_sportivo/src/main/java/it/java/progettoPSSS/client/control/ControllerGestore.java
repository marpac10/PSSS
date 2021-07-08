package it.java.progettoPSSS.client.control;

public class ControllerGestore {
	
	private static ControllerGestore istance = null;

	private ControllerGestore() {
		super();
	}

	public static ControllerGestore getIstance() {
		if (istance==null) {
			istance = new ControllerGestore();
		}
		return istance;
	}
	
}