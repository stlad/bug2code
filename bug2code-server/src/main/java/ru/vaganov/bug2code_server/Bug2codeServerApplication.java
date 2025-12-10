package ru.vaganov.bug2code_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
		"ru.vaganov.bug2code_server",              // текущий проект
		"ru.vaganov.bug2code_core"    // внешний проект
})
public class Bug2codeServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(Bug2codeServerApplication.class, args);
	}

}
