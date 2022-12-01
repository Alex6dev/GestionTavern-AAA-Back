package aaa.tavern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import aaa.tavern.filters.JwtAuthenticationFilter;
import aaa.tavern.filters.JwtAuthorizationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig implements WebMvcConfigurer {

        @Autowired
        private UserDetailsService userDetailsService;

        @Bean
        public PasswordEncoder getEncoder() {
                return new BCryptPasswordEncoder(11);
        }

        @Override
        public void addCorsMappings(CorsRegistry registry) {
                registry
                                .addMapping("/**")
                                .allowedMethods("HEAD", "OPTIONS", "GET", "POST", "PUT", "PATCH", "DELETE")
                                .allowedOrigins("http://localhost:4200/")
                                .allowCredentials(true);
        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                http.csrf().disable();
                http.cors();
                /*
                 * Les données de la session sont enregistrées dans un jeton d’authentification
                 * délivré au client. (JWT)
                 */
                http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                http.authenticationProvider(authProvider());
                /**
                 * Tout Utilisateur peut accéder a ces routes (le /login est permitAll par
                 * défaut donc pas besoin de le mentionner)
                 */
                http.authorizeHttpRequests().antMatchers("/api/register/", "/refreshToken/**", "/login/**").permitAll();
                /**
                 * Tout Utilisateur ayant le rôle USER peuvent accéder à ces routes
                 */
                http.authorizeHttpRequests().antMatchers("/api/game/**").hasAuthority("USER");
                /**
                 * Permet d'activer la génération des JWT a l'authentication et aussi les
                 * Authorizations
                 */
                http.addFilter(new JwtAuthenticationFilter(authenticationManager(null)));
                http.addFilterBefore(new JwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

                return http.build();
        }

        @Bean
        public DaoAuthenticationProvider authProvider() {
                final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

                authProvider.setUserDetailsService(userDetailsService);
                authProvider.setPasswordEncoder(getEncoder());
                return authProvider;
        }

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfiguration)
                        throws Exception {
                return authConfiguration.getAuthenticationManager();
        }

}
