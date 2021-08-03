package com.ant.linker.module.product.importing;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

@Service
public class CSVReaderHelper implements ICSVReaderHelper {

	private Map<String, CSVReader> csvReaders;
	
	public CSVReaderHelper() {
		super();
		csvReaders = new HashMap<>();
	}

	@Override
	public void addFileToLoad(String key, String filePath) {
		try {
			CSVReader csvReader = getCsvReader(filePath);
			csvReaders.put(key, csvReader);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void loadFiles(Map<String, String> filePaths) {
		csvReaders = filePaths.entrySet().stream()
				.collect(Collectors.toMap(elem -> elem.getKey(), elem -> {
					try {
						return getCsvReader(elem.getValue());
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return null;
				}));
	}

	@Override
	public String[] getNextRecordOf(String key) {
		try {
			return csvReaders.get(key).readNext();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private CSVReader getCsvReader(String filePath) throws UnsupportedEncodingException, FileNotFoundException {
		ClassLoader classLoader = getClass().getClassLoader();
		InputStreamReader reader = new InputStreamReader(
				new FileInputStream(classLoader.getResource(filePath).getFile()), "UTF-8");
		CSVParser parser = new CSVParserBuilder().withSeparator('|').build();
		CSVReader csvReader = new CSVReaderBuilder(reader).withCSVParser(parser).withSkipLines(1).build();
		return csvReader;
	}

}
