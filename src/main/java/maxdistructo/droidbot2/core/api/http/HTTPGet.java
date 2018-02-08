package maxdistructo.droidbot2.core.api.http;

import org.apache.commons.io.FileUtils;
import org.json.*;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;

import static maxdistructo.droidbot2.core.Utils.s;

public class HTTPGet{

public static JSONObject from(URL url){ //Input get URL, output JSONObject for manipulation
  File file = new File(s + "/droidbot/tmp/get.json");
  file.mkdirs(); //Creates new file in the directory specified, Allows for any installation to use this.
    try {
        file.createNewFile();
    } catch (IOException e) {
        e.printStackTrace();
    }
    try {
        FileUtils.copyURLToFile(url, file); //Get Data from Server
    } catch (IOException e) {
        e.printStackTrace();
    }
    URI uri = file.toURI();//Read TMP file from Server
       JSONTokener tokener = null;
       try {
           tokener = new JSONTokener(uri.toURL().openStream());
           System.out.println("Successfully read file config.txt");
       } catch (IOException e) {
           //Message.sendDM(Client.client.getApplicationOwner(), e.toString());
           e.printStackTrace();
       }
       file.delete();
       return new JSONObject(tokener);
}

}
