/*
 * This file is part of LanternServer, licensed under the MIT License (MIT).
 *
 * Copyright (c) LanternPowered <https://www.lanternpowered.org>
 * Copyright (c) SpongePowered <https://www.spongepowered.org>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the Software), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED AS IS, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.lanternpowered.server.item.property;

import static com.google.common.base.Preconditions.checkNotNull;

import org.lanternpowered.server.data.type.record.LanternRecordType;
import org.lanternpowered.server.data.type.record.RecordType;
import org.spongepowered.api.data.Property;
import org.spongepowered.api.data.property.AbstractProperty;

import javax.annotation.Nullable;

public final class RecordProperty extends AbstractProperty<String, RecordType> {

    public RecordProperty(RecordType value) {
        super(checkNotNull(value, "value"));
    }

    @Override
    public int compareTo(@Nullable Property<?, ?> o) {
        final LanternRecordType type0 = (LanternRecordType) getValue();
        final LanternRecordType type1 = o == null ? null : (LanternRecordType) o.getValue();
        return Integer.compare(
                type0 == null ? 0 : type0.getInternalId() + 1,
                type1 == null ? 0 : type1.getInternalId() + 1);
    }
}
