/*
 * Copyright (C) 2014 The Android Open Source Project
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

package com.android.jack.resource;

import com.android.jack.reporting.ReportableException;

import javax.annotation.Nonnull;

/**
 * A {@link ReportableException} that occurs during the resource reading phase.
 */
public class ResourceReadingException extends ReportableException {

  private static final long serialVersionUID = 1L;

  public ResourceReadingException(@Nonnull Throwable cause) {
    super(cause);
  }

  @Override
  @Nonnull
  public String getMessage() {
    return "Error during the resource reading phase: " + getCause().getMessage();
  }

  @Override
  @Nonnull
  public ProblemLevel getDefaultProblemLevel() {
    return ProblemLevel.ERROR;
  }
}
