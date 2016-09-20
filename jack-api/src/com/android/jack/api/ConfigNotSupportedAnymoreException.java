/*
 * Copyright (C) 2016 The Android Open Source Project
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

package com.android.jack.api;

import javax.annotation.Nonnull;

/**
 * Thrown when the requested Jack configuration for a given API version is not supported because
 * this Jack does not support this configuration anymore.
 */
public class ConfigNotSupportedAnymoreException extends ConfigNotSupportedException {
  private static final long serialVersionUID = 1L;

  /**
   * Construct the exception with no details.
   */
  public ConfigNotSupportedAnymoreException() {
    super();
  }

  /**
   * Construct the exception with a {@link String} message.
   * @param message the message
   */
  public ConfigNotSupportedAnymoreException(@Nonnull String message) {
    super(message);
  }

  /**
   * Construct the exception with a {@link String} message and a {@link Throwable} cause.
   * @param message the message
   * @param cause the cause
   */
  public ConfigNotSupportedAnymoreException(@Nonnull String message, @Nonnull Throwable cause) {
    super(message, cause);
  }

  /**
   * Construct the exception with a {@link Throwable} cause.
   * @param cause the cause
   */
  public ConfigNotSupportedAnymoreException(@Nonnull Throwable cause) {
    super(cause);
  }
}
