package aaa.tavern.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import aaa.tavern.service.TokenJwtService;

@RestController
public class TokenController {

    @Autowired
    private TokenJwtService tokenJwtService;

    @GetMapping("/refreshToken")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws Exception {
        tokenJwtService.refreshToken(request, response);
    }

}
