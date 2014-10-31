package info.davidwoodruff.awsexamples;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.cloudwatch.AmazonCloudWatchClient;
import com.amazonaws.services.cloudwatch.model.Datapoint;
import com.amazonaws.services.cloudwatch.model.Dimension;
import com.amazonaws.services.cloudwatch.model.GetMetricStatisticsRequest;
import com.amazonaws.services.cloudwatch.model.GetMetricStatisticsResult;
import org.joda.time.DateTime;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class CloudWatchCalls {
    public static CloudWatchCalls build(String credentialsPath) throws IOException {
        AWSCredentials credentials = new PropertiesCredentials(new File(credentialsPath));
        AmazonCloudWatchClient cloudWatchClient = new AmazonCloudWatchClient(credentials);
        return new CloudWatchCalls(cloudWatchClient);
    }

    private AmazonCloudWatchClient cloudWatchClient;

    public CloudWatchCalls(AmazonCloudWatchClient cloudWatchClient) {
        this.cloudWatchClient = cloudWatchClient;
    }

    public void getEc2InstanceMetrics(String resource, DateTime time) {
        Dimension dimension = new Dimension()
                .withName("InstanceId")
                .withValue(resource);
        GetMetricStatisticsRequest metricStatisticsRequest = new GetMetricStatisticsRequest()
                .withDimensions(dimension)
                .withMetricName("CPUUtilization")
                .withStartTime(time.toDate())
                .withEndTime(new Date())
                .withPeriod(3600)
                .withNamespace("AWS/EC2")
                .withStatistics("Average");
        GetMetricStatisticsResult metricStatisticsResult = cloudWatchClient.getMetricStatistics(metricStatisticsRequest);
        List<Datapoint> datapoints = metricStatisticsResult.getDatapoints();
        for(Datapoint datapoint : datapoints) {
            System.out.println(datapoint);
        }
    }
}
