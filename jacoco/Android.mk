# Copyright (C) 2017 The Android Open Source Project
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

LOCAL_PATH:= $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE := jacoco-core-lib-jack
LOCAL_MODULE_TAGS := optional
LOCAL_MODULE_CLASS := JAVA_LIBRARIES

LOCAL_SRC_FILES := $(call all-java-files-under, org.jacoco.core/src)

LOCAL_JAVA_LANGUAGE_VERSION := 1.7

LOCAL_JAVA_LIBRARIES := \
  asm-4.1-jack \
  asm-commons-4.1-jack \
  asm-tree-4.1-jack

include $(BUILD_HOST_JAVA_LIBRARY)

include $(CLEAR_VARS)

LOCAL_MODULE := jacoco-report-lib-jack
LOCAL_MODULE_TAGS := optional
LOCAL_MODULE_CLASS := JAVA_LIBRARIES

LOCAL_SRC_FILES := $(call all-java-files-under, org.jacoco.report/src)

LOCAL_JAVA_LANGUAGE_VERSION := 1.7

LOCAL_JAVA_LIBRARIES := \
  jacoco-core-lib-jack \
  asm-4.1-jack

include $(BUILD_HOST_JAVA_LIBRARY)
