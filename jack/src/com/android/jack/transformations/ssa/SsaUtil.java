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

package com.android.jack.transformations.ssa;

import com.google.common.collect.Lists;

import com.android.jack.ir.ast.JAsgOperation;
import com.android.jack.ir.ast.JExpression;
import com.android.jack.ir.ast.JLocal;
import com.android.jack.ir.ast.JMethodBodyCfg;
import com.android.jack.ir.ast.JNode;
import com.android.jack.ir.ast.JParameter;
import com.android.jack.ir.ast.JVariable;
import com.android.jack.ir.ast.JVariableRef;
import com.android.jack.ir.ast.JVisitor;
import com.android.jack.ir.ast.cfg.JBasicBlockElement;
import com.android.jack.ir.ast.cfg.JControlFlowGraph;
import com.android.jack.ir.ast.cfg.JVariableAsgBlockElement;

import java.util.List;

import javax.annotation.Nonnull;

/**
 * Not exactly a util but more of a set of things that are missing in the current API and we have to
 * bruteforce it here.
 */
public class SsaUtil {

  public static List<JVariableRef> getUsedVariables(JBasicBlockElement stmt) {
    final List<JVariableRef> result = Lists.newArrayList();
    (new JVisitor() {
      @Override
      public boolean visit(@Nonnull JVariableRef varRef) {
        JNode parent = varRef.getParent();
        if (!(parent instanceof JAsgOperation) || ((JAsgOperation) parent).getLhs() != varRef) {
          result.add(varRef);
        }
        return super.visit(varRef);
      }
    }).accept(stmt);
    return result;
  }

  public static JVariableRef getDefinedVariable(JBasicBlockElement stmt) {
    if (!(stmt instanceof JVariableAsgBlockElement)) {
      return null;
    }
    JExpression lhs = ((JVariableAsgBlockElement) stmt).getAssignment().getLhs();
    if (!(lhs instanceof JVariableRef)) {
      return null;
    } else {
      return (JVariableRef) lhs;
    }
  }

  public static int getLocalIndex(@Nonnull JControlFlowGraph cfg, JVariable var) {
    JMethodBodyCfg body = cfg.getMethodBody();
    int numParam = body.getMethod().getParams().size();
    if (var instanceof JParameter) {
      int paramIdx = body.getMethod().getParams().indexOf(var);
      if (paramIdx == -1) {
        throw new RuntimeException();
      }
      return paramIdx;
    }

    int numLocal = body.getLocals().size();
    if (var instanceof JLocal) {
      // We either have a defined local or catch param.
      List<JLocal> catchParams = body.getCatchLocals();
      int index = catchParams.indexOf(var);
      if (index != -1) {
        return numLocal + numParam + index;
      } else {
        int localIdx = body.getLocals().indexOf(var);
        if (localIdx == -1) {
          throw new RuntimeException("variable not found ");
        }
        return numParam + localIdx;
      }
    }

    return -1;
  }

  /**
   * TODO(acleung): Investigate if it is worth caching this.
   */
  public static JVariable getVariableByIndex(@Nonnull JControlFlowGraph cfg,
      int index) {
    JMethodBodyCfg body = cfg.getMethodBody();
    assert body != null;
    int numLocal = body.getLocals().size();
    int numParam = body.getMethod().getParams().size();

    if (index < numParam) {
      return body.getMethod().getParams().get(index);
    }

    if (index < numParam + numLocal) {
      return body.getLocals().get(index - numParam);
    }

    List<JLocal> catchParams = body.getCatchLocals();
    return catchParams.get(index - numParam - numLocal);
  }

  public static int getTotalNumberOfLocals(@Nonnull JControlFlowGraph cfg) {
    JMethodBodyCfg body = cfg.getMethodBody();
    assert body != null;
    int numParam = body.getMethod().getParams().size();
    assert body.getLocals() != null;
    int numLocal = body.getLocals().size();
    int numCatch = body.getNumCatchLocals();
    return numLocal + numParam + numCatch;
  }
}
