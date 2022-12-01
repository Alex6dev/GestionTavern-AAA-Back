package aaa.tavern.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import aaa.tavern.dto.ModifEmailDto;
import aaa.tavern.dto.ModifNicknameDto;
import aaa.tavern.dto.ModifPasswordDto;
import aaa.tavern.dto.PlayerDto;
import aaa.tavern.entity.Player;
import aaa.tavern.service.PlayerService;

@RestController
@RequestMapping("/api")
public class PlayerController {

    @Autowired
    PlayerService playerService;

    @PostMapping("/register")
    public void createPlayer(@RequestBody PlayerDto playerDto) {
        playerService.createPlayer(playerDto);

    }

    /*
     * Permet de retourner l'utilisateur connecté
     */
    @GetMapping("/game/profile")
    public Player profile(Principal principal) {
        try {

            return playerService.loadPlayerByEmail(principal.getName());
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN, e.getMessage());
        }
    }

    @PostMapping("/game/modifEmail")
    public boolean modifEmail(@RequestBody ModifEmailDto modifEmailDto) {
        try {
            return this.playerService.changeEmail(modifEmailDto);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN, e.getMessage());
        }
    }

    @PostMapping("/game/modifNickname")
    public boolean modifNickname(@RequestBody ModifNicknameDto modifNicknameDto) {
        try {
            return this.playerService.changeNickname(modifNicknameDto);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN, e.getMessage());
        }
    }

    @PostMapping("/game/modifPassword")
    public boolean modifNickname(@RequestBody ModifPasswordDto modifPasswordDto) {
        try {
            return this.playerService.changePassword(modifPasswordDto);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN, e.getMessage());
        }
    }
}