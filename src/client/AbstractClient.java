package client;

import java.rmi.Naming;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public abstract class AbstractClient {

  protected static final List<String> PRE_POPULATION_DATA = Arrays.asList(
          "put prathamesh akole",
          "put aditya shinde",
          "put ishan sarode",
          "put dnyanesh patil",
          "put kevin marino"
  );

  protected abstract void sendRequest(String hostname, int port, String command);

  public void start(String hostname, int port) {
    if (!isServerAvailable(hostname, port)) {
      logMessage("ERROR : Server is not reachable. Please ensure the server is running.");
      return;
    }

    prePopulateStore(hostname, port);
    performAutomaticOperations(hostname, port);
    acceptUserCommands(hostname, port);
  }

  protected boolean isServerAvailable(String hostname, int port) {
    try {
      String url = "rmi://" + hostname + ":" + port + "/StoreOperations";
      Naming.lookup(url);
      logMessage("RMI server is reachable.");
      return true;
    } catch (Exception e) {
      logMessage("ERROR : RMI server is not reachable. Please ensure the server is running.");
      return false;
    }
  }


  protected void prePopulateStore(String hostname, int port) {
    logMessage("Pre-populating the key-value store.");
    for (String command : PRE_POPULATION_DATA) {
      sendRequest(hostname, port, command);
    }
    logMessage("Pre-population complete.");
  }

  protected void performAutomaticOperations(String hostname, int port) {
    logMessage("Performing automatic operations...");

    List<String> operations = Arrays.asList(
            "put angel white",
            "put martin king",
            "put kayla holmes",
            "put karina kaif",
            "put dan bilt",
            "get martin",
            "get aditya",
            "get dan",
            "get kayla",
            "get ishan",
            "delete dan",
            "delete kayla",
            "delete martin",
            "delete karina",
            "delete dnyanesh"
    );

    for (String command : operations) {
      sendRequest(hostname, port, command);
    }

    logMessage("Automatic operations complete.");
  }

  protected void acceptUserCommands(String hostname, int port) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter commands (type 'exit' to quit):");

    while (true) {
      System.out.print("Enter Command: ");
      String command = scanner.nextLine();
      if ("exit".equalsIgnoreCase(command)) {
        break;
      }
      sendRequest(hostname, port, command);
    }
  }

  protected static void logMessage(String message) {
    System.out.println(System.currentTimeMillis() + " : " + message);
    ClientLogger.log(message);
  }
}