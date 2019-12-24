package com.example.demo.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.google.common.base.Predicates;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

//	@Bean
//    public Docket api() { 
//        return new Docket(DocumentationType.SWAGGER_2)  
//          .select()                             
//          .apis(RequestHandlerSelectors.basePackage("com.example.demo.web"))
//          //.apis(RequestHandlerSelectors.any())              
//          //.paths(PathSelectors.any())                          
//          .build();                                           
//    }
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.example.demo.web")).build().pathMapping("/")
	            .securitySchemes(Arrays.asList(apiKey()))
	            .securityContexts(Arrays.asList(securityContext()))
	            .apiInfo(ApiInfo.DEFAULT);
	}

	 private SecurityContext securityContext() {
	        return SecurityContext.builder()
	                .securityReferences(defaultAuth())
	                .forPaths(PathSelectors.regex("/api.*"))
	                .build();
	    }

	  private List<SecurityReference> defaultAuth() {
	        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
	        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
	        authorizationScopes[0] = authorizationScope;
	        return Arrays.asList(new SecurityReference("apiKey", authorizationScopes));
	    }

	private ApiKey apiKey() {
	    return new ApiKey("Authorization", "Authorization", "header");
	}
	
}
