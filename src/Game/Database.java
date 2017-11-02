package Game;
import java.sql.*;
import java.util.ArrayList;

public class Database {
	
	private String url = "jdbc:mysql://localhost:3306/blackjack?autoReconnect=true&useSSL=false";
	private String username = "root";
	private String password = "";
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	
	public ArrayList<Player> getPlayers() throws SQLException{
		ArrayList<Player> players = new ArrayList<Player>();
		players.clear();
		this.conn = DriverManager.getConnection(url, username, password);
		this.stmt = conn.createStatement();
		this.rs = stmt.executeQuery("SELECT name, score, date, deck FROM score ORDER BY score DESC");
		while (rs.next()) {
			String name = rs.getString("name");
			int score = rs.getInt("score");
			String date = String.valueOf(rs.getDate("date"));
			int decks = rs.getInt("deck");
			System.out.println(name+" "+score+" "+decks+" "+date);
			players.add(new Player(name, score, decks, date));
		}
		this.conn.close();
	    return players;
	}
	
	public ArrayList<Player> searchName(String searchName) throws SQLException {
		ArrayList<Player> players = new ArrayList<Player>();
		players.clear();
		this.conn = DriverManager.getConnection(url, username, password);
		this.stmt = conn.createStatement();
		this.rs = stmt.executeQuery("SELECT * FROM score WHERE name='"+searchName+"' ORDER BY score DESC;");
		while (rs.next()) {
			String name = rs.getString("name");
			int score = rs.getInt("score");
			String date = String.valueOf(rs.getDate("date"));
			int decks = rs.getInt("deck");
			System.out.println(name+" "+score+" "+decks+" "+date);
			players.add(new Player(name, score, decks, date));
		}
		this.conn.close();
	    return players;
	}
	
	public void insert(String name, int score, int decks) throws SQLException{
		this.conn = DriverManager.getConnection(url, username, password);
		this.stmt = conn.createStatement();
		java.util.Date dt = new java.util.Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = sdf.format(dt);
		stmt.execute("INSERT INTO `score`(`name`, `score`, `date`, `deck`) VALUES ('"+name+"','"+score+"','"+currentTime+"','"+decks+"')");
		this.conn.close();
	}
	
	public void close() throws SQLException {
		this.conn.close();
		System.out.println("Connection closed!");
	}
}
