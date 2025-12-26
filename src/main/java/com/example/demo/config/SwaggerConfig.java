@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI api() {
        return new OpenAPI()
                .info(new Info()
                        .title("Hostel Roommate Matcher API")
                        .version("1.0")
                        .description("API documentation"));
    }
}
