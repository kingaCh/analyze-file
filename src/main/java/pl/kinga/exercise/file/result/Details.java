package pl.kinga.exercise.file.result;

public class Details {

	private String firstPost;
	private String lastPost;
	private int totalAcceptedAnswers;
	private double avgScore;

	public Details() {
		super();

	}

	public Details(String firstPost, String lastPost, int totalAcceptedAnswers, int avgScore) {
		super();
		this.firstPost = firstPost;
		this.lastPost = lastPost;
		this.totalAcceptedAnswers = totalAcceptedAnswers;
		this.avgScore = avgScore;
	}

	public String getFirstPost() {
		return firstPost;
	}

	public void setFirstPost(String firstPost) {
		this.firstPost = firstPost;
	}

	public String getLastPost() {
		return lastPost;
	}

	public void setLastPost(String lastPost) {
		this.lastPost = lastPost;
	}

	public int getTotalAcceptedAnswers() {
		return totalAcceptedAnswers;
	}

	public void setTotalAcceptedAnswers(int totalAcceptedAnswers) {
		this.totalAcceptedAnswers = totalAcceptedAnswers;
	}

	public double getAvgScore() {
		return avgScore;
	}

	public void setAvgScore(double d) {
		this.avgScore = d;
	}

}
