package reservations;

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
 * Servlet implementation class ReservationManagmentServlet
 */
@WebServlet("/reservations/ReservationManagmentServlet")
public class ReservationManagmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReservationManagmentServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("<html><head>" + HTMLHelper.CSS + "</head><body>");
		String querry = "SELECT * FROM Reservations";
		ResultSet resultat = SQLHelper.getInstance().getAllReservationQuerry();

		String[] names = new String[7];
		names[0] = "ID Rervation";
		names[1] = "Room";
		names[2] = "Person";
		names[3] = "Object";
		names[4] = "Date";
		names[5] = "Start";
		names[6] = "End";

		int rows = 0;

		try {
			while (resultat.next()) {
				rows++;
			}
			resultat.beforeFirst();
			String[][] data = new String[rows][7];
			for (int i = 0; resultat.next(); i++) {
				String[] row = new String[7];
				row[0] = resultat.getString("id_reservation");
				String idRoom = resultat.getString("id_room");
				row[1] = SQLHelper.getInstance().getRoomNumber(idRoom);
				String idPerson = resultat.getString("id_person");
				row[2] = SQLHelper.getInstance().getUsername(idPerson);
				row[3] = resultat.getString("object");
				row[4] = resultat.getString("date");
				row[5] = resultat.getString("start");
				row[6] = resultat.getString("end");
				data[i] = row;
			}
			response.getWriter().append(HTMLHelper.makeTable(names, data, rows));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String form = "<form action=\"RemoveReservationServlet\" method=\"post\"> "
				+ "Reservation IDs (seperated by comma): <input type=\"text\" size=\"5\" name=\"ids\"/>"
				+ "&nbsp;&nbsp;" + "<input type=\"submit\" value=\"Remove Reservation\" />" + "</form>";
		String form2 = "<form action=\"ChangeReservationServlet\" method=\"post\"> "
				+ "Reservation ID: <input type=\"text\" size=\"5\" name=\"id\"/>" + "&nbsp;&nbsp;"
				+ "<input type=\"submit\" value=\"Change Reservation\" />" + "</form>";

		String form3 = "<html><head></head><body>" + "<form action=\"AddReservationServlet\" method=\"post\"> ";
		String[] rooms = SQLHelper.getInstance().getAllRooms();
		String option = HTMLHelper.makeOption(rooms, "room");
		String[] persons = SQLHelper.getInstance().getAllPersons();
		String option2 = HTMLHelper.makeOption(persons, "person");
		
		String form4 = "Object: <input type=\"text\" size=\"5\" name=\"object\" />"
				+ "Date: <input type=\"date\" name=\"date\" />" + "Start: <input type=\"time\" name=\"start\"/>"
				+ "End: <input type=\"time\" name=\"end\"/>"
				+ "<input type=\"submit\" value=\"Add Reservation\" />" + "</form>";

		response.getWriter().append(form + form2 + form3 + option + option2 + form4);
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
