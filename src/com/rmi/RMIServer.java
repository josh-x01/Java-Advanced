package com.rmi;

import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

interface RMI extends Remote {
    int port = 5000;
    String sayHello() throws RemoteException;
}

public class RMIServer extends UnicastRemoteObject implements RMI{
    public RMIServer(int port) throws RemoteException {
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
            LocateRegistry.createRegistry(5000);
            RMI rmiObj = new RMIServer(5000);
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