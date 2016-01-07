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
 * Servlet implementation class ChangeSiteServlet
 */
@WebServlet("/sites/ChangeSiteServlet")
public class ChangeSiteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeSiteServlet() {
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
		System.out.println(id);
		String querry = "SELECT * FROM Sites WHERE id_site=" + id + ";";
		ResultSet resultat = SQLHelper.getInstance().doQuerry(querry);
		try {
			resultat.next();
			String name = resultat.getString("name");

			String form1 = "<html><head></head><body>" + "<form action=\"ChangeSiteDataServlet\" method=\"post\"> "
					+ "Site ID: <input type=\"text\" size=\"5\" name=\"id\" value=\"" + id + "\"/>"
					+ "Name: <input type=\"text\" size=\"5\" name=\"name\" value=\"" + name + "\"/>";			
			String form2 = "&nbsp;&nbsp;" + "<input type=\"submit\" value=\"Change Site\" />" + "</form>"
					+ "</body></html>";

			String re = form1 + form2;
			response.getWriter().append(re);
		} catch (SQLException e) {
			e.printStackTrace();
			response.getWriter().append("Number not found");
		}
	}

}
