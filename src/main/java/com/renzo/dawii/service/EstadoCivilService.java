package com.renzo.dawii.service;

import com.renzo.dawii.bean.EstadoCivil;
import com.renzo.dawii.repository.EstadoCivilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoCivilService implements IEstadoCivilService {

    @Autowired
    private EstadoCivilRepository repository;

    @Override
    public List<EstadoCivil> getAll() {
        return (List<EstadoCivil>)repository.findAll();
    }

    @Override
    public EstadoCivil setEstadoCivil(EstadoCivil bean) {
        return repository.save(bean);
    }
}
