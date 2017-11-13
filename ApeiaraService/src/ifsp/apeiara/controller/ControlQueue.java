package ifsp.apeiara.controller;

import ifsp.apeiara.model.QueueRequest;
import ifsp.apeiara.model.Request;
import ifsp.apeiara.model.User;

import java.util.ArrayList;

public class ControlQueue {

	private static ArrayList<QueueRequest> queue = new ArrayList<QueueRequest>();

	

	
	public static void add(final User user, final Request request) {
		

		ArrayList<Request> temp= new ArrayList<Request>();
		ArrayList<QueueRequest> tmpQueue = queue;
		
		boolean found = false;
		
		for (QueueRequest queuereq : tmpQueue) {
			if (queuereq.getUser().getID() == user.getID()) {
		
				temp = queuereq.getRequests();	
				temp.add(request);
				queue.remove(queuereq);
				queue.add(new QueueRequest(user, temp));
				System.out.println("Adding request to Queue.");
				found = true;
				break;
			}
		}
		if (found == false) {
			temp = new ArrayList<Request>();
			temp.add(request);
			queue.add(new QueueRequest(user, temp));
			System.out.println("Creating a new item into Queue.");
		}
	}

	public static void addList(final User user, final ArrayList<Request> requests){
		
		for (Request req : requests) {
			add(user, req);
		}
	}
	
	
	
	public static void removeUser(final User user) {
		
		ArrayList<QueueRequest> tmpQueue = queue;
		for (QueueRequest queuereq : tmpQueue) {
			if (queuereq.getUser().getID() == user.getID()) {
				queue.remove(queuereq);
				break;
			}
		}
	}

	public static void removeRequest(final User user, final Request req){
		
		ArrayList<QueueRequest> tmpQueue = queue;

		for (QueueRequest queuereq : tmpQueue) {
			if (queuereq.getUser().getID() == user.getID()) {
				for (Request r : queuereq.getRequests()) {
					if (r == req) {

						ArrayList<Request> tmp = queuereq.getRequests();
						tmp.remove(req);

						if (tmp.size() <= 0) {
							removeUser(user);
						} else {
							queue.remove(queuereq);
							addList(user, tmp);
							break;
						}
					}
				}
			}
		}
	}
	
	
	
	public static ArrayList<QueueRequest> getQueue() {
		return queue;
	}
	public static int countRequests(){
		int result=0;
		
		final ArrayList<QueueRequest> tmpQueue=queue;
		for(QueueRequest q: tmpQueue)
		{
			result=result+ q.getRequests().size();
		}
		
		return result;
	}

}
