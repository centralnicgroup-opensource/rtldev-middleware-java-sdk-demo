package net.hexonet.testapiconnector;

import net.hexonet.apiconnector.Client;
import net.hexonet.apiconnector.ListResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {

        // perform an api login and create an api session
        Map<String, String> cfg = new HashMap<String, String>();
        cfg.put("login", "test.user");
        cfg.put("pw", "test.passw0rd");
        cfg.put("entity", "1234");
        // --- use this for 2-Factor Auth ---
        // cfg.put("otp", "my_otp_code");
        // --- use this if you have active ip filter settings ---
        // cfg.put("remoteaddr", "client's remote ip address");
        Client cl = new Client();
        ListResponse r = cl.login(cfg);

        if (r.isSuccess()) {
            System.out.println("Login succeeded.");
            // perform further api request reusing the generated api session
            Map<String, String> cmd = new HashMap<String, String>();
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
