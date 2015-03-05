package com.inpowered.user.profile.dao.jdbc;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.inpowered.user.profile.Profile;
import com.inpowered.user.profile.dao.ProfileDao;

@Repository("profileDao")
public class JdbcProfileDaoImpl extends JdbcAbstractDao implements ProfileDao{

	public void saveProfile(Profile profile) {
		persist(profile);
	}

	@SuppressWarnings("unchecked")
	public List<Profile> findAllProfiles() {
		Criteria criteria = getSession().createCriteria(Profile.class);
		return (List<Profile>) criteria.list();
	}

	public void deleteProfileByFullname(String fullname) {
		Query query = getSession().createSQLQuery("delete from Profile where fullname = :fullname");
		query.setString("fullname", fullname);
		query.executeUpdate();
	}
	
	public Profile findByFullname(String fullname){
		Criteria criteria = getSession().createCriteria(Profile.class);
		criteria.add(Restrictions.eq("fullname",fullname));
		return (Profile) criteria.uniqueResult();
	}
	
	public void updateProfile(Profile profile){
		getSession().update(profile);
	}
	
}
