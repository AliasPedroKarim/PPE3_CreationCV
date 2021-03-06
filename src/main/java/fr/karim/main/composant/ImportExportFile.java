/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.karim.main.composant;

import com.itextpdf.text.BadElementException;
import com.karimandco.pdf.GeneratorCVPDF;
import fr.karim.main.utils.Helpers;
import fr.karim.main.utils.user.Utilisateur;

import java.io.File;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

import fr.karim.main.utils.handlefile.ExportImportToCSV;
import fr.karim.main.utils.handlefile.ExportImportToJSON;
import fr.karim.references.Reference;
import org.json.simple.JSONObject;

import fr.karim.connexion.DaoSIO;
import fr.karim.main.utils.handlefile.ExportImportToXML;
import fr.karim.main.utils.user.CV;
import fr.karim.references.Message;
import java.io.IOException;

import javax.swing.table.DefaultTableModel;
import org.json.simple.JSONArray;

/**
 *
 * @author karim
 */
public class ImportExportFile extends javax.swing.JPanel {

    SimpleDateFormat simpleDate = new SimpleDateFormat("dd/mm/yyyy");

    SimpleDateFormat simpleDate1 = new SimpleDateFormat("dd-mm-yyyy");
    SimpleDateFormat simpleDate2 = new SimpleDateFormat("yyyy-mm-dd");

    DefaultComboBoxModel comboBowModel;

    DefaultTableModel listeExport;
    private List<Map<String, Object>> data_table = new ArrayList<Map<String, Object>>();

