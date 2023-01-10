package fr.istic.pdl.ticpbackend;

import fr.istic.pdl.ticpbackend.model.Admin;
import fr.istic.pdl.ticpbackend.repository.AdminRepository;
import fr.istic.pdl.ticpbackend.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;


@SpringBootApplication
public class TicpbackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicpbackendApplication.class, args);
	}

}
