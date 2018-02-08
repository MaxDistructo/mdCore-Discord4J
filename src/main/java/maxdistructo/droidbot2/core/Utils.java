package maxdistructo.droidbot2.core;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.json.*;
import sx.blah.discord.handle.obj.*;

public class Utils {

    private final static Path currentRelativePath = Paths.get("");
    public final static String s = currentRelativePath.toAbsolutePath().toString();

    public static void restartApplication()
    {
        final String javaBin = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
        File currentJar = null;
        try {
            currentJar = new File(Utils.class.getProtectionDomain().getCodeSource().getLocation().toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

  /* is it a jar file? */
        if(!currentJar.getName().endsWith(".jar"))
            return;

  /* Build command: java -jar application.jar */
        final ArrayList<String> command = new ArrayList<String>();
        command.add(javaBin);
        command.add("-jar");
        command.add(currentJar.getPath());

        final ProcessBuilder builder = new ProcessBuilder(command);
        try {
            builder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    public static String makeNewString(Object[] input, int startAt){
        StringBuilder stringBuilder = new StringBuilder();
        int i = startAt;
        while (i < input.length) {
            if(i - 1 == input.length) {
                stringBuilder.append(input[i]);
            }
            else{
                stringBuilder.append(input[i]);
                stringBuilder.append(" ");
            }
            i++;
        }
        return stringBuilder.toString();
    }

    public static long convertToLong(Object o){
        return Long.valueOf(o.toString());
    }

    public static int convertToInt(Object in){
        return Integer.valueOf(in.toString());
    }
    
    public static String[] toStringArray(JSONArray array) {
    if(array==null)
        return null;

    String[] arr=new String[array.length()];
    for(int i=0; i<arr.length; i++) {
        arr[i]=array.optString(i);
    }
    return arr;
}
    public static IChannel getMentionedChannel(IMessage message){
        
        List<IChannel> mentionedChannelList = message.getChannelMentions();
            Object[] mentionedChannelArray = mentionedChannelList.toArray();
            IChannel channelMention;
            if (mentionedChannelArray.length > 0) {
                channelMention = (IChannel) mentionedChannelArray[0];
            } else {
                channelMention = null;
            }
        return channelMention;
    }
    
    public static IUser getMentionedUser(IMessage message){
            
            List<IUser> mentionedList = message.getMentions();
            Object[] mentionedArray = mentionedList.toArray();
            IUser mentioned;
            if (mentionedArray.length > 0) {
                mentioned = (IUser) mentionedArray[0];
            } else {
                mentioned = null;
            }
        return mentioned;
    }
    
    public static IMessage.Attachment getAttachement(IMessage message){
    
        List<IMessage.Attachment> attachments = null;
        attachments = message.getAttachments();
        return attachments.get(0);
    }
    
    public static String getAttachementUrl(IMessage message){
        List<IMessage.Attachment> attachments = null;
        attachments = message.getAttachments();
        return attachments.get(0).getUrl();
    }
    
    public static String makeNewLineString(Object[] input, int startAt){
        StringBuilder stringBuilder = new StringBuilder();
        int i = startAt;
        while (i < input.length) {
                stringBuilder.append(input[i]);
                stringBuilder.append("\n");
            i++;
        }
        return stringBuilder.toString();
    }
    
    

}
