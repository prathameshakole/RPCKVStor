package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Remote interface for key-value store operations.
 * Provides methods for storing, retrieving, and deleting key-value pairs.
 */
public interface StoreOperations extends Remote {
  /**
   * Stores or updates a key-value pair in the store.
   * @param keyValue The key-value pair to store
   * @return Response indicating success or failure of the operation
   * @throws RemoteException if a remote error occurs
   */
  Response putPair(KeyValue keyValue) throws RemoteException;

  /**
   * Retrieves the value associated with the specified key.
   * @param key The key to find
   * @return Response containing the value if found, or error if not found
   * @throws RemoteException if a remote error occurs
   */
  Response getPair(String key) throws RemoteException;

  /**
   * Deletes the key-value pair for the specified key.
   * @param key The key to delete
   * @return Response indicating success or failure of the deletion
   * @throws RemoteException if a remote error occurs
   */
  Response deletePair(String key) throws RemoteException;
}