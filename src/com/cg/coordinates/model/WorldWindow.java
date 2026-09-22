package com.cg.coordinates.model;

/**
 * Representa a janela do mundo, definida pelos limites (xmin, xmax) e (ymin, ymax)
 */
public final class WorldWindow {

    private final double xmin;
    private final double xmax;
    private final double ymin;
    private final double ymax;

    public WorldWindow(double xmin, double xmax, double ymin, double ymax) {
        if (xmax <= xmin) {
            throw new IllegalArgumentException("xmax deve ser maior que xmin.");
        }
        if (ymax <= ymin) {
            throw new IllegalArgumentException("ymax deve ser maior que ymin.");
        }
        this.xmin = xmin;
        this.xmax = xmax;
        this.ymin = ymin;
        this.ymax = ymax;
    }

    public double getXmin() {
        return xmin;
    }

    public double getXmax() {
        return xmax;
    }

    public double getYmin() {
        return ymin;
    }

    public double getYmax() {
        return ymax;
    }

    public double getWidth() {
        return xmax - xmin;
    }

    public double getHeight() {
        return ymax - ymin;
    }

    /**
     * Verifica se um ponto (x, y) esta dentro dos limites da janela do mundo.
     */
    public boolean contains(double x, double y) {
        return x >= xmin && x <= xmax && y >= ymin && y <= ymax;
    }

    @Override
    public String toString() {
        return String.format("WorldWindow[xmin=%.4f, xmax=%.4f, ymin=%.4f, ymax=%.4f]",
                xmin, xmax, ymin, ymax);
    }
}
