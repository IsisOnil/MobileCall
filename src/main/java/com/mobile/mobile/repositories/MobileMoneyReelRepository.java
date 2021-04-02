package com.mobile.mobile.repositories;

import com.mobile.mobile.model.*;
import org.springframework.stereotype.Repository;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface MobileMoneyReelRepository extends JpaRepository<MobileMoneyReel, Long>{
    
}
