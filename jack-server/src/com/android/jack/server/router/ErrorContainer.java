/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.jack.server.router;

import org.simpleframework.http.Request;
import org.simpleframework.http.Response;
import org.simpleframework.http.Status;
import org.simpleframework.http.core.Container;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Nonnull;

/**
 * Container responding just an error status
 */
public class ErrorContainer implements Container {

  @Nonnull
  private static Logger logger = Logger.getLogger(ErrorContainer.class.getName());

  @Nonnull
  private final Status status;

  public ErrorContainer(@Nonnull Status status) {
    this.status = status;
  }

  @Override
  public void handle(@Nonnull Request request, @Nonnull Response response) {
    logger.log(Level.WARNING, "Unknown request: '" + request.toString() + "'");
    response.setStatus(status);
    try {
      response.close();
    } catch (IOException e) {
      logger.log(Level.SEVERE, "Exception during close: ", e);
    }
  }
}