package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Database extends SQLException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Pobiera z bazy danych pe³n¹ nazwê u¿ytkownika.
	 * 
	 * @param userid
	 *            ci¹g identyfikatora u¿ytkownika
	 * @return ci¹g pe³nej nazwy
	 * @throws SQLException
	 *             w przypadku problemu z baz¹ danych
	 */

	public static String rejestracja(String imie, String nazwisko, String email, String login, String haslo)
			throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String DRIVER = "oracle.jdbc.driver.OracleDriver";
		String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
		String USER = "system";
		String PASSWORD = "mateusz123";
		String QUERY1 = "SELECT IMIE FROM UZYTKOWNICY WHERE LOGIN = ?";
		String QUERY2 = "SELECT IMIE FROM UZYTKOWNICY WHERE EMAIL = ?";
		String QUERY3 = "INSERT INTO UZYTKOWNICY(IMIE, NAZWISKO, EMAIL, LOGIN, HASLO, DATA_ZALOZENIA) VALUES(?, ?, ?, ?, ?, ?)";
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			statement = connection.prepareStatement(QUERY1);
			statement.setString(1, login);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				return "Login jest ju¿ u¿ywany";
			}
			statement.close();
			resultSet.close();
			statement = connection.prepareStatement(QUERY2);
			statement.setString(1, email);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				return "Email jest ju¿ u¿ywany";
			} else {
				statement.close();
				resultSet.close();
				statement = connection.prepareStatement(QUERY3);
				statement.setString(1, imie);
				statement.setString(2, nazwisko);
				statement.setString(3, email);
				statement.setString(4, login);
				statement.setString(5, haslo);
				statement.setTimestamp(6, timestamp);
				statement.execute();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (resultSet != null)
				resultSet.close();
			if (statement != null)
				statement.close();
			if (connection != null)
				connection.close();
		}
		return "Rejestracja przebieg³a pomyœlnie";
	}

	public static String zalogowany(String login) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String DRIVER = "oracle.jdbc.driver.OracleDriver";
		String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
		String USER = "system";
		String PASSWORD = "mateusz123";
		String QUERY1 = "SELECT IMIE, NAZWISKO, LOGIN FROM UZYTKOWNICY WHERE LOGIN = ?";
		String dane = "";
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			statement = connection.prepareStatement(QUERY1);
			statement.setString(1, login);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				dane += "<h4>" + resultSet.getString("IMIE") + " " + resultSet.getString("NAZWISKO") + " ("
						+ resultSet.getString("LOGIN") + ")";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (resultSet != null)
				resultSet.close();
			if (statement != null)
				statement.close();
			if (connection != null)
				connection.close();
		}
		return dane;
	}

	public static void wylogowanie(String login) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String DRIVER = "oracle.jdbc.driver.OracleDriver";
		String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
		String USER = "system";
		String PASSWORD = "mateusz123";
		String QUERY1 = "UPDATE UZYTKOWNICY" + " SET OST_WYL = ?" + " WHERE LOGIN = ?";
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			statement = connection.prepareStatement(QUERY1);
			Timestamp data = new Timestamp(System.currentTimeMillis());
			statement.setTimestamp(1, data);
			statement.setString(2, login);
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (resultSet != null)
				resultSet.close();
			if (statement != null)
				statement.close();
			if (connection != null)
				connection.close();
		}
		return;
	}

	public static int logowanie(String login, String haslo) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String DRIVER = "oracle.jdbc.driver.OracleDriver";
		String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
		String QUERY1 = "SELECT IMIE FROM UZYTKOWNICY WHERE LOGIN = ? AND HASLO = ?";
		String QUERY2 = "UPDATE UZYTKOWNICY" + " SET OST_ZAL = ? " + "WHERE LOGIN = ? AND HASLO = ?";
		String QUERY3 = "UPDATE UZYTKOWNICY" + " SET BLEDNE_HASLO = ?, " + "DATA_BL_LOG = ? " + "WHERE LOGIN = ?";
		String QUERY4 = "SELECT BLEDNE_HASLO FROM UZYTKOWNICY WHERE LOGIN = ?";
		String QUERY5 = "UPDATE UZYTKOWNICY" + " SET BLEDNE_HASLO = 0" + " WHERE LOGIN = ?";
		String USER = "system";
		String PASSWORD = "mateusz123";
		int bledne_haslo = 0;
		Timestamp data_blednego_logowania = new Timestamp(System.currentTimeMillis());
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			statement = connection.prepareStatement(QUERY1);
			statement.setString(1, login);
			statement.setString(2, haslo);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				Timestamp data_zalogowania = new Timestamp(System.currentTimeMillis());
				statement.close();
				statement = connection.prepareStatement(QUERY2);
				statement.setTimestamp(1, data_zalogowania);
				statement.setString(2, login);
				statement.setString(3, haslo);
				statement.executeUpdate();
				statement.close();
				statement = connection.prepareStatement(QUERY5);
				statement.setString(1, login);
				statement.executeUpdate();
				return 1;
			} else {
				statement.close();
				resultSet.close();
				statement = connection.prepareStatement(QUERY4);
				statement.setString(1, login);
				resultSet = statement.executeQuery();
				if (resultSet.next()) {
					bledne_haslo = resultSet.getInt("BLEDNE_HASLO");
					bledne_haslo += 1;
				}
				statement.close();
				statement = connection.prepareStatement(QUERY3);
				statement.setInt(1, bledne_haslo);
				statement.setTimestamp(2, data_blednego_logowania);
				statement.setString(3, login);
				statement.executeUpdate();
				if (bledne_haslo == 3) {
					statement.close();
					statement = connection.prepareStatement(QUERY5);
					statement.setString(1, login);
					statement.executeUpdate();
					Thread.sleep(15000);
				}
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (resultSet != null)
				resultSet.close();
			if (statement != null)
				statement.close();
			if (connection != null)
				connection.close();
		}
		return 0;
	}
}
