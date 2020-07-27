package com.renzo.dawii.service;

import com.renzo.dawii.bean.TipoEmpleado;
import com.renzo.dawii.repository.TipoEstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoEmpleadoService implements ITipoEmpleadoService {

    @Autowired
    private TipoEstadoRepository repository;

    @Override
    public List<TipoEmpleado> getAll() {
        return (List<TipoEmpleado>)repository.findAll();
    }

    @Override
    public TipoEmpleado setTipoEmpleado(TipoEmpleado bean) {
        return repository.save(bean);
    }
}
