package pl.kobietydokodu.cats.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pl.kobietydokodu.cats.domain.User;

@Repository
public interface UserDAO extends CrudRepository<User, Long>{
	
}
