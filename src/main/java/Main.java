import java.io.*;

import utils.*;
import clases.*;

public class Main {

    public static void main(String[] args) {
        File file = new File("test");

        String inputType;
        try {
            file.createNewFile();
            ComUtilsService comUtilsService = new ComUtilsService(new FileInputStream(file), new FileOutputStream(file));
            inputType = comUtilsService.writeTest();
            System.out.println(comUtilsService.readTest(inputType));
        } catch (IOException e) {
            System.out.println("Error Found during Operation:" + e.getMessage());
            e.printStackTrace();
        }

        String[] clientParams = {"localhost", "1234"};

        Client client = new Client();

        client.main(clientParams);

    }

}