package org.transitclock.custom.openmove;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.text.SimpleDateFormat;

import org.json.JSONArray;
import org.json.JSONObject;
import org.transitclock.avl.PollUrlAvlModule;
import org.transitclock.db.structs.AvlReport;
import org.transitclock.db.structs.AvlReport.AssignmentType;
import org.transitclock.modules.Module;

public class OpenMoveAVLModule extends PollUrlAvlModule {

	private static String avlURL="http://localhost:8089/positions/";
	
	public OpenMoveAVLModule(String agencyId) {
		super(agencyId);		
	}

	@Override
	protected String getUrl() {
		
		return avlURL;
	}

	@Override
	protected Collection<AvlReport> processData(InputStream in) throws Exception {
		
		String json=this.getJsonString(in);
		// The return value for the method
		Collection<AvlReport> avlReportsReadIn = new ArrayList<AvlReport>();
		JSONArray array = new JSONArray(json);
				
		for (int i=0; i<array.length(); ++i) {
			JSONObject entry = array.getJSONObject(i);
			String vehicleId=entry.getString("deviceId");
			Double latitude=entry.getDouble("lat");
			Double longitude=entry.getDouble("lng");			
			long timestamp=entry.getLong("time");
			float heading=Float.NaN;;
			float speed=Float.NaN;;

			if(entry.has("heading")){
				heading=(float)entry.getDouble("heading");
			}

			if(entry.has("speed")){
				speed=(float)entry.getDouble("speed");
			}
			
			String blockId=null;
			if(entry.has("blockId")){
				blockId=entry.getString("blockId");
			}
			
			
			AvlReport avlReport =
					new AvlReport(vehicleId, timestamp, latitude,
							longitude, heading, speed, "OpenMove");
			
			if(blockId!=null){
				// Actually set the assignment
				avlReport.setAssignment(blockId,
				AssignmentType.BLOCK_ID);
			}
			
			
			avlReportsReadIn.add(avlReport);
		}
		return avlReportsReadIn;
	}
	/**
	 * Just for debugging
	 */
	public static void main(String[] args) {
		// Create a OpenMoveAVLModule for testing
		Module.start("org.transitclock.custom.openmove.OpenMoveAVLModule");
	}
}
