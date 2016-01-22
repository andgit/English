package com.english;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.english.Database.UsersWorker;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet(description = "Registration servlet", urlPatterns = { "/RegistrationServlet" })
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		//System.out.println("Hello from doGet method.");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);//chyba to nie jest potrzebne-upewnic sie i usunac!!!
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String sex = request.getParameter("sex");//not used for now
		
		if(email == null || GlobalFunctions.isEmailAddressValid(email) == false) {
			
			request.setAttribute("action", "Wrong email address.");
			getServletContext().getRequestDispatcher("/Index.jsp?subpage=3").forward(request, response);
			
		} else {
			
			UsersWorker.Insert(email, password);
			
			request.setAttribute("action", "success");
			getServletContext().getRequestDispatcher("/Index.jsp?subpage=3").forward(request, response);
		}
	}

}
