package com.carsok.web.cra.datos;

import com.carsok.web.cra.entidades.IEntidad;
import com.carsok.web.cra.entidades.Norma;
import com.carsok.web.cra.entidades.TipoNorma;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TipoNormaDao extends BaseDatos implements IPersistencia{
    private String _sql;

    @Override
    public int insertar(IEntidad obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<IEntidad> listaObjetos() {
        List<IEntidad> listTiposNormas= new ArrayList<>();
        _sql="select * from tipos_normas";
        PreparedStatement sentencia=crearSentencia(_sql);
        ResultSet rsTiposNormas=consultar(sentencia);
        try {
            while (rsTiposNormas.next()) {            
                TipoNorma tipoNorma=new TipoNorma();
                tipoNorma.setIdtipo(rsTiposNormas.getInt("idtipo"));
                tipoNorma.setNorma(rsTiposNormas.getString("norma"));
                listTiposNormas.add(tipoNorma);
            }
        } catch (SQLException ex) {
            log.debug(ex.getStackTrace());
        }
        return listTiposNormas;

    }

    @Override
    public int modificar(IEntidad obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IEntidad buscar(String[] codigos) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
