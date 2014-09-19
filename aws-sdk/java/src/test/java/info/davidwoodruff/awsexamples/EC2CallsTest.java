package info.davidwoodruff.awsexamples;

import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.*;
import org.testng.annotations.*;

import java.util.LinkedList;
import java.util.List;

import static org.testng.Assert.*;
import static org.mockito.Mockito.*;

public class EC2CallsTest {

    private EC2Calls ec2Calls;
    private AmazonEC2Client ec2Client;

    @BeforeMethod
    public void beforeMethod() {
        ec2Client = mock(AmazonEC2Client.class);
        ec2Calls = new EC2Calls(ec2Client);
    }

    @Test
    public void launchInstance() {
        String imageId = "ami-fakeid";
        String instanceType = "m3.medium";
        String expectedInstanceId = "i-fakeid";

        //Create fake objects for test
        Instance mockInstance = mock(Instance.class);
        when(mockInstance.getInstanceId()).thenReturn(expectedInstanceId);
        List<Instance> mockInstancesList = new LinkedList<Instance>();
        mockInstancesList.add(mockInstance);
        Reservation mockReservation = mock(Reservation.class);
        when(mockReservation.getInstances()).thenReturn(mockInstancesList);
        RunInstancesResult mockRunInstancesResult = mock(RunInstancesResult.class);
        when(mockRunInstancesResult.getReservation()).thenReturn(mockReservation);
        when(ec2Client.runInstances(any(RunInstancesRequest.class))).thenReturn(mockRunInstancesResult);

        String actualInstanceId = ec2Calls.launchInstance(imageId, instanceType);
        assertEquals(actualInstanceId, expectedInstanceId);
    }

    @Test
    public void getInstanceStatus() {
        String instanceId = "i-fakeid";
        String expectedStatus = "pending";

        //Create fake objects for test
        InstanceState mockInstanceState = mock(InstanceState.class);
        when(mockInstanceState.getName()).thenReturn(expectedStatus);
        Instance mockInstance = mock(Instance.class);
        when(mockInstance.getState()).thenReturn(mockInstanceState);
        List<Instance> mockInstancesList = new LinkedList<Instance>();
        mockInstancesList.add(mockInstance);
        Reservation mockReservation = mock(Reservation.class);
        when(mockReservation.getInstances()).thenReturn(mockInstancesList);
        List<Reservation> mockReservationsList = new LinkedList<Reservation>();
        mockReservationsList.add(mockReservation);
        DescribeInstancesResult mockDescribeInstancesResult = mock(DescribeInstancesResult.class);
        when(mockDescribeInstancesResult.getReservations()).thenReturn(mockReservationsList);
        when(ec2Client.describeInstances(any(DescribeInstancesRequest.class))).thenReturn(mockDescribeInstancesResult);

        String actualStatus = ec2Calls.getInstanceStatus(instanceId);
        assertEquals(actualStatus, expectedStatus);
    }

    @Test
    public void terminateInstance() {
        //TerminateInstancesResult terminateInstancesResult = ec2Client.terminateInstances(terminateInstancesRequest);
        //return terminateInstancesResult.getTerminatingInstances().get(0).getCurrentState().getName();
        String instanceId = "i-fakeid";
        String expectedTerminationStatus = "shutting-down";

        //Create fake objects for test
        InstanceState mockInstanceState = mock(InstanceState.class);
        when(mockInstanceState.getName()).thenReturn(expectedTerminationStatus);
        InstanceStateChange mockInstanceStateChange = mock(InstanceStateChange.class);
        when(mockInstanceStateChange.getCurrentState()).thenReturn(mockInstanceState);
        List<InstanceStateChange> mockInstanceStateChangesList = new LinkedList<InstanceStateChange>();
        mockInstanceStateChangesList.add(mockInstanceStateChange);
        TerminateInstancesResult mockTerminateInstancesResult = mock(TerminateInstancesResult.class);
        when(mockTerminateInstancesResult.getTerminatingInstances()).thenReturn(mockInstanceStateChangesList);
        when(ec2Client.terminateInstances(any(TerminateInstancesRequest.class))).thenReturn(mockTerminateInstancesResult);

        String actualTerminationStatus = ec2Calls.terminateInstance(instanceId);
        assertEquals(actualTerminationStatus, expectedTerminationStatus);
    }
}
