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

package com.android.sched.vfs.zip;

import com.android.sched.util.location.FileLocation;
import com.android.sched.util.location.Location;
import com.android.sched.util.location.ZipLocation;
import com.android.sched.vfs.AbstractVElement;
import com.android.sched.vfs.InputVFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.annotation.Nonnull;

class InputZipVFile extends AbstractVElement implements InputVFile {

  @Nonnull
  private final String name;
  @Nonnull
  private final ZipFile zip;
  @Nonnull
  private final ZipEntry entry;

  InputZipVFile(@Nonnull String name, @Nonnull ZipFile zip, @Nonnull ZipEntry entry) {
    this.name = name;
    this.zip = zip;
    this.entry = entry;
  }

  @Nonnull
  @Override
  public String getName() {
    return name;
  }

  @Nonnull
  @Override
  public InputStream openRead() throws IOException {
    return zip.getInputStream(entry);
  }

  @Override
  @Nonnull
  public Location getLocation() {
    return new ZipLocation(new FileLocation(new File(zip.getName())), entry);
  }

  @Override
  public boolean isVDir() {
    return false;
  }

}
