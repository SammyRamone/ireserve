package helper;

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

	public static SQLHelper getInstance() {
		if (_instance == null) {
			_instance = new SQLHelper();
		}
		return _instance;
	}
    

	private SQLHelper() {
		/* Connexion à la base de données */
		_url = "jdbc:mysql://localhost:3306/bdd_sopra";
		_utilisateur = "root";
		_motDePasse = "mamaya";
		_connexion = null;
	}

	public ResultSet doQuerry(String querry) {
		Statement statement = null;
		ResultSet resultat = null;
		String passFromDB = null;

		try {
			_connexion = DriverManager.getConnection(_url, _utilisateur, _motDePasse);
			statement = _connexion.createStatement();
			resultat = statement.executeQuery(querry);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultat;
	}

	public boolean execute(String command) {
		Statement statement = null;
		Boolean resultat = null;
		String passFromDB = null;
		System.out.println(command);

		try {
			_connexion = DriverManager.getConnection(_url, _utilisateur, _motDePasse);
			statement = _connexion.createStatement();
			resultat = statement.execute(command);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultat;
	}

	public String[] getAllSites(boolean addAllToList) {
		String querry = "SELECT * FROM Sites";
		ResultSet resultat = SQLHelper.getInstance().doQuerry(querry);

		int entries = 0;
		String[] sites = null;
		try {
			while (resultat.next()) {
				entries++;
			}
			resultat.beforeFirst();

			int i;
			if (addAllToList) {
				sites = new String[entries + 1];
				sites[0] = "All";
				i = 1;
			} else {
				sites = new String[entries];
				i = 0;
			}
			for (; resultat.next(); i++) {
				sites[i] = resultat.getString("Name");
			}

			return sites;
		} catch (SQLException e)

		{
			return null;
		}
	}

	private String firstResult(String querry, String column) {
		try {
			ResultSet resultat = SQLHelper.getInstance().doQuerry(querry);
			resultat.next();
			return resultat.getString(column);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public String getSiteID(String siteName) {
		String querry = "SELECT * FROM Sites WHERE name=\"" + siteName + "\";";
		return firstResult(querry, "id_site");
	}
	
	public String getSiteName(String id_site) {
		String querry = "SELECT * FROM Sites WHERE id_site=\"" + id_site + "\";";
		return firstResult(querry, "name");
	}

	public String getRoomNumber(String roomID) {
		String querry = "SELECT * FROM Rooms WHERE id_room=\"" + roomID + "\";";
		return firstResult(querry, "num_room");
	}

	public String getRoomID(String roomNumber) {
		String querry = "SELECT * FROM Rooms WHERE num_room=\"" + roomNumber + "\";";
		return firstResult(querry, "id_room");
	}

	public String getUsername(String personID) {
		String querry = "SELECT * FROM Persons WHERE id_person=\"" + personID + "\";";
		return firstResult(querry, "username");
	}
	
	public String getUserID(String username) {
		String querry = "SELECT * FROM Persons WHERE username=\"" + username + "\";";
		return firstResult(querry, "id_person");
	}

}
