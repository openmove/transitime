package org.transitclock.custom.lametro;

public class LametroRFPAVLRailFeed2Module extends LametroRFPAvlModule {

	public LametroRFPAVLRailFeed2Module(String agencyId) {
		super(agencyId);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String getUrl() {
		
		return "http://data.nextbus.com/service/customerfeed/lametro-rail-802/avl";
	}
	

}
