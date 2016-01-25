package users;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helper.HTMLHelper;
import helper.SQLHelper;

/**
 * Servlet implementation class ChangeUserDataServlet
 */
@WebServlet("/users/ChangeUserDataServlet")
public class ChangeUserDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeUserDataServlet() {
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
		String id = request.getParameter("id");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String location = request.getParameter("location");
		
		
		String isAdmin;
		String active = request.getParameter("isAdmin");
		if("on".equals(active)){
			isAdmin = "true";
		} else{
			isAdmin = "false";
		}
		
		SQLHelper.getInstance().changeUserDataQuerry( location,username,password,isAdmin,id);
		response.getWriter().append("<html><head>" + HTMLHelper.CSS + "</head><body> User changed" + HTMLHelper.BACKBUTTON + "</body></html>");
	}

}
