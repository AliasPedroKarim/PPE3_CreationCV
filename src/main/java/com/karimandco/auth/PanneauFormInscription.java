/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karimandco.auth;

import com.karimandco.auth.bdd.DaoSIO;
import fr.karim.main.utils.Helpers;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.event.DocumentEvent;

/**
 *
 * @author Damien F, Pawel R, Théo M
 */
public class PanneauFormInscription extends javax.swing.JPanel {

    javax.swing.JDialog panneauPereInscription = null;

    private Boolean nomOK = false;
    private Boolean prenomOK = false;
    private Boolean identifiantOK = false;
    private Boolean courrielOK = false;
    private Boolean numeroTelephoneOK = false;
    private Boolean dateNaissanceOK = false;
    private Boolean mdpOK = false;
    private Boolean mdpConfOK = false;

    private Boolean inscriptionOK = false;

    /**
     * Ce constructeur permet d'initialiser le nom des labels et de générer les
     * KeyListener pour capturer les actions.
     */
    public PanneauFormInscription() {
        initComponents();

        panneauNom.setjLabelNomChamp("Nom");
        panneauPrenom.setjLabelNomChamp("Prénom");
        panneauIdentifiant.setjLabelNomChamp("Identifiant");
        panneauCourriel.setjLabelNomChamp("Courriel");
        panneauNumeroTelephone.setjLabelNomChamp("Numéro de téléphone");
        panneauDateNaissance.setjLabelNomChamp("Date de naissance (jj/mm/aaaa)");

        panneauMdp.setjLabelNomChampSecret("Mot de passe (6 à 12 chiffres)");
        panneauMdpConfirmation.setjLabelNomChampSecret("Confirmation du mot de passe");

        KeyListener(panneauNom, 0);
        KeyListener(panneauPrenom, 1);
        KeyListener(panneauIdentifiant, 2);
        KeyListener(panneauCourriel, 3);
        KeyListener(panneauNumeroTelephone, 4);
        KeyListener(panneauDateNaissance, 5);

        KeyListener(panneauMdp);
        KeyListener(panneauMdpConfirmation);
        
        jPanel1.setBackground(new Color(25, 28, 32));
        
        jPanel2.setBackground(new Color(25, 28, 32));
    }

    public void setPanneauCourriel(PanneauChamp panneauCourriel) {
        this.panneauCourriel = panneauCourriel;
    }

    public void setPanneauDateNaissance(PanneauChamp panneauDateNaissance) {
        this.panneauDateNaissance = panneauDateNaissance;
    }

    public PanneauChamp getPanneauIdentifiant() {
        return panneauIdentifiant;
    }

    public void setPanneauIdentifiant(PanneauChamp panneauIdentifiant) {
        this.panneauIdentifiant = panneauIdentifiant;
    }

    public PanneauChampSecret getPanneauMdp() {
        return panneauMdp;
    }

    public void setPanneauMdp(PanneauChampSecret panneauMdp) {
        this.panneauMdp = panneauMdp;
    }

    public PanneauChampSecret getPanneauMdpConfirmation() {
        return panneauMdpConfirmation;
    }

    public void setPanneauMdpConfirmation(PanneauChampSecret panneauMdpConfirmation) {
        this.panneauMdpConfirmation = panneauMdpConfirmation;
    }

    public void setPanneauNom(PanneauChamp panneauNom) {
        this.panneauNom = panneauNom;
    }

    public PanneauChamp getPanneauNumeroTelephone() {
        return panneauNumeroTelephone;
    }

    public void setPanneauNumeroTelephone(PanneauChamp panneauNumeroTelephone) {
        this.panneauNumeroTelephone = panneauNumeroTelephone;
    }

    public void setPanneauPrenom(PanneauChamp panneauPrenom) {
        this.panneauPrenom = panneauPrenom;
    }
    
    public void setNomOK(Boolean nomOK) {
        this.nomOK = nomOK;
    }

    public void setPrenomOK(Boolean prenomOK) {
        this.prenomOK = prenomOK;
    }

    public void setIdentifiantOK(Boolean identifiantOK) {
        this.identifiantOK = identifiantOK;
    }

