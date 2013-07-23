package com.carsok.web.cra.datos;

import com.carsok.web.cra.entidades.IEntidad;
import com.carsok.web.cra.entidades.Norma;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

public class NormaDao extends BaseDatos implements IPersistencia{
    protected static final Logger log=Logger.getLogger(NormaDao.class.getName());
    private String _sql;

    public NormaDao() {
        setUsuario("root");
        setPasswd("usrio01");
        conectar();
    }
        
    @Override
    public int insertar(IEntidad obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<IEntidad> listaObjetos() {
        List<IEntidad> listNormas= new ArrayList<>();
        _sql="select * from normas";
        PreparedStatement sentencia=crearSentencia(_sql);
        ResultSet rsNormas=consultar(sentencia);
        try {
            while (rsNormas.next()) {            
                Norma norma=new Norma();
                norma.setNumero(rsNormas.getInt("numero"));
                norma.setIdTipo(rsNormas.getInt("idtipo"));
                norma.setFecha(rsNormas.getTimestamp("fecha"));
                norma.setDescripcion(rsNormas.getString("descripcion"));
                norma.setArchivo(rsNormas.getString("archivo"));
                listNormas.add(norma);
            }
        } catch (SQLException ex) {
            log.debug(ex.getStackTrace());
        }
        return listNormas;
        
    }

    @Override
    public int modificar(IEntidad obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IEntidad buscar(String[] codigos) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Norma> listaNormas() {
        List<Norma> listNormas= new ArrayList<>();
        _sql="select * from normas";
        PreparedStatement sentencia=crearSentencia(_sql);
        ResultSet rsNormas=consultar(sentencia);
        try {
            while (rsNormas.next()) {            
                Norma norma=new Norma();
                norma.setNumero(rsNormas.getInt("numero"));
                norma.setIdTipo(rsNormas.getInt("idtipo"));
                norma.setFecha(rsNormas.getTimestamp("fecha"));
                norma.setDescripcion(rsNormas.getString("descripcion"));
                norma.setArchivo(rsNormas.getString("archivo"));
                listNormas.add(norma);
            }
        } catch (SQLException ex) {
            log.debug(ex.getStackTrace());
        }
        return listNormas;
        
    }

}
