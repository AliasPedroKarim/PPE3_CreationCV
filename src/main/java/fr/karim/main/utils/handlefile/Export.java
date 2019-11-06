package fr.karim.main.utils.handlefile;

import java.util.List;

public interface Export {

	public Object loadFile() throws Exception;
	public Object loadFile(String path);

	public Object analyseData(String[] indexes, List<Object> values);

	public Object getData();

}
