package aaa.tavern.filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import aaa.tavern.Secret;
import aaa.tavern.utils.JwtUtil;

public class JwtAuthorizationFilter extends OncePerRequestFilter {
    /*
     * Une méthode qui s'exécute à chaque fois qu'une requête HTTP est faite par
     * l'utilisateur
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if (request.getServletPath().equals("/refreshToken")) {
            filterChain.doFilter(request, response);
        } else {
            // C'est le Json Web Token(JWT) qui contient "Authorization" en header
            String authorizationToken = request.getHeader(JwtUtil.AUTH_HEADER);
            // Si le header n'est pas null et que la valeur de celui-ci commence par "Bearer
            // "
            if (authorizationToken != null && authorizationToken.startsWith(JwtUtil.PREFIX)) {
                try {
                    // Verification du jwt avec le même mot secret que l'authentication
                    String jwt = authorizationToken.substring(JwtUtil.PREFIX.length());
                    Algorithm algorithm = Algorithm.HMAC256(Secret.JWT_SECRET);
                    JWTVerifier jwtVerifier = JWT.require(algorithm).build();
                    DecodedJWT decodedJWT = jwtVerifier.verify(jwt);
                    // On récupére la session de l'utilisateur
                    String username = decodedJWT.getSubject();
                    String[] roles = decodedJWT.getClaim("role").asArray(String.class);
                    /*
                     * Conversion de la liste des rôles car
                     * UsernamePasswordAuthenticationToken a besoin d'une
                     * "Collection<GrantedAuthority>" et non d'une "arrayList<String>"
                     */
                    Collection<GrantedAuthority> authorities = new ArrayList<>();
                    for (String role : roles) {
                        authorities.add(new SimpleGrantedAuthority(role));
                    }
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            username, null, authorities);
                    /*
                     * On informe SpringSecurity que ces infos sont correctes et autorise
                     * l'utilisateur authentifié d'effectuer une action
                     */
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    filterChain.doFilter(request, response);
                } catch (Exception e) {
                    response.setHeader("error-message", e.getMessage());
                    response.sendError(HttpServletResponse.SC_FORBIDDEN);
                }

            } else {
                filterChain.doFilter(request, response);
            }
            ;
        }
    }

}
