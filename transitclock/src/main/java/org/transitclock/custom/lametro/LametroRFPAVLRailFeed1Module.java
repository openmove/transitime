package org.transitclock.custom.lametro;

public class LametroRFPAVLRailFeed1Module extends LametroRFPAvlModule {

	public LametroRFPAVLRailFeed1Module(String agencyId) {
		super(agencyId);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String getUrl() {
		
		return "http://data.nextbus.com/service/customerfeed/lametro-rail-801/avl?command=lastdata";
	}
	

}
