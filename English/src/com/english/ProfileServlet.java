package com.english;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.english.Database.UsersWorker;

/**
 * Servlet implementation class ProfileServlet
 */
@WebServlet("/ProfileServlet")
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfileServlet() {
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		//to nie jest jeszcze skonczone-nie dziala jeszcze poprawnie
	    for(int i=0;i<2;++i) {//narazie sa 2 wiersze (uzytkownikow) w bazie danych wiec dalem 2
	    	
	    	String parameter = request.getParameter("submitButton"+i);//jak tutaj zmienie na +1 to user_id jest ten z drguiego wiersza
	    	
	        if(parameter != null) {
	        	
	    		String user_id = parameter;
	    		request.setAttribute("user_id", user_id);
	    		getServletContext().getRequestDispatcher("/Index.jsp?subpage=6").forward(request, response);
	        }
	        break;
	    }
		/*String user_id = request.getParameter("user_id");
		request.setAttribute("user_id", user_id);
		getServletContext().getRequestDispatcher("/Index.jsp?subpage=6").forward(request, response);*/
	}

}
