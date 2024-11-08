package com.JG95.SpringVueJSApp;

import com.JG95.SpringVueJSApp.entities.Profession;
import com.JG95.SpringVueJSApp.repositories.DepartmentRepository;
import com.JG95.SpringVueJSApp.repositories.EmployeeRepository;
import com.JG95.SpringVueJSApp.repositories.ProfessionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringVueJsAppApplication {

    public static void main(String[] args) {
		SpringApplication.run(SpringVueJsAppApplication.class, args);

	}

}
