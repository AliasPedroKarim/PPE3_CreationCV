package fr.karim.main.utils.handlefile;

import fr.karim.main.utils.Helpers;
import fr.karim.references.Message;
import fr.karim.references.Reference;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExportImportToXML extends ExportImport {

    private String file = null;
    private Map<String, Object> indexes = null;
    private List<Object> values = null;
    protected JSONObject dataFile = null;

    public ExportImportToXML() {
    }

    public String getFile() {
        return file;
    }

    public ExportImportToXML setFile(String file) {
        this.file = file;
        return this;
    }

    public String getPathFinding() {
        return pathFinding;
    }

    public ExportImportToXML setPathFinding(String pathFinding) {
        this.pathFinding = pathFinding;
        return this;
    }

    public Map<String, Object> getIndexes() {
        return indexes;
    }

    public ExportImportToXML setIndexes(Map<String, Object> indexes) {
        this.indexes = indexes;
        return this;
    }

    public List<Object> getValues() {
        return values;
    }

    public ExportImportToXML setValues(List<Object> values) {
        this.values = values;
        return this;
    }

    public JSONObject getDataFile() {
        return dataFile;
    }

    public ExportImportToXML setDataFile(JSONObject dataFile) {
        this.dataFile = dataFile;
        return this;
    }

    @Override
    public ExportImportToXML loadFile() throws Exception {
        if (this.pathFinding != null) {
            return this.loadFile(this.pathFinding);
        }
        throw new Exception(Message.EXECEPTION_ERROR_PATH);
    }

    public ExportImportToXML loadFile(URI uri) {
        this.loadFile(uri.getPath());
        return this;
    }

    public ExportImportToXML loadFile(File file) {
        this.loadFile(file.getAbsolutePath());
        return this;
    }

    public ExportImportToXML loadFile(String pathFinding) {
        this.file = pathFinding.endsWith("xml") ? pathFinding : String.format(Reference.NAME_FILE_EXPORT, pathFinding, "xml");
        return this;
    }

    @Override
    public ExportImportToXML analyseFile() throws Exception {
        if (this.getIndexes() != null && this.getValues() != null) {
            return this.analyseFile(this.getIndexes(), this.getValues());
        }
        throw new Exception("Attention ! On dirait que les [indexes] ou les [values] n'ont pas été définit.");
    }

    public ExportImportToXML analyseFile(Map<String, Object> idexes) throws Exception {
        this.setIndexes(idexes);
        return this.analyseFile();
    }

    public ExportImportToXML analyseFile(List<Object> values) throws Exception {
        this.setValues(values);
        return this.analyseFile();
    }

    public ExportImportToXML analyseFile(Map<String, Object> indexes, List<Object> values) throws Exception {
        this.setIndexes(indexes);
        this.setValues(values);
        if (this.getFile() != null) {
            if (this.getIndexes() != null && this.getValues() != null) {

                try {

                    List<String> indexes_string = Helpers.getIndexesMap(indexes);
                    int i;
                    String[] indexesField;
                    List<Object> items = new ArrayList<Object>();

                    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

                    // root elements
                    Document doc = docBuilder.newDocument();
                    Element rootElement = doc.createElement("root");
                    doc.appendChild(rootElement);

                    i = 0;
                    indexesField = (String[]) indexes.get(indexes_string.get(0));

                    Element parentElement = doc.createElement(indexes_string.get(0));
                    rootElement.appendChild(parentElement);
                    
                    for (Object value : values) {
                        if (value instanceof List) {
                            items.add(value);
                        } else {

                            Element element = doc.createElement((indexesField[i] != null ? indexesField[i] : "element_" + i));
                            element.appendChild(doc.createTextNode(value != null ? (String) value : "null"));
                            parentElement.appendChild(element);
                        }
                        i++;
                    }

                    indexesField = (String[]) indexes.get(indexes_string.get(1));
                    Element cvs = doc.createElement(indexes_string.get(1));
                    rootElement.appendChild(cvs);

                    for (Object item : items) {
                        i = 0;

                        List<Object> subItems = (ArrayList<Object>) item;
                        if (subItems != null && subItems.size() > 0) {

                            Element cvElement = doc.createElement(indexes_string.get(1) + "Element");
                            cvs.appendChild(cvElement);

                            Attr attr = doc.createAttribute("id");
                            attr.setValue((String) subItems.get(0));
                            cvElement.setAttributeNode(attr);

                            int j = 2; // verifier si fromation ou expérience pro
                            for (Object subItem : subItems) {

                                if (subItem instanceof List) {

                                    List<Object> subSubItem = (ArrayList<Object>) subItem;

                                    if(indexes_string.size() > j){
                                        Element formationsElement = doc.createElement(indexes_string.get(j));
                                        cvElement.appendChild(formationsElement);

                                        for (Object s : subSubItem) {
                                            if (s instanceof List) {
                                                String[] indexesField1 = (String[]) indexes.get(indexes_string.get(j));
                                                List<Object> subAttr = (ArrayList<Object>) s;

                                                Element formationElement = doc.createElement(indexes_string.get(j) + "Element");
                                                formationsElement.appendChild(formationElement);

                                                Attr attrFormation = doc.createAttribute("id");
                                                attrFormation.setValue((String) subAttr.get(0));
                                                formationElement.setAttributeNode(attrFormation);

                                                int k = 0;
                                                for (String l : (ArrayList<String>) s) {
                                                    if(k < indexesField1.length){
                                                        System.out.println(((List) s).size() + " -> " + k);
                                                        Element idFormation = doc.createElement(indexesField1[k]);
                                                        idFormation.appendChild(doc.createTextNode(l));
                                                        formationElement.appendChild(idFormation);
                                                        k++;
                                                    }
                                                }
                                            }
                                        }
                                        j++;
                                    }
                                } else {
                                    Element element = doc.createElement((indexesField[i] != null ? indexesField[i] : "element_" + i));
                                    element.appendChild(doc.createTextNode(subItem != null ? (String) subItem : "null"));
                                    cvElement.appendChild(element);
                                }
                                i++;
                            }
                        }
                    }

                    // write the content into xml file
                    TransformerFactory transformerFactory = TransformerFactory.newInstance();
                    Transformer transformer = transformerFactory.newTransformer();
                    DOMSource source = new DOMSource(doc);
                    StreamResult result = new StreamResult(this.getFile());

                    // Output to console for testing
                    // StreamResult result = new StreamResult(System.out);
                    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
                    transformer.transform(source, result);

                    JOptionPane.showMessageDialog(null, Message.SUCCESS_EXPORT, Message.TITLE_SUCCESS_EXPORT, JOptionPane.INFORMATION_MESSAGE);

                } catch (ParserConfigurationException pce) {
                    pce.printStackTrace();
                } catch (TransformerException tfe) {
                    tfe.printStackTrace();
                }
            }
            
            
        } else {
            try {
                this.loadFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.analyseFile(indexes, values);
        }
        return this;
    }

    @Override
    public ExportImportToXML importFile() {
        if (this.getFile() != null) {
            String xml = null;
            JSONParser jsonParser = new JSONParser();

            try {
                xml = FileUtils.readFileToString(new File(this.getFile()));
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (xml != null) {
                System.out.println(org.json.XML.toJSONObject(xml).toString());
                //Read JSON file
                Object obj = null;
                try {
                    obj = jsonParser.parse(org.json.XML.toJSONObject(xml).toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                if (obj instanceof JSONObject) {
                    JSONObject jsonObj = (JSONObject) obj;
                    if (jsonObj != null && jsonObj.get("root") instanceof JSONObject) {
                        this.setDataFile((JSONObject) jsonObj.get("root"));
                    }
                }
            }
        }
        return this;
    }

    @Override
    public JSONObject getData() {
        return this.getDataFile();
    }
}
