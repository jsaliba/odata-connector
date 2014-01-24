package org.mule.modules.odata.automation.testcases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.odata.automation.testcases.provider.Person;
import org.mule.modules.tests.ConnectorTestUtils;

public class GetEntitiesTestCases extends ODataTestParent {

	@Before
	public void setUp() throws Exception {
		initializeTestRunMessage("getEntitiesTestData");
		
		List<Person> entities = getTestRunMessageValue("entities");
		String entitySetName = getTestRunMessageValue("entitySetName");
		for (Person person : entities) {
			createEntity(person, entitySetName);
		}
	}

	@Category({SmokeTests.class, RegressionTests.class})
	@Test
	public void testGetEntities() {
		try {
			List<Person> entities = getTestRunMessageValue("entities");
			String entitySetName = getTestRunMessageValue("entitySetName");
			
			List<Person> retrieved = getEntities(entitySetName, Person.class.getName());
			
			assertEquals(entities.size(), retrieved.size());
			assertTrue(EqualsBuilder.reflectionEquals(entities, retrieved));
		}
		catch (Exception e) {
			fail(ConnectorTestUtils.getStackTrace(e));
		}
	}
	
	@After
	public void tearDown() throws Exception {
		List<Person> entities = getTestRunMessageValue("entities");
		String entitySetName = getTestRunMessageValue("entitySetName");
		String keyAttribute = getTestRunMessageValue("keyAttribute");
		
		for (Person entity : entities) {
			deleteEntity(entitySetName, keyAttribute, entity);
		}
	}
	
}
