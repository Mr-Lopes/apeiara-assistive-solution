package ifsp.apeiara.model;

import java.util.ArrayList;

public class QueueRequest {

	private User user;
	private ArrayList<Request> requests;

	public QueueRequest(User user, ArrayList<Request> requests) {
		this.user = user;
		this.requests = requests;
	}

	public User getUser() {
		return this.user;
	}

	public ArrayList<Request> getRequests() {
		return this.requests;
	}

	public void setRequest(ArrayList<Request> request) {
		this.requests = request;
	}

}
