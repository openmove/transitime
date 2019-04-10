/*
 * This file is part of transitclock.org
 * 
 * transitclock.org is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License (GPL) as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 *
 * transitclock.org is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Transitime.org .  If not, see <http://www.gnu.org/licenses/>.
 */
package org.transitclock.custom.lynx;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.zip.GZIPInputStream;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.transitclock.applications.Core;
import org.transitclock.avl.AvlModule;
import org.transitclock.avl.PollUrlAvlModule;
import org.transitclock.config.StringConfigValue;
import org.transitclock.configData.AvlConfig;
import org.transitclock.db.structs.AvlReport;
import org.transitclock.db.structs.AvlReport.AssignmentType;
import org.transitclock.db.structs.Route;
import org.transitclock.modules.Module;
import org.transitclock.utils.IntervalTimer;
import org.transitclock.utils.Time;

/**
 * AVL module for reading AVL data from a DoubleMap feed.
 * 
 * @author Sean Óg Crudden
 *
 */
public class DoubleMapAvlModule extends PollUrlAvlModule {

	private static StringConfigValue doubleMapBusLocFeedUrl = new StringConfigValue(
			"transitclock.avl.doubleMapLocFeedUrl", "http://golynx.doublemap.com/map/v2/buses",
			"The URL of the BusLoc json feed to use.");

	private static StringConfigValue routeLookUpURL = new StringConfigValue("transitclock.avl.doubleMapRouteLookUpURL",
			"http://golynx.doublemap.com/map/v2/routes", "The URL to read the route info from.");

	private static StringConfigValue stopLookUpURL = new StringConfigValue("transitclock.avl.doubleMapStopLookUpURL",
			"http://golynx.doublemap.com/map/v2/stops", "The URL to read the stop info from.");

	// If debugging feed and want to not actually process
	// AVL reports to generate predictions and such then
	// set shouldProcessAvl to false;
	private static boolean shouldProcessAvl = true;

	HashMap<Integer, String> routeLookUp = new HashMap<Integer, String>();
	
	HashMap<Integer, String> stopLookUp=new  HashMap<Integer, String>();
	// For logging use AvlModule class so that will end up in the AVL log file
	private static final Logger logger = LoggerFactory.getLogger(AvlModule.class);

	/********************** Member Functions **************************/

