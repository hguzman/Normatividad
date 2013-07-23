package com.carsok.web.cra.controladores;

import com.carsok.web.cra.datos.NormaDao;
import com.carsok.web.cra.entidades.Norma;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class CtrlNorma implements Observer{
    List<Norma> _lista;

    public CtrlNorma() {
        cargarDatos();
    }
    public void cargarDatos(){
        NormaDao datos=new NormaDao();
        _lista=datos.listaNormas();
    }

    public List<Norma> getLista() {
        return _lista;
    }

    public void setLista(List<Norma> _lista) {
        this._lista = _lista;
    }

    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
