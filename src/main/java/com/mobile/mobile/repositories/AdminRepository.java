package com.mobile.mobile.repositories;

import com.mobile.mobile.model.*;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends MongoRepository<Admin, Long> {
    @Query(value="{ 'name' :?0 , 'password' :?1 }")
    User checkmdp(String name,String password);
    
}
