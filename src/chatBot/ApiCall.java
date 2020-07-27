package chatBot;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.DataOutputStream;
import java.net.*;
import java.util.*;
import com.google.gson.*;

public class ApiCall {

	private static HttpURLConnection con;
	
	//Main method
	public static void main(String[] args) 
	{
		try 
		{
			Scanner s = new Scanner(System.in);
			System.out.println("Enter the city: ");
			
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		

	}
	
	/*
	 * Function to call weather API. 
	 * Takes city as parameter.
	 * Returns the current temperature of the specified city 
	 * 
	 */
	public static String callWeatherAPI(String city) throws IOException
	{
		
		String line; //Variable to store the Json response	
		String apiKey = "68bfd39e34ab806853167bb198a0352e"; //API key of the website whose API is being consumed
		String endPoint = "http://api.openweathermap.org/data/2.5/weather";	//API endpoint
		endPoint += "?q=" + city; //Adding city as parameter to the URL
		endPoint += "&APPID=" + apiKey; //Adding api key as parameter to the URL
		
		URL url = new URL(endPoint);	//Creating new URL Object
		con = (HttpURLConnection) url.openConnection(); //Opening connection to the URL
		
		
		//Request setup
		con.setRequestMethod("GET");	//setting request method as GET
		
		int status = con.getResponseCode();		//getting response code
		System.out.println("Status " + status);	//Displaying the status
		
		if(status == 200)	//Executing body if the response code is 200
		{
			BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));	// Reading the output from the server
			StringBuffer response = new StringBuffer(); //Creating string buffer object
			while((line = reader.readLine()) != null) //Loop runs until the entire JSON data is read in
			{
				response.append(line); //append json data to the line variable
			}
			
			reader.close();	//Close the buffer reader
			System.out.println(response.toString()); //Print json response

			String temp = getTemperature(response.toString());	//Calling the getTemperature method and storing the temperature in a temp variable
			System.out.println(temp);
			
			return temp; //Returning temperature
			
		}
		
