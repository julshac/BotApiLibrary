package AuditBot;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class LogElement {

    public enum Actions {
    insert, update, delete, select, set,
        create, grant, revoke, alter, show, drop
    }

    LocalDateTime time;
    String value, user, duration;
    Actions action;

    public LogElement(String value) {
       try (FileWriter writer = new FileWriter("C:\\Temp\\logTime.txt", true)){
           value.contains(Actions.values().toString());
           writer.write(String.valueOf(LocalDateTime.now()) + "\n" + Selection(value));
           writer.flush();
       } catch(IOException ex){
               System.out.println(ex.getMessage());
       }
    }

    public static String Selection(String value) {
        int indexStart = value.indexOf("LOG");
        int indexEnd = value.lastIndexOf("LOG", indexStart);
        String resStr = value.substring(indexStart, indexEnd);
        return resStr;
    }

}
