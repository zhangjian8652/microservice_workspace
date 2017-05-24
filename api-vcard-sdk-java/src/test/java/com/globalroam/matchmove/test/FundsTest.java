package com.globalroam.matchmove.test;

import com.matchmove.mmpay.api.HttpRequest;
import com.matchmove.mmpay.api.ResourceException;
import com.matchmove.mmpay.api.UnsupportedMethodException;
import org.junit.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: zhangjian
 * Date :2017/5/24.
 * Des:
 */
public class FundsTest extends BaseTest{

    @Test
    public void topup() throws ResourceException, NoSuchAlgorithmException, UnsupportedMethodException, InvalidKeyException, NoSuchPaddingException, BadPaddingException, UnsupportedEncodingException, NoSuchProviderException, IllegalBlockSizeException {
        Map<String, String> userData = new HashMap<String, String>();
        userData.put("email", "zhang.jian@globalroam.com");
        userData.put("amount", 100 + ""); // 低于100不用confirm，超过100需要confirm
        userData.put("details", "topup");

        System.out.println(this.connection.consume("users/wallets/funds", HttpRequest.METHOD_POST, userData));
        // {"ref_id":"000000A700003B9BDCBA00000002FEF1","confirm":"skip","wallet_id":"caf0860f15ceb718e853fe669fc12983","user_id":"17f5d680def5f18ac60cecb2cf5e0a33","id":"00910704252b2852658229c64ccb5781c0ff27770d5a2fbffa1eb6a93a3c9d61"}

    }

    @Test
    public void getTopup() throws ResourceException, NoSuchAlgorithmException, UnsupportedMethodException, InvalidKeyException, NoSuchPaddingException, BadPaddingException, UnsupportedEncodingException, NoSuchProviderException, IllegalBlockSizeException {

        System.out.println(this.connection.consume("users/wallets/funds/83e1081684cb55ed251af84969b31848da3451bd28ba830b5c550d8892f8a5db", HttpRequest.METHOD_GET));
        //{"id":"7e8d3aab77067942ef635233656df9f9fad27e3be0f270add96be9755af468d3","status":"pending"}
    }


    @Test
    public void confirmTopup() throws ResourceException, NoSuchAlgorithmException, UnsupportedMethodException, InvalidKeyException, NoSuchPaddingException, BadPaddingException, UnsupportedEncodingException, NoSuchProviderException, IllegalBlockSizeException {

        Map<String, String> userData = new HashMap<String, String>();
        userData.put("ids", "83e1081684cb55ed251af84969b31848da3451bd28ba830b5c550d8892f8a5db");

        System.out.println(this.connection.consume("oauth/consumer/funds", HttpRequest.METHOD_POST, userData));
        //{"transactions":[ {"id":"d1d7de673a58794a7233d2484d0c2c64fa44809ec31f4a6a5c4080e4","ref_id":"000000A700003B9BB69600000002ABA7","status":"success","description":"Topup Successful for ref_id d1d7dec31f4a6a5c4080e4\n"}]}
    }

}
