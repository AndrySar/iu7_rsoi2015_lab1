package Authorization;

import com.sun.net.httpserver.HttpExchange;
import jetty_server.jetty_server;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 10.10.15.
 */
public class Authorization {
    public String GetURIforCode()
    {
        String httpURL = "";
        try {
                httpURL = "https://github.com/login/oauth/authorize?client_id=32ab75ec177d1d73da83&redirect_uri=http://localhost:5000/oath/code";

        } catch (Exception e) {
            System.out.print(e.getMessage());
        }finally {
            return httpURL;
        }
    }

    public String GetAccessTocken(String code)
    {
       // String inputLine = "";
       // String str = "";
        String httpURL = "";
        try {
             httpURL = "https://github.com/login/oauth/access_token?" +
                    "client_id=32ab75ec177d1d73da83" +
                    "&client_secret=e72e22e9d78b6fbbdf80572132aa9ededceb2bcf" +
                    "&code=" + code +
                    "&redirect_uri=http://localhost:5000/oath/code";

        /*    URL currentURL = new URL(httpURL);
            HttpsURLConnection con = (HttpsURLConnection) currentURL.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            InputStream ins = con.getInputStream();
            InputStreamReader isr = new InputStreamReader(ins);
            BufferedReader in = new BufferedReader(isr);



            while ((inputLine = in.readLine()) != null) {
                // System.out.println(inputLine);
                str += inputLine;
            }*/
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return httpURL;
    }

    public String GetMe(String Access_Token)
    {
        return "https://api.github.com/user?access_token=" + Access_Token;
    }

    public String State()
    {
        String inputLine = "";
        String str = "";
        try {
            String httpURL = "https://github.com/status/";

            URL currentURL = new URL(httpURL);
            HttpsURLConnection con = (HttpsURLConnection) currentURL.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            InputStream ins = con.getInputStream();
            InputStreamReader isr = new InputStreamReader(ins);
            BufferedReader in = new BufferedReader(isr);


            while ((inputLine = in.readLine()) != null) {
                str += inputLine;
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return str;
    }

    public static Map<String, String> queryToMap(String query) {
        Map<String, String> result = new HashMap<String, String>();
        for (String param : query.split("&")) {
            String pair[] = param.split("=");
            if (pair.length > 1) {
                result.put(pair[0], pair[1]);
            } else {
                result.put(pair[0], "");
            }
        }
        return result;
    }

    public static String Send_Request(String target_url)
    {
        String result_request = "";
        try {

            URL currentURL = new URL(target_url);
            HttpsURLConnection con = (HttpsURLConnection) currentURL.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            InputStream ins = con.getInputStream();
            InputStreamReader isr = new InputStreamReader(ins);
            BufferedReader in = new BufferedReader(isr);

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                result_request += inputLine;
            }

        } catch (Exception e) {
            result_request = e.getMessage();
        }finally {
            return result_request;
        }
    }

}
