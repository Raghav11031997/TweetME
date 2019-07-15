package com.khoros.TweetME;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import twitter4j.TwitterException;
import java.util.List;

@SpringBootApplication
public class TweetMeApplication {

	public static void main(String[] args) {

		SpringApplication.run(TweetMeApplication.class, args);
		try{

			CreateTweet tweet = new CreateTweet();
			tweet.createTweet("tweeeet");

			GetTimeline TL = new GetTimeline();
			List<String> str = TL.getTimeLine();

			System.out.println(str);
		}
		catch (TwitterException e)
		{
			e.printStackTrace();
		}
	}
}
