# SecureImageUpload
![fly](https://github.com/user-attachments/assets/c95f6511-50b3-4ebb-8d1f-cfab3af5b4b0)
<img width="348" height="348" alt="icon" src="https://github.com/user-attachments/assets/bc4700c2-31fe-4e7c-894f-a244a54da434" />

volley library implementation
implementation 'com.android.volley:volley:1.2.1'

 <uses-permission android:name="android.permission.INTERNET"/>
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
## 3059 3060 3061 same kaz
3059 xml create 3060 app a bitmap code show 3061 server a php dia bitmap code show
3062 server a ki bitmap code গেছে কিনা php দিয়ে if else condition দিয়ে হ্যা না eco করছি 

3063 image কে server এ একটা images folder এ  রাখলাম সেখানে image1 , image2 ,.... আকারে রাখলাম যাতে দ্রুত কাজ করবে । যদি প্রত্যেকটা আলাদা আলাদা করে hit করাতাম তাহলে অনেক slow হয়ে যেতো । database a 
যদি এখন টেবিল create করে image .jpeg link ta রেখে দেই তাহলে ফাস্ট kaz korbay ..

## 3069  image upload - direct camera and gallery (use library)

Search on chrome : image picker android library
https://github.com/Dhaval2404/ImagePicker  এটায় ঢুকলাম 
setting.gradle : maven { url "https://jitpack.io" } add this line on ( dependencyResolutionManagement এর মধ্যে  )

implementation 'com.github.dhaval2404:imagepicker:2.1' add on build.gradle(Module:app) এটায় auto camera permission নেওয়া আছে । 
বাকিডা code 3069 এ দেখো ।  https://github.com/arshahrear30/SecureImageUpload/blob/main/3069%20MainActivity.java

