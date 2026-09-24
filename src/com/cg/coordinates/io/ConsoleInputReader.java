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

    /**
     * Le a janela do mundo (xmin, xmax, ymin, ymax) do usuario.
     * @return Janela do mundo informada pelo usuario.
     */
    public WorldWindow lerJanelaDoMundo() {
        System.out.println("=== Janela do mundo ===");
        double xmin = lerDouble("xmin: ");
        double xmax = lerDoubleMaiorQue("xmax: ", xmin, "xmax deve ser maior que xmin. Informe novamente.");
        double ymin = lerDouble("ymin: ");
        double ymax = lerDoubleMaiorQue("ymax: ", ymin, "ymax deve ser maior que ymin. Informe novamente.");
        return new WorldWindow(xmin, xmax, ymin, ymax);
    }

    /**
     * Le um ponto (x, y) do mundo do usuario, garantindo que o ponto esteja dentro da janela do mundo.
     * @param window Janela do mundo dentro da qual o ponto deve estar.
     * @return Ponto do mundo informado pelo usuario.
     */
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

    /**
     * Le o cenario de NDC escolhido pelo usuario.
     * @return Cenario de NDC escolhido pelo usuario.
     */
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

    /**
     * Le a resolucao do dispositivo (ndh, ndv) do usuario, com valores padrao caso o usuario nao informe nada.
     * @return Resolucao do dispositivo informada pelo usuario, ou valores padrao caso o usuario nao informe nada.
     */
    public DeviceResolution lerResolucaoDispositivo() {
        System.out.println("=== Resolucao do dispositivo ===");
        System.out.printf("ndh (numero de pixels na horizontal) [padrao %d]: ", DEFAULT_NDH);
        int ndh = lerInteiroOuPadrao(DEFAULT_NDH);
        System.out.printf("ndv (numero de pixels na vertical) [padrao %d]: ", DEFAULT_NDV);
        int ndv = lerInteiroOuPadrao(DEFAULT_NDV);
        return new DeviceResolution(ndh, ndv);
    }

    /**
     * Le um valor double do usuario, tratando erros de formato e pedindo para o usuario
     * digitar novamente caso o valor seja invalido.
     * @param prompt Mensagem exibida ao usuario antes da entrada.
     * @return Valor double informado pelo usuario.
     */
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

    /**
     * Le um valor double do usuario, garantindo que o valor seja maior que o limite informado.
     * @param prompt Mensagem exibida ao usuario antes da entrada.
     * @param limite Limite inferior que o valor deve ultrapassar.
     * @param mensagemErro Mensagem exibida ao usuario caso o valor informado seja menor ou igual ao limite.
     * @return Valor double informado pelo usuario, maior que o limite.
     */
    private double lerDoubleMaiorQue(String prompt, double limite, String mensagemErro) {
        while (true) {
            double valor = lerDouble(prompt);
            if (valor > limite) {
                return valor;
            }
            System.out.println(mensagemErro);
        }
    }

    /**
     * Le um valor inteiro do usuario, retornando um valor padrao caso o usuario nao informe nada ou informe um valor invalido.
     * @param valorPadrao Valor padrao a ser retornado caso o usuario nao informe nada ou informe um valor invalido.
     * @return Valor inteiro informado pelo usuario, ou o valor padrao caso o usuario nao informe nada ou informe um valor invalido.
     */
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
