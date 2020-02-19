package clases;

import java.io.*;
import java.net.*;

public class Servidor {

  public static void main(String[] args) {
	ServerSocket serverSocket=null;


    Socket socket=null;
 
    ComUtils comUtils;

    int portServidor = 1234;
    int value;

    if (args.length > 1)
    {
      System.out.println("Us: java Servidor [<numPort>]");
      System.exit(1);
    }

    if (args.length == 1)
      portServidor = Integer.parseInt(args[0]);
    
	try {   
      /* Creem el servidor */
      serverSocket = new ServerSocket(portServidor);
      System.out.println("Servidor socket preparat en el port " + portServidor);

      while (true) {
		System.out.println("Esperant una connexió d'un client.");

        /* Esperem a que un client es connecti amb el servidor */
		socket = serverSocket.accept();
		System.out.println("Connexió acceptada d'un client.");

        /* Associem un flux d'entrada/sortida amb el client */ 
        comUtils = new ComUtils(socket);

        /* Ens esperem a rebre un valor del client */
        value = comUtils.read_int32();
        System.out.println("He rebut el valor " + value + " del client, ara li envio un 22");

        /* Enviem un 12 al client */
        comUtils.write_int32(22);

        /* Tornem a llegir un sencer */
        value = comUtils.read_int32();

        System.out.println("He rebut un " + value + " del client, ara li responc amb un missatge d'error");

        /* Enviem el missatge d'error */
        comUtils.write_int32(99);

        /* Com es que client no rep tot el missatge ? */
        comUtils.write_string("Aixo es un exemple de practiques de SD. Bip bip bip bip bip.");
      } // fi del while infinit
    } // fi del try
    catch (IOException ex) {
      System.out.println("Els errors han de ser tractats correctament pel vostre programa");
    } // fi del catch
    finally 
    {
        /* Tanquem la comunicacio amb el client */
		try {
			if(serverSocket != null) serverSocket.close();
		}
		catch (IOException ex) {
			System.out.println("Els errors han de ser tractats correctament pel vostre programa");
		} // fi del catch
	}
	
  } // fi del main
} // fi de la classe
