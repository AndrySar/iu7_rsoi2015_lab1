package jetty_server;

import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.client.HttpClient;
import Example.*;
import Authorization.*;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.http.HttpMethod;


/**
 * Created by user on 23.10.15.
 */
public class jetty_server
{
    public static String acccess_token;
    public static void main(String[] args) throws Exception
    {
        Server server = new Server(5000);
        Authorization oath = new Authorization();

        ContextHandler context1 = new ContextHandler("/oath/autho");
        context1.setHandler( new GetCode(oath));

        ContextHandler context2 = new ContextHandler("/oath/code");
        context2.setHandler(new GetAccessToken(oath));

        ContextHandler context3 = new ContextHandler("/oath/accessToken");
        context3.setHandler( new ReadAccessToken(oath));

        ContextHandler context4 = new ContextHandler("/me");
        context4.setHandler( new GetMeClass(oath));

        ContextHandlerCollection contexts = new ContextHandlerCollection();
        contexts.setHandlers(new Handler[]{context1, context2, context3, context4});
        server.setHandler(contexts);

        server.start();
        server.join();



    }
}