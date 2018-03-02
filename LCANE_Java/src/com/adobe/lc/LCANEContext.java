package com.adobe.lc;

import java.util.HashMap;
import java.util.Map;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;

public class LCANEContext extends FREContext{

	@Override
	public void dispose() {
	}

	@Override
	public Map<String, FREFunction> getFunctions() {
		Map<String, FREFunction> functionMap = new HashMap<String, FREFunction>();
		functionMap.put("gotoAndroidActivity", new gotoAndroidActivity());
		return functionMap;
	}

}
