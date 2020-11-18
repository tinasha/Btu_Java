package ge.edu.btu.chat.client;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class client {

    public static void main(String args[])
            throws IOException, InterruptedException
    {

        DatagramSocket cs
                = new DatagramSocket(8986);
        InetAddress ip
                = InetAddress.getLocalHost();
        System.out.println("დასვი კითხვა");

        Thread csend;
        csend = new Thread(new Runnable() {
            @Override
            public void run()
            {
                try {
                    Scanner sc = new Scanner(System.in);
                    while (true) {
                        synchronized (this)
                        {
                            byte[] sd = new byte[1000];

                            sd = sc.nextLine().getBytes();


                            DatagramPacket sp
                                    = new DatagramPacket(
                                    sd,
                                    sd.length,
                                    ip,
                                    8986);

                            cs.send(sp);

                            String msg = new String(sd);

                            if (msg.equals("ნახვამდის")) {
                                System.out.println("client "
                                        + "exiting... ");
                                break;
                            }

                        }
                    }
                }
                catch (IOException e) {
                    System.out.println("შეცდომა დაფიქსირდა");
                }
            }
        });

        Thread creceive;
        creceive = new Thread(new Runnable() {
            @Override
            public void run()
            {
                try {

                    while (true) {
                        synchronized (this)
                        {

                            byte[] rd = new byte[1000];

                            DatagramPacket sp1
                                    = new DatagramPacket(
                                    rd,
                                    rd.length);
                            cs.receive(sp1);

                            String msg = (new String(rd)).trim();
                            System.out.println("Server: " + msg);

                            if ((msg).equals("მაჩვენე კურსი")) {
                                System.out.println("2.97... ");
                            }
                            else if ((msg).equals("მაჩვენე ფილიალები")){
                                System.out.println("ი.ჭავჭავაძის,"+
                                        "ვაჭა-შაველას," +
                                        "და" +
                                        "გურამიშვილის " +
                                        "გამზირებზე");
                            }
                            else if (msg.equals("ნახვამდის")) {
                                System.out.println("Server"
                                        + " Stopped....");
                                break;
                            }
                            else {
                                System.out.println("მთლად მასეთი განათლებულიც არ ვარ");
                            }
                        }
                    }
                }
                catch (IOException e) {
                    System.out.println("შეცდომა დაფიქსირდა");
                }
            }
        });

        csend.start();
        creceive.start();

        csend.join();
        creceive.join();
        cs.close();
    }
}
