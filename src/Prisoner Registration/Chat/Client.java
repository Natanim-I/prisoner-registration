package sample.Chat;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[]args){
        Socket chatSocket = null;
        InputStreamReader Input = null;
        OutputStreamWriter Output = null;
        BufferedReader Read = null;
        BufferedWriter Write = null;

        try{
            chatSocket = new Socket("localhost",1234);
            Input = new InputStreamReader(chatSocket.getInputStream());
            Output = new OutputStreamWriter(chatSocket.getOutputStream());
            Read = new BufferedReader(Input);
            Write = new BufferedWriter(Output);

            Scanner Scan = new Scanner(System.in);
            while(true){
                String msgSend = Scan.nextLine();
                Write.write(msgSend);
                Write.flush();

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
