package com.cg.coordinates.transform;

import com.cg.coordinates.model.DevicePoint;
import com.cg.coordinates.model.DeviceResolution;
import com.cg.coordinates.model.NDCPoint;
import com.cg.coordinates.model.NDCScenario;

/**
 * Responsavel pela transformacao:
 *
 *   NDC [0,1] x [0,1] -> coordenadas do dispositivo
 *
 *   dcx = round(ndcx01 * (ndh - 1))
 *   dcy = round(ndcy01 * (ndv - 1))
 */
public class NDCToDeviceTransformer {

    private final NDCScenarioConverter scenarioConverter = new NDCScenarioConverter();

    /**
     * Transforma um ponto NDC em coordenadas de dispositivo, considerando a resolucao do dispositivo.
     * Se o ponto NDC estiver no cenario [-1,1] x [-1,1], ele sera convertido para [0,1] x [0,1] antes da transformacao.
     * @param ndcPoint o ponto NDC a ser transformado.
     * @param resolution a resolucao do dispositivo.
     * @return o ponto correspondente em coordenadas de dispositivo.
     */
    public DevicePoint transform(NDCPoint ndcPoint, DeviceResolution resolution) {
        NDCPoint ndcUnit = ndcPoint.getScenario() == NDCScenario.CENTERED
                ? scenarioConverter.toUnit(ndcPoint)
                : ndcPoint;

        int dcx = (int) Math.round(ndcUnit.getNdcx() * (resolution.getNdh() - 1));
        int dcy = (int) Math.round(ndcUnit.getNdcy() * (resolution.getNdv() - 1));

        return new DevicePoint(dcx, dcy);
    }
}
