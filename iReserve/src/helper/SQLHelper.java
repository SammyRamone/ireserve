package helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import batiments.String;

public class SQLHelper implements SQLaccess {

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

	public String[] getAllRooms() {
		String querry = "SELECT * FROM Rooms";
		ResultSet resultat = SQLHelper.getInstance().doQuerry(querry);

		int entries = 0;
		String[] rooms = null;
		try {
			while (resultat.next()) {
				entries++;
			}
			resultat.beforeFirst();

			int i;
			rooms = new String[entries];
			i = 0;

			for (; resultat.next(); i++) {
				rooms[i] = resultat.getString("num_room");
			}
			return rooms;
		} catch (SQLException e) {
			return null;
		}
	}

	public String[] getAllPersons() {
		String querry = "SELECT * FROM Persons";
		ResultSet resultat = SQLHelper.getInstance().doQuerry(querry);

		int entries = 0;
		String[] persons = null;
		try {
			while (resultat.next()) {
				entries++;
			}
			resultat.beforeFirst();

			int i;
			persons = new String[entries];
			i = 0;

			for (; resultat.next(); i++) {
				persons[i] = resultat.getString("username");
			}
			return persons;
		} catch (SQLException e) {
			return null;
		}
	}

	public String[] getAllBatiments(boolean addAllToList) {
		String querry = "SELECT * FROM Batiments";
		ResultSet resultat = SQLHelper.getInstance().doQuerry(querry);

		int entries = 0;
		String[] batiments = null;
		try {
			while (resultat.next()) {
				entries++;
			}
			resultat.beforeFirst();

			int i;
			if (addAllToList) {
				batiments = new String[entries + 1];
				batiments[0] = "All";
				i = 1;
			} else {
				batiments = new String[entries];
				i = 0;
			}

			for (; resultat.next(); i++) {
				batiments[i] = resultat.getString("nom");
			}
			return batiments;
		} catch (SQLException e) {
			return null;
		}
	}
	
