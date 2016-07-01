

public class SelectedRailsInstances {
	private Project project;
	private Plan plan;
	private Suite suite;
	private Configuration configuration;
	private ValuesMatrix valuesMatrix;
	
	
	public SelectedRailsInstances(Project project) {
		super();
		this.project = project;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public Plan getPlan() {
		return plan;
	}
	public void setPlan(Plan plan) {
		this.plan = plan;
	}
	public Suite getSuite() {
		return suite;
	}
	public void setSuite(Suite suite) {
		this.suite = suite;
	}
	public Configuration getConfiguration() {
		return configuration;
	}
	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}
	public ValuesMatrix getValuesMatrix() {
		return valuesMatrix;
	}
	public void setValuesMatrix(ValuesMatrix valuesMatrix) {
		this.valuesMatrix = valuesMatrix;
	}

}
