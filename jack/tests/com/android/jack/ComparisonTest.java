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

package com.android.jack;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;

/**
 * JUnit test for compilation of comparisons.
 */
@Ignore("Tree")
public class ComparisonTest {

  @BeforeClass
  public static void setUpClass() {
    Main.class.getClassLoader().setDefaultAssertionStatus(true);
  }

  /**
   * Verifies that the test source can compile to dex file.
   */
  @Test
  public void testCompile() throws Exception {
    File[] bootclasspath = new File[]{TestTools.getFromAndroidTree(
        "out/target/common/obj/JAVA_LIBRARIES/core-libart_intermediates/classes.zip"),
        TestTools.getFromAndroidTree(
        "out/host/common/obj/JAVA_LIBRARIES/junit4-hostdex-jack_intermediates/classes.zip")};
    TestTools.runCompilation(TestTools.buildCommandLineArgs(bootclasspath, null,
        TestTools.getJackTestsWithJackFolder("comparison/test001")));
  }
}
