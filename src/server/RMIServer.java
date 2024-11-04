package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMIServer extends UnicastRemoteObject implements StoreOperations {
  private final StoreOperationsImpl storeOperationsImpl;

  protected RMIServer() throws RemoteException {
    super();
    this.storeOperationsImpl = new StoreOperationsImpl();
  }

  @Override
  public synchronized Response putPair(KeyValue keyValue) throws RemoteException {
    return storeOperationsImpl.putPair(keyValue);
  }

  @Override
  public synchronized Response getPair(String key) throws RemoteException {
    return storeOperationsImpl.getPair(key);
  }

  @Override
  public synchronized Response deletePair(String key) throws RemoteException {
    return storeOperationsImpl.deletePair(key);
  }

  protected static void logMessage(String message) {
    System.out.println(System.currentTimeMillis() + " : " + message);
    ServerLogger.log(message);
  }
}
