package ifsp.apeiara.service;

import ifsp.apeiara.controller.Manage;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Receiver {

	private static final String TAG = "RECEIVER";

	public void Execute() throws IOException {

			DataInputStream dis = null;
			ServerSocket servsock = null;
			Socket sock = null;
			
			try {
			
			servsock = new ServerSocket(Config.getSocketPort());
			while (true) {

				System.out.println(TAG + ": Waiting...");

				// Opens channels of communication
				sock = servsock.accept();
				
				///Gets only the ip 192.168.0.3:36478
				String address = sock.getRemoteSocketAddress().toString().substring(1, sock.getRemoteSocketAddress().toString().indexOf(":"));
				System.out.println(TAG + ": Connected to "+ address);

				// Receives an input of data
				dis = new DataInputStream(sock.getInputStream());

				// Gets the type, length and data
				String type = dis.readUTF();
				int dataLength = dis.readInt();
				byte[] data = new byte[dataLength];

				dis.readFully(data);
				String xml = new String(data, "UTF-8");
				
				System.out.println(TAG + ": XML downloaded from "+ address);
				//System.out.println(xml);

				// Creates a new Thread
				new Thread(new Runnable() {
					public void run() {
						new Manage().analyze(type, xml, address);
					}
				}).start();

			}
		} catch (IOException e) {
			System.out.println("ERROR: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
		} finally {
			if (dis != null)
				dis.close();
			if (servsock != null)
				servsock.close();
			if (sock!=null)
				sock.close();
		}
	}
}
