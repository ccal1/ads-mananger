package com.mananger.ads.server.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FAILED_DEPENDENCY)
public class InsuficientFundsException extends RuntimeException {
  public InsuficientFundsException(String message) {
    super(message);
  }
}