    public void setCourrielOK(Boolean courrielOK) {
        this.courrielOK = courrielOK;
    }

    public void setNumeroTelephoneOK(Boolean numeroTelephoneOK) {
        this.numeroTelephoneOK = numeroTelephoneOK;
    }

    public void setDateNaissanceOK(Boolean dateNaissanceOK) {
        this.dateNaissanceOK = dateNaissanceOK;
    }

    public void setMdpOK(Boolean mdpOK) {
        this.mdpOK = mdpOK;
    }

    public void setMdpConfOK(Boolean mdpConfOK) {
        this.mdpConfOK = mdpConfOK;
    }

    public Boolean getNomOK() {
        return nomOK;
    }

    public Boolean getPrenomOK() {
        return prenomOK;
    }

    public Boolean getIdentifiantOK() {
        return identifiantOK;
    }

    public Boolean getCourrielOK() {
        return courrielOK;
    }

    public Boolean getNumeroTelephoneOK() {
        return numeroTelephoneOK;
    }

    public Boolean getMdpOK() {
        return mdpOK;
    }

    public Boolean getMdpConfOK() {
        return mdpConfOK;
    }

    public JButton getjButton1() {
        return jButton1;
    }

    public void setFenParentInscription(javax.swing.JDialog i) {
        this.panneauPereInscription = i;
    }

    public Boolean getInscriptionOK() {
        return inscriptionOK;
    }

    public void setInscriptionOK(Boolean inscriptionOK) {
        this.inscriptionOK = inscriptionOK;
    }

    public PanneauChamp getPanneauCourriel() {
        return panneauCourriel;
    }

    public PanneauChamp getPanneauDateNaissance() {
        return panneauDateNaissance;
    }

    public PanneauChamp getPanneauNom() {
        return panneauNom;
    }

