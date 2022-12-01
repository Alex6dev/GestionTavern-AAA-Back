package aaa.tavern.filters;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import aaa.tavern.Secret;
import aaa.tavern.utils.JwtUtil;

/**
 * Permet de créer 2 JSON Web Token (JWT) access-token et refresh-token à la
 * connexion de l'utilisateur
 */
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

        private AuthenticationManager authenticationManager;

        public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
                this.authenticationManager = authenticationManager;
        }

        /**
         * La partie qui gére l'authentification de l'utilisateur
         */
        @Override
        public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
                        throws AuthenticationException {
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email,
                                password);
                return authenticationManager.authenticate(authenticationToken);
        }

        /**
         * Quand l'authentification réussie
         */
        @Override
        protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                        FilterChain chain,
                        Authentication authResult) throws IOException, ServletException {
                /**
                 * On récupére l'utilisateur authentifié
                 */
                User user = (User) authResult.getPrincipal();
                /*
                 * On choisit l'algorithme de cryptage et en paramètre, on met un mot secret
                 * pour qu'on utilisera pour la signature et on remplit la partie Header du JWT
                 */
                Algorithm algorithm = Algorithm.HMAC256(Secret.JWT_SECRET);

                /**
                 * On crée le premier JWT l'Access -Token
                 */
                String jwtAccessToken = JWT.create()
                                /**
                                 * On remplit la partie Payload(les données stockée) du jwt Web
                                 * token(https://jwt.io/)
                                 */
                                .withSubject(user.getUsername())
                                .withExpiresAt(new Date(System.currentTimeMillis() + JwtUtil.EXPIRE_ACCESS_TOKEN))
                                .withIssuer(request.getRequestURL().toString())
                                /**
                                 * Les claims sont les données que l'on souhaite stocké dans la partie payload
                                 */
                                .withClaim("role",
                                                user.getAuthorities().stream().map((ga -> ga.getAuthority()))
                                                                .collect(Collectors.toList()))
                                /**
                                 * Ici, on remplit la partie Signature du JWT et on utilise l'algorithme que
                                 * l'on a généré précédemment
                                 */
                                .sign(algorithm);

                /*
                 * On génére le RefreshToken
                 */
                String jwtRefreshToken = JWT.create()
                                .withSubject(user.getUsername())
                                .withExpiresAt(new Date(System.currentTimeMillis() + JwtUtil.EXPIRE_REFRESH_TOKEN))
                                .withIssuer(request.getRequestURL().toString())

                                .sign(algorithm);
                /**
                 * On met les 2 JWT dans un hashMap
                 */
                Map<String, String> idToken = new HashMap<>();
                idToken.put("accessToken", jwtAccessToken);
                idToken.put("refreshToken", jwtRefreshToken);
                response.setContentType("application/json");
                /**
                 * On le renvoie en format Json
                 */
                new ObjectMapper().writeValue(response.getOutputStream(), idToken);
        }
}
