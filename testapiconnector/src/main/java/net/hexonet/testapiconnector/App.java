package net.hexonet.testapiconnector;

import java.util.HashMap;
import java.util.Map;
import net.hexonet.apiconnector.APIClient;
import net.hexonet.apiconnector.Response;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {

        // perform an api login and create an api session
        APIClient cl = new APIClient();
        cl.useOTESystem().setCredentials("test.user", "test.passw0rd")
                // --- use this if you have active ip filter settings ---
                .setRemoteIPAddress("1.2.3.4");

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
