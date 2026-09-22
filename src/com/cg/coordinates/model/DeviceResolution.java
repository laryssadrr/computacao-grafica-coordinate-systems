package com.cg.coordinates.model;

/**
 * Resolucao do dispositivo: numero de posicoes (pixels)
 * enderecaveis horizontalmente (ndh) e verticalmente (ndv).
 */
public final class DeviceResolution {

    private final int ndh;
    private final int ndv;

    public DeviceResolution(int ndh, int ndv) {
        if (ndh <= 0 || ndv <= 0) {
            throw new IllegalArgumentException("ndh e ndv devem ser positivos.");
        }
        this.ndh = ndh;
        this.ndv = ndv;
    }

    public int getNdh() {
        return ndh;
    }

    public int getNdv() {
        return ndv;
    }

    @Override
    public String toString() {
        return ndh + " x " + ndv;
    }
}