    /**
     * Creates new form ImportExportFile
     */
    public ImportExportFile() {
        initComponents();

        jComboBoxFileExport.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"csv", "xml", "json"}));
        comboBowModel = (DefaultComboBoxModel) jComboBoxFileExport.getModel();

        listeExport = (DefaultTableModel) jTableListExport.getModel();
        listeExport.setColumnIdentifiers(new String[]{"Id", "Titre", "Description", "Signature", "Nom Maitrise", "Maitrise", "Formations", "Expérience Pro"});

        init();
    }

    private void init() {
        if (Utilisateur.getInstance().getEstConnecte()) {

            ArrayList<String> indexes = new ArrayList<String>();

            List<Map<String, Object>> CVs = null;

            try {
                CVs = Helpers.getIntance().getCV(Utilisateur.getInstance().getId());
            } catch (SQLException ex) {
                Logger.getLogger(Software.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (CVs != null && CVs.size() > 0) {
                for (Map<String, Object> CV : CVs) {
                    indexes.add(String.valueOf(CV.get("id")));
                }
                jComboBoxListCV.setModel(new javax.swing.DefaultComboBoxModel<>(Helpers.GetStringArray(indexes)));
            }

        }
    }

    public void loadDataInTable(List<Object> data) {

        if (data != null && data.size() > 0) {
            for (Object item : data) {

                if (item instanceof HashMap) {
                    HashMap<String, Object> item_list = (HashMap<String, Object>) item;
                    data_table.add(item_list);

                    StringBuilder builder = new StringBuilder();
                    Object[] o = new Object[]{};

                    o = Helpers.appendValue(o, item_list.get("id_utilisateur"));
                    o = Helpers.appendValue(o, item_list.get("titre"));
                    o = Helpers.appendValue(o, item_list.get("description"));
                    o = Helpers.appendValue(o, item_list.get("signature"));
                    o = Helpers.appendValue(o, item_list.get("nom_maitrise"));
                    o = Helpers.appendValue(o, item_list.get("maitrise"));

                    for (String element : new String[]{"formation", "experience_pro"}) {
                        
                        if(item_list.get(element) instanceof String[]){
                            builder.append("[ ").append(String.join(", ", (String[]) item_list.get(element))).append(" ]").append("\r\n");
                        }else if(item_list.get(element) instanceof List) {
                            List<String[]> element_coms = (List<String[]>) item_list.get(element);
                            if(element_coms != null){
                                for (String[] element_com : element_coms) {
                                    builder.append("[ ").append(String.join(", ", element_com)).append(" ]").append("\r\n");
                                }
                            }
                        }
                        o = Helpers.appendValue(o, !builder.toString().equals("") ? builder.toString() : "vide");

                    }

                    listeExport.addRow(o);
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

        jButtonExport = new javax.swing.JButton();
        jButtonImport = new javax.swing.JButton();
        jComboBoxFileExport = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableListExport = new javax.swing.JTable();
        jButtonAddImport = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jButtonGeneratorPDF = new javax.swing.JButton();
        jComboBoxListCV = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();

        jButtonExport.setText("Exporter");
        jButtonExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExportActionPerformed(evt);
            }
        });

        jButtonImport.setText("Importer");
        jButtonImport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonImportActionPerformed(evt);
            }
        });

        jComboBoxFileExport.setModel(new javax.swing.DefaultComboBoxModel<>());

        jTableListExport.setModel(new javax.swing.table.DefaultTableModel());
        jScrollPane1.setViewportView(jTableListExport);

        jButtonAddImport.setText("Ajouter");
        jButtonAddImport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddImportActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel1.setText("Exporter/Importer (CSV, XML, JSON)");

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel2.setText("Générer (PDF)");

        jButtonGeneratorPDF.setText("Générer");
        jButtonGeneratorPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGeneratorPDFActionPerformed(evt);
            }
        });

        jComboBoxListCV.setModel(new javax.swing.DefaultComboBoxModel<>());

        jLabel3.setText("Choisissez le cv");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 974, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonImport)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonAddImport))
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator3)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButtonExport)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBoxFileExport, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jComboBoxListCV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonGeneratorPDF))
                            .addComponent(jLabel3))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jSeparator2)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonExport)
                    .addComponent(jComboBoxFileExport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAddImport)
                    .addComponent(jButtonImport))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxListCV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonGeneratorPDF))
                .addGap(10, 10, 10))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(49, 49, 49)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(551, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExportActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("."));
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int r = chooser.showSaveDialog(null);

        if (chooser.getSelectedFile() != null && r == JFileChooser.APPROVE_OPTION) {

            String typeFileSelected = jComboBoxFileExport.getSelectedItem().toString();

            if (!typeFileSelected.equals("")) {

                List<Map<String, Object>> cv = null;

                if (Utilisateur.getInstance().getEstConnecte()) {

                    try {
                        cv = Helpers.getIntance().getCV(Utilisateur.getInstance().getId());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    if (typeFileSelected.equals("csv")) {
                        exportToCSV(chooser.getSelectedFile().toPath().toString(), cv);
                    } else if (typeFileSelected.equals("xml")) {
                        exportToXML(chooser.getSelectedFile().toPath().toString(), cv);
                    } else if (typeFileSelected.equals("json")) {
                        exportToJSON(chooser.getSelectedFile().toPath().toString(), cv);
                    }
                }
            }
        }
    }//GEN-LAST:event_jButtonExportActionPerformed

    private void jButtonImportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonImportActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("."));

        chooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
            private final FileNameExtensionFilter filter
                    = new FileNameExtensionFilter("Storage Files",
                            "xml", "csv", "json");

            public boolean accept(File f) {
                return filter.accept(f);
            }

            public String getDescription() {
                return "xml | csv | json - file";
            }
        });

        int r = chooser.showOpenDialog(new JFrame());
        if (r == JFileChooser.APPROVE_OPTION) {
            String name = chooser.getSelectedFile().getName();
            String type = chooser.getTypeDescription(chooser.getSelectedFile());

            if (type.equals("Document XML")) {
                importXML(chooser.getSelectedFile());
            } else if (type.equals("Fichier CSV")) {
                importCSV(chooser.getSelectedFile());
            } else if (type.equals("Fichier JSON")) {
                importJSON(chooser.getSelectedFile());
            }

            // System.out.println(chooser.getSelectedFile().getAbsolutePath());
        }
    }//GEN-LAST:event_jButtonImportActionPerformed

    private void jButtonAddImportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddImportActionPerformed
        if (data_table != null && data_table.size() > 0) {

            for (Map<String, Object> i : data_table) {
                try {
                    DaoSIO
                            .getInstance()
                            .requeteAction("INSERT INTO `cv` (`id`, `titre`, `description`, `signature`, `nom_maitrise`, `maitrise`, `id_utilisateur`) "
                                    + "VALUES (NULL, '"
                                    + i.get("titre") + "', '"
                                    + i.get("description") + "', '"
                                    + i.get("signature") + "', '"
                                    + i.get("nom_maitrise") + "', '" + i.get("maitrise") + "', '" + Utilisateur.getInstance().getId() + "');");

                    Integer idCV = DaoSIO.getInstance().getLastID("cv", "id");

                    List<String[]> formations = (List<String[]>) i.get("formation");
                    for (String[] formation : formations) {
                        try {
                            DaoSIO
                                    .getInstance()
                                    .requeteAction("INSERT INTO `formation` (`id`, `nom`, `lieu`, `description`, `annee_debut`, `annee_fin`, `id_cv`) "
                                            + "VALUES (NULL, '"
                                            + formation[1] + "', '"
                                            + formation[2] + "', '"
                                            + formation[3] + "', '"
                                            + simpleDate2.format(simpleDate.parse(formation[4].trim())) + "', '"
                                            + simpleDate2.format(simpleDate.parse(formation[5].trim())) + "', '"
                                            + idCV + "');");
                        } catch (java.text.ParseException ex) {
                            Logger.getLogger(ImportExportFile.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                    List<String[]> experiencePros = (List<String[]>) i.get("experience_pro");
                    for (String[] experiencePro : experiencePros) {
                        try {
                            DaoSIO
                                    .getInstance()
                                    .requeteAction("INSERT INTO `experience_pro` (`id`, `entreprise`, `adresse`, `description`, `annee_debut`, `annee_fin`, `id_cv`) "
                                            + "VALUES (NULL, '"
                                            + experiencePro[1] + "', '"
                                            + experiencePro[2] + "', '"
                                            + experiencePro[3] + "', '"
                                            + simpleDate2.format(simpleDate.parse(experiencePro[4].trim())) + "', '"
                                            + simpleDate2.format(simpleDate.parse(experiencePro[5].trim())) + "', '"
                                            + idCV + "');");
                        } catch (java.text.ParseException ex) {
                            Logger.getLogger(ImportExportFile.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(ImportExportFile.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            JOptionPane.showMessageDialog(this, "Donnée ajouter !");

            data_table.clear();
            jTableListExport.removeAll();
        } else {
            JOptionPane.showMessageDialog(this, "Aucun donnée n'a été import.", "", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jButtonAddImportActionPerformed

    private void jButtonGeneratorPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGeneratorPDFActionPerformed
        Object cvID = jComboBoxListCV.getSelectedItem();

        if(cvID != null){
            String cvIDSelected = cvID.toString();
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File("."));
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int r = chooser.showSaveDialog(null);

            if (chooser.getSelectedFile() != null && r == JFileChooser.APPROVE_OPTION) {

                if(cvIDSelected.matches("^[0-9]+")){
                    if (!cvIDSelected.equals("")) {

                        if (Utilisateur.getInstance().getEstConnecte()) {

                            GeneratorCVPDF g = new GeneratorCVPDF()
                                    .setPath(chooser.getSelectedFile().toPath().toString())
                                    .setCvID(Integer.parseInt(cvIDSelected));

                            try {
                                if(g.genererPDF()){

                                    int reply = JOptionPane.showConfirmDialog(this, Message.MESSAGE_GENERATION_PDF_SUCCESS, Message.TITLE_MESSAGE_GENERATION_PDF_SUCCESS, HEIGHT);

                                    if(reply == JOptionPane.YES_OPTION){
                                        Helpers.showPDF(this, g.getPath());
                                    }

                                }else{
                                    JOptionPane.showMessageDialog(this, Message.MESSAGE_GENERATION_PDF_SUCCESS, Message.TITLE_MESSAGE_GENERATION_PDF_SUCCESS, HEIGHT);

                                }

                            } catch (BadElementException ex) {
                                Logger.getLogger(ImportExportFile.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (IOException ex) {
                                Logger.getLogger(ImportExportFile.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, Message.ID_CV_INVALID, "", JOptionPane.WARNING_MESSAGE);
        }
            
    }//GEN-LAST:event_jButtonGeneratorPDFActionPerformed

    public List<Object> buildData(List<Map<String, Object>> cv) {
        List<Object> finalData = new ArrayList<Object>();
        finalData.add(String.valueOf(Utilisateur.getInstance().getId()));
        finalData.add(Utilisateur.getIdentifiant());
        finalData.add(Utilisateur.getInstance().getNom());
        finalData.add(Utilisateur.getInstance().getPrenom());
        finalData.add(Utilisateur.getInstance().getGenre());
        finalData.add(Utilisateur.getInstance().getNumeroTelephone());
        finalData.add(Utilisateur.getInstance().getCourriel());
        finalData.add(Utilisateur.getInstance().getDateNaissance());

        if (cv != null && cv.size() > 0) {
            for (Map<String, Object> item : cv) {

                List<Object> cvArray = new ArrayList<Object>();
                cvArray.add(String.valueOf(item.get("id")));
                cvArray.add((String) item.get("titre"));
                cvArray.add((String) item.get("description"));
                cvArray.add((String) item.get("signature"));
                cvArray.add((String) item.get("nom_maitrise"));
                cvArray.add(String.valueOf(item.get("maitrise")));

                for (String element : new String[]{"formation", "experience_pro"}) {
                    List<Object> subItemsArray = new ArrayList<Object>();
                    List<Map<String, Object>> subItems = null;
                    try {
                        subItems = Helpers.getIntance().get(new String[]{element}, null,
                                Helpers.getIntance().whereElement("id_cv", item.get("id"), ""));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    if (subItems != null && subItems.size() > 0) {

                        for (Map<String, Object> subItem : subItems) {
                            List<Object> subItemsElement = new ArrayList<Object>();

                            for (Map.Entry<String, Object> entry : subItem.entrySet()) {
                                Object type = entry.getValue();
                                
                                subItemsElement.add(
                                    type != null && type.getClass().getSimpleName().equals("Integer") ? String.valueOf(entry.getValue())
                                    : type != null && type.getClass().getSimpleName().equals("Date") ? Reference.simpleDateSlashes.format((Date) entry.getValue())
                                    : (entry.getValue() != null ? (String) entry.getValue() : "null")
                                );
                            }
                            subItemsArray.add(subItemsElement);
                        }
                    }
                    cvArray.add(subItemsArray);
                }
                finalData.add(cvArray);
            }
        }
        return finalData;
    }

    public void exportToCSV(String AbsolutePath, List<Map<String, Object>> cv) {
        try {
            List<Object> builderData = this.buildData(cv);

            new ExportImportToCSV()
                    .setPathFinding(AbsolutePath)
                    .loadFile()
                    .analyseFile(new String[]{"id_utilisateur", "identifiant", "nom", "prenom", "genre", "numero_telephone", "courriel", "date_de_naissance",
                "id_cv", "titre", "description", "signature", "nom_maitrise", "maitrise", "formation", "experience_pro"}, builderData);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exportToXML(String AbsolutePath, List<Map<String, Object>> cv) {

        Map<String, Object> indexes = new LinkedHashMap<String, Object>();

        indexes.put("utilisateur", new String[]{"id_utilisateur", "identifiant", "nom", "prenom", "genre", "numero_telephone", "courriel", "date_de_naissance"});
        
        ArrayList<CV> c = Utilisateur.getInstance().getCv();
        
        if(c.size() > 0){
            indexes.put("cv", new String[]{"id", "titre", "description", "signature", "nom_maitrise", "maitrise"});
            if(c.get(0).getFormations().size() > 0){
                indexes.put("formation", new String[]{"id", "nom", "lieu", "description", "annee_debut", "annee_fin", "id_cv"});
            }
            if(c.get(0).getExperiencePros().size() > 0){
                indexes.put("experience_pro", new String[]{"id", "entreprise", "adresse", "description", "annee_debut", "annee_fin", "id_cv"});
            }
        }

        List<Object> builderData = this.buildData(cv);

        try {
            new ExportImportToXML()
                .setPathFinding(AbsolutePath)
                .loadFile()
                .analyseFile(indexes, builderData);
        } catch (Exception ex) {
            Logger.getLogger(ImportExportFile.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void exportToJSON(String AbsolutePath, List<Map<String, Object>> cv) {

        Map<String, Object> indexes = new LinkedHashMap<String, Object>();

        indexes.put("utilisateur", new String[]{"id_utilisateur", "identifiant", "nom", "prenom", "genre", "numero_telephone", "courriel", "date_de_naissance"});
        
        ArrayList<CV> c = Utilisateur.getInstance().getCv();
        if(c.size() > 0){
            indexes.put("cv", new String[]{"id", "titre", "description", "signature", "nom_maitrise", "maitrise"});
            if(c.get(0).getFormations().size() > 0){
                indexes.put("formation", new String[]{"id", "nom", "lieu", "description", "annee_debut", "annee_fin", "id_cv"});
            }
            if(c.get(0).getExperiencePros().size() > 0){
                indexes.put("experience_pro", new String[]{"id", "entreprise", "adresse", "description", "annee_debut", "annee_fin", "id_cv"});
            }
        }

        List<Object> builderData = this.buildData(cv);

        try {
            new ExportImportToJSON()
                    .setPathFinding(AbsolutePath)
                    .loadFile()
                    .analyseFile(indexes, builderData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void importCSV(File selectedFile) {
        List<Object> l = null;
        try {
            l = new ExportImportToCSV()
                    .setPathFinding(selectedFile.getAbsolutePath())
                    .loadFile()
                    .setIndexes(new String[]{"id_utilisateur", "identifiant", "nom", "prenom", "genre", "numero_telephone", "courriel", "date_de_naissance",
                "id_cv", "titre", "description", "signature", "nom_maitrise", "maitrise", "formation", "experience_pro"})
                    .importFile()
                    .getData();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (l != null && l.size() > 0) {
            loadDataInTable(l);
        }

    }

    public void importJSON(File selectedFile) {
        JSONObject result = null;
        List<Object> l = new ArrayList<Object>();
        try {
            result = new ExportImportToJSON()
                    .setPathFinding(selectedFile.getAbsolutePath())
                    .loadFile()
                    .importFile()
                    .getDataFile();
        } catch (Exception e) {
            e.printStackTrace();
        }

        l = formatXMLJSON(result);

        if (l != null && l.size() > 0) {
            loadDataInTable(l);
        }
    }

    public void importXML(File selectedFile){
        JSONObject result = null;
        List<Object> l = new ArrayList<Object>();
        try {
            result = new ExportImportToXML()
                .setPathFinding(selectedFile.getAbsolutePath())
                .loadFile()
                .importFile()
                .getDataFile();
        } catch (Exception ex) {
            Logger.getLogger(ImportExportFile.class.getName()).log(Level.SEVERE, null, ex);
        }

        l = formatXMLJSON(result);

        if (l != null && l.size() > 0) {
            loadDataInTable(l);
        }
    }

    private List<Object> formatXMLJSON(JSONObject result){
        List<Object> l = new ArrayList<Object>();
        if (result != null && result.size() > 0) {
            Object utilisateur = result.get("utilisateur");
            if (utilisateur != null && utilisateur instanceof JSONObject) {
                utilisateur = (JSONObject) utilisateur;
            }

            Object cvs = choiceJSONArray(result.get("cv"));

            if (cvs != null && cvs instanceof JSONArray) {
                JSONArray cvsArray = (JSONArray) cvs;

                JSONObject u = (JSONObject) utilisateur;

                cvsArray.forEach(emp -> {
                    Map<String, Object> ocurrences = new HashMap<String, Object>();

                    if(u != null){
                        u.keySet().forEach(keyStr0 -> {
                            Object keyvalue0 = u.get(keyStr0);
                            ocurrences.put((String) keyStr0, keyvalue0);
                        });
                    }

                    if (emp instanceof JSONObject) {
                        JSONObject cvsElement = (JSONObject) emp;

                        cvsElement.keySet().forEach(keyStr -> {
                            Object keyvalue = choiceJSONArray(cvsElement.get(keyStr));
                            if (keyvalue instanceof JSONArray) {
                                JSONArray cvsElementArray = (JSONArray) keyvalue;
                                List<String[]> l_matcher = new ArrayList<String[]>();
                                
                                cvsElementArray.forEach(subemp -> {
                                    if (subemp instanceof JSONObject) {
                                        l_matcher.add(Helpers.GetStringArray((JSONObject) subemp));
                                    }
                                });
                                
                                if(cvsElementArray != null && cvsElementArray.size() > 0){
                                    if(cvsElementArray.get(0) instanceof JSONObject){
                                        ocurrences.put((String) keyStr, cvsElementArray.get(0));
                                    }
                                }
                                ocurrences.put((String) keyStr, l_matcher);
                            } else if (keyvalue instanceof Long) {
                                ocurrences.put((String) keyStr, Long.toString((long) keyvalue));
                            }else {
                                ocurrences.put((String) keyStr, String.valueOf(keyvalue));
                            }

                            System.out.println("key: " + keyStr + " value: " + keyvalue);
                        });
                    }

                    l.add(ocurrences);
                });

            }

        }
        return l;
    }

    private Object choiceJSONArray(Object a){
        Object cvs = null;
        if(a instanceof JSONArray){
            cvs = a;
        }else if(a instanceof JSONObject){
            Object inter = a;

            if(inter != null && inter instanceof JSONObject){
                JSONObject inter1 = (JSONObject) inter;
                
                String i = null;
                for (Object key : inter1.keySet()) {
                    i = (String) key;
                }
                if(inter1.get(i) instanceof JSONObject){
                    JSONArray Obj = new JSONArray();
                    Obj.add(inter1.get(i));
                    cvs = i != null ? Obj : cvs;
                }else{
                    cvs = i != null ? inter1.get(i) : cvs;
                }
            }
        }else{
            cvs = a;
        }
        return cvs;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAddImport;
    private javax.swing.JButton jButtonExport;
    private javax.swing.JButton jButtonGeneratorPDF;
    private javax.swing.JButton jButtonImport;
    private javax.swing.JComboBox<String> jComboBoxFileExport;
    private javax.swing.JComboBox<String> jComboBoxListCV;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTable jTableListExport;
    // End of variables declaration//GEN-END:variables

}