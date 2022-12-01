package aaa.tavern.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import aaa.tavern.entity.Manager;
import aaa.tavern.entity.Place;

@DataJpaTest
@TestPropertySource(locations = "classpath:test.properties")
public class PlaceRepositoryTest {

    @Autowired
    PlaceRepository placeRepository;

    @Autowired
    ManagerRepository managerRepository;

    @Test
    @Sql("given2ManagerAnd5Place_whenFindByManagerWith3Place_thenReturnFirstManagerPlaceList.sql")
    public void given2ManagerAnd5Place_whenFindByManager_thenReturnFirstManagerPlaceList() {
        Manager manager = managerRepository.findById(1).get();

        List<Place> listPlaces = placeRepository.findByManager(manager);

        assertEquals(listPlaces.size(), 3);
    }

    @Test
    @Sql("given2ManagerAnd5Place_whenFindByManagerWithoutPlace_thenReturnAnEmptyPlaceList.sql")
    public void given2ManagerAnd5Place_whenFindByManagerWithoutPlace_thenReturnAnEmptyPlaceList() {
        Manager manager = managerRepository.findById(2).get();

        List<Place> listPlaces = placeRepository.findByManager(manager);

        assertTrue(listPlaces.isEmpty());
    }
}
