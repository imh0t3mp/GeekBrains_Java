package name.imh0t3mp.course.geekbrains.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.collect.Lists.newArrayList;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public static final String DEFAULT_INCLUDE_PATTERN = "/api/.*";

    private final Logger log = LoggerFactory.getLogger(SwaggerConfig.class);


    @Value("${api.version}")
    private String apiVersion = "1.0";
    @Value("${swagger.enabled}")
    private String enabled = "true";
    @Value("${swagger.title}")
    private String title = "REST Api";
    @Value("${swagger.description}")
    private String description = "API for REST project";
    @Value("${swagger.license}")
    protected String swaggerLicense = "MIT";
    @Value("${swagger.licenseUrl}")
    protected String swaggerLicenseUrl = "";


    @Bean
    public Docket swaggerDocket() {
        log.debug("Starting Swagger");
        StopWatch watch = new StopWatch();
        watch.start();
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .enable(Boolean.valueOf(enabled))
                .apiInfo(apiInfo())
                .securitySchemes(newArrayList(new BasicAuth("test")))
                .genericModelSubstitutes(ResponseEntity.class)
                .forCodeGeneration(true)
                .genericModelSubstitutes(ResponseEntity.class)
                .select()
                .paths(regex(DEFAULT_INCLUDE_PATTERN))
                .build();
        watch.stop();
        log.debug("Started Swagger in {} ms", watch.getTotalTimeMillis());
        return docket;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .version(apiVersion)
                .license(swaggerLicense)
                .licenseUrl(swaggerLicenseUrl)
                .build();
    }
}