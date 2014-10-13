package info.davidwoodruff.awsexamples;

import java.io.IOException;

public class JavaSdkExampleApplication {
    public static void main(String[] args) {
        String credentialsPath = System.getProperty("user.home") + "/.aws/AwsCredentials.properties";
        try {
            EC2Calls ec2Calls = EC2Calls.build(credentialsPath);
            JavaSdkExampleApplication app = new JavaSdkExampleApplication(ec2Calls);
        }
        catch(IOException e) {
            System.err.println("Error while getting credentials at path: " + credentialsPath + ". Error:" + e.getMessage());
            System.exit(1);
        }
    }

    public JavaSdkExampleApplication(EC2Calls ec2Calls) {
        // Launch instance
        String instanceId = ec2Calls.launchInstance("ami-76817c1e", "m3.medium");
        System.out.println("Launching instance with ID=" + instanceId);

        //Wait for instance to be ready
        String instanceStatus = "pending";
        while(!instanceStatus.equals("running")) {
            instanceStatus = ec2Calls.getInstanceStatus(instanceId);
            System.out.println("Instance launching, in state: " + instanceStatus);
            sleepSeconds(5);
        }
        System.out.println("Instance launched successfully");

        //Terminate instance
        String terminationStatus = "running";
        while(!terminationStatus.equals("terminated")) {
            terminationStatus = ec2Calls.terminateInstance(instanceId);
            System.out.println("Terminating instance, in state: " + terminationStatus);
            sleepSeconds(5);
        }
        System.out.println("Instance terminated successfully");
    }

    private void sleepSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException ex) { }
    }
}
