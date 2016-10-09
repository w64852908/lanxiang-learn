package com.lanxiang.protobuf.writeandread;

import com.lanxiang.protobuf.AddressBookProtos.AddressBook;
import com.lanxiang.protobuf.AddressBookProtos.Person;
import com.lanxiang.protobuf.AddressBookProtos.Person.PhoneNumber;

import java.io.FileInputStream;

/**
 * Created by lanxiang on 2016/10/8.
 */
public class ListPeople {

    static void Print(AddressBook addressBook) {
        for (Person person : addressBook.getPersonList()) {
            System.out.println("Person ID: " + person.getId());
            System.out.println("  Name: " + person.getName());
            if (person.hasEmail()) {
                System.out.println("  E-mail address: " + person.getEmail());
            }

            for (PhoneNumber phoneNumber : person.getPhoneList()) {
                switch (phoneNumber.getType()) {
                    case MOBILE:
                        System.out.print("  Mobile phone #: ");
                        break;
                    case HOME:
                        System.out.print("  Home phone #: ");
                        break;
                    case WORK:
                        System.out.print("  Work phone #: ");
                        break;
                }
                System.out.println(phoneNumber.getNumber());
            }
        }
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.err.println("Usage: ListPeople ADDRESS_BOOK FILE");
            System.exit(-1);
        }

        AddressBook addressBook = AddressBook.parseFrom(new FileInputStream(args[0]));

        Print(addressBook);
    }
}
