package com.zjzcn.test;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class Decoder {

	public static void main(String[]args)
	{
	JFrame f=new JFrame("URL编码解码");
	Container contentPane=f.getContentPane();
	contentPane.setLayout(new GridLayout(1,1));
	JPanel p1=new JPanel();
	p1.setLayout(new GridLayout(6,1));
	p1.setBorder(BorderFactory.createTitledBorder("解码程序"));
	final JTextField input1=new JTextField(); // 实例化输入框
	final JTextField Result=new JTextField();
	JButton btn1=new JButton("打开文件");
	JButton btn=new JButton("解码");//创建按扭
	JButton btnClean1=new JButton("清空");
	JLabel l1=new JLabel("请输入文件路径：");
	p1.add(l1);
	p1.add(input1);
	p1.add(Result);
	p1.add(btn1);
	p1.add(btn);
	p1.add(btnClean1);
	
	JPanel p2=new JPanel ();
	p2.setLayout(new GridLayout(5,1));
	p2.setBorder(BorderFactory.createTitledBorder("编码程序"));
	final JTextField input3=new JTextField(); // 实例化输入框
	final JTextField Result2=new JTextField();
	JButton btn2=new JButton("编码");//创建按扭
	JButton btnClean2=new JButton("清空");
	JLabel l2=new JLabel("请输入");
	p2.add(l2);
	p2.add(input3);
	p2.add(Result2);
	p2.add(btn2);
	p2.add(btnClean2);
	
	contentPane.add(p1);
	contentPane.add(p2);
	f.setSize(450,350);
	f.show();
	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//退出
	btn1.addActionListener(new ActionListener()//解码监听事件 
	{ 
	    public void actionPerformed(ActionEvent event) 
	    { 
	    	
	    	try{
	            JFileChooser fileChooser = new JFileChooser(".");
	            fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
	            fileChooser.setDialogTitle("打开文件夹");
	            int ret = fileChooser.showOpenDialog(null);
	            if (ret == JFileChooser.APPROVE_OPTION)
	            {
	                //文件夹路径
	                System.out.println(fileChooser.getSelectedFile().getAbsolutePath());
	                
	                input1.setText(fileChooser.getSelectedFile().getAbsolutePath());
	            }
	    	}catch(Exception ex){ex.printStackTrace();}
	    } 
	} 
	); 
	
	btn.addActionListener(new ActionListener()//解码监听事件 
	{ 
	    public void actionPerformed(ActionEvent event) 
	    { 
	    	
	    	try{
	    		File inputfile = new File(input1.getText());
	    		FileReader fr = new FileReader(inputfile);
	    		BufferedReader br = new BufferedReader(fr);
	    		String s;
	    		while ((s = br.readLine()) != null) {
	    			System.out.println(s);
	    			String destr = URLDecoder.decode(s, "utf-8"); 
	    			Result.setText(Result.getText()+destr);
	    		}
	    		fr.close();

	    	}catch(Exception ex){ex.printStackTrace();}
	    } 
	} 
	); 
	btn2.addActionListener(new ActionListener()//编码监听事件 
	{ 
	    public void actionPerformed(ActionEvent event) 
	    {      
	         try{   
	          String str2=input3.getText();
	          String enstr = URLEncoder.encode(str2,"utf-8");
	          Result2.setText(enstr);
	          }catch(UnsupportedEncodingException ex){}
	    } 
	} 
	); 
	btnClean1.addActionListener(new ActionListener()//清空事件 
	{ 
	    public void actionPerformed(ActionEvent event) 
	    { 
	       input1.setText("");
	       Result.setText("");
	    } 
	} 
	); 
	btnClean2.addActionListener(new ActionListener()////清空事件
	{ 
	    public void actionPerformed(ActionEvent event) 
	    { 
	       input3.setText("");
	       Result2.setText("");
	    } 
	} 
	);

	}

}
