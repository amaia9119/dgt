package com.ipartek.formacion.controller;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.log4j.Logger;

import com.ipartek.formacion.modelo.dao.AgenteDao;
import com.ipartek.formacion.modelo.pojo.Agente;
import com.ipartek.formacion.modelo.pojo.Alerta;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private final static Logger LOG = Logger.getLogger(LoginController.class);
	
	private AgenteDao dao;
	private ValidatorFactory factory;
	private Validator validator;
	
	public static final String VIEW_LOGIN = "login.jsp";
	//public static final String CONTROLLER_VIDEOS = "privado/videos";
	
       
    @Override
    public void init(ServletConfig config) throws ServletException {    
    	super.init(config);
    	dao = AgenteDao.getInstance();
    	//Crear Factoria y Validador
    	factory  = Validation.buildDefaultValidatorFactory();
    	validator  = factory.getValidator();
    }

	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		request.getRequestDispatcher(VIEW_LOGIN).forward(request, response);
	}
    
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nombre = request.getParameter("nombre");
		String pass = request.getParameter("pass");
		boolean redirect = false;
		
		try {
			
			HttpSession session = request.getSession();
			
			
			// validar
			Agente agente = new Agente();
			agente.setNombre(nombre);
			agente.setPass(pass);
			
			Set<ConstraintViolation<Agente>> violations = validator.validate(agente);
			
			
			if ( violations.size() > 0) {			// validacion NO PASA
				
				 String errores = "<ul>"; 
				 for (ConstraintViolation<Agente> violation : violations) {					 	
					 errores += String.format("<li> %s : %s </li>" , violation.getPropertyPath(), violation.getMessage() );					
				 }
				 errores += "</ul>";				 
				 request.setAttribute("mensaje", errores);				
				
			} else {                                // validacion OK
			
				agente = dao.login(nombre, pass);
				
				if ( agente == null ) {
					//request.setAttribute("mensaje", new Alerta(Alerta.TIPO_DANGER, "credenciales no válidas"));
				}else {
					session.setMaxInactiveInterval(60*5*5);
					
					session.setAttribute("agente", agente);
					request.getRequestDispatcher("privado/index.jsp").forward(request, response);
				}
			}	
				
			
		}catch (Exception e) {			
			LOG.error(e);
		}finally {
			
			if(redirect) {				
				response.sendRedirect( request.getContextPath() + "/privado/index.jsp");
			}else {
				request.setAttribute("mensaje", new Alerta(Alerta.TIPO_DANGER, "credenciales no válidas"));
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		}
	}

}
