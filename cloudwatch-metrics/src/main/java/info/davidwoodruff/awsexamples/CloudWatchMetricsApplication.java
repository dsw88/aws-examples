package info.davidwoodruff.awsexamples;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.io.IOException;

public class CloudWatchMetricsApplication {
    public static void main(String[] args) {
        String credentialsPath = System.getProperty("user.home") + "/.aws/AwsCredentials.properties";
        if(args.length < 3) {
            System.out.println("Usage: java -jar myjar.jar <service> <resource-id> <start-time>");
            System.exit(1);
        }
        String service = args[0];
        String resource = args[1];
        String time = args[2];
        try {
            CloudWatchCalls cloudWatchCalls = CloudWatchCalls.build(credentialsPath);
            CloudWatchMetricsApplication cloudWatchMetricsApplication = new CloudWatchMetricsApplication(cloudWatchCalls);

            //TODO - Eventually figure out what service to go to
            cloudWatchMetricsApplication.getEc2Metrics(resource, time);
        }
        catch(IOException e) {
            System.err.println("Error while getting credentials at path: " + credentialsPath + ". Error:" + e.getMessage());
            System.exit(1);
        }
    }

    private CloudWatchCalls cloudWatchCalls;

    public CloudWatchMetricsApplication(CloudWatchCalls cloudWatchCalls) {
        this.cloudWatchCalls = cloudWatchCalls;
    }

    public void getEc2Metrics(String resource, String time) {
        DateTime dateTime = new DateTime( time ).withZone(DateTimeZone.UTC);
        cloudWatchCalls.getEc2InstanceMetrics(resource, dateTime);
    }
}