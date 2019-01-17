package com.ipartek.formacion.modelo.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.ipartek.formacion.modelo.pojo.Agente;

public class AgenteDao {
	private final static String SQL_GETBYID = "{call agente_getById()}";

	private final static Logger LOG = Logger.getLogger(AgenteDao.class);
	private static AgenteDao INSTANCE = null;

	// constructor privado, solo acceso por getInstance
	private AgenteDao() {
		super();
	}

	// Esta sincronizado para que no haya dos peticiones al mismo tiempo SINGLETON
	public synchronized static AgenteDao getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new AgenteDao();
		}
		return INSTANCE;
	}

	/**
	 * Este metodo solamente devuelve el agente sin las multas
	 * 
	 * @param id
	 * @return
	 */
	public Agente login(String nombre, String pass) {

		Agente agente = null;
		String sql = "SELECT id, nombre, pass FROM agente WHERE nombre = ? AND pass = ?;";

		try (Connection conn = ConnectionManager.getConnection(); PreparedStatement pst = conn.prepareStatement(sql);) {
			pst.setString(1, nombre);
			pst.setString(2, pass);
			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()) { // hemos encontrado usuario
					agente = new Agente();
					agente.setId(rs.getLong("id"));
					agente.setNombre(rs.getString("nombre"));
					agente.setPass(rs.getString("pass"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return agente;
	}
	
	public Agente getByID(Long id) {
		Agente a = new Agente();

		String sql = SQL_GETBYID;
		try (Connection conn = ConnectionManager.getConnection(); CallableStatement cs = conn.prepareCall(sql);) {
			cs.setLong(1, id);
			try (ResultSet rs = cs.executeQuery();) {
				while (rs.next()) {
					a = rowMapper(rs);
				}
			}
		} catch (Exception e) {
			LOG.debug(e);
		}
		return a;
	}

	
	private Agente rowMapper(ResultSet rs) throws SQLException {
		Agente a = new Agente();
		a.setId(rs.getLong("id"));
		a.setNombre(rs.getString("nombre"));
		a.setPlaca(rs.getLong("placa"));
		a.setPass(rs.getString("pass"));
		
		return a;
}


}
