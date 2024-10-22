package com.github.diogodelima.gateway

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.context.annotation.Bean

@SpringBootApplication
class GatewayApplication {

	@Bean
	fun routeLocator(builder: RouteLocatorBuilder): RouteLocator {

		return builder.routes()
			.route("users_route") { route ->
				route.path("/users/**")
					.uri("http://localhost:8081/users")
			}
			.route("users_api_route") { route ->
				route.path("/api/users/**")
					.uri("http://localhost:8081/users")
			}
			.route("products_route") { route ->
				route.path("/products/**")
					.uri("http://localhost:8082")
			}
			.build()

	}

}

fun main(args: Array<String>) {
	runApplication<GatewayApplication>(*args)
}
