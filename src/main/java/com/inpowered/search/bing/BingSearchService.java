package com.inpowered.search.bing;

import java.util.Iterator;

import org.springframework.stereotype.Service;

import net.billylieurance.azuresearch.AzureSearchResultSet;
import net.billylieurance.azuresearch.AzureSearchWebQuery;
import net.billylieurance.azuresearch.AzureSearchWebResult;

import com.inpowered.Util;
import com.inpowered.search.SearchService;
import com.inpowered.user.profile.Profile;

@Service("searchService")
public class BingSearchService implements SearchService 
{
	protected final static String TWITTER_DOMAIN = "twitter.com";
	protected final static String TWITTER_ACCOUNT_QUERY_TEMPLATE =  "%s %s twitter profile";
	
	protected String accountKey = "KRloYPIlUpTzZW9jPDL5gX2Tk9dFqtcUUtmqEbeLU1M";
	
	public String getAccountKey() {
		return accountKey;
	}

	public void setAccountKey(String accountKey) {
		this.accountKey = accountKey;
	}

	/* (non-Javadoc)
	 * @see com.inpowered.search.bing.SearchService#searchForTwitterProfile(com.inpowered.user.profile.Profile)
	 */
	public void searchForTwitterProfile(Profile profile)
	{	
		// Compose the search query
		String query = String.format(TWITTER_ACCOUNT_QUERY_TEMPLATE, profile.getFullName(), profile.getSite());
		
		// Construct the client call
		AzureSearchWebQuery aq = new AzureSearchWebQuery();		
		aq.setAppid(this.getAccountKey());
		aq.setQuery(query);
		aq.doQuery();
		
		// Get the results of the call
		AzureSearchResultSet<AzureSearchWebResult> ars = aq.getQueryResult();
		
		// Iterate through the results
		for(Iterator<AzureSearchWebResult> i = ars.iterator();i.hasNext();)
		{
			AzureSearchWebResult result = i.next();
			
			// If the reported url is the twitter domain
			if( result.getUrl().contains(TWITTER_DOMAIN) )
			{
				// Grab it!
				profile.setTwitterUrl(Util.toURI(result.getUrl()));				
				break;
			}			
		}		
	}
}
