package com.sinabro.sinabro.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import jakarta.servlet.ServletContext;

@Configuration
public class SwaggerConfig {
	private static final String PROJECT_NAME = "Sinabro";
	private static final String SERVER_NAME = PROJECT_NAME +" Server";
	private static final String API_TITLE = PROJECT_NAME +" 서버 API 문서";
	private static final String API_DESCRIPTION = PROJECT_NAME + " 서버 API 문서입니다";
	private static final String GITHUB_URL = "https://github.com/Sinabr0/back";

	@Value("${swagger.version}")
	private String version;

	@Bean
	public OpenAPI openAPI(ServletContext servletContext) {
		Server server = new Server().url(servletContext.getContextPath()).description(API_DESCRIPTION);
		return new OpenAPI()
			.servers(List.of(server))
			.info(swaggerInfo());
	}

	private Info swaggerInfo() {
		License license = new License();
		license.setUrl(GITHUB_URL);
		license.setName(SERVER_NAME);

		return new Info()
			.version("v" + version)
			.title(API_TITLE)
			.description(API_DESCRIPTION)
			.license(license);
	}
}
