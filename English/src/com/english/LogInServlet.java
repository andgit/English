package com.english;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.english.Database.DBHandler;


/**
 * Servlet implementation class LogInServlet
 */
@WebServlet("/LogInServlet")
public class LogInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogInServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void setWrongSession(HttpSession session) {
		
		session.setAttribute("logged", "ErrorEmailPass");
		session.setAttribute("account_type", "ErrorEmailPass");
		session.setAttribute("email", "ErrorEmailPass");
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//METODA JESZCZE NIE SKONCZONA
		
		response.setContentType("text/html");
		String email = request.getParameter("account_email");
		String pass = request.getParameter("account_password");
		
		HttpSession session = request.getSession();
		session.setAttribute("logged", GlobalData.sessionOffLine);
		session.setAttribute("account_type", GlobalData.sessionOffLine);
		session.setAttribute("email", GlobalData.sessionOffLine);
		
		if(/*GlobalFunctions.isEmailAddressValid(email) &&*/ !pass.contains(" ")) {//trzeba bedzie odkomentowac isEmailValid()-narazie dla szybkich testow zakomentowalem

			try {

				Connection conn = DBHandler.connect();
				Statement stmt = conn.createStatement();
				ResultSet result = stmt.executeQuery("SELECT * FROM english.t_account WHERE account_email='"+email+"'");
				
				if(result.next()) {
					
					email = result.getString("account_email");
					String account_type = result.getString("account_type");
					session.setAttribute("logged", GlobalData.sessionOffLine);
					session.setAttribute("account_type", account_type);
					session.setAttribute("email", email);
				} else {
					
					setWrongSession(session);
				}
				DBHandler.close(conn);
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		} else {
			
			setWrongSession(session);
		}
		
		response.sendRedirect("Index.jsp");
	}
}
