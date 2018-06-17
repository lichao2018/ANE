# ANE Android
学习网址：https://www.adobe.com/devnet/air/articles/ane-android-devices.html

## 环境配置
### Flash Builder
* 安装Flash Builder4.7，如果在官网不能下载安装，请参考：http://blog.csdn.net/jiuqiyuliang/article/details/46612881
* 替换AIR SDK，下载地址：https://www.adobe.com/devnet/air/air-sdk-download.html
  将Flash Builder安装目录中，Adobe Flash Builder 4.7/eclipse/plugins/com.adobe.flash.compiler_4.7.0.349722/AIRSDK，这里面的内容备份到其他地方，然后删除，并将刚刚下载的AIR SDK中的内容解压到这里。
* 替换FLEX SDK，下载地址：https://www.adobe.com/devnet/air/air-sdk-download.html
  不同于AIR SDK，FLEX SDK是该网址中Note的部分：Note: Flex users will need to download the original AIR SDK without the new compiler.
  将Adobe Flash Builder 4.7\sdks\4.6.0中的内容拷贝个副本在当前路径，并起名4.6.0-air28.0，然后将刚刚下载的FLEX SDK中的内容解压并覆盖到这里。
### eclipse和Android SDK和Java
* 略

## 开发ActionScript库
* 在Flash Builder中新建一个ActionScript库项目，选中"包括Adobe AIR库"选项
* 新建一个类，包名"com.adobe.lc"，类名"LCASExtension"
* 类中声明一个ExtensionContext类型的变量context，在构造函数中通过`ExtensionContext.createExtensionContext("com.adobe.lc","")`方法给context赋值，第一个参数下面有说明。然后调用`context.call("initMe")`初始化context。
* 调用Java的方法，就用`context.call("javaFunction")`即可。
* 写好代码后，右键项目，构建项目，会在bin文件夹中生成一个swc文件，后面会用到。

## 开发Java库
* 在eclipse中新建Android工程
* 添加External Jars，jar包路径:

  Adobe Flash Builder 4.7 (64 Bit)\eclipse\plugins\com.adobe.flash.compiler_4.7.0.349722\AIRSDK\lib\android\FlashRuntimeExtensions.jar
* 新建LCANE类，包名com.adobe.lc，继承FREEXTENSION类，重写createContext方法，生成FRECONTEXT对象
* 新建LCANEContext类，继承FRECONTEXT类，重写getFunctions方法，连接ActionScript库中的方法和Java中的方法
* 新建GotoActivity类，集成FREFUNCTION类，重写call方法，call方法中实现Java中具体要做的事情
* 将整个工程以jar包方式导出

## 打包ANE文件
* extension.xml文件
```xml
<extension xmlns="http://ns.adobe.com/air/extension/28.0">
    <id>com.adobe.sampleasextension</id>
    <versionNumber>1</versionNumber>
    <platforms>
        <platform name="Android-ARM">
            <applicationDeployment>
                <nativeLibrary>ANESample.jar</nativeLibrary>
                <initializer>com.adobe.sampleasextension.ANESample</initializer>
                <finalizer>com.adobe.sampleasextension.ANESample</finalizer>
            </applicationDeployment>
        </platform>
    </platforms>
</extension>
```
第一行是AIR SDK的版本，第二行是as库中获取context时要传的第一个参数，platform name="Andorid-ARM"表示Android平台，nativeLibrary填写xx.jar，initializer和finalizer中填写Java中的LCANE类
* 新建一个文件夹，将刚才的xx.swc文件，extension.xml文件，cer.p12文件(打包签名用，例子中有)放进该文件夹
* 在刚才的文件夹中新建Android-ARM文件夹，将xx.jar，library.swf(解压xx.swc获取)，Android工程中的res放进该文件夹
* 找到adt(AIR DEVELOPER TOOL)工具，在上面的文件夹路径进入命令行，并执行`"adt package target ane xx.ane extension.xml swc xx.swc -platform ANDROID-ARM -C ANDROID-ARM"`，然后就会生成一个ane文件

## 使用ANE文件
* 新建Flex手机项目
* Flex SDK版本栏，点击"配置Flex SDK"，浏览添加上述4.6.0-air28.0文件夹，确定，下一步
* 手机设置中，勾掉Apple iOS，应用程序模板选择基于视图的应用程序，下一步两次
* 本机拓展中添加刚才打包生成的ANE
* mxml文件中，通过调用as库的方法，间接调用Java中的方法
* app.xml文件中，找到<android>标签，找到其中的<manifest>标签，在</manifest>标签前加上
  ```
  <application android:enabled="true" android:debuggable="true">
    <activity android:name="xx.xx.xx.XXActivity"></activity>
  </application>
  ```
  `<activity>`中为Android工程中包含的Activity
* 右键项目-属性-Flex编译器，附件的编译器参数中添加：-swf-version=39，如果后面导出发行版时报错，再根据具体的报错内容修改version值，确定
* 选择菜单栏项目-导出发行版-下一步，数字签名选项选择cert.p12，密码是password，本机扩展中，"包"选项勾上，完成便会生成apk文件，并安装到手机上
