package Example;

/**
 * Created by user on 24.10.15.
 */

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

public class GetCode extends AbstractHandler
{
    private Authorization oath;
    public GetCode(Authorization param)
    { this.oath = param;            }

    public void handle( String target,
                        Request baseRequest,
                        HttpServletRequest request,
                        HttpServletResponse response ) throws IOException,
            ServletException
    {
        response.setContentType("text/html; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);

     //   PrintWriter out = response.getWriter();

       // out.println("<h1>" + "GetCode" + "</h1>");

        response.sendRedirect(this.oath.GetURIforCode());

        baseRequest.setHandled(true);
    }
}
