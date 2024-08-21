package com.dts.test;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.tika.Tika;

public class PaySlipBill 
{
	public static void main(String[] args) 
	{
		try 
		{
			Tika tika = new Tika();
			File file = new File("D:\\Salary-Slip.pdf");
			
			String text = tika.parseToString(file);
			Pattern pattern = Pattern.compile("([A-Za-z]+)(\\D{4}");
			Matcher matcher = pattern.matcher(text);
			
			if(matcher.find()) {
				String month = matcher.group(1);
				System.out.println("payslip month is : "+month);
			}
			else {
				System.out.println("can't find month in this payslip.");
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}