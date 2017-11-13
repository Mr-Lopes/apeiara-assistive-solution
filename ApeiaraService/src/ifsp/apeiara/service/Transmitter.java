package ifsp.apeiara.service;

import java.io.DataOutputStream;
import java.net.Socket;

public class Transmitter {

	private static final String TAG = "TRANSMITTER";
	private static int MAX_TRIES = 10;

	public void Execute(final String type, final byte[] data, final String address) throws IllegalAccessException, Exception {

		Socket mySocket = null;
		int ntry = 0;
		boolean sentIT=false;
			while (ntry<=MAX_TRIES) {
				
				try {
					mySocket = new Socket(address, Config.getSocketPort());
					System.out.println(TAG + ": Connected to " + address);

					DataOutputStream dos = new DataOutputStream(mySocket.getOutputStream());

					dos.writeUTF(type);
					dos.writeInt(data.length);
					dos.write(data);
					dos.flush();

					dos.close();
					mySocket.close();

					System.out.println(TAG+ ": XML ("+type+") sent to the remote address.");
					//Breaks the loop
					ntry+=MAX_TRIES;
					//Change flag to true
					sentIT=true;
					
			} catch (Exception e) {

				if (e.getMessage().equalsIgnoreCase("Connection timed out: connect")) {
					// throw new IllegalAccessException("Timed");
					//System.out.println(TAG + e.getMessage());
				} else if (e.getMessage().equalsIgnoreCase("Connection refused: connect")) {
					//System.out.println(TAG + e.getMessage());
					// throw new IllegalAccessException("Refused");
				} else {
					System.out.println(TAG + e.getMessage());
				}
			} finally {
				ntry++;
				Thread.sleep(1000);
			}
		}
		if (sentIT == false) {
			throw new IllegalAccessException("Timed");
		}
	}
}

