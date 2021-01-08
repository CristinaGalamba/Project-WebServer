import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class WebServerTest
{
    File root= new  File("TestSite");
    File fileHTML= new File(root,"typefile.html");
    File fileCSS= new File(root,"typefile.css");
    File fileJS= new File(root,"typefile.js");
    File fileN1= new File(root,"typefile.js..d");

    WebServer server;

    @Test
    public void test_getStatus()
    {
        //set the server state to 1 and check if the correct server state is returned
        server.setStateServer(1);
        assertEquals(1,server.getStateServer());

    }

    @Test
    public void test_getSocketClient() {
        Socket sock = new Socket();
        server.setClientSocket(sock);
        assertEquals(sock,server.getClientSocket());
    }


    @Before
    public void setup() {
        server=new WebServer();
    }

    @Test
    public void testHTML()
    {
        assertEquals("text/html",server.getFilePath(fileHTML));
    }

    @Test
    public void testCSS()
    {
        assertEquals("text/css",server.getFilePath(fileCSS));
    }

    @Test
    public void testJS()
    {
        assertEquals("text/js",server.getFilePath(fileJS));
    }

    @Test
    public void testBadFile()
    {
        assertEquals("text/html",server.getFilePath(fileN1));
    }

    @Test
    public void testPortUnderZero()
    {
        assertEquals(false,server.setPort(-1));
    }

    @Test
    public void testPortAboveMax()
    {
        assertEquals(false,server.setPort(66000));
    }


    @Test
    public void testSetPort1()
    {
        int port=0;
        server.setPort(port);
        assertEquals(false,server.acceptServerPort());
    }

    @Test
    public void test_acceptServerPort1()
    {
        server.setPort(10005);
        assertEquals(true,server.acceptServerPort());
    }

    @Test
    public void test_acceptServerPort2()
    {
        server.setPort(100000);
        assertEquals(false,server.acceptServerPort());
    }

    @Test
    public void test_acceptServerPort3()
    {
        server.setPort(-10);
        assertEquals(false,server.acceptServerPort());
    }

    @Test
    public void test_acceptServerPort4()
    {
        server.setPort(1024);
        assertEquals(false,server.acceptServerPort());
    }


    @Test
    public void test_acceptServerPort5()
    {
        server.setPort(65000);
        assertEquals(false,server.acceptServerPort());
    }

    @Test
    public void testListen()
    {
        server.setPort(3600);
        server.acceptServerPort();
        int srv = server.conectionClient;
        assertEquals(0,srv);
    }
}
