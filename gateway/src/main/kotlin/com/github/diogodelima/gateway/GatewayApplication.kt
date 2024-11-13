package com.github.diogodelima.gateway

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.context.annotation.Bean
import org.springframework.web.reactive.config.EnableWebFlux

@SpringBootApplication
class GatewayApplication {

	@Bean
	fun routeLocator(builder: RouteLocatorBuilder): RouteLocator {

		return builder.routes()
			.route("users_route") { route ->
				route.path("/users/**")
					.uri("http://users-service:8080/users")
			}
			.route("products_route") { route ->
				route.path("/products/**")
					.uri("http://products-service:8080")
			}
			.build()

	}

}

fun main(args: Array<String>) {
	runApplication<GatewayApplication>(*args)
}
