/*
 * Copyright (C) 2013 The Android Open Source Project
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

package com.android.sched.util.log.stats;

import javax.annotation.Nonnull;


/**
 * Represents a counter statistic.
 */
public class PercentImpl extends Percent {
  private long trueCount;
  private long total;

  protected PercentImpl(@Nonnull StatisticId<? extends Statistic> id) {
    super(id);
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public synchronized void addTrue() {
    this.trueCount++;
    this.total++;
  }

  @Override
  public synchronized void addFalse() {
    this.total++;
  }

  @Override
  public synchronized void add(boolean value) {
    if (value) {
      addTrue();
    } else {
      addFalse();
    }
  }

  @Override
  public synchronized void removeTrue() {
    this.trueCount--;
    this.total--;
  }

  @Override
  public synchronized void removeFalse() {
    this.total--;
  }

  @Override
  public synchronized void remove(boolean value) {
    if (value) {
      removeTrue();
    } else {
      removeFalse();
    }
  }

  @Override
  public synchronized double getPercent() {
    if (trueCount < 0 || total < 0) {
      return Double.NaN;
    }

    return (double ) trueCount / (double) total;
  }

  @Override
  public long getTotal() {
    return total;
  }

  @Override
  public long getTrueCount() {
    return trueCount;
  }

  @Override
  public synchronized void merge(@Nonnull Statistic statistic) {
    PercentImpl percent = (PercentImpl) statistic;

    synchronized (percent) {
      this.trueCount += percent.trueCount;
      this.total   += percent.total;
    }
  }
}
