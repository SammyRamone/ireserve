package Rooms;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

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
		ArrayList<String> sites = new ArrayList<String>();
		ArrayList<String> persons = new ArrayList<String>();
		HashMap<String, Object[]> batiments = new HashMap<String, Object[]>();
		SQLHelper sqlhelp = SQLHelper.getInstance();
		String querry = "SELECT * FROM Sites;";
		ResultSet allsites = sqlhelp.getAllSiteQuerry();
		try {
			while(allsites.next())
			{
				String site = allsites.getString("name");
				ArrayList<String> bats = new ArrayList<String>();
				querry = "SELECT Batiments.nom FROM Sites, Batiments WHERE (Sites.name=\"" + site + "\" and Sites.id_site=Batiments.id_site);";
				ResultSet batFromSite = sqlhelp.getAllBatimentFromSite(site);
				while(batFromSite.next())
				{
					bats.add(batFromSite.getString("nom"));
				}
				sites.add(site);
				batiments.put(site, bats.toArray());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Gson gson = new Gson();
		String gmap = gson.toJson(batiments);
		ResultSet a = sqlhelp.getAllPersonneByUsernameQuerry();
		
		try {
			while(a.next())
			{
				String p = a.getString("username");
				persons.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("Batiments", batiments); //batiment init
		request.setAttribute("GBatiments", gmap); //batiment pour changer
		request.setAttribute("Sites", sites.toArray());
		request.setAttribute("Persons", persons.toArray());
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
		String querry = "SELECT Rooms.id_room FROM Rooms, Batiments, Sites WHERE (Sites.name = \"" + site + "\" and Sites.id_site=Batiments.id_site and Batiments.nom= \"" + bat + "\" and Rooms.id_batiment=Batiments.id_batiment and Rooms.num_room=" + room + ");";
		ResultSet a = sqlhelp.getRoomsQuerry(site, bat, room);
		try {
			while(a.next())
			{
				id_room = a.getString("id_room");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		a = sqlhelp.getPersonneQuerry( site,personne);
		try {
			while(a.next())
			{
				id_person = a.getString("id_person");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sqlhelp.BookReservationQuerry(id_room, id_person, object, date, start, end);
		doGet(request, response);
	}

}
