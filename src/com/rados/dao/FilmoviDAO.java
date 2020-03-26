package com.rados.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.rados.model.Film;

public class FilmoviDAO {
	Connection connection;

	public void loadDriver() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void openConnection() {
		String url = "jdbc:mysql://localhost:3306/test";
		String user = "root";
		String pass = "";
		try {
			connection = DriverManager.getConnection(url, user, pass);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public ArrayList<Film> getAllMovies() throws SQLException {
		ArrayList<Film> lista = new ArrayList<>();
		String query = "select * from film order by GodinIzdavanja desc";
		Statement s = connection.createStatement();
		ResultSet rs = s.executeQuery(query);
		while (rs.next()) {
			int id = rs.getInt("idFilma");
			String naslov = rs.getString("Naslov");
			int trajanje = rs.getInt("Trajanje");
			int godinaIzdavanja = rs.getInt("GodinIzdavanja");
			String zanr = rs.getString("Zanr");
			String aka = rs.getString("AKA");
			String trejler = rs.getString("trailer");
			String opis=rs.getString("Opis");
			Film film = new Film(id, naslov, trajanje, godinaIzdavanja, zanr, aka, trejler, opis);

			lista.add(film);

		}
		return lista;
	}

	public void dodaj(Film film) throws SQLException {
		String query = "insert into film(Naslov, Trajanje, GodinIzdavanja, Zanr, AKA) VALUES(?,?,?,?,?)";
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setString(1, film.getNaslov());
		ps.setInt(2, film.getTrajanje());
		ps.setInt(3, film.getGodina());
		ps.setString(4, film.getZanr());
		ps.setString(5, film.getAka());
		ps.execute();

	}

	public ArrayList<Film> getFilteredList() throws SQLException {
		ArrayList<Film> lista = new ArrayList<>();
		String query = "SELECT * FROM film WHERE Trajanje>170";
		Statement s = connection.createStatement();
		ResultSet rs = s.executeQuery(query);
		while (rs.next()) {
			int id = rs.getInt("idFilma");
			String naslov = rs.getString("Naslov");
			int trajanje = rs.getInt("Trajanje");
			int godinaIzdavanja = rs.getInt("GodinIzdavanja");
			String zanr = rs.getString("Zanr");
			String aka = rs.getString("AKA");
			String trejler = rs.getString("trailer");
			Film film = new Film(id, naslov, trajanje, godinaIzdavanja, zanr, aka, trejler);
			lista.add(film);

		}
		return lista;
	}

	public void deleteMovie(String idFilma) throws SQLException {
		int id = Integer.parseInt(idFilma);
		String query = "delete from film where idFilma=?";
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setInt(1, id);
		ps.execute();

	}

	public Film loadData(String idFilma) throws SQLException {
		String query = "select * from film where idFilma=?";
		PreparedStatement ps = connection.prepareStatement(query);
		int id = Integer.parseInt(idFilma);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		Film f = null;
		while (rs.next()) {

			String naslov = rs.getString("Naslov");
			int trajanje = rs.getInt("Trajanje");
			int godinaIzdavanja = rs.getInt("GodinIzdavanja");
			String zanr = rs.getString("Zanr");
			String aka = rs.getString("AKA");
			String trejler = rs.getString("trailer");
			// MORAS DA VRATIS I ID BEZ OBZIRA STO SI GA PROSLEDIO GORE PREKO
			// ps.setInt(1,id)
			// tj moras da ga stavis u objekat
			f = new Film(id, naslov, trajanje, godinaIzdavanja, zanr, aka, trejler);

		}
		return f;
	}

	public void update(Film film) throws SQLException {
		String query = "update film set Naslov=?, Trajanje=?, GodinIzdavanja=?, Zanr=?, AKA=? where idFilma=?";
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setString(1, film.getNaslov());
		ps.setInt(2, film.getTrajanje());
		ps.setInt(3, film.getGodina());
		ps.setString(4, film.getZanr());
		ps.setString(5, film.getAka());
		ps.setInt(6, film.getIdFilma());
		ps.execute();
		

	}

	public Film getMovieTralier(String idFilma) throws SQLException {

		Film f= null;
		String query = "select * from film where idFilma=?";
		PreparedStatement ps = connection.prepareStatement(query);
		int id = Integer.parseInt(idFilma);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
			String naslov = rs.getString("Naslov");
			int trajanje = rs.getInt("Trajanje");
			int godinaIzdavanja = rs.getInt("GodinIzdavanja");
			String zanr = rs.getString("Zanr");
			String aka = rs.getString("AKA");
			String trejler = rs.getString("trailer");

			 f = new Film(id, naslov, trajanje, godinaIzdavanja, zanr, aka, trejler);

		}
		return f;
	}

	public void addTrailer(int id, String trailer) throws SQLException {
		String trejler= trailer.replace("https://www.youtube.com/watch?v=", "https://www.youtube.com/embed/");
		String query="update  film set trailer=? where idFilma=?";
		PreparedStatement ps= connection.prepareStatement(query);
		ps.setString(1, trejler);
		ps.setInt(2, id);
		ps.execute();
		
	}

	public Film opisFilma(String idfilma) throws SQLException {
		int id = Integer.parseInt(idfilma);
		String query="select * from film where idFilma=?";
		PreparedStatement ps= connection.prepareStatement(query);
		ps.setInt(1, id);
		ResultSet rs= ps.executeQuery();
		Film film=null;
		while(rs.next()) {
			
			String naslov = rs.getString("Naslov");
			int trajanje = rs.getInt("Trajanje");
			int godinaIzdavanja = rs.getInt("GodinIzdavanja");
			String zanr = rs.getString("Zanr");
			String aka = rs.getString("AKA");
			String trejler = rs.getString("trailer");
			String opis= rs.getString("Opis");
			
			 film = new Film(id, naslov, trajanje, godinaIzdavanja, zanr, aka, trejler, opis);
		}
		
		return film;
	}

}
