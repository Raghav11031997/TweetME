//package com.khoros.tweetme;
//
//import com.khoros.tweetme.CONTROLLER.HelloWorldResource;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.mockito.junit.MockitoJUnitRunner;
//import twitter4j.ResponseList;
//import twitter4j.Status;
//import twitter4j.Twitter;
//import javax.ws.rs.core.Response;
//import java.util.List;
//
//@RunWith(MockitoJUnitRunner.class)
//public class HelloWorldResourceTest {
//
//	Twitter twitter = Mockito.mock(Twitter.class);
//
//	@Test
//	public void testPost() throws Exception{
//
//		String PostMessage = "Posted";
//
//		Status PostStatus = Mockito.mock(Status.class);
//
//		Mockito.doReturn(PostStatus).when(twitter).updateStatus(PostMessage);
//
//		HelloWorldResource helloWorldResource = new HelloWorldResource(twitter);
//
//		Response response = helloWorldResource.doPost(PostMessage);
//
//		Assert.assertEquals(response.getEntity(), PostStatus);
//	}
//
//	@Test
//	public void testGet() throws Exception{
//
//		List<Status> tweets = Mockito.mock(ResponseList.class);
//
//		Status PostStatus = Mockito.mock(Status.class);
//
//		tweets.add(PostStatus);
//
//		Mockito.doReturn(tweets).when(twitter).getHomeTimeline();
//
//		HelloWorldResource helloWorldResource = new HelloWorldResource(twitter);
//
//		Response response = helloWorldResource.doGet();
//
//		Assert.assertEquals(response.getEntity(), tweets);
//	}
//}
