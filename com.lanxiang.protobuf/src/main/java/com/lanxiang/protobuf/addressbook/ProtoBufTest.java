package com.lanxiang.protobuf.addressbook;

import com.lanxiang.protobuf.AddressBookProtos.Person;
import org.junit.Test;

/**
 * Created by lanxiang on 2016/10/8.
 */
public class ProtoBufTest {
    @Test
    public void test() {
        Person john = Person.newBuilder()
                .setId(1234)
                .setName("John Doe")
                .setEmail("273109445@qq.com")
                .addPhone(Person.PhoneNumber.newBuilder()
                        .setNumber("555-4321")
                        .setType(Person.PhoneType.HOME))
                .build();
        System.out.println(john.getName());
    }

    @Test
    public void test2() {
        System.out.println("test 2");
    }
}
