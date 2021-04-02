package com.mobile.mobile.repositories;

import com.mobile.mobile.model.*;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InfoOffreRepository extends JpaRepository<InfoOffre, Long>{
    // @Query("SELECT * FROM infooffre  WHERE infooffre.idoffre = ?1 ")
    // public InfoOffre findInfoOffreByIdOffre(long idOffre); 
}
