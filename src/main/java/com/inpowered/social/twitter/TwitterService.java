package com.inpowered.social.twitter;

import org.springframework.stereotype.Service;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;

import com.inpowered.Util;
import com.inpowered.social.SocialService;
import com.inpowered.user.profile.Profile;

@Service("socialService")
public class TwitterService implements SocialService {

	/** Need to configure!  **/
	
	public String consumerKey = null;
	public String consumerKeySecret = null;
	public String accessToken = null;
	public String accessTokenSecret = null;

	
	public String getConsumerKey() {
		return consumerKey;
	}

	public void setConsumerKey(String consumerKey) {
		this.consumerKey = consumerKey;
	}

	public String getConsumerKeySecret() {
		return consumerKeySecret;
	}

	public void setConsumerKeySecret(String consumerKeySecret) {
		this.consumerKeySecret = consumerKeySecret;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getAccessTokenSecret() {
		return accessTokenSecret;
	}

	public void setAccessTokenSecret(String accessTokenSecret) {
		this.accessTokenSecret = accessTokenSecret;
	}

	/* (non-Javadoc)
	 * @see com.inpowered.social.twitter.SocialService#resolveUserImageURI(com.inpowered.user.profile.Profile)
	 */
	public void resolveUserImageURI(Profile profile)
	{
		// Construct the access tokekn
		AccessToken accessToken = new AccessToken(getAccessToken(),getAccessTokenSecret());
		
		// Construct an instance to the twitter API service
		Twitter twitter = new TwitterFactory().getInstance();

		// Specify the authentication parameters
		twitter.setOAuthConsumer(getConsumerKey(), getConsumerKeySecret());
		twitter.setOAuthAccessToken(accessToken);

		User user = null;

		try {
			// Grab the user by their login name
			user = twitter.showUser(profile.getTwitterUri().getPath().replaceFirst("\\/", ""));
		} catch (IllegalStateException e) {
			e.printStackTrace();
			return;
		} catch (TwitterException e) {			
			e.printStackTrace();
			return;
		}

		// Update the url
		profile.setTwitterUrl(Util.toURI(user.getProfileImageURL()));
	}
}
