package com.renzo.dawii.service;

import com.renzo.dawii.bean.Departamento;
import com.renzo.dawii.repository.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartamentoService implements IDepartamentoService {

    @Autowired
    private DepartamentoRepository repository;

    @Override
    public List<Departamento> getAll() {
        return (List<Departamento>)repository.findAll();
    }

    @Override
    public Departamento setDepartamento(Departamento bean) {
        return repository.save(bean);
    }
}
