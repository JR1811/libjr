package net.shirojr.libjr.color;

import net.minecraft.util.math.MathHelper;

public class ArgbUtil {
    private final int argb;

    public ArgbUtil(int argb) {
        this.argb = argb;
    }

    public ArgbUtil(float normalizedAlpha, float normalizedRed, float normalizedGreen, float normalizedBlue) {
        int alpha = fullColorValue(normalizedAlpha);
        int red = fullColorValue(normalizedRed);
        int green = fullColorValue(normalizedGreen);
        int blue = fullColorValue(normalizedBlue);
        this.argb = (alpha << 24) | (red << 16) | (green << 8) | blue;
    }

    public int getArgb() {
        return argb;
    }

    int getAlpha() {
        return (argb >> 24) & 0xFF;
    }

    public ArgbUtil setAlpha(float normalized) {
        normalized = MathHelper.clamp(normalized, 0f, 1f);
        return new ArgbUtil(
                normalized,
                ArgbUtil.normalized(getRed()),
                ArgbUtil.normalized(getGreen()),
                ArgbUtil.normalized(getBlue())
        );
    }

    public int getRed() {
        return (argb >> 16) & 0xFF;
    }

    public ArgbUtil setRed(float normalized) {
        normalized = MathHelper.clamp(normalized, 0f, 1f);
        return new ArgbUtil(
                ArgbUtil.normalized(getAlpha()),
                normalized,
                ArgbUtil.normalized(getGreen()),
                ArgbUtil.normalized(getBlue())
        );
    }

    public int getGreen() {
        return (argb >> 8) & 0xFF;
    }

    public ArgbUtil setGreen(float normalized) {
        normalized = MathHelper.clamp(normalized, 0f, 1f);
        return new ArgbUtil(
                ArgbUtil.normalized(getAlpha()),
                ArgbUtil.normalized(getRed()),
                normalized,
                ArgbUtil.normalized(getBlue())
        );
    }

    public int getBlue() {
        return argb & 0xFF;
    }

    public ArgbUtil setBlue(float normalized) {
        normalized = MathHelper.clamp(normalized, 0f, 1f);
        return new ArgbUtil(
                ArgbUtil.normalized(getAlpha()),
                ArgbUtil.normalized(getRed()),
                ArgbUtil.normalized(getGreen()),
                normalized
        );
    }

    public ArgbUtil setBrightness(float factor) {
        factor = MathHelper.clamp(factor, 0f, 1f);
        int red = (int) Math.clamp(getRed() * factor, 0, 255);
        int green = (int) Math.clamp(getGreen() * factor, 0, 255);
        int blue = (int) Math.clamp(getBlue() * factor, 0, 255);

        return new ArgbUtil(
                ArgbUtil.normalized(getAlpha()),
                ArgbUtil.normalized(red),
                ArgbUtil.normalized(green),
                ArgbUtil.normalized(blue)
        );
    }

    public static float normalized(int color) {
        return color / 255f;
    }

    public static int fullColorValue(float normalized) {
        return Math.round(normalized * 255);
    }

    public static int getARGB(int alpha, int red, int green, int blue) {
        return (alpha << 24) | (red << 16) | (green << 8) | blue;
    }
}
