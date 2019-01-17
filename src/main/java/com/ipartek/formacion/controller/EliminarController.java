package com.ipartek.formacion.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ipartek.formacion.modelo.dao.MultaDao;
import com.ipartek.formacion.modelo.pojo.Alerta;

/**
 * Servlet implementation class EliminarController
 */
@WebServlet("/eliminar")
public class EliminarController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger LOG = Logger.getLogger(EliminarController.class);
	private MultaDao daoMulta;
	
	//private Coche c;
	
	@Override
    public void init(ServletConfig config) throws ServletException {    
    	super.init(config);
    	daoMulta = MultaDao.getInstance();   	
    }
	
	@Override
	public void destroy() {
		super.destroy();
		daoMulta = null;
	}
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long idM = Long.parseLong(request.getParameter("id"));
		try {
			daoMulta.delete(idM);
			request.setAttribute("multas", daoMulta.getAll(MultaDao.ACTIVAS));
			request.getRequestDispatcher("multas.jsp").forward(request, response);
		} catch (SQLException e) {
			LOG.error(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
