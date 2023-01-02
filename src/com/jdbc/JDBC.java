package com.jdbc;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetProvider;
import java.sql.*;

public class JDBC {
    Connection connection;
    Statement statement;
    ResultSet resultSet;
    PreparedStatement preparedStatement;

    private void closeConnection() {
        try {
            System.out.println("Closing connection ...");
            connection.close();
            System.out.println("[OK]");
        } catch (Exception e) {
            System.err.println("Failed to close connection!");
        }
    }
    private void displayResult(ResultSet resultSet) {
        try {
            System.out.println("Displaying result data ...");
            while (resultSet.next()) {
                System.out.println(
                        resultSet.getInt(1) + " " +
                                resultSet.getString(2) + " " +
                                resultSet.getString(3) + " "
                );
            }
            System.out.println("[OK]");
        } catch (Exception e) {
            System.err.println("Failed to display the result!");
        }

    }
    private void prepareStatement(int id, String name, String dep) {
        try {
            System.out.println("preparing " + name + "'s statement ...");
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, dep);
            System.out.println("[OK]");
        } catch (Exception e) {
            System.err.println("Failed to execute prepare statement");
        }
    }
    private void getColMetaData(int col) {
        try {
            ResultSetMetaData rsmd = connection.prepareStatement("SELECT * from users").executeQuery().getMetaData();
            System.out.print("Getting column meta data\n" +
                    "Total columns: " + rsmd.getColumnCount() + "\n" +
                            "Column Name: " + rsmd.getColumnName(col) + "\n" +
                            "Column Type: " + rsmd.getColumnType(col) + "\n[OK]\n"
            );
        } catch (Exception e) {
            System.err.println("Failed to get meta data!");
        }

    }
    private void getDriverMetaData() {
        try {
            System.out.println("Getting driver meta data ...");
            DatabaseMetaData dbmd = connection.getMetaData();
            System.out.println(
                    "Driver Name: " + dbmd.getDriverName() + "\n" +
                            "Driver Version: " + dbmd.getDriverVersion() + "\n" +
                            "Database Username: " + dbmd.getUserName() + "\n" +
                            "Database Product Name: " + dbmd.getDatabaseProductName() + "\n" +
                            "Database Product version: " +dbmd.getDatabaseProductVersion()
            );
            System.out.println("[OK]");
        } catch (Exception e) {
            System.err.println("Failed to get driver meta data!");
        }
    }
    JDBC () {
        try {
            // registering com.mysql.jdbc.Driver Class
            Class.forName("com.mysql.jdbc.Driver");

            // creating connection
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "root");

            // initializing statement
            statement = connection.createStatement();

            // execute batch
//            statement.addBatch("INSERT INTO users value(7, 'Fira', 'CSE')");
//            statement.addBatch("INSERT INTO users value(8, 'Melate', 'CSE')");
//            statement.addBatch("INSERT INTO users value(9, 'Feven', 'CSE')");
//            statement.executeBatch();

            // initializing prepare statement
            preparedStatement = connection.prepareStatement("INSERT INTO users value(?,?,?)");

            // sending user data to prepare statement
//            prepareStatement(6, "Abebe", "CSE");
//            preparedStatement.executeUpdate();

            // statement example
            statement.executeQuery("select * from users");

            // displaying the result
            displayResult( statement.getResultSet());

            // getting column meta data
            getColMetaData(2);

            // getting database meta data
            getDriverMetaData();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            closeConnection();
        }

    }
}

class RowSet {
    JdbcRowSet rowSet;
    RowSet() {
        try {
            JdbcRowSet rowSet = RowSetProvider.newFactory().createJdbcRowSet();
            rowSet.setUrl("jdbc:mysql://localhost:3306/jdbc");
            rowSet.setUsername("root");
            rowSet.setPassword("root");

            rowSet.setCommand("select * from users");
            rowSet.execute();

            while (rowSet.next()) {
                System.out.println("Id: " + rowSet.getString(1));
                System.out.println("Name: " + rowSet.getString(2));
                System.out.println("Department: " + rowSet.getString(3));
            }

        } catch (Exception e) {
            System.err.println("RowSet error!");
        }
    }
}


class CallJDBC {
    public static void main(String[] args) {
//        JDBC jdbc = new JDBC();
        RowSet rowSet = new RowSet();
    }
}
