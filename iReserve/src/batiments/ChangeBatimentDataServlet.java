package batiments;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helper.SQLHelper;

/**
 * Servlet implementation class ChangeBatimentDataServlet
 */
@WebServlet("/batiments/ChangeBatimentDataServlet")
public class ChangeBatimentDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeBatimentDataServlet() {
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
		String name = request.getParameter("name");
		String site = request.getParameter("site");
		String siteID = SQLHelper.getInstance().getSiteID(site);		
		
		String update= "UPDATE Batiments SET nom=\"" + name + "\", id_site=" + siteID + " WHERE id_batiment=" + id + ";";
		SQLHelper.getInstance().execute(update);
		response.getWriter().append("Batiment Changed");
	}

}
