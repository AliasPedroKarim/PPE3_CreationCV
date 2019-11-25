package fr.karim.main.utils.handlefile;

import fr.karim.main.utils.Helpers;
import fr.karim.references.Message;
import fr.karim.references.Reference;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.json.Json;
import javax.json.stream.JsonGenerator;
import javax.swing.*;
import java.io.*;
import java.net.URI;
import java.util.*;

public class ExportImportToJSON extends ExportImport {

    private String file = null;
    private Map<String, Object> indexes = null;
    private List<Object> values = null;
	private JSONObject dataFile = null;

	public ExportImportToJSON() {
    }

	public JSONObject getDataFile() {
		return dataFile;
	}

	public ExportImportToJSON setDataFile(JSONObject dataFile) {
		this.dataFile = dataFile;
		return this;
	}

	public String getPathFinding() {
        return pathFinding;
    }

    public ExportImportToJSON setPathFinding(String pathFinding) {
        this.pathFinding = pathFinding;
        return this;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public Map<String, Object> getIndexes() {
        return indexes;
    }

    public ExportImportToJSON setIndexes(Map<String, Object> idexes) {
        this.indexes = idexes;
        return this;
    }

    public List<Object> getValues() {
        return values;
    }

    public ExportImportToJSON setValues(List<Object> values) {
        this.values = values;
        return this;
    }

    @Override
    public ExportImportToJSON loadFile() throws Exception {
        if (this.pathFinding != null) {
            return this.loadFile(this.pathFinding);
        }
        throw new Exception(Message.EXECEPTION_ERROR_PATH);
    }

    public ExportImportToJSON loadFile(URI uri) {
        this.loadFile(uri.getPath());
        return this;
    }

    public ExportImportToJSON loadFile(File file) {
        this.loadFile(file.getAbsolutePath());
        return this;
    }

    public ExportImportToJSON loadFile(String pathFinding) {
        this.file = pathFinding.endsWith("json") ? pathFinding : String.format(Reference.NAME_FILE_EXPORT, pathFinding, "json");
        return this;
    }

    @Override
    public ExportImportToJSON analyseFile() throws Exception {
        if (this.getIndexes() != null && this.getValues() != null) {
            return this.analyseFile(this.getIndexes(), this.getValues());
        }
        throw new Exception("Attention ! On dirait que les [indexes] ou les [values] n'ont pas été définit.");
    }

    public ExportImportToJSON analyseFile(Map<String, Object> idexes) throws Exception {
        this.setIndexes(idexes);
        return this.analyseFile();
    }

    public ExportImportToJSON analyseFile(List<Object> values) throws Exception {
        this.setValues(values);
        return this.analyseFile();
    }

    public ExportImportToJSON analyseFile(Map<String, Object> indexes, List<Object> values) {
        if (this.getFile() != null) {

            if (indexes != null && values != null) {
                FileWriter writer = null;
                JSONParser parser = new JSONParser();
                Object simpleObj = null;

                String[] indexesField;
                int i;

                try {
                    writer = new FileWriter(this.getFile()); // Modify path as per your need
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // JsonGenerator to create JSONObject and store it to file location mentioned above
                JsonGenerator generator = Json.createGenerator(writer);

                List<String> indexes_string = new ArrayList<String>();

                for (Map.Entry<String, Object> entry : indexes.entrySet()) {
                    indexes_string.add(entry.getKey());
                }

                //Begining
                List<Object> items = new ArrayList<>();

                generator
                        .writeStartObject();

                generator
                        .writeStartObject(indexes_string.get(0));

                i = 0;
                indexesField = (String[]) indexes.get(indexes_string.get(0));
                for (Object value : values) {
                    if (value instanceof List) {
                        items.add(value);
                    } else {
                        generator
                                .write((indexesField[i] != null ? indexesField[i] : "element_" + i), value != null ? (String) value : "null");
                    }
                    i++;
                }

                generator
                        .writeEnd();

                // -------
                indexesField = (String[]) indexes.get(indexes_string.get(1));
                generator
                        .writeStartArray(indexes_string.get(1));

                for (Object item : items) {
                    i = 0;
                    generator
                            .writeStartObject();

                    List<Object> subItems = (ArrayList<Object>) item;
                    if (subItems != null && subItems.size() > 0) {

                        int j = 0; // verifier si fromation ou expérience pro
                        for (Object subItem : subItems) {

                            if (subItem instanceof List) {

                                List<Object> subSubItem = (ArrayList<Object>) subItem;

                                generator
                                        .writeStartArray(indexes_string.get(j == 0 ? 2 : 3));

                                for (Object s : subSubItem) {
                                    if(s instanceof List){
                                        String[] indexesField1 = (String[]) indexes.get(indexes_string.get(j == 0 ? 2 : 3));

                                        generator
                                                .writeStartObject();
                                        int k = 0;
                                        for (String l : (ArrayList<String>) s) {

                                            generator.write(indexesField1[k], l);
                                            k++;

                                        }
                                        generator
                                                .writeEnd();
                                    }
                                }
                                j++;
                                generator
                                        .writeEnd();
                            } else {
                                generator
                                        .write((indexesField[i] != null ? indexesField[i] : "element_" + i), subItem != null ? (String) subItem : "null");
                            }
                            i++;
                        }
                    }
                    generator
                            .writeEnd();
                }
                generator
                        .writeEnd();
                // -------

                generator
                        .writeEnd();

                // End
                generator.close();

                try {
                    simpleObj = parser.parse(new FileReader(this.getFile()));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                if (simpleObj != null) {
                    String prettyJson = Helpers.crunchifyPrettyJSONUtility(simpleObj.toString());

                    try (FileWriter file = new FileWriter(this.getFile())) {
                        file.write(prettyJson);
                        JOptionPane.showMessageDialog(null, Message.SUCCESS_EXPORT, Message.TITLE_SUCCESS_EXPORT, JOptionPane.INFORMATION_MESSAGE);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return this;
    }

    @Override
    public ExportImportToJSON importFile() {
        if (this.getFile() != null) {
            //JSON parser object to parse read file
            JSONParser jsonParser = new JSONParser();

            try (FileReader reader = new FileReader(this.getFile())) {
                //Read JSON file
                Object obj = jsonParser.parse(reader);

                if (obj instanceof JSONObject) {
                    JSONObject jsonObj = (JSONObject) obj;
                    this.setDataFile(jsonObj);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return this;
    }

    @Override
    public ExportImportToJSON getData() {
        return null;
    }
}