	public String[] getAllBatiments(){
		return getAllBatiments(false);
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

	public String getBatimentName(String id_batiment) {
		String querry = "SELECT * FROM Batiments WHERE id_batiment=\"" + id_batiment + "\";";
		return firstResult(querry, "nom");
	}
	
	public String getSitebyBatiment(String id_batiment) {
		String querry = "SELECT * FROM Batiments, Sites WHERE Batiments.id_site=Sites.id_site AND Batiments.id_batiment=\"" + id_batiment + "\";";
		return firstResult(querry, "name");
	}


	public String getBatimentID(String batiment) {
		String querry = "SELECT * FROM Batiments WHERE nom=\"" + batiment + "\";";
		return firstResult(querry, "id_batiment");
	}
	
	public void addBatimentQuerry(String name, String site){
		String id_site = this.getSiteID(site);
		String querry = "INSERT INTO Batiments (nom, id_site) VALUES (\"" + name + "\", " + id_site + ");";
		this.execute(querry); 
	}
	public void changeBatimentDataQuerry(String name, String siteID, String id){
		String querry = "UPDATE Batiments SET nom=\"" + name + "\", id_site=" + siteID + " WHERE id_batiment=" + id + ";";
		this.execute(querry);
	}
	public ResultSet getBatimentByIdQuerry(String id){
		 String querry = "SELECT * FROM Batiments WHERE id_batiment=" + id + ";";
		 ResultSet resultat = this.doQuerry(querry);
		 return resultat; 
	}
	public ResultSet getAllSiteQuerry(){
		String querry = "SELECT * FROM Sites;";
		ResultSet resultat = this.doQuerry(querry);
		return resultat; 
	}
	public ResultSet getSiteByIdQuerry(String id_site){
		querry = "SELECT * FROM Sites WHERE id_site=" + id_site;
		ResultSet resultat = this.doQuerry(querry);
		return resultat; 
	}
	public ResultSet getAllBatimentQuerry(){
		String querry = "SELECT * FROM Batiments";
		ResultSet resultat = this.doQuerry(querry);
		return resultat; 
	}
	public ResultSet getAllBatimentFromSite(String site){
		querry = "SELECT Batiments.nom FROM Sites, Batiments WHERE (Sites.name=\"" + site + "\" and Sites.id_site=Batiments.id_site);";
		ResultSet resultat = this.doQuerry(querry);
		return resultat; 
	}
	public void RemoveBatimentQuerry(String id){
		String command = "DELETE FROM Batiments WHERE id_batiment=" + id + ";";
		this.execute(command);
	}
	public String getPasswordByloginQuerry(String login){
		String passFromDB = null;
		String querry = "SELECT * FROM Persons WHERE username='" + login + "';";
		ResultSet resultat = this.doQuerry(querry);
		try {
			resultat.next();
			passFromDB = resultat.getString("password");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return passFromDB; 
	}
	public ResultSet getAllPersonneByUsernameQuerry(){
		querry = "SELECT Persons.username FROM Persons;";
		ResultSet resultat = this.doQuerry(querry);
		return resultat; 
	}
	public ResultSet getPersonneQuerry(String site, String personne){
		querry = "SELECT Persons.id_person FROM Persons, Sites WHERE (Persons.username=\"" + personne + "\" and Sites.name=\"" + site + "\" and Persons.location=Sites.id_site);";
		return this.doQuerry(querry);
	}
	public ResultSet getRoomsQuerry(String site, String bat, String room){
		String querry = "SELECT Rooms.id_room FROM Rooms, Batiments, Sites WHERE (Sites.name = \"" + site + "\" and Sites.id_site=Batiments.id_site and Batiments.nom= \"" + bat + "\" and Rooms.id_batiment=Batiments.id_batiment and Rooms.num_room=" + room + ");";
		ResultSet resultat = this.doQuerry(querry);
		return resultat; 
	}
	public void AddReservationQuerry( String id_room , String id_person,String object , String date, String start, String end){
		String add = "INSERT INTO Reservations (id_room, id_person, object, date, start, end) VALUES (" + id_room + "," + id_person + ",\"" + object + "\",\"" + date + "\",\"" + start + "\",\"" + end + "\");";
		this.execute(add);
	}
	public void ChangeReservationQuerry( String idRoom , String idUser,String object , String date, String start, String end, String idReservation){
		String update= "UPDATE Reservations SET id_room=\"" + idRoom + "\", id_person=\"" + idUser + "\", object=\"" + object + "\", date='" + date + "', start='" + start + "', end='" + end + "' WHERE id_reservation=" + idReservation + ";";
		this.execute(update);
	}
	public ResultSet getReservationByID(String id){
		String querry = "SELECT * FROM Reservations WHERE id_reservation=" + id + ";";
		ResultSet resultat = this.doQuerry(querry);
		return resultat;
	}
	public void removeReservationQuerry(String id ){
		String command = "DELETE FROM Reservations WHERE id_reservation=" + id + ";";
		this.execute(command);
	}
	public ResultSet getAllReservationQuerry(){
		String querry = "SELECT * FROM Reservations";
		ResultSet resultat = this.doQuerry(querry);
		return resultat; 
	}
	public void addRoomQuerry(String batiment, String number, String size){
		String id_batiment = this.getBatimentID(batiment);
		String querry = "INSERT INTO Rooms (id_batiment, num_room, capacity) VALUES (" + id_batiment + "," + number + "," + size + ");";
		this.execute(querry);
	}
	public void BookReservationQuerry(String id_room, String id_person, String object, String date, String start, String end){
		String querry = "INSERT INTO Reservations (id_room, id_person, object, date, start, end) VALUES (" + id_room + ", " + id_person + ", \"" + object + "\", \"" + date + "\", \"" + start + "\", \"" + end + "\");";
		this.execute(querry);
	}
	public void ChangeRoomDataQuerry(String site, String number, String capacity, String id){
		String siteID = this.getSiteID(site);
		String update= "UPDATE Rooms SET id_site=" + siteID + ", num_room=" + number + ", capacity=" + capacity + " WHERE id_room=" + id + ";";
		this.execute(update);
	}
	public ResultSet allRoomwithNumberQuerry(String number){
		String querry = "SELECT * FROM Rooms WHERE num_room=" + number + ";";
		ResultSet resultat = this.doQuerry(querry);
		return resultat; 
	}
	public ResultSet allBatByIDQuerry(String idBatiment){
		String querry = "SELECT * FROM Batiments WHERE id_batiment=" + idBatiment;
		ResultSet resultat = this.doQuerry(querry);
		return resultat; 
	}
	public ResultSet getAllRooms(){
		String querry = "SELECT * FROM Rooms;";
		ResultSet resultat = this.doQuerry(querry);
		return resultat; 
	}
	public ResultSet getallRoomByBatIdQuerry(String batiment){
		String id = this.getBatimentID(batiment);
		String querry = "SELECT * FROM Rooms WHERE id_batiment=\"" + id + "\";";
		return this.doQuerry(querry);
	}
	public void removeRoomsQuerry(String numb){
		String command = "DELETE FROM Rooms WHERE num_room=" + numb + ";";
		this.execute(command);
	}
	public ResultSet getRoomByCapacity(){
		String querry = "SELECT Rooms.capacity FROM Rooms GROUP BY Rooms.capacity HAVING COUNT(Rooms.capacity)>=0;";
		return this.doQuerry(querry);
	}
	public ResultSet getAvailableRoomWithoutParticularitiesQuerry(String capa, String batiment, String date , String site,int havingCount){
		String querry = "SELECT Rooms.num_room,Reservations.start, Reservations.end FROM Sites, Rooms, Reservations, Batiments where (Batiments.nom=\"" + batiment + "\" and Sites.name=\"" +site + "\" and Reservations.date=\"" + date + "\" and Sites.id_site=Batiments.id_site and Rooms.capacity>=" + capa + " and Rooms.id_batiment=Batiments.id_batiment and Rooms.id_room=Reservations.id_room and Rooms.id_room IN (SELECT Rooms.id_room FROM Rooms, Particularities, Own where (Own.id_room = Rooms.id_room and Own.id_part=Particularities.id_part) GROUP BY Rooms.id_room HAVING COUNT(Rooms.id_room)>= " + havingCount + "));";
		ResultSet resultat = this.doQuerry(querry);
		return resultat; 
	}
	
	public ResultSet getAvailableRoomWithParticularitiesQuerry(String capa, String batiment,String particularities, String date , String site,int havingCount){
		String querry = "SELECT Rooms.num_room,Reservations.start, Reservations.end FROM Sites, Rooms, Reservations, Batiments where (Batiments.nom=\"" + batiment + "\" and Sites.name=\"" +site + "\" and Reservations.date=\"" + date + "\" and Sites.id_site=Batiments.id_site and Rooms.capacity>=" + capa + " and Rooms.id_batiment=Batiments.id_batiment and Rooms.id_room=Reservations.id_room and Rooms.id_room IN (SELECT Rooms.id_room FROM Rooms, Particularities, Own where ((" + particularities + ")  and Own.id_room = Rooms.id_room and Own.id_part=Particularities.id_part) GROUP BY Rooms.id_room HAVING COUNT(Rooms.id_room)>= " + havingCount + "));";
		ResultSet reultat = this.doQuerry(querry);
		return resultat;
	}
	public void addSiteQuerry(String name){
		String add= "INSERT INTO Sites (name) VALUES (\"" + name + "\");";
		this.execute(add);
	}
	public void ChangeSiteDataQuerry(String name, String id){
		String update= "UPDATE Sites SET name=\"" + name + "\" WHERE id_site=" + id + ";";
		this.execute(update);
	}
	public void removeSiteQuerry(String id){
		String command = "DELETE FROM Sites WHERE id_site=" + id + ";";
		this.execute(command);
	}
	public void changeUserDataQuerry(String location, String username, String password, String isAdmin, String id){
		String siteID = this.getSiteID(location);
		String update= "UPDATE Persons SET username=\"" + username + "\", password=\"" + password + "\", isAdmin=" + isAdmin + ", location=" + siteID + " WHERE id_person=" + id + ";";
		this.execute(update);
	}
	public ResultSet getAllPersonneQuerry(){
		String querry = "SELECT * FROM Persons";
		ResultSet resultat = this.doQuerry(querry);
		return resultat; 
	}
	public void registerQuerry(String username, String password, String isAdmin, String location){
		String siteID = this.getSiteID(location);
		String querry = "INSERT INTO Persons (username, password, isAdmin, location) VALUES (\"" + username + "\", \"" + password + "\", " + isAdmin + ", " + siteID + ");";
		this.execute(querry);
	}
	public void removeUserQuerry(String id){
		String command = "DELETE FROM Persons WHERE id_person=" + id + ";";
		this.execute(command);
	}
}
