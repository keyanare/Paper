package com.destroystokyo.paper.event.entity;

import com.google.common.base.Preconditions;
import org.bukkit.entity.Egg;
import org.bukkit.entity.EntityType;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * Called when a thrown egg might hatch.
 * <p>
 * This event fires for all thrown eggs that may hatch, players, dispensers, etc.
 */
@NullMarked
public class ThrownEggHatchEvent extends Event {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Egg egg;
    private boolean hatching;
    private byte numHatches;
    private EntityType hatchType;

    @ApiStatus.Internal
    public ThrownEggHatchEvent(final Egg egg, final boolean hatching, final byte numHatches, final EntityType hatchingType) {
        this.egg = egg;
        this.hatching = hatching;
        this.numHatches = numHatches;
        this.hatchType = hatchingType;
    }

    /**
     * Gets the egg involved in this event.
     *
     * @return the egg involved in this event
     */
    public Egg getEgg() {
        return this.egg;
    }

    /**
     * Gets whether the egg is hatching or not. Will be what the server
     * would've done without interaction.
     *
     * @return boolean Whether the egg is going to hatch or not
     */
    public boolean isHatching() {
        return this.hatching;
    }

    /**
     * Sets whether the egg will hatch or not.
     *
     * @param hatching {@code true} if you want the egg to hatch, {@code false} if you want it
     *                 not to
     */
    public void setHatching(final boolean hatching) {
        this.hatching = hatching;
    }

    /**
     * Get the type of the mob being hatched ({@link EntityType#CHICKEN} by default)
     *
     * @return The type of the mob being hatched by the egg
     */
    public EntityType getHatchingType() {
        return this.hatchType;
    }

    /**
     * Change the type of mob being hatched by the egg
     *
     * @param hatchType The type of the mob being hatched by the egg
     */
    public void setHatchingType(final EntityType hatchType) {
        Preconditions.checkArgument(hatchType.isSpawnable(), "Can't spawn that entity type from an egg!");
        this.hatchType = hatchType;
    }

    /**
     * Get the number of mob hatches from the egg. By default, the number will
     * be the number the server would've done
     * <ul>
     *  <li>7/8 chance of being 0
     *  <li>31/256 ~= 1/8 chance to be 1
     *  <li>1/256 chance to be 4
     * </ul>
     *
     * @return The number of mobs going to be hatched by the egg
     */
    public byte getNumHatches() {
        return this.numHatches;
    }

    /**
     * Change the number of mobs coming out of the hatched egg
     * <p>
     * The boolean hatching will override this number. I.e. If hatching is
     * {@code false}, this number will not matter
     *
     * @param numHatches The number of mobs coming out of the egg
     */
    public void setNumHatches(final byte numHatches) {
        this.numHatches = numHatches;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
