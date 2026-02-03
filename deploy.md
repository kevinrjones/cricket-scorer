## Create an Upload Key

On my machine this is in `/Users/kevinjones/.android/release.keystore` with my usual dev password


## To Create a Bundle

1. If this is an update yo need to change the version number for the release
    1. In build.gradle.kts (:app) Increment the versionCode value
1. Build..Generate Signed App Bundle/APK...
1. Android App Bundle
    1. Click Next
1. Enter the Key store path `/Users/kevinjones/.android/release.keystore`, the key alias and the key password
    1. Click Next
1. Choose release and 
    1. Click Create
    1. After it's built click on 'locate' to go to the directory with the bundle

## Uploading the Bundle

1. Either create an app and add it to the 'Internal Testing' Channel or...
1. Go to the 'Internal Testing' channel (in 'Test and release') and click on 'Create new release'
1. Drag the app into the box
1. After the upload click Next and Publish