	/**
	 * Constructor
	 * 
	 * @param agencyId
	 */
	public DoubleMapAvlModule(String agencyId) {
		super(agencyId);
		
		try {
			// Read in the route and stop info so can map to GTFS ids.
			readDoubleMapStopData();
			readDoubleMapRouteData();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Returns URL to use
	 */
	@Override
	protected String getUrl() {
		return doubleMapBusLocFeedUrl.getValue();
	}

	/**
	 * Called when AVL data is read from URL. Processes the JSON data and calls
	 * processAvlReport() for each AVL report.
	 */
	@Override
	protected Collection<AvlReport> processData(InputStream in) throws Exception {
		// Get the JSON string containing the AVL data
		String jsonStr = getJsonString(in);
		Collection<AvlReport> avlReportsReadIn = new ArrayList<AvlReport>();
		try {
			// Convert JSON string to a JSON array.
			JSONArray entities = new JSONArray(jsonStr);

			
			for (int i = 0; i < entities.length(); i++) {
				JSONObject vehicle = entities.getJSONObject(i);

				Integer routeid = vehicle.getInt("route");

				if (routeid != null) {
					long timestamp = vehicle.getLong("lastUpdate");

					String source = null;

					if (vehicle.getJSONObject("fields").has("type")) {
						source = vehicle.getJSONObject("fields").getString("type");
					} else {
						source = new String("None");
					}

					// Create the AvlReport
					AvlReport avlReport = new AvlReport(vehicle.getString("name"), timestamp * Time.MS_PER_SEC,
							vehicle.getDouble("lat"), vehicle.getDouble("lon"), Float.NaN, Float.NaN, source);

					// Actually set the assignment
					if (routeLookUp.containsKey(routeid)) {
						avlReport.setAssignment(routeLookUp.get(routeid), AssignmentType.ROUTE_ID);

						logger.debug("From DoubleMapAvlModule {}", avlReport);

						if (shouldProcessAvl) {
							avlReportsReadIn.add(avlReport);
						}
					}else
					{
						logger.error("From DoubleMapAvlModule {}. Could not find route map entry for route {}.", avlReport,routeid);
					}
				}
			}
			// Return all the AVL reports read in
			return avlReportsReadIn;
		} catch (JSONException e) {
			logger.error("Error parsing JSON. {}. {}", e.getMessage(), jsonStr, e);
			return new ArrayList<AvlReport>();

		}

	}
	
	protected void readDoubleMapStopData() throws Exception {
		// For logging
		IntervalTimer timer = new IntervalTimer();

		// Get from the AVL feed subclass the URL to use for this feed
		String fullUrl = stopLookUpURL.getValue();

		// Log what is happening
		logger.info("Getting stop data from feed using url=" + stopLookUpURL);

		// Create the connection
		URL url = new URL(fullUrl);
		URLConnection con = url.openConnection();

		// Set the timeout so don't wait forever
		int timeoutMsec = AvlConfig.getAvlFeedTimeoutInMSecs();
		con.setConnectTimeout(timeoutMsec);
		con.setReadTimeout(timeoutMsec);

		// Set any additional AVL feed specific request headers
		setRequestHeaders(con);

		// Create appropriate input stream depending on whether content is
		// compressed or not
		InputStream in = con.getInputStream();

		// For debugging
		logger.debug("Time to access inputstream {} msec", timer.elapsedMsec());

		processStopData(in);

		in.close();

	}



	protected void readDoubleMapRouteData() throws Exception {
		// For logging
		IntervalTimer timer = new IntervalTimer();

		// Get from the AVL feed subclass the URL to use for this feed
		String fullUrl = routeLookUpURL.getValue();

		// Log what is happening
		logger.info("Getting route data from feed using url=" + routeLookUpURL);

		// Create the connection
		URL url = new URL(fullUrl);
		URLConnection con = url.openConnection();

		// Set the timeout so don't wait forever
		int timeoutMsec = AvlConfig.getAvlFeedTimeoutInMSecs();
		con.setConnectTimeout(timeoutMsec);
		con.setReadTimeout(timeoutMsec);

		// Set any additional AVL feed specific request headers
		setRequestHeaders(con);

		// Create appropriate input stream depending on whether content is
		// compressed or not
		InputStream in = con.getInputStream();

		// For debugging
		logger.debug("Time to access inputstream {} msec", timer.elapsedMsec());

		processRouteData(in);

		in.close();

	}
	

	private void processStopData(InputStream in) throws JSONException, IOException {
		// TODO Auto-generated method stub
		String jsonStr = getJsonString(in);

		JSONArray stops = new JSONArray(jsonStr);
		
		for (int i = 0; i < stops.length(); i++) {
			
			JSONObject stop = stops.getJSONObject(i);
			
			stopLookUp.put(stop.getInt("id"), stop.getString("code"));				
		}
	}

	private void processRouteData(InputStream in) throws JSONException, IOException {
		// TODO Auto-generated method stub
		String jsonStr = getJsonString(in);

		JSONArray entities = new JSONArray(jsonStr);
		
	
		for (int i = 0; i < entities.length(); i++) {
			

			JSONObject route = entities.getJSONObject(i);

			JSONArray stops = route.getJSONArray("stops");
			
			HashMap<Route, Integer> stopCounterByRoute=new HashMap<Route, Integer> ();

			for (int j = 0; j < stops.length(); j++) {
								
				Integer stop = stops.getInt(j);
										
				Collection<Route> possibleRoutes = Core.getInstance().getDbConfig().getRoutesForStop(stopLookUp.get(stop));
				
				if (possibleRoutes!=null && possibleRoutes.size() >  1) {
					
					Iterator<Route> iterator = possibleRoutes.iterator(); 
					while (iterator.hasNext()) {												
						Route currentRoute=iterator.next();
						if(stopCounterByRoute.containsKey(currentRoute))
						{
							stopCounterByRoute.put(currentRoute, stopCounterByRoute.get(currentRoute)+1);
						}else
						{
							stopCounterByRoute.put(currentRoute, 1);
						}
						
					}
					
				}
			}			
			if(findMax(stopCounterByRoute,route.getInt("id"))!=null)
					routeLookUp.put(route.getInt("id"), findMax(stopCounterByRoute,route.getInt("id")));			
		}

	}

	private String findMax(HashMap<Route, Integer> stopCounterByRoute, Integer routeId) {
		
		Iterator<Route> keys=stopCounterByRoute.keySet().iterator();
		
		Integer max=0;
		Route maxRoute=null;
		while(keys.hasNext())
		{
		
			Route key=keys.next();
			if(stopCounterByRoute.get(key)>max)
			{
				max=stopCounterByRoute.get(key);
				maxRoute=key;
			}
			
		}
		if(max<5)
			logger.info("Route {} has max stops matching of {}. DoubleMap route is {}.", maxRoute, max, routeId);
		return maxRoute.getId();
	}

	/**
	 * Just for debugging
	 */
	public static void main(String[] args) {
		// For debugging turn off the actual processing of the AVL data.
		// This way the AVL data is logged, but that is all.
		shouldProcessAvl = false;

		// Create a DoubleMapAvlModule for testing
		Module.start("org.transitclock.custom.lynx.DoubleMapAvlModule");
	}

}
