package reservations;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helper.HTMLHelper;
import helper.SQLHelper;

/**
 * Servlet implementation class AddReservationServlet
 */
@WebServlet("/reservations/AddReservationServlet")
public class AddReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddReservationServlet() {
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
		String room = request.getParameter("room");
		String person = request.getParameter("person");
		String object = request.getParameter("object");
		String date = request.getParameter("date");
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		
		String id_room = SQLHelper.getInstance().getRoomID(room);
		String id_person = SQLHelper.getInstance().getUserID(person);

		String add = "INSERT INTO Reservations (id_room, id_person, object, date, start, end) VALUES (" + id_room + "," + id_person + ",\"" + object + "\",\"" + date + "\",\"" + start + "\",\"" + end + "\");";
		SQLHelper.getInstance().execute(add);
		response.getWriter().append("<html><head>" + HTMLHelper.CSS + "</head><body> Reservation Added" + HTMLHelper.BACKBUTTON + "</body></html>");
	}

}
