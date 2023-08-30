package br.unipar.api.ApiPillTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
@EnableSwagger2
public class ApiPillTimeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiPillTimeApplication.class, args);
	}

	@Bean
	public Docket api(){
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("br.unipar.api.ApiPillTime"))
				.paths(PathSelectors.any())
				.build()
				.globalOperationParameters(getGlobalOperationParameters()); // Adiciona os parâmetros de autenticação a todas as operações
	}

	private List<Parameter> getGlobalOperationParameters() {
		List<Parameter> parameters = new ArrayList<>();
		parameters.add(new ParameterBuilder()
				.name("Authorization")
				.modelRef(new ModelRef("string"))
				.parameterType("header")
				.required(false)
				.build());

		return parameters;
	}

}
