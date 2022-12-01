package aaa.tavern.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import aaa.tavern.entity.Place;
import aaa.tavern.entity.Manager;

@Repository
public interface PlaceRepository extends CrudRepository<Place, Integer> {
    List<Place> findByManager(Manager manager);

}
