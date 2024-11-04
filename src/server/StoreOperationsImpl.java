package server;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StoreOperationsImpl implements StoreOperations {

  private final ConcurrentHashMap<String, String> storeMap;

  public StoreOperationsImpl() {
    storeMap = new ConcurrentHashMap<>();
  }

  @Override
  public Response putPair(KeyValue keyValue) throws RemoteException {
    validateKeyValue(keyValue);
    storeMap.put(keyValue.getKey(), keyValue.getValue());
    return Response.success("Key-Value Pair Saved Successfully!");
  }

  @Override
  public Response getPair(String key) throws RemoteException {
    validateKey(key);
    String value = storeMap.get(key);
    if (value != null) {
      return Response.success("Retrieved Key : Value Pair " + new KeyValue(key, value));
    } else {
      return Response.error("Key not found");
    }
  }

  @Override
  public Response deletePair(String key) throws RemoteException {
    validateKey(key);
    if (storeMap.remove(key) != null) {
      return Response.success("Key-Value Pair Deleted Successfully!");
    } else {
      return Response.error("Key not found");
    }
  }

  private void validateKeyValue(KeyValue keyValue) throws RemoteException {
    if (keyValue == null) {
      throw new RemoteException("Key Value pair cannot be null");
    }
    validateKey(keyValue.getKey());
    if (keyValue.getValue() == null) {
      throw new RemoteException("Value cannot be null");
    }
  }

  private void validateKey(String key) throws RemoteException {
    if (key == null) {
      throw new RemoteException("Key cannot be null");
    }
    if (key.trim().isEmpty()) {
      throw new RemoteException("Key cannot be empty or blank");
    }
  }
}
