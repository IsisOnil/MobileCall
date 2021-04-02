package com.mobile.mobile.repositories;


import com.mobile.mobile.model.*;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends MongoRepository<Token, Long> {
    @Query(value="{ 'token' :?0 }")
    public Token getToken(String token);
}
