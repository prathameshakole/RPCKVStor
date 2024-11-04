package server;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;

import static server.RMIServer.logMessage;

public class Response implements Serializable {
  private String status;
  private String message;

  public static final String SUCCESS = "SUCCESS";
  public static final String ERROR = "ERROR";

  public Response(String status, String message) {
    this.status = status;
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  @Override
  public String toString() {
    return status + ": " + message;
  }

  public static Response success(String message) {
    logMessage(new Response(SUCCESS, message).toString());
    return new Response(SUCCESS, message);
  }

  public static Response error(String message) {
    logMessage(new Response(ERROR, message).toString());
    return new Response(ERROR, message);
  }

}