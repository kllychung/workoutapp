## workoutapp
Android app that 
1. lets user go through a series of exercises. Each exercise is timed.
2. allows a user to calculate bmi in english units and metric units
3. records user's history of completed exercises 

## Installation
Clone this repository and import into **Android Studio**
```bash
git clone https://github.com/kllychung/workoutapp.git
```
## Tests
This repo contains android instrumented tests(medium tests)
1. DAO tests which uses a in memory database to test database data manipulation
2. Espresso tests which verify app functionality
3. Snapshot test using Shopify Testify (Note that this repo contains screenshot for an emulator Pixel 5 API 33 )

To run all tests through command line
```bash
./gradlew connectedAndroidTest  
```
To run screenshot test
```bash
./gradlew screenshotTest  
```
### Test report
To run screenshot test
```bash
 ./gradlew reportShow
```
       
