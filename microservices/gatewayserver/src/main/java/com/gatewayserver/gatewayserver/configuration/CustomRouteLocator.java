package com.gatewayserver.gatewayserver.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomRouteLocator {
    @Bean
    RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p.path("/hello/ac/**")
                        .filters(f -> f.rewritePath("/hello/accounts/(?<segment>.*)", "/${segment}"))
                        .uri("lb://ACCOUNTS"))
                .route(p -> p.path("/hello/loans/**")
                        .filters(f -> f.rewritePath("/hello/loans/(?<segment>.*)", "/${segment}"))
                        .uri("lb://LOANS"))
                .build();
    }
}
