package com.cg.coordinates;

import com.cg.coordinates.io.ConsoleInputReader;
import com.cg.coordinates.model.DeviceResolution;
import com.cg.coordinates.model.NDCScenario;
import com.cg.coordinates.model.TransformationResult;
import com.cg.coordinates.model.WorldPoint;
import com.cg.coordinates.model.WorldWindow;
import com.cg.coordinates.transform.CoordinatePipeline;
import com.cg.coordinates.view.DeviceDisplayFrame;

import javax.swing.SwingUtilities;
import java.util.Scanner;

/**
 * Ponto de entrada da aplicacao.
 *
 * Fluxo:
 *   1. Le a janela do mundo (xmin, xmax, ymin, ymax).
 *   2. Le o ponto (x, y) no mundo.
 *   3. Le o cenario de NDC escolhido.
 *   4. Le a resolucao do dispositivo (ndh, ndv).
 *   5. Executa as transformacoes Mundo -> NDC -> Dispositivo.
 *   6. Exibe o resultado no terminal.
 *   7. Abre a janela com o pixel verde na posicao calculada.
 */
public final class Main {

    private Main() {
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConsoleInputReader inputReader = new ConsoleInputReader(scanner);

        WorldWindow window = inputReader.lerJanelaDoMundo();
        WorldPoint point = inputReader.lerPontoDoMundo(window);
        NDCScenario scenario = inputReader.lerCenarioNDC();
        DeviceResolution resolution = inputReader.lerResolucaoDispositivo();

        CoordinatePipeline pipeline = new CoordinatePipeline();
        TransformationResult result = pipeline.execute(window, point, scenario, resolution);

        exibirResultadoNoTerminal(result);

        SwingUtilities.invokeLater(() -> {
            DeviceDisplayFrame frame = new DeviceDisplayFrame(resolution, result.getDevicePoint());
            frame.setVisible(true);
        });
    }

    private static void exibirResultadoNoTerminal(TransformationResult result) {
        System.out.println();
        System.out.println("Coordenada no mundo: " + result.getWorldPoint());
        System.out.println("Coordenada NDC: " + result.getNdcFinal());
        System.out.println("Coordenada do dispositivo: " + result.getDevicePoint());
    }
}
