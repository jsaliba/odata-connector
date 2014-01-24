package org.mule.modules.odata.automation.testcases;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.odata.automation.testcases.provider.Person;
import org.mule.modules.tests.ConnectorTestUtils;
import org.odata4j.producer.resources.BatchResult;

public class BatchTestCases extends ODataTestParent {
	
	@Before
	public void setUp() throws Exception {
		initializeTestRunMessage("batchTestData");
	}
	
	@Category({RegressionTests.class})
	@Test
	public void testBatch() {
		try {
			Person entity = getTestRunMessageValue("entity");
			String entitySetName = getTestRunMessageValue("entitySetName");
			String keyAttribute = getTestRunMessageValue("keyAttribute");
			
			BatchResult result = batch(entitySetName, keyAttribute, entity);
			
			List<Person> entities = getEntities(entitySetName, entity.getClass().getName());
			assertFalse(entities.contains(entity));
		}
		catch (Exception e) {
			fail(ConnectorTestUtils.getStackTrace(e));
		}
	}
	
}
