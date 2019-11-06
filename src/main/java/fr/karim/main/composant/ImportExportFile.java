/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.karim.main.composant;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import fr.karim.main.utils.Helpers;
import fr.karim.main.utils.Utilisateur;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.File;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import fr.karim.main.utils.handlefile.ExportToCSV;
import fr.karim.references.Message;
import fr.karim.references.Reference;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.json.Json;
import javax.json.stream.JsonGenerator;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import fr.karim.connexion.DaoSIO;
import java.io.BufferedReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.table.DefaultTableModel;

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

    public void loadDataInTable(List<Map<String, Object>> data) {

        if (data != null && data.size() > 0) {
            for (Map<String, Object> item : data) {

                data_table.add(item);

                StringBuilder result_formation = new StringBuilder();
                StringBuilder result_experience_pro = new StringBuilder();

                List<String[]> formations = (List<String[]>) item.get("formations");
                for (String[] formation : formations) {
                    result_formation.append("[ ").append(String.join(", ", formation)).append(" ]").append("\n");
                }

                List<String[]> experience_pros = (List<String[]>) item.get("experience_pro");
                for (String[] experience_pro : experience_pros) {
                    result_experience_pro.append("[ ").append(String.join(", ", experience_pro)).append(" ]").append("\n");
                }
                listeExport.addRow(new Object[]{
                    item.get("id"),
                    item.get("titre"),
                    item.get("description"),
                    item.get("signature"),
                    item.get("nom_maitrise"),
                    item.get("maitrise"),
                    !result_formation.toString().equals("") ? result_formation.toString() : "vide",
                    !result_experience_pro.toString().equals("") ? result_experience_pro.toString() : "vide"
                });

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
        jButton1 = new javax.swing.JButton();
        jComboBoxListCV = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();

        jButtonExport.setText("Exporter");
        jButtonExport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonExportMouseClicked(evt);
            }
        });

        jButtonImport.setText("Importer");
        jButtonImport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonImportMouseClicked(evt);
            }
        });

        jComboBoxFileExport.setModel(new javax.swing.DefaultComboBoxModel<>());

        jTableListExport.setModel(new javax.swing.table.DefaultTableModel());
        jScrollPane1.setViewportView(jTableListExport);

        jButtonAddImport.setText("Ajouter");
        jButtonAddImport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonAddImportMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel1.setText("Exporter/Importer (CSV, XML, JSON)");

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel2.setText("Générer (PDF)");

        jButton1.setText("Générer");

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
                                .addComponent(jButton1))
                            .addComponent(jLabel3))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
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
                    .addComponent(jButton1))
                .addGap(10, 10, 10))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(49, 49, 49)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(551, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonExportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonExportMouseClicked
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
    }//GEN-LAST:event_jButtonExportMouseClicked

    private void jButtonImportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonImportMouseClicked
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
                System.out.println(chooser.getSelectedFile().getAbsolutePath());
            } else if (type.equals("Fichier CSV")) {
                importCSV(chooser.getSelectedFile());
            } else if (type.equals("Fichier JSON")) {

            }

            // System.out.println(chooser.getSelectedFile().getAbsolutePath());
        }
    }//GEN-LAST:event_jButtonImportMouseClicked

    private void jButtonAddImportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonAddImportMouseClicked

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

                    List<String[]> formations = (List<String[]>) i.get("formations");
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
    }//GEN-LAST:event_jButtonAddImportMouseClicked

    public void exportToCSV(String AbsolutePath, List<Map<String, Object>> cv) {

        try {

            List<Object> d = new ArrayList<Object>(); // simul element dans cv
            d.add("efsefse");
            d.add("tonsefsefseo");

            List<Object> c = new ArrayList<Object>(); // simul element dans cv
            c.add(d);
            c.add(d);

            List<Object> finalData = new ArrayList<Object>();
            finalData.add(String.valueOf(Utilisateur.getInstance().getId()));
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
                        List<Map<String, Object>> subItems = Helpers.getIntance().get(new String[]{element}, null,
                                Helpers.getIntance().whereElement("id_cv", item.get("id"), ""));

                        if (subItems != null && subItems.size() > 0) {

                            for (Map<String, Object> subItem : subItems) {
                                List<Object> subItemsElement = new ArrayList<Object>();

                                for (Map.Entry<String, Object> entry : subItem.entrySet()) {
                                    String type = entry.getValue().getClass().getSimpleName();
                                    subItemsElement.add(
		                                    type.equals("Integer")  ? String.valueOf(entry.getValue())
				                                    : type.equals("Date")  ? Reference.simpleDateSlashes.format((Date) entry.getValue())
				                                    : (String) entry.getValue()
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

            new ExportToCSV()
                    .setPathFinding(AbsolutePath)
                    .loadFile()
                    .analyseData(new String[]{"id_utilisateur", "identifiant", "nom", "prenom", "genre", "numero_telephone", "courriel", "date_de_naissance",
                "id_cv", "titre", "description", "signature", "nom_maitrise", "maitrise", "formations", "experience_pro"}, finalData);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exportToXML(String AbsolutePath, List<Map<String, Object>> cv) {

        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("PPECreatorCV");
            doc.appendChild(rootElement);

            // User elements
            Element user = doc.createElement("User");
            rootElement.appendChild(user);

            Element id_utilisateur = doc.createElement("idUtilisateur");
            id_utilisateur.appendChild(doc.createTextNode(String.valueOf(Utilisateur.getInstance().getId())));
            user.appendChild(id_utilisateur);

            Element identifiant = doc.createElement("identifiant");
            identifiant.appendChild(doc.createTextNode(Utilisateur.getIdentifiant() != null ? Utilisateur.getIdentifiant() : "null"));
            user.appendChild(identifiant);

            Element nom = doc.createElement("nom");
            nom.appendChild(doc.createTextNode(Utilisateur.getInstance().getNom() != null ? Utilisateur.getInstance().getNom() : "null"));
            user.appendChild(nom);

            Element prenom = doc.createElement("prenom");
            prenom.appendChild(doc.createTextNode(Utilisateur.getInstance().getPrenom() != null ? Utilisateur.getInstance().getPrenom() : "null"));
            user.appendChild(prenom);

            Element genre = doc.createElement("genre");
            genre.appendChild(doc.createTextNode(Utilisateur.getInstance().getGenre() != null ? Utilisateur.getInstance().getGenre() : "null"));
            user.appendChild(genre);

            Element numero_telephone = doc.createElement("numeroTelephone");
            numero_telephone.appendChild(doc.createTextNode(Utilisateur.getInstance().getNumeroTelephone() != null ? Utilisateur.getInstance().getNumeroTelephone() : "null"));
            user.appendChild(numero_telephone);

            Element courriel = doc.createElement("courriel");
            courriel.appendChild(doc.createTextNode(Utilisateur.getInstance().getCourriel() != null ? Utilisateur.getInstance().getCourriel() : "null"));
            user.appendChild(courriel);

            Element date_de_naissance = doc.createElement("date_de_naissance");
            date_de_naissance.appendChild(doc.createTextNode(Utilisateur.getInstance().getDateNaissance() != null ? Utilisateur.getInstance().getDateNaissance() : "null"));
            user.appendChild(date_de_naissance);

            // CV elements
            Element cvs = doc.createElement("CVs");
            rootElement.appendChild(cvs);

            if (cv != null && cv.size() > 0) {
                for (Map<String, Object> item : cv) {
                    Element cvElement = doc.createElement("CV");
                    cvs.appendChild(cvElement);

                    Attr attr = doc.createAttribute("id");
                    attr.setValue(String.valueOf(item.get("id")));
                    cvElement.setAttributeNode(attr);

                    Element id_cv = doc.createElement("id_cv");
                    id_cv.appendChild(doc.createTextNode(String.valueOf(item.get("id_cv"))));
                    cvElement.appendChild(id_cv);

                    Element titre = doc.createElement("titre");
                    titre.appendChild(doc.createTextNode(item.get("titre") != null ? (String) item.get("titre") : "null"));
                    cvElement.appendChild(titre);

                    Element description = doc.createElement("description");
                    description.appendChild(doc.createTextNode(item.get("description") != null ? (String) item.get("description") : "null"));
                    cvElement.appendChild(description);

                    Element signature = doc.createElement("signature");
                    signature.appendChild(doc.createTextNode(item.get("signature") != null ? (String) item.get("signature") : "null"));
                    cvElement.appendChild(signature);

                    Element nom_maitrise = doc.createElement("nom_maitrise");
                    nom_maitrise.appendChild(doc.createTextNode(item.get("nom_maitrise") != null ? (String) item.get("nom_maitrise") : "null"));
                    cvElement.appendChild(nom_maitrise);

                    Element maitrise = doc.createElement("maitrise");
                    maitrise.appendChild(doc.createTextNode(item.get("maitrise") != null ? (String) item.get("maitrise") : "null"));
                    cvElement.appendChild(maitrise);

                    // Formations
                    Element formationsElement = doc.createElement("Formations");
                    cvElement.appendChild(formationsElement);

                    try {
                        List<Map<String, Object>> formations = Helpers.getIntance().get(new String[]{"formation"}, null,
                                Helpers.getIntance().whereElement("id_cv", item.get("id"), ""));

                        if (formations != null && formations.size() > 0) {

                            for (Map<String, Object> formation : formations) {
                                Element formationElement = doc.createElement("Formation");
                                formationsElement.appendChild(formationElement);

                                Attr attrFormation = doc.createAttribute("id");
                                attrFormation.setValue(String.valueOf(formation.get("id")));
                                formationElement.setAttributeNode(attrFormation);

                                Element idFormation = doc.createElement("id");
                                idFormation.appendChild(doc.createTextNode(String.valueOf(formation.get("id"))));
                                formationElement.appendChild(idFormation);

                                Element nomFormation = doc.createElement("nom");
                                nomFormation.appendChild(doc.createTextNode(formation.get("nom") != null ? (String) formation.get("nom") : "null"));
                                formationElement.appendChild(nomFormation);

                                Element lieuFormation = doc.createElement("lieu");
                                lieuFormation.appendChild(doc.createTextNode(formation.get("lieu") != null ? (String) formation.get("lieu") : "null"));
                                formationElement.appendChild(lieuFormation);

                                Element descriptionFormation = doc.createElement("description");
                                descriptionFormation.appendChild(doc.createTextNode(formation.get("description") != null ? (String) formation.get("description") : "null"));
                                formationElement.appendChild(descriptionFormation);

                                Element annee_debutFormation = doc.createElement("annee_debut");
                                annee_debutFormation.appendChild(doc.createTextNode(formation.get("annee_debut") != null ? simpleDate.format((Date) formation.get("annee_debut")) : "null"));
                                formationElement.appendChild(annee_debutFormation);

                                Element annee_finFormation = doc.createElement("annee_fin");
                                annee_finFormation.appendChild(doc.createTextNode(formation.get("annee_fin") != null ? simpleDate.format((Date) formation.get("annee_fin")) : "null"));
                                formationElement.appendChild(annee_finFormation);

                            }

                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    // Experience Pro
                    Element experienceProsElement = doc.createElement("ExperiencePros");
                    cvElement.appendChild(experienceProsElement);

                    try {
                        List<Map<String, Object>> experiencePros = Helpers.getIntance().get(new String[]{"experience_pro"}, null,
                                Helpers.getIntance().whereElement("id_cv", item.get("id"), ""));

                        if (experiencePros != null && experiencePros.size() > 0) {

                            for (Map<String, Object> experiencePro : experiencePros) {
                                Element experienceProElement = doc.createElement("ExperiencePro");
                                experienceProsElement.appendChild(experienceProElement);

                                Attr attrExperiencePro = doc.createAttribute("id");
                                attrExperiencePro.setValue(String.valueOf(experiencePro.get("id")));
                                experienceProElement.setAttributeNode(attrExperiencePro);

                                Element idExperiencePro = doc.createElement("id");
                                idExperiencePro.appendChild(doc.createTextNode(String.valueOf(experiencePro.get("id"))));
                                experienceProElement.appendChild(idExperiencePro);

                                Element entrepriseExperiencePro = doc.createElement("entreprise");
                                entrepriseExperiencePro.appendChild(doc.createTextNode(experiencePro.get("entreprise") != null ? (String) experiencePro.get("entreprise") : "null"));
                                experienceProElement.appendChild(entrepriseExperiencePro);

                                Element adresseExperiencePro = doc.createElement("adresse");
                                adresseExperiencePro.appendChild(doc.createTextNode(experiencePro.get("adresse") != null ? (String) experiencePro.get("adresse") : "null"));
                                experienceProElement.appendChild(adresseExperiencePro);

                                Element descriptionExperiencePro = doc.createElement("description");
                                descriptionExperiencePro.appendChild(doc.createTextNode(experiencePro.get("description") != null ? (String) experiencePro.get("description") : "null"));
                                experienceProElement.appendChild(descriptionExperiencePro);

                                Element annee_debutExperiencePro = doc.createElement("annee_debut");
                                annee_debutExperiencePro.appendChild(doc.createTextNode(experiencePro.get("annee_debut") != null ? simpleDate.format((Date) experiencePro.get("annee_debut")) : "null"));
                                experienceProElement.appendChild(annee_debutExperiencePro);

                                Element annee_finExperiencePro = doc.createElement("annee_fin");
                                annee_finExperiencePro.appendChild(doc.createTextNode(experiencePro.get("annee_debut") != null ? simpleDate.format((Date) experiencePro.get("annee_fin")) : "null"));
                                experienceProElement.appendChild(annee_finExperiencePro);

                            }

                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            }

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(String.format(Reference.NAME_FILE_EXPORT, AbsolutePath, "xml"));

            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.transform(source, result);

            JOptionPane.showMessageDialog(this, Message.SUCCESS_EXPORT, "Exportation complete", JOptionPane.INFORMATION_MESSAGE);

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }

    }

    public void exportToJSON(String AbsolutePath, List<Map<String, Object>> cv) {

        FileWriter writer = null;
        JSONParser parser = new JSONParser();
        Object simpleObj = null;

        String f = String.format(Reference.NAME_FILE_EXPORT, AbsolutePath, "json");

        System.out.println(f);

        try {
            writer = new FileWriter(String.format(Reference.NAME_FILE_EXPORT, AbsolutePath, "json")); // Modify path as per your need
        } catch (IOException e) {
            e.printStackTrace();
        }

        // JsonGenerator to create JSONObject and store it to file location mentioned above
        JsonGenerator generator = Json.createGenerator(writer);

        generator
                .writeStartObject();

        generator
                .writeStartObject("User")
                .write("id_utilisateur", Utilisateur.getInstance().getId())
                .write("identifiant", Utilisateur.getIdentifiant())
                .write("nom", Utilisateur.getInstance().getNom() != null ? Utilisateur.getInstance().getNom() : "null")
                .write("prenom", Utilisateur.getInstance().getPrenom() != null ? Utilisateur.getInstance().getPrenom() : "null")
                .write("genre", Utilisateur.getInstance().getGenre() != null ? Utilisateur.getInstance().getGenre() : "null")
                .write("numero_telephone", Utilisateur.getInstance().getNumeroTelephone() != null ? Utilisateur.getInstance().getNumeroTelephone() : "null")
                .write("courriel", Utilisateur.getInstance().getCourriel() != null ? Utilisateur.getInstance().getCourriel() : "null")
                .write("date_de_naissance", Utilisateur.getInstance().getDateNaissance() != null ? Utilisateur.getInstance().getDateNaissance() : "null")
                .writeEnd();

        generator
                .writeStartArray("CV");
        if (cv != null && cv.size() > 0) {

            for (Map<String, Object> item : cv) {
                generator
                        .writeStartObject();
                generator
                        .write("id", (int) item.get("id"))
                        .write("titre", item.get("titre") != null ? (String) item.get("titre") : "null")
                        .write("description", item.get("description") != null ? (String) item.get("description") : "null")
                        .write("signature", item.get("signature") != null ? (String) item.get("signature") : "null")
                        .write("nom_maitrise", item.get("nom_maitrise") != null ? (String) item.get("nom_maitrise") : "null")
                        .write("maitrise", item.get("maitrise") != null ? (String) item.get("maitrise") : "null");

                try {
                    List<Map<String, Object>> formations = Helpers.getIntance().get(new String[]{"formation"}, null,
                            Helpers.getIntance().whereElement("id_cv", item.get("id"), ""));

                    generator
                            .writeStartArray("formation");

                    if (formations != null && formations.size() > 0) {

                        for (Map<String, Object> formation : formations) {
                            generator
                                    .writeStartObject()
                                    .write("id", (int) formation.get("id"))
                                    .write("nom", (String) formation.get("nom"))
                                    .write("lieu", (String) formation.get("lieu"))
                                    .write("description", (String) formation.get("description"))
                                    .write("annee_debut", simpleDate.format((Date) formation.get("annee_debut")))
                                    .write("annee_fin", simpleDate.format((Date) formation.get("annee_fin")))
                                    .writeEnd();
                        }
                    }

                    generator
                            .writeEnd();

                    // --------------------------
                    List<Map<String, Object>> experiencePros = Helpers.getIntance().get(new String[]{"experience_pro"}, null,
                            Helpers.getIntance().whereElement("id_cv", item.get("id"), ""));

                    generator
                            .writeStartArray("experience_pro");

                    if (experiencePros != null && experiencePros.size() > 0) {

                        for (Map<String, Object> experiencePro : experiencePros) {
                            generator
                                    .writeStartObject()
                                    .write("id", (int) experiencePro.get("id"))
                                    .write("entreprise", (String) experiencePro.get("entreprise"))
                                    .write("adresse", (String) experiencePro.get("adresse"))
                                    .write("description", (String) experiencePro.get("description"))
                                    .write("annee_debut", simpleDate.format((Date) experiencePro.get("annee_debut")))
                                    .write("annee_fin", simpleDate.format((Date) experiencePro.get("annee_fin")))
                                    .writeEnd();
                        }
                    }

                    generator
                            .writeEnd();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                generator
                        .writeEnd();
            }
        }
        generator
                .writeEnd();
        generator
                .writeEnd();

        generator.close();

        try {
            simpleObj = parser.parse(new FileReader(String.format(Reference.NAME_FILE_EXPORT, AbsolutePath, "json")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (simpleObj != null) {
            String prettyJson = Helpers.crunchifyPrettyJSONUtility(simpleObj.toString());

            try (FileWriter file = new FileWriter(String.format(Reference.NAME_FILE_EXPORT, AbsolutePath, "json"))) {
                file.write(prettyJson);
                JOptionPane.showMessageDialog(this, Message.SUCCESS_EXPORT, "Exportation complete", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void importCSV(File selectedFile) {

        
        
        List<Map<String, Object>> l = new ArrayList<Map<String, Object>>();
        try (CSVReader csvReader = new CSVReader(new FileReader(selectedFile));) {
            String[] values = null;
            int i = 0;
            String[] indexes = null;

            while ((values = csvReader.readNext()) != null) {
                if (i == 0) {
                    indexes = values;
                } else {
                    if (indexes != null) {
                        Map<String, Object> m = new HashMap<String, Object>();
                        int j = 0;
                        for (String index : indexes) {
                            // Pattern test = Pattern.compile("(\\[(.*)\\])", Pattern.DOTALL);
                            List<String[]> l_matcher = new ArrayList<String[]>();
                            Pattern pattern = Pattern.compile("\\[(.*?)\\]", Pattern.MULTILINE);
                            Matcher matcher = pattern.matcher(values[j]);
                            while (matcher.find()) {
                                l_matcher.add(matcher.group(1).split("\\|"));
                            }
                            if (matcher.find(0)) {
                                m.put(index, l_matcher);
                            } else {
                                m.put(index, values[j]);
                            }
                            j++;
                        }
                        l.add(m);
                    }
                }
                i++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

        if (l != null && l.size() > 0) {
            loadDataInTable(l);
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonAddImport;
    private javax.swing.JButton jButtonExport;
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

/*



        // first create file object for file placed at location
        // specified by filepath
        File file = new File(String.format(Reference.NAME_FILE_EXPORT, AbsolutePath, "csv"));
        try {
            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(file);

            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile);

            // adding header to csv
            String[] header = {"id_utilisateur", "identifiant", "nom", "prenom", "genre", "numero_telephone", "courriel", "date_de_naissance",
                 "id_cv", "titre", "description", "signature", "nom_maitrise", "maitrise", "formations", "experience_pro"};
            writer.writeNext(header);

            // add data to csv
            ArrayList<String> list = new ArrayList<>();

            if (Utilisateur.getInstance().getEstConnecte()) {
                list.add(String.valueOf(Utilisateur.getInstance().getId()));
                list.add(Utilisateur.getIdentifiant());
                list.add(Utilisateur.getInstance().getNom());
                list.add(Utilisateur.getInstance().getPrenom());
                list.add(Utilisateur.getInstance().getGenre());
                list.add(Utilisateur.getInstance().getNumeroTelephone());
                list.add(Utilisateur.getInstance().getCourriel());
                list.add(Utilisateur.getInstance().getDateNaissance());

                if (cv != null && cv.size() > 0) {
                    for (Map<String, Object> item : cv) {

                        list.add(String.valueOf(item.get("id")));
                        list.add((String) item.get("titre"));
                        list.add((String) item.get("description"));
                        list.add((String) item.get("signature"));
                        list.add((String) item.get("nom_maitrise"));
                        list.add(String.valueOf(item.get("maitrise")));

                        try {
                            List<Map<String, Object>> formations = Helpers.getIntance().get(new String[]{"formation"}, null,
                                    Helpers.getIntance().whereElement("id_cv", item.get("id"), ""));

                            if (formations != null && formations.size() > 0) {
                                StringBuilder fBuilder = new StringBuilder();
                                for (Map<String, Object> formation : formations) {
                                    fBuilder.append(" [ ");
                                    fBuilder.append(String.join(" | ", new String[]{
                                        String.valueOf(formation.get("id")),
                                        (String) formation.get("nom"),
                                        (String) formation.get("lieu"),
                                        (String) formation.get("description"),
                                        simpleDate.format((Date) formation.get("annee_debut")),
                                        simpleDate.format((Date) formation.get("annee_fin"))
                                    }));
                                    fBuilder.append(" ] ");
                                }
                                list.add(fBuilder.toString());
                            } else {
                                list.add("");
                            }

                            List<Map<String, Object>> experiencePros = Helpers.getIntance().get(new String[]{"experience_pro"}, null,
                                    Helpers.getIntance().whereElement("id_cv", item.get("id"), ""));

                            if (experiencePros != null && experiencePros.size() > 0) {
                                StringBuilder fBuilder = new StringBuilder();
                                for (Map<String, Object> experiencePro : experiencePros) {
                                    fBuilder.append(" [ ");
                                    fBuilder.append(String.join(" | ", new String[]{
                                        String.valueOf(experiencePro.get("id")),
                                        (String) experiencePro.get("entreprise"),
                                        (String) experiencePro.get("adresse"),
                                        (String) experiencePro.get("description"),
                                        simpleDate.format((Date) experiencePro.get("annee_debut")),
                                        simpleDate.format((Date) experiencePro.get("annee_fin"))
                                    }));
                                    fBuilder.append(" ] ");
                                }
                                list.add(fBuilder.toString());
                            } else {
                                list.add("");
                            }

                        } catch (SQLException ex) {
                            Logger.getLogger(ImportExportFile.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        writer.writeNext(Helpers.GetStringArray(list));

                        JOptionPane.showMessageDialog(this, Message.SUCCESS_EXPORT, "Exportation complete", JOptionPane.INFORMATION_MESSAGE);

                    }
                }
            }

            // closing writer connection
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
*/
