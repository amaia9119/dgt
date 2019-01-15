package com.ipartek.formacion.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;


import com.ipartek.formacion.modelo.dao.MultaDao;
import com.ipartek.formacion.modelo.pojo.Agente;

/**
 * Servlet implementation class ListarBajaController
 */
@WebServlet("/listar")
public class ListarBajaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger LOG = Logger.getLogger(ListarBajaController.class);
	private MultaDao daoMulta;
	
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
		HttpSession session = request.getSession();
		Agente ag = (Agente) session.getAttribute("agente");
		try {
			request.setAttribute("multbaja", daoMulta.getAllByIdAgenteDarBaja(ag.getId()));
			request.getRequestDispatcher("multbaja.jsp").forward(request, response);
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
