package com.rmi.dani;

import java.io.IOException;
import java.net.*;
import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;
import java.sql.*;
import java.util.Scanner;

interface employeeInterface extends Remote {
void createEmployee(int cid, String employeeName, String position) throws RemoteException;
void updateEmployee(int cid, String employeeName, String position) throws RemoteException;
void deleteEmployee(int cid) throws RemoteException;
}

public class EmployeeManagement2 extends UnicastRemoteObject implements employeeInterface {
    Connection con = null;
    PreparedStatement ps = null;
    String sql = "";
    String table = "employeeTable";

    public EmployeeManagement2() throws RemoteException{
        super();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee", "admin", "admin");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    @Override
    public void createEmployee(int cid, String employeeName, String position) throws RemoteException {
        sql = "INSERT INTO " + table + " (cid, name, position) VALUE(?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, cid);
            ps.setString(2, employeeName);
            ps.setString(3, position);
            ps.execute();
            System.out.println("Employee created successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
        public void updateEmployee(int cid, String employeeName, String position) throws RemoteException {
        sql = "UPDATE " + table + " SET name=?, position=? WHERE cid=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, employeeName);
            ps.setString(2, position);
            ps.setInt(3, cid);
            ps.execute();
            System.out.println("Employee updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteEmployee(int cid) throws RemoteException {
        sql = "DELETE FROM " + table + " WHERE cid=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, cid);
            ps.execute();
            System.out.println("Employee deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

class Server {
    public static void main(String[] args) {
        int port = 4321;
        String url = "rmi://localhost:"+port+"/EMP";
        try {
            System.out.println("Server running...");
            LocateRegistry.createRegistry(port);
            employeeInterface employeeSystem = new EmployeeManagement2();
            Naming.rebind(url, employeeSystem);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}

class Client {
    static private employeeInterface employeeSystem;
    static private int port = 4321;
    static private String postion;
    static private Integer id;
    static private String url = "rmi://localhost:"+port+"/EMP";
    static private String name;
    static private char choice;
    static private Scanner scanner = new Scanner(System.in);
    static void option() {
        System.out.println("1. Create Employee" +
                "\n2. Update Employee" +
                "\n3. Delete Employee" +
                "\n4. Stop Program");
    }

    static void clientAcceptor() {
        System.out.print("\tEmployee id: ");
        id = scanner.nextInt();
        if (choice == '3')
            return;
        System.out.print("\tEmployee name: ");
        name = scanner.next();
        System.out.print("\tEmployee position: ");
        postion = scanner.next();
    }

    public static void main(String[] args) {
        try {
            employeeSystem = (employeeInterface) Naming.lookup(url);
        } catch (Exception e) {
            e.printStackTrace();
        }

        while (true) {
            try {
                option();
                System.out.print("1/2/3/q: ");
                choice = scanner.next().charAt(0);
                switch (choice) {
                    case '1':
                        clientAcceptor();
                        employeeSystem.createEmployee(id, name, postion);
                        break;
                    case '2':
                        clientAcceptor();
                        employeeSystem.updateEmployee(id, name, postion);
                        break;
                    case '3':
                        clientAcceptor();
                        employeeSystem.deleteEmployee(id);
                        break;
                    default:
                        choice = 'q';
                }
                if (choice == 'q')
                    break;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                System.err.println("Null value!");
            }
        }
    }
}