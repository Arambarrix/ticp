package fr.istic.pdl.ticpbackend.controller;

import fr.istic.pdl.ticpbackend.service.JoueurService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@AllArgsConstructor
@RequestMapping("api/v1/joueur")
public class JoueurController {
    JoueurService service;

}
