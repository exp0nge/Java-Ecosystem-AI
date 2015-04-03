import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * Created by MD on 3/31/2015.
 */
public class Main {
    public static void main(String[] args) {
        Ecosystem e1 = new Ecosystem();
        for (int i = 0; i < 31; i++) {
            if(i == 0)
                continue;
            String fileName = "C:\\Users\\unid\\SkyDrive\\Classes\\sftwre design lab\\HW4_Ecosystem\\src\\stages\\" + i + ".txt";
            try{
                File file = new File(fileName);
                file.createNewFile();
                FileOutputStream fos = new FileOutputStream(file);
                PrintStream ps = new PrintStream(fos);
                System.setOut(ps);
                e1.print();

            }catch(IOException e){
                e.printStackTrace();
            }

            e1.addTime();
        }





    }
}
