package pl.kinga.exercise.file;

import java.io.IOException;
import java.net.URL;

import org.json.JSONException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pl.kinga.exercise.file.result.AnalyzeResult;

@RestController
public class FileController {

	@Autowired
	private FileService fileService;

	@RequestMapping(value = "/analyze", method = RequestMethod.POST)
	@ResponseBody
	public AnalyzeResult getAnalyzeResult(@RequestBody String url)  {
		
		AnalyzeResult analyzeresult = new AnalyzeResult();

		try {
			analyzeresult = fileService.createResult(url);
		} catch (JSONException | IOException e) {
			
			e.printStackTrace();
		}
		
		return analyzeresult;

	}

}
