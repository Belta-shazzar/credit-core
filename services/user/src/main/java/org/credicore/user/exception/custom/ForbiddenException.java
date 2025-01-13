package org.credicore.user.exception.custom;

import java.io.Serial;

public class ForbiddenException extends Exception {
  @Serial
  private static final long serialVersionUID = 1L;

  public ForbiddenException(String msg) {
    super(msg);
  }
}
