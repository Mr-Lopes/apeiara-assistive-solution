package ifsp.apeiara.controller;

import ifsp.apeiara.dao.JPAUtil;
import ifsp.apeiara.dao.UserDAO;
import ifsp.apeiara.model.LoggedUsers;
import ifsp.apeiara.model.QueueRequest;
import ifsp.apeiara.model.Request;
import ifsp.apeiara.model.User;
import ifsp.apeiara.service.Transmitter;

import java.util.ArrayList;

import javax.persistence.EntityManager;

public class DoControl {

	public void submitInTime(Request req) {

		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();

		UserDAO dao = new UserDAO(em);
		User us = dao.search(req.getCUIDANDO().getID());
		req.setCUIDANDO(us);

		boolean flagUser = false;
		try {
			for (User u1 : us.getUSERS()) {			
				flagUser = false;
				for (LoggedUsers u2 : ControlAccess.getUsers()) {
					if (u1.getID() == u2.getUser().getID()) {
						try {						
							new Transmitter().Execute("REQUEST", new DoXML().writeRequest(req).getBytes("UTF-8"), u2.getAdress());
							flagUser=true;
							break;
						
						} catch (Exception ex) {
							if (ex.getMessage().equalsIgnoreCase("Timed")) {
								flagUser=true;
								ControlQueue.add(u1, req);
								break;
							} else {
								System.out.println("Error sending Request in time to remote address "+ ex.getMessage());
							}
						}
					}
				}
				if (flagUser==false) {
					// Adds the request for the other "cuidadores" too.
					ControlQueue.add(u1, req);
				}
			}
		} catch (Exception ex) {
			System.out.println("Error sending Request in time to remote address "+ ex.getMessage());
		} finally {
			em.close();
			System.out.println("Queue: " + ControlQueue.getQueue().size()+ " (Requests: " + ControlQueue.countRequests() + ")");
		}
	}

	public void submitASAP(final User us, final String address) {
        
		ArrayList<Request> tmpRmReq= new ArrayList<Request>();
		ArrayList<QueueRequest> tmpQueue = ControlQueue.getQueue();
		
		boolean flagUser = false;
		try {
			for (QueueRequest queue : tmpQueue) {
				if (us.getID() == queue.getUser().getID()) {
					flagUser = true;
					for (Request req : queue.getRequests()) {

								try {
	    							new Transmitter().Execute("REQUEST", new DoXML().writeRequest(req).getBytes("UTF-8"), address);

								} catch (Exception e) {
									if (e.getMessage().equalsIgnoreCase("Timed")) {
										tmpRmReq.add(req);
									} else if (e.getMessage().equalsIgnoreCase("Refused")){
										//Thread.sleep(1000);
										//new Transmitter().Execute("REQUEST", new DoXML().writeRequest(req).getBytes("UTF-8"), address);
										//System.out.println("Refused");
									} else {
										System.out.println("Error sending Request in ASAP to remote address: "+ e.getMessage());
							}
						}
					}
					if (flagUser) {
						ControlQueue.removeUser(us);
						break;
					}
				}

			}
		} catch (Exception ex) {
			System.out.println("Error sending Request in ASAP to remote address: "+ ex.getMessage());
		} finally {
			//If some request gets an error when sending it, readds them to the queue.
			if (tmpRmReq.size()>0){
				ControlQueue.addList(us, tmpRmReq);
			}		
			System.out.println("Queue: " + ControlQueue.getQueue().size()+ " (Requests: " + ControlQueue.countRequests() + ")");
		}
	}
}
