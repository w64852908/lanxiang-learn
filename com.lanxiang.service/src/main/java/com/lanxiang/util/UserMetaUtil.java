package com.lanxiang.util;

import com.lanxiang.model.Address;
import com.lanxiang.model.User;
import com.lanxiang.model.dto.AddressDTO;
import com.lanxiang.model.dto.UserDTO;

import java.util.UUID;

/**
 * Created by lanxiang on 16/9/2.
 */
public class UserMetaUtil {

    public static void setUserFromUserDTO(User user, UserDTO userDTO) {
        if (user == null || userDTO == null) {
            return;
        }

        if (userDTO.getId() != null) {
            user.setId(userDTO.getId());
        } else {
            user.generateId();
        }

        if (userDTO.getName() != null) {
            user.setName(userDTO.getName());
        }

        if (userDTO.getPassword() != null) {
            user.setPassword(userDTO.getPassword());
        }

        if (userDTO.getSex() != null) {
            user.setSex(userDTO.getSex());
        }

        user.setAge(userDTO.getAge());

        user.setStatus(userDTO.getStatus());

        Address address = getAddressFromAddressDTO(userDTO.getAddressDTO());

        address.setUserId(user.getId());

        user.setAddress(address);
    }

    private static Address getAddressFromAddressDTO(AddressDTO addressDTO) {

        Address address = new Address();
        if (addressDTO == null) {
            return address;
        }

        if (addressDTO.getId() != null) {
            address.setId(addressDTO.getId());
        } else {
            address.generateId();
        }

        if (addressDTO.getUserId() != null) {
            address.setUserId(addressDTO.getUserId());
        }

        if (addressDTO.getProvince() != null) {
            address.setProvince(addressDTO.getProvince());
        }

        if (addressDTO.getCity() != null) {
            address.setCity(addressDTO.getCity());
        }

        if (addressDTO.getTown() != null) {
            address.setTown(addressDTO.getTown());
        }

        if (addressDTO.getDetail() != null) {
            address.setDetail(addressDTO.getDetail());
        }
        return address;
    }
}
