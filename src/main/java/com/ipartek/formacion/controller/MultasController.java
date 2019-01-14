package com.ipartek.formacion.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.modelo.dao.CocheDao;
import com.ipartek.formacion.modelo.dao.MultaDao;
import com.ipartek.formacion.modelo.pojo.Alerta;
import com.ipartek.formacion.modelo.pojo.Coche;
import com.ipartek.formacion.modelo.pojo.Multa;


/**
 * Servlet implementation class ListadoMultasController
 */
@WebServlet("/multas")
public class MultasController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MultaDao daoMulta;
	private CocheDao daoCoche;
	private String id;
	
	@Override
    public void init(ServletConfig config) throws ServletException {    
    	super.init(config);
    	daoMulta = MultaDao.getInstance();
    	daoCoche = CocheDao.getInstance();    	
    }
	
	@Override
	public void destroy() {
		super.destroy();
		daoMulta = null;
		daoCoche = null;
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setAttribute("multas", daoMulta.getAll());
		
		request.getRequestDispatcher("multas.jsp").forward(request, response);
	}

	/** 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id_coche = request.getParameter("coche_id");
		String importe = request.getParameter("importe");
		String concepto = request.getParameter("concepto");
		
		Multa m = new Multa();
		Coche c = new Coche();	
		
		int identificador = Integer.parseInt(id);
		c.setId(identificador);
		m.setImporte(Integer.parseInt(importe));
		m.setConcepto(concepto);
		
	}

}