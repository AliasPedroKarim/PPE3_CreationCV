package fr.karim.main.utils.handlefile;

import fr.karim.main.utils.Helpers;
import fr.karim.references.Message;
import fr.karim.references.Reference;
import org.apache.commons.csv.*;

import javax.swing.*;
import java.io.*;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExportImportToCSV extends ExportImport {

    private Path file = null;
    private String[] indexes = null;
    private List<Object> values = null;
	protected List<Object> dataFile = null;

	public ExportImportToCSV() {

    }

	public List<Object> getDataFile() {
		return dataFile;
	}

	public ExportImportToCSV setDataFile(List<Object> dataFile) {
		this.dataFile = dataFile;
		return this;
	}

	public String getPathFinding() {
		return pathFinding;
	}

	public ExportImportToCSV setPathFinding(String pathFinding) {
		this.pathFinding = pathFinding;
		return this;
	}

    public Path getFile() {
        return file;
    }

    public ExportImportToCSV setFile(Path file) {
        this.file = file;
        return this;
    }

    public String[] getIndexes() {
        return indexes;
    }

    public ExportImportToCSV setIndexes(String[] indexes) {
        this.indexes = indexes;
        return this;
    }

    public List<Object> getValues() {
        return values;
    }

    public ExportImportToCSV setValues(List<Object> values) {
        this.values = values;
        return this;
    }

    private List<String> recurciveArray(List<String> list, List<Object> values) {

        if (values != null && values.size() > 0) {
            for (Object value : values) {
            }
        }
        return list;
    }

	@Override
    public ExportImportToCSV loadFile() throws Exception {
        if (this.pathFinding != null) {
            return this.loadFile(this.pathFinding);
        }
        throw new Exception(Message.EXECEPTION_ERROR_PATH);
    }

    public ExportImportToCSV loadFile(URI uri) {
        this.loadFile(uri.getPath());
        return this;
    }

    public ExportImportToCSV loadFile(File file) {
        this.loadFile(file.getAbsolutePath());
        return this;
    }

    public ExportImportToCSV loadFile(String pathFinding) {
        this.file = Paths.get(pathFinding.endsWith("csv") ? pathFinding : String.format(Reference.NAME_FILE_EXPORT, pathFinding, "csv"));
        return this;
    }

	@Override
	public ExportImportToCSV analyseFile() throws Exception {
        if (this.getIndexes() != null && this.getValues() != null) {
            return this.analyseFile(this.getIndexes(), this.getValues());
        }
        throw new Exception("Attention ! On dirait que les [indexes] ou les [values] n'ont pas été définit.");
    }

    public ExportImportToCSV analyseFile(String[] idexes) throws Exception {
        this.setIndexes(idexes);
        return this.analyseFile();
    }

    public ExportImportToCSV analyseFile(List<Object> values) throws Exception {
        this.setValues(values);
        return this.analyseFile();
    }

    public ExportImportToCSV analyseFile(String[] indexes, List<Object> values) {
        this.setIndexes(indexes);
        this.setValues(values);
	    if (this.getFile() != null) {
            try {
                if (this.getIndexes() != null && this.getValues() != null) {
                    BufferedWriter writer = Files.newBufferedWriter(this.getFile());

                    CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.RFC4180
                            .withHeader(this.getIndexes())
                            .withQuoteMode(QuoteMode.ALL)
                    );

                    List<String> first_list = new ArrayList<String>();
                    List<Object> items_list = new ArrayList<Object>();

                    // add data to csv
                    for (Object value : values) { // all
                        if (value instanceof List) {
                            items_list.add(value);
                        } else {
                            first_list.add((String) value);
                        }
                    }

                    if (items_list != null && items_list.size() > 0) {
                        for (Object i : items_list) {
							List<String> list = new ArrayList<String>(first_list);

                            List<Object> items = (ArrayList<Object>) i;

                            if (items != null && items.size() > 0) {
                                for (Object item : items) { // cv item
                                    if (item instanceof List) {
                                        List<Object> subItems = (ArrayList<Object>) item;

                                        if (subItems != null && subItems.size() > 0) {
                                            StringBuilder builder = new StringBuilder();
                                            for (Object subItem : subItems) { // fort° ou exp_pro item
                                                builder.append(" [ ");
                                                if (subItem instanceof List) {
                                                    builder.append(String.join(" | ", Helpers.GetStringArray((ArrayList<String>) subItem)));
                                                } else if (subItem instanceof String[]) {
                                                    builder.append(String.join(" | ", (String[]) subItem));
                                                } else {
                                                    builder.append((String) subItem);
                                                }
                                                builder.append(" ] ");
                                            }
                                            list.add(builder.toString());
                                        } else {
                                            list.add("");
                                        }

                                    } else {
                                        list.add((String) item);
                                    }
                                }
                            } else {
                                list.add("");
                            }
                            csvPrinter.printRecord(list);
                        }
                    }
                    csvPrinter.flush();
                    JOptionPane.showMessageDialog(null, Message.SUCCESS_EXPORT, Message.TITLE_SUCCESS_EXPORT, JOptionPane.INFORMATION_MESSAGE);

                    // closing writer connection
                    writer.close();
                }

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
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
    public ExportImportToCSV importFile() {
        this.importFile(this.getIndexes());
        return this;
    }

    public List<Object> importFile(String[] indexes){
        if (this.getFile() != null && indexes != null){
            List<Object> l = new ArrayList<Object>();
            try (
                    Reader reader = Files.newBufferedReader(this.getFile());
                    CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                            .withFirstRecordAsHeader()
                            .withHeader(this.getIndexes())
                            .withIgnoreHeaderCase()
                            .withTrim());
            ) {
                for (CSVRecord csvRecord : csvParser) {
                    Map<String,Object> ocurrences = new HashMap<String, Object>();
                    for (String element : this.getIndexes()) {
                        List<String[]> l_matcher = new ArrayList<String[]>();
                        Pattern pattern = Pattern.compile("\\[(.*?)\\]", Pattern.MULTILINE);
                        Matcher matcher = pattern.matcher(csvRecord.get(element));
                        while (matcher.find()) {
                            l_matcher.add(matcher.group(1).split("\\|"));
                        }
                        if (matcher.find(0)){
                            ocurrences.put(element, l_matcher);
                        }else{
                            ocurrences.put(element, csvRecord.get(element));
                        }
                    }
                    l.add(ocurrences);
                    this.setDataFile(l);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return l;
        }
        return null;
    }

    @Override
    public List<Object> getData() {
        return this.getDataFile();
    }
}
