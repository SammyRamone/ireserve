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
import Rooms.ResultSRQuerry;
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
		ResultSet a = sqlhelp.getAllSiteQuerry();
		try {
			while(a.next())
			{
				String site = a.getString("name");
				ArrayList<String> bats = new ArrayList<String>();
				ResultSet b = sqlhelp.getAllBatimentFromSite(site);
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
		
		a = sqlhelp.getRoomByCapacity();
		
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
		ResultSet a;
		if(havingCount==0)
		{
			a = sqlhelp.getAvailableRoomWithoutParticularitiesQuerry(capa,batiment,date,site,havingCount);
		}
		else
		{
			a = sqlhelp.getAvailableRoomWithParticularitiesQuerry(capa,batiment,particularities,date ,site, havingCount);
		}
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
		this.getServletContext().getRequestDispatcher( "/iFrame/bookRoom.html" ).forward( request, response );
	}

}
