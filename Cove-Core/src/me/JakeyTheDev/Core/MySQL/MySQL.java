package me.JakeyTheDev.Core.MySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import me.JakeyTheDev.Core.Core;

public class MySQL extends Database {

	private String user;
	private String database;
	private String password;
	private String port;
	private String hostname;

	public MySQL(Plugin plugin, String hostname, String port, String database, String username, String password) {
		super(plugin);
		this.hostname = hostname;
		this.port = port;
		this.database = database;
		this.user = username;
		this.password = password;

		connect(hostname, port, database, username, password);
	}

	public boolean connect(String host, String port, String db, String user, String pass) {
		try {
			openConnection();
			return checkConnection();
		} catch (Exception e) {
			e.printStackTrace();
			plugin.getLogger().log(Level.SEVERE, "Could not connect to DB");
			return false;
		}
	}

	public void createTable(final String tableName, final String vars) {
		Bukkit.getScheduler().runTaskAsynchronously(Core.getInstance(), new Runnable() {
			public void run() {
				try {
					updateDB("CREATE TABLE IF NOT EXISTS " + tableName + "(" + vars + ");"); //Nope
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}
		});

	}

	public void update(final String update) {

		try {
			updateDB(update);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public ResultSet query(String query) {
		try {
			return this.queryDB(query);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}


	public Connection openConnection() throws SQLException, ClassNotFoundException {
		if (checkConnection()) {
			return connection;
		}
		connection = DriverManager.getConnection("jdbc:mysql://" + hostname + ":" + port + "/" + database, user, password);
		return connection;
	}

	public void register() {
		createTable("Players", "UUID VARCHAR(255), NAME VARCHAR(255), CRYSTALS BIGINT, RANK VARCHAR(255)");
	}

}

abstract class Database {
	public Connection connection;
	protected Plugin plugin;

	protected Database(Plugin plugin) {
		this.plugin = plugin;
		this.connection = null;
	}

	public abstract Connection openConnection() throws SQLException, ClassNotFoundException;

	public boolean checkConnection() throws SQLException {
		return (this.connection != null) && (!this.connection.isClosed());
	}

	public Connection getConnection() {
		return this.connection;
	}

	public boolean closeConnection() throws SQLException {
		if (this.connection == null) {
			return false;
		}
		this.connection.close();
		return true;
	}

	protected ResultSet queryDB(String query) throws SQLException, ClassNotFoundException {
		if (!checkConnection()) {
			openConnection();
		}
		Statement statement = this.connection.createStatement();
		ResultSet result = statement.executeQuery(query);
		return result;
	}

	protected int updateDB(String update) throws SQLException, ClassNotFoundException {
		if (!checkConnection()) {
			openConnection();
		}
		Statement statement = this.connection.createStatement();
		int result = statement.executeUpdate(update);
		return result;
	}

	public int[] sendBatchStatement(String[] stmts) throws SQLException, ClassNotFoundException {
		if (!checkConnection()) {
			openConnection();
		}
		Statement statement = this.connection.createStatement();
		for (String state : stmts) {
			statement.addBatch(state);
		}
		int[] ResultCodes = statement.executeBatch();

		this.connection.commit();
		return ResultCodes;
	}

	public Object getValue(UUID uuid, String key) {
		try {
			if (!checkConnection()) {
				openConnection();
			}
			PreparedStatement sql = connection.prepareStatement("SELECT `" + key + "` FROM `" +
					"Core" + "`.`" + "Players" + "` WHERE UUID=?;");
			sql.setString(1, uuid.toString());
			ResultSet resultSet = sql.executeQuery();
			boolean exists = resultSet.next();

			if (exists == true) {
				return resultSet.getObject(key);
			}

			sql.close();
			resultSet.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public void setValue(UUID uuid, String key, Object value, int i) {
		try {
			PreparedStatement updateData = connection.prepareStatement("UPDATE `" + "Players" +  "` SET " + key + "=? WHERE UUID=?;");
			updateData.setObject(1, value);
			updateData.setString(2, uuid.toString());
			updateData.executeUpdate();

			updateData.close();

		} catch (Exception e) {
		}
	}
	public boolean accountExists(UUID uuid, String string) {
		try {
			if (!checkConnection()) {
				openConnection();
			}
			PreparedStatement sql = this.connection.prepareStatement("SELECT * FROM `" + "Players" + "` WHERE UUID=?;"); 
			sql.setString(1, uuid.toString());
			ResultSet resultSet = sql.executeQuery();
			boolean containsPlayer = resultSet.next();

			sql.close();
			resultSet.close();

			return containsPlayer;

		} catch (Exception e) {
			e.printStackTrace();

			return false;
		}
	}
}