
public class Suite {
	private Long completedOn;
	private String description;
	private int id;
	private boolean isBaseline;
	private boolean isCompleted;
	private boolean isMaster;
	private String name;
	private int projectId;
	private String url;
	
	public Suite(Long completedOn, String description, int id, boolean isBaseline, boolean isCompleted, boolean isMaster,
			String name, int projectId) {
		super();
		this.completedOn = completedOn;
		this.description = description;
		this.id = id;
		this.isBaseline = isBaseline;
		this.isCompleted = isCompleted;
		this.isMaster = isMaster;
		this.name = name;
		this.projectId = projectId;
	}

	public Long getCompletedOn() {
		return completedOn;
	}
	public String getDescription() {
		return description;
	}
	public int getId() {
		return id;
	}
	public boolean isBaseline() {
		return isBaseline;
	}
	public boolean isCompleted() {
		return isCompleted;
	}
	public boolean isMaster() {
		return isMaster;
	}
	public String getName() {
		return name;
	}
	public int getProjectId() {
		return projectId;
	}
	public String getUrl() {
		return url;
	}
	
	

}
