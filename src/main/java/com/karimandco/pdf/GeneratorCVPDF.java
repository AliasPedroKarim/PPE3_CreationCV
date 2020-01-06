package com.karimandco.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.html.WebColors;
import com.itextpdf.text.pdf.FontSelector;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import fr.karim.main.utils.user.CV;
import fr.karim.main.utils.user.ExperiencePro;
import fr.karim.main.utils.user.Formation;
import fr.karim.main.utils.user.Utilisateur;
import fr.karim.references.Reference;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.itextpdf.text.html.WebColors.getRGBColor;

public class GeneratorCVPDF {

    private String path;
    private Integer cvID;

    Integer uniqueID = new Random().nextInt(100000000);

    public GeneratorCVPDF() {
    }

    public String getPath() {
        return path;
    }

    public GeneratorCVPDF setPath(String path) {
        this.path = path.endsWith("pdf") ? path : String.format(Reference.NAME_FILE_EXPORT, path, "pdf");
        return this;
    }

    public Integer getCvID() {
        return cvID;
    }

    public GeneratorCVPDF setCvID(Integer cvID) {
        this.cvID = cvID;
        return this;
    }

    public Boolean genererPDF() throws FileNotFoundException, BadElementException, IOException {

        if (Utilisateur.getInstance().getEstConnecte() && this.getCvID() != null){

            try {

                Document document = new Document(PageSize.A4, 0, 0, 0, 0);
                // Creation de different style de police

                FontSelector selector = new FontSelector();
                Font f1 = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
                selector.addFont(f1);
                Font f2 = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 41);
                selector.addFont(f2);
                Font f3 = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
                f3.setColor(getRGBColor("#318CE7"));
                selector.addFont(f3);
                Font f4 = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 24);
                selector.addFont(f4);
                Font f5 = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
                f5.setColor(getRGBColor("#318CE7"));
                selector.addFont(f5);
                Font f6 = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 1);
                f6.setColor(getRGBColor("#318CE7"));
                selector.addFont(f6);
                String url = Reference.URL_ICON_SOFTWARE;
                Image image = Image.getInstance(url);

                image.scaleToFit(64, 64);

                Image imageUser = null;
                if (Utilisateur.getInstance().getMedia() != null && Utilisateur.getInstance().getMedia().getImage() != null){
                    imageUser = Image.getInstance(Utilisateur.getInstance().getMedia().getImage());
                    imageUser.scaleToFit(100, 100);
                }

                // C'est ici qu'on commence à ecrire le fichier
                PdfWriter.getInstance(document, new FileOutputStream(this.getPath()));
                document.open();
                float[] columnWidths = {10f, 10f, 10f, 10f, 800f};
                PdfPTable Table = new PdfPTable(columnWidths);
                PdfPCell Plein = new PdfPCell(new Phrase(""));
                Plein.setBorderColor(BaseColor.WHITE);
                Table.setWidthPercentage(100);
                Table.setSpacingBefore(0f);
                Table.setSpacingAfter(0f);

                // Travail dans la Premier Cellulue de la table


                PdfPTable Bleu = new PdfPTable(1);
                Bleu.setWidthPercentage(100);
                Bleu.setSpacingBefore(0f);
                Bleu.setSpacingAfter(0f);
                PdfPCell Bleu1 = new PdfPCell(new Phrase(""));
                Bleu1.setBackgroundColor(WebColors.getRGBColor("#318CE7"));
                Bleu1.setBorderColor(BaseColor.WHITE);
                Bleu1.setFixedHeight(842);
                Table.addCell(Bleu1);

                PdfPTable White = new PdfPTable(1);
                White.setWidthPercentage(100);
                White.setSpacingBefore(0f);
                White.setSpacingAfter(0f);
                PdfPCell White1 = new PdfPCell(new Phrase(""));
                White1.setBackgroundColor(BaseColor.WHITE);
                White1.setBorderColor(BaseColor.WHITE);
                White1.setFixedHeight(840);
                Table.addCell(White1);

                PdfPTable Blue = new PdfPTable(1);
                Blue.setWidthPercentage(100);
                Blue.setSpacingBefore(0f);
                Blue.setSpacingAfter(0f);
                PdfPCell Blue1 = new PdfPCell(new Phrase(""));
                Blue1.setBackgroundColor(WebColors.getRGBColor("#318CE7"));
                Blue1.setBorderColor(BaseColor.WHITE);
                Blue1.setFixedHeight(840);
                Table.addCell(Blue1);

