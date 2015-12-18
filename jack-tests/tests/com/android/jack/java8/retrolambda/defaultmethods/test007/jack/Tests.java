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
// Copyright © 2013-2015 Esko Luontola <www.orfjackal.net>
// This software is released under the Apache License 2.0.
// The license text is at http://www.apache.org/licenses/LICENSE-2.0

package com.android.jack.java8.retrolambda.defaultmethods.test007.jack;

import org.junit.Assert;
import org.junit.Test;

public class Tests {

  private interface OriginalDefault {
    default String foo() {
        return "original";
    }
}

private interface OverriddenDefault extends OriginalDefault {
    @Override
    default String foo() {
        return "overridden";
    }
}

private class ImplementsOriginal implements OriginalDefault {
}

private class ImplementsOriginalAndOverriddenDefault implements OriginalDefault, OverriddenDefault {
}

private class ImplementsOverriddenAndOriginalDefault implements OverriddenDefault, OriginalDefault {
}

private class ExtendsImplementsOriginalAndImplementsOverriddenDefault extends ImplementsOriginal implements OverriddenDefault {
}

@Test
  public void implements_original_and_overridden_default_method() {
    Assert.assertEquals(new ImplementsOriginal().foo(), "original");
    Assert.assertEquals(new ImplementsOriginalAndOverriddenDefault().foo(), "overridden");
    Assert.assertEquals(new ImplementsOverriddenAndOriginalDefault().foo(), "overridden");
    Assert.assertEquals(
        new ExtendsImplementsOriginalAndImplementsOverriddenDefault().foo(), "overridden");
  }

}

