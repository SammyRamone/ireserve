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

	public String getSiteID(String siteName) {
		try {
			String idQuerry = "SELECT * FROM Sites WHERE name=\"" + siteName + "\";";
			ResultSet resultat = SQLHelper.getInstance().doQuerry(idQuerry);
			resultat.next();
			return resultat.getString("id_site");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
