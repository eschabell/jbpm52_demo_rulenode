Update: June 2018 moved to [Gitlab](https://gitlab.com/eschabell/jbpm52_demo_rulenode)


jBPM Demo with a Rule based Gateway (decision)
=============================================

This git repository helps you get up and running quickly with the jBPM 5 and a usable demo process.

To make use of this process project, just clone and make use of the maven setup as follows:

	1) clone the repo:    git clone git://github.com/eschabell/jbpm52_demo_rulenode.git

	2) get project deps:  mvn eclipse:eclipse   

	3) view process def:  src/main/resources/rulenodedemo.bpmn2

	4) run ProcessTest class (output in console) at src/main/java/ProcessTest
  
You can read the test class comments for details as to what is happening and you will see the output 
of the process as it enters and leaves nodes. The age of the person in the test currently determines
if the request is Valid or Invalid (18+ age) and then the amount of the persons request is tested for
valid or invalid (amount >= 1000).

Still TODO: integrate the Finance Rules to actually do something with an external rule file and 
to extend valid path to then check for valid amount in request.

An example run should look like this if your request is of valid age and amount:

	Entering Initialize Node

	Leaving Initialize Node

	Gateway: Qualify Age

	Gateway: Qualify Age

	Entering Adult Customer Node

	Detected and reporting valid request

	Set validRequest to: true

	Leaving Adult Customer Node

	Entering Finance Rules Node

	Leaving Finance Rules Node.

	Gateway: Decide Financial Status

	Leaving Finance Rules Node.

	Gateway: Decide Financial Status

	Entering Rich Customer Node

	Detected and reporting valid request

	Leaving Rich Customer Node

	Process Ended.

Enjoy!
