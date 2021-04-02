package com.mobile.mobile.service;

import com.mobile.mobile.model.Transfert;
import com.mobile.mobile.repositories.TransfertRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransfertService implements ITransfertService {
    @Autowired
    private TransfertRepository repository;

    @Override
    public List<Transfert> findAll() {

        List<Transfert> Transferts = (List<Transfert>) repository.findAll();

        return Transferts;
    }
}
