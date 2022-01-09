package ServletPackage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BeanPackage.UserBean;
import SQLPackage.SQLConnector;

/**
 * Servlet implementation class AcceptFriendServlet
 */
@WebServlet("/acceptfriendservlet")
public class AcceptFriendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AcceptFriendServlet() {
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
		
		ArrayList<Object> listeobject = new ArrayList<>();
		
		String[] iduseracceptedtab = request.getParameterValues("iduseraccepted[]");
		String[] nomuseracceptedtab = request.getParameterValues("nomuseraccepted[]");
		String[] prenomuseracceptedtab = request.getParameterValues("prenomuseraccepted[]");
		
		String iduseraccepted = String.valueOf(iduseracceptedtab[0]);
		String nomuseraccepted = String.valueOf(nomuseracceptedtab[0]);
		String prenomuseraccepted = String.valueOf(prenomuseracceptedtab[0]);
		
		listeobject.add(nomuseraccepted);
		listeobject.add(prenomuseraccepted);
		
		
		current_user.getAmis().put(Integer.valueOf(iduseraccepted), listeobject);
		
		int delete = Integer.valueOf(iduseraccepted);
		sc.acceptFriend(String.valueOf(current_user.getId()),String.valueOf(delete));
		sc.deleteNotif(String.valueOf(current_user.getId()),String.valueOf(delete));
		
		if(current_user.getRang().trim().equals("basic_user")) {
			response.sendRedirect("JSP_pages/userfriends.jsp");
		}else {
			
			if(current_user.getRang().trim().equals("admin")) {
				response.sendRedirect("JSP_pages/adminfriends.jsp");
			}
		}
	}

}
