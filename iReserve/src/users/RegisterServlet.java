package users;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helper.HTMLHelper;
import helper.SQLHelper;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/users/RegisterServlet")
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String form1 = "<html><head></head><body>" + "<form action=\"RegisterServlet\" method=\"post\"> "
				+ "Username: <input type=\"text\" size=\"5\" name=\"username\"/>"
				+ "Password: <input type=\"text\" size=\"5\" name=\"password\"/>"
				+ "isAdmin: <input type=\"CHECKBOX\" size=\"5\" name=\"isAdmin\"/>";

		String[] sites = SQLHelper.getInstance().getAllSites(false);
		String option = HTMLHelper.makeOption(sites, "location");

		String form2 = "&nbsp;&nbsp;" + "<input type=\"submit\" value=\"Add User\" />" + "</form>"
				+ "</body></html>";
		response.getWriter().append(form1 + option + form2);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String location = request.getParameter("location");
		String siteID = SQLHelper.getInstance().getSiteID(location);
		
		String isAdmin;
		String active = request.getParameter("isAdmin");
		if("on".equals(active)){
			isAdmin = "true";
		} else{
			isAdmin = "false";
		}
		
		String querry = "INSERT INTO Persons (username, password, isAdmin, location) VALUES (\"" + username + "\", \"" + password + "\", " + isAdmin + ", " + siteID + ");";
		SQLHelper.getInstance().execute(querry);
		response.getWriter().append("User added");
	}

}
