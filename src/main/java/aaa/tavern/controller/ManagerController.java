package aaa.tavern.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import aaa.tavern.dto.CreateManagerDTO;
import aaa.tavern.dto.ManagerDto;
import aaa.tavern.dto.PlayerEmailDto;
import aaa.tavern.dto.StatusDto;
import aaa.tavern.dto.received.ManagerIdDto;
import aaa.tavern.service.ManagerService;

@RestController
@RequestMapping("/api/game/")
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @PostMapping("/manager/listExistingManager")
    public List<ManagerDto> listExistingManager(@RequestBody PlayerEmailDto PlayerEmailDto) {
        try {
            return managerService.listExistingManagerDto(PlayerEmailDto.getEmail());

        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN, e.getMessage());
        }
    }

    @PostMapping("select/manager")
    public ManagerDto getManager(@RequestBody ManagerIdDto managerIdDto) {
        try {
            return managerService.selectManager(managerIdDto.getManagerId());

        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN, e.getMessage());
        }
    }

    @PostMapping("delete/manager")
    public StatusDto deleteManager(@RequestBody ManagerIdDto managerIdDto) {
        try {
            managerService.deleteManager(managerIdDto.getManagerId());

            return new StatusDto(1, "Le manager est bien supprimé");

        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN, e.getMessage());
        }
    }

    @PostMapping("/manager/create")
    public void createManager(@RequestBody CreateManagerDTO createManagerDTO) {
        managerService.createManager(createManagerDTO);

    }
}
