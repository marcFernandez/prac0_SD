package utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.util.*;
import java.util.logging.Logger;

public class ComUtilsService {
    private ComUtils comUtils;

    private final static Logger LOGGER = Logger.getLogger(ComUtilsService.class.getName());

    public ComUtilsService(InputStream inputStream, OutputStream outputStream) throws IOException {
        comUtils = new ComUtils(inputStream, outputStream);
        //LOGGER.setLevel(Level.INFO);
    }

    public String writeTest() {
        //TODO: put your code here
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter a string or integer:");

        String inputStr = myObj.nextLine();  // Read user input
        String type = "string";

        int inputNum = 0;

        try{
            inputNum = Integer.parseInt(inputStr);
            //System.out.println("Input is an integer: " + inputNum);  // Output user input
            LOGGER.info("Input is an integer: " + inputNum);
            if(inputNum > 2147483647 || inputNum < -2147483648){
                //System.out.println("The integer is too big to be determined with 32 bits, so it will be assumed as a string.");
                LOGGER.warning("The integer is too big to be determined with 32 bits, so it will be assumed as a string.");
            }else{
                type = "int";
            }
        }catch(NumberFormatException nfe){
            //System.out.println("Parsing error: "+nfe.getMessage());
            //System.out.println("Input is a string: " + inputStr);  // Output user input
            LOGGER.info("Input is a string: " + inputStr);
        }

        if(type == "string"){
            try{
                comUtils.write_string(inputStr);
                //System.out.println("String written.");
                LOGGER.info("String written.");
                return type;
            }catch (IOException ioe){
                //System.out.println("Error writting the string: "+ioe.getMessage());
                LOGGER.warning("Error writting the string: "+ioe.getMessage());
            }
        }else{
            try{
                comUtils.write_int32(inputNum);
                //System.out.println("Int written.");
                LOGGER.info("Int written.");
                return type;
            }catch (IOException ioe){
                //System.out.println("Error writting the integer: "+ioe.getMessage());
                LOGGER.warning("Error writting the integer: "+ioe.getMessage());
            }
        }

        return "error";

    }

    public String readTest(String type) {
        String result = "";
        //TODO: put your code here

        if(type == "int") {
            try {
                result = Integer.toString(comUtils.read_int32());
            } catch (IOException ioe) {
                //System.out.println("Error reading the integer: " + ioe.getMessage());
                LOGGER.warning("Error reading the integer: " + ioe.getMessage());
            }
        }else{
            try {
                result = comUtils.read_string();
            } catch (IOException ioe) {
                //System.out.println("Error reading the string: " + ioe.getMessage());
                LOGGER.warning("Error reading the string: " + ioe.getMessage());
            }
        }

        return result;
    }

}
