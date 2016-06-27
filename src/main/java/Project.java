

public class Project {
	private int suiteMode;
	private Long completedOn;
	private String name;
	private int id;
	private boolean showAnnouncement;
	private boolean isCompleted;
	private String url;
	private String announcement;
	
	public Project(int suiteMode, Long completedOn, String name, int id, boolean showAnnouncement, boolean isCompleted,
			String url, String announcement) {
		super();
		
		//TODO: add check for not null for the fields: id, name, url
		
		this.suiteMode = suiteMode;
		this.completedOn = completedOn;
		this.name = name;
		this.id = id;
		this.showAnnouncement = showAnnouncement;
		this.isCompleted = isCompleted;
		this.url = url;
		this.announcement = announcement;
	}

	public int getSuiteMode() {
		return suiteMode;
	}

	public void setSuiteMode(int suiteMode) {
		this.suiteMode = suiteMode;
	}

	public Long getCompletedOn() {
		return completedOn;
	}

	public void setCompletedOn(Long completedOn) {
		this.completedOn = completedOn;
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
		return showAnnouncement;
	}

	public void setShowAnnouncement(boolean showAnnouncement) {
		this.showAnnouncement = showAnnouncement;
	}

	public boolean isCompleted() {
		return isCompleted;
	}

	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
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
