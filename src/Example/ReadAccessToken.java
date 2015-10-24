package Example;

/**
 * Created by user on 24.10.15.
 */
import org.eclipse.jetty.client.HttpExchange;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.client.HttpClient;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import Authorization.*;

public class ReadAccessToken extends AbstractHandler
{
    private Authorization oath;
    private URI serverUri;
    public ReadAccessToken(Authorization param)
    { this.oath = param;            }

    public void handle( String target,
                        Request baseRequest,
                        HttpServletRequest request,
                        HttpServletResponse response ) throws IOException,
            ServletException
    {
        response.setContentType("text/html; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);

        Map<String, String> map = Authorization.queryToMap(request.getQueryString());

        if(map.get("access_token") != null)
        {

            response.setContentType("text/html; charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
            PrintWriter out = response.getWriter();
            out.println("<h1>" + "Access_Token=" + map.get("access_token") + "</h1>");

        }else
        {
            response.setContentType("text/html; charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            PrintWriter out = response.getWriter();
            out.println("<h1>" + "No Access_Token" + "</h1>");
        }

        baseRequest.setHandled(true);
    }
}

