package ServletPackage;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BeanPackage.UserBean;
import SQLPackage.SQLConnector;

/**
 * Servlet implementation class ModifyParameters
 */
@WebServlet("/modifyparameters")
public class ModifyParameters extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyParameters() {
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
		System.out.println("JE SUIS ICI");
		HttpSession session = request.getSession();
		
		UserBean current_user = (UserBean) session.getAttribute("current_user");
		SQLConnector sc = new SQLConnector();	
		
		String nom = request.getParameter("nom1");
		if(!(nom == "" )){
			sc.modifyParameter("nom",nom , String.valueOf(current_user.getId()));
			current_user.setNom(nom);
		}
		
		String prenom = request.getParameter("prenom1");
		if(!(prenom == "" )){
			sc.modifyParameter("prenom",prenom , String.valueOf(current_user.getId()));
			current_user.setPrenom(prenom);
		}
		
		String login = request.getParameter("login1");
		if(!(login == "" )){
			sc.modifyParameter("login1",login , String.valueOf(current_user.getId()));
			current_user.setLogin(login);
		}
		
		
		String password = request.getParameter("password1");
		if(!(password == "" )){
			sc.modifyParameter("password",password , String.valueOf(current_user.getId()));
		}
		
		String datestr = request.getParameter("date1");
		LocalDate date =  LocalDate.parse(datestr);
		
		if(!(datestr == "" )){
			sc.modifyDate(date, String.valueOf(current_user.getId()));
			current_user.setDate(date);
		}
		
		if(current_user.getRang().trim().equals("basic_user")) {
			response.sendRedirect("JSP_pages/userprofile.jsp");
		}else {
			
			if(current_user.getRang().trim().equals("admin")) {
				response.sendRedirect("JSP_pages/adminprofile.jsp");
			}
		}
		
		
		
		
	}

}
