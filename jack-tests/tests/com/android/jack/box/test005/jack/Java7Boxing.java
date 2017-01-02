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

package com.android.jack.box.test005.jack;

import org.junit.Assert;
import org.junit.Test;

public class Java7Boxing {

  @Test
  public void test001() {
    Assert.assertEquals(4414079, Java7Boxing.get(true));
  }
  @Test
  public void test002() {
    try {
      Java7Boxing.get(false);
      Assert.fail();
    } catch (ClassCastException e) {
      // OK
    }
  }

  @SuppressWarnings("boxing")
  public static int get(boolean cond) {
    int i = 35312635;
    Object s = 702730947;
    i >>>= (int) (cond ? s : -1284717918.0f);
    return i;
  }

}
