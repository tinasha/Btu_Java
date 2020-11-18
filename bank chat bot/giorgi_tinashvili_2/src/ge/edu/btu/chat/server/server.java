package ge.edu.btu.chat.server;
import java.net.*;
import java.io.*;
import java.util.*;

public class server {

    public static void main(String args[])
            throws IOException, InterruptedException
    {

        DatagramSocket ss = new DatagramSocket(8987);
        InetAddress ip = InetAddress.getLocalHost();

        System.out.println("სერვერი მზადყოფნაშია");

        Thread ssend;
        ssend = new Thread(new Runnable() {
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
                                    8987);

                            ss.send(sp);

                            String msg = new String(sd);
                            System.out.println("Sam: "
                                    + msg);
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

                            else if ((msg).equals("ნახვამდის")) {
                                System.out.println("Sam"
                                        + " გემშვიდდობება ");
                                break;
                            }
                            else {
                                System.out.println("მთლად მასეთი განათლებულიც არ ვარ");
                            }

                        }
                    }
                }
                catch (Exception e) {
                    System.out.println("შეცდომა დაფიქსირდა");
                }
            }
        });

        Thread sreceive;
        sreceive = new Thread(new Runnable() {
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
                            ss.receive(sp1);

                            String msg
                                    = (new String(rd)).trim();
                            System.out.println("Client ("
                                    + sp1.getPort()
                                    + "):"
                                    + " "
                                    + msg);

                            if (msg.equals("ნახვამდის")) {
                                System.out.println("Client"
                                        + " connection closed.");
                                break;
                            }
                        }
                    }
                }
                catch (Exception e) {
                    System.out.println("შეცდომა დაფიქსირდა");
                }
            }
        });

        ssend.start();
        sreceive.start();

        ssend.join();
        sreceive.join();
        ss.close();
    }
}
