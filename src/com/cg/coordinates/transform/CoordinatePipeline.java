package com.cg.coordinates.transform;

import com.cg.coordinates.model.DevicePoint;
import com.cg.coordinates.model.DeviceResolution;
import com.cg.coordinates.model.NDCPoint;
import com.cg.coordinates.model.NDCScenario;
import com.cg.coordinates.model.TransformationResult;
import com.cg.coordinates.model.WorldPoint;
import com.cg.coordinates.model.WorldWindow;

/**
 * Orquestra todas as transformacoes de coordenadas:
 *
 *   Mundo -> NDC [0,1] x [0,1] -> (opcional) NDC [-1,1] x [-1,1] -> Dispositivo
 */
public class CoordinatePipeline {

    private final WorldToNDCTransformer worldToNDCTransformer = new WorldToNDCTransformer();
    private final NDCScenarioConverter ndcScenarioConverter = new NDCScenarioConverter();
    private final NDCToDeviceTransformer ndcToDeviceTransformer = new NDCToDeviceTransformer();

    /**
     * Delega a execução de todas as transformacoes de coordenadas, retornando um objeto
     * contendo todos os pontos intermediarios.
     *
     * @param window Janela do mundo.
     * @param point Ponto (x, y) no sistema de coordenadas do mundo.
     * @param scenario Cenário ([0,1] x [0,1] ou [-1,1] x [-1,1]) escolhido pelo usuário.
     * @param resolution Resolucao do dispositivo.
     * @return TransformationResult contendo todos os pontos intermediarios da transformacao.
     */
    public TransformationResult execute(WorldWindow window,
                                         WorldPoint point,
                                         NDCScenario scenario,
                                         DeviceResolution resolution) {

        // 1) Mundo -> NDC [0,1] x [0,1]
        NDCPoint ndcUnit = worldToNDCTransformer.transform(window, point);

        // 2) Caso o cenario seja [-1,1] x [-1,1], realiza a conversao adicional.
        NDCPoint ndcFinal = (scenario == NDCScenario.CENTERED)
                ? ndcScenarioConverter.toCentered(ndcUnit)
                : ndcUnit;

        // 3) NDC -> Dispositivo (a propria classe cuida de voltar para [0,1] se necessario)
        DevicePoint devicePoint = ndcToDeviceTransformer.transform(ndcFinal, resolution);

        return new TransformationResult(point, ndcUnit, ndcFinal, devicePoint);
    }
}
