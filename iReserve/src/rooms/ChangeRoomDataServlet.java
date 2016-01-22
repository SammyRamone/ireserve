package rooms;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helper.HTMLHelper;
import helper.SQLHelper;
 
/**
 * Servlet implementation class ChangeRoomDataServlet
 */
@WebServlet("/rooms/ChangeRoomDataServlet")
public class ChangeRoomDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeRoomDataServlet() {
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
		String number = request.getParameter("number");
		String capacity = request.getParameter("capacity");
		String site = request.getParameter("sites");
		String siteID = SQLHelper.getInstance().getSiteID(site);
		
		String update= "UPDATE Rooms SET id_site=" + siteID + ", num_room=" + number + ", capacity=" + capacity + " WHERE id_room=" + id + ";";
		SQLHelper.getInstance().execute(update);
		response.getWriter().append("<html><head>" + HTMLHelper.CSS + "</head><body> Room removed" + HTMLHelper.BACKBUTTON + "</body></html>");
	}

}
