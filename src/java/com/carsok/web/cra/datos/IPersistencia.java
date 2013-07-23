package com.carsok.web.cra.datos;

import com.carsok.web.cra.entidades.IEntidad;
import java.util.List;

public interface IPersistencia {
	public int insertar(IEntidad obj); 
        public List<IEntidad> listaObjetos();
        public int modificar(IEntidad obj);
        public IEntidad buscar(String[] codigos) throws Exception;
}