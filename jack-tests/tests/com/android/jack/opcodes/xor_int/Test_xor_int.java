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

package com.android.jack.opcodes.xor_int;

import com.android.jack.opcodes.xor_int.jm.T_xor_int_1;
import com.android.jack.opcodes.xor_int.jm.T_xor_int_3;
import com.android.jack.opcodes.xor_int.jm.T_xor_int_4;
import com.android.jack.test.DxTestCase;


public class Test_xor_int extends DxTestCase {

    /**
     * @title Arguments = 15, 8
     */
    public void testN1() {
        T_xor_int_1 t = new T_xor_int_1();
        assertEquals(7, t.run(15, 8));
    }

    /**
     * @title Arguments = 0xfffffff8, 0xfffffff1
     */
    public void testN2() {
        T_xor_int_1 t = new T_xor_int_1();
        assertEquals(9, t.run(0xfffffff8, 0xfffffff1));
    }

    /**
     * @title  Arguments = 0xcafe & -1
     */
    public void testN3() {
        T_xor_int_1 t = new T_xor_int_1();
        assertEquals(0xFFFF3501, t.run(0xcafe, -1));
    }

    /**
     * @title  Arguments = 0xcafe & -1
     */
    public void testN4() {
        T_xor_int_3 t = new T_xor_int_3();
        assertEquals(0xFFFF3501, t.run(0xcafe, -1));
    }

    /**
     * @title  Arguments = 0xcafe & -1
     */
    public void testN5() {
        T_xor_int_4 t = new T_xor_int_4();
        assertEquals(0xFFFF3501, t.run(0xcafe, -1));
    }

    /**
     * @title  Arguments = 0 & -1
     */
    public void testB1() {
        T_xor_int_1 t = new T_xor_int_1();
        assertEquals(-1, t.run(0, -1));
    }

    /**
     * @title  Arguments = Integer.MAX_VALUE & Integer.MIN_VALUE
     */
    public void testB2() {
        T_xor_int_1 t = new T_xor_int_1();
        assertEquals(0xffffffff, t.run(Integer.MAX_VALUE, Integer.MIN_VALUE));
    }

}
