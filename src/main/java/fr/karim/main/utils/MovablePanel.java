/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.karim.main.utils;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author karim
 */
public class MovablePanel {
    private JPanel panel;
    private int xx=0;
    private int yy=0;

    public MovablePanel(JPanel panel, JFrame jFrame) {
        
        this.setPanel(panel);
        this.panel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) { }

            @Override
            public void mousePressed(MouseEvent e) { 
                xx = e.getX();
                yy = e.getY();  
            }

            @Override
            public void mouseReleased(MouseEvent e) { }

            @Override
            public void mouseEntered(MouseEvent e) { }

            @Override
            public void mouseExited(MouseEvent e) { }
        });
        
        this.panel.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int x=e.getXOnScreen();
                int y=e.getYOnScreen();
                jFrame.setLocation(x-xx, y-yy);
            }

            @Override
            public void mouseMoved(MouseEvent e) { }
        });
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }
    
    
}
