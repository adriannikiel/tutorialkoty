package pl.kobietydokodu.koty;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.kobietydokodu.koty.domain.Kot;


@Repository
public class HibernateKotDAO {

	@PersistenceContext
    EntityManager entityManager;

	@Transactional
	public void dodajKota(Kot kot) {
		entityManager.merge(kot);
	}

	public List<Kot> getKoty() {

		Query query = entityManager.createQuery("SELECT k FROM Kot k");
		List<Kot> koty = query.getResultList();

		return koty;
	}

	public Kot getKotById(Long id) {
		
		Kot kot = new Kot();
		kot = entityManager.find(Kot.class, id);
		
		return kot;
		
	}

}
