package ifsp.apeiara;

import ifsp.apeiara.service.Receiver;

import java.io.IOException;
import java.util.logging.Level;



public class Main {
	
	private static final String TAG="MAIN";

	public static void main(String[] args) {
		
		try {
			//Logs only Hibernate's ERROR messages on the console
			System.out.println("Using Hibernate 4.3.8's Technologies");
			java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
			
			new Receiver().Execute();
		
		
		} catch (IOException e) {
			System.out.println(TAG + ": " + e.getMessage());
		}

	}

}
