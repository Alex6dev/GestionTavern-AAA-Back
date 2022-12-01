package aaa.tavern.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;

import aaa.tavern.Secret;
import aaa.tavern.entity.Player;
import aaa.tavern.utils.JwtUtil;

@Service
public class TokenJwtService {
    @Autowired
    private PlayerService playerService;

    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // C'est le Json Web Token(JWT) qui contient "Authorization" en header
        String authToken = request.getHeader(JwtUtil.AUTH_HEADER);
        /*
         * Si le header n'est pas null et que la valeur de celui-ci commence par "Bearer
         */
        // "
        if (authToken != null && authToken.startsWith(JwtUtil.PREFIX)) {
            try {
                // Verification du jwt avec le même mot secret que l'authentication
                String jwt = authToken.substring(JwtUtil.PREFIX.length());
                Algorithm algorithm = Algorithm.HMAC256(Secret.JWT_SECRET);
                JWTVerifier jwtVerifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = jwtVerifier.verify(jwt);
                // On récupére le subject qui contient l'email de l'utilisateur
                String email = decodedJWT.getSubject();
                // On utilise le service qui permet de charger l'utilisateur
                Player player = playerService.loadPlayerByEmail(email);
                // On recrée l'access-Token avec les informations récupéré via le service
                String jwtAccessToken = JWT.create()
                        .withSubject(player.getEmail())
                        .withExpiresAt(new Date(System.currentTimeMillis() + JwtUtil.EXPIRE_ACCESS_TOKEN))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("role",
                                player.getRoles().stream().map(r -> r.getName()).collect(Collectors.toList()))
                        .sign(algorithm);
                // On renvoie un nouveaux access-token à l'utilisateur
                Map<String, String> idToken = new HashMap<>();
                idToken.put("accessToken", jwtAccessToken);
                idToken.put("refreshToken", jwt); 
                response.setContentType("application/json");
                new ObjectMapper().writeValue(response.getOutputStream(), idToken);
            } catch (Exception e) {
                throw e;
            }
        } else {
            throw new RuntimeException("Refresh token required");
        }
    }
}
