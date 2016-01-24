package batiments;

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
 * Servlet implementation class ManageBatimentsServlet
 */
@WebServlet("/batiments/ManageBatimentsServlet")
public class ManageBatimentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ManageBatimentsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("<html><head>" + HTMLHelper.CSS +"</head><body>");
		ResultSet resultat = SQLHelper.getInstance().getAllBatimentQuerry();

		String[] names = new String[3];
		names[0] = "ID";
		names[1] = "Name";
		names[2] = "Site";

		int rows = 0;

		try {
			while (resultat.next()) {
				rows++;
			}
			resultat.beforeFirst();
			String[][] data = new String[rows][3];
			for (int i = 0; resultat.next(); i++) {
				String[] row = new String[5];
				row[0] = resultat.getString("id_batiment");
				row[1] = resultat.getString("nom");
				String site = resultat.getString("id_site");
				row[2] = SQLHelper.getInstance().getSiteName(site);
				data[i] = row;
			}
			response.getWriter().append(HTMLHelper.makeTable(names, data, rows));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String form = "<form action=\"RemoveBatimentServlet\" method=\"post\"> "
				+ "Batiment IDs (seperated by comma): <input type=\"text\" size=\"5\" name=\"ids\"/>" + "&nbsp;&nbsp;"
				+ "<input type=\"submit\" value=\"Remove Batiment\" />" + "</form>";
		String form2 = "<form action=\"ChangeBatimentServlet\" method=\"post\"> "
				+ "Batiment ID: <input type=\"text\" size=\"5\" name=\"id\"/>" + "&nbsp;&nbsp;"
				+ "<input type=\"submit\" value=\"Change Batiment\" />" + "</form>";
		String form3 = "<form action=\"AddBatimentServlet\" method=\"post\"> "
				+ "Name: <input type=\"text\" size=\"5\" name=\"name\"/>";
		String [] sites = SQLHelper.getInstance().getAllSites(false);
		String option = HTMLHelper.makeOption(sites, "site");
		String form4 = "&nbsp;&nbsp;" + "<input type=\"submit\" value=\"Add Batiment\" />" + "</form>";
		response.getWriter().append(form + form2 + form3 + option + form4);
		response.getWriter().append(HTMLHelper.BACKBUTTON + "</body></html>");
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
