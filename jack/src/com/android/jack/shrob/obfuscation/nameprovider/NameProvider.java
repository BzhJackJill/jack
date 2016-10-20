/*
 * Copyright (C) 2012 The Android Open Source Project
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

package com.android.jack.shrob.obfuscation.nameprovider;

import com.android.jack.shrob.obfuscation.key.Key;
import com.android.sched.util.codec.VariableName;

import javax.annotation.Nonnull;

/**
 * A class that provide names
 */
@VariableName("provider")
public interface NameProvider {
  @Nonnull
  public String getNewName(@Nonnull Key key);

  public boolean hasAlternativeName(@Nonnull Key key);
}
