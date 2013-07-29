package com.carsok.web.cra.controladores;

import com.carsok.web.cra.datos.NormaDao;
import com.carsok.web.cra.datos.TipoNormaDao;
import com.carsok.web.cra.entidades.IEntidad;
import com.carsok.web.cra.entidades.Norma;
import com.carsok.web.cra.entidades.TipoNorma;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.faces.model.SelectItem;

public class CtrlNorma implements Observer{
    List<Norma> _lista;
    List<Norma> _normasFiltradas;
    private SelectItem[] _tiposNormas;
    private Norma _normaSeleccionada;
    
    public CtrlNorma() {
        cargarDatos();
        _tiposNormas=crearFiltro();
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
    
    private SelectItem[] crearFiltro()  {  
        TipoNormaDao tipos=new TipoNormaDao();
        List<IEntidad> lista=tipos.listaObjetos();

        SelectItem[] options = new SelectItem[lista.size()+1];  
        int x=1;
        options[0] = new SelectItem("", "Select");  
        for (IEntidad tipoNorma:lista){
            options[x]=new SelectItem(((TipoNorma)tipoNorma).getNorma(),((TipoNorma)tipoNorma).getNorma());
            x=x+1;
        }
  
//        options[1] = new SelectItem("ACUERDOS", "ACUERDOS");  
//        options[2] = new SelectItem("RESOLUCIONES", "RESOLUCIONES");  
        return options;  
    }  

    public SelectItem[] getTiposNormas() {
        return _tiposNormas;
    }

    public List<Norma> getNormasFiltradas() {
        return _normasFiltradas;
    }

    public void setNormasFiltradas(List<Norma> _normasFiltradas) {
        this._normasFiltradas = _normasFiltradas;
    }

    public Norma getNormaSeleccionada() {
        return _normaSeleccionada;
    }

    public void setNormaSeleccionada(Norma _normaSeleccionada) {
        this._normaSeleccionada = _normaSeleccionada;
    }
    
}
