package com.css.pos.reports;

import java.io.File;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.faces.context.FacesContext;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
public class ReportUtil {

	public static String currentReportName;
	// @param parameters
	// @param
	// @param
	public static JasperPrint exportPDF(HashMap jasperParameter,String outputFileName,String reportName,String saveToDrivePath,boolean attachToResponse, Connection conn){
		JasperPrint jasperPrint = null;
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy__HH-mm");
		Date date = new Date();
		outputFileName += dateFormat.format(date)+".pdf";   //attach date to output file name
		currentReportName = outputFileName;
		try {
			
			if(conn != null)
				jasperPrint = JasperFillManager.fillReport(
					new File(FacesContext
							.getCurrentInstance()
							.getExternalContext()
							.getRealPath(
									"WEB-INF\\classes\\com\\pos\\reports\\"
											+ reportName)).getAbsolutePath(), jasperParameter, conn);
			else
				jasperPrint = JasperFillManager.fillReport(
						new File(FacesContext
								.getCurrentInstance()
								.getExternalContext()
								.getRealPath(
										"WEB-INF\\classes\\com\\pos\\reports\\"
												+ reportName)).getAbsolutePath(), jasperParameter);
			if (saveToDrivePath != null) 
			{
				
				JasperExportManager.exportReportToPdfFile(jasperPrint, saveToDrivePath
						+ outputFileName);
			}
			conn.close();
			if(!attachToResponse){
				return null;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jasperPrint;
	}
	public static void main(String[] args) {
		//CHECK IS FOLDER EXIST OR NOT 
		File theDir = new File("D:\\mcv_reports\\SHIPMENT\\200");

		  // if the directory does not exist, create it
		  if (!theDir.exists())
		  {
		    
		    boolean result = theDir.mkdir();  
		    if(result){    
		       System.out.println("DIR created");  
		     }

		  }
	}
}
