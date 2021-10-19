package client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import iface.*;

public class Client {

  private static String SERVER_NAME = "factorial_server";

  public static void main(String[] args) {

    if (args.length < 1) {
      System.out.println("Must supply an integer as a command argument!");
      System.exit(1);
    }
    int n = Integer.parseInt(args[0]);

    try {
      Registry registry = LocateRegistry.getRegistry();
      ICalc server = (ICalc) registry.lookup(SERVER_NAME);
      long result = server.factorial(n);
      System.out.printf("🎉 The factorial of %d is: %d\n", n, result);
      System.exit(0);
    } catch (Exception e) {
      System.err.println("🆘 exception:");
      e.printStackTrace();
      System.exit(1);
    }
  }
}
