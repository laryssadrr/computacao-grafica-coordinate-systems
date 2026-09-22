package com.cg.coordinates.view;

import com.cg.coordinates.model.DevicePoint;
import com.cg.coordinates.model.DeviceResolution;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

/**
 * Area de visualizacao que representa o display do dispositivo.
 * Acende exatamente um pixel verde (#00FF00) na coordenada (dcx, dcy).
 */
public class DeviceDisplayPanel extends JPanel {

    private static final Color PIXEL_COLOR = Color.GREEN;

    private final DeviceResolution resolution;
    private DevicePoint activePixel;

    public DeviceDisplayPanel(DeviceResolution resolution, DevicePoint activePixel) {
        this.resolution = resolution;
        this.activePixel = activePixel;
        setPreferredSize(new Dimension(resolution.getNdh(), resolution.getNdv()));
        //setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawPixel(g, activePixel);
    }

    /**
     * Pinta um unico pixel na cor verde na coordenada (dcx, dcy) do dispositivo.
     */
    private void drawPixel(Graphics g, DevicePoint point) {
        if (point == null) {
            return;
        }
        g.setColor(PIXEL_COLOR);
        g.fillRect(point.getDcx(), point.getDcy(), 1, 1);
    }

    /**
     * Define a coordenada do pixel ativo e solicita a sua repintura
     */
    public void setPixel(DevicePoint devicePoint) {
        this.activePixel = devicePoint;
        repaint();
    }

    public DeviceResolution getResolution() {
        return resolution;
    }
}