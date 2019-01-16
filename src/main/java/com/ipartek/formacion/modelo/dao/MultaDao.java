package com.ipartek.formacion.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.ipartek.formacion.modelo.pojo.Coche;
import com.ipartek.formacion.modelo.pojo.Multa;



public class MultaDao {
	private final static Logger LOG = Logger.getLogger(MultaDao.class);
	private static MultaDao INSTANCE = null;
	private static final String SQL_GETALL  = "SELECT "
			+ "multa.id AS 'id',"
			+ "coche.id AS 'id_coche',"
			+ "coche.modelo AS 'modelo_coche',"
			+ "coche.matricula AS 'matricula_coche',"
			+ "coche.km AS 'kilometros',"
			+ "multa.importe AS 'importe_de_multa',"
			+ "multa.concepto AS 'concepto',"
			+ "multa.fecha AS 'fecha',"
			+ "multa.fecha_baja AS 'fecha_baja'"
			+ "FROM multa INNER JOIN agente ON multa.id_agente=agente.id "
						+ "INNER JOIN coche ON multa.id_coche=coche.id	"
						+ "WHERE fecha_baja IS NULL;";
	
	private static final String SQL_GETALLBYIDAGENTE_FECHA_BAJA  = "SELECT "
			+ "multa.id AS 'id',"
			+ "coche.id AS 'id_coche',"
			+ "coche.modelo AS 'modelo_coche',"
			+ "coche.matricula AS 'matricula_coche',"
			+ "coche.km AS 'kilometros',"
			+ "multa.importe AS 'importe_de_multa',"
			+ "multa.concepto AS 'concepto',"
			+ "multa.fecha AS 'fecha',"
			+ "multa.fecha_baja AS 'fecha_baja'"
			+ "FROM multa INNER JOIN agente ON multa.id_agente=agente.id "
						+ "INNER JOIN coche ON multa.id_coche=coche.id	"
						+ "WHERE id_agente=? " 
						+ "AND fecha_baja IS NOT NULL;";
	
	private final static String SQL_GETALLBYIDAGENTE = "SELECT m.id AS id_multa, importe, concepto, fecha_alta,id_agente,id_coche, c.matricula, c.modelo, c.km"
			+ " FROM multa AS m INNER JOIN coche AS c ON m.id_coche= c.id WHERE id_agente=? AND fecha_baja IS NULL ORDER BY fecha_alta DESC";
	
	private static final String SQL_INSERT = "INSERT INTO multa (importe, concepto,id_coche,id_agente) VALUES (?,?,?,?);";
//	private final static String SQL_GETALLBYIDAGENTE_FECHA_BAJA="SELECT multa.id AS id_multa, multa.importe AS importe_multa, multa.concepto AS concepto_multa,multa.fecha_alta AS fecha_alta_multa,multa.fecha_baja AS fecha_baja_multa,multa.id_agente AS id_agente_multa,multa.id_coche AS id_coche_multa, c.matricula AS matricula_coche, c.modelo AS modelo_coche, c.km AS kilometros"
//			+ " FROM multa AS m INNER JOIN coche AS c ON m.id_coche= c.id WHERE id_agente=? AND m.fecha_baja IS NOT NULL "
//			+ "ORDER BY fecha_alta DESC ";
	private final static String SQL_UPDATE_FECHA_BAJA="UPDATE multa SET fecha_baja=CURRENT_TIMESTAMP() WHERE id =?";
	
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
	
	public ArrayList<Multa> getAll() {

		ArrayList<Multa> multas = new ArrayList<Multa>();
		
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pst = conn.prepareStatement(SQL_GETALL);
				ResultSet rs = pst.executeQuery()) {

			while (rs.next()) {
				try {					
					multas.add(rowMapper(rs));
					LOG.info("Id de la multa" + multas.toString());
				} catch (Exception e) {
					LOG.error("Multa no valida");
				}
			}

		} catch (Exception e) {
			LOG.debug(e);
		}
		return multas;
	}
	

	public ArrayList<Multa> getAllByIdAgente(Long idAgente) throws SQLException {
		ArrayList<Multa> multas = new ArrayList<>();
		String sql = SQL_GETALLBYIDAGENTE;
		try (Connection conn = ConnectionManager.getConnection(); PreparedStatement pst = conn.prepareStatement(sql);) {
			pst.setLong(1, idAgente);
			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()) {
					multas.add(rowMapper(rs));
				}
			}
		} catch (Exception e) {
			LOG.debug(e);
		}

		return multas;
}
	
	public ArrayList<Multa> getAllByIdAgenteDarBaja(Long idAgente) throws SQLException{
		ArrayList<Multa> multasAgente= new ArrayList<>();
		String sql = SQL_GETALLBYIDAGENTE_FECHA_BAJA;
		try(
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pst = conn.prepareStatement(sql);	
			){
			pst.setLong(1, idAgente);
			try(
					ResultSet rs = pst.executeQuery()
					){
					while(rs.next()) {
						multasAgente.add(rowMapperBaja(rs));
						LOG.info("Id valido");
					}
				}
		}catch (Exception e) {
			LOG.debug(e);
		}
		
		return multasAgente;
}
	
	
	public boolean insert(Multa m, Long id_agente) throws SQLException {

		boolean resul = false;
	
		try (Connection conn = ConnectionManager.getConnection(); 
				PreparedStatement pst = conn.prepareStatement(SQL_INSERT);) {

			pst.setInt(1, m.getImporte() );
			pst.setString(2, m.getConcepto());
			pst.setLong(3, m.getCoche().getId());
			pst.setLong(4, id_agente);
			int affectedRows = pst.executeUpdate();
			if (affectedRows == 1) {
				resul = true;
			}

		}
		return resul;

	}
	
	public boolean delete(Long id) throws SQLException{
		boolean result = false;
		String sql =SQL_UPDATE_FECHA_BAJA;
		try(Connection conn = ConnectionManager.getConnection();
			PreparedStatement pst = conn.prepareStatement(sql);
			){
			pst.setLong(1, id);
			
			int affectedRows = pst.executeUpdate();
			if(affectedRows== 1){
				result =true;
			}	
		}
		
		
		
		return result;
}
	
	private Multa rowMapper(ResultSet rs) throws SQLException {
		Multa m = new Multa();
		m.setId( rs.getLong("id"));
		m.setImporte(rs.getInt("importe_de_multa"));
		m.setConcepto(rs.getString("concepto"));
		m.setFecha(rs.getDate("fecha"));
		m.setCoche(new Coche(rs.getLong("id_coche"), rs.getString("matricula_coche"), rs.getString("modelo_coche"), rs.getInt("kilometros")));
		return m;
	}
	
	private Multa rowMapperBaja(ResultSet rs) throws SQLException {
		Multa m = new Multa();
		m.setId(rs.getLong("id"));
		m.setConcepto(rs.getString("concepto"));
		m.setImporte(rs.getInt("importe_de_multa"));
		m.setFecha(rs.getDate("fecha"));
		m.setFecha_baja(rs.getDate("fecha_baja"));
		m.setCoche(new Coche(rs.getLong("id_coche"), rs.getString("matricula_coche"), rs.getString("modelo_coche"), rs.getInt("kilometros")));
		return m;
}
}
