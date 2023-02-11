package com.rmi;

import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

interface RMI extends Remote {
    public static final int port = 60000;
    String sayHello() throws RemoteException;
}

public class RMIServer extends UnicastRemoteObject implements RMI {

    public RMIServer() throws RemoteException {
        super(port);
    }

    @Override
    public String sayHello() throws RemoteException {
        return "Hello from RMI server class!";
    }
}

class RunServer {
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(RMI.port);
            RMI rmiObj = new RMIServer();
            Naming.rebind("rmi://localhost:5000/RMI_SERVER", rmiObj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class RunClient {
    public static void main(String[] args) {
        try {
            RMI rmiObj = (RMI) Naming.lookup("rmi://localhost:5000/RMI_SERVER");
            System.out.println(rmiObj.sayHello());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}