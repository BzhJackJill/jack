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

package com.android.jack.jayce.v0004.nodes;

import com.android.jack.ir.ast.JBreakStatement;
import com.android.jack.ir.ast.JLabel;
import com.android.jack.jayce.linker.CatchBlockLinker;
import com.android.jack.jayce.v0004.io.ExportSession;
import com.android.jack.jayce.v0004.io.ImportHelper;
import com.android.jack.jayce.v0004.io.JayceInternalReaderImpl;
import com.android.jack.jayce.v0004.io.JayceInternalWriterImpl;
import com.android.jack.jayce.v0004.io.Token;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

/**
 * Java break statement.
 */
public class NBreakStatement extends NStatement {
  @Nonnull
  public static final Token TOKEN = Token.BREAK_STATEMENT;

  @CheckForNull
  public String label;

  @Nonnull
  public List<String> catchBlockIds = Collections.emptyList();

  @Override
  public void importFromJast(@Nonnull ImportHelper loader, @Nonnull Object node) {
    JBreakStatement jBreakStatement = (JBreakStatement) node;
    label = ImportHelper.getLabelName(jBreakStatement.getLabel());
    catchBlockIds = loader.getIds(loader.getCatchBlockSymbols(), jBreakStatement.getJCatchBlocks());
    sourceInfo = jBreakStatement.getSourceInfo();
  }

  @Override
  @Nonnull
  public JBreakStatement exportAsJast(@Nonnull ExportSession exportSession) {
    assert sourceInfo != null;
    JLabel jLabel = (label == null) ? null : new JLabel(sourceInfo, label);
    JBreakStatement jBreakStatement = new JBreakStatement(sourceInfo, jLabel);
    for (String catchId : catchBlockIds) {
      exportSession.getCatchBlockResolver().addLink(catchId, new CatchBlockLinker(jBreakStatement));
    }
    return jBreakStatement;
  }

  @Override
  public void writeContent(@Nonnull JayceInternalWriterImpl out) throws IOException {
    out.writeString(label);
  }

  @Override
  public void readContent(@Nonnull JayceInternalReaderImpl in) throws IOException {
    label = in.readString();
  }

  public static void skipContent(@Nonnull JayceInternalReaderImpl in) throws IOException {
    in.skipString();
  }

  @Override
  @Nonnull
  public Token getToken() {
    return TOKEN;
  }

  @Override
  @Nonnull
  public List<String> getCatchBlockIds() {
    return catchBlockIds;
  }

  @Override
  public void setCatchBlockIds(@Nonnull List<String> catchBlockIds) {
    this.catchBlockIds = catchBlockIds;
  }
}
