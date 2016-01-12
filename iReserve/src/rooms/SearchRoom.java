package rooms;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import rooms.ResultSRQuerry;
import helper.SQLHelper;

/**
 * Servlet implementation class SearchRoom
 */
@WebServlet("/rooms/SearchRoom")
public class SearchRoom extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchRoom() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<String> sites = new ArrayList<String>();
		ArrayList<String> capacity = new ArrayList<String>();
		HashMap<String, Object[]> batiments = new HashMap<String, Object[]>();
		SQLHelper sqlhelp = SQLHelper.getInstance();
		String querry = "SELECT * FROM Sites;";
		ResultSet a = sqlhelp.doQuerry(querry);
		try {
			while(a.next())
			{
				String site = a.getString("name");
				ArrayList<String> bats = new ArrayList<String>();
				querry = "SELECT Batiments.nom FROM Sites, Batiments WHERE (Sites.name=\"" + site + "\" and Sites.id_site=Batiments.id_site);";
				ResultSet b = sqlhelp.doQuerry(querry);
				while(b.next())
				{
					bats.add(b.getString("nom"));
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
		
		querry = "SELECT Rooms.capacity FROM Rooms GROUP BY Rooms.capacity HAVING COUNT(Rooms.capacity)>=0;";
		a = sqlhelp.doQuerry(querry);
		
		try {
			while(a.next())
			{
				String c = a.getString("capacity");
				capacity.add(c);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("Batiments", batiments); //batiment init
		request.setAttribute("GBatiments", gmap); //batiment pour changer
		request.setAttribute("Sites", sites.toArray());
		request.setAttribute("Capacities", capacity.toArray());
		this.getServletContext().getRequestDispatcher( "/users/SearchRoom.jsp" ).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String date = request.getParameter("date");
		String batiment = request.getParameter("batiment");
		String site = request.getParameter("site");
		String capa = request.getParameter("capa");
		String rbSecu = request.getParameter("particularite-secu");
		String rbVisio = request.getParameter("particularite-visio");
		String rbDigi = request.getParameter("particularite-digilab");
		String rbTab = request.getParameter("particularite-tableau");
		String particularities = "";
		int havingCount=0;
		if(rbSecu != null)
		{
			particularities += "Particularities.name_part=\"Salle securisee\"";
			havingCount++;
		}
		if(rbVisio!= null)
		{
			if(havingCount==0)
			{
				particularities += "Particularities.name_part=\"Videoprojecteur\"";
			}
			else
			{
				particularities += " or Particularities.name_part=\"Videoprojecteur\"";
			}
			havingCount++;
		}
		if(rbDigi!= null)
		{
			if(havingCount==0)
			{
				particularities += "Particularities.name_part=\"DigiLAB\"";
			}
			else
			{
				particularities += " or Particularities.name_part=\"DigiLAB\"";
			}
			havingCount++;
		}
		if(rbTab!= null)
		{
			if(havingCount==0)
			{
				particularities += "Particularities.name_part=\"Tableau\"";
			}
			else
			{
				particularities += " or Particularities.name_part=\"Tableau\"";
			}
			havingCount++;
		}
		
		SQLHelper sqlhelp = SQLHelper.getInstance();
		String querry = "";
		if(havingCount==0)
		{
			querry = "SELECT Rooms.num_room,Reservations.start, Reservations.end FROM Sites, Rooms, Reservations, Batiments where (Batiments.nom=\"" + batiment + "\" and Sites.name=\"" +site + "\" and Reservations.date=\"" + date + "\" and Sites.id_site=Batiments.id_site and Rooms.capacity>=" + capa + " and Rooms.id_batiment=Batiments.id_batiment and Rooms.id_room=Reservations.id_room and Rooms.id_room IN (SELECT Rooms.id_room FROM Rooms, Particularities, Own where (Own.id_room = Rooms.id_room and Own.id_part=Particularities.id_part) GROUP BY Rooms.id_room HAVING COUNT(Rooms.id_room)>= " + havingCount + "));";
		}
		else
		{
			querry = "SELECT Rooms.num_room,Reservations.start, Reservations.end FROM Sites, Rooms, Reservations, Batiments where (Batiments.nom=\"" + batiment + "\" and Sites.name=\"" +site + "\" and Reservations.date=\"" + date + "\" and Sites.id_site=Batiments.id_site and Rooms.capacity>=" + capa + " and Rooms.id_batiment=Batiments.id_batiment and Rooms.id_room=Reservations.id_room and Rooms.id_room IN (SELECT Rooms.id_room FROM Rooms, Particularities, Own where ((" + particularities + ")  and Own.id_room = Rooms.id_room and Own.id_part=Particularities.id_part) GROUP BY Rooms.id_room HAVING COUNT(Rooms.id_room)>= " + havingCount + "));";
		}
		ResultSet a = sqlhelp.doQuerry(querry);
		ArrayList<ResultSRQuerry> result = new ArrayList<ResultSRQuerry>();
		
		try {
			while(a.next())
			{
				ResultSRQuerry element = new ResultSRQuerry(a.getString("num_room"), a.getString("start"), a.getString("end"));
				result.add(element);
				System.out.println(element);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		doGet(request, response);
	}

}