                PdfPTable White2 = new PdfPTable(1);
                White2.setWidthPercentage(100);
                White2.setSpacingBefore(0f);
                White2.setSpacingAfter(0f);
                PdfPCell White21 = new PdfPCell(new Phrase(""));
                White21.setBackgroundColor(BaseColor.WHITE);
                White21.setBorderColor(BaseColor.WHITE);
                White21.setFixedHeight(840);
                Table.addCell(White21);


                PdfPCell C1 = new PdfPCell(new Phrase("Gris"));
                C1.setBackgroundColor(BaseColor.LIGHT_GRAY);
                C1.setFixedHeight(840);

                // Travail dans la Seconde Cellulue de la table


                // <editor-fold>
                // Création de la table Principale


                PdfPTable Table2 = new PdfPTable(1);
                Table2.setWidthPercentage(100);
                Table2.setSpacingBefore(0f);
                Table2.setSpacingAfter(0f);
                Table2.setHorizontalAlignment(Element.ALIGN_RIGHT);
                // </editor-fold>
                // Création de la tables avec les info et la photo


                float[] columnWidths3 = {500f, 200f};
                PdfPTable Table3 = new PdfPTable(columnWidths3);
                Table3.setWidthPercentage(100);
                Table3.setSpacingBefore(0f);
                Table3.setSpacingAfter(0f);
                // Création de la Cellule contenant les cellules d'information perso


                PdfPCell PresentationPhoto = new PdfPCell(new Phrase(""));
                PresentationPhoto.addElement(Table3);
                PresentationPhoto.setBorderColor(BaseColor.GREEN);

                // Création des cellules d'information perso



                PdfPCell Para = new PdfPCell(new Phrase(""));
                Para.setBorderColor(BaseColor.WHITE);
                Paragraph para1 = new Paragraph("");
                para1.add(new Paragraph(Utilisateur.getInstance().getNom() + " " + Utilisateur.getInstance().getPrenom(), f2));

                if(Utilisateur.getInstance().getAdresse() != null){
                    if (Utilisateur.getInstance().getAdresse().getN_rue() != null && Utilisateur.getInstance().getAdresse().getNom_rue() != null){
                        para1.add(new Paragraph(Utilisateur.getInstance().getAdresse().getN_rue() + " " + Utilisateur.getInstance().getAdresse().getNom_rue(), f1));
                    }

                    if (Utilisateur.getInstance().getAdresse().getCode_postale() != null && Utilisateur.getInstance().getAdresse().getVille() != null){
                        para1.add(new Paragraph(Utilisateur.getInstance().getAdresse().getCode_postale() + " " + Utilisateur.getInstance().getAdresse().getVille(), f1));
                    }

                    if (Utilisateur.getInstance().getAdresse().getCode_postale() != null){
                        para1.add(new Paragraph(Utilisateur.getInstance().getAdresse().getCode_postale(), f1));
                    }
                }

                para1.add(new Paragraph(Utilisateur.getInstance().getCourriel(), f3));
                para1.add(new Paragraph(Utilisateur.getInstance().getNumeroTelephone(), f1));
                para1.add(new Paragraph(Utilisateur.getInstance().getUrl_site(), f1));
                //para1.add(new Paragraph("Date de Naissance " + Utilisateur.getInstance().getDateNaissance(), f1));
                Para.addElement(para1);

                // Creation de la Cellule Image

                PdfPCell Image = new PdfPCell(new Phrase(""));
                Image.setBorderColor(BaseColor.WHITE);
                Image.addElement(image);

                PdfPCell ImageUser = null;
                if (imageUser != null){
                    ImageUser = new PdfPCell(new Phrase(""));
                    ImageUser.setBorderColor(BaseColor.WHITE);
                    ImageUser.addElement(imageUser);
                }

                // Création de la Cellule


