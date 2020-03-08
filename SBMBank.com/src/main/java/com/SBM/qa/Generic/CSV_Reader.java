package com.SBM.qa.Generic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.Properties;

import com.opencsv.CSVReader;

public class CSV_Reader implements IAutoConst {

	public static String cell_value;
	public static Properties Pro;
	public static String proValue;
	public static String Env="";

	public static String csv_value(int row,int col) throws Exception{
		try {
			File file=new File(proValue);
			FileInputStream fis=new FileInputStream(file);
			byte[] byteArray=new byte[(int)file.length()];
			fis.read(byteArray);
			String data=new String(byteArray);
			String[] stringArray=data.split("\r\n");
			//System.out.println("Number of lines in the file are :: " +stringArray.length);
			int rowcnt=stringArray.length;
			String sCurrentLine;
			BufferedReader br=new BufferedReader(new FileReader(file));

			int linenum=0;
			if(row<=rowcnt) {
				while(row !=linenum) {
					br.readLine();
					linenum++;
				}
				if(row==linenum) {
					//System.out.println("Row num -"+row);
					sCurrentLine=br.readLine();
					String[] val=sCurrentLine.split(",");
					//System.out.println("Length *****"+val.length);

					if(col<=val.length) {
						cell_value=(val[col-1]);
						//System.out.println("Cell value "+cell_value);
					}
					else {
						System.out.println("");
					}
				}		
			}
			else {
				System.out.println("No such row exists");
			}
			fis.close();
			br.close();
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("File not found Exception");
		}
		return cell_value;
	}


	public static int getRowUsed(File file) throws Exception {
		file=new File(proValue);
		FileInputStream fis=new FileInputStream(file);
		byte[] byteArray=new byte[(int)file.length()];
		fis.read(byteArray);
		String data=new String(byteArray);
		String[] stringArray=data.split("\r\n");
		int rowcnt=stringArray.length;	
		fis.close();
		return rowcnt;
	}

	public static String getTestCaseName(String sTestCase) throws Exception  {
		String value=sTestCase;
		try {
			//System.out.println("Test case name is "+sTestCase);
			int posi=value.indexOf("@");
			value=value.substring(0, posi);
			posi=value.lastIndexOf(".");
			value=value.substring(posi+1);
			return value;
		}	
		catch(Exception e) {
			throw(e);
		}
	}

	public  static int getRowContains(String sTestCaseName,String InstanceName) throws Exception{
		int i=0;
		try {
			System.out.println("Printing provalue "+proValue);
			File file= new File(proValue);
			int rowCount=getRowUsed(file);
			//System.out.println("Row Count "+rowCount);
			for(i=0;i<rowCount;i++) {
				if(CSV_Reader.csv_value(i, 1).equalsIgnoreCase(sTestCaseName)){
					System.out.println("*****TestCase Name is *****"+CSV_Reader.csv_value(i, 1));
					if(CSV_Reader.csv_value(i, 2).equalsIgnoreCase(InstanceName))
						System.out.println("*****TestInstance Name is *****"+CSV_Reader.csv_value(i, 2));
					//System.out.println("Testcase ROw "+i);
					break;
				}
			}
			//System.out.println("Row Index "+i);
		}

		catch(Exception e) {
			e.printStackTrace();
		}
		return i;
	}

	public static int getColumnIndex(String columName) throws Exception {
		File file=new File(proValue);
		int colIndex=0;
		int colLength=0;
		int iColumn=0;
		FileInputStream fis=new FileInputStream(file);
		BufferedReader br= new BufferedReader(new FileReader(file));
		CSVReader csvReader=new CSVReader(new FileReader(file));

		String[] header=csvReader.readNext();
		if(header!=null) {
			colLength=header.length;
		}
		//System.out.println("Column Length "+colLength);
		//System.out.println("Column Name"+columName);

		for(iColumn=1;iColumn<=colLength;iColumn++) {
			if(columName.equalsIgnoreCase(csv_value(0,iColumn))) {
				colIndex=iColumn;
				//System.out.println("Check Column Index "+colIndex);
				break;
			}
			else {
				continue;
			}
		}
		if(colIndex==0) {
			System.out.println("No such Column Exists");
		}
		//System.out.println("Column Count "+colIndex);
		fis.close();
		br.close();
		csvReader.close();
		return colIndex;
	}

	public static String getCSV_value(String scenarioName,String sInstanceName,String columnName) {
		try {
			int row=CSV_Reader.getRowContains(scenarioName, sInstanceName);
			int column=CSV_Reader.getColumnIndex(columnName);
			File file=new File(proValue);
			FileInputStream fis=new FileInputStream(file);
			byte[] byteArray=new byte[(int)file.length()];
			fis.read(byteArray);
			String data=new String(byteArray);
			String[] stringArray=data.split("\r\n");
			//System.out.println("Number of lines in the file are :: " +stringArray.length);
			int rowcnt=stringArray.length;
			String sCurrentLine;
			BufferedReader br=new BufferedReader(new FileReader(file));

			int linenum=0;
			if(row<=rowcnt) {
				while(row !=linenum) {
					br.readLine();
					linenum++;
				}
				if(row==linenum) {
					System.out.println("Row num -"+row);
					sCurrentLine=br.readLine();
					String [] val=sCurrentLine.split(",");
					//System.out.println("Length *****"+val.length);

					if(column<=val.length) {
						cell_value=(val[column-1]);
						//System.out.println("Cell value "+cell_value);
					}
				}
			}
			else {
				System.out.println("No such row exists");
			}
			fis.close();
			br.close();
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("File not found Exception");
		}
		return cell_value;

	}	
}
