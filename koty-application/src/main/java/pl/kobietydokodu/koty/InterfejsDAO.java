package pl.kobietydokodu.koty;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pl.kobietydokodu.koty.domain.Kot;

@Repository
public interface InterfejsDAO extends CrudRepository<Kot, Long>{
	
	public List<Kot> findAll();
	public Kot findById(Long id);

}
