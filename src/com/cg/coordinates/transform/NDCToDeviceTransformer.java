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

    public DevicePoint transform(NDCPoint ndcPoint, DeviceResolution resolution) {
        NDCPoint ndcUnit = ndcPoint.getScenario() == NDCScenario.CENTERED
                ? scenarioConverter.toUnit(ndcPoint)
                : ndcPoint;

        int dcx = (int) Math.round(ndcUnit.getNdcx() * (resolution.getNdh() - 1));
        int dcy = (int) Math.round(ndcUnit.getNdcy() * (resolution.getNdv() - 1));

        return new DevicePoint(dcx, dcy);
    }
}
