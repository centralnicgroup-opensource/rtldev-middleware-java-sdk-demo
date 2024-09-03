package com.centralnicreseller.testapiconnector;

import java.util.HashMap;
import java.util.Map;
import com.centralnicreseller.apiconnector.APIClient;
import com.centralnicreseller.apiconnector.Response;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {

        // perform an api login and create an api session
        APIClient cl = new APIClient();
        cl.useOTESystem().setCredentials(System.getenv("CNR_TEST_USER"), System.getenv("CNR_TEST_PASSWORD"));

        Response r = cl.login();
        // --- use this for 2-Factor Auth ---
        // Response r = cl.login("1234567");

        if (r.isSuccess()) {
            System.out.println("Login succeeded.");
            // perform further api request reusing the generated api session
            Map<String, Object> cmd = new HashMap<String, Object>();
            cmd.put("COMMAND", "StatusAccount");
            r = cl.request(cmd);
            if (r.isSuccess()) {
                System.out.println("Command succeeded.");
            } else {
                System.out.println("Command failed.");
            }
            // perform api logout and destroy api session
            r = cl.logout();
            if (r.isSuccess()) {
                System.out.println("Logout succeeded.");
            } else {
                System.out.println("Logout failed.");
            }
        } else {
            System.out.println("Login failed.");
        }
    }
}
