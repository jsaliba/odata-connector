package org.mule.modules.odata.automation.testcases;

import static org.junit.Assert.assertFalse;
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

public class UpdateEntityTestCases extends ODataTestParent {

	@Before
	public void setUp() throws Exception {
		initializeTestRunMessage("updateEntityTestData");
		
		Person entity = getTestRunMessageValue("entity");
		String entitySetName = getTestRunMessageValue("entitySetName");
		createEntity(entity, entitySetName);
	}
	
	@Category({RegressionTests.class})
	@Test
	public void testUpdateEntity() {
		try {
			Person originalEntity = getTestRunMessageValue("entity");
			String entitySetName = getTestRunMessageValue("entitySetName");
			String keyAttribute = getTestRunMessageValue("keyAttribute");
			
			Person updatedEntity = getTestRunMessageValue("updatedEntity");
			updateEntity(entitySetName, keyAttribute, updatedEntity);
			
			List<Person> entities = getEntities(entitySetName, originalEntity.getClass().getName());
			
			assertTrue(entities.contains(updatedEntity));
			assertFalse(entities.contains(originalEntity));
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
