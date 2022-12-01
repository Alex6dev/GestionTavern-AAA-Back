package aaa.tavern.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import aaa.tavern.dao.PlaceRepository;
import aaa.tavern.dao.TableRestRepository;
import aaa.tavern.dto.TableRestDto;
import aaa.tavern.entity.Place;
import aaa.tavern.entity.TableRest;

@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
public class TableRestServiceTest {

    @Autowired
    TableRestService tableRestService;

    @MockBean
    TableRestRepository tableRestRepository;

    @MockBean
    PlaceRepository placeRepository;

    @Test
    public void loadTableRestByPlace() {
        Place place = new Place();
        Mockito.when(placeRepository.findById(0)).thenReturn(Optional.of(place));
        List<TableRest> listTableRest = new ArrayList<TableRest>();
        
        for (int i = 0; i < 5; i++) {
            TableRest tableRest = new TableRest();
            tableRest.setHygiene(10f);
            tableRest.setNumberPlace(3);
            tableRest.setPlace(place);
            tableRest.setPosX(1f);
            tableRest.setPosY(1f);
        }
        Mockito.when(tableRestRepository.findByPlace(place)).thenReturn(listTableRest);
        
        List<TableRestDto> listTableRestDto = new ArrayList<TableRestDto>();

        for (TableRest tableRest : listTableRest) {
            TableRestDto tableRestDto = new TableRestDto(tableRest);
            listTableRestDto.add(tableRestDto);
        }
        List<TableRestDto> listTableRestDto2 = tableRestService.loadTableRestByPlace(0);

        assertEquals(listTableRestDto, listTableRestDto2);
    }
}
