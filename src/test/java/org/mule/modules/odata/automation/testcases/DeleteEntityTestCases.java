package org.mule.modules.odata.automation.testcases;

import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.odata.automation.testcases.provider.Person;

public class DeleteEntityTestCases extends ODataTestParent {
	
	@Before
	public void setUp() throws Exception {
		initializeTestRunMessage("deleteEntityTestData");
		
		Person entity = getTestRunMessageValue("entity");
		String entitySetName = getTestRunMessageValue("entitySetName");
		createEntity(entity, entitySetName);
		
	}
	
	@Category({RegressionTests.class})
	@Test
	public void testDeleteEntity() throws Exception {
		Person entity = getTestRunMessageValue("entity");
		String entitySetName = getTestRunMessageValue("entitySetName");
		String keyAttribute = getTestRunMessageValue("keyAttribute");
		
		deleteEntity(entitySetName, keyAttribute, entity);
		
		List<Person> entities = getEntities(entitySetName, entity.getClass().getName());
		assertFalse(entities.contains(entity));
	}

}
