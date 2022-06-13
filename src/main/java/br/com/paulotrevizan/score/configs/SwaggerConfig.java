package br.com.paulotrevizan.score.configs;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    private static final String AUTHORIZATION = "Authorization";

    @Bean
    public OpenAPI api() {
        return new OpenAPI()
                .info(new Info().title("Score App")
                        .description("API REST para cadastro de pessoas com score e afinidades")
                        .version("v1.0.0"))
                .components(new Components()
                        .addSecuritySchemes(AUTHORIZATION,
                                new SecurityScheme()
                                        .name(AUTHORIZATION)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")))
                .addSecurityItem(new SecurityRequirement().addList(AUTHORIZATION));
    }

}
