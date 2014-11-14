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

package com.android.jack.library.v0001;

import com.android.jack.library.FileType;
import com.android.jack.library.FileTypeDoesNotExistException;
import com.android.jack.library.LibraryIOException;
import com.android.jack.library.OutputJackLibrary;
import com.android.jack.library.OutputLibrary;
import com.android.jack.library.OutputLibraryLocation;
import com.android.sched.util.file.CannotCreateFileException;
import com.android.sched.util.file.NoSuchFileException;
import com.android.sched.util.file.NotFileOrDirectoryException;
import com.android.sched.vfs.InputOutputVDir;
import com.android.sched.vfs.InputVFile;
import com.android.sched.vfs.OutputVFile;
import com.android.sched.vfs.SequentialOutputVDir;
import com.android.sched.vfs.VPath;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.annotation.Nonnull;

/**
 * Jack library generated by Jack.
 */
public class OutputJackLibraryImpl extends OutputJackLibrary {

  @Nonnull
  private final InputOutputVDir outputVDir;

  @Nonnull
  private final OutputLibraryLocation location = new OutputLibraryLocation() {
    @Override
    @Nonnull
    public String getDescription() {
      return outputVDir.getLocation().getDescription();
    }

    @Override
    @Nonnull
    public OutputLibrary getOutputLibrary() {
      return OutputJackLibraryImpl.this;
    }

    @Override
    public final boolean equals(Object obj) {
      return obj instanceof OutputLibraryLocation
        && ((OutputLibraryLocation) obj).getOutputLibrary().equals(getOutputLibrary());
    }

    @Override
    public final int hashCode() {
      return OutputJackLibraryImpl.this.hashCode();
    }
  };

  public OutputJackLibraryImpl(@Nonnull InputOutputVDir outputVDir, @Nonnull String emitterId,
      @Nonnull String emitterVersion) {
    super(new Properties());
    this.outputVDir = outputVDir;
    putProperty(KEY_LIB_EMITTER, emitterId);
    putProperty(KEY_LIB_EMITTER_VERSION, emitterVersion);
    putProperty(KEY_LIB_MAJOR_VERSION, String.valueOf(getMajorVersion()));
    putProperty(KEY_LIB_MINOR_VERSION, String.valueOf(getMinorVersion()));
  }

  @Override
  @Nonnull
  public OutputVFile createFile(@Nonnull FileType fileType, @Nonnull VPath typePath)
      throws CannotCreateFileException {
    putProperty(fileType.getPropertyPrefix(), String.valueOf(true));
    addFileType(fileType);
    typePath.addSuffix(fileType.getFileExtension());
    typePath.prependPath(fileType.getVPathPrefix());
    return outputVDir.createOutputVFile(typePath);
  }

  @Override
  public boolean needsSequentialWriting() {
    return outputVDir instanceof SequentialOutputVDir;
  }

  @Override
  @Nonnull
  public OutputLibraryLocation getLocation() {
    return location;
  }

  @Override
  public void close() throws LibraryIOException {
    OutputStream os = null;
    try {
      OutputVFile libraryPropertiesOut = outputVDir.createOutputVFile(LIBRARY_PROPERTIES_VPATH);
      os = libraryPropertiesOut.openWrite();
      libraryProperties.store(os, "Library properties");
    } catch (CannotCreateFileException e) {
      throw new LibraryIOException(getLocation(), e);
    } catch (IOException e) {
      throw new LibraryIOException(getLocation(), e);
    } finally {
      if (os != null) {
        try {
          os.close();
        } catch (IOException e) {
          throw new LibraryIOException(getLocation(), e);
        }
      }
    }
  }

  @Override
  public int getMinorVersion() {
    return Version.MINOR;
  }

  @Override
  public int getMajorVersion() {
    return Version.MAJOR;
  }

  @Override
  @Nonnull
  public Iterator<InputVFile> iterator(@Nonnull FileType fileType) {
    List<InputVFile> inputVFiles = new ArrayList<InputVFile>();
    fillFiles(outputVDir, fileType, inputVFiles);
    return inputVFiles.listIterator();
  }

  @Override
  @Nonnull
  public InputVFile getFile(@Nonnull FileType fileType, @Nonnull VPath typePath)
      throws FileTypeDoesNotExistException {
    try {
      VPath clonedPath = typePath.clone();
      clonedPath.addSuffix(fileType.getFileExtension());
      clonedPath.prependPath(fileType.getVPathPrefix());
      return outputVDir.getInputVFile(clonedPath);
    } catch (NotFileOrDirectoryException e) {
      throw new FileTypeDoesNotExistException(getLocation(), typePath, fileType);
    } catch (NoSuchFileException e) {
      throw new FileTypeDoesNotExistException(getLocation(), typePath, fileType);
    }
  }
}
