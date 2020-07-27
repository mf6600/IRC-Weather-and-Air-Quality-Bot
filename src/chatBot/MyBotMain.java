//Sets up chatbot and connects it to the server
package chatBot;

public class MyBotMain {
    
    public static void main(String[] args) throws Exception {
        
    	MyBot ChatBot = new MyBot();
        ChatBot.setVerbose(true);
        ChatBot.connect("irc.freenode.net"); //tells it where to connect to - this is the same as the web interface I linked in the last slide
        ChatBot.joinChannel("#MainChannel"); // Name of channel that you want to connect to - in this case it’s called “#MainChannel” 
        //this is the default message it will send when your pircbot first goes live 
        ChatBot.sendMessage("#MainChannel", "Welcome! You can find the current weather and air quality right here!");
        ChatBot.sendMessage("#MainChannel", "Enter \"Weather CityName\" or \"Weather ZipCode\" to find the current weather.");
        ChatBot.sendMessage("#MainChannel", "Enter \"Air Quality CityName\" or \"Air Quality ZipCode\" to find the air quality.");
        

        
    }
    
}