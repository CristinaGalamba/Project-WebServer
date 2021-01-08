import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

public class WebTest {

    public int responseCode() {
        try {
            URL myUrl = new URL("http://localhost:3600");
            HttpURLConnection connection = (HttpURLConnection)myUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            return connection.getResponseCode();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
    private String sendStrig, recString, expected;
    private WebTest web;


    public String httpUrlConnection(String desiredUrl)
            throws Exception
    {
        URL url = new URL(desiredUrl);
        BufferedReader reader = null;
        StringBuilder stringBuilder;

        try
        {

            url = new URL(desiredUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();


            connection.setRequestMethod("GET");

            connection.setReadTimeout(15*1000);
            connection.connect();


            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            stringBuilder = new StringBuilder();

            String line = null;
            while ((line = reader.readLine()) != null)
            {
                stringBuilder.append(line + "\n");
            }
            return stringBuilder.toString();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw e;
        }
        finally
        {

            if (reader != null)
            {
                try
                {
                    reader.close();
                }
                catch (IOException ioe)
                {
                    ioe.printStackTrace();
                }
            }
        }
    }
}