                PdfPCell ExpePro = null;
                PdfPCell Experience = null;
                PdfPCell formationPDF = null;
                PdfPCell FormationC = null;
                if (this.getCvID() != null) {
                    CV cv = Utilisateur.getInstance().returnSingleCV(this.getCvID());
                    List<Formation> formations = cv.getFormations();
                    List<ExperiencePro> experience_pros = cv.getExperiencePros();

                    FormationC = new PdfPCell(new Phrase(""));

                    if (formations != null && formations.size() > 0) {
                        for (Formation formation : formations) {
                            Paragraph FormationCV = new Paragraph("Formation : " + formation.getNom(), f3);
                            FormationCV.add(new Paragraph("Lieu : " + formation.getLieu(), f1));
                            FormationCV.add(new Paragraph("Date :", f1));
                            FormationCV.add(new Paragraph("Date : du "
                                    + (formation.getAnnee_debut() != null ? Reference.simpleDateSlashes2.format(formation.getAnnee_debut()) : "non renseigné") + " au "
                                    + (formation.getAnnee_fin() != null ? Reference.simpleDateSlashes2.format(formation.getAnnee_fin()) : "non renseigné"), f1));
                            FormationCV.add(new Paragraph(formation.getDescription(), f1));
                            FormationC.addElement(FormationCV);
                        }
                    }

                    FormationC.setBorderColor(BaseColor.WHITE);
                    // Création de la categorie Experience Pro


                    ExpePro = new PdfPCell(new Phrase(""));
                    Paragraph Expe = new Paragraph("EXPERIENCE PROFESSIONNELLE", f5);
                    Expe.add(new Paragraph(" ", f6));
                    ExpePro.setBorderColor(BaseColor.WHITE);
                    ExpePro.addElement(Expe);

                    Experience = new PdfPCell(new Phrase(""));

                    if (experience_pros != null && experience_pros.size() > 0) {
                        for (ExperiencePro experience_pro : experience_pros) {
                            Paragraph ExperienceP = new Paragraph("Entreprise : " + experience_pro.getEntreprise(), f3);
                            ExperienceP.add(new Paragraph("Adresse : " + experience_pro.getAdresse(), f1));
                            ExperienceP.add(new Paragraph("Date : du "
                                    + ( experience_pro.getAnnee_debut() != null ? Reference.simpleDateSlashes2.format(experience_pro.getAnnee_debut()) : "non renseigné" ) + " au "
                                    + ( experience_pro.getAnnee_fin()!= null ? Reference.simpleDateSlashes2.format(experience_pro.getAnnee_fin()) : "non renseigné" ), f1));
                            ExperienceP.add(new Paragraph(experience_pro.getDescription(), f1));
                            Experience.addElement(ExperienceP);
                        }
                    }

                    Experience.setBorderColor(BaseColor.WHITE);
                    // Création de la categorie Formation


                    formationPDF = new PdfPCell(new Phrase(""));
                    Paragraph Forma = new Paragraph("FORMATION", f5);
                    Forma.add(new Paragraph(" ", f6));
                    formationPDF.setBorderColor(BaseColor.WHITE);
                    formationPDF.addElement(Forma);
                }

                // Création de la categorie Informatique


                PdfPCell Competence = new PdfPCell(new Phrase(""));
                Paragraph Compete = new Paragraph("COMPETENCE", f5);
                Compete.add(new Paragraph(" ", f6));
                Competence.setBorderColor(BaseColor.WHITE);
                Competence.addElement(Compete);
                // Création de la categorie Langues


                PdfPCell Langues = new PdfPCell(new Phrase(""));
                Paragraph Lange = new Paragraph("LANGUES", f5);
                Lange.add(new Paragraph(" ", f6));
                Langues.setBorderColor(BaseColor.WHITE);
                Langues.addElement(Lange);
                // Création de la categorie Centres D'Interet


                PdfPCell Centres = new PdfPCell(new Phrase(""));
                Paragraph Centr = new Paragraph("CENTRES D'INTERET", f5);
                Centr.add(new Paragraph(" ", f6));
                Centres.setBorderColor(BaseColor.WHITE);
                Centres.addElement(Centr);
                // Création de la barre bleu qui sépare chaque Categorie


                PdfPCell TraitBleu = new PdfPCell(new Phrase(""));
                TraitBleu.setBorderColor(BaseColor.WHITE);
                TraitBleu.setBackgroundColor(WebColors.getRGBColor("#318CE7"));
                TraitBleu.setFixedHeight(1f);
                // Rajout de Toute les Cellule et Table dans la Table Principale


                // Table3.addCell(Image);
                Table3.addCell(Para);
                Table3.addCell(ImageUser);
                Table2.addCell(PresentationPhoto);
                if (ExpePro != null) {
                    Table2.addCell(ExpePro);
                }
                Table2.addCell(TraitBleu);
                if (Experience != null) {
                    Table2.addCell(Experience);
                }
                if (formationPDF != null) {
                    Table2.addCell(formationPDF);
                }
                Table2.addCell(TraitBleu);

                if (FormationC != null){
                    Table2.addCell(FormationC);
                }
                Table2.addCell(Competence);
                Table2.addCell(TraitBleu);
                //Table2.addCell(espaces);
                Table2.addCell(Langues);
                Table2.addCell(TraitBleu);
                // Table2.addCell(espaces);
                Table2.addCell(Centres);
                Table2.addCell(TraitBleu);
                // Table2.addCell(espaces);
                Plein.addElement(Table2);
                Table.addCell(Plein);

                document.add(Table);

                document.close();
                
                return true;

            } catch (DocumentException ex) {
                Logger.getLogger(GeneratorCVPDF.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
}
