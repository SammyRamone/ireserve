package rooms;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helper.SQLHelper;

/**
 * Servlet implementation class BookRoom
 */
@WebServlet("/rooms/BookRoom")
public class BookRoom extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookRoom() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.getServletContext().getRequestDispatcher( "/users/BookReservation.jsp" ).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SQLHelper sqlhelp = SQLHelper.getInstance();
		String date = request.getParameter("date");
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		String bat = request.getParameter("batiment");
		String site = request.getParameter("site");
		String room = request.getParameter("room");
		String personne = request.getParameter("personne");
		String object = request.getParameter("object");
		String id_room="";
		String id_person="";
		System.out.println(date + start + end + bat + site + room + personne + object);
		String querry = "SELECT Rooms.id_room FROM Rooms, Batiments, Sites WHERE (Sites.name = \"" + site + "\" and Sites.id_site=Batiments.id_site and Batiments.nom= \"" + bat + "\" and Rooms.id_batiment=Batiments.id_batiment and Rooms.num_room=" + room + ");";
		ResultSet a = sqlhelp.doQuerry(querry);
		try {
			while(a.next())
			{
				id_room = a.getString("id_room");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		querry = "SELECT Persons.id_person FROM Persons, Sites WHERE (Persons.username=\"" + personne + "\" and Sites.name=\"" + site + "\" and Persons.location=Sites.id_site);";
		a = sqlhelp.doQuerry(querry);
		try {
			while(a.next())
			{
				id_person = a.getString("id_person");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		querry = "INSERT INTO Reservations (id_room, id_person, object, date, start, end) VALUES (" + id_room + ", " + id_person + ", \"" + object + "\", \"" + date + "\", \"" + start + "\", \"" + end + "\");";
		sqlhelp.execute(querry);
		doGet(request, response);
	}

}
