package com.globalroam.matchmove.test;

import com.matchmove.mmpay.api.Connection;
import com.matchmove.mmpay.test.Session;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.Security;

/**
 * Author: zhangjian
 * Date :2017/5/23.
 * Des:
 */
public class BaseTest {
    protected String host;
    protected String key;
    protected String secret;
    protected String user;
    protected String password;

    protected Connection connection;

    public BaseTest() {
        Security.addProvider(new BouncyCastleProvider());
        this.host = "https://beta-api.mmvpay.com/sgmc/v1";
        this.key = "tOp5M3EfdBhdindGD4XTpzExjduVAE4v";
        this.secret = "6PvRSshWW8Jib1yDpQecjwdl5HdTAuNXrB5wD7zJS2iSPLmBzPwa2hVjqCENNnJE";
        this.user = "zhang.jian@globalroam.com";
        this.password = "942645426zj";

        // Initialize
        this.connection = new Connection(host, key, secret);
        connection.setSession(new Session());
    }
}
