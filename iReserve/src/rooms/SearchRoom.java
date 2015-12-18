package rooms;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import marc_playground.SQLHelper;

/**
 * Servlet implementation class SearchRoom
 */
@WebServlet("/SearchRoom")
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
		// TODO Auto-generated method stub
		this.getServletContext().getRequestDispatcher( "/WEB-INF/SearchRoom.jsp" ).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String date = request.getParameter("date");
		String etage = request.getParameter("etage");
		String site = request.getParameter("site");
		String capa = request.getParameter("capa");
		String rbSecu = request.getParameter("particularite-secu");
		String rbVisio = request.getParameter("particularite-viso");
		String rbDigi = request.getParameter("particularite-digilab");
		String rbTab = request.getParameter("particularite-tableau");
		SQLHelper sqlhelp = SQLHelper.getInstance();
		
		ResultSet a = sqlhelp.doQuerry("SELECT * FROM Sites");
		
		try {
			a.next();
			System.out.println(a.getString("Name"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		doGet(request, response);
	}

}
