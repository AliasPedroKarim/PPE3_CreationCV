/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.karim.main.composant;

import com.alee.utils.swing.extensions.WindowCloseAdapter;
import fr.karim.main.JDialogInscription;
import fr.karim.main.utils.user.Utilisateur;
import fr.karim.references.Reference;
import java.awt.event.ComponentEvent;

/**
 *
 * @author karim
 */
public class JPanelAccount extends javax.swing.JPanel {

    JDialogInscription inscriptionDialog;

    /**
     * Creates new form JPanelAccount
     */
    public JPanelAccount() {
        initComponents();

        if(Utilisateur.getInstance().getEstConnecte()){
            jLabelTitleAccount.setText("Votre compte - " + Utilisateur.getIdentifiant());

            init();
        }

    }

    public void init(){
        // System.out.println(System.getProperty("java.io.tmpdir"));

        String m = Reference.simpleDateSlashes2.format(Utilisateur.getInstance().getModified_at());
        String c = Reference.simpleDateSlashes2.format(Utilisateur.getInstance().getModified_at());
        
        jLabelCourriel.setText(Utilisateur.getInstance().getCourriel());
        jLabelDateDeNaissance.setText(Utilisateur.getInstance().getDateNaissance());
        jLabelGenre.setText(Utilisateur.getInstance().getGenre());
        jLabelIdentifiant.setText(Utilisateur.getIdentifiant());
        jLabelLastModified.setText(m != null ? m : "null");
        jLabelMemberSince.setText(c != null ? c : "null");
        jLabelNom.setText(Utilisateur.getInstance().getNom());
        jLabelPrenom.setText(Utilisateur.getInstance().getPrenom());
        jLabelStatut.setText(Utilisateur.getInstance().getStatut() != null && Utilisateur.getInstance().getStatut() <= Reference.STATUS.length ? Reference.STATUS[Utilisateur.getInstance().getStatut()] : "null");
        jLabelTel.setText(Utilisateur.getInstance().getNumeroTelephone());

        showImage1.readPicture(Utilisateur.getInstance().getId());
    }



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        showImage1 = new com.karimandco.image.ShowImage();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabelTitleAccount = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabelStatut = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabelMemberSince = new javax.swing.JLabel();
        jLabelNom = new javax.swing.JLabel();
        jLabelPrenom = new javax.swing.JLabel();
        jLabelGenre = new javax.swing.JLabel();
        jLabelTel = new javax.swing.JLabel();
        jLabelCourriel = new javax.swing.JLabel();
        jLabelDateDeNaissance = new javax.swing.JLabel();
        jLabelIdentifiant = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabelLastModified = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(78, 39, 123));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(78, 33, 104));

        jLabelTitleAccount.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabelTitleAccount.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTitleAccount.setText("jLabel1");

        jButton2.setBackground(new java.awt.Color(78, 33, 104));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Modifer");
        jButton2.setBorder(null);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(78, 33, 104));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Desactiver");
        jButton1.setBorder(null);

        jSeparator1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabelTitleAccount, javax.swing.GroupLayout.DEFAULT_SIZE, 824, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTitleAccount)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        jLabel1.setText("Identifiant : ");

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        jLabel2.setText("Nom :");

        jLabel3.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Role");

        jLabel4.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        jLabel4.setText("Prenom : ");

        jLabelStatut.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        jLabelStatut.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelStatut.setText("...");

        jLabel6.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        jLabel6.setText("Genre :");

        jLabel7.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        jLabel7.setText("N° Telephone :");

        jLabel8.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        jLabel8.setText("Courriel :");

        jLabel9.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        jLabel9.setText("Date de naissance :");

        jLabel10.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Membre depuis");

        jLabelMemberSince.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        jLabelMemberSince.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelMemberSince.setText("...");

        jLabelNom.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        jLabelNom.setText("...");

        jLabelPrenom.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        jLabelPrenom.setText("...");

        jLabelGenre.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        jLabelGenre.setText("...");

        jLabelTel.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        jLabelTel.setText("...");

        jLabelCourriel.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        jLabelCourriel.setText("...");

        jLabelDateDeNaissance.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        jLabelDateDeNaissance.setText("...");

        jLabelIdentifiant.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        jLabelIdentifiant.setText("...");

        jLabel19.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Dernière modification");

        jLabelLastModified.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        jLabelLastModified.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelLastModified.setText("...");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(showImage1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelStatut, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelMemberSince, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelLastModified, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelIdentifiant, javax.swing.GroupLayout.DEFAULT_SIZE, 487, Short.MAX_VALUE)
                    .addComponent(jLabelNom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelPrenom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelGenre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelTel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelCourriel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelDateDeNaissance, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(showImage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabelIdentifiant)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabelNom)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabelPrenom)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabelGenre)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabelTel)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabelCourriel)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabelDateDeNaissance))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel2)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel4)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel6)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel7)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel8)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel9)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelStatut)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelMemberSince)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelLastModified)
                        .addContainerGap(96, Short.MAX_VALUE))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        inscriptionDialog = new JDialogInscription();

        inscriptionDialog.addWindowListener(new WindowCloseAdapter() {
            @Override
            public void closed(ComponentEvent ce) {
                inscriptionDialog.setModal(false);
                inscriptionDialog.setVisible(false);
                inscriptionDialog = null;

            }
        });

        inscriptionDialog.setTitle(String.format(Reference.TITLE_NAME_SOFTWARE, "Inscription"));
        inscriptionDialog.setModal(true);
        inscriptionDialog.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelCourriel;
    private javax.swing.JLabel jLabelDateDeNaissance;
    private javax.swing.JLabel jLabelGenre;
    private javax.swing.JLabel jLabelIdentifiant;
    private javax.swing.JLabel jLabelLastModified;
    private javax.swing.JLabel jLabelMemberSince;
    private javax.swing.JLabel jLabelNom;
    private javax.swing.JLabel jLabelPrenom;
    private javax.swing.JLabel jLabelStatut;
    private javax.swing.JLabel jLabelTel;
    private javax.swing.JLabel jLabelTitleAccount;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private com.karimandco.image.ShowImage showImage1;
    // End of variables declaration//GEN-END:variables
}