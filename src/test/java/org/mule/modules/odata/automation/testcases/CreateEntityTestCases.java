package org.mule.modules.odata.automation.testcases;

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

public class CreateEntityTestCases extends ODataTestParent {

	@Before
	public void setUp() throws Exception {
		initializeTestRunMessage("createEntityTestData");
	}

	@Category({SmokeTests.class, RegressionTests.class})
	@Test
	public void testCreateEntity() {
		try {
			Person person = getTestRunMessageValue("entity");
			String entitySetName = getTestRunMessageValue("entitySetName");
			
			createEntity(person, entitySetName);
			
			List<Person> entities = getEntities(entitySetName, Person.class.getName());
			boolean found = false;
			for (Person entity : entities) {
				if (EqualsBuilder.reflectionEquals(entity, person)) {
					found = true;
					break;
				}
			}
			
			assertTrue(found);
		}
		catch (Exception e) {
			fail(ConnectorTestUtils.getStackTrace(e));
		}
	}
	
	@After
	public void tearDown() throws Exception {
		Person entity = getTestRunMessageValue("entity");
		String entitySetName = getTestRunMessageValue("entitySetName");
		String keyAttribute = getTestRunMessageValue("keyAttribute");
		
		deleteEntity(entitySetName, keyAttribute, entity);
	}
	
}
