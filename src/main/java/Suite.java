import com.google.gson.annotations.Expose;

public class Suite {
	private Long completed_on;
	private String description;
	private int id;
	private boolean is_baseline;
	private boolean is_completed;
	private boolean is_master;
	@Expose private String name;
	@Expose private int project_id;
	private String url;
	
	public Suite(Long completedOn, String description, int id, boolean isBaseline, boolean isCompleted, boolean isMaster,
			String name, int projectId) {
		super();
		this.completed_on = completedOn;
		this.description = description;
		this.id = id;
		this.is_baseline = isBaseline;
		this.is_completed = isCompleted;
		this.is_master = isMaster;
		this.name = name;
		this.project_id = projectId;
	}

	public Long getCompletedOn() {
		return completed_on;
	}
	public String getDescription() {
		return description;
	}
	public int getId() {
		return id;
	}
	public boolean isBaseline() {
		return is_baseline;
	}
	public boolean isCompleted() {
		return is_completed;
	}
	public boolean isMaster() {
		return is_master;
	}
	public String getName() {
		return name;
	}
	public int getProjectId() {
		return project_id;
	}
	public String getUrl() {
		return url;
	}
	
	

}
