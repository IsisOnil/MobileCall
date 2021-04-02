package com.mobile.mobile.repositories;

import com.mobile.mobile.model.*;
import org.springframework.stereotype.Repository;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface  TransfertRepository extends JpaRepository<Transfert, Long> {
    // @Query( nativeQuery = true, value = "SELECT * from transfert where transfert.iduser = :iduser")
    // Transfert findByIdUser(long iduser);
}
