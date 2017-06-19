package pl.kobietydokodu.cats.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import pl.kobietydokodu.cats.domain.Cat;

/**
 * The use of a List to collect cats
 */
@Repository
public class OldCatDAO {

	List<Cat> cats = new ArrayList<Cat>();

	/**
	 * Add cat to List of cats
	 */
	public void addCat(Cat kot) {
		cats.add(kot);
	}

	/**
	 * @return Return list of cats
	 */
	public List<Cat> getCats() {
		return cats;
	}

	/**
	 * @param id Unique identification number of cat
	 * @return Cat with specific id number
	 */
	public Cat getCatById(Integer id) {

		if (id < cats.size()) {
			return cats.get(id);
		} else {
			return null;
		}

	}

}
