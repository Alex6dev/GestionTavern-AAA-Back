package aaa.tavern.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import aaa.tavern.dto.LoadManagerDto;
import aaa.tavern.dto.received.ManagerIdDto;
import aaa.tavern.service.LoadManagerService;

@RestController
@RequestMapping("/api/game")
public class LoadManagerController {

    @Autowired
    private LoadManagerService loadManagerService;

    /**
     * TODO: Faire la doc
     * 
     * @requestBody managerIdDto
     * @return
     */
    @PostMapping("/manager/loadManager")
    public LoadManagerDto loadManager(@RequestBody ManagerIdDto managerIdDto) {
        try {
            return loadManagerService.loadManager(managerIdDto.getManagerId());

        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN, e.getMessage());
        }
    }
}
