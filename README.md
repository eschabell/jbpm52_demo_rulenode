jBPM Demo with a Rule based Gateway (decision)
=============================================

This git repository helps you get up and running quickly with the jBPM 5 and a usable demo process.

To make use of this process project, just clone and make use of the maven setup as follows:

	1) clone the repo:    git clone git://github.com/eschabell/jbpm52_demo_rulenode.git

	2) get project deps:  mvn eclipse:eclipse   

	3) view process def:  src/main/resources/rulenodedemo.bpmn2

	4) run ProcessTest class (output in console) at src/main/java/ProcessTest
  
You can read the test class comments for details as to what is happening and you will see the output 
of the process as it enters and leaves nodes. You can tweak the path taken by setting a variable 
'validRequest' in the Initialize node, it is found in the Action property of that node and looks like
this:

	System.out.println("Entering Initialize Node");

	// Set this to true or false to adjust path gateway will take.
	kcontext.setVariable("validRequest", true);

	System.out.println("Set validRequest to: " + kcontext.getVariable("validRequest"));

	System.out.println("Leaving Initialize Node");

An example run should look like this if your validRequest is true:

	Entering Initialize Node

	Set validRequest to: true

	Leaving Initialize Node

	Entering Evaluation Node

	We have 0 Valid Requests

	Leaving Evaluation Node

	Gateway has detected a request with value of: true

	Entering Valid Action Node.

	Leaving Valid Action Node.

	Process Ended.

Enjoy!
