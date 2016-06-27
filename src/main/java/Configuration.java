
public class Configuration {
	private ConfigurationItem configurationItems[];
	private int id;
	private String name;
	private int projectId;
	public Configuration(ConfigurationItem[] configurationItems, int id, String name, int projectId) {
		super();
		this.configurationItems = configurationItems;
		this.id = id;
		this.name = name;
		this.projectId = projectId;
	}
	public ConfigurationItem[] getConfigurationItems() {
		return configurationItems;
	}
	public void setConfigurationItems(ConfigurationItem[] configurationItems) {
		this.configurationItems = configurationItems;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	
	

}
