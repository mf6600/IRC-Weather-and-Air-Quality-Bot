//Implements the chatbot responses
package chatBot;

import java.io.IOException;

import org.jibble.pircbot.PircBot;

public class MyBot extends PircBot 
{
    ApiCall api;
	//constructor 
	public MyBot()
	{
		this.setName("MyWeatherBot"); //this is the name the bot will use to join the IRC server
	}
	
	//every time a message is sent, this method will be called and this information will be passed on
	//this is how you read a message from the channel 
	public void onMessage(String channel, String sender, String login, String hostname, String message)
	{
		
		//this function reads the message that comes in and returns the appropriate response
		if(message.contains("Weather"))
		{
			String input = message.split(" ")[1];	//Retrieves the zipCode or city name
			String temp;
			
			//Checks if the input is a zipcode or a city
			if (Character.isDigit(input.charAt(0))) 
			{
				try 
				{
					temp = ApiCall.callWeatherAPIZip(input);	//Calls the weather api function to retrieve the temperature of the specified zip code
					String temp_min = ApiCall.callWeatherAPIZipLow(input);	//Calls the weather api function to retrieve the minimum temperature of the specified zip code
					String temp_max = ApiCall.callWeatherAPIZipMax(input);	//Calls the weather api function to retrieve the maximum temperature of the specified zip code
					sendMessage(channel, "Hey " + sender + "! " + "Temperature is " + temp + " Kelvin with a low of " + temp_min + " and a high of " + temp_max);
					
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}
			
			else
			{
				try 
				{
					temp = ApiCall.callWeatherAPI(input);	//Calls the weather api function to retrieve the temperature of the specified zip code
					String temp_min = ApiCall.callWeatherAPICityLow(input);	//Calls the weather api function to retrieve the minimum temperature of the specified zip code
					String temp_max = ApiCall.callWeatherAPICityMax(input);	//Calls the weather api function to retrieve the maximum temperature of the specified zip code
					sendMessage(channel, "Hey " + sender + "! " + "Temperature in " + input + " is " + temp + " Kelvin with a low of " + temp_min + " and a high of " + temp_max);
					
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}
		            
		} 
		
		//Checks if the message contains air quality
		else if(message.contains("Air Quality"))
		{
			String input = message.split(" ")[2];	//Retrieves the zipCode or city name
			String airQuality;
			String pollutant;
			String health;
			
			if (Character.isDigit(input.charAt(0))) 
			{
				try 
				{
					airQuality = ApiCall.callAirQualityAPI_AQ(input);	//Calls the air quality api to retrieve the air quality information
					pollutant = ApiCall.callAirQualityAPI_pollutant(input);	//Calls the air quality api to retrieve the pollutant information
					health = ApiCall.callAirQualityAPI_health(input);		//Calls the air quality api to retrieve the health information
					sendMessage(channel, "Hey " + sender + "! " + airQuality);
					sendMessage(channel, "Dominant Pollutant in air: " + pollutant);
					sendMessage(channel, "Health Recommendation: " + health);
					
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}
			
			//Same process occurs if input is cityname instead of zipcode
			else
			{
				try 
				{
					airQuality = ApiCall.callAirQualityAPI_AQ(input);
					pollutant = ApiCall.callAirQualityAPI_pollutant(input);
					health = ApiCall.callAirQualityAPI_health(input);
					sendMessage(channel, "Hey " + sender + "! " + airQuality);
					sendMessage(channel, "Dominant Pollutant in air: " + pollutant);
					sendMessage(channel, "Health Recommendation: " + health);
					
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}
		            
		}
		
		else if(message.contains("Hello"))
		{
			sendMessage(channel, "Hey " + sender + "!");

		} 
		
		//Displays this message if instructions are not valid
		else
		{
			sendMessage(channel, "Oops! I can't help you with that! " + sender + ", please read the instructions below");
			sendMessage("#testChannel", "Enter \"Weather CityName\" or \"Weather ZipCode\" to find the current weather.");
	        sendMessage("#testChannel", "Enter \"Air Quality CityName\" or \"Air Quality ZipCode\" to find the air quality.");
		}
	}

}