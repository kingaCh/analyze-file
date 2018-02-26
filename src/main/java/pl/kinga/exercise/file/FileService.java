package pl.kinga.exercise.file;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import pl.kinga.exercise.file.result.AnalyzeResult;
import pl.kinga.exercise.file.result.Details;

@Service
public class FileService {

	@Autowired
	private ResourceLoader resourceLoader;

	private AnalyzeResult analyzeDate = new AnalyzeResult();
	private Details details = new Details();
	private Logger logger = LoggerFactory.getLogger(FileService.class);

	public AnalyzeResult createResult(String string) throws JSONException, IOException {
		analyzeDate.setAnalyzeDate();
		analyzeDate.setDetails(details);
		details.setFirstPost(findFirstPost(string));
		details.setLastPost(findLastPost(string));
		details.setTotalAcceptedAnswers(countTotalAcceptedAnswer(string));
		details.setAvgScore(countAvgScore(string));

		return analyzeDate;

	}

	// read File as xml String
	private String readFile(String url) {
		Resource resource = resourceLoader.getResource(url);
		StringBuilder stringBuilder = new StringBuilder();
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
			reader.lines().forEach(stringBuilder::append);
		} catch (IOException e) {

			logger.error("Wrong url", e);

		}
		return stringBuilder.toString();
	}

	public JSONObject xmlToJson(String xmlString) {

		String xmlFile = readFile(xmlString);
		JSONObject jsonObject = null;
		try {
			jsonObject = XML.toJSONObject(xmlFile);
		} catch (JSONException e) {

			logger.error("XML to JSON conversion faild", e);
		}
		return jsonObject;

	}

	private JSONArray sortJSONArray(String string) {

		JSONObject postsObject;
		JSONArray rowArray = null;
		JSONArray sortedRowArray = new JSONArray();
		List<JSONObject> jsonValues = new ArrayList<JSONObject>();

		try {
			postsObject = xmlToJson(string).getJSONObject("posts");
			rowArray = postsObject.getJSONArray("row");
			for (int i = 0; i < rowArray.length(); i++) {
				jsonValues.add(rowArray.getJSONObject(i));
			}

		} catch (JSONException e1) {

			logger.error("Failed to create JSONArray", e1);
		}

		Collections.sort(jsonValues, new Comparator<JSONObject>() {

			@Override
			public int compare(JSONObject a, JSONObject b) {
				int compare = 0;

				try {

					String dateOne = a.getString("CreationDate");
					String dateTwo = b.getString("CreationDate");

					compare = dateOne.compareTo(dateTwo);

				} catch (JSONException e) {
					logger.error("Couldn't find JSONObject to compare", e);
				}
				return compare;
			}

		});

		for (int i = 0; i < rowArray.length(); i++) {

			sortedRowArray.put(jsonValues.get(i));
		}
		return sortedRowArray;
	}

	private String findFirstPost(String string) {

		String firstPost = null;
		try {
			firstPost = sortJSONArray(string).getJSONObject(0).getString("CreationDate");
		} catch (JSONException e) {
			logger.error("Couldn't find CreationDate", e);
		}
		return firstPost;
	}

	private String findLastPost(String string) {
		JSONArray jsonArray = sortJSONArray(string);
		String lastPost = null;
		try {
			lastPost = jsonArray.getJSONObject(jsonArray.length() - 1).getString("CreationDate");
		} catch (JSONException e) {
			logger.error("Couldn't find CreationDate", e);
		}
		return lastPost;
	}

	private int countTotalAcceptedAnswer(String string) {
		JSONArray jsonArray = sortJSONArray(string);
		int totalAcceptedAnswerId = 0;
		for (int i = 0; i < jsonArray.length(); i++) {

			int count = 0;
			try {
				count = jsonArray.getJSONObject(i).optInt("AcceptedAnswerId");
			} catch (JSONException e) {
				logger.error("Couldn't find JSONArray", e);
			}
			totalAcceptedAnswerId = totalAcceptedAnswerId + count;
		}
		return totalAcceptedAnswerId;
	}

	private double countAvgScore(String string) throws JSONException {
		JSONArray jsonArray = sortJSONArray(string);
		double avarageScore = 0;
		int sum = 0;
		JSONArray scoreArray = new JSONArray();
		for (int i = 0; i < jsonArray.length(); i++) {
			if (jsonArray.getJSONObject(i).has("Score")) {
				scoreArray.put(jsonArray.get(i));
			}
		}

		for (int i = 0; i < scoreArray.length(); i++) {
			int a = scoreArray.getJSONObject(i).getInt("Score");

			sum = sum + a;
			avarageScore = sum / scoreArray.length();
		}
		return avarageScore;
	}
}
