package nl.han.oose.martis.yarince;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DB {

    private static final Properties properties = new Properties();

    static {
        try {
            properties.load(DB.class.getClassLoader().getResourceAsStream("config.properties"));
            Class.forName(properties.getProperty("jdbc.driver"));
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(properties.getProperty("jdbc.url"), properties.getProperty("jdbc.username"), properties.getProperty("jdbc.password"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    public synchronized void addUrl(String url)  {
        try (
                Connection con = getConnection();
                PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO crawler.record (URL) VALUES (?)")
        ) {
            preparedStatement.setString(1, url);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public synchronized void addContent(String url,String h1) {
        try (
                Connection con = getConnection();
                PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO crawler.content (H1) VALUES (?)")
        ) {
            preparedStatement.setString(1, h1);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public synchronized boolean exists(String url) {
        try (
                Connection con = getConnection();
                PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM crawler.record  WHERE URL = ?")
        ) {
            preparedStatement.setString(1, url);
            ResultSet resultSet;
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}