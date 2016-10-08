package com.lanxiang.protobuf.writeandread;

import com.lanxiang.protobuf.AddressBookProtos.Person;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

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

            }
        }
    }
}
