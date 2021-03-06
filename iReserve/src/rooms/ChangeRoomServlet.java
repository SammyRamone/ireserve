package rooms;

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
 * Servlet implementation class ChangeRoomServlet
 */
@WebServlet("/rooms/ChangeRoomServlet")
public class ChangeRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChangeRoomServlet() {
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
		String number = request.getParameter("number");
		ResultSet resultat = SQLHelper.getInstance().allRoomwithNumberQuerry(number);
		try {
			resultat.next();
			String id = resultat.getString("id_room");
			String capacity = resultat.getString("capacity");
			String idBatiment = resultat.getString("id_batiment");

			resultat = SQLHelper.getInstance().allBatByIDQuerry(idBatiment);
			resultat.next();
			String batiment = resultat.getString("name");

			String form1 = "<html><head>" + HTMLHelper.CSS + "</head><body>" + "<form action=\"ChangeRoomDataServlet\" method=\"post\"> "
					+ "Room ID: <input type=\"text\" size=\"5\" name=\"id\" value=\"" + id + "\"/>"
					+ "Room Number: <input type=\"text\" size=\"5\" name=\"number\" value=\"" + number + "\"/>"
					+ "Room Capacity: <input type=\"text\" size=\"5\" name=\"capacity\" value=\"" + capacity + "\"/>";
			String [] batiments = SQLHelper.getInstance().getAllBatiments();
			String option = HTMLHelper.makeOption(batiments, "batiment", batiment);
			String form2 = "&nbsp;&nbsp;" + "<input type=\"submit\" value=\"Change Room\" />" + "</form>"
					+ HTMLHelper.BACKBUTTON + "</body></html>";

			String re = form1 + option +  form2;
			response.getWriter().append(re);

		} catch (SQLException e) {
			response.getWriter().append("Number not found");
		}

	}

}
