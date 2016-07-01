import com.google.gson.Gson;
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
import java.util.LinkedHashSet;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.experimental.theories.Theories;

public class FetchData {
	public static final String APIKEY = "BtiEB.ufbdTcx.yGGVb/-sXfC1RvxnCwcaeecZQby";
	public static final int PROJECT_ID = 31;
	public static final int FULL_TEST_SUITE_ID = 301;
	public static final int CONFIG_ID = 124;
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
		client.setUser("yuriily");
		client.setPassword("Artalaire543");


		System.out.println("Getting projects...");
		JSONArray jsonProjects = (JSONArray) client.sendGet("get_projects");
		Project projects[] = gson.fromJson(jsonProjects.toJSONString(), Project[].class);
		Project currentProject = null;
		for(int iter = 0; iter<projects.length; iter++)
			if(PROJECT_ID==projects[iter].getId())
				currentProject = projects[iter];
		
		railsInstances = new SelectedRailsInstances(currentProject);

		System.out.println("Getting suites...");
		JSONArray jsonSuites = (JSONArray) client.sendGet("get_suites/" + PROJECT_ID);
		Suite suites[] = gson.fromJson(jsonSuites.toJSONString(), Suite[].class);
		JSONObject jsonSuite = (JSONObject) client.sendGet("get_suite/"+FULL_TEST_SUITE_ID);
		railsInstances.setSuite((Suite)gson.fromJson(jsonSuite.toJSONString(), Suite.class));

		System.out.println("Getting cases...");
		JSONArray jsonCases = (JSONArray) client
				.sendGet("get_cases/" + PROJECT_ID + "/&suite_id=" + FULL_TEST_SUITE_ID);
		Case cases[] = gson.fromJson(jsonCases.toJSONString(), Case[].class);
		
		LinkedHashSet<String> dataRows = new LinkedHashSet<String>();
		//loop here to check for nulls
		for(int iter=0; iter<cases.length; iter++) {
			if(!cases[iter].getTitle().equals(""))
				dataRows.add(cases[iter].getTitle());
		}

		System.out.println("Getting configurations...");
		JSONArray jsonConfigs = (JSONArray) client.sendGet("get_configs/" + PROJECT_ID);
		Configuration configs[] = gson.fromJson(jsonConfigs.toJSONString(), Configuration[].class);
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

	}
	
	private String populateTestPlan() {
		//fills in current test plan with data stores in values matrix
		StringBuilder results = new StringBuilder();
		Boolean isFailed = false;
		
		//check if everything is set up: 
		//current plan, suite, configuration, and file with data
		if(null == railsInstances.getValuesMatrix() || railsInstances.getValuesMatrix().isEmpty()) {
			results.append("ERROR: there is no data in the file or the file is absent.\n");
			isFailed=true;
		}
		if(null==railsInstances.getPlan()) {
			results.append("Test plan is not selected.\n");
			isFailed=true;
		}
		if(null==railsInstances.getSuite()) {
			results.append("Test suite is not selected.\n");
			isFailed=true;
		}
		if(null==railsInstances.getConfiguration()) {
			results.append("Test configuration is not selected.\n");
			isFailed=true;
		}
		
		if(isFailed)
			return results.toString();
		
		//TODO main part
		return results.toString();
		
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
