package com.renzo.dawii.service;

import com.renzo.dawii.bean.Departamento;

import java.util.List;

public interface IDepartamentoService {

    List<Departamento> getAll();

    Departamento setDepartamento(Departamento bean);

}
