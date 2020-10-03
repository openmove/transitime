package org.transitclock.custom.lametro;

public class LametroRFPAVLRailFeed4Module extends LametroRFPAvlModule {

	public LametroRFPAVLRailFeed4Module(String agencyId) {
		super(agencyId);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String getUrl() {
		
		return "http://data.nextbus.com/service/customerfeed/lametro-rail-804/avl?command=lastdata";
	}
	

}
