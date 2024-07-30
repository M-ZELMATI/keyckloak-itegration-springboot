package com.tf.tfgfabackend.Keyckloak.config;
import com.tf.tfgfabackend.Keyckloak.security.CustomAuthenticationEntryPoint;
import com.tf.tfgfabackend.Keyckloak.security.CustomFilter;
import org.keycloak.adapters.authorization.integration.jakarta.ServletPolicyEnforcerFilter;
import org.keycloak.adapters.authorization.spi.ConfigurationResolver;
import org.keycloak.adapters.authorization.spi.HttpRequest;
import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.representations.adapters.config.PolicyEnforcerConfig;
import org.keycloak.util.JsonSerialization;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.server.resource.web.authentication.BearerTokenAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;
import java.io.IOException;
import java.util.logging.Filter;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableWebSecurity
public class SecurityConfig  {

  CustomFilter customFilter;

  CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

  public SecurityConfig(CustomFilter customFilter, CustomAuthenticationEntryPoint customAuthenticationEntryPoint) {
    this.customFilter = customFilter;
    this.customAuthenticationEntryPoint = customAuthenticationEntryPoint;
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    return http
            .authorizeRequests(authorizeRequests -> authorizeRequests
                    .anyRequest().permitAll()
            )
            .oauth2ResourceServer(oauth2 -> oauth2
                    .jwt(withDefaults())
                    .authenticationEntryPoint(customAuthenticationEntryPoint)
            )
            .sessionManagement(session -> session
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .addFilterAfter(createPolicyEnforcerFilter(), BearerTokenAuthenticationFilter.class)
            .addFilterAfter(customFilter, BearerTokenAuthenticationFilter.class)
            .build();
  }




  //pour activer Swagger
//@Bean
//public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
// return http.build();
////  return http.authorizeHttpRequests(authorize -> authorize
//////                .requestMatchers("/api/v1/*").authenticated()
////                          .anyRequest().authenticated()
////          )
////          .oauth2ResourceServer(oauth2 -> oauth2.jwt(withDefaults())
////                  .authenticationEntryPoint(customAuthenticationEntryPoint))
////          .addFilterAfter(createPolicyEnforcerFilter(), BearerTokenAuthenticationFilter.class)
////          .addFilterAfter(customFilter, BearerTokenAuthenticationFilter.class)
////          .build();
//}


  private ServletPolicyEnforcerFilter createPolicyEnforcerFilter() {
    return new ServletPolicyEnforcerFilter(new ConfigurationResolver() {
      @Override
      public PolicyEnforcerConfig resolve(HttpRequest request) {
        try {
          PolicyEnforcerConfig policyEnforcerConfig = JsonSerialization.readValue(getClass().getResourceAsStream("/policy-enforcer.json"), PolicyEnforcerConfig.class);
          return policyEnforcerConfig;
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      }
    });
  }
}