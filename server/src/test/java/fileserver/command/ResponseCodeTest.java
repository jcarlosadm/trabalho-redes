package fileserver.command;

import static org.junit.Assert.*;

import org.junit.Test;

public class ResponseCodeTest {

	@Test
	public void testCodes() throws Exception {
		
		ResponseCode[] responseCodes = ResponseCode.values();
		for(int index1 = 0; index1 < responseCodes.length; ++index1)
			for(int index2 = index1 + 1; index2 < responseCodes.length; ++index2)
				assertFalse(responseCodes[index1].getResponseCode() == responseCodes[index2].getResponseCode());
		
	}
	
	@Test
	public void testNames() throws Exception {
		for (ResponseCode responseCode : ResponseCode.values()) {
			assertTrue(responseCode.toString().matches("[\\d]+[\\s][\\w\\W]+"));
		}
	}

}