    public PanneauChamp getPanneauPrenom() {
        return panneauPrenom;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jLabelEtatInscription = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        panneauIdentifiant = new com.karimandco.auth.PanneauChamp();
        panneauCourriel = new com.karimandco.auth.PanneauChamp();
        panneauNumeroTelephone = new com.karimandco.auth.PanneauChamp();
        panneauDateNaissance = new com.karimandco.auth.PanneauChamp();
        jLabel1 = new javax.swing.JLabel();
        panneauNom = new com.karimandco.auth.PanneauChamp();
        panneauPrenom = new com.karimandco.auth.PanneauChamp();
        jPanel2 = new javax.swing.JPanel();
        panneauMdp = new com.karimandco.auth.PanneauChampSecret();
        panneauMdpConfirmation = new com.karimandco.auth.PanneauChampSecret();
        panelKeyboradPassword1 = new fr.karim.main.utils.PanelKeyboradPassword();

        jButton1.setText("S'inscrire");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabelEtatInscription.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Inscription");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.CENTER, jPanel1Layout.createSequentialGroup()
                        .addComponent(panneauNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                        .addComponent(panneauPrenom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.CENTER, jPanel1Layout.createSequentialGroup()
                        .addComponent(panneauNumeroTelephone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panneauDateNaissance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.CENTER, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                .addComponent(panneauCourriel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(panneauIdentifiant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 458, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(10, 10, 10)
                .addComponent(panneauIdentifiant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panneauNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panneauPrenom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(panneauCourriel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panneauNumeroTelephone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panneauDateNaissance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(panneauMdp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panneauMdpConfirmation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panelKeyboradPassword1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panneauMdpConfirmation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panneauMdp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addComponent(panelKeyboradPassword1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelEtatInscription, javax.swing.GroupLayout.PREFERRED_SIZE, 696, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelEtatInscription, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (nomOK && prenomOK && identifiantOK && courrielOK && numeroTelephoneOK && dateNaissanceOK && mdpOK && mdpConfOK) {
            String[] date_split = this.panneauDateNaissance.getChamp2().getText().split("/");
            String date_newFormat = date_split[2] + "-" + date_split[1] + "-" + date_split[0];

            String mdp_sha256 = Cryptage.sha256(Cryptage.sha256(String.valueOf(this.panneauMdp.getChampSecret1().getPassword())));

            if (DaoSIO.getInstance().requeteAction("INSERT INTO utilisateurs (nom, prenom, identifiant, courriel, num_telephone, date_de_naissance, mot_de_passe, photo) VALUES ('" + this.panneauNom.getChamp2().getText() + "', '" + this.panneauPrenom.getChamp2().getText() + "', '" + this.panneauIdentifiant.getChamp2().getText() + "', '" + this.panneauCourriel.getChamp2().getText() + "', '" + this.panneauNumeroTelephone.getChamp2().getText() + "', '" + date_newFormat + "', '" + mdp_sha256 + "', '')") > 0) {
                jLabelEtatInscription.setForeground(Color.blue);
                jLabelEtatInscription.setText("Inscription réussi");
                this.setInscriptionOK(true);
            } else {
                jLabelEtatInscription.setForeground(Helpers.COLOR_NOT_VALIDATED);
                jLabelEtatInscription.setText("Echec de l'inscription");
                this.setInscriptionOK(false);
            }
        } else {
            jLabelEtatInscription.setForeground(Helpers.COLOR_NOT_VALIDATED);
            jLabelEtatInscription.setText("Champ(s) manquant(s)");
            this.setInscriptionOK(false);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelEtatInscription;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private fr.karim.main.utils.PanelKeyboradPassword panelKeyboradPassword1;
    private com.karimandco.auth.PanneauChamp panneauCourriel;
    private com.karimandco.auth.PanneauChamp panneauDateNaissance;
    private com.karimandco.auth.PanneauChamp panneauIdentifiant;
    private com.karimandco.auth.PanneauChampSecret panneauMdp;
    private com.karimandco.auth.PanneauChampSecret panneauMdpConfirmation;
    private com.karimandco.auth.PanneauChamp panneauNom;
    private com.karimandco.auth.PanneauChamp panneauNumeroTelephone;
    private com.karimandco.auth.PanneauChamp panneauPrenom;
    // End of variables declaration//GEN-END:variables

    /**
     * Cette méthode permet de générer un KeyListener pour les panneaux champ.
     *
     * @param champ PanneauChamp champ
     * @param numeroVerif Integer numéro du champ
     */
    private void KeyListener(PanneauChamp champ, Integer numeroVerif) {
        champ.getChamp2().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) { }

            @Override
            public void keyPressed(KeyEvent e) { }

            @Override
            public void keyReleased(KeyEvent e) {
                updateJLabelEtat(champ, numeroVerif);
            }
        });
        
        champ.getChamp2().getDocument().addDocumentListener(new Helpers.SimpleDocumentListener() {
            @Override
            public void update(DocumentEvent e) {
                updateJLabelEtat(champ, numeroVerif);
            }
        });
    }

    /**
     * Cette méthode permet de générer un KeyListener pour les panneaux champ
     * secret.
     *
     * @param champ PanneauChampSecret champ
     */
    private void KeyListener(PanneauChampSecret champ) {
        champ.getChampSecret1().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) { }

            @Override
            public void keyPressed(KeyEvent e) { }

            @Override
            public void keyReleased(KeyEvent e) {
                updateJLabelEtat();
            }
        });
        
        champ.getChampSecret1().getDocument().addDocumentListener(new Helpers.SimpleDocumentListener() {
            @Override
            public void update(DocumentEvent e) {
                updateJLabelEtat();
            }
        });
        
        champ.getChampSecret1().addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) { 
                panelKeyboradPassword1.setPasswordField(champ.getChampSecret1());
            }

            @Override
            public void focusLost(FocusEvent e) { }
        });
    }
    
    /**
     * Cette méthode permet de mettre à jour les JLabelEtat des champs
     *
     * @param champ PanneauChamp champ
     * @param numeroVerif Integer numéro du champ à vérifier
     */
    public void updateJLabelEtat(PanneauChamp champ, Integer numeroVerif) {
        Boolean resultat = null;
        if (!champ.getChamp2().getText().equals("")) {
            if (numeroVerif == 0) {
                resultat = champ.getChamp2().verifNom();
                setNomOK(resultat);
            } else if (numeroVerif == 1) {
                resultat = champ.getChamp2().verifPrenom();
                setPrenomOK(resultat);
            } else if (numeroVerif == 2) {
                resultat = champ.getChamp2().verifIdentifiant();
                setIdentifiantOK(resultat);
            } else if (numeroVerif == 3) {
                resultat = champ.getChamp2().verifCourriel();
                setCourrielOK(resultat);
            } else if (numeroVerif == 4) {
                resultat = champ.getChamp2().verifNumeroTelephone();
                setNumeroTelephoneOK(resultat);
            } else if (numeroVerif == 5) {
                resultat = champ.getChamp2().verifDateNaissance();
                setDateNaissanceOK(resultat);
            }

            if (resultat == true) {
                champ.setjLabelEtatChamp(Helpers.COLOR_VALIDATED);
                champ.setjLabelEtatChamp("Format ok");
            } else {
                champ.setjLabelEtatChamp(Helpers.COLOR_NOT_VALIDATED);
                champ.setjLabelEtatChamp("Format non ok");
            }
        } else {
            if (numeroVerif == 0) {
                setNomOK(champ.getChamp2().verifNom());
            } else if (numeroVerif == 1) {
                setPrenomOK(champ.getChamp2().verifPrenom());
            } else if (numeroVerif == 2) {
                setIdentifiantOK(champ.getChamp2().verifIdentifiant());
            } else if (numeroVerif == 3) {
                setCourrielOK(champ.getChamp2().verifCourriel());
            } else if (numeroVerif == 4) {
                setNumeroTelephoneOK(champ.getChamp2().verifNumeroTelephone());
            } else if (numeroVerif == 5) {
                setDateNaissanceOK(champ.getChamp2().verifDateNaissance());
            }

            champ.setjLabelEtatChamp(Color.black);
            champ.setjLabelEtatChamp("");
        }
    }

    /**
     * Cette méthode permet de mettre à jour les JLabelEtat des champs secrets
     */
    public void updateJLabelEtat() {
        if (!String.valueOf(panneauMdp.getChampSecret1().getPassword()).equals("") && !String.valueOf(panneauMdpConfirmation.getChampSecret1().getPassword()).equals("")) {
            if (panneauMdp.getChampSecret1().verifPassword() && panneauMdpConfirmation.getChampSecret1().verifPassword()) {
                if (String.valueOf(panneauMdp.getChampSecret1().getPassword()).equals(String.valueOf(panneauMdpConfirmation.getChampSecret1().getPassword()))) {
                    setMdpOK(true);
                    setMdpConfOK(true);
                    panneauMdpConfirmation.setjLabelEtatChampSecret(Helpers.COLOR_VALIDATED);
                    panneauMdp.setjLabelEtatChampSecret(Helpers.COLOR_VALIDATED);
                    panneauMdpConfirmation.setjLabelEtatChampSecret("Correspondance ok");
                    panneauMdp.setjLabelEtatChampSecret("Correspondance ok");
                } else {
                    setMdpOK(false);
                    setMdpConfOK(false);
                    panneauMdpConfirmation.setjLabelEtatChampSecret(Helpers.COLOR_NOT_VALIDATED);
                    panneauMdp.setjLabelEtatChampSecret(Helpers.COLOR_NOT_VALIDATED);
                    panneauMdpConfirmation.setjLabelEtatChampSecret("Correspondance non ok");
                    panneauMdp.setjLabelEtatChampSecret("Correspondance non ok");
                }
            } else {
                setMdpOK(false);
                setMdpConfOK(false);
                panneauMdpConfirmation.setjLabelEtatChampSecret(Helpers.COLOR_NOT_VALIDATED);
                panneauMdp.setjLabelEtatChampSecret(Helpers.COLOR_NOT_VALIDATED);
                panneauMdpConfirmation.setjLabelEtatChampSecret("Format non ok");
                panneauMdp.setjLabelEtatChampSecret("Format non ok");
            }
        } else {
            setMdpOK(false);
            setMdpConfOK(false);
            panneauMdp.setjLabelEtatChampSecret(Color.black);
            panneauMdp.setjLabelEtatChampSecret("");
            panneauMdpConfirmation.setjLabelEtatChampSecret(Color.black);
            panneauMdpConfirmation.setjLabelEtatChampSecret("");
        }
    }

}
