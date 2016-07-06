import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.gurock.testrail.APIClient;
import com.gurock.testrail.APIException;

import java.util.Map;

import javax.net.ssl.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class FetchData {
	public static final String APIKEY = "BtiEB.ufbdTcx.yGGVb/-sXfC1RvxnCwcaeecZQby";
	public static final int PROJECT_ID = 76; //31
	public static final int FULL_TEST_SUITE_ID = 301;
	public static final int CONFIG_ID = 124;
	public static final int PLAN_ID = 8874;
	public static final String fileNameForCSV = "C:\\Trash\\~tmp\\rails.csv";
	
	
	public static SelectedRailsInstances railsInstances;
	
	//TODO do we need it now?
	public ConfigurationItem configItems[];
	
	
	private LinkedHashSet<String> dataRows;
	private LinkedHashSet<String> dataCols;

	public static void main(String[] args) throws MalformedURLException, IOException, APIException {
		// TODO Auto-generated method stub
		System.out.println("Started");

		trustEveryone();
		Gson gson = new Gson();

		APIClient client = new APIClient("https://testrail.ee.playtech.corp/testrail/");
		client.setUser("");
		client.setPassword("");


		System.out.println("Getting projects...");
		JSONArray jsonProjects = (JSONArray) client.sendGet("get_projects");
		System.out.println(jsonProjects.toJSONString());
		Project projects[] = gson.fromJson(jsonProjects.toJSONString(), Project[].class);
		Project currentProject = null;
		for(int iter = 0; iter<projects.length; iter++)
			if(PROJECT_ID==projects[iter].getId())
				currentProject = projects[iter];
		
		railsInstances = new SelectedRailsInstances(currentProject);
		
		System.out.println("Getting configurations...");
		JSONArray jsonConfigs = (JSONArray) client.sendGet("get_configs/" + PROJECT_ID);
		Configuration configs[] = gson.fromJson(jsonConfigs.toJSONString(), Configuration[].class);
		System.out.println(jsonConfigs.toJSONString());
		
		
		System.exit(0);
		
		System.out.println("Getting suites...");
		JSONArray jsonSuites = (JSONArray) client.sendGet("get_suites/" + PROJECT_ID);
		Suite suites[] = gson.fromJson(jsonSuites.toJSONString(), Suite[].class);
		JSONObject jsonSuite = (JSONObject) client.sendGet("get_suite/"+FULL_TEST_SUITE_ID);
		railsInstances.setSuite((Suite)gson.fromJson(jsonSuite.toJSONString(), Suite.class));

		System.out.println("Getting cases...");
		JSONArray jsonCases = (JSONArray) client
				.sendGet("get_cases/" + PROJECT_ID + "/&suite_id=" + FULL_TEST_SUITE_ID);
		Case cases[] = gson.fromJson(jsonCases.toJSONString(), Case[].class);
		railsInstances.setCases(cases);
		
		LinkedHashSet<String> dataRows = new LinkedHashSet<String>();
		//loop here to check for nulls
		for(int iter=0; iter<cases.length; iter++) {
			if(!cases[iter].getTitle().equals(""))
				dataRows.add(cases[iter].getTitle());
		}

		System.out.println("Getting configurations...");
//		JSONArray jsonConfigs = (JSONArray) client.sendGet("get_configs/" + PROJECT_ID);
//		Configuration configs[] = gson.fromJson(jsonConfigs.toJSONString(), Configuration[].class);
		LinkedHashSet<String> dataCols = new LinkedHashSet<String>();

		System.out.println("Configuration items fill-in...");
		for (int iter = 0; iter < configs.length; iter++) {
			if (CONFIG_ID == configs[iter].getId()) {
				railsInstances.setConfiguration(configs[iter]);
				ConfigurationItem configItems[] = Arrays.copyOf(configs[iter].getConfigurationItems(), 
						configs[iter].getConfigurationItems().length);
				for (int configIter = 0; configIter < configItems.length; configIter++)
					dataCols.add(configItems[configIter].getName());
			}
		}
		ValuesMatrix values = new ValuesMatrix(dataRows, dataCols);
		
		try {
//			values.writeToCSV(fileNameForCSV);
			values.readFromCSV(fileNameForCSV);
		} catch (IOException e) {
			e.printStackTrace();
		}
		railsInstances.setValuesMatrix(values);
		JSONObject jsonPlan = (JSONObject) client.sendGet("get_plan/"+PLAN_ID);
		railsInstances.setPlan((Plan)gson.fromJson(jsonPlan.toJSONString(), Plan.class));
		//String testPlanJson = populateTestPlan();
		PlanEntry planEntry = createTestPlanEntry();
		planEntry.setAssignedToId(17);
		
		//return json of planEntry for posting to forum
		
		
		Map<String, Object> data=new HashMap<>();
		data.put("suite_id", planEntry.getSuiteId());
		data.put("name", planEntry.getName());
		data.put("assignedto_id", planEntry.getAssignedToId());
		data.put("include_all", true);
		data.put("config_ids", planEntry.getConfigIds());
		
		List entryRuns = new ArrayList();
		for(Run currentRun : planEntry.getRuns()) {
			Map<String, Object> mapRun=new HashMap<>();
			mapRun.put("suite_id", currentRun.getSuiteId());
			mapRun.put("name", currentRun.getName());
			mapRun.put("is_completed", currentRun.isCompleted());
			mapRun.put("include_all", currentRun.isIncludeAll());
			mapRun.put("case_ids", currentRun.getCaseIds());
			mapRun.put("config_ids", currentRun.getConfigIds());
			mapRun.put("description", currentRun.getDescription());
			entryRuns.add(mapRun);
		}
		
		data.put("runs", entryRuns);
		
		//upload the results to TestRails
//		JSONObject jsonResult = (JSONObject) client.sendPost("add_plan_entry/"+PLAN_ID, data);
//		System.out.println(jsonResult.toJSONString());
		
//		JSONObject jsonResult = (JSONObject) client.sendPost("add_plan_entry/"+PLAN_ID, testPlanJson);
//		System.out.println(jsonResult.toJSONString());


	}
	
	private static String populateTestPlan() {
		//fills in current test plan with data stored in values matrix
		
		//TODO add some error handler in case if some object is empty
		StringBuilder results = new StringBuilder();
		Boolean isFailed = false;
		
		//check if everything is set up: 
		//current plan, suite, configuration, and file with data
		//TODO: throw strings into outer configuration
		if(null == railsInstances.getValuesMatrix() || railsInstances.getValuesMatrix().isEmpty()) {
			results.append("ERROR: there is no data in the file or the file is absent.\n");
			isFailed=true;
		}
		if(null==railsInstances.getPlan()) {
			results.append("ERROR: Test plan is not selected.\n");
			isFailed=true;
		}
		if(null==railsInstances.getSuite()) {
			results.append("ERROR: Test suite is not selected.\n");
			isFailed=true;
		}
		if(null==railsInstances.getConfiguration()) {
			results.append("ERROR: Test configuration is not selected.\n");
			isFailed=true;
		}
		if(null==railsInstances.getCases() || railsInstances.getCases().length==0) {
			results.append("ERROR: No test cases were found or current test suite is empty.\n");
			isFailed=true;
		}
		
		if(isFailed)
			return results.toString();
		
		// we've got suite that knows test case ids and names
		// now they should be mapped to test names of the matrix that was read from csv
		try {
			railsInstances.getValuesMatrix().setDataCaseIds(railsInstances.getCases());
		} catch (Exception e) {
			e.printStackTrace();
			results.append("ERROR: Error during mapping test instances");
			return results.toString();
		}
		PlanEntry planEntry = createTestPlanEntry();
		planEntry.setAssignedToId(17);
		Gson gson = new Gson();
		String jsonString = gson.toJson(planEntry);
		
		return jsonString;
		
	}
	
	private static PlanEntry createTestPlanEntry() {
		//create entry consisting of test runs
		PlanEntry planEntry = new PlanEntry();
		
		//remove this when done testing, because current method should be called only from
		//populateTestPlan() which already has this
		try {
			railsInstances.getValuesMatrix().setDataCaseIds(railsInstances.getCases());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		planEntry.setSuiteId(FULL_TEST_SUITE_ID);
		planEntry.setName("Common tests");
		planEntry.setIncludeAll(false);
		//set config_ids = current config
		ConfigurationItem[] configItems = railsInstances.getConfiguration().getConfigurationItems();
		ArrayList<Integer> configIds = new ArrayList<>();
		for(int iter=0;iter<configItems.length;iter++)
			configIds.add(configItems[iter].getId());
		planEntry.setConfigIds(configIds);
		
		//add all test runs, number of runs = number of items in selected configuration
		ArrayList<Run> testRuns = new ArrayList<>();
		for(int iter=0;iter<configItems.length;iter++) {
			testRuns.add(createTestRun(null, configItems[iter]));
		}
		planEntry.setRuns(testRuns);
		return planEntry;
	}
	
	private static Run createTestRun(Suite suite, ConfigurationItem configItem) {
		//creates test run to be inserted further into a test plan entry
		//for now test plan is created from only one test suite, so there is no need to use suite variable
		//however test plan can consist of many suites
		Run currentRun = new Run();
		currentRun.setIncludeAll(false);
		currentRun.setSuiteId(FULL_TEST_SUITE_ID);
		currentRun.setName("Configuration for: \'" + configItem.getName() + "\'");
		currentRun.setDescription("Configuration for: \'" + configItem.getName() + "\'");
		currentRun.setConfig(configItem.getName());
		List tempList = new ArrayList<Integer>();
		tempList.add(configItem.getId());
		currentRun.setConfigIds((ArrayList<Integer>) tempList);

		//TODO: in future, create a local copy of heavyweight object here
		ValuesMatrix localMatrix = new ValuesMatrix(railsInstances.getValuesMatrix());
		//LinkedHashMap<String, Integer> dataCaseIds = railsInstances.getValuesMatrix().getDataCaseIds();
		ArrayList<Integer> caseIds = new ArrayList<>();
		
		//set test case ids
		//loop the whole ValuesMatrix and see if there any data for
		//current row (test instance name) and current column (configuration name)
		//if so, add the id of test case, it is already mapped in ValuesMatrix.dataCaseIds
		for(String currentRow : localMatrix.getDataRows()) {
			//check if a row is still present in the table of values
			//TODO: throw an exception here
			if(!localMatrix.getIsIncluded().containsKey(currentRow)) {
				System.out.println("ERROR: no such record");
				return null;
			}
			HashMap<String, ?> currentRowValues = (HashMap<String, ?>) localMatrix.getIsIncluded().get(currentRow);
			String cellValue = (String) currentRowValues.get(configItem.getName());
			if(cellValue!=null) {
				//TODO: there can be a plenty of values that should be ignored
				//better to create a collection of values that should be treated as empty
				//and check for them here
				if(!cellValue.equals("null") || !cellValue.equals("")) {
	  				  caseIds.add(localMatrix.getDataCaseIds().get(currentRow));
				}
			}
		}
		currentRun.setCaseIds(caseIds);
		return currentRun;
	
	}
	
	

	private static void trustEveryone() {
		try {
			HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}
			});
			SSLContext context = SSLContext.getInstance("TLS");
			context.init(null, new X509TrustManager[] { new X509TrustManager() {
				public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				}

				public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				}

				public X509Certificate[] getAcceptedIssuers() {
					return new X509Certificate[0];
				}
			} }, new SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(context.getSocketFactory());
		} catch (Exception e) { // should never happen
			e.printStackTrace();
		}
	}

}
