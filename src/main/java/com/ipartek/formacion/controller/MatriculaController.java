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


import com.ipartek.formacion.modelo.dao.CocheDao;
import com.ipartek.formacion.modelo.pojo.Alerta;
import com.ipartek.formacion.modelo.pojo.Coche;

/**
 * Servlet implementation class MultarController
 */
@WebServlet("/matricula")
public class MatriculaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger LOG = Logger.getLogger(MatriculaController.class);
	private CocheDao dao;
       
@Override
public void init(ServletConfig config) throws ServletException {
	super.init(config);
	dao = CocheDao.getInstance();
}

@Override
public void destroy() {
	super.destroy();
	dao = null;
}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("matricula.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String matricula = request.getParameter("matriculaenv");
		
		Coche c = null;
		
		if (!matricula.equals("")) {
			try {
				c=dao.getByMatricula(matricula);
				LOG.info("Matricula encontrada" + matricula);
			} catch (SQLException e) {
				LOG.error("Matricula no encontrada" + matricula);
			}
			
			if (c != null) {
				request.setAttribute("coche", c);
				request.getRequestDispatcher("multar.jsp").forward(request, response);
			} else {
				request.setAttribute("mensaje", new Alerta(Alerta.TIPO_DANGER, "matrícula no válida"));
				request.getRequestDispatcher("matricula.jsp").forward(request, response);
			}
		}else {
			request.setAttribute("mensaje", new Alerta(Alerta.TIPO_DANGER, "matrícula no válida"));
			request.getRequestDispatcher("matricula.jsp").forward(request, response);
		}
		
	}

}
