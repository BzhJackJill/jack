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

package com.android.sched.util.codec;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;


/**
 * This exception is thrown when a list checker cannot check one element in the list.
 */
public class ListCheckingException extends CheckingException {
  private static final long serialVersionUID = 1L;

  @Nonnegative
  private final int index;

  public ListCheckingException(@Nonnegative int index, @Nonnull String message) {
    super("element #" + index  + ": " + message);
    this.index = index;
  }

  public ListCheckingException(@Nonnegative int index, @Nonnull Throwable cause) {
    super("element #" + index + ": " + cause.getMessage(), cause);
    this.index = index;
  }

  public ListCheckingException(
      @Nonnegative int index, @Nonnull String message, @Nonnull Throwable cause) {
    super(message, cause);
    this.index = index;
  }

  @Nonnegative
  public int getIndex() {
    return index;
  }
}
