package com.cg.coordinates.model;

/**
 * Ponto (ndcx, ndcy) em coordenadas normalizadas do dispositivo.
 *
 * O mesmo tipo e usado tanto para o cenario [0,1] x [0,1] quanto
 * para o cenario [-1,1] x [-1,1]; o campo "scenario" identifica
 * a qual dos dois cenarios os valores armazenados pertencem.
 */
public final class NDCPoint {

    private final double ndcx;
    private final double ndcy;
    private final NDCScenario scenario;

    public NDCPoint(double ndcx, double ndcy, NDCScenario scenario) {
        this.ndcx = ndcx;
        this.ndcy = ndcy;
        this.scenario = scenario;
    }

    public double getNdcx() {
        return ndcx;
    }

    public double getNdcy() {
        return ndcy;
    }

    public NDCScenario getScenario() {
        return scenario;
    }

    @Override
    public String toString() {
        return String.format("(%.4f, %.4f) %s", ndcx, ndcy, scenario.getDescricao());
    }
}
