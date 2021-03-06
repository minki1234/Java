package com.example.rorro.controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
	

	@GetMapping("/")
	public String home() {
		return "index";
	}
	
	@GetMapping("/another")
	public String anotherPage(Model model) {
		WriteLog wl = new WriteLog();
		wl.writeLog("another페이지로 이동\n");
		String msg1 = wl.readText();       
		model.addAttribute("send1", msg1);
		return "another";
	}
	@GetMapping("/another/result")
	public String result(@RequestParam("input-name") String name, Model model) {
		
		model.addAttribute("send1", name);
		return "result";
	}
	
}
class WriteLog{
	public void writeLog(String page) {
		FileOutputStream fout = null;
		Calendar b = Calendar.getInstance();
		Date date = b.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
		String times = sdf.format(date);
		String msg = "["+times+"] "+page;
		
		
		byte byteString[] = msg.getBytes();
		
		try {
			fout = new FileOutputStream("c:\\java\\log.txt", true);
			fout.write(byteString);
			fout.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String readText() {
		
		FileInputStream fin = null;
		int read = -1;
		String str1 = "";
		try {
			fin = new FileInputStream("c:\\java\\info.txt");
			InputStreamReader reader = new InputStreamReader(fin, "UTF-8");
			BufferedReader in = new BufferedReader(reader);
			while((read = in.read()) != -1) {
				str1 += (char)read;
			}
			System.out.println(str1);
		} catch (Exception e) {
			e.printStackTrace();
		
		}
		return str1;
	}
}