package aaa.tavern.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aaa.tavern.dao.PlaceRepository;
import aaa.tavern.dao.TableRestRepository;
import aaa.tavern.dto.TableRestDto;
import aaa.tavern.entity.Place;
import aaa.tavern.entity.TableRest;

@Service
public class TableRestService {

    @Autowired
    PlaceRepository placeRepository;

    @Autowired
    TableRestRepository tableRestRepository;

    public List<TableRestDto> loadTableRestByPlace(int placeId) {
        Place place = placeRepository.findById(placeId).get();

        List<TableRest> listTableRest = tableRestRepository.findByPlace(place);
        if (listTableRest.isEmpty()) {
            throw new EntityNotFoundException();
        }
        List<TableRestDto> listTableRestDto = new ArrayList<TableRestDto>();

        for (TableRest tableRest : listTableRest) {
            TableRestDto tableRestDto = new TableRestDto(tableRest);
            listTableRestDto.add(tableRestDto);
        }
        return listTableRestDto;
    }
}
