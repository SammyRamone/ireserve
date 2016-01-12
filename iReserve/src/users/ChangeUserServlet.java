package users;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helper.HTMLHelper;
import helper.SQLHelper;

/**
 * Servlet implementation class ChangeUserServlet
 */
@WebServlet("/users/ChangeUserServlet")
public class ChangeUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeUserServlet() {
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
		String querry = "SELECT * FROM Persons WHERE id_person=" + id + ";";
		ResultSet resultat = SQLHelper.getInstance().doQuerry(querry);
		try {
			resultat.next();
			String username = resultat.getString("username");
			String password = resultat.getString("password");
			boolean isAdmin = resultat.getBoolean("isAdmin");
			String location = resultat.getString("location");

			String form1 = "<html><head>" + HTMLHelper.CSS + "</head><body>" + "<form action=\"ChangeUserDataServlet\" method=\"post\"> "
					+ "User ID: <input type=\"text\" size=\"5\" name=\"id\" value=\"" + id + "\"/>"
					+ "Username: <input type=\"text\" size=\"5\" name=\"username\" value=\"" + username + "\"/>"
					+ "Password: <input type=\"text\" size=\"5\" name=\"password\" value=\"" + password + "\"/>";
			String checkbox;
			if(isAdmin){
					checkbox = "isAdmin: <input type=\"CHECKBOX\" size=\"5\" name=\"isAdmin\" checked/>";
			}else{
				checkbox = "isAdmin: <input type=\"CHECKBOX\" size=\"5\" name=\"isAdmin\"/>";
			}
			String [] sites = SQLHelper.getInstance().getAllSites(false);
			
			querry = "SELECT * FROM Sites WHERE id_site=" + location;
			resultat = SQLHelper.getInstance().doQuerry(querry);
			resultat.next();
			String site = resultat.getString("name");
			
			String option = HTMLHelper.makeOption(sites, "location", site);
			String form2 = "&nbsp;&nbsp;" + "<input type=\"submit\" value=\"Change User\" />" + "</form>"
					+ HTMLHelper.BACKBUTTON + "</body></html>";

			String re = form1 + checkbox + option +  form2;
			response.getWriter().append(re);

		} catch (SQLException e) {
			response.getWriter().append("Number not found");
		}
	}

}
