package sites;

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
 * Servlet implementation class ManageSitesServlet
 */
@WebServlet("/sites/ManageSitesServlet")
public class ManageSitesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageSitesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("<html><head>" + HTMLHelper.CSS + "</head><body>");
		String querry = "SELECT * FROM Sites";
		ResultSet resultat = SQLHelper.getInstance().doQuerry(querry);

		String[] names = new String[2];
		names[0] = "ID";
		names[1] = "Name";

		int rows = 0;

		try {
			while (resultat.next()) {
				rows++;
			}
			resultat.beforeFirst();
			String[][] data = new String[rows][2];
			for (int i = 0; resultat.next(); i++) {
				String[] row = new String[5];
				row[0] = resultat.getString("id_site");
				row[1] = resultat.getString("name");
				data[i] = row;
			}
			response.getWriter().append(HTMLHelper.makeTable(names, data, rows));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String form = "<form action=\"RemoveSiteServlet\" method=\"post\"> "
				+ "Site IDs (seperated by comma): <input type=\"text\" size=\"5\" name=\"ids\"/>"
				+ "&nbsp;&nbsp;" + "<input type=\"submit\" value=\"Remove Site\" />" + "</form>";
		String form2 = "<form action=\"ChangeSiteServlet\" method=\"post\"> "
				+ "Site ID: <input type=\"text\" size=\"5\" name=\"id\"/>"
				+ "&nbsp;&nbsp;" + "<input type=\"submit\" value=\"Change Site\" />" + "</form>";
		String form3 = "<form action=\"AddSiteServlet\" method=\"post\"> "
				+ "Name: <input type=\"text\" size=\"5\" name=\"name\"/>"
				+ "&nbsp;&nbsp;" + "<input type=\"submit\" value=\"Add Site\" />" + "</form>";
		response.getWriter().append(form);
		response.getWriter().append(form2);
		response.getWriter().append(form3);
		response.getWriter().append(HTMLHelper.BACKBUTTON + "</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
