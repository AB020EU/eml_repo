package co.absa.eml.soupinteractionpoint;

import co.absa.eml.applicationcapture.ApplicationCapture;
import co.absa.eml.restinteractionpoints.RestInteractionPoints;
import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.net.ssl.*;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import static io.restassured.RestAssured.given;
@Component
public class SOAPInteractionPoint {
    @Autowired
    RestInteractionPoints restInteractionPoints;
    public String send(String abNumber,String wsURL, String body, String soapAction) throws Exception {

         //Create a trust manager that does not validate certificate chains
		TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return null;
			}
			public void checkClientTrusted(X509Certificate[] certs, String authType) {
			}
			public void checkServerTrusted(X509Certificate[] certs, String authType) {
			}
		}
		};

		// Install the all-trusting trust manager
		SSLContext sc = SSLContext.getInstance("SSL");
		sc.init(null, trustAllCerts, new java.security.SecureRandom());
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

		// Create all-trusting host name verifier
		HostnameVerifier allHostsValid = new HostnameVerifier() {
			public boolean verify(String hostname, SSLSession session) {
				return true;
			}
		};

		// Install the all-trusting host verifier
		HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);



        URL url = new URL(wsURL);
        URLConnection connection = url.openConnection();
        HttpsURLConnection httpURLConnection = (HttpsURLConnection)connection;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        byte[] buffer = new byte[body.length()];
        buffer = body.getBytes();
        byteArrayOutputStream.write(buffer);
        byte[] byteArray = byteArrayOutputStream.toByteArray();

        httpURLConnection.setRequestProperty("Content-Length", String.valueOf(byteArray.length));
        httpURLConnection.setRequestProperty("Content-Type", "text/xml;charset=utf-8");
        httpURLConnection.setRequestProperty("SOAPAction",soapAction);
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setDoInput(true);
        OutputStream outputStream = httpURLConnection.getOutputStream();
        outputStream.write(byteArray);
        outputStream.close();

        InputStreamReader inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream());
        BufferedReader in = new BufferedReader(inputStreamReader);

        String responseString = "";
        String response = "";
        while ((responseString = in.readLine()) != null) {
            response += responseString;
        }
        String applicationNumber=path(response,"submitApplicationResponse","applicationNo");
        ApplicationCapture applicationCapture = new ApplicationCapture();
        applicationCapture.setAbNumber(abNumber);
        applicationCapture.setOwner(abNumber);
        applicationCapture.setApplicationNumber(applicationNumber);
        Gson g = new Gson();
        String str = g.toJson(applicationCapture);
        Response response0 = restInteractionPoints.post("/captured/application",str);
        return response;

    }

    private String path(String response, String node, String tagName) throws Exception {
        Document doc = DocumentBuilderFactory.newInstance()
                .newDocumentBuilder()
                .parse(new InputSource(new StringReader(response)));

        NodeList errNodes = doc.getElementsByTagName(node);
        if (errNodes.getLength() > 0) {
            Element err = (Element) errNodes.item(0);
            return err.getElementsByTagName(tagName)
                    .item(0)
                    .getTextContent();
        } else {
            throw new Exception(tagName + " NOT FOUND.");
        }
    }

    public Response send(String wsURL, String body) throws Exception {
        return given()
                .relaxedHTTPSValidation()
                .contentType(ContentType.XML)
                .request().body(body).post(wsURL);
    }

    public Response sendWithContentType(String wsURL, String body, String proxy, int port, String contentType) throws Exception {
        return given()
                .relaxedHTTPSValidation().proxy(proxy,port)
                .contentType(contentType)
                .request().body(body).post(wsURL);
    }
}