package ru.vaganov.bug2code_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.telegram.telegrambots.starter.TelegramBotStarterConfiguration;

@SpringBootApplication
@ComponentScan(basePackages = {
		"ru.vaganov.bug2code_server",              // текущий проект
		"ru.vaganov.bug2code_core"    // внешний проект
})
@Import(TelegramBotStarterConfiguration.class)
public class Bug2codeServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(Bug2codeServerApplication.class, args);
	}

}
