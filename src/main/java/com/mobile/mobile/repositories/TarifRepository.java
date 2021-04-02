package com.mobile.mobile.repositories;

import com.mobile.mobile.model.*;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarifRepository extends JpaRepository<Tarif, Long>{
    
}