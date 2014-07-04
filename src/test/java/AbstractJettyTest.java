import com.sun.jersey.spi.spring.container.servlet.SpringServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;
import org.junit.After;
import org.junit.Before;

public class AbstractJettyTest {

    private Server server;

    @Before
    public final void startJetty() throws Exception {
        server = new Server(8080);
        ServletContextHandler contextHandler = new ServletContextHandler(server,"/", ServletContextHandler.SESSIONS);
        ServletHolder servlet = new ServletHolder(new SpringServlet());
        contextHandler.addServlet(servlet, "/*");

        WebAppContext context = new WebAppContext();

//        final String projectRoot = "/home/dev/projects/spikes/mockrestservice";
        final String projectRoot = "/home/dev/stuff/spikes2/web-service-swagger";
        context.setDescriptor(projectRoot +"/src/main/webapp/WEB-INF/web.xml");
        context.setResourceBase(projectRoot + "/src/main/webapp");
        context.setContextPath("/");
        context.setParentLoaderPriority(true);

        server.setHandler(context);
        server.start();
    }

    @After
    public final void stopJetty() throws Exception {
        server.stop();
    }
}
