/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.karim.main.composant;

import com.alee.utils.swing.extensions.WindowCloseAdapter;
import fr.karim.main.JDialogInscription;
import fr.karim.main.JDialogMoreInformation;
import fr.karim.main.utils.user.Adresse;
import fr.karim.main.utils.user.Utilisateur;
import fr.karim.references.Reference;
import java.awt.event.ComponentEvent;

/**
 *
 * @author karim
 */
public class JPanelAccount extends javax.swing.JPanel {

    JDialogInscription inscriptionDialog;
    JDialogMoreInformation moreDialog;

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

        // Adresse
        
        Adresse a = Utilisateur.getInstance().getAdresse();
        
        if(a != null){
            jLabelFullAdresse.setText((a.getN_rue() != null ? a.getN_rue() + " " : "") + (a.getNom_rue()!= null ? a.getNom_rue() + " " : "") + (a.getCode_postale() != null ? ", " + a.getCode_postale() : ""));
            jLabelVille.setText(a.getVille() != null ? a.getVille() : "...");
            jLabelPays.setText(a.getPays()!= null ? a.getPays(): "...");
        }
        
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
        jButtonModify = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jButtonMore = new javax.swing.JButton();
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
        jPanel3 = new javax.swing.JPanel();
        jLabelTitleAccount1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabelFullAdresse = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabelVille = new javax.swing.JLabel();
        jLabelPays = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();

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

        jButtonModify.setBackground(new java.awt.Color(78, 33, 104));
        jButtonModify.setForeground(new java.awt.Color(255, 255, 255));
        jButtonModify.setText("Modifer");
        jButtonModify.setBorder(null);
        jButtonModify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModifyActionPerformed(evt);
            }
        });

        jSeparator1.setBackground(new java.awt.Color(255, 255, 255));

        jButtonMore.setBackground(new java.awt.Color(78, 33, 104));
        jButtonMore.setForeground(new java.awt.Color(255, 255, 255));
        jButtonMore.setText("Plus");
        jButtonMore.setBorder(null);
        jButtonMore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMoreActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabelTitleAccount, javax.swing.GroupLayout.PREFERRED_SIZE, 784, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonMore)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonModify)
                        .addGap(19, 19, 19)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTitleAccount)
                    .addComponent(jButtonModify)
                    .addComponent(jButtonMore))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Identifiant : ");

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Nom :");

        jLabel3.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Role");

        jLabel4.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Prenom : ");

        jLabelStatut.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        jLabelStatut.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelStatut.setText("...");

        jLabel6.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Genre :");

        jLabel7.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("N° Telephone :");

        jLabel8.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Courriel :");

        jLabel9.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Date de naissance :");

        jLabel10.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
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
        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Dernière modification");

        jLabelLastModified.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        jLabelLastModified.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelLastModified.setText("...");

        jPanel3.setBackground(new java.awt.Color(78, 39, 123));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 32, Short.MAX_VALUE)
        );

        jLabelTitleAccount1.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        jLabelTitleAccount1.setForeground(new java.awt.Color(0, 0, 0));
        jLabelTitleAccount1.setText("Adresse");

        jLabel5.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("n°/nom rue, code postale : ");

        jLabelFullAdresse.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        jLabelFullAdresse.setText("...");

        jLabel11.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Ville : ");

        jLabelVille.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        jLabelVille.setText("...");

        jLabelPays.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        jLabelPays.setText("...");

        jLabel12.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Pays : ");

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelTitleAccount1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
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
                                    .addComponent(jLabelDateDeNaissance, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabelFullAdresse, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabelVille, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabelPays, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE))))
                            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addContainerGap())))
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabelTitleAccount1)
                                .addGap(2, 2, 2)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabelFullAdresse)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabelVille)
                                    .addComponent(jLabel11))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabelPays)
                                    .addComponent(jLabel12))
                                .addGap(49, 49, 49))
                            .addGroup(layout.createSequentialGroup()
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
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonModifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModifyActionPerformed
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
    }//GEN-LAST:event_jButtonModifyActionPerformed

    private void jButtonMoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMoreActionPerformed
        
        moreDialog = new JDialogMoreInformation();
        
        moreDialog.addWindowListener(new WindowCloseAdapter() {
            @Override
            public void closed(ComponentEvent ce) {
                moreDialog.setModal(false);
                moreDialog.setVisible(false);
                moreDialog = null;

            }
        });
        
        moreDialog.setTitle(String.format(Reference.TITLE_NAME_SOFTWARE, "Plus d'informations"));
        moreDialog.setModal(true);
        moreDialog.setVisible(true);
        
    }//GEN-LAST:event_jButtonMoreActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonModify;
    private javax.swing.JButton jButtonMore;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelCourriel;
    private javax.swing.JLabel jLabelDateDeNaissance;
    private javax.swing.JLabel jLabelFullAdresse;
    private javax.swing.JLabel jLabelGenre;
    private javax.swing.JLabel jLabelIdentifiant;
    private javax.swing.JLabel jLabelLastModified;
    private javax.swing.JLabel jLabelMemberSince;
    private javax.swing.JLabel jLabelNom;
    private javax.swing.JLabel jLabelPays;
    private javax.swing.JLabel jLabelPrenom;
    private javax.swing.JLabel jLabelStatut;
    private javax.swing.JLabel jLabelTel;
    private javax.swing.JLabel jLabelTitleAccount;
    private javax.swing.JLabel jLabelTitleAccount1;
    private javax.swing.JLabel jLabelVille;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private com.karimandco.image.ShowImage showImage1;
    // End of variables declaration//GEN-END:variables
}
