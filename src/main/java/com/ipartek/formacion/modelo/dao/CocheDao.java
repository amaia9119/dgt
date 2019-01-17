package com.ipartek.formacion.modelo.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.ipartek.formacion.modelo.pojo.Coche;

public class CocheDao {
	private final static Logger LOG = Logger.getLogger(CocheDao.class);
	private final static String SQL_GETBYMATRICULA = "{call coche_getByMatricula(?)}";
	private final static String SQL_GETBYID="{call coche_getById(?)}";
	// c.km FROM coche AS c WHERE c.id =?;";

	private static CocheDao INSTANCE = null;

	// constructor privado, solo acceso por getInstance
	private CocheDao() {
		super();
	}

	// Esta sincronizado para que no haya dos peticiones al mismo tiempo SINGLETON
	public synchronized static CocheDao getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new CocheDao();
		}
		return INSTANCE;
	}

	public Coche getByMatricula(String matricula) throws SQLException {
		Coche c = null;
		try (Connection conn = ConnectionManager.getConnection();
				CallableStatement cs = conn.prepareCall(SQL_GETBYMATRICULA);) {
			cs.setString(1, matricula);
			try (ResultSet rs = cs.executeQuery()) {

				while (rs.next()) {
					try {
						c = new Coche(rs.getLong("id"), rs.getString("matricula"), rs.getString("modelo"),
								rs.getInt("km"));

					} catch (Exception e) {
						LOG.error("matricula no valida");
					}
				} // while

			} catch (Exception e) {
				LOG.debug(e);
			}
			return c;
		}
		}
		
		public Coche getById(Long id) throws SQLException {
			Coche c = null;
			try (Connection conn = ConnectionManager.getConnection();
					CallableStatement cs = conn.prepareCall(SQL_GETBYID);) {
				cs.setLong(1, id);
				try (ResultSet rs = cs.executeQuery()) {

					while (rs.next()) {
						try {
							c = new Coche(rs.getLong("id"), rs.getString("matricula"), rs.getString("modelo"),
									rs.getInt("km"));

						} catch (Exception e) {
							LOG.error("Id no valido");
						}
					} // while

				} catch (Exception e) {
					LOG.debug(e);
				}
				return c;
			}

//	private Coche rowMapper(ResultSet rs) throws SQLException {
//		Coche c  =new Coche();
//			c.setId(rs.getLong("id"));
//			c.setMatricula(rs.getString("matricula"));
//			c.setModelo(rs.getString("modelo"));
//			c.setKm(rs.getInt("km"));
//		return c;
//}

	}
}
