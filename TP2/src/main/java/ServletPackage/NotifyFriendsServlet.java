package ServletPackage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BeanPackage.UserBean;
import SQLPackage.SQLConnector;

/**
 * Servlet implementation class NotifyFriendsServlet
 */
@WebServlet("/notifyfriendsservlet")
public class NotifyFriendsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NotifyFriendsServlet() {
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
		HttpSession session = request.getSession();
		SQLConnector sc = new SQLConnector();
		UserBean current_user = (UserBean) session.getAttribute("current_user");
		
		sc.notifyFriends(String.valueOf(current_user.getId()));
		
		if(current_user.getRang().trim().equals("basic_user")) {
			response.sendRedirect("JSP_pages/logged.jsp");
		}else {
			
			if(current_user.getRang().trim().equals("admin")) {
				response.sendRedirect("JSP_pages/admin.jsp");
			}
		}
		
	}

}
