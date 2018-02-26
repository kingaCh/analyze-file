package pl.kinga.exercise.file.result;

import java.time.LocalDateTime;

public class AnalyzeResult {
	private LocalDateTime analyzeDate;
	private Details details;

	public AnalyzeResult() {
		super();

	}

	public AnalyzeResult(Details details) {
		super();
		this.analyzeDate = LocalDateTime.now();
		this.details = details;
	}

	public LocalDateTime getAnalyzeDate() {
		return analyzeDate;
	}

	public void setAnalyzeDate() {
		this.analyzeDate = LocalDateTime.now();
	}

	public Details getDetails() {
		return details;
	}

	public void setDetails(Details details) {
		this.details = details;
	}

}
