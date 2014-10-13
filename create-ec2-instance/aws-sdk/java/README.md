# AWS Java SDK Example
Simple example of using the AWS Java SDK to create an EC2 instance

# Example Prerequisites
The app has several dependencies:
* Java
* Maven

I've provided a Vagrantfile in the root of this repository. You can use Vagrant to
"vagrant up", which will install all required dependencies for these examples.

All other dependencies are handled via the Maven build.

Before running this example, you must add a file with your AWS credentials to
~/.aws/AwsCredentials.properties:
```
accessKey = <your_access_key_here>
secretKey = <your_secret_key_here>
```

This project builds using Maven, so just run the regular Maven command to build it:
```
mvn clean install
```

# Example Usage
To run this example, just invoke the built JAR file:
```
java -jar target/aws-java-sdk-example.jar
```

# Example Output
When running this example, you'll see output similar to the following:
```
Launching instance with ID=i-dbcef430
Instance launching, in state: pending
Instance launching, in state: pending
Instance launching, in state: pending
Instance launching, in state: pending
Instance launching, in state: running
Instance launched successfully
Terminating instance, in state: shutting-down
Terminating instance, in state: shutting-down
Terminating instance, in state: shutting-down
Terminating instance, in state: shutting-down
Terminating instance, in state: shutting-down
Terminating instance, in state: shutting-down
Terminating instance, in state: terminated
Instance terminated successfully
```
