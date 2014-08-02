package com.carsok.web.cra.datos;

import com.carsok.web.cra.entidades.IEntidad;
import com.carsok.web.cra.entidades.Norma;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class NormaDao extends BaseDatos implements IPersistencia{
    protected static final Logger logNorma=Logger.getLogger(NormaDao.class.getName());
    private String _sql;

    public NormaDao() {
        BasicConfigurator.configure();
        setUsuario("root");
        setPasswd("usrio01");
        conectar();
    }
        
    @Override
    public int insertar(IEntidad obj) {
        Norma objNorma=(Norma) obj;
        _sql="insert into normas value(?,?,?,?,?)";
        PreparedStatement sentencia=crearSentencia(_sql);

        try {
            sentencia.setInt(1, objNorma.getNumero());
            sentencia.setInt(2, objNorma.getIdTipo());
            sentencia.setTimestamp(3, objNorma.getFecha());
            sentencia.setString(4, objNorma.getDescripcion());
            sentencia.setString(5, objNorma.getArchivo());
        } catch (SQLException ex) {
            logNorma.debug(ex.getStackTrace());
        }
        return actualizar(sentencia);
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
            logNorma.debug(ex.getStackTrace());
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
        _sql="select n.numero,n.idtipo,n.fecha,n.descripcion,n.archivo,t.norma from normas n, tipos_normas t where n.idtipo=t.idtipo;";
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
                norma.setTipo(rsNormas.getString("norma"));
                listNormas.add(norma);
            }
        } catch (SQLException ex) {
            logNorma.debug(ex.getStackTrace());
        }
        return listNormas;
        
    }

}
