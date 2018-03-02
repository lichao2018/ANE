adt="E:/Program Files/Adobe/Adobe Flash Builder 4.7 (64 Bit)/eclipse/plugins/com.adobe.flash.compiler_4.7.0.349722/AIRSDK/bin/adt.bat"
nativedir="../LCANE_Java"

echo "********************************************************************"
echo " - creating ANE package"

rm -rf Android-ARM/*
rm -f SampleASExtension.ane library.swf
mkdir -p Android-ARM

unzip ../LCANE_AS/bin/LCANE_AS.swc library.swf
cp library.swf Android-ARM
cp "$nativedir"/LCANE.jar Android-ARM
cp -r "$nativedir"/res Android-ARM

"$adt" -package -target ane LCANE.ane extension.xml -swc ../LCANE_AS/bin/LCANE_AS.swc -platform Android-ARM -C Android-ARM .

#"$adt" -package -storetype PKCS12 -keystore cer.p12 -storepass password -target ane SampleASExtension.ane extension.xml -swc ANESample.swc -platform Android-ARM -C Android-ARM .

read -p "Press any key to continue." var