package com.cg.coordinates.io;

import com.cg.coordinates.model.DeviceResolution;
import com.cg.coordinates.model.NDCScenario;
import com.cg.coordinates.model.WorldPoint;
import com.cg.coordinates.model.WorldWindow;

import java.util.Scanner;

/**
 * Responsavel unicamente por coletar, via terminal, todas as entradas do usuário necessarias
 */
public class ConsoleInputReader {

    private static final int DEFAULT_NDH = 800;
    private static final int DEFAULT_NDV = 600;

    private final Scanner scanner;

    public ConsoleInputReader(Scanner scanner) {
        this.scanner = scanner;
    }

    public WorldWindow lerJanelaDoMundo() {
        System.out.println("=== Janela do mundo ===");
        double xmin = lerDouble("xmin: ");
        double xmax = lerDoubleMaiorQue("xmax: ", xmin, "xmax deve ser maior que xmin. Informe novamente.");
        double ymin = lerDouble("ymin: ");
        double ymax = lerDoubleMaiorQue("ymax: ", ymin, "ymax deve ser maior que ymin. Informe novamente.");
        return new WorldWindow(xmin, xmax, ymin, ymax);
    }

    public WorldPoint lerPontoDoMundo(WorldWindow window) {
        System.out.println("=== Ponto no mundo ===");
        while (true) {
            double x = lerDouble("x: ");
            double y = lerDouble("y: ");
            if (window.contains(x, y)) {
                return new WorldPoint(x, y);
            }
            System.out.printf("Ponto (%.4f, %.4f) fora da janela do mundo %s. Informe novamente.%n",
                    x, y, window);
        }
    }

    public NDCScenario lerCenarioNDC() {
        System.out.println("=== Cenario de NDC ===");
        System.out.println("1) [0,1] x [0,1]");
        System.out.println("2) [-1,1] x [-1,1] (centrado na origem)");
        while (true) {
            System.out.print("Escolha (1 ou 2): ");
            String linha = scanner.nextLine().trim();
            if (linha.equals("1")) {
                return NDCScenario.UNIT;
            }
            if (linha.equals("2")) {
                return NDCScenario.CENTERED;
            }
            System.out.println("Opcao invalida. Digite 1 ou 2.");
        }
    }

    public DeviceResolution lerResolucaoDispositivo() {
        System.out.println("=== Resolucao do dispositivo ===");
        System.out.printf("ndh (numero de pixels na horizontal) [padrao %d]: ", DEFAULT_NDH);
        int ndh = lerInteiroOuPadrao(DEFAULT_NDH);
        System.out.printf("ndv (numero de pixels na vertical) [padrao %d]: ", DEFAULT_NDV);
        int ndv = lerInteiroOuPadrao(DEFAULT_NDV);
        return new DeviceResolution(ndh, ndv);
    }

    private double lerDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String linha = scanner.nextLine().trim().replace(",", ".");
            try {
                return Double.parseDouble(linha);
            } catch (NumberFormatException e) {
                System.out.println("Valor invalido. Digite um numero (ex: 10 ou 10.5).");
            }
        }
    }

    private double lerDoubleMaiorQue(String prompt, double limite, String mensagemErro) {
        while (true) {
            double valor = lerDouble(prompt);
            if (valor > limite) {
                return valor;
            }
            System.out.println(mensagemErro);
        }
    }

    private int lerInteiroOuPadrao(int valorPadrao) {
        String linha = scanner.nextLine().trim();
        if (linha.isEmpty()) {
            return valorPadrao;
        }
        try {
            int valor = Integer.parseInt(linha);
            if (valor <= 0) {
                System.out.println("Valor deve ser positivo. Usando padrao " + valorPadrao + ".");
                return valorPadrao;
            }
            return valor;
        } catch (NumberFormatException e) {
            System.out.println("Valor invalido. Usando padrao " + valorPadrao + ".");
            return valorPadrao;
        }
    }
}
