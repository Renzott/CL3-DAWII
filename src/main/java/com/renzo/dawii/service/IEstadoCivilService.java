package com.renzo.dawii.service;

import com.renzo.dawii.bean.EstadoCivil;

import java.util.List;

public interface IEstadoCivilService {

    List<EstadoCivil> getAll();

    EstadoCivil setEstadoCivil(EstadoCivil bean);

}
