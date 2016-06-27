
public class Run {
	private int assignedToId;
	private int blockedCount;
	private int[] caseIds;
	private Long completedOn;
	private String config;
	private int[] configIds;
	private int createdBy;
	private Long createdOn;
	private int customStatus1Count;
	private int customStatus2Count;
	private int customStatus3Count;
	private int customStatus4Count;
	private int customStatus5Count;
	private int customStatus6Count;
	private int customStatus7Count;
	private String description;
	private int failedCount;
	private int id;
	private boolean includeAll;
	private boolean isCompleted;
	private int milestoneId;
	private int planId;
	private String name;
	private int passedCount;
	private int projectId;
	private int resetCount;
	private int suiteId;
	private int untestedCount;
	private String url;
	public Run(int assignedTo, int blockedCount, Long completedOn, String config, int[] configIds, int createdBy,
			Long createdOn, int customStatus1Count, int customStatus2Count, int customStatus3Count,
			int customStatus4Count, int customStatus5Count, int customStatus6Count, int customStatus7Count,
			String description, int failedCount, int id, boolean includeAll, boolean isCompleted, int milestoneId,
			int planId, String name, int passedCount, int projectId, int resetCount, int suiteId, int untestedCount,
			String url) {
		super();
		this.assignedToId = assignedTo;
		this.blockedCount = blockedCount;
		this.completedOn = completedOn;
		this.config = config;
		this.configIds = configIds;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
		this.customStatus1Count = customStatus1Count;
		this.customStatus2Count = customStatus2Count;
		this.customStatus3Count = customStatus3Count;
		this.customStatus4Count = customStatus4Count;
		this.customStatus5Count = customStatus5Count;
		this.customStatus6Count = customStatus6Count;
		this.customStatus7Count = customStatus7Count;
		this.description = description;
		this.failedCount = failedCount;
		this.id = id;
		this.includeAll = includeAll;
		this.isCompleted = isCompleted;
		this.milestoneId = milestoneId;
		this.planId = planId;
		this.name = name;
		this.passedCount = passedCount;
		this.projectId = projectId;
		this.resetCount = resetCount;
		this.suiteId = suiteId;
		this.untestedCount = untestedCount;
		this.url = url;
	}
	public int getAssignedTo() {
		return assignedToId;
	}
	public void setAssignedTo(int assignedTo) {
		this.assignedToId = assignedTo;
	}
	public int getBlockedCount() {
		return blockedCount;
	}
	public void setBlockedCount(int blockedCount) {
		this.blockedCount = blockedCount;
	}
	public Long getCompletedOn() {
		return completedOn;
	}
	public void setCompletedOn(Long completedOn) {
		this.completedOn = completedOn;
	}
	public String getConfig() {
		return config;
	}
	public void setConfig(String config) {
		this.config = config;
	}
	public int[] getConfigIds() {
		return configIds;
	}
	public void setConfigIds(int[] configIds) {
		this.configIds = configIds;
	}
	public int getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	public Long getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Long createdOn) {
		this.createdOn = createdOn;
	}
	public int getCustomStatus1Count() {
		return customStatus1Count;
	}
	public void setCustomStatus1Count(int customStatus1Count) {
		this.customStatus1Count = customStatus1Count;
	}
	public int getCustomStatus2Count() {
		return customStatus2Count;
	}
	public void setCustomStatus2Count(int customStatus2Count) {
		this.customStatus2Count = customStatus2Count;
	}
	public int getCustomStatus3Count() {
		return customStatus3Count;
	}
	public void setCustomStatus3Count(int customStatus3Count) {
		this.customStatus3Count = customStatus3Count;
	}
	public int getCustomStatus4Count() {
		return customStatus4Count;
	}
	public void setCustomStatus4Count(int customStatus4Count) {
		this.customStatus4Count = customStatus4Count;
	}
	public int getCustomStatus5Count() {
		return customStatus5Count;
	}
	public void setCustomStatus5Count(int customStatus5Count) {
		this.customStatus5Count = customStatus5Count;
	}
	public int getCustomStatus6Count() {
		return customStatus6Count;
	}
	public void setCustomStatus6Count(int customStatus6Count) {
		this.customStatus6Count = customStatus6Count;
	}
	public int getCustomStatus7Count() {
		return customStatus7Count;
	}
	public void setCustomStatus7Count(int customStatus7Count) {
		this.customStatus7Count = customStatus7Count;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getFailedCount() {
		return failedCount;
	}
	public void setFailedCount(int failedCount) {
		this.failedCount = failedCount;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isIncludeAll() {
		return includeAll;
	}
	public void setIncludeAll(boolean includeAll) {
		this.includeAll = includeAll;
	}
	public boolean isCompleted() {
		return isCompleted;
	}
	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}
	public int getMilestoneId() {
		return milestoneId;
	}
	public void setMilestoneId(int milestoneId) {
		this.milestoneId = milestoneId;
	}
	public int getPlanId() {
		return planId;
	}
	public void setPlanId(int planId) {
		this.planId = planId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPassedCount() {
		return passedCount;
	}
	public void setPassedCount(int passedCount) {
		this.passedCount = passedCount;
	}
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public int getResetCount() {
		return resetCount;
	}
	public void setResetCount(int resetCount) {
		this.resetCount = resetCount;
	}
	public int getSuiteId() {
		return suiteId;
	}
	public void setSuiteId(int suiteId) {
		this.suiteId = suiteId;
	}
	public int getUntestedCount() {
		return untestedCount;
	}
	public void setUntestedCount(int untestedCount) {
		this.untestedCount = untestedCount;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int[] getCaseIds() {
		return caseIds;
	}
	public void setCaseIds(int[] caseIds) {
		this.caseIds = caseIds;
	}
	
	
	
}
