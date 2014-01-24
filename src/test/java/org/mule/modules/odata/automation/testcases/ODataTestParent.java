package org.mule.modules.odata.automation.testcases;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.Timeout;
import org.mule.modules.odata.automation.testcases.provider.ODataServiceProvider;
import org.mule.modules.odata.automation.testcases.provider.Person;
import org.mule.modules.tests.ConnectorTestCase;
import org.odata4j.producer.resources.DefaultODataProducerProvider;
import org.odata4j.producer.server.ODataServer;
import org.springframework.stereotype.Controller;

@Controller
public class ODataTestParent extends ConnectorTestCase {
	
	@Rule
	public Timeout globalTimeout = new Timeout(600000);

	private ODataServer server;

	@Before
	public void initServer() throws Exception {
		String serviceUri = getBeanFromContext("serviceUri");
		
		DefaultODataProducerProvider.setInstance(ODataServiceProvider.createJpaProducer());
		
		server = ODataServiceProvider.getServer(serviceUri);
		server.start();
	}
	
	@After
	public void tearDownServer() throws Exception {
		System.out.println("Tearing down server");
		server.stop();
	}
	
	public void createEntity(Person person, String entitySetName) throws Exception {
		upsertOnTestRunMessage("entityRef", person);
		upsertOnTestRunMessage("entitySetName", entitySetName);

		runFlowAndGetPayload("create-entity");
	}

	public void deleteEntity(String entitySetName, String keyAttribute, Person entity) throws Exception {
		upsertOnTestRunMessage("entitySetName", entitySetName);
		upsertOnTestRunMessage("keyAttribute", keyAttribute);
		upsertOnTestRunMessage("entityRef", entity);

		runFlowAndGetPayload("delete-entity");
	}
	
	public void updateEntity(String entitySetName, String keyAttribute, Person entity) throws Exception {
		upsertOnTestRunMessage("entitySetName", entitySetName);
		upsertOnTestRunMessage("keyAttribute", keyAttribute);
		upsertOnTestRunMessage("entityRef", entity);

		runFlowAndGetPayload("update-entity");
	}
	
	public List<Person> getEntities(String entitySetName, String returnClass) throws Exception {
		upsertOnTestRunMessage("entitySetName", entitySetName);
		upsertOnTestRunMessage("returnClass", returnClass);
		
		return runFlowAndGetPayload("get-entities");
	}
	
	
	
}
