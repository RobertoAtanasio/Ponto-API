package com.rapl.ponto.api.config;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.rapl.ponto.api.security.utils.JwtTokenUtil;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@Profile("dev")
@EnableSwagger2
public class SwaggerConfig {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService userDetailsService;

//	@Bean
//	public Docket api() {		
//		return new Docket(DocumentationType.SWAGGER_2).select()
//				.apis(RequestHandlerSelectors.basePackage("com.rapl.ponto.api.controllers"))
////				.apis(RequestHandlerSelectors.basePackage("com.rapl.ponto.api.security.controllers"))
//				.paths(PathSelectors.any()).build()
//				.apiInfo(apiInfo());
//	}

	@Bean
	public Docket api() {
		
		String swaggerToken;
		try {
			UserDetails userDetails = this.userDetailsService.loadUserByUsername("admin@admin.com");
			swaggerToken = this.jwtTokenUtil.obterToken(userDetails);
		} catch (Exception e) {
			swaggerToken = "";
		}
		
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.rapl.ponto.api.controllers"))
				.paths(PathSelectors.any()).build()
				.apiInfo(apiInfo())
				.globalOperationParameters(
		            		Collections.singletonList(
		                    new ParameterBuilder()
		                            .name("Authorization")
		                            .modelRef(new ModelRef("string"))
		                            .parameterType("header")
		                            .required(true)
		                            .hidden(true)
		                            .defaultValue("Bearer " + swaggerToken)
		                            .build()
		                    )
		            );
		            
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Ponto API")
				.description("Documentação da API de acesso aos endpoints do Ponto de Funcionários.").version("1.0")
				.build();
	}

//	@Bean
//	public SecurityConfiguration security() {
//		String token;
//		try {
//			UserDetails userDetails = this.userDetailsService.loadUserByUsername("admin@admin.com");
//			token = this.jwtTokenUtil.obterToken(userDetails);
//		} catch (Exception e) {
//			token = "";
//		}
//		return new SecurityConfiguration(null, null, null, null, "Bearer " + token, ApiKeyVehicle.HEADER,
//				"Authorization", ",");
//	}

}
