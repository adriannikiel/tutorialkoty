package pl.kobietydokodu.cats.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.kobietydokodu.cats.domain.Cat;

/**
 * The use of EntityManager to generate JPQL queries and standard operations on database.
 */
@Repository
public class HibernateKotDAO {

	@PersistenceContext
	EntityManager entityManager;

	/**
	 * Add cat to table 'cats'
	 */
	@Transactional
	public void addCat(Cat cat) {
		entityManager.merge(cat);
	}

	/**
	 * @return Return list of cats from table 'cats'
	 */
	public List<Cat> getCats() {

		Query query = entityManager.createQuery("SELECT k FROM Cat k");
		List<Cat> cats = query.getResultList();

		return cats;
	}

	/**
	 * @param id Unique identification number of cat
	 * @return Cat with specific id number
	 */
	public Cat getKotById(Long id) {

		Cat cat = new Cat();
		cat = entityManager.find(Cat.class, id);

		return cat;

	}

}
