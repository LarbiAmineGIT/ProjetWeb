package ServletPackage;

import java.io.IOException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BeanPackage.UserBean;
import SQLPackage.SQLConnector;

/**
 * Servlet implementation class ActivityServlet
 */
@WebServlet("/addactivityservlet")
public class AddActivityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddActivityServlet() {
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
		
		UserBean current_user = (UserBean) session.getAttribute("current_user");
		SQLConnector sc = new SQLConnector();
		
		String date = request.getParameter("date");
		String lieu = request.getParameter("lieu");
		String heuredeb = request.getParameter("heuredeb");
		String heurefin = request.getParameter("heurefin");
		
		LocalTime heuredebconv = LocalTime.parse(heuredeb);
		LocalTime heurefinconv = LocalTime.parse(heurefin);
		
		sc.addActivite(LocalDate.parse(date), heuredebconv , heurefinconv, lieu);
		
		if(current_user.getRang().trim().equals("basic_user")) {
			response.sendRedirect("JSP_pages/useractivities.jsp");
		}else {
			
			if(current_user.getRang().trim().equals("admin")) {
				response.sendRedirect("JSP_pages/adminactivities.jsp");
			}
		}
		
		
	}

}
