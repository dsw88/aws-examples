#!/bin/bash
####################################################################################################################
# Before running this script, make sure to configure your AWS CLI with credentials
# by running "aws configure". This will ask for several items:
#   AWS Access Key ID [********************]: YOUR_ACCESS_KEY_ID_HERE
#   AWS Secret Access Key [********************]: YOUR_SECRET_ACCESS_KEY_HERE
#   Default region name [us-east-1]: us-east-1 # I default to us-east-1, but you may want to use a different region
#   Default output format [None]:
#
# Once you've configured the CLI with your credentials, you should be able to make
# calls like the one in this script
#
# See http://docs.aws.amazon.com/cli/latest/reference/ for the full CLI reference
# on all the CLI calls
####################################################################################################################

# Run an EC2 instance
# CLI Paramters Information:
#  * image-id - I'm just using the default AMI for Amazon Linux in my account. Your AMI may be different
#  * instance-type - Again, this is configurable, but I just chose m3.medium
run_instances_output=$(aws ec2 run-instances --image-id ami-76817c1e --instance-type m3.medium)
instance_id=$(echo "$run_instances_output" | jq '.Instances[0].InstanceId' | sed 's/"//g') # You need to have the "jq" tool installed to parse JSON in bash
echo "Launching instance with ID=$instance_id"

# Wait for the instance to be ready
# CLI Parameters Information:
#   * instance-ids - A list of instance IDs, which we get from the run-instances call. We're only waiting for one instance in this example
instance_state="pending"
while [ $instance_state != "running" ]
do
  describe_instances_output=$(aws ec2 describe-instances --instance-ids "$instance_id")
  instance_state=$(echo "$describe_instances_output" | jq '.Reservations[0].Instances[0].State.Name' | sed 's/"//g')
  echo "Instance launching, in state: $instance_state"
  sleep 5
done
echo "Instance launched successfully"

# Terminate the EC2 instance, so we don't pay for it :)
# CLI Parameters Information:
#  * instance-ids - A list of instance IDs, which we get from the run-instances call. We're only terminating one instance in this example
# We can keep calling terminate-instances to get the state of the terminating instance. You could also call describe-instances too.
termination_state="running"
while [ $termination_state != "terminated" ]
do
  terminate_instances_output=$(aws ec2 terminate-instances --instance-ids "$instance_id")
  termination_state=$(echo "$terminate_instances_output" | jq '.TerminatingInstances[0].CurrentState.Name' | sed 's/"//g')
  echo "Terminating instance, in state: $termination_state"
  sleep 5
done
echo "Instance terminated successfully"
