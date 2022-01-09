package ServletPackage;

import java.io.IOException;
import java.sql.Date;
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
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
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
		
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String passwordconfirmation = request.getParameter("passwordconfirmation");
		String datestr = request.getParameter("date");
		LocalDate date = LocalDate.parse(datestr);

		HttpSession session = request.getSession();
		SQLConnector sc = new SQLConnector();
		
		
		if((login != "") && (login != null) && (password != "") && (password != null) && (passwordconfirmation != null)) {
			
			int i = sc.createUserPossible(login);
			System.out.println("Valeur de i :"+i);
			System.out.println("\n");
			
			if(i == 1) {
				//Cas ou c'est possible d'ajouter une nouvelle adresse mail
				System.out.println("Cas ou on ajoute une adresse mail :"+ login );
				sc.createUser(login, password, nom, prenom,date);
				response.sendRedirect("JSP_pages/accountaccepted.jsp");
			}
			
			if(i == 0){
				//Cas ou ce n'est pas possible
				System.out.println("Cas ou on n'ajoute pas d'adresse mail");
				//request.getRequestDispatcher("JSP_pages/emailnotavailable.jsp").forward(request, response);
				response.sendRedirect("JSP_pages/emailnotavailable.jsp");
			}
			
			
		
		}else{
			
			session.setAttribute("msg-err"," login ou mot de passe mal formé !");
			
			session.setAttribute("current_user",null);
			request.setAttribute("current_user",null);
			
		}	
	}
}
