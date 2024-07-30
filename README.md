Raising the bar higher, i have successfully implemented latest screenshot testing. This has 3 steps
Step 1 -  .\gradlew updateDebugScreenshotTest 
Step 2 -  .\gradlew validateDebugScreenshotTest
Step 3 -  Look for file TEST-PreviewKt.xml inside app/Build/test-results/ (This details the results like below)
<testsuite name="PreviewKt" tests="5" skipped="0" failures="0" errors="0" timestamp="2024-07-30T14:06:21" hostname="AMAN" time="35.308">

