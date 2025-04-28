package com.roomify.roomifybackend.configuration.security;

import com.roomify.roomifybackend.configuration.security.filter.JwtTokenValidator;
import com.roomify.roomifybackend.utils.ApiPaths;
import com.roomify.roomifybackend.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtUtils jwtUtils;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, CorsConfigurationSource corsConfigurationSource) throws Exception {
        return httpSecurity
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> csrf.disable())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(http -> {
                    //Configurar los endpoints publicos
                    http.requestMatchers(HttpMethod.POST, "/auth/**").permitAll();
                    http.requestMatchers(HttpMethod.GET, "/method/get").permitAll();


                    //Configurar los endpoints privados
                    http.requestMatchers(HttpMethod.POST, ApiPaths.ROOMS).permitAll();
                    http.requestMatchers(HttpMethod.GET, ApiPaths.ROOMS).permitAll();
                    http.requestMatchers(HttpMethod.GET, ApiPaths.ROOM_BY_ID).permitAll();
                    http.requestMatchers(HttpMethod.PUT, ApiPaths.ROOM_BY_ID).permitAll();
                    http.requestMatchers(HttpMethod.DELETE, ApiPaths.ROOM_BY_ID).permitAll();

                    //RoomsTypes
                    http.requestMatchers(HttpMethod.POST, ApiPaths.ROOMS_TYPES).permitAll();
                    http.requestMatchers(HttpMethod.GET, ApiPaths.ROOMS_TYPES).permitAll();
                    http.requestMatchers(HttpMethod.GET, ApiPaths.ROOM_TYPE_BY_ID).permitAll();
                    http.requestMatchers(HttpMethod.PUT, ApiPaths.ROOM_TYPE_BY_ID).permitAll();
                    http.requestMatchers(HttpMethod.DELETE, ApiPaths.ROOM_TYPE_BY_ID).permitAll();

                    //Amenities
                    http.requestMatchers(HttpMethod.POST, ApiPaths.AMENITIES).permitAll();
                    http.requestMatchers(HttpMethod.GET, ApiPaths.AMENITIES).permitAll();
                    http.requestMatchers(HttpMethod.GET, ApiPaths.AMENITY_BY_ID).permitAll();
                    http.requestMatchers(HttpMethod.PUT, ApiPaths.AMENITY_BY_ID).permitAll();
                    http.requestMatchers(HttpMethod.DELETE, ApiPaths.AMENITY_BY_ID).permitAll();

                    //Bookings
                    http.requestMatchers(HttpMethod.POST, ApiPaths.BOOKINGS).permitAll();
                    http.requestMatchers(HttpMethod.GET, ApiPaths.BOOKINGS).permitAll();
                    http.requestMatchers(HttpMethod.GET, ApiPaths.BOOKING_BY_ID).permitAll();
                    http.requestMatchers(HttpMethod.PUT, ApiPaths.BOOKING_BY_ID).permitAll();
                    http.requestMatchers(HttpMethod.DELETE, ApiPaths.BOOKING_BY_ID).permitAll();


                    http.requestMatchers(HttpMethod.POST, "/method/post").hasAnyRole("ADMIN");
                    http.requestMatchers(HttpMethod.PUT, "/method/put").hasAnyRole("RECEPTIONIST");
                    http.requestMatchers(HttpMethod.PATCH, "/method/patch").hasAnyRole("CUSTOMER");

                    //Configurar los endpoints autenticados
                    http.requestMatchers(HttpMethod.GET, "/auth/profile").authenticated();

                    //Configurar el resto de endpoints no especificados
                    http.anyRequest().permitAll();
                })
                .addFilterBefore(new JwtTokenValidator(jwtUtils), BasicAuthenticationFilter.class) //Agregamos el filtro que creamos, pero antes del filtro BasicAuthenticationFilter
                .exceptionHandling(exception ->
                        exception.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                )
                .build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:5173"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


}
