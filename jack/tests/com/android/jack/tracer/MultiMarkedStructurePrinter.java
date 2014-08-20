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

package com.android.jack.tracer;

import com.android.jack.ir.ast.JNode;
import com.android.jack.util.StructurePrinter;

import java.io.PrintStream;

import javax.annotation.Nonnull;

public class MultiMarkedStructurePrinter extends StructurePrinter {

  private final int id;

  public MultiMarkedStructurePrinter(@Nonnull PrintStream out, int id) {
    super(out);
    this.id = id;
  }

  @Override
  protected boolean acceptFilter(@Nonnull JNode node) {
    MultiTracerMarker marker = node.getMarker(MultiTracerMarker.class);
    if (marker != null) {
      return marker.isSet(id);
    }
    return false;
  }

}
