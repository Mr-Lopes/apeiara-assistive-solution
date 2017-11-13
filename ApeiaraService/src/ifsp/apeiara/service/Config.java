package ifsp.apeiara.service;

import java.io.File;

public final class Config {
	
	// Choose a port which is greater than 1,023! 
	private static final int SOCKET_PORT = 6666;
	private static final String directoryPath = "C:/Apeiara/Data/";
	private static final String TAG = "RECEPTOR";
	
	public static final int getSocketPort() {
		return SOCKET_PORT;
	}
	
	public static final String getDirectorypath() {
		
		//If the current directory doens't exist, creates it
		File dir = new File(directoryPath);
		if (!dir.exists()) {
			if (!dir.mkdirs()) {
				System.out.println(TAG + ": Failed to create directory: " + directoryPath);
			}
		}
		return directoryPath;
	}
}
