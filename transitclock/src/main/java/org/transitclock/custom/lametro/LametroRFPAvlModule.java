package org.transitclock.custom.lametro;

import org.transitclock.modules.Module;
import org.transitclock.utils.MathUtils;
import org.transitclock.utils.Time;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.transitclock.avl.NextBusAvlModule;
import org.transitclock.db.structs.AvlReport;
import org.transitclock.db.structs.AvlReport.AssignmentType;

public class LametroRFPAvlModule extends NextBusAvlModule {
	private static final DateFormat dateFormat= 
			new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final Logger logger = 
			LoggerFactory.getLogger(LametroRFPAvlModule.class);	
	/**
	 * Constructor
	 * @param agencyId
	 */
	public LametroRFPAvlModule(String agencyId) {
		super(agencyId);
	}
	@Override
	protected Collection<AvlReport> extractAvlData(Document doc) throws NumberFormatException {
		
		// The return value for the method
		Collection<AvlReport> avlReportsReadIn = new ArrayList<AvlReport>();
		
		Element rootNode = doc.getRootElement();
		
		Element vehiclesNode = rootNode.getChild("vehicles");
		
		List<Element> vehicles = vehiclesNode.getChildren("vehicle");
		for (Element vehicle : vehicles) {
			
			//"2020-09-08 15:04:25"
						 
			try {
				Date time = dateFormat.parse(vehicle.getChild("date").getValue());
				
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
					
					avlReportsReadIn.add(avlReport);
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
			
		return avlReportsReadIn;
	}

	@Override
	protected String getUrl() {
		// Determine the URL to use.
			return "http://data.nextbus.com/service/customerfeed/lametro/avl?command=lastdata";
	}
	
	/**
	 * Just for debugging
	 */
	public static void main(String[] args) {
		// Create a NextBusAvlModue for testing
		Module.start("org.transitclock.custom.lametro.LametroRFPAvlModule");
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		super.start();
	}
	
}
