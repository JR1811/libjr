package net.shirojr.libjr.misc;

import net.minecraft.util.math.MathHelper;

import java.util.Optional;

public interface TickHandler {
    int getTick();

    void setTick(int tick);

    default void incrementTick() {
        this.incrementTick(1);
    }

    default void incrementTick(int increment) {
        this.setTick(this.getTick() + increment);
    }

    default void resetTick() {
        this.setTick(0);
    }

    default boolean hasMaxTicks() {
        return getMaxTicks() > -1;
    }

    default int getMaxTicks() {
        return -1;
    }

    default Optional<Float> getNormalizedTick() {
        if (!this.hasMaxTicks()) return Optional.empty();
        return Optional.of((float) (this.getTick() / this.getMaxTicks()));
    }

    default Optional<Float> getLerpedTick(int newMin, int newMax) {
        if (newMin >= newMax) return Optional.empty();
        return this.getNormalizedTick().map(normalized -> MathHelper.lerp(normalized, 0f, (float) this.getMaxTicks()));
    }
}
