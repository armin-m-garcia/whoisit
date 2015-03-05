package com.inpowered.nlp.stanford;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.inpowered.nlp.NamingService;
import com.inpowered.user.profile.Profile;

import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;

@Service("namingService")
public class StanfordNLPNamingService implements NamingService {
	
	 protected final static String PERSON = "PERSON";

	 protected String serializedClassifier = "classifiers/english.all.3class.distsim.crf.ser.gz";
	 protected AbstractSequenceClassifier<CoreLabel> classifier = null;
	 
	public String getSerializedClassifier() {
		return serializedClassifier;
	}

	public void setSerializedClassifier(String serializedClassifier) {
		this.serializedClassifier = serializedClassifier;
	}

	protected AbstractSequenceClassifier<CoreLabel> classifier()
	 {
		 return this.classifier;
	 }
	
	 protected void classifier(AbstractSequenceClassifier<CoreLabel> classifier)
	 {
		 this.classifier = classifier;
	 }
	 
	 /* (non-Javadoc)
	 * @see com.inpowered.nlp.stanford.NamingService#classifyNames(com.inpowered.user.profile.Profile)
	 */
	public void classifyNames(Profile profile)
	 {
		for (List<CoreLabel> lcl : classifier.classify(profile.getFullName())) {
			// There should only be two names
			boolean isPerson = lcl.size() == 2
					// And both of them should be names of people
					&& PERSON.equals(lcl.get(0).get(CoreAnnotations.AnswerAnnotation.class))
					&& PERSON.equals(lcl.get(1).get(CoreAnnotations.AnswerAnnotation.class));

			profile.setIsPerson(isPerson);
		}
	 }
	
	@PostConstruct 
	public void startUp() throws Exception
	{
		this.classifier(CRFClassifier.getClassifier(this.getSerializedClassifier()));
	}
}
