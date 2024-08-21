package com.dts.test;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import org.apache.tika.Tika;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.BodyContentHandler;

public class PaySlipBill_Pdf 
{
	public static void getMonthData() 
	{
		try {
//			File file = new File("D:\\TCSPaySlip.pdf");
			File file = new File("D:\\Salary-Slip-Excel.pdf");
			Tika tika = new Tika();
			String content = tika.parseToString(file);

		/*	
			String month = "";
			if(content.contains("Payslip for the month of")) {
				int startIndex = content.indexOf("Payslip for the month of") + "Payslip for the month of".length();
				int endIndex = content.indexOf(",",startIndex);
				
				month = content.substring(startIndex,endIndex).trim().toUpperCase();
				System.out.println(month);
			}
			else {
				System.out.println("Invalid");
			}
			
		*/	

			InputStream inputStream = new FileInputStream(file);
			BodyContentHandler bodyContentHandler = new BodyContentHandler();
			Metadata metaData = new Metadata();
			AutoDetectParser autoDetectParser = new AutoDetectParser();
			ParseContext parseContext = new ParseContext();

			autoDetectParser.parse(inputStream, bodyContentHandler, metaData, parseContext);
			
			String extracMonthData = getExtracMonthData(content);
			System.out.println(extracMonthData);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static String getExtracMonthData(String content) 
	{
		final String[] months = {"January" , "February" , "March" , "April" , "May" ,"June" , "July" , "August" , "September" , "October" , "November" , "December" , "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
		for(String extractMonth : months) {
			if(content.contains(extractMonth)) {
				return extractMonth.toUpperCase();
			}
		}
		return "month not found.";
	}
	public static void main(String[] args) {
		getMonthData();
	}
}