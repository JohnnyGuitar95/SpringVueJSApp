package com.JG95.SpringVueJSApp.repositories;

import com.JG95.SpringVueJSApp.entities.Profession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessionRepository extends JpaRepository<Profession, Long> {

}
