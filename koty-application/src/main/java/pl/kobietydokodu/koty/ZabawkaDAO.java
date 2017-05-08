package pl.kobietydokodu.koty;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pl.kobietydokodu.koty.domain.Zabawka;

@Repository
public interface ZabawkaDAO extends CrudRepository<Zabawka, Long>{

	public Zabawka findById(Long id);
	public List<Zabawka> findByKotek_id(Long id);
	
}
