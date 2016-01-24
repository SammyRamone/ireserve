package rooms;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helper.HTMLHelper;
import helper.SQLHelper;

/**
 * Servlet implementation class ListRoomsServlet
 */
@WebServlet("/rooms/ListRoomsServlet")
public class ListRoomsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListRoomsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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
	
	public static String getRoomTable(){
		//String querry = "SELECT Sites.name, Rooms.num_room, Rooms.capacity, Batiments.nom FROM Rooms, Sites, Batiments WHERE Rooms.id_batiment=Batiments.id_batiment AND Batiments.id_site=Sites.id_site;";
		ResultSet resultat = SQLHelper.getInstance().getAllRooms();
		String[] names = new String[4];
		names[0] = "Number";
		names[1] = "Capacity";
		names[2] = "Batiment";
		names[3] = "Site";
		try {
			int rows = 0;
			while(resultat.next()){
				rows++;
			}
			resultat.beforeFirst();
			System.out.print("rows: " + rows );
			String[][] data = new String[rows][4];
			for (int i = 0; resultat.next(); i++) {				
				String[] row = new String[4];
				row[0] = resultat.getString("num_room");
				row[1] = resultat.getString("capacity");
				row[2]  = SQLHelper.getInstance().getBatimentName(resultat.getString("id_batiment")); 
				row[3] = SQLHelper.getInstance().getSitebyBatiment(resultat.getString("id_batiment")); 
				data[i] = row;
			}
			return HTMLHelper.makeTable(names, data, rows);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
}
