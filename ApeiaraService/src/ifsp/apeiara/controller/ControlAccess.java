package ifsp.apeiara.controller;

import ifsp.apeiara.model.LoggedUsers;
import ifsp.apeiara.model.User;

import java.util.ArrayList;


public class ControlAccess {

	private static ArrayList<LoggedUsers> users = new ArrayList<LoggedUsers>();
	private static String TAG = "ControlAccess: ";

	public static void add(final User user, final String address) {
		//Looks for if the user is already logged. If it is, updates it
		boolean found = false;
		for (LoggedUsers currentUser : users) {
			if (currentUser.getUser().getID() == user.getID()) {
				users.remove(currentUser);
				users.add(new LoggedUsers(user, address));
				found = true;
				break;
			}
		}
		//If not, inserts it in the list
		if (found == false) {
			users.add(new LoggedUsers(user, address));
		}
	
	}

	public static void remove(final User user) {
		for (LoggedUsers currentUser : users) {
			if (currentUser.getUser().getID() == user.getID()) {
				users.remove(currentUser);
				System.out.println(TAG+  user.getNAME() + " left.");
				break;
			}
		}
	}

	public static String getAddress(final User user)
	{
		String address = null;
		for (LoggedUsers currentUser: users)
		{
			if (currentUser.getUser().getID()==user.getID())
			{
				address=currentUser.getAdress();
				break;
			}
		}
		
		return address;
	}

	public static ArrayList<LoggedUsers> getUsers() {
		return users;
	}


}
