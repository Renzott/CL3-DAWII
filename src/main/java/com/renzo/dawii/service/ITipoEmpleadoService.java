package com.renzo.dawii.service;

import com.renzo.dawii.bean.TipoEmpleado;

import java.util.List;

public interface ITipoEmpleadoService {

    List<TipoEmpleado> getAll();

    TipoEmpleado setTipoEmpleado(TipoEmpleado bean);

}
