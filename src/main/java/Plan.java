import com.google.gson.annotations.Expose;

public class Plan {
	private String name;
	private String description;
	private int milestone_id;
	private PlanEntry[] entries;
	public Plan(String name, String description, int milestoneId, PlanEntry[] entries) {
		super();
		this.name = name;
		this.description = description;
		this.milestone_id = milestoneId;
		this.entries = entries;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getMilestoneId() {
		return milestone_id;
	}
	public void setMilestoneId(int milestoneId) {
		this.milestone_id = milestoneId;
	}
	public PlanEntry[] getEntries() {
		return entries;
	}
	public void setEntries(PlanEntry[] entries) {
		this.entries = entries;
	}
	
	

}
