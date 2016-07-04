import java.util.ArrayList;

public class Run {
	private Integer assignedto_id;
	private Integer blocked_count;
	private ArrayList<Integer> case_ids;
	private Long completed_on;
	private String config;
	private ArrayList<Integer> config_ids;
	private Integer created_by;
	private Long created_on;
	private Integer custom_status1_count;
	private Integer custom_status2_count;
	private Integer custom_status3_count;
	private Integer custom_status4_count;
	private Integer custom_status5_count;
	private Integer custom_status6_count;
	private Integer custom_status7_count;
	private String description;
	private Integer failed_count;
	private Integer id;
	private boolean include_all;
	private boolean is_completed;
	private Integer milestone_id;
	private Integer plan_id;
	private String name;
	private Integer passed_count;
	private Integer project_id;
	private Integer reset_count;
	private Integer suite_id;
	private Integer untested_count;
	private String url;
	
	public Run(int assignedTo, int blockedCount, Long completedOn, String config, ArrayList<Integer> configIds, int createdBy,
			Long createdOn, int customStatus1Count, int customStatus2Count, int customStatus3Count,
			int customStatus4Count, int customStatus5Count, int customStatus6Count, int customStatus7Count,
			String description, int failedCount, int id, boolean includeAll, boolean isCompleted, int milestoneId,
			int planId, String name, int passedCount, int projectId, int resetCount, int suiteId, int untestedCount,
			String url) {
		super();
		this.assignedto_id = assignedTo;
		this.blocked_count = blockedCount;
		this.completed_on = completedOn;
		this.config = config;
		this.config_ids = configIds;
		this.created_by = createdBy;
		this.created_on = createdOn;
		this.custom_status1_count = customStatus1Count;
		this.custom_status2_count = customStatus2Count;
		this.custom_status3_count = customStatus3Count;
		this.custom_status4_count = customStatus4Count;
		this.custom_status5_count = customStatus5Count;
		this.custom_status6_count = customStatus6Count;
		this.custom_status7_count = customStatus7Count;
		this.description = description;
		this.failed_count = failedCount;
		this.id = id;
		this.include_all = includeAll;
		this.is_completed = isCompleted;
		this.milestone_id = milestoneId;
		this.plan_id = planId;
		this.name = name;
		this.passed_count = passedCount;
		this.project_id = projectId;
		this.reset_count = resetCount;
		this.suite_id = suiteId;
		this.untested_count = untestedCount;
		this.url = url;
	}
	public Run() {
		// TODO Auto-generated constructor stub
	}
	public String getConfig() {
		return config;
	}
	public void setConfig(String config) {
		this.config = config;
	}
	public ArrayList<Integer> getConfigIds() {
		return config_ids;
	}
	public void setConfigIds(ArrayList<Integer> configIds) {
		this.config_ids = configIds;
	}
	public Long getCreatedOn() {
		return created_on;
	}
	public void setCreatedOn(Long createdOn) {
		this.created_on = createdOn;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isIncludeAll() {
		return include_all;
	}
	public void setIncludeAll(boolean includeAll) {
		this.include_all = includeAll;
	}
	public boolean isCompleted() {
		return is_completed;
	}
	public void setCompleted(boolean isCompleted) {
		this.is_completed = isCompleted;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public ArrayList<Integer> getCaseIds() {
		return case_ids;
	}
	public void setCaseIds(ArrayList<Integer> caseIds) {
		this.case_ids = caseIds;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPlanId() {
		return plan_id;
	}
	public void setPlanId(Integer planId) {
		this.plan_id = planId;
	}
	public Integer getProjectId() {
		return project_id;
	}
	public void setProjectId(Integer projectId) {
		this.project_id = projectId;
	}
	public Integer getSuiteId() {
		return suite_id;
	}
	public void setSuiteId(Integer suiteId) {
		this.suite_id = suiteId;
	}
	public void setMilestoneId(Integer milestoneId) {
		this.milestone_id = milestoneId;
	}
	public Integer getAssignedToId() {
		return assignedto_id;
	}
	public void setAssignedToId(Integer assignedToId) {
		this.assignedto_id = assignedToId;
	}
	public Integer getBlockedCount() {
		return blocked_count;
	}
	public void setBlockedCount(Integer blockedCount) {
		this.blocked_count = blockedCount;
	}
	public Long getCompletedOn() {
		return completed_on;
	}
	public void setCompletedOn(Long completedOn) {
		this.completed_on = completedOn;
	}
	public Integer getCreatedBy() {
		return created_by;
	}
	public void setCreatedBy(Integer createdBy) {
		this.created_by = createdBy;
	}
	public Integer getCustomStatus1Count() {
		return custom_status1_count;
	}
	public void setCustomStatus1Count(Integer customStatus1Count) {
		this.custom_status1_count = customStatus1Count;
	}
	public Integer getCustomStatus2Count() {
		return custom_status2_count;
	}
	public void setCustomStatus2Count(Integer customStatus2Count) {
		this.custom_status2_count = customStatus2Count;
	}
	public Integer getCustomStatus3Count() {
		return custom_status3_count;
	}
	public void setCustomStatus3Count(Integer customStatus3Count) {
		this.custom_status3_count = customStatus3Count;
	}
	public Integer getCustomStatus4Count() {
		return custom_status4_count;
	}
	public void setCustomStatus4Count(Integer customStatus4Count) {
		this.custom_status4_count = customStatus4Count;
	}
	public Integer getCustomStatus5Count() {
		return custom_status5_count;
	}
	public void setCustomStatus5Count(Integer customStatus5Count) {
		this.custom_status5_count = customStatus5Count;
	}
	public Integer getCustomStatus6Count() {
		return custom_status6_count;
	}
	public void setCustomStatus6Count(Integer customStatus6Count) {
		this.custom_status6_count = customStatus6Count;
	}
	public Integer getCustomStatus7Count() {
		return custom_status7_count;
	}
	public void setCustomStatus7Count(Integer customStatus7Count) {
		this.custom_status7_count = customStatus7Count;
	}
	public Integer getFailedCount() {
		return failed_count;
	}
	public void setFailedCount(Integer failedCount) {
		this.failed_count = failedCount;
	}
	public Integer getPassedCount() {
		return passed_count;
	}
	public void setPassedCount(Integer passedCount) {
		this.passed_count = passedCount;
	}
	public Integer getResetCount() {
		return reset_count;
	}
	public void setResetCount(Integer resetCount) {
		this.reset_count = resetCount;
	}
	public Integer getUntestedCount() {
		return untested_count;
	}
	public void setUntestedCount(Integer untestedCount) {
		this.untested_count = untestedCount;
	}
	public Integer getMilestoneId() {
		return milestone_id;
	}
	
	
	
}
