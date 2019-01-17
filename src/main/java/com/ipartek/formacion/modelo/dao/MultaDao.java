package com.ipartek.formacion.modelo.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.ipartek.formacion.modelo.pojo.Coche;
import com.ipartek.formacion.modelo.pojo.Multa;

public class MultaDao {
	
	private final static Logger LOG = Logger.getLogger(MultaDao.class);
	
	
	public final static int ACTIVAS = 0;
	public final static int ANULADAS = 1;
	
	private static MultaDao INSTANCE = null;
	private static final String SQL_GETALL = "{call multa_getAll(?)}";

	//private static final String SQL_GETALLBYIDAGENTE_FECHA_BAJA = "{call multa_getAllFechaBaja(?)}";

//	private final static String SQL_GETALLBYIDAGENTE = "SELECT m.id AS id_multa, importe, concepto, fecha_alta,id_agente,id_coche, c.matricula, c.modelo, c.km"
//			+ " FROM multa AS m INNER JOIN coche AS c ON m.id_coche= c.id WHERE id_agente=? AND fecha_baja IS NULL ORDER BY fecha_alta DESC";

	private static final String SQL_INSERT = "{call multa_insert(?, ?, ?, ?, ?)}";

	private final static String SQL_UPDATE_FECHA_BAJA = "{call multa_update(?)}";

	// constructor privado, solo acceso por getInstance()
	private MultaDao() {
		super();
	}

	public synchronized static MultaDao getInstance() {

		if (INSTANCE == null) {
			INSTANCE = new MultaDao();
		}
		return INSTANCE;
	}

	/**
	 * 
	 * @param opcion usar constantes para multas activas y anuladas
	 * @see ACTIVAS
	 * @see ANULADAS
	 * @return
	 */
	public ArrayList<Multa> getAll( int opcion) {

		ArrayList<Multa> multas = new ArrayList<Multa>();
		String sql = SQL_GETALL;
		try (Connection conn = ConnectionManager.getConnection(); CallableStatement cs = conn.prepareCall(sql);) {
			
			cs.setInt(1, opcion);
			try (ResultSet rs = cs.executeQuery()) {
				while (rs.next()) {
					multas.add(rowMapper(rs));
					LOG.info("Id valido");
				}
			}
		} catch (Exception e) {
			LOG.debug(e);
		}
		return multas;
	}

//	public ArrayList<Multa> getAllByIdAgente(Long idAgente) throws SQLException {
//		ArrayList<Multa> multas = new ArrayList<>();
//		String sql = SQL_GETALLBYIDAGENTE;
//		try (Connection conn = ConnectionManager.getConnection(); PreparedStatement pst = conn.prepareStatement(sql);) {
//			pst.setLong(1, idAgente);
//			try (ResultSet rs = pst.executeQuery()) {
//				while (rs.next()) {
//					multas.add(rowMapper(rs));
//				}
//			}
//		} catch (Exception e) {
//			LOG.debug(e);
//		}
//
//		return multas;
//	}
	
	/**
	 * 
	 * @param opcion usar constantes para multas activas y anuladas
	 * @see ACTIVAS
	 * @see ANULADAS
	 * @return
	 */
	public ArrayList<Multa> getAllByIdAgenteDarBaja(int opcion) throws SQLException {
		ArrayList<Multa> multasAgente = new ArrayList<>();
		String sql = SQL_GETALL;
		try (Connection conn = ConnectionManager.getConnection(); CallableStatement cs = conn.prepareCall(sql);) {
			cs.setInt(1, opcion);
			try (ResultSet rs = cs.executeQuery()) {
				while (rs.next()) {
					multasAgente.add(rowMapperBaja(rs));
					LOG.info("Id valido");
				}
			}
		} catch (Exception e) {
			LOG.debug(e);
		}

		return multasAgente;
	}

	public boolean insert(Multa m, Long id_agente) throws SQLException {

		boolean resul = false;

		try (Connection conn = ConnectionManager.getConnection();
				CallableStatement cs = conn.prepareCall(SQL_INSERT);) {

			cs.setInt(1, m.getImporte());
			cs.setString(2, m.getConcepto());
			cs.setLong(3, m.getCoche().getId());
			cs.setLong(4, id_agente);

			cs.registerOutParameter(5, Types.INTEGER);

			int affectedRows = cs.executeUpdate();
			if (affectedRows == 1) {
				resul = true;
			}

		}
		return resul;

	}

	public boolean delete(Long id) throws SQLException {
		boolean result = false;
		String sql = SQL_UPDATE_FECHA_BAJA;
		try (Connection conn = ConnectionManager.getConnection(); CallableStatement cs = conn.prepareCall(sql);) {
			cs.setLong(1, id);

			int affectedRows = cs.executeUpdate();
			if (affectedRows == 1) {
				result = true;
			}
		}

		return result;
	}

	private Multa rowMapper(ResultSet rs) throws SQLException {
		Multa m = new Multa();
		m.setId(rs.getLong("id"));
		m.setImporte(rs.getInt("importe_de_multa"));
		m.setConcepto(rs.getString("concepto"));
		m.setFecha(rs.getDate("fecha"));
		m.setCoche(new Coche(rs.getLong("id_coche"), rs.getString("matricula_coche"), rs.getString("modelo_coche"),
				rs.getInt("kilometros")));
		return m;
	}

	private Multa rowMapperBaja(ResultSet rs) throws SQLException {
		Multa m = new Multa();
		m.setId(rs.getLong("id"));
		m.setConcepto(rs.getString("concepto"));
		m.setImporte(rs.getInt("importe_de_multa"));
		m.setFecha(rs.getDate("fecha"));
		m.setFecha_baja(rs.getDate("fecha_baja"));
		m.setCoche(new Coche(rs.getLong("id_coche"), rs.getString("matricula_coche"), rs.getString("modelo_coche"),
				rs.getInt("kilometros")));
		return m;
	}
}
