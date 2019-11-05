/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.karim.main.composant;

import fr.karim.main.utils.Helpers;
import fr.karim.main.utils.Utilisateur;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author karim
 */
public class Software extends javax.swing.JPanel {

    DefaultTableModel listeCV;
    
    /**
     * Creates new form Software
     */
    public Software() {
        initComponents();
        
        listeCV = (DefaultTableModel) jTable1.getModel();
        listeCV.setColumnIdentifiers(new String[]{ "id", "titre", "appartient" });
        
        loadListCV();
        
        creationDuCV1.setIdUtilisateur(Utilisateur.getInstance().getId());
        
        init();
        handlePanel();
    }

    public void init(){
        
        creationDuCV1.getjButtonValidationCV().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loadListCV();
            }

            @Override
            public void mousePressed(MouseEvent e) { }

            @Override
            public void mouseReleased(MouseEvent e) { }

            @Override
            public void mouseEntered(MouseEvent e) { }

            @Override
            public void mouseExited(MouseEvent e) { }
        });
        
    }
    
    public void handlePanel(){
        this.handlePanel(null);
    }
    
    public void handlePanel(String name){
        if(name != null){
            if(name.equals("panel_acceuil")){
                jPanelAccueil.setVisible(true);
            }else{
                jPanelAccueil.setVisible(false);
            }

            if(name.equals("panel_list_cv")){
                jPanelListCV.setVisible(true);
            }else{
                jPanelListCV.setVisible(false);
            }

            if(name.equals("panel_create_cv")){
                creationDuCV1.setVisible(true);
            }else{
                creationDuCV1.setVisible(false);
            }

            if(name.equals("panel_import_export")){
                importExportFile1.setVisible(true);
            }else{
                importExportFile1.setVisible(false);
            }
        }else{
            jPanelAccueil.setVisible(true);
            jPanelListCV.setVisible(false);
            creationDuCV1.setVisible(false);
            importExportFile1.setVisible(false);
        }
    }
    
    public void loadListCV(){
        
        List<Map<String, Object>> CVs = null;
        
        if(Utilisateur.getInstance().getEstConnecte()){
            
            for(int i = listeCV.getRowCount() - 1; i >=0; i--) { listeCV.removeRow(i);  }
            
            try {
                CVs = Helpers.getIntance().getCV(Utilisateur.getInstance().getId());
            } catch (SQLException ex) {
                Logger.getLogger(Software.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if(CVs != null && CVs.size() > 0){
                for(Map<String, Object> CV : CVs){
                    
                    listeCV.addRow(new Object []{ CV.get("id"), CV.get("titre"), CV.get("signature") });
                    
                }
            }
        }
        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanelAccueil = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanelListCV = new javax.swing.JPanel();
        jButtonAjouter = new javax.swing.JButton();
        jButtonModifier = new javax.swing.JButton();
        jButtonSupprimer = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        creationDuCV1 = new com.karimandco.cv.CreationDuCV();
        importExportFile1 = new fr.karim.main.composant.ImportExportFile();

        jPanelAccueil.setBackground(new java.awt.Color(78, 39, 123));

        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        jLabel1.setText("Accueil");

        javax.swing.GroupLayout jPanelAccueilLayout = new javax.swing.GroupLayout(jPanelAccueil);
        jPanelAccueil.setLayout(jPanelAccueilLayout);
        jPanelAccueilLayout.setHorizontalGroup(
            jPanelAccueilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAccueilLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelAccueilLayout.setVerticalGroup(
            jPanelAccueilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAccueilLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(84, Short.MAX_VALUE))
        );

        jButtonAjouter.setText("Ajouter");
        jButtonAjouter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonAjouterMouseClicked(evt);
            }
        });

        jButtonModifier.setText("Modifier");
        jButtonModifier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonModifierMouseClicked(evt);
            }
        });

        jButtonSupprimer.setText("Supprimer");
        jButtonSupprimer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonSupprimerMouseClicked(evt);
            }
        });

        jTable1.setModel(new DefaultTableModel());
        jScrollPane2.setViewportView(jTable1);

        javax.swing.GroupLayout jPanelListCVLayout = new javax.swing.GroupLayout(jPanelListCV);
        jPanelListCV.setLayout(jPanelListCVLayout);
        jPanelListCVLayout.setHorizontalGroup(
            jPanelListCVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelListCVLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonSupprimer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonModifier)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonAjouter)
                .addContainerGap())
            .addComponent(jScrollPane2)
        );
        jPanelListCVLayout.setVerticalGroup(
            jPanelListCVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelListCVLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 451, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanelListCVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAjouter)
                    .addComponent(jButtonModifier)
                    .addComponent(jButtonSupprimer))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(creationDuCV1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(importExportFile1, javax.swing.GroupLayout.PREFERRED_SIZE, 1119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanelListCV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanelAccueil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelAccueil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jPanelListCV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(creationDuCV1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(importExportFile1, javax.swing.GroupLayout.PREFERRED_SIZE, 632, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonModifierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonModifierMouseClicked
        int[] selectedRows = jTable1.getSelectedRows();
        
        if(selectedRows != null && selectedRows.length > 0){
            creationDuCV1.setIdCV((Integer) listeCV.getValueAt(selectedRows[0], 0));
            jTabbedPane1.setSelectedIndex(1);
        }else{
            JOptionPane.showMessageDialog(this, "Veuillez selectionné un Curriculum Vitae dans la liste pour pouvoir le modifié.", "Attention ! Selection", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jButtonModifierMouseClicked

    private void jButtonSupprimerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonSupprimerMouseClicked
        boolean ok = true;
        int reply = JOptionPane.showConfirmDialog(null, "Etes-vous sûr de vouloir supprimer les Curriculum Vitae selectionnés ?", "Comfimez la suppression !", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            int[] selectedRows = jTable1.getSelectedRows();
        
            if(selectedRows != null && selectedRows.length > 0){
                for(int i : selectedRows){
                    try {
                        if(!Helpers.getIntance().supprimerToutCV((Integer) listeCV.getValueAt(i, 0))){
                            ok = false;
                            break;
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(Software.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if(i == selectedRows.length){
                        JOptionPane.showMessageDialog(this, "Tous les CVs selectionnés ont était supprimé.", "Attention ! Selection", JOptionPane.WARNING_MESSAGE);
                    }
                }
                if(!ok){
                    JOptionPane.showMessageDialog(this, "Une erreur s'est produit lors de la suppression des CVs selectionnés.", "Erreur !", JOptionPane.ERROR_MESSAGE);
                }
                
                loadListCV();
            }else{
                JOptionPane.showMessageDialog(this, "Veuillez selectionné un Curriculum Vitae dans la liste pour pouvoir le supprimer.", "Attention ! Selection", JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButtonSupprimerMouseClicked

    private void jButtonAjouterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonAjouterMouseClicked
        creationDuCV1.setIdCV(0);
        jTabbedPane1.setSelectedIndex(1);
        loadListCV();
    }//GEN-LAST:event_jButtonAjouterMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.karimandco.cv.CreationDuCV creationDuCV1;
    private fr.karim.main.composant.ImportExportFile importExportFile1;
    private javax.swing.JButton jButtonAjouter;
    private javax.swing.JButton jButtonModifier;
    private javax.swing.JButton jButtonSupprimer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanelAccueil;
    private javax.swing.JPanel jPanelListCV;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}