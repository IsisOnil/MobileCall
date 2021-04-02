package com.mobile.mobile.repositories;

import com.mobile.mobile.model.*;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, Long> {
    @Query(value="{ 'number' :?0 , 'password' :?1 }")
    User checkmdp(String number,String password);
}
