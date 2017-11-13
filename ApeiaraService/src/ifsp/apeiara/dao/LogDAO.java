package ifsp.apeiara.dao;

import ifsp.apeiara.model.Log;

import java.util.List;

import javax.persistence.EntityManager;

public class LogDAO {

	private final DAO<Log> dao;

	public LogDAO(EntityManager em) {
		dao = new DAO<Log>(em, Log.class);
	}

	public void insert(Log t) {
		dao.insert(t);
	}

	public Log search(Integer id) {
		return dao.search(id);
	}

	public void remove(Log t) {
		dao.remove(t);
	}

	public void update(Log apr) {
		dao.update(apr);
	}

	public List<Log> list() {
		return dao.list();
	}
}
