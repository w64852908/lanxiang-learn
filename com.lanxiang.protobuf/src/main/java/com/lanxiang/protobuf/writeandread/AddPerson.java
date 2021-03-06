package com.lanxiang.protobuf.writeandread;

import com.lanxiang.protobuf.AddressBookProtos.Person;
import com.lanxiang.protobuf.AddressBookProtos.AddressBook;


import java.io.*;

/**
 * Created by lanxiang on 2016/10/8.
 */
public class AddPerson {

    static Person PromptForAddress(BufferedReader br, PrintStream ps) throws IOException {
        Person.Builder person = Person.newBuilder();

        ps.print("Enter person ID: ");
        person.setId(Integer.valueOf(br.readLine()));

        ps.print("Enter name: ");
        person.setName(br.readLine());

        ps.print("Enter email address (blank for none): ");
        String email = br.readLine();
        if (email.length() > 0) {
            person.setEmail(email);
        }

        while (true) {
            ps.print("Enter a phone number (or leave blank to finish): ");
            String number = br.readLine();
            if (number.length() == 0) {
                break;
            }

            Person.PhoneNumber.Builder phoneNumber = Person.PhoneNumber.newBuilder().setNumber(number);

            ps.print("Is this a mobile, home or work phone? ");
            String type = br.readLine();
            if (type.equals("mobile")) {
                phoneNumber.setType(Person.PhoneType.MOBILE);
            } else if (type.equals("home")) {
                phoneNumber.setType(Person.PhoneType.HOME);
            } else if (type.equals("work")) {
                phoneNumber.setType(Person.PhoneType.WORK);
            } else {
                ps.print("Unknown phone type. Using default.");
            }

            person.addPhone(phoneNumber);
        }

        return person.build();
    }

    //从文件读取所有通讯录信息,根据用户的输入添加一个通讯录,写回到文件中
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.err.println("Usage: AddPerson ADDRESS_BOOK_FILE");
            System.exit(-1);
        }

        AddressBook.Builder addressBook = AddressBook.newBuilder();

        try {
            addressBook.mergeFrom(new FileInputStream(args[0]));
        } catch (FileNotFoundException e) {
            System.out.println(args[0] + ": File not found. Creating a new file.");
        }

        addressBook.addPerson(
                PromptForAddress(new BufferedReader(new InputStreamReader(System.in)),
                        System.out));

        FileOutputStream fos = new FileOutputStream(args[0]);
        addressBook.build().writeTo(fos);
        fos.close();
    }
}
