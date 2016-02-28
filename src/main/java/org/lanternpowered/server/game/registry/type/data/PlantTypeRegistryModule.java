/*
 * This file is part of LanternServer, licensed under the MIT License (MIT).
 *
 * Copyright (c) LanternPowered <https://github.com/LanternPowered>
 * Copyright (c) SpongePowered <https://www.spongepowered.org>
 * Copyright (c) Contributors
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
package org.lanternpowered.server.game.registry.type.data;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import org.lanternpowered.server.data.type.LanternPlantType;
import org.spongepowered.api.data.type.PlantType;
import org.spongepowered.api.data.type.PlantTypes;
import org.spongepowered.api.registry.CatalogRegistryModule;
import org.spongepowered.api.registry.util.RegisterCatalog;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

public final class PlantTypeRegistryModule implements CatalogRegistryModule<PlantType> {

    @RegisterCatalog(PlantTypes.class) private final Map<String, PlantType> plantTypes = Maps.newHashMap();

    @Override
    public void registerDefaults() {
        for (LanternPlantType type : LanternPlantType.values()) {
            this.plantTypes.put(type.getId(), type);
            this.plantTypes.put(type.name().toLowerCase(), type);
        }
    }

    @Override
    public Optional<PlantType> getById(String id) {
        return Optional.ofNullable(this.plantTypes.get(checkNotNull(id).toLowerCase()));
    }

    @Override
    public Collection<PlantType> getAll() {
        return ImmutableSet.copyOf(this.plantTypes.values());
    }

}