		return null;
		
	}
	
	/*
	 * Function to call weather API using the ZipCode. 
	 * Takes Zipcode as parameter.
	 * Returns the current temperature of the specified zipcode
	 * 
	 */
	public static String callWeatherAPIZip(String zipCode) throws IOException
	{
		
		String line;	//Variable to store the Json response	
		StringBuffer responseContent;
		String apiKey = "68bfd39e34ab806853167bb198a0352e";	//API key of the website whose API is being consumed
		String endPoint = "http://api.openweathermap.org/data/2.5/weather";	//API endpoint
		endPoint += "?zip=" + zipCode;	//Adding zipcode as parameter to the URL
		endPoint += "&APPID=" + apiKey;	//Adding api key as parameter to the URL
		
		URL url = new URL(endPoint);	//Creating new URL Object
		con = (HttpURLConnection) url.openConnection();	//Opening connection to the URL
		
		
		//Request setup
		con.setRequestMethod("GET");	//setting request method as GET
		con.setConnectTimeout(5000);
		con.setReadTimeout(5000);

		int status = con.getResponseCode();	//getting response code
		System.out.println("Status " + status);	
		
		if(status == 200)	//Executing body if the response code is 200
		{
			BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));	// Reading the output from the server
			StringBuffer response = new StringBuffer();		//Creating string buffer object
			while((line = reader.readLine()) != null)		//Loop runs until the entire JSON data is read in
			{
				response.append(line);	//append json data to the line variable
			}
			
			reader.close();			//Closing the reader
			System.out.println(response.toString());

			String temp = getTemperature(response.toString());	//Calling the getTemperature method and storing the temperature in a temp variable
			System.out.println(temp);	
			
			return temp;		//Returning temperature
			
		}
		
		return null;	
		
	}
	
	/*
	 * Function to call weather API to retrieve minimum temperature using the ZipCode. 
	 * Takes Zipcode as parameter.
	 * Returns the current minimum temperature of the specified zipcode
	 * 
	 */
	public static String callWeatherAPIZipLow(String zipCode) throws IOException
	{
		
		String line;	//Variable to store the Json response	
		StringBuffer responseContent;
		String apiKey = "68bfd39e34ab806853167bb198a0352e";		//API key of the website whose API is being consumed
		String endPoint = "http://api.openweathermap.org/data/2.5/weather";	//API endpoint
		endPoint += "?zip=" + zipCode;		//Adding zipcode as parameter to the URL
		endPoint += "&APPID=" + apiKey;		//Adding api key as parameter to the URL
		
		URL url = new URL(endPoint);		//Creating new URL Object
		con = (HttpURLConnection) url.openConnection();		//Opening connection to the URL	
		
		
		//Request setup
		con.setRequestMethod("GET");	
		con.setConnectTimeout(5000);
		con.setReadTimeout(5000);
		
		int status = con.getResponseCode();	//Closing the reader
		System.out.println("Status " + status);
		
		if(status == 200)	//Executing body if the response code is 200
		{
			BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));	// Reading the output from the server
			StringBuffer response = new StringBuffer();	//Creating string buffer object
			while((line = reader.readLine()) != null)	//Loop runs until the entire JSON data is read in
			{
				response.append(line);		//append json data to the line variable
			}
			
			reader.close();
			System.out.println(response.toString());
			
			//JSONObject myResponse = new JSONObject(response.toString());
			//JsonObject myResponse = response.toStirng()
			
			String temp_min = getTemp_Min(response.toString());	//Calling the getTemp_Min method and storing the temperature in a temp variable
			System.out.println(temp_min);
			
			return temp_min;	//Returning temperature
			
		}
		
		return null;
		
	}
	
	/*
	 * Function to call weather API to retrieve maximum temperature using the ZipCode. 
	 * Takes Zipcode as parameter.
	 * Returns the current maximum temperature of the specified zipcode
	 * 
	 */
	public static String callWeatherAPIZipMax(String zipCode) throws IOException
	{
			
		String line;	//Variable to store the Json response	
		StringBuffer responseContent;
		String apiKey = "68bfd39e34ab806853167bb198a0352e";		//API key of the website whose API is being consumed
		String endPoint = "http://api.openweathermap.org/data/2.5/weather";	//API endpoint
		endPoint += "?zip=" + zipCode;	//Adding zipcode as parameter to the URL
		endPoint += "&APPID=" + apiKey;	//Adding api key as parameter to the URL
			
		URL url = new URL(endPoint);	//Creating new URL Object
		con = (HttpURLConnection) url.openConnection(); //opening url connection
		
		
		//Request setup
		con.setRequestMethod("GET");	//setting request method as GET
		con.setConnectTimeout(5000);
		con.setReadTimeout(5000);

		
		int status = con.getResponseCode();	//Closing the reader
		System.out.println("Status " + status);
		
		if(status == 200)	//Executing body if the response code is 200
		{	
			BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));	// Reading the output from the server
			StringBuffer response = new StringBuffer();		//Creating string buffer object
			while((line = reader.readLine()) != null)		//Loop runs until the entire JSON data is read in
			{	
				response.append(line);		//append json data to the line variable
			}
			
			reader.close();
			System.out.println(response.toString());
			
			//JSONObject myResponse = new JSONObject(response.toString());
			//JsonObject myResponse = response.toStirng()
			
			String temp_max = getTemp_Max(response.toString());		//Calling the getTemp_Max method and storing the temperature in a temp_max variable
			System.out.println(temp_max);
			
			return temp_max; //Returning maximum temperature
			
		}
		
		return null;
		
	}
	
	/*
	 * Function to call weather API to retrieve minimum temperature using the city as parameter. 
	 * Takes city as parameter.
	 * Returns the current minimum temperature of the specified city
	 * 
	 */
	public static String callWeatherAPICityLow(String city) throws IOException
	{
		
		String line;
		StringBuffer responseContent;
		String apiKey = "68bfd39e34ab806853167bb198a0352e";
		String endPoint = "http://api.openweathermap.org/data/2.5/weather";
		endPoint += "?q=" + city;
		endPoint += "&APPID=" + apiKey;
		
		URL url = new URL(endPoint);
		con = (HttpURLConnection) url.openConnection();
		
		
		//Request setup
		con.setRequestMethod("GET");
		con.setConnectTimeout(5000);
		con.setReadTimeout(5000);

		int status = con.getResponseCode();
		System.out.println("Status " + status);
		
		if(status == 200)
		{
			BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			StringBuffer response = new StringBuffer();
			while((line = reader.readLine()) != null)
			{
				response.append(line);
			}
			
			reader.close();
			System.out.println(response.toString());
			
			//JSONObject myResponse = new JSONObject(response.toString());
			//JsonObject myResponse = response.toStirng()
			
			String temp_min = getTemp_Min(response.toString());
			System.out.println(temp_min);
			
			return temp_min;
			
		}
		
		return null;
		
	}
	
	/*
	 * Function to call weather API to retrieve maximum temperature using the city as parameter. 
	 * Takes city as parameter.
	 * Returns the current minimum temperature of the specified city
	 * 
	 */
	public static String callWeatherAPICityMax(String city) throws IOException
	{
		
		String line;
		StringBuffer responseContent;
		String apiKey = "68bfd39e34ab806853167bb198a0352e";
		String endPoint = "http://api.openweathermap.org/data/2.5/weather";
		endPoint += "?q=" + city;
		endPoint += "&APPID=" + apiKey;
		
		URL url = new URL(endPoint);
		con = (HttpURLConnection) url.openConnection();
		
		
		//Request setup
		con.setRequestMethod("GET");
		con.setConnectTimeout(5000);
		con.setReadTimeout(5000);

		int status = con.getResponseCode();
		System.out.println("Status " + status);
		
		if(status == 200)
		{
			BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			StringBuffer response = new StringBuffer();
			while((line = reader.readLine()) != null)
			{
				response.append(line);
			}
			
			reader.close();
			System.out.println(response.toString());
			
			//JSONObject myResponse = new JSONObject(response.toString());
			//JsonObject myResponse = response.toStirng()
			
			String temp_max = getTemp_Max(response.toString());
			System.out.println(temp_max);
			
			return temp_max;
			
		}
		
		return null;
		
	}
	
	/*
	 * Function to call weather API to retrieve air quality using the city as parameter. 
	 * Takes city as parameter.
	 * Returns the current minimum temperature of the specified city
	 * 
	 */
	public static String callAirQualityAPI_AQ(String city) throws IOException
	{
		//Same process as the weather api call to retrieve the longitude and latitude
		String line;
		StringBuffer responseContent;
		String apiKey = "68bfd39e34ab806853167bb198a0352e";
		String endPoint = "http://api.openweathermap.org/data/2.5/weather";
		endPoint += "?q=" + city;
		endPoint += "&APPID=" + apiKey;
		
		URL url = new URL(endPoint);
		con = (HttpURLConnection) url.openConnection();
		
		//Request setup
		con.setRequestMethod("GET");
		con.setConnectTimeout(5000);
		con.setReadTimeout(5000);
		
		int status = con.getResponseCode();
		System.out.println(status);
		
		String lat;
		String lon;
		if(status == 200)
		{
			BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			StringBuffer response = new StringBuffer();
			while((line = reader.readLine()) != null)
			{
				response.append(line);
			}
			
			reader.close();
			
			lat = getLat(response.toString()); //Retrieves the latitude 
			lon = getLon(response.toString());	//Retrieves the longitude
			
			System.out.print("lat: " + lat);
			System.out.print("lon: " + lon);
			
			//-----------------------------------
			String inline;	
			String apiKey2 = "1a4ba6466f694847b6e65c6972d15e26";
			String endPoint2 = "https://api.breezometer.com/air-quality/v2/current-conditions";
			endPoint2 += "?lat=" + lat;	//Adds the latitude as parameter
			endPoint2 += "&lon=" + lon;	//Adds the longitude as parameter
			endPoint2 += "&key=" + apiKey2;	//Adds the api key as parameter
			endPoint2 += "&features=" + "breezometer_aqi,local_aqi,health_recommendations,sources_and_effects,pollutants_concentrations,pollutants_aqi_information"; //Adds the features as parameter
			
			URL url2 = new URL(endPoint2);	//Creates URL object
			con = (HttpURLConnection) url2.openConnection();	//Opens connection
			
			
			//Request setup
			con.setRequestMethod("GET");
			con.setConnectTimeout(5000);
			con.setReadTimeout(5000);
			
			int status2 = con.getResponseCode();
			System.out.println(status2);
			
			if(status2 == 200)
			{
				BufferedReader reader2 = new BufferedReader(new InputStreamReader(con.getInputStream()));
				StringBuffer response2 = new StringBuffer();
				
				while((inline = reader2.readLine()) != null)
				{
					response2.append(inline);
				}
				
				reader2.close();
				
				System.out.println(response2.toString());
				
				//Retrieves air quality
				String airQuality = getAirQuality(response2.toString());
				System.out.println(airQuality);
				
				//Retrieves pollutant information
				String pollutant = getAirQualityPollutant(response2.toString());
				System.out.println(pollutant);
				
				
				//Retrieves health recommendation information
				String health = getAirQualityHealth(response2.toString());
				System.out.println(health);
				System.out.println(response2.toString());
				
				return airQuality;	//Returns the air quality
			
			}

		}
		
		return null;
	}
	
	/*
	 * Function to call AirQuality API to retrieve pollutant information using the city as parameter. 
	 * Takes city as parameter.
	 * Returns the current minimum temperature of the specified city
	 * 
	 */
	public static String callAirQualityAPI_pollutant(String city) throws IOException
	{
		String line;
		StringBuffer responseContent;
		String apiKey = "68bfd39e34ab806853167bb198a0352e";
		String endPoint = "http://api.openweathermap.org/data/2.5/weather";
		endPoint += "?q=" + city;
		endPoint += "&APPID=" + apiKey;
		
		URL url = new URL(endPoint);
		con = (HttpURLConnection) url.openConnection();
		
		//Request setup
		con.setRequestMethod("GET");
		con.setConnectTimeout(5000);
		con.setReadTimeout(5000);
		
		int status = con.getResponseCode();
		System.out.println(status);
		
		String lat;
		String lon;
		if(status == 200)
		{
			BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			StringBuffer response = new StringBuffer();
			while((line = reader.readLine()) != null)
			{
				response.append(line);
			}
			
			reader.close();
			
			//JSONObject myResponse = new JSONObject(response.toString());
			//JsonObject myResponse = response.toStirng()
			
			lat = getLat(response.toString());
			lon = getLon(response.toString());
			
			System.out.print("lat: " + lat);
			System.out.print("lon: " + lon);
			
			//-----------------------------------
			String inline;
			String apiKey2 = "1a4ba6466f694847b6e65c6972d15e26";
			String endPoint2 = "https://api.breezometer.com/air-quality/v2/current-conditions";
			endPoint2 += "?lat=" + lat;
			endPoint2 += "&lon=" + lon;
			endPoint2 += "&key=" + apiKey2;
			endPoint2 += "&features=" + "breezometer_aqi,local_aqi,health_recommendations,sources_and_effects,pollutants_concentrations,pollutants_aqi_information";
			
			
			URL url2 = new URL(endPoint2);
			con = (HttpURLConnection) url2.openConnection();
			
			
			//Request setup
			con.setRequestMethod("GET");
			con.setConnectTimeout(5000);
			con.setReadTimeout(5000);
			
			int status2 = con.getResponseCode();
			System.out.println(status2);
			
			if(status2 == 200)
			{
				BufferedReader reader2 = new BufferedReader(new InputStreamReader(con.getInputStream()));
				StringBuffer response2 = new StringBuffer();
				
				while((inline = reader2.readLine()) != null)
				{
					response2.append(inline);
				}
				
				reader2.close();
				
				System.out.println(response2.toString());

				
				String pollutant = getAirQualityPollutant(response2.toString());
				System.out.println(pollutant);
				
				return pollutant;
			
			}

		}
		
		return null;
	}
	
	/*
	 * Function to call AirQuality API to retrieve health recommendation information using the city as parameter. 
	 * Takes city as parameter.
	 * Returns the current minimum temperature of the specified city
	 * 
	 */
	public static String callAirQualityAPI_health(String city) throws IOException
	{
		String line;
		StringBuffer responseContent;
		String apiKey = "68bfd39e34ab806853167bb198a0352e";
		String endPoint = "http://api.openweathermap.org/data/2.5/weather";
		endPoint += "?q=" + city;
		endPoint += "&APPID=" + apiKey;
		
		URL url = new URL(endPoint);
		con = (HttpURLConnection) url.openConnection();
		
		//Request setup
		con.setRequestMethod("GET");
		con.setConnectTimeout(5000);
		con.setReadTimeout(5000);
		
		int status = con.getResponseCode();
		System.out.println(status);
		
		String lat;
		String lon;
		if(status == 200)
		{
			BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			StringBuffer response = new StringBuffer();
			while((line = reader.readLine()) != null)
			{
				response.append(line);
			}
			
			reader.close();
			
			//JSONObject myResponse = new JSONObject(response.toString());
			//JsonObject myResponse = response.toStirng()
			
			lat = getLat(response.toString());
			lon = getLon(response.toString());
			
			System.out.print("lat: " + lat);
			System.out.print("lon: " + lon);
			
			//-----------------------------------
			String inline;
			String apiKey2 = "1a4ba6466f694847b6e65c6972d15e26";
			String endPoint2 = "https://api.breezometer.com/air-quality/v2/current-conditions";
			endPoint2 += "?lat=" + lat;
			endPoint2 += "&lon=" + lon;
			endPoint2 += "&key=" + apiKey2;
			endPoint2 += "&features=" + "breezometer_aqi,local_aqi,health_recommendations,sources_and_effects,pollutants_concentrations,pollutants_aqi_information";
			
			
			URL url2 = new URL(endPoint2);
			con = (HttpURLConnection) url2.openConnection();
			
			
			//Request setup
			con.setRequestMethod("GET");
			con.setConnectTimeout(5000);
			con.setReadTimeout(5000);
			
			int status2 = con.getResponseCode();
			System.out.println(status2);
			
			if(status2 == 200)
			{
				BufferedReader reader2 = new BufferedReader(new InputStreamReader(con.getInputStream()));
				StringBuffer response2 = new StringBuffer();
				
				while((inline = reader2.readLine()) != null)
				{
					response2.append(inline);
				}
				
				reader2.close();
				
				System.out.println(response2.toString());

				
				String health = getAirQualityHealth(response2.toString());
				System.out.println(health);
				
				return health;
			
			}

		}
		
		return null;
	}
	
	

	/*
	 * Function to retrieve air quality information using the json response as parameter. 
	 * Takes json response as parameter.
	 * Returns the air quality of the specified city
	 */
	public static String getAirQuality(String response)
	{	
		 //Parse the JSON data present in the string format
		JsonParser parse = new JsonParser();
		
		//Type cast the parsed json data in json object
		JsonObject jobj = (JsonObject)parse.parse(response);

		//checks if the jobj is a json object
		if(jobj.isJsonObject()) 
		{
		    //Convert json object to json object
			JsonObject jsonObject = jobj.getAsJsonObject();
		    
			JsonElement f2 = jsonObject.get("data");	//Retrieve the data field of the object
		    JsonElement f1 = ((JsonObject)f2).get("indexes");	//Retrieve the indexes field of the object
		    JsonElement f3 = ((JsonObject)f1).get("baqi");		//Retrieve the baqi field of the object
		    String f4 = ((JsonObject)f3).get("category").getAsString();	//Retrieve category field of the object
		    
		    return f4;	//return the category data 
	
		}
		
		return null;
	}
	
	/*
	 * Function to retrieve pollutant information using the json response as parameter. 
	 * Takes json response as parameter.
	 * Returns the air quality of the specified city
	 */
	public static String getAirQualityPollutant(String response)
	{	
		 //Parse the JSON data present in the string format
		JsonParser parse = new JsonParser();
		
		//Type cast the parsed json data in json object
		JsonObject jobj = (JsonObject)parse.parse(response);

		//checks if the jobj is a json object
		if(jobj.isJsonObject()) 
		{
			//Convert json object to json object
		    JsonObject jsonObject = jobj.getAsJsonObject();
		    
		    JsonElement f2 = jsonObject.get("data");	//Retrieve the data field of the object
		    JsonElement f1 = ((JsonObject)f2).get("indexes");	//Retrieve the indexes field of the object
		    JsonElement f3 = ((JsonObject)f1).get("baqi");	//Retrieve the baqi field of the object
		    String f4 = ((JsonObject)f3).get("dominant_pollutant").getAsString();	//Retrieve dominant_pollutant field of the object
		    
		    return f4;	//Return dominant_pollutant
	
		}
		
		return null;
	}
	
	/*
	 * Function to retrieve health information using the json response as parameter. 
	 * Takes json response as parameter.
	 * Returns the air quality of the specified city
	 */
	public static String getAirQualityHealth(String response)
	{	
		 //Parse the JSON data present in the string format
		JsonParser parse = new JsonParser();
		
		//Type cast the parsed json data in json object
		JsonObject jobj = (JsonObject)parse.parse(response);

		
		if(jobj.isJsonObject()) 
		{
		    JsonObject jsonObject = jobj.getAsJsonObject();
		    
		    JsonElement f2 = jsonObject.get("data");	//Retrieve the data field of the object
		    JsonElement f1 = ((JsonObject)f2).get("health_recommendations");	//Retrieve the health recommendations field of the object
		    String f4 = ((JsonObject)f1).get("general_population").getAsString();	//Retrieve the general_population field of the object
		    
		    return f4;	//Return health information
	
		}
		
		return null;
	}
	
	public static String getTemperature(String response)
	{
		//Parse the JSON data present in the string format
		JsonParser parse = new JsonParser();
		
		//Type cast the parsed json data in json object
		JsonObject jobj = (JsonObject)parse.parse(response);
		
		//Convert json object to json object
		JsonObject jsonObject = jobj.getAsJsonObject();
		JsonElement f2 = jsonObject.get("main");	//Retrieve the main field of the object
		String f1 = ((JsonObject) f2).get("temp").getAsString();	//Retrieve the temperature field of the object

		return f1;	//Returns the temperature
		
	}
	
	/*
	 * Function to retrieve minimum temperature using the json response as parameter. 
	 * Takes json response as parameter.
	 * Returns the minimum temperature of the specified city
	 */
	public static String getTemp_Min(String response)
	{
		//Parse the JSON data present in the string format
		JsonParser parse = new JsonParser();
		
		//Type cast the parsed json data in json object
		JsonObject jobj = (JsonObject)parse.parse(response);
		
		//Convert json object to json object
		JsonObject jsonObject = jobj.getAsJsonObject();
		
		JsonElement f2 = jsonObject.get("main");	//Retrieve the main field of the object
		String f1 = ((JsonObject) f2).get("temp_min").getAsString();	//Retrieve the minimum temperature field of the object

		return f1;	//Returns the minimum temperature
		
	}
	
	/*
	 * Function to retrieve maximum temperature using the json response as parameter. 
	 * Takes json response as parameter.
	 * Returns the minimum temperature of the specified city
	 */
	public static String getTemp_Max(String response)
	{
		//Parse the JSON data present in the string format
		JsonParser parse = new JsonParser();
		
		//Type cast the parsed json data in json object
		JsonObject jobj = (JsonObject)parse.parse(response);
		
		//Convert json object to json object
		JsonObject jsonObject = jobj.getAsJsonObject();
		
		JsonElement f2 = jsonObject.get("main");	//Retrieve the main field of the object
		String f1 = ((JsonObject) f2).get("temp_max").getAsString();	//Retrieve the maximum temperature field of the object

		return f1;	//Returns the maximum temperature
		
	}
	
	/*
	 * Function to retrieve longitude using the json response as parameter. 
	 * Takes json response as parameter.
	 * Returns the longitude 
	 */
	public static String getLon(String response)
	{
		//Parse the JSON data present in the string format
		JsonParser parse = new JsonParser();
		
		//Type cast the parsed json data in json object
		JsonObject jobj = (JsonObject)parse.parse(response);
		
		//Convert json object to json object
		JsonObject jsonObject = jobj.getAsJsonObject();
		
		JsonElement f2 = jsonObject.get("coord");	//Retrieve the coord field of the object
		String f1 = ((JsonObject) f2).get("lon").getAsString();	//Retrieve the longitude field of the object

		return f1; 	//Returns the longitude
		
	}
	
	/*
	 * Function to retrieve latitude using the json response as parameter. 
	 * Takes json response as parameter.
	 * Returns the latitude
	 */
	public static String getLat(String response)
	{
		//Parse the JSON data present in the string format
		JsonParser parse = new JsonParser();
		
		//Type cast the parsed json data in json object
		JsonObject jobj = (JsonObject)parse.parse(response);
		
		//Convert json object to json object
		JsonObject jsonObject = jobj.getAsJsonObject();
		
		JsonElement f2 = jsonObject.get("coord");	//Retrieve the coord field of the object
		String f3 = ((JsonObject) f2).get("lat").getAsString();	//Retrieve the latitude field of the object
		
		return f3;	//Returns the latitude
		
	}

}
