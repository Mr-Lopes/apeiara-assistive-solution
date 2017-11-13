package ifsp.apeiara.controller;

import ifsp.apeiara.dao.JPAUtil;
import ifsp.apeiara.dao.LogDAO;
import ifsp.apeiara.dao.RequestDAO;
import ifsp.apeiara.dao.UserDAO;
import ifsp.apeiara.model.Log;
import ifsp.apeiara.model.Request;
import ifsp.apeiara.model.User;
import ifsp.apeiara.service.Transmitter;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.persistence.EntityManager;

public class Manage {

	private static SimpleDateFormat sdf = new SimpleDateFormat(
			"dd-MM-yyyy HH:mm:ss.SSS");

	public void analyze(final String type, final String xml,final String address) {

		if (type.equalsIgnoreCase("request")) {
			// Gets xml's values
			Request req = new DoXML().readRequest(xml);

			EntityManager em = new JPAUtil().getEntityManager();
			em.getTransaction().begin();

			// Saves log
			LogDAO log = new LogDAO(em);

			// Create a Request instantiation
			RequestDAO dao = new RequestDAO(em);

			try {
				dao.insert(req);
				log.insert(new Log(0, sdf.format(Calendar.getInstance().getTime()), req, req.getSTATUS(), req.getCUIDADOR(),"", "", ""));
				em.getTransaction().commit();
				new DoControl().submitInTime(req);

			} catch (Exception ex) {
				System.out.println("Error creating New Request " + ex.getMessage());
			} finally {
				em.close();
			}

		} else if (type.equalsIgnoreCase("done")) {
			// Gets xml's values
			Request req = new DoXML().readRequest(xml);

			EntityManager em = new JPAUtil().getEntityManager();
			em.getTransaction().begin();

			// Saves log
			LogDAO log = new LogDAO(em);

			// Create a Request instantiation
			RequestDAO dao = new RequestDAO(em);

			try {
				dao.update(req);
				log.insert(new Log(0, sdf.format(Calendar.getInstance().getTime()), req, "DONE", req.getCUIDADOR(), req.getSTATUS(), req.getLATITUDE(),req.getLONGITUDE()));
				em.getTransaction().commit();
				System.out.println("Request " + req.getID() + " answered by " + req.getCUIDADOR().getID());

			} catch (Exception ex) {
				System.out.println("Error updating Request " + ex.getMessage());
			} finally {
				em.close();
			}
		} else if (type.equalsIgnoreCase("repass")) {
			// Gets xml's values
			Request req = new DoXML().readRequest(xml);

			EntityManager em = new JPAUtil().getEntityManager();
			em.getTransaction().begin();

			// Saves log
			LogDAO log = new LogDAO(em);

			try {
				log.insert(new Log(0, sdf.format(Calendar.getInstance().getTime()), req, "REPASS", req.getCUIDADOR(), req.getSTATUS(), req.getLATITUDE(),req.getLONGITUDE()));
				em.getTransaction().commit();
				System.out.println("Repassing request " + req.getID() + " by " + req.getCUIDADOR().getID());

			} catch (Exception ex) {
				System.out.println("Error repassing Request " + ex.getMessage());
			} finally {
				em.close();
			}

		} else if (type.equalsIgnoreCase("login")) {

			User us = new DoXML().readLogin(xml);

			EntityManager em = new JPAUtil().getEntityManager();
			em.getTransaction().begin();

			UserDAO dao = new UserDAO(em);

			try {
				us = dao.login(us);

				if (us != null) {
				
					ControlAccess.add(us, address);
					System.out.println("User " + us.getNAME() + " ("+ us.getPHONE() + ") logged. Users logged: " + ControlAccess.getUsers().size());
					new Transmitter().Execute("USER", new DoXML().writeUser(us).getBytes("UTF-8"), address);
					new DoControl().submitASAP(us, address);
					
				} else {
					System.out.println("A user tried to log in.");
					new Transmitter().Execute("WRONG","LOGIN".getBytes("UTF-8"), address);
				}

			} catch (Exception ex) {
				System.out.println("Error loging User " + ex.getMessage());
			} finally {
				em.close();
			}

		} else if (type.equalsIgnoreCase("logout")) {

			User us = new DoXML().readUser(xml);
			ControlAccess.remove(us);

		} else if (type.equalsIgnoreCase("authenticate")) {

			User us = new DoXML().readUser(xml);
			ControlAccess.add(us, address);
			System.out.println("User " + us.getNAME() + " ("+ us.getPHONE() + ") renewed the address("+address+").");		
			new DoControl().submitASAP(us, address);
		}
		else {
			System.out.println("Invalid XML received from " + address);
		}
	}

}
