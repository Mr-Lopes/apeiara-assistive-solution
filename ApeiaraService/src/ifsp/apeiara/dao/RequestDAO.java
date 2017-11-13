package ifsp.apeiara.dao;

import ifsp.apeiara.model.Request;
import java.util.List;
import javax.persistence.EntityManager;

public class RequestDAO {

	private final DAO<Request> dao;

	public RequestDAO(EntityManager em) {
		dao = new DAO<Request>(em, Request.class);
	}

	public void insert(Request t) {
		dao.insert(t);
	}

	public Request search(Integer id) {
		return dao.search(id);
	}

	public void remove(Request t) {
		dao.remove(t);
	}

	public void update(Request apr) {
		dao.update(apr);
	}

	public List<Request> list() {
		return dao.list();
	}
}
