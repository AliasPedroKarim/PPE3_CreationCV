package fr.karim.main.utils.handlefile;

import com.opencsv.CSVWriter;
import fr.karim.main.utils.Helpers;
import fr.karim.references.Message;
import fr.karim.references.Reference;
import org.apache.commons.csv.QuoteMode;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class ExportToCSV implements Export {

    private String pathFinding = null;
    private File file = null;
    private String[] idexes = null;
    private List<Object> values = null;

    public ExportToCSV() {

    }

    public String getPathFinding() {
        return pathFinding;
    }

    public ExportToCSV setPathFinding(String pathFinding) {
        this.pathFinding = String.format(Reference.NAME_FILE_EXPORT, pathFinding, "csv");
        return this;
    }

    public File getFile() {
        return file;
    }

    public ExportToCSV setFile(File file) {
        this.file = file;
        return this;
    }

    public String[] getIdexes() {
        return idexes;
    }

    public ExportToCSV setIdexes(String[] idexes) {
        this.idexes = idexes;
        return this;
    }

    public List<Object> getValues() {
        return values;
    }

    public ExportToCSV setValues(List<Object> values) {
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

    public ExportToCSV loadFile() throws Exception {
        if (this.pathFinding != null) {
            return this.loadFile(this.pathFinding);
        }
        throw new Exception(Message.EXECEPTION_ERROR_PATH);
    }

    @Override
    public ExportToCSV loadFile(String pathFinding) {
        this.file = new File(pathFinding.endsWith("csv") ? pathFinding : String.format(Reference.NAME_FILE_EXPORT, pathFinding, "csv"));
        return this;
    }

    /*
	format array :

	<array> [
		item,
		item,
		<array> [
			item,
			item,
			<array> [
				<array> [ ],
				<array> [ ]
			],
			<array> [
				<array> [ ],
				<array> [ ],
				<array> [ ],
			]
		],
		<array> [
			...
		]
	]
     */
    public ExportToCSV analyseData() throws Exception {
        if (this.idexes != null && this.values != null) {
            return this.analyseData(this.idexes, this.values);
        }
        throw new Exception("Attention ! Soit les [indexes] ou les [values] n'ont pas été définit.");
    }

    public ExportToCSV analyseData(String[] idexes) throws Exception {
        this.setIdexes(idexes);
        return this.analyseData();
    }

    public ExportToCSV analyseData(List<Object> values) throws Exception {
        this.setValues(values);
        return this.analyseData();
    }

    @Override
    public ExportToCSV analyseData(String[] indexes, List<Object> values) {
        this.setIdexes(indexes);
        this.setValues(values);

        if (this.file != null) {
            try {
                if (this.idexes != null) {

                    BufferedWriter writer = Files.newBufferedWriter(Paths.get(this.getPathFinding()));

                    org.apache.commons.csv.CSVPrinter csvPrinter = new org.apache.commons.csv.CSVPrinter(writer, org.apache.commons.csv.CSVFormat.RFC4180
                            .withHeader(this.idexes).withQuoteMode(QuoteMode.ALL));

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
                            List<String> list = new ArrayList<String>();
                            list.retainAll(first_list);

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
            this.analyseData(indexes, values);
        }
        return this;
    }

    @Override
    public ExportToCSV getData() {
        return this;
    }
}
