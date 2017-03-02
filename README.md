This repo provides a demo app for the [Shortcut Android SDK](https://github.com/shortcutmedia/shortcut-sdk-android). To get it working please follow these steps:

1. Clone the example code by typing the following command in your terminal: `git clone https://github.com/shortcutmedia/shortcut-sdk-android-example.git` or download the [zip file](https://github.com/shortcutmedia/shortcut-sdk-android-example/archive/master.zip) and unzip it.
2. Download the latest SDK from the [releases page](https://github.com/shortcutmedia/shortcut-sdk-android/releases) and unzip it.
3. Open the example project with Android Studio.
4. Import the SDK by selecting menu 'File' > 'New', 'Import Module...' and select the path to the downloaded directory with the SDK. 
5. Make the SDK classes available to the project. Open 'File' > 'Project Structure' and select module 'app'. Switch to tab
'Dependencies' and add _shortcutReaderSDK_ as a new "Module Dependency".
6. Add the keys below to your project's _Manifest.xml_ file.

```script
DEMO_API_KEY = 677795eb-4fba-4797-963d-2e455f7d08f6
DEMO_API_SECRET = 4NoTXkiaw4mLze0irkTuIg0KDj7D73er6v4lTvEm
```

With these keys you are able to scan the [Lenna test image](https://en.wikipedia.org/wiki/Lenna).

```xml
<manifest ... >
  <application ... >
    <meta-data android:name="com.shortcutmedia.shortcut.sdk.API_KEY" android:value="<DEMO_API_KEY>"/>
    <meta-data android:name="com.shortcutmedia.shortcut.sdk.API_SECRET" android:value="<DEMO_API_SECRET>"/>
  </application>
<manifest>
```

To be able to recognize your own items you need to add your own access key and secret token in the _Manifest.xml_ file. You can get your keys by emailing support@shortcutmedia.com.

