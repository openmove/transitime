package org.transitclock.custom.lametro;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.transitclock.avl.NextBusAvlModule;
import org.transitclock.db.structs.AvlReport;
import org.transitclock.db.structs.AvlReport.AssignmentType;
import org.transitclock.utils.MathUtils;

public class AvlModuleFeedCombined extends NextBusAvlModule {

	private static final Logger logger = 
			LoggerFactory.getLogger(AvlModuleFeedCombined.class);

	public AvlModuleFeedCombined(String agencyId) {
		super(agencyId);
		// TODO Auto-generated constructor stub
	}
	HashMap<String, String> assignments = new HashMap<String,String>();

	@Override
	protected void getAndProcessData() throws Exception {
				
		Collection<AvlReport> avlReports = null;
			
		URL rfp = new URL("http://data.nextbus.com/service/customerfeed/lametro/avl?command=lastdata");
		
		BufferedReader in = new BufferedReader(new InputStreamReader(rfp.openStream()));
		
		SAXBuilder builder = new SAXBuilder();
		
		Document doc = builder.build(in);
		
		assignments=extractAssignmentData(doc);
		
		in.close();
		
		super.getAndProcessData();
			        		
	}
	protected HashMap<String, String> extractAssignmentData(Document doc)
	{
						
		Element rootNode = doc.getRootElement();
		
		Element vehiclesNode = rootNode.getChild("vehicles");
		
		List<Element> vehicles = vehiclesNode.getChildren("vehicle");
		
		for (Element vehicle : vehicles) {
			
			//"2020-09-08 15:04:25"
						 
			try {
												
				SimpleDateFormat sdfgmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			    sdfgmt.setTimeZone(TimeZone.getTimeZone("UTC"));			   
			    
			    Date time = null;
			    try {
			        time = sdfgmt.parse(vehicle.getChild("date").getValue());
			    } catch (ParseException e) {e.printStackTrace();}

			   				
				Element id = vehicle.getChild("id");
				
				String vehicleId=id.getValue();
				
				Double lat = new Double(vehicle.getChild("lat").getValue());
				Double lon = new Double(vehicle.getChild("lon").getValue());
				String block=null;
				if(vehicle.getChild("block")!=null)
					block=vehicle.getChild("block").getValue();
				Float speed=new Float(vehicle.getChild("speed").getValue());
				Float direction=new Float(vehicle.getChild("direction").getValue());
				
				String vehstatus=vehicle.getChild("vehstatus").getValue();
				if(vehstatus!=null&&vehstatus.equals("f"))
				{
					
					String driver=null;
					if(vehicle.getChild("driver")!=null)		
						driver=vehicle.getChild("driver").getValue();
					
					AvlReport avlReport = new AvlReport(vehicleId, time.getTime(), 
							MathUtils.round(lat, 5), MathUtils.round(lon, 5), 
							speed, direction, "LAMetroRFP", null, driver, 
							null,       // license plate
							-1,
							Float.NaN); // passengerFullness
					if(block!=null&&!block.isEmpty())
						avlReport.setAssignment(block, AssignmentType.BLOCK_ID);
					
					assignments.put(vehicleId, block);
				}
			} catch (Exception e) {
				e.printStackTrace();
			
				logger.error(e.getMessage(),e);
			}			 
			 /*
			  * public AvlReport(String vehicleId, long time, double lat, double lon,
			float speed, float heading, String source, String leadVehicleId,
			String driverId, String licensePlate, Integer passengerCount,
			float passengerFullness) 
			  */										
		}
		return assignments;
		
	}
	
	@Override
	protected void processAvlReport(AvlReport avlReport) {
		
		if(assignments.get(avlReport.getVehicleId())!=null&&avlReport.getAssignmentType()!=AssignmentType.UNSET)
		{				
			avlReport.setAssignment(assignments.get(avlReport.getVehicleId()), AssignmentType.BLOCK_ID);
		}
		super.processAvlReport(avlReport);
	}
	

	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return super.getUrl();
	}
	
	@Override
	protected Collection<AvlReport> extractAvlData(Document doc) throws NumberFormatException {
		// TODO Auto-generated method stub
		Collection<AvlReport> data = super.extractAvlData(doc);
								
		return data;
	}

	

}
