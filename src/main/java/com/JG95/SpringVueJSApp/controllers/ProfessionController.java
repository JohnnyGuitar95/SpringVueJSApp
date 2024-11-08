package com.JG95.SpringVueJSApp.controllers;

import com.JG95.SpringVueJSApp.entities.Profession;
import com.JG95.SpringVueJSApp.services.ProfessionService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("professionsApi")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@CrossOrigin(origins = "http://localhost:5173")
public class ProfessionController {


    ProfessionService professionService;

    @GetMapping("/professions")
    public List<Profession> getAllProfessions() {
        return professionService.getAllProfessions();
    }

    @GetMapping("/profession/{id}")
    public ResponseEntity<Profession> getProfessionById(@PathVariable Long id) {
        return professionService.getProfessionById(id);
    }

    @PostMapping("/add")
    public Profession createProfession(@RequestBody Profession profession) {
        return professionService.createProfession(profession);
    }

    @PutMapping("/profession")
    public Profession updateProfession(@RequestBody Profession profession) {
        return professionService.updateProfession(profession);
    }

    @DeleteMapping("/profession/{id}")
    public ResponseEntity<Void> deleteProfession(@PathVariable Long id) {
        return professionService.deleteProfession(id);
    }
}
