package com.globalroam.matchmove.api;

/**
 * Created by Administrator on 2017/4/25.
 */
public interface AuthenticationAPI {
    /**
     * oauth_consumer_key 	Also referred to as API Key which identifies the application account that is used to connect to the API system.
     * oauth_nonce 	Any random alphanumeric value that identifies the transaction being requested. This must be unique within five (5) minutes.
     * oauth_signature_method 	Method used to create the signature of the request in [Step 3d]. Currently, we only support “HMAC-SHA1.”
     * oauth_timestamp 	Time when the request is being called in Unix Timestamp Format.
     * oauth_user_name 	Registered user of a Virtual Wallet.
     * oauth_user_password 	Password associated to the oauth_user_name.
     * oauth_version 	Version of OAuth you are connecting to. Currently, we only support “1.0.”
     */


}
