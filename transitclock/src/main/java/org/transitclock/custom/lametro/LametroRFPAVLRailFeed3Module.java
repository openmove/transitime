package org.transitclock.custom.lametro;

public class LametroRFPAVLRailFeed3Module extends LametroRFPAvlModule {

	public LametroRFPAVLRailFeed3Module(String agencyId) {
		super(agencyId);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String getUrl() {
		
		return "http://data.nextbus.com/service/customerfeed/lametro-rail-803/avl?command=lastdata";
	}
	

}
