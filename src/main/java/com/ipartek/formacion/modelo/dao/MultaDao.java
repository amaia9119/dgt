package com.ipartek.formacion.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ipartek.formacion.modelo.pojo.Coche;
import com.ipartek.formacion.modelo.pojo.Multa;



public class MultaDao {
	
	private static MultaDao INSTANCE = null;
	private static final String SQL_GETALL  = "select\r\n"
			+ "multa.id as 'id'" + 
			"coche.modelo as 'modelo_del_coche',\r\n" + 
			"multa.importe as 'importe_de_multa',\r\n" + 
			"multa.concepto as 'concepto',\r\n" + 
			"multa.fecha as 'fecha'\r\n" + 
			"from multa\r\n" + 
			"inner join agente on multa.id_agente=agente.id\r\n" + 
			"inner join coche on multa.id_coche=coche.id ";
	
	private static final String SQL_INSERT = "INSERT INTO multa (importe, concepto,id_coche,id_agente) VALUES (?,?,?,?);";
	
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
				} catch (Exception e) {
					System.out.println("multa no valido");
					e.printStackTrace();
				}
			} // while

		} catch (Exception e) {
			e.printStackTrace();
		}
		return multas;
	}
	
	
	public boolean insert(Multa m) throws SQLException {

		boolean resul = false;
	
		try (Connection conn = ConnectionManager.getConnection(); 
				PreparedStatement pst = conn.prepareStatement(SQL_INSERT);) {

			pst.setInt(1, m.getImporte() );
			pst.setString(2, m.getConcepto());
			pst.setObject(3, m.getCoche().getId());
			int affectedRows = pst.executeUpdate();
			if (affectedRows == 1) {
				resul = true;
			}

		}
		return resul;

	}
	
	private Multa rowMapper(ResultSet rs) throws SQLException {
		Multa m = new Multa();
		m.setId( rs.getLong("id"));
		m.setImporte(rs.getInt("importe_de_multa"));
		m.setConcepto(rs.getString("concepto"));
		m.setCoche(new Coche(rs.getLong("id"), rs.getString("matricula"), rs.getString("modelo_del_coche"), rs.getInt("km")));
	
		return m;
	}
}
