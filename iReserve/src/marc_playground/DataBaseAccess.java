package marc_playground;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DataBaseAccess {
	public static DataBaseAccess instance; 
	
	
	private DataBaseAccess(){
		
	}
	public static DataBaseAccess getInstance(){
		if(instance==null)
			instance = new DataBaseAccess(); 
		return instance;
	}
		//Methodes
		
		
		
		public Pair<String, Boolean> loginQuery(String login){
			String query = "SELECT * FROM Persons WHERE username='" + login + "';";
			Pair<String, Boolean> result = new Pair<String , Boolean>("",false);
			String passFromDB ="";
			ResultSet resultat = SQLHelper.getInstance().doQuerry(query);
			try {
				resultat.next();
				result.modifyFirst( resultat.getString("password"));
				result.modifySecond(  resultat.getBoolean("isAdmin"));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;
		}
	
	
		
	}

