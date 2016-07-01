
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
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

	private HashSet<String> dataRows;
	private HashSet<String> dataCols;
	private LinkedHashMap<String, Map<String, ?>> isIncluded;

	public ValuesMatrix(HashSet<String> dataRows, HashSet<String> dataCols) {
		super();
		this.dataRows = dataRows;
		this.dataCols = dataCols;
		LinkedHashSet<String> header = new LinkedHashSet<String>();
		header.add(FIRST_COLUMN_NAME);
		header.addAll(dataCols);
		Iterator<String> iterRows = dataRows.iterator();
		Iterator<String> iterCols = dataCols.iterator();
		this.isIncluded = new LinkedHashMap<String, Map<String, ?>>();

		// fill in with false, as this constructor is for creating a template
		// all the cells of the matrix will be empty
		while (iterRows.hasNext()) {
			String currentRow = (String) iterRows.next();
			HashMap<String, String> currentRowValues = new HashMap<String, String>();
			//add a test name
			//currentRowValues.put(FIRST_COLUMN_NAME, currentRow);
			iterCols = dataCols.iterator();
			while (iterCols.hasNext())
				currentRowValues.put(iterCols.next(), new String(""));
			this.isIncluded.put(currentRow, currentRowValues);

		}

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

		// create header for the table, first item is for first column name
		final String[] header = new String[getDataCols().size()+1];
		header[0] = FIRST_COLUMN_NAME;
		Iterator<String> iterCols = dataCols.iterator();
		int iter=1;
		while(iterCols.hasNext()) {
			header[iter] = iterCols.next();
			iter++;
		}

		// create cell processor now, when we know the number of columns
		// it is not needed to identify its items
		final CellProcessor[] processors = new CellProcessor[header.length];

		try {
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
		//reads the stuff from csv
		
		ICsvMapReader mapReader = null;

		try {
			mapReader = new CsvMapReader(new FileReader(filePath), CsvPreference.STANDARD_PREFERENCE);
			// read the header to know the number of cell processors
			// and set our columns set
			// also the header has a right sequence of configurations, while mapReader.read order is random
			final String[] header = mapReader.getHeader(true);
			if(null==header) {
				//file exists, but is is empty
				throw new IOException("file " + filePath + " is empty");
			}
				
			this.setDataCols(new LinkedHashSet<String>(Arrays.asList(header)));
			// add checking for FIRST_COLUMN_NAME in the header - if it doesn't exist, return error
			if(!this.getDataCols().contains(FIRST_COLUMN_NAME)) {
				throw new IOException("First column cannot be mapped, column not found: " + FIRST_COLUMN_NAME);
			} else
				// TODO do we really need to delete it?
				this.getDataCols().remove(FIRST_COLUMN_NAME);
			
			final CellProcessor[] processors = new CellProcessor[header.length];
			Map<String, Object> oneRowRecord;
			System.out.println("\n\nReading the file...");
			// TODO check if it's a valid csv file
			
			while ((oneRowRecord = mapReader.read(header, processors)) != null) {
				String currentRecordId = (String) oneRowRecord.get(FIRST_COLUMN_NAME); 
				this.dataRows.add(currentRecordId);
				oneRowRecord.remove(FIRST_COLUMN_NAME);
				this.isIncluded.put(currentRecordId, oneRowRecord);
			}

		} finally {
			if (mapReader != null)
				mapReader.close();
		}

		return this;
	}
	public ValuesMatrix() {
		// TODO maybe delete it as empty object is not usable
	}

	public HashSet<String> getDataRows() {
		return dataRows;
	}

	public void setDataRows(HashSet<String> dataRows) {
		this.dataRows = dataRows;
	}

	public HashSet<String> getDataCols() {
		return dataCols;
	}

	public void setDataCols(HashSet<String> dataCols) {
		this.dataCols = dataCols;
	}
	
	public boolean isEmpty() {
		if((null==this.dataCols || this.dataCols.size()<1) || (null==this.dataRows || this.dataRows.size()<1))
			return true;
		if(null==this.isIncluded || this.isIncluded.size()<1)
			return true;
		return false;
		
	}

}
