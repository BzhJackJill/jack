/*
 * Copyright (C) 2017 The Android Open Source Project
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

package com.android.jack.ir.ast;

import com.android.jack.ir.ast.cfg.JPhiBlockElement;
import com.android.jack.ir.sourceinfo.SourceInfo;
import com.android.sched.item.Component;
import com.android.sched.scheduler.ScheduleInstance;
import com.android.sched.transform.TransformRequest;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

/**
 * This version of the SSA variable reference only appears on the right hand side of any
 * assignments.
 */
public class JSsaVariableUseRef extends JSsaVariableRef {

  @Nonnull
  private final JSsaVariableDefRef def;

  /* package */ JSsaVariableUseRef(@Nonnull SourceInfo info, @Nonnull JVariable target,
      @Nonnegative int version, JSsaVariableDefRef def) {
    super(info, target, version);
    this.def = def;
  }

  /**
   * @return true if it is used in a Phi element.
   */
  public boolean isPhiUse() {
    JNode parent = getParent();
    return parent instanceof JPhiBlockElement;
  }

  @Nonnull
  public JSsaVariableDefRef getDef() {
    return def;
  }

  public void deleteUseFromDef() {
    boolean result = def.removeUse(this);
    assert result;
  }

  @Override
  public void traverse(@Nonnull JVisitor visitor) {
    visitor.visit(this);
    visitor.endVisit(this);
  }

  @Override
  public void traverse(@Nonnull ScheduleInstance<? super Component> schedule) throws Exception {
    schedule.process(this);
  }

  @Override
  public void visit(@Nonnull JVisitor visitor, @Nonnull TransformRequest transformRequest)
      throws Exception {
    visitor.visit(this, transformRequest);
  }
}
