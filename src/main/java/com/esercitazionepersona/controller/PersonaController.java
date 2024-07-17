package com.esercitazionepersona.controller;


import com.esercitazionepersona.model.Persona;
import com.esercitazionepersona.repository.PersonaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/persona")
public class PersonaController {


    @GetMapping(value = "/selezionaTuttePersone")
    public static List<Persona> selectAllPersona()
    {
            return  PersonaRepository.selectAllPersona();
    }

    @PostMapping(value = "inserisciPersona")
    public static Persona inserisciPersona(@RequestBody Persona persona)
    {
         return PersonaRepository.inserisciPersona(persona);
    }



    // Aggiungi il nuovo metodo per selezionare i libri per genere
    @PostMapping("/selezionaPersonaPerEmail")
    public List<Persona> selezionaPersonaPerEmail(@RequestBody String email) {
        return PersonaRepository.selezionaPersonaPerEmail(email);
    }

}
