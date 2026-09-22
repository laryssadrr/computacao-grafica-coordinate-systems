package com.cg.coordinates.model;

/**
 * Agrega o resultado de todas as etapas de transformacao para facilitar na hora de mostrar a saída:
 *   Mundo -> NDC [0,1] -> (opcional) NDC [-1,1] -> Dispositivo
 */
public final class TransformationResult {

    private final WorldPoint worldPoint;
    private final NDCPoint ndcUnit;      // sempre calculado: [0,1] x [0,1]
    private final NDCPoint ndcFinal;     // NDC no cenario escolhido pelo usuario
    private final DevicePoint devicePoint;

    public TransformationResult(WorldPoint worldPoint,
                                 NDCPoint ndcUnit,
                                 NDCPoint ndcFinal,
                                 DevicePoint devicePoint) {
        this.worldPoint = worldPoint;
        this.ndcUnit = ndcUnit;
        this.ndcFinal = ndcFinal;
        this.devicePoint = devicePoint;
    }

    public WorldPoint getWorldPoint() {
        return worldPoint;
    }

    public NDCPoint getNdcUnit() {
        return ndcUnit;
    }

    public NDCPoint getNdcFinal() {
        return ndcFinal;
    }

    public DevicePoint getDevicePoint() {
        return devicePoint;
    }
}
