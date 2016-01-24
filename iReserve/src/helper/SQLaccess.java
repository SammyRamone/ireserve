package helper;

public interface SQLaccess {
	

	

		public static SQLaccess getInstance();

		

		public ResultSet doQuerry(String querry);

		public boolean execute(String command);

		public String[] getAllSites(boolean addAllToList);

		public String[] getAllRooms();

		public String[] getAllPersons();

		public String[] getAllBatiments(boolean addAllToList);
		
		public String[] getAllBatiments();
		
		private String firstResult(String querry, String column);

		public String getSiteID(String siteName);

		public String getSiteName(String id_site);

		public String getRoomNumber(String roomID);

		public String getRoomID(String roomNumber);

		public String getUsername(String personID);

		public String getUserID(String username);

		public String getBatimentName(String id_batiment);
		
		public String getSitebyBatiment(String id_batiment);


		public String getBatimentID(String batiment) ;
		
		public void addBatimentQuerry(String name, String site);
		public void changeBatimentDataQuerry(String name, String siteID, String id);
		public ResultSet getBatimentByIdQuerry(String id);
		public ResultSet getAllSiteQuerry();
		public ResultSet getSiteByIdQuerry(String id_site);
		public ResultSet getAllBatimentQuerry();
		public ResultSet getAllBatimentFromSite(String site);
		public void RemoveBatimentQuerry(String id);
		public String getPasswordByloginQuerry(String login);
		public ResultSet getAllPersonneByUsernameQuerry();
		public ResultSet getPersonneQuerry(String site, String personne);
		public ResultSet getRoomsQuerry(String site, String bat, String room);
		public void AddReservationQuerry( String id_room , String id_person,String object , String date, String start, String end);
		public void ChangeReservationQuerry( String idRoom , String idUser,String object , String date, String start, String end, String idReservation);
		public ResultSet getReservationByID(String id);
		public void removeReservationQuerry(String id );
		public ResultSet getAllReservationQuerry();
		public void addRoomQuerry(String batiment, String number, String size);
		public void BookReservationQuerry(String id_room, String id_person, String object, String date, String start, String end);
		public void ChangeRoomDataQuerry(String site, String number, String capacity, String id);
		public ResultSet allRoomwithNumberQuerry(String number);
		public ResultSet allBatByIDQuerry(String idBatiment);
		public ResultSet getAllRooms();
		public ResultSet getallRoomByBatIdQuerry(String batiment);
		public void removeRoomsQuerry(String numb);
		public ResultSet getRoomByCapacity();
		public ResultSet getAvailableRoomWithoutParticularitiesQuerry(String capa, String batiment, String date , String site,int havingCount);
		
		public ResultSet getAvailableRoomWithParticularitiesQuerry(String capa, String batiment,String particularities, String date , String site,int havingCount);
		public void addSiteQuerry(String name);
		public void ChangeSiteDataQuerry(String name, String id);
		public void removeSiteQuerry(String id);
		public void changeUserDataQuerry(String location, String username, String password, String isAdmin, String id);
		public ResultSet getAllPersonneQuerry();
		public void registerQuerry(String username, String password, String isAdmin, String location);
		public void removeUserQuerry(String id);
	}


