# Bidder
A bidder platform which allows advertisers to submit bids to buy mobile ad space in real-time.
The platform receives bid requests from 3rd-party ad exchanges and retrieves all available campaigns filtering
them by targeting country and responde with the highest bid in terms of bid price.

# Installation
This project requires Java 8 to be installed. If java 8 is not installed please follow
the instractions from the oracle website (http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html).
External dependencies are handled by gradle wrapper.

# Run bidder
To compile and start the project:
* Navigate to project directory
* Run tests by executing the command : ```./gradlew clean test```
* Run the project by executing the command: ```./gradlew bootRun```

Further gradle tasks:
* To clean the project execute: ```./gradlew clean```
* To compile the project execute : ```./gradlew build```

# Acceptance test scenarios
Acceptance tests are included in : ```test/java/com/bluebanana/bidder/integration```
