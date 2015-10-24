package Example;

/**
 * Created by user on 24.10.15.
 */

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.HttpRequest;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.http.HttpMethod;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import Authorization.*;
import jetty_server.*;

public class GetMeClass extends AbstractHandler
{
    private Authorization oath;
    public GetMeClass(Authorization param)
    {
        this.oath = param;
    }

    public void handle( String target,
                        Request baseRequest,
                        HttpServletRequest request,
                        HttpServletResponse response ) throws IOException,
            ServletException
    {


        String req = oath.GetMe(jetty_server.acccess_token);

        String resp = Authorization.Send_Request(req);

    /*    try{
            HttpClient client = new HttpClient();
            client.start();
            ContentResponse res = client.POST(req).method(HttpMethod.GET).send();

            response.setContentType("text/html; charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            PrintWriter out = response.getWriter();
            out.println(res.getContent());
            out.close();

            client.stop();

        }catch (Exception e)
        {
            response.setContentType("text/html; charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
            PrintWriter out = response.getWriter();
            out.println("Error");
            out.close();
        }

*/
        response.setContentType("text/html; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        PrintWriter out = response.getWriter();
        out.println(resp);
        out.close();

        baseRequest.setHandled(true);
    }
}
