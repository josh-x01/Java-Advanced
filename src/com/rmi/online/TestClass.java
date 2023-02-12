package com.rmi.online;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class TestClass extends UnicastRemoteObject implements TestInterface{
    public TestClass(int port) throws RemoteException {
        super(port);
    }

    @Override
    public String connectionMessage(String name) throws RemoteException {
        return "Hello " + name + ", this is message from remote server!";
    }

    public static void main(String[] args) {
        int port = 5000;
        try {
            LocateRegistry.createRegistry(port);
            TestClass testing = new TestClass(port);
            Naming.rebind("rmi://192.168.102.149:5000/RMI_TEST", testing);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}

class TestClient {
    public static void main(String[] args) {
        try {
            TestInterface test = (TestInterface) Naming.lookup("rmi://192.168.102.149:5000/RMI_TEST");
            System.out.println(test.connectionMessage("Josh"));
        } catch (NotBoundException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}

