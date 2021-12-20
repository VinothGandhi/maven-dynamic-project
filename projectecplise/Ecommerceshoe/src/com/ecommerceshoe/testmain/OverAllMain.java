package com.ecommerceshoe.testmain;

import java.sql.Connection;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import com.ecommerceshoe.dao.AdminDao;
import com.ecommerceshoe.dao.ConnectionUtil;
import com.ecommerceshoe.dao.ProductDao;
import com.ecommerceshoe.dao.UserDao;
import com.ecommerceshoe.model.Admin;
import com.ecommerceshoe.model.Product;
import com.ecommerceshoe.model.Users;

public class OverAllMain {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
		System.out.println("\n1.Adminlogin\n2.Createaccount\n3.Userlogin\n4.addproduct\nEnter your Choice");
		int choice=Integer.parseInt(sc.nextLine());
		Users user=null;
		Admin admin=null;
		switch(choice) {
		case 1:
		admin=new Admin();
		 String admin_email =null;
		 do{
		 System.out.println("ADMINLOGIN");
		 do
		 {
		System.out.println("Enter the email");
		admin_email=sc.nextLine();
	     if(admin_email.isEmpty())
	     {
			  System.out.println("email cant be empty");
		}
         if(!admin_email.matches("[a-z][a-z0-9]+[@][a-z]+[.][a-z]+"))
         {
       	  System.out.println("email must be eg:vinoth33@gmail.com");
         }
		}
		 while(!admin_email.matches("[a-z][a-z0-9]+[@][a-z]+[.][a-z]+"));
		 
		 String password;
		do 
		 {
	    System.out.println("Enter the password");
	    password =sc.nextLine();
	    if(password.isEmpty())
	    {
			  System.out.println("password cant be empty");
		}
		if(!password.matches("[a-zA-Z0-9@#!.&]{8,16}"))
		{
			System.out.println("password should be 8 character");
		}
		}
		 while(!password.matches("[a-zA-Z0-9@#!.&]{8,16}"));
		 
		AdminDao currentAdmin=new AdminDao();
		admin=currentAdmin.validateAdmin(admin_email,password);
		if(admin!=null)
		{
		System.out.println("Welcome admin");
		
		} else {
			System.out.println("invalid email or password ");
		}

		 }while(admin==null);
	
		break;
		
		
		case 2:
			String  Name=null;
			String password=null;
			long mobileNo=0;
			String  email=null;
			String Address=null;
			do{
				System.out.println("enter the Username details");
				  Name=sc.nextLine();
				  if(Name.isEmpty()) 
				  {
					  System.out.println("name cant be empty");
				  }
				  if(!Name.matches("[a-zA-z0-9]{3,}")) 
				  {
					  System.out.println("name should be maximum 3 character");
				  }
			}
			while(!Name.matches("[a-zA-z0-9]{3,}"));
			
		do 
		{
		System.out.println("enter the Password");
		password=sc.nextLine();
		if(password.isEmpty())
		{
			  System.out.println("password cant be empty");
		}
		if(!password.matches("[a-zA-Z0-9@#!.&]{8,16}"))
		{
			System.out.println("password should be 8 character");
		}
		}
		while(!password.matches("[a-zA-Z0-9@#!.&]{8,16}"));
		
		String tempmobile=null;
		do 
		{
		System.out.println("enter the Mobileno");
        tempmobile=sc.nextLine();
        if(tempmobile.isEmpty()) 
        {
			  System.out.println("mobilenumber cant be empty");
		}
        if(!tempmobile.matches("[6-9][0-9]{9}"))
        {
        	System.out.println("Mobilenumber must be 10 digit");
        }
		}
		while(!tempmobile.matches("[6-9][0-9]{9}"));
		mobileNo=Long.parseLong(tempmobile);
		
		do
		{
        System.out.println("enter Email id");
          email=sc.nextLine();
          if(email.isEmpty())
          {
			  System.out.println("email cant be empty");
          }		
          if(!email.matches("[a-z][a-z0-9]+[@][a-z]+[.][a-z]+"))
          {
        	  System.out.println("email must be eg:vinoth33@gmail.com");
          }
		}
		while(!email.matches("[a-z][a-z0-9]+[@][a-z]+[.][a-z]+"));
		
		do 
		{
        System.out.println("enter the Address");
         Address=sc.nextLine();
         if( Address.isEmpty())
         {
			  System.out.println("Address cant be empty");
         }
		}
		while(Address.isEmpty());
		
        user =new Users( Name,password,mobileNo,email,Address);
        UserDao.inserUser(user);
		
		case 3:
			 user=new Users();
			 String  email_id=null;
			 do{
			 System.out.println("LOGIN");
			 do
			 {
			System.out.println("Enter the email");
		     email_id=sc.nextLine();
		     if(email_id.isEmpty())
		     {
				  System.out.println("email cant be empty");
			}
	          if(!email_id.matches("[a-z][a-z0-9]+[@][a-z]+[.][a-z]+"))
	          {
	        	  System.out.println("email must be eg:vinoth33@gmail.com");
	          }
			}
			 while(!email_id.matches("[a-z][a-z0-9]+[@][a-z]+[.][a-z]+"));
			 
			 do 
			 {
		    System.out.println("Enter the password");
		    password =sc.nextLine();
		    if(password.isEmpty())
		    {
				  System.out.println("password cant be empty");
			}
			if(!password.matches("[a-zA-Z0-9@#!.&]{8,16}"))
			{
				System.out.println("password should be 8 character");
			}
			}
			 while(!password.matches("[a-zA-Z0-9@#!.&]{8,16}"));
			 
			UserDao currentUser=new UserDao();
			user=currentUser.validateUser(email_id,password);
			if(user!=null)
			{
			System.out.println("Welcome "+user.getName());
			
			} else {
				System.out.println("invalid email or password ");
			}
	
			 }while(user==null);
			 String brandName =null;
			 int  brandSize=0;
			 String color=null;
				double prices=0;
		case 4:
			
			Product product=null;
			ProductDao productdao=null;
			System.out.println("\n1.insert products\n2.update products\n3.delete products");
			int choice1=Integer.parseInt(sc.nextLine());
			String brandType=null;
			Date manufactureDate = null ;
			switch(choice1) {
			case 1:
			
			do{
				System.out.println("enter the addProduct  details");
				System.out.println("enter the BrandName ");
				brandName=sc.nextLine();
				  if(brandName.isEmpty()) 
				  {
					  System.out.println("name cant be empty");
				  }			 
			}
			while(brandName.isEmpty());
			
		do 
		{
		System.out.println("enter the Brandtype ");
		brandType=sc.nextLine();
		if(brandType.isEmpty())
		{
			  System.out.println("brandtype cant be empty");
		}
		
		}
		while(brandType.isEmpty());
		
		String tempbrandsize=null;
		do 
		{
		System.out.println("enter the BrandSize");
        tempbrandsize=sc.nextLine();
        if(tempbrandsize.isEmpty()) 
        {
			  System.out.println("BrandSize cant be empty");
		}
       
		}
		while(tempbrandsize.isEmpty());
		 brandSize=Integer.parseInt(tempbrandsize);
		
		String tempPrices=null;
		do
		{
        System.out.println("enter Price");
        tempPrices=sc.nextLine();
          if(tempPrices.isEmpty())
          {
			  System.out.println("price cant be empty");
          }		
         
		}
		while(tempPrices.isEmpty());
		prices=Double.parseDouble(tempPrices);
		
		
		do 
		{
        System.out.println("enter the color");
        color=sc.nextLine();
         if( color.isEmpty())
         {
			  System.out.println("color cant be empty");
         }
		}
         while(color.isEmpty());
         String Tempdate=null;
         
         try
         {
         do 
 		{
         System.out.println("enter the manufacturedate");
         Tempdate =sc.nextLine();
          if( Tempdate.isEmpty())
          {
 			  System.out.println("date cant be empty");
          }
          if(!Tempdate.matches("(0[1-9]|[12][0-9]|3[01])[-](0[1-9]|1[012])[-]\\d{4}"))
          {
        	  System.out.println("dateofbirth format");
          }
		}
         
		while(Tempdate.isEmpty());
				
         manufactureDate=sdf.parse(Tempdate);
			}
			 catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
         
         
         
		
		
        product =new Product(brandName,brandType,brandSize, color,prices,manufactureDate);
        ProductDao.inserproduct(product);
		break;
		
			case 2:
		System.out.println("I will update current Brandname");
		String  brandName1=sc.nextLine();
		System.out.println("I will update current brandsize");
		int brandsize1=Integer.parseInt(sc.nextLine());
		
			System.out.println("I will update current price");
			double price=Double.parseDouble(sc.nextLine());
			
			System.out.println("For this color");
			String color1=sc.nextLine();
			
			
			product=new Product(brandName1,brandsize1, price,color1);
			 productdao=new ProductDao();
			 productdao.updated(product);
			break;
	
}
}
}
}