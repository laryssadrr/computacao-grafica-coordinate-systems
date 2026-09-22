package com.cg.coordinates.transform;

import com.cg.coordinates.model.NDCPoint;
import com.cg.coordinates.model.NDCScenario;
import com.cg.coordinates.model.WorldPoint;
import com.cg.coordinates.model.WorldWindow;

/**
 * Responsavel pela transformacao:
 *
 *   Sistema de coordenadas do mundo -> NDC [0,1] x [0,1]
 *
 *   ndcx01 = (x - xmin) / (xmax - xmin)
 *   ndcy01 = (y - ymin) / (ymax - ymin)
 */
public class WorldToNDCTransformer {

    public NDCPoint transform(WorldWindow window, WorldPoint point) {
        double ndcx01 = (point.getX() - window.getXmin()) / window.getWidth();
        double ndcy01 = (point.getY() - window.getYmin()) / window.getHeight();
        return new NDCPoint(ndcx01, ndcy01, NDCScenario.UNIT);
    }
}
