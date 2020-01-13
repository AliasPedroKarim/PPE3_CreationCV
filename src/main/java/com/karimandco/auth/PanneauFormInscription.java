/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karimandco.auth;

import com.karimandco.auth.bdd.DaoSIO;
import fr.karim.main.composant.JPanelComplementaire;
import fr.karim.main.utils.Helpers;
import fr.karim.main.utils.user.Adresse;
import fr.karim.references.Reference;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.event.DocumentEvent;
import fr.karim.main.utils.user.Utilisateur;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
    
    private Boolean inscrit = false;
    
    private Adresse adresse = new Adresse();
    private Boolean n_rueOK = true;
    
    private Boolean createNewUser = false;

    /**
     * Ce constructeur permet d'initialiser le nom des labels et de générer les
     * KeyListener pour capturer les actions.
     */
    
    public PanneauFormInscription() {
        this(false);
    }
    
    public PanneauFormInscription(Boolean createNewUser) {
        initComponents();
        
        this.setCreateNewUser(createNewUser);
        
        // Custom 
        jComboBoxGenre.setModel(new javax.swing.DefaultComboBoxModel<>(Reference.GENRE_USER));

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
        
        jPanel1.setBackground(Reference.MAIN_DARK);
        
        jPanel2.setBackground(Reference.MAIN_DARK);
        
        init();
    }

    public void init(){
        
        if(Utilisateur.getInstance().getEstConnecte() && !this.getCreateNewUser()){
            jButtonInscription.setText("Mettre à jour");

            panneauNom.getChamp2().setText(Utilisateur.getInstance().getNom());
            panneauPrenom.getChamp2().setText(Utilisateur.getInstance().getPrenom());
            panneauIdentifiant.getChamp2().setText(Utilisateur.getIdentifiant());
            panneauCourriel.getChamp2().setText(Utilisateur.getInstance().getCourriel());
            panneauNumeroTelephone.getChamp2().setText(Utilisateur.getInstance().getNumeroTelephone());
            panneauDateNaissance.getChamp2().setText(Utilisateur.getInstance().getDateNaissance());
            
            jComboBoxGenre.setSelectedIndex(
                    Utilisateur.getInstance().getGenre().equals("Femme") ? 0 : 
                    Utilisateur.getInstance().getGenre().equals("Homme") ? 1 : 2);
            
            choisirImage1.getShowImage1().readPicture(Utilisateur.getInstance().getId());
        }
        
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

    public JButton getjButtonInscription() {
        return jButtonInscription;
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

    public Boolean getN_rueOK() {
        return n_rueOK;
    }

    public void setN_rueOK(Boolean n_rueOK) {
        this.n_rueOK = n_rueOK;
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

    public JComboBox<String> getjComboBoxGenre() {
        return jComboBoxGenre;
    }

    public void setjComboBoxGenre(JComboBox<String> jComboBoxGenre) {
        this.jComboBoxGenre = jComboBoxGenre;
    }

    public Boolean getCreateNewUser() {
        return createNewUser;
    }

    public void setCreateNewUser(Boolean createNewUser) {
        this.createNewUser = createNewUser;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonInscription = new javax.swing.JButton();
        jLabelEtatInscription = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        panneauIdentifiant = new com.karimandco.auth.PanneauChamp();
        panneauCourriel = new com.karimandco.auth.PanneauChamp();
        panneauNumeroTelephone = new com.karimandco.auth.PanneauChamp();
        panneauDateNaissance = new com.karimandco.auth.PanneauChamp();
        panneauNom = new com.karimandco.auth.PanneauChamp();
        panneauPrenom = new com.karimandco.auth.PanneauChamp();
        jComboBoxGenre = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        panneauMdp = new com.karimandco.auth.PanneauChampSecret();
        panneauMdpConfirmation = new com.karimandco.auth.PanneauChampSecret();
        panelKeyboradPassword1 = new fr.karim.main.utils.PanelKeyboradPassword();
        choisirImage1 = new com.karimandco.image.ChoisirImage();
        complementaire1 = new fr.karim.main.composant.JPanelComplementaire();
        jPanel3 = new javax.swing.JPanel();
        jTextFieldNumRue = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabelNumRue = new javax.swing.JLabel();
        jTextFieldNomRue = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldVille = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldPays = new javax.swing.JTextField();
        jTextFieldCP = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();

        jButtonInscription.setText("S'inscrire");
        jButtonInscription.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInscriptionActionPerformed(evt);
            }
        });

        jLabelEtatInscription.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);

        jComboBoxGenre.setModel(new javax.swing.DefaultComboBoxModel<>());

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Genre");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panneauNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panneauCourriel, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(panneauIdentifiant, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panneauNumeroTelephone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panneauPrenom, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBoxGenre, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panneauDateNaissance, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panneauIdentifiant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panneauNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panneauPrenom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panneauCourriel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxGenre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(panneauDateNaissance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(panneauNumeroTelephone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10))))
        );

        jTabbedPane1.addTab("Général", jPanel1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(panelKeyboradPassword1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(panneauMdp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panneauMdpConfirmation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(32, 32, 32))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panneauMdpConfirmation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panneauMdp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelKeyboradPassword1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(54, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Mot de passe", jPanel2);
        jTabbedPane1.addTab("Image/Avatar", choisirImage1);
        jTabbedPane1.addTab("Conplémentaires", complementaire1);

        jPanel3.setBackground(Reference.MAIN_DARK);

        jTextFieldNumRue.setBackground(Reference.MAIN_DARK);
        jTextFieldNumRue.setForeground(new java.awt.Color(255, 255, 255));
        jTextFieldNumRue.setBorder(null);
        jTextFieldNumRue.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldNumRueFocusLost(evt);
            }
        });
        jTextFieldNumRue.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldNumRueKeyReleased(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(78, 39, 123));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Votre adresse");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(383, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabelNumRue.setForeground(new java.awt.Color(255, 255, 255));
        jLabelNumRue.setText("N° Rue");

        jTextFieldNomRue.setBackground(Reference.MAIN_DARK);
        jTextFieldNomRue.setForeground(new java.awt.Color(255, 255, 255));
        jTextFieldNomRue.setBorder(null);

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Nom Rue");

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Code Postale");

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Ville");

        jTextFieldVille.setBackground(Reference.MAIN_DARK);
        jTextFieldVille.setForeground(new java.awt.Color(255, 255, 255));
        jTextFieldVille.setBorder(null);

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Pays");

        jTextFieldPays.setBackground(Reference.MAIN_DARK);
        jTextFieldPays.setForeground(new java.awt.Color(255, 255, 255));
        jTextFieldPays.setBorder(null);
        jTextFieldPays.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldPaysFocusLost(evt);
            }
        });

        jTextFieldCP.setBackground(Reference.MAIN_DARK);
        jTextFieldCP.setForeground(new java.awt.Color(255, 255, 255));
        jTextFieldCP.setBorder(null);
        jTextFieldCP.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldCPFocusLost(evt);
            }
        });

        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));

        jSeparator2.setForeground(new java.awt.Color(255, 255, 255));

        jSeparator3.setForeground(new java.awt.Color(255, 255, 255));

        jSeparator4.setForeground(new java.awt.Color(255, 255, 255));

        jSeparator5.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelNumRue)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextFieldCP, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTextFieldNumRue)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jTextFieldNomRue)
                    .addComponent(jSeparator2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jTextFieldPays, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldVille, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                            .addComponent(jLabel6)
                            .addComponent(jSeparator5))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNumRue)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldNumRue, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldNomRue, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldCP, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldPays, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldVille, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, 0)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(313, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Adresse", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelEtatInscription, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonInscription, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 513, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonInscription, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelEtatInscription, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonInscriptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInscriptionActionPerformed
        checkN_rue();
        
        if(n_rueOK){
            if (nomOK && prenomOK && identifiantOK && courrielOK && numeroTelephoneOK && dateNaissanceOK && ( (mdpOK && mdpConfOK) || Utilisateur.getInstance().getEstConnecte() )) {
                String[] date_split = this.panneauDateNaissance.getChamp2().getText().split("/");
                String date_newFormat = date_split[2] + "-" + date_split[1] + "-" + date_split[0];

                String mdp_sha256 = null;
                
                String pass = String.valueOf(this.panneauMdp.getChampSecret1().getPassword());
                if(!pass.equals("")){
                    mdp_sha256 = Cryptage.sha256(Cryptage.sha256( pass ));
                }

                Integer request = null;

                if(Utilisateur.getInstance().getEstConnecte() && !this.getCreateNewUser()){
                    request = DaoSIO.getInstance().requeteAction("UPDATE utilisateurs "
                        + "SET "
                            + "nom = '" + this.panneauNom.getChamp2().getText() + "', "
                            + "prenom = '" + this.panneauPrenom.getChamp2().getText() + "', "
                            + "genre = '" + (String) this.getjComboBoxGenre().getSelectedItem() + "', "
                            + "identifiant = '" + this.panneauIdentifiant.getChamp2().getText() + "', "
                            + "courriel = '" + this.panneauCourriel.getChamp2().getText() + "', "
                            + "num_telephone = '" + this.panneauNumeroTelephone.getChamp2().getText() + "', "
                            + "date_de_naissance = '" + date_newFormat + "', "
                            + (mdp_sha256 != null ? "mot_de_passe = '" + mdp_sha256 + "', " : "")
                            + "modified_at = '" + Reference.simpleDateDashes3.format(new Date()) + "' "
                        + "WHERE "
                            + "id = " + Utilisateur.getInstance().getId());
                }else{
                    request = DaoSIO.getInstance().requeteAction("INSERT "
                        + "INTO utilisateurs (nom, prenom, genre, identifiant, courriel, num_telephone, date_de_naissance, mot_de_passe, created_at, modified_at) "
                        + "VALUES ('" + this.panneauNom.getChamp2().getText() + "', '" 
                        + this.panneauPrenom.getChamp2().getText() + "', '" 
                        + (String) this.getjComboBoxGenre().getSelectedItem() + "', '" 
                        + this.panneauIdentifiant.getChamp2().getText() + "', '" 
                        + this.panneauCourriel.getChamp2().getText() + "', '" 
                        + this.panneauNumeroTelephone.getChamp2().getText() + "', '" 
                        + date_newFormat + "', '" 
                        + mdp_sha256 + "', '" 
                        + Reference.simpleDateDashes3.format(new Date()) + "', '" 
                        + Reference.simpleDateDashes3.format(new Date()) + "' )");
                }

                if (request > 0) {

                    try {
                        Integer idUtilisateur = null;

                        if(Utilisateur.getInstance().getEstConnecte() && !this.getCreateNewUser()){
                            idUtilisateur = Utilisateur.getInstance().getId();
                        }else{
                            idUtilisateur = fr.karim.connexion.DaoSIO.getInstance().getLastID("utilisateurs", "id");
                        }

                        if(idUtilisateur != null){
                            Integer i = null;

                            if(Utilisateur.getInstance().getEstConnecte() && Utilisateur.getInstance().getMedia() != null && !this.getCreateNewUser()){
                                i = choisirImage1.sendImage(idUtilisateur, Utilisateur.getInstance().getMedia().getId());
                            }else{
                                i = choisirImage1.sendImage(idUtilisateur);

                                if(!jTextFieldNumRue.getText().isEmpty() 
                                    || !jTextFieldNomRue.getText().isEmpty() 
                                    || !jTextFieldCP.getText().isEmpty() 
                                    || !jTextFieldVille.getText().isEmpty() 
                                    || !jTextFieldPays.getText().isEmpty()){

                                    adresse
                                        .setN_rue(!jTextFieldNumRue.getText().isEmpty() ? Integer.parseInt(jTextFieldNumRue.getText()) : null)
                                        .setNom_rue(jTextFieldNomRue.getText())
                                        .setCode_postale(jTextFieldCP.getText())
                                        .setVille(jTextFieldVille.getText())
                                        .setPays(jTextFieldPays.getText())
                                        .setId_utilisateur(idUtilisateur);

                                    adresse.sync();
                                }
                            }
                            this.setInscrit(i != null && i == 1);
                            this.getComplementaire1().sync(idUtilisateur);
                            Utilisateur.getInstance().reloadUser();

                            jLabelEtatInscription.setForeground(Color.blue);
                            jLabelEtatInscription.setText(Utilisateur.getInstance().getEstConnecte() && !this.getCreateNewUser() ? "Modification réussi" : "Inscription réussi");
                            this.setInscriptionOK(true);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(PanneauFormInscription.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    jLabelEtatInscription.setForeground(Reference.COLOR_NOT_VALIDATED);
                    jLabelEtatInscription.setText(Utilisateur.getInstance().getEstConnecte() ? "Echec de la modification" : "Echec de l'inscription");
                    this.setInscriptionOK(false);
                }
            }
            
            
        } else {
            jLabelEtatInscription.setForeground(Reference.COLOR_NOT_VALIDATED);
            jLabelEtatInscription.setText("Champ(s) manquant(s) ou n° de rue invalide");
            this.setInscriptionOK(false);
        }
    }//GEN-LAST:event_jButtonInscriptionActionPerformed

    private void jTextFieldCPFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldCPFocusLost
        handleAutoComplete();
    }//GEN-LAST:event_jTextFieldCPFocusLost

    private void jTextFieldPaysFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldPaysFocusLost
        handleAutoComplete();
    }//GEN-LAST:event_jTextFieldPaysFocusLost

    private void jTextFieldNumRueKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldNumRueKeyReleased
        
        if(jTextFieldNumRue.getText().matches("^[0-9]+")){
            jLabelNumRue.setForeground(Reference.COLOR_VALIDATED);
            this.setN_rueOK(true);
        }else{
            jLabelNumRue.setForeground(Reference.COLOR_NOT_VALIDATED);
            this.setN_rueOK(false);
        }
        
        if(jTextFieldNumRue.getText().isEmpty()){
            jLabelNumRue.setForeground(Color.WHITE);
            this.setN_rueOK(true);
        }
    }//GEN-LAST:event_jTextFieldNumRueKeyReleased

    private void jTextFieldNumRueFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldNumRueFocusLost
        checkN_rue();
    }//GEN-LAST:event_jTextFieldNumRueFocusLost

    public Boolean getInscrit() {
        return inscrit;
    }

    public void setInscrit(Boolean inscrit) {
        this.inscrit = inscrit;
    }

    public JPanelComplementaire getComplementaire1() {
        return complementaire1;
    }
    
    public void checkN_rue(){
        this.setN_rueOK(jTextFieldNumRue.getText().isEmpty() ? true : this.getN_rueOK());
    }
 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.karimandco.image.ChoisirImage choisirImage1;
    private fr.karim.main.composant.JPanelComplementaire complementaire1;
    private javax.swing.JButton jButtonInscription;
    private javax.swing.JComboBox<String> jComboBoxGenre;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabelEtatInscription;
    private javax.swing.JLabel jLabelNumRue;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextFieldCP;
    private javax.swing.JTextField jTextFieldNomRue;
    private javax.swing.JTextField jTextFieldNumRue;
    private javax.swing.JTextField jTextFieldPays;
    private javax.swing.JTextField jTextFieldVille;
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
                champ.setjLabelEtatChamp(Reference.COLOR_VALIDATED);
                champ.setjLabelEtatChamp("Format ok");
            } else {
                champ.setjLabelEtatChamp(Reference.COLOR_NOT_VALIDATED);
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
                    panneauMdpConfirmation.setjLabelEtatChampSecret(Reference.COLOR_VALIDATED);
                    panneauMdp.setjLabelEtatChampSecret(Reference.COLOR_VALIDATED);
                    panneauMdpConfirmation.setjLabelEtatChampSecret("Correspondance ok");
                    panneauMdp.setjLabelEtatChampSecret("Correspondance ok");
                } else {
                    setMdpOK(false);
                    setMdpConfOK(false);
                    panneauMdpConfirmation.setjLabelEtatChampSecret(Reference.COLOR_NOT_VALIDATED);
                    panneauMdp.setjLabelEtatChampSecret(Reference.COLOR_NOT_VALIDATED);
                    panneauMdpConfirmation.setjLabelEtatChampSecret("Correspondance non ok");
                    panneauMdp.setjLabelEtatChampSecret("Correspondance non ok");
                }
            } else {
                setMdpOK(false);
                setMdpConfOK(false);
                panneauMdpConfirmation.setjLabelEtatChampSecret(Reference.COLOR_NOT_VALIDATED);
                panneauMdp.setjLabelEtatChampSecret(Reference.COLOR_NOT_VALIDATED);
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
    
    public void handleAutoComplete(){
        try {
            if(!jTextFieldPays.getText().equals("")){

                String dataAPI = Helpers.getDataAPI("https://restcountries.eu/rest/v2/name/" + jTextFieldPays.getText().toString().toLowerCase());

                if(dataAPI != null){
                    JSONParser jsonParser = new JSONParser();

                    Object obj = jsonParser.parse(dataAPI);

                    if (obj != null && obj instanceof JSONArray) {
                        JSONArray jsonObj = (JSONArray) obj;

                        if(jsonObj.size() == 1){

                            JSONObject o = (JSONObject) jsonObj.get(0);

                            if(o != null && o.get("alpha3Code") != null){

                                if(!jTextFieldCP.getText().equals("")){
                                    dataAPI = Helpers.getDataAPI("http://api.zippopotam.us/"+ o.get("alpha2Code").toString().toLowerCase() + "/" + jTextFieldCP.getText());
                                    if(dataAPI != null){
                                        obj = jsonParser.parse(dataAPI);
                                        if (obj != null && obj instanceof JSONObject) {
                                            JSONObject p = (JSONObject) obj;

                                            if(p != null && p.get("places") != null && p.get("places") instanceof JSONArray){
                                                if(((JSONArray) p.get("places")).size() > 0){

                                                    jTextFieldVille.setText((String) ((JSONObject) ((JSONArray) p.get("places")).get(0)).get("place name"));

                                                }

                                            }
                                        }
                                    }
                                }

                            }

                        }

                    }
                }

            }

        } catch (ParseException ex) {
            Logger.getLogger(PanneauFormInscription.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
