
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Map.Entry;

import org.supercsv.cellprocessor.ParseBool;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvMapReader;
import org.supercsv.io.CsvMapWriter;
import org.supercsv.io.ICsvMapReader;
import org.supercsv.io.ICsvMapWriter;
import org.supercsv.prefs.CsvPreference;

import javafx.util.Pair;

public class ValuesMatrix {
	private static final String FIRST_COLUMN_NAME = "Test name";

	private LinkedHashSet<String> dataRows;
	private LinkedHashSet<String> dataCols;
	private Map<String, Map<String, ?>> isIncluded;

	public ValuesMatrix(LinkedHashSet<String> dataRows, LinkedHashSet<String> dataCols) {
		super();
		this.dataRows = dataRows;
		this.dataCols = new LinkedHashSet<String>();
		this.dataCols.add(FIRST_COLUMN_NAME);
		this.dataCols.addAll(dataCols);
		Iterator<String> iterRows = dataRows.iterator();
		Iterator<String> iterCols = dataCols.iterator();
		this.isIncluded = new HashMap<String, Map<String, ?>>();

		// fill in with false, as this constructor is for creating a template
		// all the cells of the matrix will be empty
		while (iterRows.hasNext()) {
			String currentRow = (String) iterRows.next();
			HashMap<String, String> currentRowValues = new HashMap<String, String>();
			iterCols = dataCols.iterator();
			while (iterCols.hasNext())
				currentRowValues.put(iterCols.next(), new String(""));
			this.isIncluded.put(currentRow, currentRowValues);

		}

	}

	public ValuesMatrix() {
		// TODO maybe delete it as empty object is not usable
	}

	public LinkedHashSet<String> getDataRows() {
		return dataRows;
	}

	public void setDataRows(LinkedHashSet<String> dataRows) {
		this.dataRows = dataRows;
	}

	public LinkedHashSet<String> getDataCols() {
		return dataCols;
	}

	public void setDataCols(LinkedHashSet<String> dataCols) {
		this.dataCols = dataCols;
	}

	public String toPrettyString() {
		// output as a table

		StringBuilder sb = new StringBuilder();
		// header row
		Iterator<String> iterRows = dataRows.iterator();
		Iterator<String> iterCols = dataCols.iterator();
		while (iterCols.hasNext())
			sb.append(iterCols.next() + ",");
		sb.deleteCharAt(sb.length() - 1);
		sb.append(System.getProperty("line.separator"));

		// all other rows
		while (iterRows.hasNext()) {
			String currentRowHeader = iterRows.next();
			iterCols = this.dataCols.iterator();
			sb.append(currentRowHeader + ",");
			while (iterCols.hasNext())
				sb.append(this.isIncluded.get(currentRowHeader).get(iterCols.next()) + ",");
			sb.deleteCharAt(sb.length() - 1);
			sb.append(System.getProperty("line.separator"));
		}

		return sb.toString();
	}

	public void writeToCSV(String fileName) throws IOException {
		// writes its contents to csv located in provided place

		ICsvMapWriter mapWriter = null;

		// create header for the table
		final String[] header = new String[getDataCols().size()];
		getDataCols().toArray(header);

		// create cell processor now, when we know the number of columns
		// first column is always string, other ones for now are always boolean
		// not needed to identify now
		final CellProcessor[] processors = new CellProcessor[header.length];
		// processors[0] = new NotNull();
		// for(int iter=1; iter<header.length; iter++)
		// processors[iter] = new NotNull(new ParseBool());

		try {
			// try to create a file now
			mapWriter = new CsvMapWriter(new FileWriter(fileName), CsvPreference.STANDARD_PREFERENCE);
			// write the file map-by-map
			mapWriter.writeHeader(header);
			for (Entry<String, Map<String, ?>> entry : isIncluded.entrySet()) {
				HashMap<String, Object> oneRow = (HashMap<String, Object>) entry.getValue();
				oneRow.put(FIRST_COLUMN_NAME, entry.getKey());
				mapWriter.write(oneRow, header, processors);
			}
		} finally {
			if (null != mapWriter) {
				mapWriter.close();
			}
		}

	}

	public ValuesMatrix readFromCSV(String filePath) throws IOException {
		ICsvMapReader mapReader = null;

		// TODO check if file exists
		// TODO check if it's a valid csv file
		try {
			mapReader = new CsvMapReader(new FileReader(filePath), CsvPreference.STANDARD_PREFERENCE);
			// read the header to know the number of cell processors
			// and set our columns set
			final String[] header = mapReader.getHeader(true);
			this.setDataCols(new LinkedHashSet<String>(Arrays.asList(header)));
			final CellProcessor[] processors = new CellProcessor[header.length];
			Map<String, Object> oneRowRecord;
			System.out.println("\n\nReading the file...");
			
			while ((oneRowRecord = mapReader.read(header, processors)) != null) {
				System.out.println(oneRowRecord.toString());
			}
			

		} finally {
			if (mapReader != null)
				mapReader.close();
		}
		// TODO read the data
		// TODO create new instance with this data

		return this;
	}

}
