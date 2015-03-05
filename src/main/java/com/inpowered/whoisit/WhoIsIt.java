package com.inpowered.whoisit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.inpowered.Util;
import com.inpowered.user.profile.Profile;
import com.inpowered.user.profile.ProfileService;
import com.inpowered.whoisit.spring.configuration.WhoIsItConfiguration;

public class WhoIsIt {

	public static void main(String[] args)
	{
		BufferedReader reader = null;
		
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(WhoIsItConfiguration.class);
		ObjectMapper mapper = new ObjectMapper();
		ProfileService profileService = (ProfileService) context.getBean("profileService");		
		
		try {			
		    reader = new BufferedReader(new InputStreamReader(Util.findInClasspath("input.txt").openStream()));
			
			for (String line = reader.readLine(); line != null; line = reader.readLine()) 
			{				
				profileService.decorateProfile(mapper.readValue(mapper.readTree(line), Profile.class));
			}
			
		} catch (Throwable t) {
			t.printStackTrace();
		}
		finally
		{
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		context.close();
	}
}
