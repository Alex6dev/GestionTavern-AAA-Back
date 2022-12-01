package aaa.tavern.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import aaa.tavern.entity.Place;
import aaa.tavern.entity.TableRest;

@DataJpaTest
@TestPropertySource(locations = "classpath:test.properties")
public class TableRestRepositoryTest {

    @Autowired
    PlaceRepository placeRepository;

    @Autowired
    TableRestRepository tableRestRepository;

    @Test
    @Sql("givenPlaceWithManagerAnd5TableRest_whenFindByPlace_thenReturnListTableRest.sql")
    public void givenPlaceWithManagerAnd5TableRest_whenFindByPlace_thenReturnListTableRest() {
        Place place = placeRepository.findById(1).get();
        List<TableRest> listTableRest = tableRestRepository.findByPlace(place);

        assertEquals(listTableRest.size(), 5);
    }

    @Test
    @Sql("givenPlaceWithManagerAnd5TableRest_whenFindByPlaceWithoutTableRest_thenReturnAnEmptyListTableRest.sql")
    public void givenPlaceWithManagerAnd5TableRest_whenFindByPlaceWithoutTableRest_thenReturnAnEmptyListTableRest() {
        Place place = placeRepository.findById(2).get();
        List<TableRest> listTableRest = tableRestRepository.findByPlace(place);

        assertTrue(listTableRest.isEmpty());
    }
}
