package com.cg.coordinates.transform;

import com.cg.coordinates.model.NDCPoint;
import com.cg.coordinates.model.NDCScenario;

/**
 * Responsavel unicamente por converter coordenadas NDC entre os
 * dois cenarios definidos na especificacao:
 *
 *   [0,1] x [0,1]   <->   [-1,1] x [-1,1]
 *
 *   ndcx11 = 2 * ndcx01 - 1        ndcx01 = (ndcx11 + 1) / 2
 *   ndcy11 = 2 * ndcy01 - 1        ndcy01 = (ndcy11 + 1) / 2
 */
public class NDCScenarioConverter {

    /**
     * Converte de NDC [0,1] x [0,1] para NDC [-1,1] x [-1,1] (cenario II).
     * @param ndcUnit Ponto no cenario [0,1] x [0,1].
     * @return Ponto equivalente no cenario [-1,1] x [-1,1].
     */
    public NDCPoint toCentered(NDCPoint ndcUnit) {
        if (ndcUnit.getScenario() != NDCScenario.UNIT) {
            throw new IllegalArgumentException("Ponto de entrada deve estar no cenario [0,1] x [0,1].");
        }
        double ndcx11 = 2 * ndcUnit.getNdcx() - 1;
        double ndcy11 = 2 * ndcUnit.getNdcy() - 1;
        return new NDCPoint(ndcx11, ndcy11, NDCScenario.CENTERED);
    }

    /**
     * Converte de NDC [-1,1] x [-1,1] (cenario II) para NDC [0,1] x [0,1].
     * @param ndcCentered Ponto no cenario [-1,1] x [-1,1].
     * @return Ponto equivalente no cenario [0,1] x [0,1].
     */
    public NDCPoint toUnit(NDCPoint ndcCentered) {
        if (ndcCentered.getScenario() != NDCScenario.CENTERED) {
            throw new IllegalArgumentException("Ponto de entrada deve estar no cenario [-1,1] x [-1,1].");
        }
        double ndcx01 = (ndcCentered.getNdcx() + 1) / 2.0;
        double ndcy01 = (ndcCentered.getNdcy() + 1) / 2.0;
        return new NDCPoint(ndcx01, ndcy01, NDCScenario.UNIT);
    }
}
