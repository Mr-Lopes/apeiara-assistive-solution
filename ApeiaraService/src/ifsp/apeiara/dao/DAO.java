package ifsp.apeiara.dao;

import java.util.List;

import javax.persistence.EntityManager;

public class DAO<T> {

	private final Class<T> classe;
	private final EntityManager em;

	public DAO(EntityManager em, Class<T> classe) {
		this.em = em;
		this.classe = classe;

	}

	public void insert(T t) {
		this.em.persist(t);
	}

	public void update(T t) {
		this.em.merge(t);
	}

	public void remove(T t) {
		this.em.remove(t);
	}

	public T search(Integer id) {
		return this.em.find(classe, id);
	}

	public T searchSTR(String id) {
		return this.em.getReference(classe, id);
	}

	@SuppressWarnings("unchecked")
	public List<T> list() {
		return this.em.createQuery("from " + classe.getName()).getResultList();
	}

}
