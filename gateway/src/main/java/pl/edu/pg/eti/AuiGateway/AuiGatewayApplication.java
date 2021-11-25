package pl.edu.pg.eti.AuiGateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
public class AuiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuiGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator makeRoutes(RouteLocatorBuilder builder,
								   @Value("${gateway.host}") String hostname,
								   @Value("${api.companies.url}") String companyUrl,
								   @Value("${api.players.url}") String playerUrl)
	{
		return builder.routes()
				.route("player", p -> p
						.host(hostname)
						.and()
							.path("/api/players", "/api/players/{id}")
						.uri(playerUrl))
				.route("company", p -> p
						.host(hostname)
						.and()
						.path("/api/companies",
								"/api/companies/**",
								"/api/players/{p_id}/companies",
								"/api/players/{p_id}/companies/**")
						.uri(companyUrl))
				.build();
	}

	@Bean
	public CorsWebFilter makeCorsWebFilter() {
		CorsConfiguration corsConfig = new CorsConfiguration();
		corsConfig.setAllowedOrigins(Collections.singletonList("*"));
		corsConfig.setMaxAge(3600L);
		corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT"));
		corsConfig.addAllowedHeader("*");

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfig);

		return new CorsWebFilter(source);
	}

}
