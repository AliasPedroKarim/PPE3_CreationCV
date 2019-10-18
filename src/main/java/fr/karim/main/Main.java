/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.karim.main;

import fr.karim.main.utils.Utilisateur;
import java.awt.event.MouseAdapter;

/**
 *
 * @author karim
 */
public class Main {
    
    public static void main(String[] args) {
        
        JFrameConnexion jFrameConnexion = new JFrameConnexion();
        jFrameConnexion.setTitle("Connexion - CV Creator");
        jFrameConnexion.setVisible(true);
        
        jFrameConnexion.getHome().getConnexion().getjButtonConnexion().addMouseListener(new MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println(Utilisateur.getInstance().getEstConnecte());
            }
        });
        
    }
}
