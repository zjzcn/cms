package com.zjzcn.service;

import org.springframework.stereotype.Service;

import com.zjzcn.entity.Log;
import com.zjzcn.service.LogService;

@Service
public class LogService extends BaseService<Log> {
public static void main(String[] args) {
	LogService a = new LogService();
	System.out.println(a);
	
}
}
