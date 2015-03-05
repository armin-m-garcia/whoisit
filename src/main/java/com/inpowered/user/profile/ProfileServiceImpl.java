package com.inpowered.user.profile;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inpowered.nlp.NamingService;
import com.inpowered.search.SearchService;
import com.inpowered.social.SocialService;
import com.inpowered.user.profile.dao.ProfileDao;

@Service("profileService")
@Transactional
public class ProfileServiceImpl implements ProfileService {

	@Autowired
	protected NamingService namingService = null;
	@Autowired
	protected SearchService searchService = null;
	@Autowired
	protected SocialService socialService = null;	
	@Autowired
	private ProfileDao dao;
	
	public NamingService getNamingService() {
		return namingService;
	}
	
	public SearchService getSearchService() {
		return searchService;
	}

	public SocialService getSocialService() {
		return socialService;
	}

	public ProfileDao getDao() {
		return dao;
	}

	/* (non-Javadoc)
	 * @see com.inpowered.user.profile.ProfileService#saveProfile(com.inpowered.user.profile.Profile)
	 */
	public void saveProfile(Profile profile) {
		dao.saveProfile(profile);
	}

	/* (non-Javadoc)
	 * @see com.inpowered.user.profile.ProfileService#findAllProfiles()
	 */
	public List<Profile> findAllProfiles() {
		return dao.findAllProfiles();
	}

	/* (non-Javadoc)
	 * @see com.inpowered.user.profile.ProfileService#deleteProfileByFullname(java.lang.String)
	 */
	public void deleteProfileByFullname(String fullname) {
		dao.deleteProfileByFullname(fullname);
	}

	/* (non-Javadoc)
	 * @see com.inpowered.user.profile.ProfileService#findProfileByFullname(java.lang.String)
	 */
	public Profile findProfileByFullname(String fullname) {
		return dao.findByFullname(fullname);
	}

	/* (non-Javadoc)
	 * @see com.inpowered.user.profile.ProfileService#updateProfile(com.inpowered.user.profile.Profile)
	 */
	public void updateProfile(Profile profile){
		dao.updateProfile(profile);
	}
	
	/* (non-Javadoc)
	 * @see com.inpowered.user.profile.ProfileService#decorateProfile(com.inpowered.user.profile.Profile)
	 */
	public void decorateProfile(Profile profile)
	{
		this.getNamingService().classifyNames(profile);
		
		this.getSearchService().searchForTwitterProfile(profile);
		
		// Will work if keys are configured.
//		this.getSocialService().resolveUserImageURI(profile);
		
		// Have UTC issue w/ MySQL
//		this.saveProfile(profile);
	}
}
