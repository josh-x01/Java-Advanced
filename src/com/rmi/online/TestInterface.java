package com.rmi.online;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TestInterface extends Remote {
    String connectionMessage(String name) throws RemoteException;
}
