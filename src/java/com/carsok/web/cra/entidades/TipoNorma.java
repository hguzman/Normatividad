package com.carsok.web.cra.entidades;

public class TipoNorma implements IEntidad{
    private int _idtipo;
    private String _norma;

    public int getIdtipo() {
        return _idtipo;
    }

    public void setIdtipo(int _idtipo) {
        this._idtipo = _idtipo;
    }

    public String getNorma() {
        return _norma;
    }

    public void setNorma(String _norma) {
        this._norma = _norma;
    }

    @Override
    public boolean comparar(IEntidad objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
