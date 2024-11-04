package client;

import server.StoreOperations;
import server.KeyValue;
import server.Response;
import server.StoreOperationsImpl;

import java.rmi.Naming;
import java.rmi.RemoteException;

public class RMIClient extends AbstractClient {

  private StoreOperations store;

  public RMIClient(String hostname, int port) throws Exception {
    String url = "rmi://" + hostname + ":" + port + "/StoreOperations";
    store = (StoreOperations) Naming.lookup(url);
  }

  @Override
  protected void sendRequest(String hostname, int port, String command) {
    try {
      String[] parts = command.split(" ", 3);
      String operation = parts[0].toLowerCase();
      Response response;

      switch (operation) {
        case "put":
          if (parts.length == 3) {
            KeyValue keyValue = new KeyValue(parts[1], parts[2]);
            response = store.putPair(keyValue);
            logMessage(response.getMessage());
          } else {
            logMessage("ERROR : Invalid PUT command format.");
          }
          break;

        case "get":
          if (parts.length == 2) {
            response = store.getPair(parts[1]);
            logMessage(response.getMessage());
          } else {
            logMessage("ERROR : Invalid GET command format.");
          }
          break;

        case "delete":
          if (parts.length == 2) {
            response = store.deletePair(parts[1]);
            logMessage(response.getMessage());
          } else {
            logMessage("ERROR : Invalid DELETE command format.");
          }
          break;

        default:
          logMessage("ERROR : Unknown command: " + operation);
      }
    } catch (RemoteException e) {
      logMessage("ERROR : RemoteException occurred: " + e.getMessage());
    } catch (Exception e) {
      logMessage("ERROR : Exception occurred: " + e.getMessage());
    }
  }
}
