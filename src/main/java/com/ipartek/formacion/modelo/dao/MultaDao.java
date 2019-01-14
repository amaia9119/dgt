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
	private static final String SQL_GETALL  = "select\r\n"
			+ "multa.id as 'id',"
			+ "coche.id as 'id_coche',\r\n"+ 
			"coche.modelo as 'modelo_coche',\r\n"
			+ "coche.matricula as 'matricula_coche',\r\n"
			+ "coche.km as 'kilometros',\r\n" + 
			"multa.importe as 'importe_de_multa',\r\n" + 
			"multa.concepto as 'concepto',\r\n" + 
			"multa.fecha as 'fecha'\r\n" + 
			"from multa\r\n" + 
			"inner join agente on multa.id_agente=agente.id\r\n" + 
			"inner join coche on multa.id_coche=coche.id ";
	
	private static final String SQL_INSERT = "INSERT INTO multa (importe, concepto,id_coche,id_agente) VALUES (?,?,?,?);";
	private final static String SQL_GETALLBYIDAGENTE_FECHA_BAJA="SELECT m.id AS id_multa, importe, concepto, fecha_alta ,id_agente,id_coche, c.matricula, c.modelo, c.km"
			+ " FROM multa AS m INNER JOIN coche AS c ON m.id_coche= c.id WHERE id_agente=? AND m.fecha_baja IS NOT NULL "
			+ "ORDER BY fecha_alta DESC ";
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
					LOG.info("multa valida");
				} catch (Exception e) {
					LOG.error("multa no valida");
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
						multasAgente.add(rowMapper(rs));
						
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
}
