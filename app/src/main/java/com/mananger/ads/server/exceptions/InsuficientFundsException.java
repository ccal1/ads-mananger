package com.mananger.ads.server.exceptions;

public class InsuficientFundsException extends RuntimeException {
  public InsuficientFundsException(String message) {
    super(message);
  }
}
