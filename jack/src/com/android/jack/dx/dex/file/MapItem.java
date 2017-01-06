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

package com.android.jack.dx.dex.file;

import com.android.jack.dx.util.AnnotatedOutput;
import com.android.jack.dx.util.Hex;

import java.util.ArrayList;

/**
 * Class that represents a map item.
 */
public final class MapItem extends OffsettedItem {
  /** file alignment of this class, in bytes */
  private static final int ALIGNMENT = 4;

  /** write size of this class, in bytes: three {@code uint}s */
  private static final int WRITE_SIZE = (4 * 3);

  /** {@code non-null;} item type this instance covers */
  private final ItemType type;

  /** {@code non-null;} section this instance covers */
  private final Section section;

  /**
   * {@code null-ok;} first item covered or {@code null} if this is
   * a self-reference
   */
  private final Item firstItem;

  /**
   * {@code > 0;} count of items covered; {@code 1} if this
   * is a self-reference
   */
  private final int itemCount;

  /**
   * Constructs a list item with instances of this class representing
   * the contents of the given array of sections, adding it to the
   * given map section.
   *
   * @param sections {@code non-null;} the sections
   * @param mapSection {@code non-null;} the section that the resulting map
   * should be added to; it should be empty on entry to this method
   */
  public static void addMap(Section[] sections, MixedItemSection mapSection) {
    assert sections != null;

    if (mapSection.items().size() != 0) {
      throw new IllegalArgumentException("mapSection.items().size() != 0");
    }

    ArrayList<MapItem> items = new ArrayList<MapItem>(50);

    for (Section section : sections) {
      ItemType currentType = null;
      Item firstItem = null;
      int count = 0;

      for (Item item : section.items()) {
        ItemType type = item.itemType();
        if (type != currentType) {
          if (count != 0) {
            items.add(new MapItem(currentType, section, firstItem, count));
          }
          currentType = type;
          firstItem = item;
          count = 0;
        }
        count++;
      }

      if (count != 0) {
        // Add a MapItem for the final items in the section.
        items.add(new MapItem(currentType, section, firstItem, count));
      } else if (section == mapSection) {
        // Add a MapItem for the self-referential section.
        items.add(new MapItem(mapSection));
      }
    }

    mapSection.add(new UniformListItem<MapItem>(ItemType.TYPE_MAP_LIST, items));
  }

  /**
   * Constructs an instance.
   *
   * @param type {@code non-null;} item type this instance covers
   * @param section {@code non-null;} section this instance covers
   * @param firstItem {@code non-null;} first item covered
   * @param itemCount {@code > 0;} count of items covered
   */
  private MapItem(ItemType type, Section section, Item firstItem, int itemCount) {
    super(ALIGNMENT, WRITE_SIZE);
    assert type != null;
    assert section != null;
    assert firstItem != null;
    if (itemCount <= 0) {
      throw new IllegalArgumentException("itemCount <= 0");
    }
    this.type = type;
    this.section = section;
    this.firstItem = firstItem;
    this.itemCount = itemCount;
  }

  /**
   * Constructs a self-referential instance. This instance is meant to
   * represent the section containing the {@code map_list}.
   *
   * @param section {@code non-null;} section this instance covers
   */
  private MapItem(Section section) {
    super(ALIGNMENT, WRITE_SIZE);
    assert section != null;
    this.type = ItemType.TYPE_MAP_LIST;
    this.section = section;
    this.firstItem = null;
    this.itemCount = 1;
  }

  /** {@inheritDoc} */
  @Override
  public ItemType itemType() {
    return ItemType.TYPE_MAP_ITEM;
  }

  /** {@inheritDoc} */
  @Override
  public String toString() {
    StringBuffer sb = new StringBuffer(100);

    sb.append(getClass().getName());
    sb.append('{');
    sb.append(section.toString());
    sb.append(' ');
    sb.append(type.toHuman());
    sb.append('}');

    return sb.toString();
  }

  /** {@inheritDoc} */
  @Override
  public void addContents(DexFile file) {
    // We have nothing to add.
  }

  /** {@inheritDoc} */
  @Override
  public final String toHuman() {
    return toString();
  }

  /** {@inheritDoc} */
  @Override
  protected void writeTo0(DexFile file, AnnotatedOutput out) {
    int value = type.getMapValue();
    int offset;

    if (firstItem == null) {
      offset = section.getFileOffset();
    } else {
      offset = section.getAbsoluteItemOffset(firstItem);
    }

    if (out.annotates()) {
      out.annotate(0, offsetString() + ' ' + type.getTypeName() + " map");
      out.annotate(2, "  type:   " + Hex.u2(value) + " // " + type.toString());
      out.annotate(2, "  unused: 0");
      out.annotate(4, "  size:   " + Hex.u4(itemCount));
      out.annotate(4, "  offset: " + Hex.u4(offset));
    }

    out.writeShort(value);
    out.writeShort(0); // unused
    out.writeInt(itemCount);
    out.writeInt(offset);
  }
}
