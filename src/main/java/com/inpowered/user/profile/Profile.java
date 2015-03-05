package com.inpowered.user.profile;

import java.net.URI;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="profile")
public class Profile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int id;
	
	@Column(name = "fullname", nullable = false)
	protected String fullName = null;        // "Lindsey Dimattina"
	
	@Column(name = "site", nullable = false)
	protected String site = null;            // "site":"radaronline.com"

	@Column(name = "storyurl", nullable = true)
	protected URI storyUrl = null;           // "storyUrl":"http://radaronline.com/exclusives/2013/08/rhoc-star-slade-smiley-calls-vicki-gunvalson-tupperware-face-says-she-looks-like-a-man/"
	
	@Column(name = "isperson", nullable = false)
	protected boolean isPerson = false;
	
	@Column(name = "twitteruri", nullable = true)
	protected URI twitterUri = null;
	
	public URI getTwitterUri() {
		return twitterUri;
	}

	public void setTwitterUrl(URI twitterUri) {
		this.twitterUri = twitterUri;
	}

	public boolean isPerson() {
		return isPerson;
	}

	public void setIsPerson(boolean isPerson) {
		this.isPerson = isPerson;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public URI getStoryUrl() {
		return storyUrl;
	}

	public void setStoryUrl(URI storyUrl) {
		this.storyUrl = storyUrl;
	}

	public String toString()
	{
		return "fullName="+fullName+" site="+site+" storyUrl="+storyUrl+" isPerson="+isPerson+" twitterUri="+twitterUri;
	}
}