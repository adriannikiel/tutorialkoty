package pl.kobietydokodu.koty;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pl.kobietydokodu.koty.domain.Uzytkownik;

@Repository
public interface UzytkownikDAO extends CrudRepository<Uzytkownik, Long>{
	
}
