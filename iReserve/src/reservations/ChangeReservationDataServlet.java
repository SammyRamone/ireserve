package reservations;

import java.io.IOException;
import java.sql.Time;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helper.SQLHelper;

/**
 * Servlet implementation class ChangeReservationDataServlet
 */
@WebServlet("/reservations/ChangeReservationDataServlet")
public class ChangeReservationDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeReservationDataServlet() {
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
		String idReservation = request.getParameter("id");
		String roomNumber = request.getParameter("id_room");
		String idRoom = SQLHelper.getInstance().getRoomID(roomNumber);
		String user = request.getParameter("id_person");
		String idUser = SQLHelper.getInstance().getUserID(user);
		String object = request.getParameter("object");
		String date = request.getParameter("date");
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		
		String update= "UPDATE Reservations SET id_room=\"" + idRoom + "\", id_person=\"" + idUser + "\", object=\"" + object + "\", date='" + date + "', start='" + start + "', end='" + end + "' WHERE id_reservation=" + idReservation + ";";
		SQLHelper.getInstance().execute(update);
		response.getWriter().append("User Changed");
	}

}
