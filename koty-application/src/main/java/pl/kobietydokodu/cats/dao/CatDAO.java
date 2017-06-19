package pl.kobietydokodu.cats.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pl.kobietydokodu.cats.domain.Cat;

/**
 * The use of Spring Data to manage operation on table 'cats'
 */
@Repository
public interface CatDAO extends CrudRepository<Cat, Long> {

	public List<Cat> findAll();

	public Cat findById(Long id);

}
