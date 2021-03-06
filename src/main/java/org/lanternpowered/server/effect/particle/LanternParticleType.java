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
package org.lanternpowered.server.effect.particle;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableMap;
import org.lanternpowered.server.catalog.PluginCatalogType;
import org.spongepowered.api.effect.particle.ParticleOption;
import org.spongepowered.api.effect.particle.ParticleType;

import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;

public class LanternParticleType extends PluginCatalogType.Base implements ParticleType {

    private final String name;
    private final OptionalInt internalType;
    private final Map<ParticleOption<?>, Object> options;

    public LanternParticleType(String pluginId, String id, String name,
            OptionalInt internalType, Map<ParticleOption<?>, Object> options) {
        super(pluginId, id, name);
        this.options = ImmutableMap.copyOf(options);
        this.internalType = internalType;
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public OptionalInt getInternalType() {
        return this.internalType;
    }

    @Override
    protected MoreObjects.ToStringHelper toStringHelper() {
        return super.toStringHelper()
                .omitNullValues()
                .add("internalType", this.internalType.isPresent() ? this.internalType.getAsInt() : null);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <V> Optional<V> getDefaultOption(ParticleOption<V> option) {
        return Optional.ofNullable((V) this.options.get(option));
    }

    @Override
    public Map<ParticleOption<?>, Object> getDefaultOptions() {
        return this.options;
    }
}
