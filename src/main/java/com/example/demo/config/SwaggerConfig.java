@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI api() {   // <-- rename to api()

        SecurityScheme securityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT");

        Server server = new Server()
                .url("https://9248.408procr.amypo.ai/")
                .description("Production Server");

        return new OpenAPI()
                .addServersItem(server)
                .components(
                        new Components().addSecuritySchemes("bearerAuth", securityScheme)
                )
                .addSecurityItem(
                        new SecurityRequirement().addList("bearerAuth")
                );
    }
}
