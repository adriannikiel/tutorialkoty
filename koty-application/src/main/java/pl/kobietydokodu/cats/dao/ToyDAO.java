package pl.kobietydokodu.cats.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pl.kobietydokodu.cats.domain.Toy;

@Repository
public interface ToyDAO extends CrudRepository<Toy, Long> {

	public Toy findById(Long id);

	public List<Toy> findByKitten_id(Long id);

}
