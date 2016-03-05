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
package org.lanternpowered.server.effect.potion;

import static com.google.common.base.Preconditions.checkNotNull;

import org.lanternpowered.server.data.property.AbstractPropertyHolder;
import org.lanternpowered.server.data.util.DataQueries;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.MemoryDataContainer;
import org.spongepowered.api.data.Queries;
import org.spongepowered.api.effect.potion.PotionEffect;
import org.spongepowered.api.effect.potion.PotionEffectType;

public class LanternPotionEffect implements PotionEffect, AbstractPropertyHolder {

    private final PotionEffectType effectType;
    private final int duration;
    private final int amplifier;
    private final boolean ambient;
    private final boolean showParticles;

    public LanternPotionEffect(PotionEffectType effectType, int duration, int amplifier, boolean ambient, boolean showParticles) {
        this.effectType = checkNotNull(effectType, "effectType");
        this.showParticles = showParticles;
        this.duration = duration;
        this.amplifier = amplifier;
        this.ambient = ambient;
    }

    @Override
    public PotionEffectType getType() {
        return this.effectType;
    }

    @Override
    public int getDuration() {
        return this.duration;
    }

    @Override
    public int getAmplifier() {
        return this.amplifier;
    }

    @Override
    public boolean isAmbient() {
        return this.ambient;
    }

    @Override
    public boolean getShowParticles() {
        return this.showParticles;
    }

    @Override
    public int getContentVersion() {
        return 1;
    }

    @Override
    public DataContainer toContainer() {
        return new MemoryDataContainer()
                .set(Queries.CONTENT_VERSION, getContentVersion())
                .set(DataQueries.POTION_TYPE, this.effectType.getId())
                .set(DataQueries.POTION_DURATION, this.duration)
                .set(DataQueries.POTION_AMPLIFIER, this.amplifier)
                .set(DataQueries.POTION_AMBIANCE, this.ambient)
                .set(DataQueries.POTION_SHOWS_PARTICLES, this.showParticles);
    }
}