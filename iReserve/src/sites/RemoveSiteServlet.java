package sites;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helper.HTMLHelper;
import helper.SQLHelper;
import helper.String;

/**
 * Servlet implementation class RemoveSiteServlet
 */
@WebServlet("/sites/RemoveSiteServlet")
public class RemoveSiteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveSiteServlet() {
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
		String ids = request.getParameter("ids");
		String[] idsArr = ids.split(",");
		for (int i = 0; i < idsArr.length; i++) {
			System.out.print(idsArr[i]);
			SQLHelper.getInstance().removeSiteQuerry(idsArr[i].trim());
		}
		response.getWriter().append("<html><head>" + HTMLHelper.CSS + "</head><body> Site delted" + HTMLHelper.BACKBUTTON + "</body></html>");
	}

}
