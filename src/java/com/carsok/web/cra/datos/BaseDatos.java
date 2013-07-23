package com.carsok.web.cra.datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Observable;
import java.util.ResourceBundle;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class BaseDatos extends Observable{
	protected static final Logger log=Logger.getLogger(BaseDatos.class.getName());
	private String _driver="com.mysql.jdbc.Driver";
	private static Connection _conexion; // Cualquier instancia que se genere
//	private String _url="jdbc:mysql://localhost:3306/simeone2013";
//	private String _url="jdbc:mysql://190.1.168.222:3306/simeone2013";
//	private String _url="jdbc:mysql://192.168.1.10:3306/simeone2013";
	private String _url= ResourceBundle.getBundle("com/carsok/web/cra/recursos/Etiquetas").getString("url_base_datos");
	private String _usuario;
	private String _passwd;
	private PreparedStatement _sentencia;	

	public BaseDatos() {
		BasicConfigurator.configure();
		log.info("Contructor clase BaseDatos");
		cargarDriver();
//                conectar();

	}
	

    private void cargarDriver(){
        try {
            Class.forName(this.getDriver());
        } catch (ClassNotFoundException ex) {
        	log.error("No se encuentra el controlador");
        }		
	}
    
    protected Boolean conectar(){
    	Boolean resp=false;
    	try {    	
    		_conexion=DriverManager.getConnection(this.getUrl(), this.getUsuario(), this.getPasswd());
    		_conexion.setAutoCommit(false);
			resp=true;
		} catch (SQLException e) {			
			log.error("No se pudo conectar");			
			log.error(e.getMessage());
		}
    	return resp;
    }
    
    public void commit(){
    	try {
			_conexion.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public void rollback(){
    	try {
			_conexion.rollback();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public PreparedStatement crearSentencia(String sql){
        try {
            this.setSentencia(getConexion().prepareStatement(sql));
            this.debug(sql);
            return _sentencia;
        } catch (SQLException ex) {
        	log.error("Sentencia Mal creada");
            return null;
        }
    }
    
    protected ResultSet consultar(PreparedStatement sentencia){
        ResultSet resp=null;
        try {
            resp= sentencia.executeQuery();
        } catch (SQLException ex) {
        	log.error("No se ejecuto la consulta");
        }
        return resp;
    }

    protected int actualizar(PreparedStatement sentencia){
        int _resp=0;
        try {        	
             _resp=sentencia.executeUpdate();             
        } catch (SQLException ex) {        	
        	log.error("No se pudo Actualizar "+ ex.getMessage());
        }
        return _resp;
        
    }	
    
    public void debug(String sql){
    	log.debug("Sentencia="+sql);
    }

	public String getDriver() {
		return _driver;
	}

	public void setDriver(String _driver) {
		this._driver = _driver;
	}

	public static Connection getConexion() {
		return _conexion;
	}


	public String getUrl() {
		return _url;
	}

	public void setUrl(String _url) {
		this._url = _url;
	}

	public String getUsuario() {
		return _usuario;
	}

	public void setUsuario(String _usuario) {
		this._usuario = _usuario;
	}

	public String getPasswd() {
		return _passwd;
	}

	public void setPasswd(String _passwd) {
		this._passwd = _passwd;
	}

	public PreparedStatement getSentencia() {
		return _sentencia;
	}

	public void setSentencia(PreparedStatement _sentencia) {
		this._sentencia = _sentencia;
	}

}