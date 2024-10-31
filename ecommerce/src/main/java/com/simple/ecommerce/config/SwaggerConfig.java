package com.simple.ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.servers.Server;
import java.util.*;

@SecurityScheme(
        type = SecuritySchemeType.APIKEY,
        name = "Authorization",
        description = "access Token을 입력해주세요.",
        in = SecuritySchemeIn.HEADER)
@Configuration
public class SwaggerConfig {
    
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .addServersItem(new Server().url("/"))
                .info(getInfo())
                .security(getSecurityRequirement());
    }

    private List<SecurityRequirement> getSecurityRequirement() {
        List<SecurityRequirement> requirements = new ArrayList<>();
        requirements.add(new SecurityRequirement().addList("Authorization"));
        return requirements;
    }

    public Info getInfo() {
        Info info = new Info();
        info.setTitle("IAteIt");
        info.setDescription("IAteIt API를 알아보자!");
        return info;
    }

}
