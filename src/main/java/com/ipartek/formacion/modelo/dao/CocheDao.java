package com.ipartek.formacion.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ipartek.formacion.modelo.pojo.Coche;

public class CocheDao {

	private final static String SQL_GETBYMATRICULA = "SELECT c.id, c.matricula, c.modelo, c.km FROM coche AS c WHERE c.matricula =?;";
	private final static String SQL_GETBYID="SELECT c.id, c.matricula, c.modelo, c.km FROM coche AS c WHERE c.id =?;";
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
				PreparedStatement pst = conn.prepareStatement(SQL_GETBYMATRICULA);) {
			pst.setString(1, matricula);
			try (ResultSet rs = pst.executeQuery()) {

				while (rs.next()) {
					try {
						c = new Coche(rs.getLong("id"), rs.getString("matricula"), rs.getString("modelo"),
								rs.getInt("km"));

					} catch (Exception e) {
						System.out.println("matricula no valida");
						e.printStackTrace();
					}
				} // while

			} catch (Exception e) {
				e.printStackTrace();
			}
			return c;
		}
		}
		
		public Coche getById(Long id) throws SQLException {
			Coche c = null;
			try (Connection conn = ConnectionManager.getConnection();
					PreparedStatement pst = conn.prepareStatement(SQL_GETBYID);) {
				pst.setLong(1, id);
				try (ResultSet rs = pst.executeQuery()) {

					while (rs.next()) {
						try {
							c = new Coche(rs.getLong("id"), rs.getString("matricula"), rs.getString("modelo"),
									rs.getInt("km"));

						} catch (Exception e) {
							System.out.println("matricula no valida");
							e.printStackTrace();
						}
					} // while

				} catch (Exception e) {
					e.printStackTrace();
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
