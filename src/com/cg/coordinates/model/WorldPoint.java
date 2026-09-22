package com.cg.coordinates.model;

/**
 * Ponto (x, y) no sistema de coordenadas do mundo.
 */
public final class WorldPoint {

    private final double x;
    private final double y;

    public WorldPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return String.format("(%.4f, %.4f)", x, y);
    }
}
