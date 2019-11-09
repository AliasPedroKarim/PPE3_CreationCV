package fr.karim.main.utils.handlefile;

import java.util.List;

public interface IfaceExportImport {

	public Object loadFile() throws Exception;

	public Object analyseFile() throws Exception ;

	public Object importFile();
	public Object getData();

}
