package com.ipartek.formacion.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.ipartek.formacion.modelo.pojo.Agente;

public class AgenteDao {
	private final static String SQL_GETBYID = "SELECT id, nombre, placa, id_departamento FROM agente WHERE id=?";

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
	public Agente getByID(Long id) {
		Agente a = new Agente();

		String sql = SQL_GETBYID;
		try (Connection conn = ConnectionManager.getConnection(); PreparedStatement pst = conn.prepareStatement(sql);) {
			pst.setLong(1, id);
			try (ResultSet rs = pst.executeQuery();) {
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
		
		return a;
}


}
