package rooms;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import marc_playground.*;
import helper.HTMLHelper;
import helper.SQLHelper;
import marc_playground.DataBaseAccess;


/**
 * Servlet implementation class AddRoomServlet
 */
@WebServlet("/rooms/AddRoomServlet")
public class AddRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddRoomServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
 
		String number= request.getParameter("number");
		String size= request.getParameter("size");
		String batiment = request.getParameter("batiment");
		System.out.println(batiment);

		SQLHelper.getInstance().addRoomQuerry( batiment, number, size);

		response.getWriter().append("<html><head>" + HTMLHelper.CSS + "</head><body> Room Added" + HTMLHelper.BACKBUTTON + "</body></html>");
 
	}

}
