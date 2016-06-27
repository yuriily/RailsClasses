import com.google.gson.Gson;
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
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class FetchData {
	public static final String APIKEY = "BtiEB.ufbdTcx.yGGVb/-sXfC1RvxnCwcaeecZQby"; 
	public static final int PROJECT_ID = 31;
	public static final int FULL_TEST_SUITE_ID = 301;
	public static final int CONFIG_ID = 124;
	


	public static void main(String[] args) throws MalformedURLException, IOException, APIException {
		// TODO Auto-generated method stub
		System.out.println("Started");
		
		trustEveryone();
		
		APIClient client = new APIClient("https://testrail.ee.playtech.corp/testrail/");
		client.setUser("yuriily");
		client.setPassword("Artalaire543");
		
		Gson gson = new Gson();

		System.out.println("Getting projects...");
		JSONArray jsonProjects = (JSONArray) client.sendGet("get_projects");
		Project projects[] = gson.fromJson(jsonProjects.toJSONString(), Project[].class);
		
		System.out.println("Getting suites...");
		JSONArray jsonSuites = (JSONArray) client.sendGet("get_suites/"+PROJECT_ID);
		Suite suites[] = gson.fromJson(jsonSuites.toJSONString(), Suite[].class);
		
		System.out.println("Getting cases...");
		JSONArray jsonCases = (JSONArray) client.sendGet("get_cases/"+PROJECT_ID+ "/&suite_id=" + FULL_TEST_SUITE_ID);
		Case cases[] = gson.fromJson(jsonCases.toJSONString(), Case[].class);
		
		System.out.println("Getting configurations...");
		JSONArray jsonConfigs = (JSONArray) client.sendGet("get_configs/"+PROJECT_ID);
		Configuration configs[] = gson.fromJson(jsonConfigs.toJSONString(), Configuration[].class);
		ArrayList<ConfigurationItem> configItems=null;
		for(int iter=0;iter<configs.length;iter++) {
			System.out.println(configs[iter].getId()+":"+configs[iter].getName());
			if(CONFIG_ID == configs[iter].getId())
				configItems= new ArrayList<ConfigurationItem>(Arrays.asList(configs[iter].getConfigurationItems()));
		}
		
		System.out.println("Configuration items:");
		for(int iter=0;iter<configItems.size();iter++)
			System.out.println(configItems.get(iter).getId()+":"+configItems.get(iter).getName());
		
		

	}
	
	private static void trustEveryone() {
        try {
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, new X509TrustManager[]{new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            }}, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(context.getSocketFactory());
        } catch (Exception e) { // should never happen
            e.printStackTrace();
        }
    }

}
