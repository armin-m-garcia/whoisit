package com.inpowered.user.profile.dao;

import java.util.List;

import com.inpowered.user.profile.Profile;

public interface ProfileDao {

	void saveProfile(Profile profile);
	
	List<Profile> findAllProfiles();
	
	void deleteProfileByFullname(String fullname);
	
	Profile findByFullname(String fullname);
	
	void updateProfile(Profile profile);
}
