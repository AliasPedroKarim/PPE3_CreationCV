package fr.karim.references;

import java.awt.Color;
import java.text.SimpleDateFormat;

public final class Reference {
    // Use this constant with String.format and spécifie full path directory and extention file
    public static final String 
            NAME_FILE_EXPORT = "%s/Curriculum_Vitae_-_CV_Creator.%s",
            NAME_SOFTWARE = "CV Creator Software",
            TITLE_NAME_SOFTWARE = "%s - " + Reference.NAME_SOFTWARE;

    public static final Boolean DEBUB = true;

    public static final SimpleDateFormat
                    simpleDateSlashes = new SimpleDateFormat("dd/mm/yyyy"),
                    simpleDateSlashes2 = new SimpleDateFormat("dd/MM/yyyy"),
                    simpleDateDashes1 = new SimpleDateFormat("dd-mm-yyyy"),
                    simpleDateDashes2 = new SimpleDateFormat("yyyy-mm-dd"),
                    simpleDateDashes3 = new SimpleDateFormat("yyyy-MM-dd");

    public static Boolean ETAT_DB = true;
    
    public static String[] GENRE_USER = new String[]{ "Femme", "Homme", "Non spécifier" };
        
    // Status Utilisateur Reference
    /*
        - 0 = utilisateur normal
        - 1 = utilisateur administrateur
    */

    public static String[] STATUS = new String[]{ "Utilisateur", "Administrateur" };
    
    public static Color MAIN_DARK = new Color(25, 28, 32);
    public static Color COLOR_VALIDATED = new Color(119, 221, 119);
    public static Color COLOR_NOT_VALIDATED = new Color(236, 25, 25);
}
