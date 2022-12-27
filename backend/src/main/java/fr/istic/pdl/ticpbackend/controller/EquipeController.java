package fr.istic.pdl.ticpbackend.controller;

import fr.istic.pdl.ticpbackend.service.EquipeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@AllArgsConstructor
@RequestMapping("api/v1/equipes")
public class EquipeController {
    EquipeService service;
}
