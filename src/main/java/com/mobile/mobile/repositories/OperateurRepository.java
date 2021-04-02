package com.mobile.mobile.repositories;

import com.mobile.mobile.model.*;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperateurRepository extends JpaRepository<Operateur, Long> {

    @Query("SELECT * FROM operateur  WHERE operateur.id = ?1 ")
    public Operateur findOperateurById(String id); 
}
