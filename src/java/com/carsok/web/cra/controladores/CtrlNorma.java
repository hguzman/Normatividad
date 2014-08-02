package com.carsok.web.cra.controladores;

import com.carsok.web.cra.datos.NormaDao;
import com.carsok.web.cra.datos.TipoNormaDao;
import com.carsok.web.cra.entidades.IEntidad;
import com.carsok.web.cra.entidades.Norma;
import com.carsok.web.cra.entidades.TipoNorma;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.apache.log4j.Logger;
import org.apache.myfaces.custom.fileupload.UploadedFile;

@ManagedBean
@RequestScoped
public class CtrlNorma implements Observer, Serializable{
    List<Norma> _lista;
    List<Norma> _normasFiltradas;
    private SelectItem[] _tiposNormas;
    private Norma _normaSeleccionada;
    private Integer _numNorma;
    private int _idTipo;
    private Date _fecha;
    private String _descripcion;
    private String _archivo;
    private UploadedFile _file;
    FacesMessage msg;
    private static final String RUTA = java.util.ResourceBundle.getBundle("com/carsok/web/cra/recursos/Etiquetas").getString("ruta");
    private static final Logger log=Logger.getLogger(CtrlNorma.class);

   
    public CtrlNorma() {
        cargarDatos();
        _tiposNormas=crearFiltro();
    }
    
    public void guardar(){
        log.debug("valor de file="+_file);
//        log.debug("NOMBRE file="+_file.getName());
        if (_file != null){
            log.debug("Selecciono Archivo");
            if (upload()){
//                File nuevoArchivo= new File(RUTA, _file.getFileName());
                File nuevoArchivo= new File(RUTA, _file.getName());
                Norma objNorma= new Norma();
                objNorma.setNumero(getNumNorma());
                objNorma.setIdTipo(_idTipo);
                objNorma.setFecha((Timestamp) getFecha());
                objNorma.setDescripcion(getDescripcion());
                objNorma.setArchivo(nuevoArchivo.getPath());
                NormaDao normaDao=new NormaDao();
                try {
                    if (normaDao.insertar(objNorma)>0){
                        msg=new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Guardado", "Muy bien!");
                    }else{
                        msg=new FacesMessage(FacesMessage.SEVERITY_ERROR, "No Guardado", "Lo siento!");						
                    }
                } catch (Exception e) {
                }				
               }
			
            }else{
                    log.debug("NO SELECCIONO");			
                    msg=new FacesMessage(FacesMessage.SEVERITY_ERROR, "No selecciono archivo", "Lo siento!");
            }
            FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    private boolean upload(){
        boolean resp=false;
        log.debug("Nombre archivo"+_file.getName());
        try {
            String archivo=_file.getName();
            InputStream inputStream = _file.getInputStream();
            copiarArchivo(archivo, inputStream);
            resp=true;
        } catch (IOException e) {
            log.error("No se pudo cargar el inputStream");
            resp=false;
        }
        return resp;
    }
    
    public void copiarArchivo(String nomArchivo, InputStream in){
        File nuevoArchivo= new File(RUTA, nomArchivo);
        try {
            OutputStream outputStream=new FileOutputStream(nuevoArchivo);
            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = in.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }

            in.close();
            outputStream.flush();
            outputStream.close();

	} catch (FileNotFoundException e) {
            log.error("Archivo no encontrado");
	} catch (IOException e) {
            log.error("Error entrada y salida");
	}
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
        options[0] = new SelectItem("", "Seleccione");  
        for (IEntidad tipoNorma:lista){
            options[x]=new SelectItem(((TipoNorma)tipoNorma).getNorma(),((TipoNorma)tipoNorma).getNorma());
            x=x+1;
        }
  
//        options[1] = new SelectItem("ACUERDOS", "ACUERDOS");  
//        options[2] = new SelectItem("RESOLUCIONES", "RESOLUCIONES");  
        return options;  
    }  

    public SelectItem[] getListaNormas(){  
        TipoNormaDao tipos=new TipoNormaDao();
        List<IEntidad> lista=tipos.listaObjetos();

        SelectItem[] options = new SelectItem[lista.size()+1];  
        int x=1;
        options[0] = new SelectItem("", "Seleccione");  
        for (IEntidad tipoNorma:lista){
            options[x]=new SelectItem(((TipoNorma)tipoNorma).getIdtipo(),((TipoNorma)tipoNorma).getNorma());
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

    public Integer getNumNorma() {
        return _numNorma;
    }

    public void setNumNorma(Integer _numNorma) {
        this._numNorma = _numNorma;
    }

    public int getIdTipo() {
        return _idTipo;
    }

    public void setIdTipo(int _idTipo) {
        this._idTipo = _idTipo;
    }

    public Date getFecha() {
        return _fecha;
    }

    public void setFecha(Date _fecha) {
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

    public UploadedFile getFile() {
        return _file;
    }

    public void setFile(UploadedFile _file) {
        this._file = _file;
    }

}
