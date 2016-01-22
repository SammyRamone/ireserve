package rooms;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helper.HTMLHelper;
import helper.SQLHelper;

/**
 * Servlet implementation class RemoveRoomServlet
 */
@WebServlet("/rooms/RemoveRoomServlet")
public class RemoveRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RemoveRoomServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			ResultSet resultat;
			String querry;
			// drop down
			String batiment = request.getParameter("batiment");
			response.getWriter().append("<html><head>" + HTMLHelper.CSS + "</head><body><form action=\"RemoveRoomServlet\" method=\"get\">");
			String[] batiments = SQLHelper.getInstance().getAllBatiments(true);
			response.getWriter().append(HTMLHelper.makeOption(batiments, "batiment", batiment));
			response.getWriter().append("<input type=\"submit\" value=\"Filter\"/></form>");
			
			// table
			
			if (batiment != null && !batiment.equals("All")) {
				String id = SQLHelper.getInstance().getBatimentID(batiment);
				querry = "SELECT * FROM Rooms WHERE id_batiment=\"" + id + "\";";
			} else {
				querry = "SELECT * FROM Rooms;";
			}
			resultat = SQLHelper.getInstance().doQuerry(querry);
			String[] names = new String[3];
			names[0] = "Number";
			names[1] = "Capacity";
			names[2] = "Batiment";

			int rows = 0;
			while (resultat.next()) {
				rows++;
			}
			resultat.beforeFirst();
			String[][] data = new String[rows][3];
			for (int i = 0; resultat.next(); i++) {
				String[] row = new String[3];
				row[0] = resultat.getString("num_room");
				row[1] = resultat.getString("capacity");
				row[2] = SQLHelper.getInstance().getBatimentName(resultat.getString("id_batiment"));
				data[i] = row;
			}
			if(batiment== null){
				batiment = "All";
			}
			response.getWriter().append(HTMLHelper.makeTable(names, data, rows));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// fields

		String form = "<form action=\"RemoveRoomServlet\" method=\"post\"> "
				+ "Room Numbers (seperated by comma): <input type=\"text\" size=\"5\" name=\"numbers\"/>"
				+ "&nbsp;&nbsp;" + "<input type=\"submit\" value=\"Remove Rooms\" />" + "</form>";
		String form2 = "<form action=\"ChangeRoomServlet\" method=\"post\"> "
				+ "Room Number: <input type=\"text\" size=\"5\" name=\"number\"/>"
				+ "&nbsp;&nbsp;" + "<input type=\"submit\" value=\"Change Room\" />" + "</form>";
		response.getWriter().append(form);
		response.getWriter().append(form2);
		response.getWriter().append(HTMLHelper.BACKBUTTON + "</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String numbers = request.getParameter("numbers");
		String[] numbersArr = numbers.split(",");
		for (int i = 0; i < numbersArr.length; i++) {
			System.out.print(numbersArr[i]);
			String command = "DELETE FROM Rooms WHERE num_room=" + numbersArr[i].trim() + ";";
			SQLHelper.getInstance().execute(command);
		}
		response.getWriter().append("<html><head>" + HTMLHelper.CSS + "</head><body> Rooms delted" + HTMLHelper.BACKBUTTON + "</body></html>");
		

	}
}
