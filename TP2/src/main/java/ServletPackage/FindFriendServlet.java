package ServletPackage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BeanPackage.UserBean;
import SQLPackage.SQLConnector;

/**
 * Servlet implementation class FindFriendServlet
 */
@WebServlet("/FindFriendServlet")
public class FindFriendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindFriendServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		
		UserBean current_user = (UserBean) session.getAttribute("current_user");
		
		
		String nom = request.getParameter("nomami");
		String prenom = request.getParameter("prenomami");
		//ArrayList<Integer> results = new ArrayList<>();
		HashMap<Integer, ArrayList<Object>> resultmap = new HashMap<Integer, ArrayList<Object>>();
		
		SQLConnector sc = new SQLConnector();	
		
	
		
		if((nom != "") || (prenom != null)) {
			
			resultmap = sc.getUsers(nom, prenom);
			
		}
		
		request.getSession().setAttribute("utilisateurs", resultmap);
		
		if(current_user.getRang().trim().equals("basic_user")) {
			response.sendRedirect("JSP_pages/userfriendsresult.jsp");
		}else {
			
			if(current_user.getRang().trim().equals("admin")) {
				response.sendRedirect("JSP_pages/adminfriendsresult.jsp");
			}
		}
		//this.getServletContext().getRequestDispatcher("/JSP_pages/adminfriendsresult.jsp").forward(request,response);
		
	}

}
