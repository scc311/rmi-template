package server;

import java.rmi.registry.Registry;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import iface.*;

public class Server extends UnicastRemoteObject implements ICalc {

  private static int SERVER_PORT = 1099;
  private static String REGISTRY_ENTRY_NAME = "factorial_server";

  public Server() throws RemoteException {
    super(0);
  }

  public long factorial(int n) {
    System.out.println("📩  handling client request: factorial");
    return numberFactorial(n);
  }

  private long numberFactorial(int n) {
    if (n == 0)
      return 1;
    else
      return (n * numberFactorial(n - 1));
  }

  public static void main(String[] args) {
    try {
      Registry registry = LocateRegistry.createRegistry(SERVER_PORT);
      registry.rebind(REGISTRY_ENTRY_NAME, new Server());
      System.out.println("✅  server running...");
    } catch (Exception e) {
      System.err.println("🆘 exception:");
      e.printStackTrace();
      System.exit(1);
    }
  }
}