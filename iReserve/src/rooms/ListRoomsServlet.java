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

		String querry = "SELECT * FROM Rooms;";
		ResultSet resultat = SQLHelper.getInstance().doQuerry(querry);
		String[] names = new String[3];
		names[0] = "Number";
		names[1] = "Capacity";
		names[2] = "Batiment";
		try {
			int rows = 0;
			while(resultat.next()){
				rows++;
			}
			resultat.beforeFirst();
			System.out.print("rows: " + rows );
			String[][] data = new String[rows][3];
			for (int i = 0; resultat.next(); i++) {				
				String[] row = new String[3];
				row[0] = resultat.getString("num_room");
				row[1] = resultat.getString("capacity");
				row[2]  = SQLHelper.getInstance().getBatimentName(resultat.getString("id_batiment")); 
				data[i] = row;
			}
			response.getWriter().append(HTMLHelper.makeTable(names, data, rows));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
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
