import com.gurock.testrail.APIClient;
import com.gurock.testrail.APIException;
import java.util.Map;
import java.util.HashMap;
import org.json.simple.JSONObject;
public class FetchData {
	public static final String APIKEY = "bb/1CdRKLj2cr6xOwoOO-JrBD6V49zhyK9PUCPz3U"; 

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Started");
		
		APIClient client = new APIClient("https://testrail.ee.playtech.corp/testrail/");
		client.setUser("yuriily");
		client.setPassword(APIKEY);
		

	}

}
