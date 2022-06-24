package com.example.demoswagger.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;


@Configuration
public class OpenAPIConfig
{
    @Value("${auth.token.url}")
    private String tokenURL;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("OAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.OAUTH2)
                                .scheme("bearer")
                                .bearerFormat("jwt")
                                .in(SecurityScheme.In.HEADER)
                                .name("Authorization")
                                .flows(new OAuthFlows().password(new OAuthFlow().tokenUrl(tokenURL)))))
                .addSecurityItem(new SecurityRequirement().addList("OAuth"))
                .info(new Info().title("TEST API").version("snapshot").description("Test application")
                        .contact(new Contact().name("WD").email("").url("")))
                .addSecurityItem(
                        new SecurityRequirement().addList("bearer-jwt", Arrays.asList("read", "write", "trust")));
    }
}
