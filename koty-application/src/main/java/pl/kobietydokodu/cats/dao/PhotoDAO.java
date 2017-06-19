package pl.kobietydokodu.cats.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pl.kobietydokodu.cats.domain.Photo;

@Repository
public interface PhotoDAO extends CrudRepository<Photo, Long> {

	public Photo findById(Long id);

}
