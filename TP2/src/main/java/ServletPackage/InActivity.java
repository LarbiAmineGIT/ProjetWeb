package ServletPackage;

import java.io.IOException;
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
 * Servlet implementation class InActivity
 */
@WebServlet("/inactivity")
public class InActivity extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InActivity() {
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
		
		int int_senderuserid = current_user.getId();
		String namesender = current_user.getNom();

		String[] inactivitytab = request.getParameterValues("inactivity[]");
		String[] acthourdebtab = request.getParameterValues("acthourdeb[]");
		String[] acthourendtab = request.getParameterValues("acthourend[]");
		
		String inactivity = String.valueOf(inactivitytab[0]);
		String acthourdeb = String.valueOf(acthourdebtab[0]);
		String acthourfin = String.valueOf(acthourendtab[0]);
		
		LocalTime heuredebconv = LocalTime.parse(acthourdeb);
		LocalTime heurefinconv = LocalTime.parse(acthourfin);
		
		
		System.out.println("J'insère id :"+ current_user.getId());
		System.out.println("J'insère heuredeb"+ heuredebconv);
		System.out.println("J'insère heurefin"+ heurefinconv);
		System.out.println("J'insère activiti" + inactivity);
		
		sc.addparticipation(String.valueOf(current_user.getId()), heuredebconv, heurefinconv, inactivity);
		
		if(current_user.getRang().trim().equals("basic_user")) {
			response.sendRedirect("JSP_pages/useractivities.jsp");
		}else {
			
			if(current_user.getRang().trim().equals("admin")) {
				response.sendRedirect("JSP_pages/adminactivities.jsp");
			}
		}
		
	}

}
