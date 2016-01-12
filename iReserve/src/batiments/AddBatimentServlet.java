package batiments;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helper.HTMLHelper;
import helper.SQLHelper;

/**
 * Servlet implementation class AddBatimentServlet
 */
@WebServlet("/batiments/AddBatimentServlet")
public class AddBatimentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddBatimentServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String site = request.getParameter("site");
		
		String id_site = SQLHelper.getInstance().getSiteID(site);

		String add = "INSERT INTO Batiments (nom, id_site) VALUES (\"" + name + "\", " + id_site + ");";
		SQLHelper.getInstance().execute(add);
		response.getWriter().append("<html><head>" + HTMLHelper.CSS + "</head><body> Batiment Added" + HTMLHelper.BACKBUTTON + "</body></html>");
	}

}
