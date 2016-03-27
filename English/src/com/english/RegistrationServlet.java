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
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String country = request.getParameter("country");
		String city = request.getParameter("city");
		int englishLevel = Integer.parseInt(request.getParameter("englishLevel"));
		String skype = request.getParameter("skype");
		boolean sex = Boolean.parseBoolean(request.getParameter("sex"));//check if it`s correct!?
		
		if(email == null || GlobalFunctions.isEmailAddressValid(email) == false) {
			
			request.setAttribute("RegistrationMessageAttribute", "Wrong email address.");
			getServletContext().getRequestDispatcher("/Index.jsp?subpage=3").forward(request, response);
			
		} else {
			
			UsersWorker.AddUser(name, surname, country, city, englishLevel, skype, sex, email, password);
			
			request.setAttribute("RegistrationMessageAttribute", "successfulRegistration");
			getServletContext().getRequestDispatcher("/Index.jsp?subpage=3").forward(request, response);
		}
	}

}
