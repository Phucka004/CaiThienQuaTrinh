package vn.iotstar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	// authentication
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        // Tạo user mẫu
        UserDetails admin = User.withUsername("Phucka")
                .password(encoder.encode("123"))
                .roles("ADMIN") // ROLE_USER
                .build();

        UserDetails user = User.withUsername("user")
        		.password(encoder.encode("123"))
                .roles("USER") // ROLE_USER
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }

	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	  @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	         return http.csrf(csrf -> csrf.disable())
	            .authorizeHttpRequests(auth -> auth
	            		.requestMatchers("/").permitAll()  // Không yêu cầu authentication
		                .requestMatchers("/customers/**").authenticated() // Yêu cầu authentication
	            		)
	            .formLogin(Customizer.withDefaults())
	            .build();
	           
	    }
}
