package com.cg.coordinates.model;

/**
 * Coordenada inteira (dcx, dcy) enderecavel pelo dispositivo.
 */
public final class DevicePoint {

    private final int dcx;
    private final int dcy;

    public DevicePoint(int dcx, int dcy) {
        this.dcx = dcx;
        this.dcy = dcy;
    }

    public int getDcx() {
        return dcx;
    }

    public int getDcy() {
        return dcy;
    }

    @Override
    public String toString() {
        return String.format("(%d, %d)", dcx, dcy);
    }
}
