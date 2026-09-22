package com.cg.coordinates.view;

import com.cg.coordinates.model.DevicePoint;
import com.cg.coordinates.model.DeviceResolution;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;

/**
 * Janela (JFrame) que hospeda o display do dispositivo.
 *
 * Responsabilidade: montar a janela Swing/AWT em torno do DeviceDisplayPanel,
 * sem conhecer detalhes de como as coordenadas foram calculadas.
 */
public class DeviceDisplayFrame extends JFrame {

    private final DeviceDisplayPanel displayPanel;

    public DeviceDisplayFrame(DeviceResolution resolution, DevicePoint activePixel) {
        super("Dispositivo Grafico - Pixel Ativo");
        this.displayPanel = new DeviceDisplayPanel(resolution, activePixel);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        add(displayPanel, BorderLayout.CENTER);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
    }

    public DeviceDisplayPanel getDisplayPanel() {
        return displayPanel;
    }
}
