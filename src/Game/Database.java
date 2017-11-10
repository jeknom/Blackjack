package Game;
import java.sql.*;
import java.util.ArrayList;

public class Database {
	
	private String url = "jdbc:mysql://localhost:3306/blackjack?useSSL=false";
	private String username = "root";
	private String password = "";
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public void setDb(String db) {
		this.url = "jdbc:mysql://"+db+"?useSSL=false";
	}
	
	public void setUn(String un) {
		this.username = un;
	}
	
	public void setPw(String pw) {
		this.password = pw;
	}
	
	public ArrayList<Player> getPlayers() throws SQLException{
		ArrayList<Player> players = new ArrayList<Player>();
		players.clear();
		this.conn = DriverManager.getConnection(url, username, password);
		String query = "SELECT name, score, date, deck FROM score ORDER BY score DESC";
		this.pstmt = conn.prepareStatement(query);
		this.rs = pstmt.executeQuery();
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
		String query = "SELECT * FROM score WHERE name = ? ORDER BY score DESC";
		this.pstmt = this.conn.prepareStatement(query);
		this.pstmt.setString(1, searchName);
		this.rs = this.pstmt.executeQuery();
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
		java.util.Date dt = new java.util.Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = sdf.format(dt);
		String insert = "INSERT INTO `score`(`name`, `score`, `date`, `deck`) VALUES (?, ?, ?, ?)";
		PreparedStatement ps = conn.prepareStatement(insert);
		ps.setString(1, name);
		ps.setInt(2, score);
		ps.setString(3, currentTime);
		ps.setInt(4, decks);
		ps.execute();
		this.conn.close();
	}
	
	public void close() throws SQLException {
		this.conn.close();
		System.out.println("Connection closed!");
	}
}
