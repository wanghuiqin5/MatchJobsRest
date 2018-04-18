package util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RestfulRequest {
    public static final String GET = "GET";

    
    private String context="";
    private String httpMethod = GET;
    private String url="";
    
    private String returnData;
    int responseCode;
    String responseMessage; 
    
    
    public RestfulRequest(String url) {
        this.httpMethod = RestfulRequest.GET;
        this.url = url;
    }
    
    
    public RestfulRequest(String url, String context, String httpMethod) {
        this.context = context;
        this.httpMethod = httpMethod;
        this.url = url;
    }
    
    public void sendRequest() {
        HttpURLConnection conn = null;
        try {
            URL urlObj = new URL(url + context);
            conn = (HttpURLConnection) urlObj.openConnection();

            conn.setRequestMethod(httpMethod);
            conn.setRequestProperty("Accept", "application/json");
            
            conn.setConnectTimeout(20000);
            conn.connect();

            // process response
             responseCode = conn.getResponseCode();
             responseMessage = conn.getResponseMessage();

            // failure path
            if (responseCode == -1) {
                responseCode = 404;
                responseMessage = "Not connected error.";
            }
            // SUCCESS path
            else if (responseCode == 200) {
                returnData = extractData(conn.getInputStream());
                System.out.println("returnData: "+returnData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           
            if (conn != null)
                conn.disconnect();
        }
    }
    
    private String extractData(InputStream inputStream) throws IOException {
        StringBuffer sbuf = new StringBuffer();
        BufferedReader br = new BufferedReader(new InputStreamReader((inputStream)));

        String line = br.readLine();
        while (line != null) {
            sbuf.append(line);
            sbuf.append('\n');
            line = br.readLine();
        }

        return sbuf.toString();
    }


    public String getReturnData() {
        return returnData;
    }


    public int getResponseCode() {
        return responseCode;
    }


    public String getResponseMessage() {
        return responseMessage;
    }

    public boolean isSuccess() {
        if(responseCode==200)
            return true;
        return false;
    }


    @Override
    public String toString() {
        return "RestfulRequest [context=" + context + ", httpMethod=" + httpMethod + ", url=" + url + ", returnData="
                + returnData + ", responseCode=" + responseCode + ", responseMessage=" + responseMessage + "]";
    }


}
