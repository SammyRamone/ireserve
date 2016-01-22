package reservations;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helper.HTMLHelper;
import helper.SQLHelper;

/**
 * Servlet implementation class ChangeReservationServlet
 */
@WebServlet("/reservations/ChangeReservationServlet")
public class ChangeReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeReservationServlet() {
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
		String querry = "SELECT * FROM Reservations WHERE id_reservation=" + id + ";";
		ResultSet resultat = SQLHelper.getInstance().doQuerry(querry);
		try {
			resultat.next();
			String idRoom = resultat.getString("id_room");
			String room = SQLHelper.getInstance().getRoomNumber(idRoom);
			String idUser = resultat.getString("id_person");
			String user = SQLHelper.getInstance().getUsername(idUser);
			String object = resultat.getString("object");
			Date date = resultat.getDate("date");
			Time start = resultat.getTime("start");
			Time end = resultat.getTime("end");

			String form1 = "<html><head>" + HTMLHelper.CSS + "</head><body>" + "<form action=\"ChangeReservationDataServlet\" method=\"post\"> "
					+ "Reservation ID: <input type=\"text\" size=\"5\" name=\"id\" value=\"" + id + "\"/>"
					+ "Room: <input type=\"text\" size=\"5\" name=\"id_room\" value=\"" + room + "\"/>"
					+ "User: <input type=\"text\" size=\"5\" name=\"id_person\" value=\"" + user + "\"/>"
					+ "Object: <input type=\"text\" size=\"5\" name=\"object\" value=\"" + object + "\"/>"
					+ "Date: <input type=\"date\" name=\"date\" value=\"" + date + "\">"
					+ "Start: <input type=\"time\" name=\"start\" value=\"" + start + "\">"
					+ "End: <input type=\"time\" name=\"end\" value=\"" + end + "\">";
			
			String form2 = "&nbsp;&nbsp;" + "<input type=\"submit\" value=\"Change Reservation\" />" + "</form>"
					+ HTMLHelper.BACKBUTTON + "</body></html>";

			String re = form1 +  form2;
			response.getWriter().append(re);

		} catch (SQLException e) {
			response.getWriter().append("Number not found");
		}
	}

}
