package com.diogo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.diogo.api.controller.ApiController;

/**
 * 
 * @author dbarreiros
 *
 * Responsável por iniciar a aplicação.
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.diogo")
public class Application implements CommandLineRunner {

	@Autowired
	ApiController apiController;

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    	apiController.run();
    }
}
