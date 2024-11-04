package client;

import static client.AbstractClient.logMessage;

public class RMIClientApp {
  public static void main(String[] args) {
    if (args.length != 2) {
      logMessage("Usage: java client.RMIClientApp <host-name> <port-number>");
      return;
    }

    String hostname = args[0];
    int port;

    try {
      port = Integer.parseInt(args[1]);
    } catch (NumberFormatException e) {
      logMessage("ERROR : Invalid port number.");
      return;
    }

    try {
      RMIClient client = new RMIClient(hostname, port);
      client.start(hostname, port);
    } catch (Exception e) {
      logMessage("ERROR : Failed to start RMI client: " + e.getMessage());
    }
  }
}
