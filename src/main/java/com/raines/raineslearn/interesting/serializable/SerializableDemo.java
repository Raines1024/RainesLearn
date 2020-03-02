package com.raines.raineslearn.interesting.serializable;

import org.apache.tomcat.util.http.fileupload.IOUtils;

import java.io.*;
import java.util.Date;

public class SerializableDemo {

    public static void main(String[] args) {
        //Initializes The Object
        User user = new User();
        user.setName("test");
        user.setGender("male");
        user.setAge(23);
        user.setBirthday(new Date());
        System.out.println(user);

        //Write Obj to File
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("tempFile.txt"));
            oos.writeObject(user);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //Read Obj from File
        File file = new File("tempFile.txt");
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(file));

            User newUser = (User) ois.readObject();
            System.out.println(newUser);
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

    //将InputStream转换为String
    static String convertStreamToString(java.io.InputStream is) {
        System.out.println(is);
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

}
/**
 print
 User{name='test', age=23, gender=male, birthday=Sun Jun 02 18:15:17 CST 2019}
 User{name='test', age=23, gender=null, birthday=Sun Jun 02 18:15:17 CST 2019}
 **/
