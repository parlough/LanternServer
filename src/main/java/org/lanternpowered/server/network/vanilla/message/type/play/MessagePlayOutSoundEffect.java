package org.lanternpowered.server.network.vanilla.message.type.play;

import static com.google.common.base.Preconditions.checkNotNull;

import org.lanternpowered.server.network.message.Message;

import com.flowpowered.math.vector.Vector3d;

public final class MessagePlayOutSoundEffect implements Message {

    private final String name;
    private final Vector3d position;

    private final float volume;
    private final float pitch;

    /**
     * Creates a new sound effect message.
     * 
     * @param name The effect name
     * @param x The x coordinate
     * @param y The y coordinate
     * @param z The z coordinate
     * @param volume The volume
     * @param pitch The pitch value
     */
    public MessagePlayOutSoundEffect(String name, Vector3d position, float volume, float pitch) {
        this.position = checkNotNull(position, "position");
        this.name = checkNotNull(name, "name");
        this.volume = volume;
        this.pitch = pitch;
    }

    /**
     * Gets the volume of the sound effect.
     * 
     * @return The volume
     */
    public float getVolume() {
        return this.volume;
    }

    /**
     * Gets the pitch of the sound effect.
     * 
     * @return The pitch value
     */
    public float getPitch() {
        return this.pitch;
    }

    /**
     * Gets the name of the sound effect.
     * 
     * @return The name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the position of the effect.
     * 
     * @return The position
     */
    public Vector3d getPosition() {
        return this.position;
    }

}
