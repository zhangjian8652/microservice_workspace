package com.matchmove.mmpay.test;

import com.matchmove.mmpay.api.Connection;
import com.matchmove.mmpay.api.HttpRequest;
import com.matchmove.mmpay.api.ResourceException;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.Test;

import java.security.Security;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/25.
 */
public class TokenTest {


    @Test
    public void test() throws Exception {
        Security.addProvider(new BouncyCastleProvider());

      //  String host = "https://beta-api.mmvpay.com/sg/v1";
        String host = "https://beta-api.mmvpay.com/sgmc/v1";
        //String key = "<ConsumerKey>";
        String key = "tOp5M3EfdBhdindGD4XTpzExjduVAE4v";
        // String secret = "<ConsumerSecret>";
        String secret = "6PvRSshWW8Jib1yDpQecjwdl5HdTAuNXrB5wD7zJS2iSPLmBzPwa2hVjqCENNnJE";

       // String user = "someone@email.com";
        String user = "zhang.jian@globalroam.com";
        String password = "942645426zj";

        // Initialize
        Connection connection = new Connection(host, key, secret);
        Connection anoConnection = new Connection(host, key, secret);

        connection.setSession(new Session());

        try {
            // Anonymous calling
            // Get all cards offered
            System.out.println("users/wallets/cards/types~GET: " + anoConnection.consume("users/wallets/cards/types"));

            Map<String, String> userFundsData = new HashMap<String, String>();

            userFundsData.put("email", user);
            userFundsData.put("amount", "1000");
            // Topup a user
            System.out.println("users/wallets/funds~POST: " + anoConnection.consume("users/wallets/funds", HttpRequest.METHOD_POST, userFundsData));

            if (connection.authenticate(user)) {
                System.out.println("Already authenticated");
            } else {
                // Use OAuth login
                connection.authenticate(user, password);
            }

            // Get user's details
            System.out.println("users~GET: " + connection.consume("users"));

            // Update user's details
            Map<String, String> userData = new HashMap<String, String>();

            userData.put("title", "Mr");
            userData.put("gender", "male");
            userData.put("id_number", "s1234567890");
            userData.put("id_type", "nric");
            userData.put("country_of_issue", "singapore");

            System.out.println("users~PUT: " + connection.consume("users", HttpRequest.METHOD_PUT, userData));
        } catch (ResourceException e) {
            System.out.println("Error [" + e.getCode() + "]: " + e.getMessage());
        }

        System.out.println("done!");
    }
}
