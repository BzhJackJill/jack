/*
 * Copyright (C) 2008 The Android Open Source Project
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

package com.android.jack.opcodes.if_ltz;

import com.android.jack.opcodes.if_ltz.jm.T_if_ltz_1;
import com.android.jack.opcodes.if_ltz.jm.T_if_ltz_2;
import com.android.jack.test.DxTestCase;


public class Test_if_ltz extends DxTestCase {

    /**
     * @title  Argument = 5
     */
    public void testN1() {
        T_if_ltz_1 t = new T_if_ltz_1();
        /*
         * Compare with 1234 to check that in case of failed comparison
         * execution proceeds at the address following if_ltz instruction
         */
        assertEquals(1234, t.run(5));
    }

    /**
     * @title  Argument = 0
     */
    public void testN2() {
        T_if_ltz_1 t = new T_if_ltz_1();
        assertEquals(1234, t.run(0));
    }

    /**
     * @title  Arguments = -5
     */
    public void testN3() {
        T_if_ltz_1 t = new T_if_ltz_1();
        assertEquals(1, t.run(-5));
    }

    /**
     * @title  Arguments = -5
     */
    public void testN4() {
        T_if_ltz_2 t = new T_if_ltz_2();
        assertEquals(true, t.run(-5));
    }

    /**
     * @title  Arguments = Integer.MAX_VALUE
     */
    public void testB1() {
        T_if_ltz_1 t = new T_if_ltz_1();
        assertEquals(1234, t.run(Integer.MAX_VALUE));
    }

    /**
     * @title  Arguments = Integer.MIN_VALUE
     */
    public void testB2() {
        T_if_ltz_1 t = new T_if_ltz_1();
        assertEquals(1, t.run(Integer.MIN_VALUE));
    }

}
