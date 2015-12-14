package marc_playground;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SQLHelper {

	private static SQLHelper _instance;
	private String _url;
	private String _utilisateur;
	private String _motDePasse;
	private Connection _connexion;
	
	public static SQLHelper getInstance(){
		if(_instance == null){
			_instance = new SQLHelper();
		}
		return _instance;
	}
	
	private SQLHelper(){
		 /* Connexion à la base de données */
	    _url = "jdbc:mysql://localhost:3306/bdd_sopra";
	    _utilisateur = "root";
	    _motDePasse = "root";
	    _connexion = null;	    
	}
	
	public ResultSet doQuerry(String querry){
		Statement statement = null;
	    ResultSet resultat = null;
	    String passFromDB = null;
	    
	    try {
			_connexion = DriverManager.getConnection( _url, _utilisateur, _motDePasse );
			statement = _connexion.createStatement();
			resultat = statement.executeQuery( querry);			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return resultat;
	    
		
	}
	
	
}
