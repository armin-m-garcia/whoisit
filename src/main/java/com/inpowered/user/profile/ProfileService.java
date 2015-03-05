package com.inpowered.user.profile;

import java.util.List;

public interface ProfileService {

	public abstract void saveProfile(Profile profile);

	public abstract List<Profile> findAllProfiles();

	public abstract void deleteProfileByFullname(String fullname);

	public abstract Profile findProfileByFullname(String fullname);

	public abstract void updateProfile(Profile profile);

	/**
	 * This is method is absolutely written to not be performant.  To do so, we'd implement a 
	 * reactive pattern to prevent the thread from blocking and waiting for a response.  The
	 * CPU can be doing more useful things during the block.
	 * 
	 * @param profile
	 */
	public abstract void decorateProfile(Profile profile);

}