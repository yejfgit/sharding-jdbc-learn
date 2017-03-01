package com.netease.okr.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.expressme.openid.Association;
import org.expressme.openid.Endpoint;
import org.expressme.openid.OpenIdException;
import org.expressme.openid.OpenIdManager;
import org.expressme.openid.ShortName;

/**
 * for netease
 * @author lzs
 *
 */
public class OpenIdAction {

	static final long ONE_HOUR = 3600000L;
    static final long TWO_HOUR = ONE_HOUR * 2L;
	static final String ATTR_MAC = "openid_mac";
    static final String ATTR_ALIAS = "openid_alias";
    private ShortName shortName = new ShortName();
    private OpenIdManager manager;
    private Set<String> nonceDb = new HashSet<String>();
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

	public String getOpenIdUri(String _sOp, HttpServletRequest request){
		String sRealm = shortName.lookupUrlByName("realm");
    	String sReturnTo = shortName.lookupUrlByName("returnTo");
    	return getOpenIdUri(sRealm, sReturnTo, _sOp, request);
	}
	
	public String getOpenIdUri(String _sRealm, String _sReturnTo, String _sOp, HttpServletRequest request){
		String sReturn = "";
		manager = new OpenIdManager();
        manager.setRealm(_sRealm);
        manager.setReturnTo(_sReturnTo);

        try{
            Endpoint endpoint = manager.lookupEndpoint(_sOp);
            Association association = manager.lookupAssociation(endpoint);
            request.getSession().setAttribute(ATTR_MAC, association.getRawMacKey());
            request.getSession().setAttribute(ATTR_ALIAS, endpoint.getAlias());
            sReturn = manager.getAuthenticationUrl(endpoint, association);
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
		return sReturn;
	}
	

    public boolean checkOpenId(String nonce, HttpServletRequest request) {
    	
    	String sRealm = shortName.lookupUrlByName("realm");
    	String sReturnTo = shortName.lookupUrlByName("returnTo");
    	
    	manager = new OpenIdManager();
        manager.setRealm(sRealm);
        manager.setReturnTo(sReturnTo);
    	
    	try{
	    	byte[] mac_key = (byte[]) request.getSession().getAttribute(ATTR_MAC);
	        String alias = (String) request.getSession().getAttribute(ATTR_ALIAS);
	        
	       // System.out.println("mac_key: "+mac_key.toString());
	       // System.out.println("alias: "+alias);
	        
//	        Authentication authentication = 
	        		manager.getAuthentication(request, mac_key, alias);
	        
	       // System.out.println("authentication: "+authentication.toString());
    	}catch(Exception ex){
    		ex.printStackTrace();
    		return false;
    	}
    	
        // check response_nonce to prevent replay-attack:
        if (nonce==null || nonce.length()<20){
        	return false;
        	//throw new OpenIdException("Verify failed.");
        }
        // make sure the time of server is correct:
        long nonceTime = getNonceTime(nonce);
        long diff = Math.abs(System.currentTimeMillis() - nonceTime);
//        System.out.println("diff: "+diff);
        if (diff > ONE_HOUR){
        	return false;
            //throw new OpenIdException("Bad nonce time.");
        }
        if (isNonceExist(nonce)){
        	return false;
            //throw new OpenIdException("Verify nonce failed.");
        }
        storeNonce(nonce, nonceTime + TWO_HOUR);
        return true;
    }

    // simulate a database that store all nonce:
    

    // check if nonce is exist in database:
    boolean isNonceExist(String nonce) {
        return nonceDb.contains(nonce);
    }

    // store nonce in database:
    void storeNonce(String nonce, long expires) {
        nonceDb.add(nonce);
    }

    long getNonceTime(String nonce) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                    .parse(nonce.substring(0, 19) + "+0000")
                    .getTime();
        }
        catch(ParseException e) {
            throw new OpenIdException("Bad nonce time.");
        }
    }

}
