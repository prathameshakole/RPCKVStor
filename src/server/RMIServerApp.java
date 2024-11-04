package server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import static server.RMIServer.logMessage;

public class RMIServerApp {
  public static void main(String[] args) {
    if (args.length == 0) {
      logMessage("Usage: java server.RMIServerApp <port_number>");
      System.exit(1);
    }

    try {
      int port = Integer.parseInt(args[0]);

      RMIServer server = new RMIServer();
      Registry registry = LocateRegistry.createRegistry(port);
      registry.rebind("StoreOperations", server);

      logMessage("RMI Server started on port " + port + " and bound to registry.");
    } catch (NumberFormatException e) {
      logMessage("Invalid port number. Please enter a valid integer.");
      System.exit(1);
    } catch (Exception e) {
      logMessage("Error occurred: " + e.getMessage());
      e.printStackTrace();
      System.exit(1);
    }
  }
}