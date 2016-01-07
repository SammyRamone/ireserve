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
 * Servlet implementation class ManageUserServlet
 */
@WebServlet("/users/ManageUserServlet")
public class ManageUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ManageUserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("<html><head></head><body>");
		String querry = "SELECT * FROM Persons";
		ResultSet resultat = SQLHelper.getInstance().doQuerry(querry);

		String[] names = new String[5];
		names[0] = "ID";
		names[1] = "Username";
		names[2] = "Password";
		names[3] = "IsAdmin";
		names[4] = "Location";

		int rows = 0;

		try {
			while (resultat.next()) {
				rows++;
			}
			resultat.beforeFirst();
			String[][] data = new String[rows][5];
			for (int i = 0; resultat.next(); i++) {
				String[] row = new String[5];
				row[0] = resultat.getString("id_person");
				row[1] = resultat.getString("username");
				row[2] = resultat.getString("password");
				row[3] = resultat.getString("isAdmin");
				row[4] = resultat.getString("location");
				data[i] = row;
			}
			response.getWriter().append(HTMLHelper.makeTable(names, data, rows));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String form = "<form action=\"RemoveUserServlet\" method=\"post\"> "
				+ "User IDs (seperated by comma): <input type=\"text\" size=\"5\" name=\"ids\"/>"
				+ "&nbsp;&nbsp;" + "<input type=\"submit\" value=\"Remove User\" />" + "</form>";
		String form2 = "<form action=\"ChangeUserServlet\" method=\"post\"> "
				+ "User ID: <input type=\"text\" size=\"5\" name=\"id\"/>"
				+ "&nbsp;&nbsp;" + "<input type=\"submit\" value=\"Change User\" />" + "</form>";
		response.getWriter().append(form);
		response.getWriter().append(form2);
		response.getWriter().append("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
