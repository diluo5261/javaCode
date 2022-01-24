package com.dilo.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class School {

    @Value("天津")
    private String address;
    @Value("022")
    private String phone;

    public School() {
    }

    public School(String address, String phone) {
        this.address = address;
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }



    @Override
    public String toString() {
        return "School{" +
                "address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
