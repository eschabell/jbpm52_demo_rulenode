package org.jbpm.demo;

import java.util.HashMap;
import java.util.Map;

import org.drools.KnowledgeBase;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.logger.KnowledgeRuntimeLogger;
import org.drools.logger.KnowledgeRuntimeLoggerFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.process.WorkflowProcessInstance;
import org.jbpm.process.workitem.wsht.WSHumanTaskHandler;
//import org.jbpm.process.workitem.wsht.WSHumanTaskHandler;

/**
 * This is a sample file to launch a process.
 */
public class ProcessTest {

	public static final void main(String[] args) {
		try {
			// load up the knowledge base
			KnowledgeBase kbase = readKnowledgeBase();
			StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
			KnowledgeRuntimeLogger logger = KnowledgeRuntimeLoggerFactory.newThreadedFileLogger(ksession, "test", 1000);
			ksession.getWorkItemManager().registerWorkItemHandler("Human Task", new WSHumanTaskHandler());

			// start a new process instance by setup of a Person and Request.
			Person person = new Person("john", "John Doe");
			person.setAge(16);
			Request request = new Request("12345");
			request.setPersonId("john");
			request.setAmount(1000L);
			ksession.insert(person);

			// put them in the Map to be passed to the startProcess.
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("aPerson", person);
			params.put("aRequest", request);
			
			// Fire it up!
			WorkflowProcessInstance processInstance = (WorkflowProcessInstance) ksession.startProcess("org.jbpm.demo.rulenode", params);
			ksession.insert(processInstance);
			ksession.fireAllRules();
			
			// Finished, clean up the logger.
			System.out.println("Process Ended.");
			logger.close();
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	private static KnowledgeBase readKnowledgeBase() throws Exception {
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		kbuilder.add(ResourceFactory.newClassPathResource("rulenodedemo.bpmn2"), ResourceType.BPMN2);
		kbuilder.add(ResourceFactory.newClassPathResource("validation.drl"),
				ResourceType.DRL);

		return kbuilder.newKnowledgeBase();
	}

}
