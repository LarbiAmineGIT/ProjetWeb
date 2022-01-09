package ServletPackage;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.buf.StringUtils;

import BeanPackage.UserBean;
import SQLPackage.SQLConnector;

/**
 * Servlet implementation class AddFriendServlet
 */
@WebServlet("/addfriendservlet")
public class AddFriendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddFriendServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("bean");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		HttpSession session = request.getSession();
		
		UserBean current_user = (UserBean) session.getAttribute("current_user");
		
		int int_senderuserid = current_user.getId();
		String namesender = current_user.getNom();

		String[] idreceive = request.getParameterValues("idreceive[]");
		

		
		for(int i = 0; i < idreceive.length; i++) {
			System.out.println(idreceive[i]);
		}
		
		
		String idsender = String.valueOf(int_senderuserid);
		System.out.println("User sender ID "+ idsender);
		
		String idreceiver = String.valueOf(idreceive[0]);
		System.out.println("User receiver ID "+ idreceiver);
		
		if(idreceiver != null && !idreceiver.isEmpty() && idsender != null && !idsender.isEmpty()) {
			
		
			SQLConnector sc = new SQLConnector();
			
			sc.addUser(idsender, idreceiver);
			
			
			if(current_user.getRang().trim().equals("basic_user")) {
				response.sendRedirect("JSP_pages/userfriends.jsp");
			}else {
				
				if(current_user.getRang().trim().equals("admin")) {
					response.sendRedirect("JSP_pages/adminfriends.jsp");
				}
			}
			
		}	
	}
}
