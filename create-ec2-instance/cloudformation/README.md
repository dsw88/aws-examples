# CloudFormation Example
Simple example of using a CloudFormation template to create an EC2 instance

# Template Explanation
CloudFormation templates are JSON documents. They contain several top-level fields.
The most important of these is the *Resources* field. This is where you specify
which AWS resources will be created in the template.

This template creates two AWS resources:

* An EC2 Security Group
* An EC2 Instance

The security group is attached to the instance. It currently has ***wide-open***
permissions! **DO NOT use this template for anything other than learning!!** You
should never create an instance that is wide-open like this, it should always
be as locked-down as possible. The reason we are creating a wide-open instance
is to allow the template to be as simple as possible for understanding.

There's a lot going on in this template. When looking at this template, it is
helpful to consult the
[CloudFormation template reference](http://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/template-reference.html).

# Running This Template
To run this template, go to the CloudFormation app in the AWS Console. Then do
the following:

1. Click the *Create Stack* button.
2. Fill out the following information on the screen:
  * Name: Choose an arbitrary stack name. It must be unique in your account
  * Source: Select the *Upload a template to Amazon S3* option. Upload your template using the file picker.
3. Click the *Next* button to go to the next page.
4. Fill out the template parameters, or just leave them at their default values.
5. Click the *Next* button to go to the next page.
6. Don't add any tags on this page, just click *Next* again.
7. Review your template creation information, then click the *Create* button

CloudFormation will then begin to create your stack from your template. If there are
syntactical or logical errors, CloudFormation will fail the stack creation and display
the relevant error message.

# Deleting Your Test Stack
Once you've created a stack, deleting it is easy:

1. Go to the main CloudFormation app page in the AWS Console.
2. Select the stack you would like to delete from the list
3. Click the "Delete Stack" button. When it asks for confirmation, click the *Yes, Delete* button.
