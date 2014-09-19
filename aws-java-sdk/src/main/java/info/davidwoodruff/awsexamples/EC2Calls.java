package info.davidwoodruff.awsexamples;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.*;

import java.io.File;
import java.io.IOException;

public class EC2Calls {

    public static EC2Calls build(String credentialsPath) throws IOException {
        AWSCredentials credentials = new PropertiesCredentials(new File(credentialsPath));
        AmazonEC2Client ec2Client = new AmazonEC2Client(credentials);
        return new EC2Calls(ec2Client);
    }

    private AmazonEC2Client ec2Client;

    public EC2Calls(AmazonEC2Client ec2Client) {
        this.ec2Client = ec2Client;
    }

    public String launchInstance(String imageId, String instanceType) {
        RunInstancesRequest runInstancesRequest = new RunInstancesRequest()
                .withImageId(imageId)
                .withInstanceType(instanceType)
                .withMinCount(1)
                .withMaxCount(1);

        RunInstancesResult runInstancesResult = ec2Client.runInstances(runInstancesRequest);
        return runInstancesResult.getReservation().getInstances().get(0).getInstanceId();
    }

    public String getInstanceStatus(String instanceId) {
        DescribeInstancesRequest describeInstancesRequest = new DescribeInstancesRequest()
                .withInstanceIds(instanceId);
        DescribeInstancesResult describeInstancesResult = ec2Client.describeInstances(describeInstancesRequest);
        return describeInstancesResult.getReservations().get(0).getInstances().get(0).getState().getName();
    }

    public String terminateInstance(String instanceId) {
        TerminateInstancesRequest terminateInstancesRequest = new TerminateInstancesRequest()
                .withInstanceIds(instanceId);

        TerminateInstancesResult terminateInstancesResult = ec2Client.terminateInstances(terminateInstancesRequest);
        return terminateInstancesResult.getTerminatingInstances().get(0).getCurrentState().getName();
    }
}
