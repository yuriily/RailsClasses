

public class Project {
	private int suite_mode;
	private Long completed_on;
	private String name;
	private int id;
	private boolean show_announcement;
	private boolean is_completed;
	private String url;
	private String announcement;
	
	public Project(int suiteMode, Long completedOn, String name, int id, boolean showAnnouncement, boolean isCompleted,
			String url, String announcement) {
		super();
		
		//TODO: add check for not null for the fields: id, name, url
		
		this.suite_mode = suiteMode;
		this.completed_on = completedOn;
		this.name = name;
		this.id = id;
		this.show_announcement = showAnnouncement;
		this.is_completed = isCompleted;
		this.url = url;
		this.announcement = announcement;
	}

	public int getSuiteMode() {
		return suite_mode;
	}

	public void setSuiteMode(int suiteMode) {
		this.suite_mode = suiteMode;
	}

	public Long getCompletedOn() {
		return completed_on;
	}

	public void setCompletedOn(Long completedOn) {
		this.completed_on = completedOn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isShowAnnouncement() {
		return show_announcement;
	}

	public void setShowAnnouncement(boolean showAnnouncement) {
		this.show_announcement = showAnnouncement;
	}

	public boolean isCompleted() {
		return is_completed;
	}

	public void setCompleted(boolean isCompleted) {
		this.is_completed = isCompleted;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAnnouncement() {
		return announcement;
	}

	public void setAnnouncement(String announcement) {
		this.announcement = announcement;
	}
	
	

}
