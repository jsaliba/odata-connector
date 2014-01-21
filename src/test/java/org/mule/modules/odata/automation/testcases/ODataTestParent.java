package org.mule.modules.odata.automation.testcases;

import org.junit.Rule;
import org.junit.rules.Timeout;
import org.mule.modules.tests.TestParent;

public class ODataTestParent extends TestParent {

	@Rule
	public Timeout timeout = new Timeout(600000);
	
	
	
}
