package pl.kobietydokodu.koty;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pl.kobietydokodu.koty.domain.Zdjecie;

@Repository
public interface ZdjecieDAO extends CrudRepository<Zdjecie, Long>{

	public Zdjecie findById(Long id);
	
}
