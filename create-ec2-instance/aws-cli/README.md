# AWS CLI Bash Example
Simple example of using the AWS CLI to create and destroy an EC2 instance

This example uses Bash and the AWS CLI to launch an EC2 instance, wait for it
to finish creating, then terminate it.

# Example Prerequisites
The script requires several dependencies:
* AWS CLI (Best installed via the Python 'Pip' package manager)
* jq (Command-line tool to parse JSON results from AWS CLI)

You can install those dependencies yourself if you wish. Alternatively, I've
provided a Vagrantfile in the root of this repository. You can use Vagrant to
"vagrant up", which will install all required dependencies for these examples.

Before running this example, you must configure the AWS CLI with your credentials:
```
aws configure

# OUTPUT:
# AWS Access Key ID [None]: <enter_your_access_key_here>
# AWS Secret Access Key [None]: <enter_your_secret_key_here>
# Default region name [None]: us-east-1
# Default output format [None]:
```

# Example Usage
To run this example, just invoke the bash script from this directory:
```
./bash/create-instance.sh
```

# Example Output
When running this example, you'll see output similar to the following:
```
Launching instance with ID=i-315200df
Instance launching, in state: pending
Instance launching, in state: pending
Instance launching, in state: running
Instance launched successfully
Terminating instance, in state: shutting-down
Terminating instance, in state: shutting-down
Terminating instance, in state: shutting-down
Terminating instance, in state: shutting-down
Terminating instance, in state: terminated
Instance terminated successfully
```
