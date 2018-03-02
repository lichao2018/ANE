package com.adobe.lc
{
	import flash.external.ExtensionContext;

	public class LCASExtension
	{
		private static var extContext:ExtensionContext = null;
		
		public function LCASExtension()
		{
			if(!extContext){
				extContext = ExtensionContext.createExtensionContext("com.adobe.lc", "");
				if(extContext){
					extContext.call("initMe");
				}
			}
		}
		
		public function gotoAndroidActivity():void{
			extContext.call("gotoAndroidActivity", "MSG FROM AS");
		}
	}
}