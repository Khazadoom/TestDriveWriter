package DriveWriter.DriveWriter;


import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpResponse;
import com.google.api.services.drive.Drive;


import com.google.api.services.drive.DriveScopes;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;


import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.auth.oauth.*;
import com.google.api.client.auth.oauth2.*;
import com.google.api.client.googleapis.auth.oauth2.*;
import com.google.api.services.oauth2.Oauth2;
//import com.google.api.client.extensions.servlet.auth.oauth2.*;
////import com.google.api.client.extensions.appengine.auth.oauth2.*;
import com.google.api.services.oauth2.model.*;

import java.util.*;
import java.awt.Desktop;
import java.io.*;

import com.google.api.services.drive.model.File;

import java.lang.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import com.google.api.client.auth.openidconnect.*;
import com.google.api.client.googleapis.services.*;

public class TestAPI{
	
	static String CLIENT_ID = "1008882763671-44hlr0138kcqmvebcrfole9srgcjvmvo.apps.googleusercontent.com";
	static String CLIENT_SECRET = "ps-5vzn4_rote-yU7QiYEcPA";
	String REDIRECT_URI = "https://localhost";
	String accessToken;
	String refreshToken;
	
	public void main() throws IOException, URISyntaxException{
	
		GoogleCredential credentials = getStoredCredentials();
		
		if (credentials==null){
			getAuthCode();
		}
		
		writeContents(downloadFromDrive());
	}
	
	
	private void writeContents(String Contents) throws IOException{
		BufferedWriter writer = null;
		String timeLog = "Contents.txt";
        String homefolder = System.getProperty("user.home");
        java.io.File logFile = new java.io.File(homefolder,timeLog);
        writer = new BufferedWriter(new FileWriter(logFile,true));
        writer.write(Contents);
        writer.close();
		
	}
	
	private void getAuthCode() throws IOException, URISyntaxException{
		HttpTransport httpTransport = new NetHttpTransport();
	    JsonFactory jsonFactory = new JacksonFactory();
		
		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
				httpTransport, jsonFactory,CLIENT_ID ,CLIENT_SECRET, Arrays.asList(DriveScopes.DRIVE))
				.setAccessType("offline")
				.setApprovalPrompt("auto").build();
				String url = flow.newAuthorizationUrl().setRedirectUri(REDIRECT_URI).build();
		
				System.out.println("Enter authorization code:");
			    Desktop.getDesktop().browse(new URI(url));
			    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			    String code = br.readLine();
				
				GoogleTokenResponse response = flow.newTokenRequest(code).setRedirectUri(REDIRECT_URI).execute();
	            GoogleCredential credential = new GoogleCredential.Builder().setTransport(httpTransport)
	                .setJsonFactory(jsonFactory)
	                .setClientSecrets(CLIENT_ID, CLIENT_SECRET)
	                .build()
	                .setFromTokenResponse(response);
	 accessToken = credential.getAccessToken();
	 refreshToken = credential.getRefreshToken();
	 StoreCredentials();

	GoogleCredential credential1 = new GoogleCredential.Builder().setJsonFactory(jsonFactory)
	.setTransport(httpTransport).setClientSecrets(CLIENT_ID, CLIENT_SECRET).build();
	credential1.setAccessToken(accessToken);
	credential1.setRefreshToken(refreshToken);
	}
	
	
	
	
	private void StoreCredentials() throws IOException{
		BufferedWriter writer = null;
		String timeLog = "TestFileTokens.txt";
        String homefolder = System.getProperty("user.dir");
        java.io.File logFile = new java.io.File(homefolder,timeLog);
        writer = new BufferedWriter(new FileWriter(logFile,true));
        writer.write(accessToken + "," + refreshToken);
        writer.close();
        
	}
	
	private static GoogleCredential getStoredCredentials() throws IOException{
		
		System.out.println(System.getProperty("user.dir"));
		
		String localAccess;
		String localRefresh;
		int comma;
		HttpTransport httpTransport = new NetHttpTransport();
	    JsonFactory jsonFactory = new JacksonFactory();
		String LineRead;
		BufferedReader reader = null;
		reader = new BufferedReader(new FileReader("TestFileTokens.txt"));
		LineRead = reader.readLine();
		reader.close();
		comma = LineRead.indexOf(',');
		localAccess = LineRead.substring(0, comma);
		localRefresh = LineRead.substring(comma);
		GoogleCredential storedCredentials = new GoogleCredential.Builder().setJsonFactory(jsonFactory)
				.setTransport(httpTransport).setClientSecrets(CLIENT_ID,CLIENT_SECRET).build();
		storedCredentials.setAccessToken(localAccess);
		storedCredentials.setRefreshToken(localRefresh);
		return storedCredentials;
	}
	
	static Drive buildService(GoogleCredential credentials) {
	    HttpTransport httpTransport = new NetHttpTransport();
	    JacksonFactory jsonFactory = new JacksonFactory();

	    return new Drive.Builder(httpTransport, jsonFactory, credentials)
	        .build();
	  }
	
public  String downloadFromDrive() throws IOException{
	
		
		Drive service = buildService(getStoredCredentials());
        
       	
		
		String fileContents = null;
		File Drivefile = service.files().get("1T_rXb_2CcB4YCA9NS9PyOuboSNcw8dTdnklUYB3O2Fg").execute();
		//Drivefile.setId("1T_rXb_2CcB4YCA9NS9PyOuboSNcw8dTdnklUYB3O2Fg");
		
		String downloadUrl = Drivefile.getExportLinks().get("text/csv");
		System.out.println(downloadUrl);
		fileContents = convertStreamToString(downloadFile(service,Drivefile));
		return fileContents;
		
	}
	
	static String convertStreamToString(java.io.InputStream is) {
	    java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");

	    return s.hasNext() ? s.next() : "";
	}
	
	private static void printFile(Drive service, String fileId) {

	    try {
	      File file = service.files().get(fileId).execute();

	      System.out.println("Title: " + file.getTitle());
	      System.out.println("Description: " + file.getDescription());
	      System.out.println("MIME type: " + file.getMimeType());
	    } catch (IOException e) {
	      System.out.println("An error occured: " + e);
	    }
	  }

	  /**
	   * Download a file's content.
	   *
	   * @param service Drive API service instance.
	   * @param file Drive File instance.
	   * @return InputStream containing the file's content if successful,
	   *         {@code null} otherwise.
	   */
	  private static InputStream downloadFile(Drive service, File file) {
	    if (file.getDownloadUrl() != null && file.getDownloadUrl().length() > 0) {
	      try {
	        HttpResponse resp =
	            service.getRequestFactory().buildGetRequest(new GenericUrl("1T_rXb_2CcB4YCA9NS9PyOuboSNcw8dTdnklUYB3O2Fg"))
	                .execute();
	        return resp.getContent();
	      } catch (IOException e) {
	        // An error occurred.
	        e.printStackTrace();
	        return null;
	      }
	    } else {
	      // The file doesn't have any content stored on Drive.
	      return null;
	    }
	  }
	
	
	
	
	
	}

