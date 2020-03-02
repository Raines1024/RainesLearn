package com.raines.raineslearn.interesting.serializable;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SerializableDemoList {

    public static void main(String[] args) {
        //Initializes The list
        List<String> list = new ArrayList<String>();
        list.add("hello");
        list.add("world");
        list.add("test");
        System.out.println("init list" + list);

        //Write list to File
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("tempFile"));
            oos.writeObject(list);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //Read list from File
        File file = new File("tempFile");
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(file));
            List<String> newlist = (List<String>) ois.readObject();
            System.out.println("newlist" + newlist);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                ois.close();
                file.delete();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
/**
 print
 init list[hello, world, test]
 newlist[hello, world, test]
 **/