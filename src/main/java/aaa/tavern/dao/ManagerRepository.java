package aaa.tavern.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import aaa.tavern.entity.Manager;
import aaa.tavern.entity.Player;


@Repository
public interface ManagerRepository extends CrudRepository<Manager, Integer> {
    List<Manager>findByPlayer(Player player);
    
}
