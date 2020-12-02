package ge.edu.btu.SearchFile;

import java.io.*;
import java.util.Scanner;

public class Search {
    public static void main(String[] args) throws InterruptedException {
        File dir = new File("C:/Users/Dazai/Desktop/New folder/რესურსები/BTU_DOCUMENT");

        Scanner sc = new Scanner(System.in);
        System.out.println("შემოივანე საძიებო სიტყვა:");
        String sc1 = sc.nextLine();
        FilenameFilter filter = new FilenameFilter() {
            public boolean accept (File dir, String name) {
                return name.startsWith(sc1);
            }
        };

        String[] children = dir.list(filter);
        if (children == null) {
            System.out.println("Either dir does not exist or is not a directory");
        } else {
            for (int i = 0; i< children.length; i++) {
                String filename = children[i];
                System.out.println(filename);
            }
            while (true){
                int filecount = dir.list().length;
                System.out.println(filecount);
                Thread.sleep(3000);
            }
        }

    }
}