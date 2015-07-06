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

package com.android.jack.opcodes.invoke_virtual.jm;

public class T_invoke_virtual_14 extends TSuper {

    public boolean run() {
        int a = 123;
        int b = 659;
        if(testArgsOrder(300, 3) == 100)
            if(a == 123)
                if(b == 659)
                    return true;
        return false;
    }
}
