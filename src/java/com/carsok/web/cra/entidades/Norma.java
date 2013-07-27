package com.carsok.web.cra.entidades;
//Clase Utilizada para guardar las normas

import java.sql.Timestamp;

public class Norma implements IEntidad{
    private int _numero;
    private int _idTipo;
    private String _tipo;
    private Timestamp _fecha;
    private String _descripcion;
    private String _archivo;

    public int getNumero() {
        return _numero;
    }

    public void setNumero(int _numero) {
        this._numero = _numero;
    }

    public int getIdTipo() {
        return _idTipo;
    }

    public void setIdTipo(int _idTipo) {
        this._idTipo = _idTipo;
    }

    public Timestamp getFecha() {
        return _fecha;
    }

    public void setFecha(Timestamp _fecha) {
        this._fecha = _fecha;
    }

    public String getDescripcion() {
        return _descripcion;
    }

    public void setDescripcion(String _descripcion) {
        this._descripcion = _descripcion;
    }

    public String getArchivo() {
        return _archivo;
    }

    public void setArchivo(String _archivo) {
        this._archivo = _archivo;
    }
    
    

    @Override
    public boolean comparar(IEntidad objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
