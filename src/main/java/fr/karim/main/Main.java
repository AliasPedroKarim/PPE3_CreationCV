/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.karim.main;

import fr.karim.main.utils.user.Utilisateur;
import fr.karim.references.Reference;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

/**
 *
 * @author karim
 */
public class Main {
    
    public static JFrameSoftware jFrameSoftware = null;
    
    public static JFrameConnexion jFrameConnexion = null;
    
    public static void main(String[] args) {
        
        initialize();
        
    }
    
    public static void OpenSoftware(){
        
        jFrameSoftware = new JFrameSoftware();
        
        jFrameSoftware.getjMenuItemLogout().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Utilisateur.getInstance().logout();
                logout();
            }
        });
        
        jFrameSoftware.setTitle(String.format(Reference.TITLE_NAME_SOFTWARE, "PPE 3 Creation Curriculum Vitae"));
        jFrameSoftware.setVisible(true);        
    }
    
    public static void logout(){
        jFrameSoftware.setVisible(false);
        jFrameSoftware = null;
        
        initialize();
    }
    
    public static void initialize(){
        jFrameConnexion = new JFrameConnexion();
        jFrameConnexion.setTitle(String.format(Reference.TITLE_NAME_SOFTWARE, "Connexion"));
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
}
