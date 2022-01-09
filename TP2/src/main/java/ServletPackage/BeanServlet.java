package ServletPackage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BeanPackage.UserBean;

/**
 * Servlet implementation class BeanServlet
 */
@WebServlet("/bean")
public class BeanServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BeanServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");	
		
		HttpSession session = request.getSession();
		
		UserBean current_user = (UserBean) session.getAttribute("current_user");
		
		if(current_user == null) {
			
			request.getRequestDispatcher( "/JSP_pages/bean.jsp" ).forward( request, response );
			System.out.println("DANS LE PREMIER CAS");
		}
		else{
			if(current_user.getRang().trim().equals("basic_user")) {
				response.sendRedirect("JSP_pages/logged.jsp");
				//request.getRequestDispatcher( "/JSP_pages/logged.jsp").forward( request, response);
			}
			else {
				if(current_user.getRang().trim().equals("admin")) {
					response.sendRedirect("JSP_pages/admin.jsp");
					//request.getRequestDispatcher( "/JSP_pages/admin.jsp" ).forward( request, response );
				}
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
