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
    
    public static JFrameSoftware jFrameSoftware = null;
    
    public static JFrameConnexion jFrameConnexion = null;
    
    public static void main(String[] args) {
        
        jFrameConnexion = new JFrameConnexion();
        jFrameConnexion.setTitle("Connexion - CV Creator Software");
        jFrameConnexion.setVisible(true);
        
        jFrameConnexion.getHome().getConnexion().getjButtonConnexion().addMouseListener(new MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if(Utilisateur.getInstance().getEstConnecte()){
                    jFrameConnexion.setVisible(false);
                    jFrameConnexion = null;
                    OpenSoftware();
                }
                
            }
        });
        
    }
    
    public static void OpenSoftware(){
        
        jFrameSoftware = new JFrameSoftware();
        jFrameSoftware.setTitle("PPE 3 Creation Curriculum Vitae - CV Creator Software");
        jFrameSoftware.setVisible(true);
        
    }
}
