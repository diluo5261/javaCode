package com.dilo.smsservice.service;

import java.util.HashMap;

public interface SmsService {
    boolean sendSmsPhone(String phone, HashMap<String, String> param);
}
