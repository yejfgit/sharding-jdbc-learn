package com.netease.okr.util;

import org.openid4java.consumer.ConsumerManager;

public class OpenIdUtil {

	private static ConsumerManager consumermanager = null;

	public static synchronized ConsumerManager getConsumerManager(){
		if (consumermanager  == null) {
			consumermanager = new ConsumerManager();
			consumermanager.getRealmVerifier().setEnforceRpId(false);
//			consumermanager.setAssociations(new InMemoryConsumerAssociationStore());
//			consumermanager.setNonceVerifier(new InMemoryNonceVerifier(5000));
			consumermanager.setMaxAssocAttempts(0);
		}
		return consumermanager;
	}
}
