package com.rmi.online;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

import java.rmi.registry.LocateRegistry;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.Scanner;

interface EmployeeSystem extends Remote {
    String addEmployee(int id, String name, int age) throws RemoteException;
    String updateEmployee(int id, String name, int age) throws RemoteException;
    String deleteEmployee(int id) throws RemoteException;
    void sayHi(String ip) throws RemoteException;
    void sayBye(String ip) throws RemoteException;
}

public class EmployeeManagement extends UnicastRemoteObject implements EmployeeSystem {
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private String sql = "";
    private String table = "employee";
    private String DBUsername = "employee";
    private String DBPassword = "employee";
    private String DBUrl = "jdbc:mysql://localhost:3306/employee";
    public EmployeeManagement() throws RemoteException{
        super();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(DBUrl, DBUsername, DBPassword);
            System.out.println("SQL connection established.");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    @Override
    public String addEmployee(int id, String name, int age) throws RemoteException {
        sql = "INSERT INTO " + table + " (id, name, age) VALUE(?,?,?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setInt(3, age);
            preparedStatement.execute();
            return "[OK] user added successfully!";
        } catch (SQLException e) {
            return "[FAILED] to add user!";
        }
    }

    @Override
    public String updateEmployee(int id, String name, int age) throws RemoteException {
        sql = "UPDATE " + table + " SET name=?, age=? WHERE id=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.setInt(3, id);
            preparedStatement.execute();
            return "[OK] user updated successfully!";
        } catch (SQLException e) {
            return "[FAILED] to update user!";
        }
    }

    @Override
    public String deleteEmployee(int id) throws RemoteException {
        sql = "DELETE FROM " + table + " WHERE id=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            return "[OK] user deleted successfully!";
        } catch (SQLException e) {
            return "[FAILED] to delete user!";
        }
    }

    @Override
    public void sayHi(String ip) throws RemoteException {
        System.out.println(ip+" connected!");
    }
    public void sayBye(String ip) throws RemoteException {
        System.out.println(ip+" Disconnected!");
    }
}

class RunEmployeeServer {
    public static void main(String[] args) {
        int port = 5000;
        String url = "rmi://localhost:"+port+"/EMP_MAN";
        try {
            System.out.println("Server started!");
            LocateRegistry.createRegistry(port);
            EmployeeSystem employeeSystem = new EmployeeManagement();
            Naming.rebind(url, employeeSystem);
            System.out.println("[OK] remote object bind successfully!");
            System.out.println("Waiting for client request . . .");
        } catch (RemoteException e) {
            System.err.println("Can't register the driver!");
        } catch (MalformedURLException e) {
            System.err.println("Something went wrong on url mapping!");
        }
    }
}

class RunClient {
    static EmployeeSystem employeeSystem;
    static int port = 5000;
    static Integer id, age;
    static String url = "rmi://localhost:"+port+"/EMP_MAN", name, msg = "";
    static char choice;
    static Scanner scanner = new Scanner(System.in);
    static void menu() {
        System.out.println("1. Add User" +
                "\n2. Update User" +
                "\n3. Delete User" +
                "\n4. Stop Program");
    }

    static void inputManager() {
        System.out.print("\tEmployee id: ");
        try {
            id = scanner.nextInt();
        } catch (Exception e) {
            System.err.println("\n\tInvalid Input!\n\tID must be integer.");
            id = null;
            choice = ' ';
        }
        if (choice == '3')
            return;
        System.out.print("\tEmployee name: ");
        name = scanner.next();
        System.out.print("\tEmployee age: ");
        try {
            age = scanner.nextInt();
        } catch (Exception e) {
            System.err.println("\n\tInvalid Input!\n\tAge must be integer.");
            age = null;
            choice = ' ';
        }
    }

    public static void main(String[] args) {
        try {
            employeeSystem = (EmployeeSystem) Naming.lookup(url);
            employeeSystem.sayHi(Inet4Address.getLocalHost().getHostAddress());
        } catch (NotBoundException e) {
            System.err.println("Something went wrong! NotBoundException occurs!");
            return;
        } catch (MalformedURLException e) {
            System.err.println("Something went wrong! May be invalid url!");
            return;
        } catch (RemoteException e) {
            System.err.println("Something went wrong! RemoteException occurs!");
            return;
        } catch (UnknownHostException e) {
            System.err.println("Host address not found!");
            return;
        }

        while (true) {
            try {
                menu();
                System.out.print("Your choice: ");
                choice = scanner.next().charAt(0);
                switch (choice) {
                    case '1':
                        inputManager();
                        msg = employeeSystem.addEmployee(id, name, age);
                        break;
                    case '2':
                        inputManager();
                        msg = employeeSystem.updateEmployee(id, name, age);
                        break;
                    case '3':
                        inputManager();
                        msg = employeeSystem.deleteEmployee(id);
                        break;
                    default:
                        choice = '4';
                }
            } catch (IOException e) {
                System.err.println("Something went wrong! IOException occurs.");
            } catch (NullPointerException e) {
                System.err.println("Null value!");
            }

            if (choice == '4')
                break;
            if (!msg.equals("") && msg.contains("[FAILED]")) {
                System.out.println("\t"+msg);
                System.out.println("\tTry different id!\n");
            } else {
                System.out.println("\t" + msg + "\n");
            }
        }

        try {
            employeeSystem.sayBye(Inet4Address.getLocalHost().getHostAddress());
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }

        System.err.println("Program Terminated!");

    }
}