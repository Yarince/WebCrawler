package nl.han.oose.martis.yarince;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

    public Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(properties.getProperty("jdbc.url"), properties.getProperty("jdbc.username"), properties.getProperty("jdbc.password"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    public void addUrl(String url) {
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
}