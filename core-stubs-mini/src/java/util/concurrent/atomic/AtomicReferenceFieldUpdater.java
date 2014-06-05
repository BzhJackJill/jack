/*
* Copyright (C) 2014 The Android Open Source Project
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

package java.util.concurrent.atomic;

public abstract class AtomicReferenceFieldUpdater<T, V> {
  protected AtomicReferenceFieldUpdater() {
    throw new RuntimeException("Stub!");
  }

  public static <U, W> java.util.concurrent.atomic.AtomicReferenceFieldUpdater<U, W> newUpdater(
      java.lang.Class<U> tclass, java.lang.Class<W> vclass, java.lang.String fieldName) {
    throw new RuntimeException("Stub!");
  }

  public abstract boolean compareAndSet(T obj, V expect, V update);

  public abstract boolean weakCompareAndSet(T obj, V expect, V update);

  public abstract void set(T obj, V newValue);

  public abstract void lazySet(T obj, V newValue);

  public abstract V get(T obj);

  public V getAndSet(T obj, V newValue) {
    throw new RuntimeException("Stub!");
  }
}
