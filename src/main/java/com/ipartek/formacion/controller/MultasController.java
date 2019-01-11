package com.ipartek.formacion.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.modelo.dao.MultaDao;


/**
 * Servlet implementation class ListadoMultasController
 */
@WebServlet("/multas")
public class MultasController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MultaDao dao;
	
	@Override
    public void init(ServletConfig config) throws ServletException {    
    	super.init(config);
    	dao = MultaDao.getInstance();    	
    }
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setAttribute("multas", dao.getAll());
		
		request.getRequestDispatcher("multas.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("multar.jsp").forward(request, response);
	}

}