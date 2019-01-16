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

import com.ipartek.formacion.modelo.dao.CocheDao;
import com.ipartek.formacion.modelo.dao.MultaDao;
import com.ipartek.formacion.modelo.pojo.Agente;
import com.ipartek.formacion.modelo.pojo.Alerta;
import com.ipartek.formacion.modelo.pojo.Coche;
import com.ipartek.formacion.modelo.pojo.Multa;


/**
 * Servlet implementation class ListadoMultasController
 */
@WebServlet("/multas")
public class MultasController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger LOG = Logger.getLogger(CocheDao.class);
	private MultaDao daoMulta;
	private CocheDao daoCoche;
	//private String id;
	private Coche c;
	
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
		
			request.setAttribute("multas", daoMulta.getAll( MultaDao.ACTIVAS));
			request.getRequestDispatcher("multas.jsp").forward(request, response);
		}

	/** 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Long id_coche = Long.parseLong(request.getParameter("coche_id"));
		
		// recoger par
		Long id_coche = Long.parseLong(request.getParameter("coche_id"));		
		String importe = request.getParameter("importe");
		String concepto = request.getParameter("concepto");
		
		//agente session
		HttpSession session = request.getSession();
		Agente ag = (Agente)session.getAttribute("agente");
		try {
			c = daoCoche.getById(id_coche);
			Multa m = new Multa();
		
			
			m.setImporte(Integer.parseInt(importe));
			m.setConcepto(concepto);
			m.setCoche(c);
			if (m != null) {
				daoMulta.insert(m, ag.getId());
				//mandar mensaje de que ha creado nice
				request.setAttribute("mensaje", new Alerta(Alerta.TIPO_SUCCESS, "se ha registrado correctamente"));
				response.sendRedirect("multas");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}