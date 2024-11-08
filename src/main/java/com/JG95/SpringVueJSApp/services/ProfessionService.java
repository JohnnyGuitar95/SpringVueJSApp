package com.JG95.SpringVueJSApp.services;

import com.JG95.SpringVueJSApp.entities.Profession;
import com.JG95.SpringVueJSApp.repositories.ProfessionRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProfessionService {


    ProfessionRepository professionRepository;

    public List<Profession> getAllProfessions() {
        return professionRepository.findAll();
    }

    public ResponseEntity<Profession> getProfessionById(Long id) {
        Optional<Profession> profession = professionRepository.findById(id);
        return profession.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public Profession createProfession(Profession profession) {
        return professionRepository.save(profession);
    }

    public Profession updateProfession(Profession profession) {
        return professionRepository.save(profession);
    }

    public ResponseEntity<Void> deleteProfession(Long id) {
        if (professionRepository.existsById(id)) {
            professionRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
