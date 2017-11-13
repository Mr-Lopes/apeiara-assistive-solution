package ifsp.apeiara.dao;

import ifsp.apeiara.model.User;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class UserDAO {

	private final DAO<User> dao;
	private EntityManager em;

	public UserDAO(EntityManager em) {
		dao = new DAO<User>(em, User.class);
		this.em = em;
	}

	public void insert(User t) {
		dao.insert(t);
	}

	public User search(Integer id) {
		return dao.search(id);
	}

	public void remove(User t) {
		dao.remove(t);
	}

	public void update(User us) {
		dao.update(us);
	}

	public User login(User us){
		
		Query q = em
			   //"u WHERE email = :email AND pass = MD5(:password)"
				.createQuery("SELECT u FROM " + User.class.getName()
			    + " u WHERE email = :email AND pass = :password")
			    .setParameter("email", us.getEMAIL())
			    .setParameter("password",us.getPASS());
			
		if(!q.getResultList().isEmpty()) 
			//Return the user if it isn't empty
			return (User) q.getSingleResult();
		else
			return null;

	}

	public List<User> list() {
		return dao.list();
	}
}
