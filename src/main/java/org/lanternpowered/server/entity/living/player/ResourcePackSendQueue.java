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
package org.lanternpowered.server.entity.living.player;

import static com.google.common.base.Preconditions.checkNotNull;

import org.lanternpowered.server.network.vanilla.message.type.play.MessagePlayOutSendResourcePack;
import org.spongepowered.api.resourcepack.ResourcePack;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.Nullable;

public final class ResourcePackSendQueue {

    private final List<ResourcePack> queue = new ArrayList<>();
    @Nullable private ResourcePack waitingForResponse;
    private final LanternPlayer player;

    ResourcePackSendQueue(LanternPlayer player) {
        this.player = player;
    }

    public void offer(ResourcePack resourcePack) {
        checkNotNull(resourcePack, "resourcePack");
        synchronized (this.queue) {
            if (this.waitingForResponse == null) {
                this.send(resourcePack);
            } else {
                this.queue.add(resourcePack);
            }
        }
    }

    public Optional<ResourcePack> poll() {
        synchronized (this.queue) {
            final ResourcePack resourcePack = this.waitingForResponse;
            if (!this.queue.isEmpty()) {
                this.send(this.queue.remove(0));
            } else {
                this.waitingForResponse = null;
            }
            return Optional.ofNullable(resourcePack);
        }
    }

    private void send(ResourcePack resourcePack) {
        this.waitingForResponse = resourcePack;
        final String hash = resourcePack.getHash().orElse(resourcePack.getId());
        final String location = resourcePack.getUri().toString();
        this.player.getConnection().send(new MessagePlayOutSendResourcePack(location, hash));
    }
}
