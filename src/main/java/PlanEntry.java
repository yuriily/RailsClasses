
public class PlanEntry {
	private int suiteId;
	private String name;
	private String description;
	private int assignedToId;
	private boolean includeAll;
	private int[] configIds;
	private Run[] runs;
	
	public PlanEntry(int suiteId, String name, String description, int assignedToId, boolean includeAll,
			int[] configIds, Run[] runs) {
		super();
		this.suiteId = suiteId;
		this.name = name;
		this.description = description;
		this.assignedToId = assignedToId;
		this.includeAll = includeAll;
		this.configIds = configIds;
		this.runs = runs;
	}

	public int getSuiteId() {
		return suiteId;
	}

	public void setSuiteId(int suiteId) {
		this.suiteId = suiteId;
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

	public int getAssignedToId() {
		return assignedToId;
	}

	public void setAssignedToId(int assignedToId) {
		this.assignedToId = assignedToId;
	}

	public boolean isIncludeAll() {
		return includeAll;
	}

	public void setIncludeAll(boolean includeAll) {
		this.includeAll = includeAll;
	}

	public int[] getConfigIds() {
		return configIds;
	}

	public void setConfigIds(int[] configIds) {
		this.configIds = configIds;
	}

	public Run[] getRuns() {
		return runs;
	}

	public void setRuns(Run[] runs) {
		this.runs = runs;
	}
	
	

}
