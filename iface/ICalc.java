package iface;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ICalc extends Remote {
  long factorial(int n) throws RemoteException;
}
