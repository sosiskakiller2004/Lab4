package services;

import java.sql.*;

public class DBService {


    private Connection getConnect(){
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        final String url = "jdbc:postgresql://localhost:5432/mydatabase";
        final String user = "postgres";
        final String password = "12345";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public void insert(String sql){
        Statement statement = null;
        try {
            Connection connection = getConnect();
            statement = connection.createStatement();
            statement.executeUpdate(sql);
            statement.close();
            connection.close();

        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
    }
    public void delete(String sql, Object... params) {
        PreparedStatement preparedStatement = null;
        try {
            Connection connection = getConnect();
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void update(String sql, Object... params) {
        PreparedStatement preparedStatement = null;
        try {
            Connection connection = getConnect();
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public ResultSet select(String sql){
        Connection connection = getConnect();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            return rs;
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            return null;
        }
        finally {

        }
    }

}
