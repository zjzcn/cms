package com.zjzcn.service.impl;

import org.springframework.stereotype.Service;

import com.zjzcn.entity.Log;
import com.zjzcn.service.LogService;

@Service
public class LogServiceImpl extends BaseServiceImpl<Log> implements LogService {
public static void main(String[] args) {
	LogServiceImpl a = new LogServiceImpl();
	System.out.println(a);
	
}
}
