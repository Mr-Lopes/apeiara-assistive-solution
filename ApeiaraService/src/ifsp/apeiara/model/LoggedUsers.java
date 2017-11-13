package ifsp.apeiara.model;


public class LoggedUsers {
	
	private User user;
	private String adress;
	
	public LoggedUsers(User user, String adress) {
		this.user = user;
		this.adress = adress;
	}

	public User getUser() {
		return this.user;
	}

	public String getAdress() {
		return this.adress;
	}
	
	
}
