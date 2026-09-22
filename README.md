# Sistemas de Coordenadas – Computação Gráfica

Projeto Java (Swing/AWT) que implementa as transformações de coordenadas:

    Mundo -> NDC [0,1]x[0,1] -> (opcional) NDC [-1,1]x[-1,1] -> Dispositivo

## Fluxo de uso

1. Informe a janela do mundo: `xmin`, `xmax`, `ymin`, `ymax`.
2. Informe o ponto `(x, y)` no mundo (deve estar dentro da janela).
3. Escolha o cenário de NDC: `1` para [0,1]x[0,1] ou `2` para [-1,1]x[-1,1].
4. Informe a resolução do dispositivo `ndh` e `ndv` (ou pressione Enter para usar o padrão 800x600).
5. O programa exibe no terminal as coordenadas em cada etapa (mundo, NDC, dispositivo)
   e abre uma janela Swing mostrando o pixel verde (#00FF00) na posição calculada,
   com fundo preto e sem grades/eixos.

## Organização dos pacotes

- `com.cg.coordinates.model`      – Entidades: WorldWindow, WorldPoint, NDCPoint, DevicePoint,
                                     DeviceResolution, NDCScenario, TransformationResult.
- `com.cg.coordinates.transform`  – Transformações isoladas por responsabilidade:
                                     WorldToNDCTransformer, NDCScenarioConverter,
                                     NDCToDeviceTransformer, e o orquestrador CoordinatePipeline.
- `com.cg.coordinates.io`         – Leitura de entradas do usuário via terminal (ConsoleInputReader).
- `com.cg.coordinates.view`       – Interface gráfica Swing/AWT (DeviceDisplayPanel, DeviceDisplayFrame).
- `com.cg.coordinates.Main`       – Ponto de entrada que orquestra todo o fluxo.
