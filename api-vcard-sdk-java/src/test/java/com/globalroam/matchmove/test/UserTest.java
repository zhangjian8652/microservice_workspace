package com.globalroam.matchmove.test;

import com.matchmove.mmpay.api.*;
import com.matchmove.mmpay.test.Session;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

/**
 * Author: zhangjian
 * Date :2017/5/23.
 * Des:
 */
public class UserTest extends BaseTest {



    /**
     * request
     * id	no	32	Account unique identifier
     * email	yes	50	Email address
     * password	yes	32	User password must contain at least a letter and a number. Symbols may be added but are be limited to the following: !@#$%^&*()_+`-={}
     * first_name	yes	50	First or given name
     * middle_name	no	50	Middle name
     * last_name	yes	50	Last or family name
     * preferred_name	yes	25	Preferred Name or Name on Card
     * mobile_country_code	yes	3	Mobile country code
     * mobile	yes	12	Mobile number
     */

    @Test
    public void addUser() throws ResourceException, NoSuchAlgorithmException, UnsupportedMethodException, InvalidKeyException, NoSuchPaddingException, BadPaddingException, UnsupportedEncodingException, NoSuchProviderException, IllegalBlockSizeException {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        Integer number = Integer.parseInt((Math.abs(new Random(8).nextInt()) + "").substring(2, 10));
        // Update user's details
        Map<String, String> userData = new HashMap<String, String>();
        userData.put("email", "test" + uuid.substring(0, 3) + "@globalroam.com");
        userData.put("password", "1234abcd");
        userData.put("first_name", "jian");
        userData.put("last_name", "zhang");
        userData.put("preferred_name", "jian");
        userData.put("mobile_country_code", "86");
        userData.put("mobile", "183" + number);
        System.out.println("users~POST: " + this.connection.consume("users", HttpRequest.METHOD_POST, userData));
        // response :{"date":{"registration":"2017-05-23T15:27:48+08:00"},"name":{"middle":null,"last":"Zhang","first":"Jian","preferred":"jian"},"mobile":{"country_code":"86","number":"18358562568"},"id":"c9a91c63c9985c7a8c0af12547616df5","email":"testc4f@globalroam.com","status":{"is_active":true,"text":"active"}}
    }


    @Test
    public void getUser() throws ResourceException, NoSuchAlgorithmException, UnsupportedMethodException, InvalidKeyException, NoSuchPaddingException, BadPaddingException, UnsupportedEncodingException, NoSuchProviderException, IllegalBlockSizeException, InvalidAlgorithmParameterException, InvalidKeySpecException, ShortBufferException, URISyntaxException {
        Map<String, String> userData = new HashMap<String, String>();
        userData.put("email", "zhang.jian@globalroam.com");

        if (this.connection.authenticate(user)) {
            System.out.println("Already authenticated");
        } else {
            // Use OAuth login
            this.connection.authenticate(user, password);
        }

        this.connection.authenticate(this.user, this.password);
        System.out.println("users~GET: " + this.connection.consume("users", HttpRequest.METHOD_GET, userData));
        // response : {"date":{"registration":"2017-05-23T14:12:58+08:00"},"identification":{"number":"","expiry":null,"type":""},"name":{"middle":null,"last":"Zhang","first":"Jian","preferred":"jian"},"mobile":{"country_code":"86","number":"18328358153"},"links":[{"method":"GET","rel":"users.wallets","href":"https://beta-api.mmvpay.com/sgmc/v1/users/wallets"}],"id":"17f5d680def5f18ac60cecb2cf5e0a33","email":"zhang.jian@globalroam.com","status":{"is_active":true,"text":"active"}}
    }


