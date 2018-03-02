package com.adobe.lc;

import android.content.Intent;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREInvalidObjectException;
import com.adobe.fre.FREObject;
import com.adobe.fre.FRETypeMismatchException;
import com.adobe.fre.FREWrongThreadException;

public class gotoAndroidActivity implements FREFunction{

	@Override
	public FREObject call(FREContext context, FREObject[] asArgs) {
		Intent intent = new Intent(context.getActivity(), MainActivity.class);
		try {
			intent.putExtra("asMsg", asArgs[0].getAsString());
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (FRETypeMismatchException e) {
			e.printStackTrace();
		} catch (FREInvalidObjectException e) {
			e.printStackTrace();
		} catch (FREWrongThreadException e) {
			e.printStackTrace();
		}
		context.getActivity().startActivity(intent);
		return null;
	}

}
