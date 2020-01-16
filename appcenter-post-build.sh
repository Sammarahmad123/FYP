#script1.sh
clear
echo "Starting........"
Is
appcenter test run appium --app "ahmadsammar74-gmail.com/New-APp" --devices 9321822b --app-path $APPCENTER_OUTPUT_DIRECTORY/app-release.apk --test-series "master" --locale "en_US" --build-dir $APPCENTER_SOURCE_DIRECTORY/HumanFocusProject/android.tests/target/upload
echo "ending......"
cat script1.sh