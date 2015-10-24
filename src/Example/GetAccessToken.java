package Example;

/**
 * Created by user on 24.10.15.
 */
import org.eclipse.jetty.client.HttpExchange;
import org.eclipse.jetty.client.api.ContentProvider;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.api.Response;
import org.eclipse.jetty.http.HttpMethod;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.HttpRequest;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URL;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.handler.AbstractHandler;
import Authorization.*;
import jetty_server.*;

public class GetAccessToken extends AbstractHandler
{
    private Authorization oath;
    public GetAccessToken(Authorization param)
    {
        this.oath = param;
    }

    public void handle( String target,
                        Request baseRequest,
                        HttpServletRequest request,
                        HttpServletResponse response ) throws IOException,
            ServletException
    {
        response.setContentType("text/html; charset=utf-8");

        Map<String, String> map = Authorization.queryToMap(request.getQueryString());

        String result_request = "";

        if(map.get("code") != null)
        {
               /* HttpClient client = new HttpClient();
                org.eclipse.jetty.client.api.Request res = client.POST(oath.GetAccessTocken(map.get("code")));
                HttpClient httpClient = new HttpClient();
                httpClient.start();
                String str = oath.GetAccessTocken(map.get("code"));
               ContentResponse wer = httpClient.newRequest(str).method(HttpMethod.POST).send();

                System.out.print(wer.getContent());
                httpClient.stop();*/

                try {

                    URL currentURL = new URL(oath.GetAccessTocken(map.get("code")));
                    HttpsURLConnection con = (HttpsURLConnection) currentURL.openConnection();
                    con.setRequestMethod("POST");
                    con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
                    InputStream ins = con.getInputStream();
                    InputStreamReader isr = new InputStreamReader(ins);
                    BufferedReader in = new BufferedReader(isr);


                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        result_request += inputLine;
                    }

                    Map<String, String> res_map = Authorization.queryToMap(result_request);

                    jetty_server.acccess_token = res_map.get("access_token");

                    response.setStatus(HttpServletResponse.SC_OK);
                    PrintWriter out = response.getWriter();
                    out.println("access_token=" + res_map.get("access_token"));
                    out.close();

                } catch (Exception e) {

                    response.setContentType("text/html; charset=utf-8");
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    PrintWriter out = response.getWriter();
                    out.println("<h1>" + "Error" + "</h1>");
                    out.close();
                }

        }else
        {
            response.setContentType("text/html; charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            PrintWriter out = response.getWriter();
            out.println("<h1>" + "Error" + "</h1>");
            out.close();
        }


        //PrintWriter out = response.getWriter();

      //  out.println("<h1>" + "GetCode" + "</h1>");

       // response.sendRedirect(this.oath.GetURIforCode());

        baseRequest.setHandled(true);
    }
}
