package aaa.tavern.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import aaa.tavern.entity.Place;
import aaa.tavern.entity.TableRest;

@Repository
public interface TableRestRepository extends CrudRepository<TableRest, Integer> {
    List<TableRest> findByPlace(Place place);
}
