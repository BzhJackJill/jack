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

package com.android.jack.jayce.v0003.nodes;

import com.android.jack.ir.ast.marker.SimpleName;
import com.android.jack.jayce.v0003.io.ExportSession;
import com.android.jack.jayce.v0003.io.ImportHelper;
import com.android.jack.jayce.v0003.io.JayceInternalReaderImpl;
import com.android.jack.jayce.v0003.io.Token;

import java.io.IOException;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;


/**
 * This {@link NMarker} holds source name retrieved from ecj.
 */
public class NSimpleName extends NMarker {

  @Nonnull
  public static final Token TOKEN = Token.SIMPLE_NAME;

  @CheckForNull
  public String simpleName;

  @Override
  public void importFromJast(@Nonnull ImportHelper loader, @Nonnull Object node) {
    SimpleName marker = (SimpleName) node;
    simpleName = marker.getSimpleName();
  }

  @Override
  @Nonnull
  public SimpleName exportAsJast(@Nonnull ExportSession exportSession) {
    assert simpleName != null;
    return new SimpleName(simpleName);
  }

  @Override
  public void readContent(@Nonnull JayceInternalReaderImpl in) throws IOException {
    simpleName = in.readString();
  }

  @Override
  @Nonnull
  public Token getToken() {
    return TOKEN;
  }
}
