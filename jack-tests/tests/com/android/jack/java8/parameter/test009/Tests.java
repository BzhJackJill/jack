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

package com.android.jack.java8.parameter.test009;

import com.android.jack.java8.parameter.common.ParameterTestModifier;

import junit.framework.Assert;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

class MyBaseClass {

  public void foo(String param1) {
  }

}
public class Tests extends MyBaseClass {

  @Test
  public void testStaticMethod() throws NoSuchMethodException, SecurityException {
    Method[] methods = Tests.class.getDeclaredMethods();
    for (Method method : methods) {
      if (method.isSynthetic()) {
        Parameter[] parameters = method.getParameters();
        Assert.assertEquals(1, parameters.length);
        check(parameters[0], "param1", /* isNamePresent= */ true, /* isImplicit= */ false,
            /* isSynthetic= */ true, ParameterTestModifier.MODIFIER_SYNTHETIC);
      }
    }
  }

  private void check(Parameter parameter, String name, boolean isNamePresent, boolean isImplicit,
      boolean isSynthetic, int modifier) {
    Assert.assertEquals(name, parameter.getName());
    Assert.assertEquals(isNamePresent, parameter.isNamePresent());
    Assert.assertEquals(isSynthetic, parameter.isSynthetic());
    Assert.assertEquals(isImplicit, parameter.isImplicit());
    Assert.assertEquals(modifier, parameter.getModifiers());
  }
}
