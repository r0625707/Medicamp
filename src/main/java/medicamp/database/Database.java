package medicamp.database;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import medicamp.model.Kind;
import medicamp.model.Voogd;
public class Database {
	private EntityManagerFactory factory;
	private EntityManager manager;
	private String dbName;

	public void openConnection(String name) {
		factory = Persistence.createEntityManagerFactory("MEDICAMP");
		manager = factory.createEntityManager();
	}

	public void closeConnection() throws Exception {
		try {
			manager.close();
			factory.close();
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
	}
	public List<Kind> getAllKinderen(){
		
		return null;
	}

	public void addVoogd(Voogd voogd) {
		manager.getTransaction().begin();
		manager.persist(voogd);
		manager.getTransaction().commit();
	}

	public Voogd getVoogd(Long id) {
		Voogd voogd = manager.find(Voogd.class, id);
		return voogd;
	}

}
