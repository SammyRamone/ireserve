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
 * Servlet implementation class RemoveBatimentServlet
 */
@WebServlet("/batiments/RemoveBatimentServlet")
public class RemoveBatimentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveBatimentServlet() {
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
				String command = "DELETE FROM Batiments WHERE id_batiment=" + idsArr[i].trim() + ";";
				SQLHelper.getInstance().execute(command);
			}
			response.getWriter().append("<html><head>" + HTMLHelper.CSS + "</head><body> Batiment delted" + HTMLHelper.BACKBUTTON + "</body></html>");
	}

}
