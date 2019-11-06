package fr.karim.references;

import java.text.SimpleDateFormat;

public class Reference {
    // Use this constant with String.format and spécifie full path directory and extention file
    public static final String NAME_FILE_EXPORT = "%s/Curriculum_Vitae_-_CV_Creator.%s";

    public static final Boolean DEBUB = true;

	public static final SimpleDateFormat
			simpleDateSlashes = new SimpleDateFormat("dd/mm/yyyy"),
			simpleDateDashes1 = new SimpleDateFormat("dd-mm-yyyy"),
			simpleDateDashes2 = new SimpleDateFormat("yyyy-mm-dd");
}
