package pl.kinga.exercise.file;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.json.JSONException;
import org.json.JSONObject;

import org.junit.Test;


public class FileControllerTest {

	private static FileService fileService = new FileService();

	

	@Test
	public void testPrivateXmlToJson() throws JSONException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
			String input ="<note><to>Tove</to><from>Jani</from><heading>Reminder</heading><body>Don't forget me this weekend!</body></note>";
			String jsonString = "{\r\n" + 
	        		"  \"note\": {\r\n" + 
	        		"    \"to\": \"Tove\",\r\n" + 
	        		"    \"from\": \"Jani\",\r\n" + 
	        		"    \"heading\": \"Reminder\",\r\n" + 
	        		"    \"body\": \"Don't forget me this weekend!\"\r\n" + 
	        		"  }\r\n" + 
	        		"}";
			JSONObject result = new JSONObject(jsonString);
		
		 	Method method = FileService.class.getDeclaredMethod("xmlToJson", JSONObject.class);
	        method.setAccessible(true);
	        
	        JSONObject output = (JSONObject) method.invoke(fileService, input);

	        assertEquals(result, output);

	}

}
