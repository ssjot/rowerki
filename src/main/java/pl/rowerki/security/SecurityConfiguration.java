package pl.rowerki.security;

import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .requiresChannel(channel ->
                        channel.anyRequest().requiresSecure())
                .authorizeHttpRequests((requests) -> requests
                        .dispatcherTypeMatchers(DispatcherType.FORWARD, DispatcherType.ERROR).permitAll()
                        .requestMatchers("/", "/index.html", "/static/**").permitAll()
                        .requestMatchers("/service/admin/**").hasAuthority("admin")
                        .requestMatchers("/service/employee/**").hasAuthority("employee")
                        .requestMatchers("/service/**").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/locations/**").hasAuthority("employee")
                        .requestMatchers("/api/locations/**").hasAuthority("admin")
                        .requestMatchers("/api/orders/**").hasAuthority("employee")
                        .requestMatchers("/api/users/isAdmin").hasAuthority("employee")
                        .requestMatchers("/api/users/**").hasAuthority("admin")
                        .requestMatchers("/api/vehicles/**").hasAuthority("admin")
                        .requestMatchers(HttpMethod.GET, "/api/vehicleKinds/**").hasAuthority("employee")
                        .requestMatchers("/api/vehicleKinds/**").hasAuthority("admin")
                        .requestMatchers("/api/workDay/**").hasAuthority("employee"))
                .formLogin((form) -> form
                        .defaultSuccessUrl("/service", true)
                        .permitAll()
                )
                .logout(LogoutConfigurer::permitAll);

        return http.build();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

}