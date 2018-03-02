package com.adobe.lc;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREExtension;

public class LCANE implements FREExtension{

	@Override
	public FREContext createContext(String arg0) {
		return new LCANEContext();
	}

	@Override
	public void dispose() {
	}

	@Override
	public void initialize() {
	}

}