    @Test
    public void updateUser() throws ResourceException, NoSuchAlgorithmException, UnsupportedMethodException, InvalidKeyException, NoSuchPaddingException, BadPaddingException, UnsupportedEncodingException, NoSuchProviderException, IllegalBlockSizeException, InvalidAlgorithmParameterException, InvalidKeySpecException, ShortBufferException, URISyntaxException {
        Map<String, String> userData = new HashMap<String, String>();
        userData.put("mobile", "18328358152");

        if (this.connection.authenticate(user)) {
            System.out.println("Already authenticated");
        } else {
            // Use OAuth login
            this.connection.authenticate(user, password);
        }

        this.connection.authenticate(this.user, this.password);
        System.out.println("users~PUT: " + this.connection.consume("users", HttpRequest.METHOD_PUT, userData));
        // response : {"date":{"registration":"2017-05-23T14:12:58+08:00"},"identification":{"number":"","expiry":null,"type":""},"name":{"middle":null,"last":"Zhang","first":"Jian","preferred":"jian"},"mobile":{"country_code":"86","number":"18328358153"},"links":[{"method":"GET","rel":"users.wallets","href":"https://beta-api.mmvpay.com/sgmc/v1/users/wallets"}],"id":"17f5d680def5f18ac60cecb2cf5e0a33","email":"zhang.jian@globalroam.com","status":{"is_active":true,"text":"active"}}
    }

    @Test
    //跑不通，400 不知道具体原因
    public void deleteUser() throws ResourceException, NoSuchAlgorithmException, UnsupportedMethodException, InvalidKeyException, NoSuchPaddingException, BadPaddingException, UnsupportedEncodingException, NoSuchProviderException, IllegalBlockSizeException, InvalidAlgorithmParameterException, InvalidKeySpecException, ShortBufferException, URISyntaxException {
        Map<String, String> userData = new HashMap<String, String>();
        userData.put("state", "blocked");
        System.out.println("users blocked ~DELETE: " + this.connection.consume("users/17f5d680def5f18ac60cecb2cf5e0a33", HttpRequest.METHOD_DELETE, userData));
        // { "id": "17f5d680def5f18ac60cecb2cf5e0a33", "state": "blocked"}
    }


    @Test
    public void activeUser() throws ResourceException, NoSuchAlgorithmException, UnsupportedMethodException, InvalidKeyException, NoSuchPaddingException, BadPaddingException, UnsupportedEncodingException, NoSuchProviderException, IllegalBlockSizeException, InvalidAlgorithmParameterException, InvalidKeySpecException, ShortBufferException, URISyntaxException {
        Map<String, String> userData = new HashMap<String, String>();
        userData.put("id", "17f5d680def5f18ac60cecb2cf5e0a33");

        System.out.println("users  active ~POST: " + this.connection.consume("users", HttpRequest.METHOD_POST, userData));
        // {"date":{"registration":"2017-05-23T14:12:58+08:00"},"identification":{"number":"","expiry":null,"type":""},"name":{"middle":null,"last":"Zhang","first":"Jian","preferred":"jian"},"mobile":{"country_code":"86","number":"18328358153"},"links":[{"method":"GET","rel":"users.wallets","href":"https://beta-api.mmvpay.com/sgmc/v1/users/wallets"}],"id":"17f5d680def5f18ac60cecb2cf5e0a33","email":"zhang.jian@globalroam.com","status":{"is_active":true,"text":"active"}}
    }

    @Test
    public void changePassword() throws ResourceException, NoSuchAlgorithmException, UnsupportedMethodException, InvalidKeyException, NoSuchPaddingException, BadPaddingException, UnsupportedEncodingException, NoSuchProviderException, IllegalBlockSizeException, InvalidAlgorithmParameterException, InvalidKeySpecException, ShortBufferException, URISyntaxException {
        Map<String, String> userData = new HashMap<String, String>();
        userData.put("email", "zhang.jian@globalroam.com");
        userData.put("password", "942645426zj");
        System.out.println(connection.consume("oauth/password", HttpRequest.METHOD_POST, userData));
    }

    public static void main(String[] args) {
        Random random = new Random(6);
        System.out.println(Math.abs(random.nextInt()) / 100);
    }
}
