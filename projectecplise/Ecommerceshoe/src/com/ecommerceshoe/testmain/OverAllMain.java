package com.ecommerceshoe.testmain;

import java.sql.Connection;
import java.util.Date;
import java.util.List;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import com.ecommerceshoe.dao.AdminDao;
import com.ecommerceshoe.dao.CartDao;
import com.ecommerceshoe.dao.ConnectionUtil;
import com.ecommerceshoe.dao.OrderDao;
import com.ecommerceshoe.dao.ProductDao;
import com.ecommerceshoe.dao.UserDao;
import com.ecommerceshoe.model.Admin;
import com.ecommerceshoe.model.Order;
import com.ecommerceshoe.model.Product;
import com.ecommerceshoe.model.Users;
import com.ecommerceshoe.model.cart;

public class OverAllMain {

	public static void main(String[] args) throws ParseException {
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		System.out.println("\n1.AdminLogin\n2.CreateAccount\n3.UserLogin\n4.show order\n5.insertcart\n6.showcart\nEnter your Choice");
		int choice = Integer.parseInt(sc.nextLine());
		Users user = null;
		Admin admin = null;
		double amount = 0;
		Users currentUser=null; ;
		switch (choice) {
		case 1:
			admin = new Admin();
			String admin_email = null;
			do {
				System.out.println("ADMINLOGIN");
				do {
					System.out.println("Enter the email");
					admin_email = sc.nextLine();
					if (admin_email.isEmpty()) {
						System.out.println("email cant be empty");
					}
					if (!admin_email.matches("[a-z][a-z0-9]+[@][a-z]+[.][a-z]+")) {
						System.out.println("email must be eg:vinoth33@gmail.com");
					}
				} while (!admin_email.matches("[a-z][a-z0-9]+[@][a-z]+[.][a-z]+"));

				String password;
				do {
					System.out.println("Enter the password");
					password = sc.nextLine();
					if (password.isEmpty()) {
						System.out.println("password cant be empty");
					}
					if (!password.matches("[a-zA-Z0-9@#!.&]{8,16}")) {
						System.out.println("password should be 8 character");
					}
				} while (!password.matches("[a-zA-Z0-9@#!.&]{8,16}"));

				AdminDao currentAdmin = new AdminDao();
				admin = currentAdmin.validateAdmin(admin_email, password);
				if (admin != null) {
					System.out.println("Welcome Admin");

				} else {
					System.out.println("invalid email or password ");
				}

			} while (admin == null);

			Product product = null;
			ProductDao productdao = new ProductDao();
			System.out.println("PRODUCT");
			// showProduct
			System.out.println("List of Products");
			ProductDao proDao = new ProductDao();
			System.out.println();
			List<Product> ProductList = proDao.showProduct();
			for (int i = 0; i < ProductList.size(); i++) {
				System.out.println(ProductList.get(i));

			}
			System.out.println("\n1.Insert Products\n2.Update Products\n3.Delete Products");
			int choice1 = Integer.parseInt(sc.nextLine());
			String brandName = null;
			int brandSize = 0;
			String color = null;
			double prices = 0;
			String brandType = null;
			Date manufactureDate = null;
			switch (choice1) {
			case 1:
				char stop;
				do {

					do {
						System.out.println("enter the addProduct  details");
						System.out.println("enter the BrandName ");
						brandName = sc.nextLine();
						if (brandName.isEmpty()) {
							System.out.println("name cant be empty");
						}
					} while (brandName.isEmpty());

					do {
						System.out.println("enter the Brandtype ");
						brandType = sc.nextLine();
						if (brandType.isEmpty()) {
							System.out.println("brandtype cant be empty");
						}

					} while (brandType.isEmpty());

					String tempbrandsize = null;
					do {
						System.out.println("enter the BrandSize");
						tempbrandsize = sc.nextLine();
						if (tempbrandsize.isEmpty()) {
							System.out.println("BrandSize cant be empty");
						}

					} while (tempbrandsize.isEmpty());
					brandSize = Integer.parseInt(tempbrandsize);

					String tempPrices = null;
					do {
						System.out.println("enter Price");
						tempPrices = sc.nextLine();
						if (tempPrices.isEmpty()) {
							System.out.println("price cant be empty");
						}

					} while (tempPrices.isEmpty());
					prices = Double.parseDouble(tempPrices);

					do {
						System.out.println("enter the color");
						color = sc.nextLine();
						if (color.isEmpty()) {
							System.out.println("color cant be empty");
						}
					} while (color.isEmpty());
					String Tempdate = null;

					try {
						do {
							System.out.println("enter the manufacturedate");
							Tempdate = sc.nextLine();
							if (Tempdate.isEmpty()) {
								System.out.println("date cant be empty");
							}
							if (!Tempdate.matches("(0[1-9]|[12][0-9]|3[01])[-](0[1-9]|1[012])[-]\\d{4}")) {
								System.out.println("dateofbirth format");
							}
						}

						while (Tempdate.isEmpty());

						manufactureDate = sdf.parse(Tempdate);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					int i = productdao.inserproduct(brandName, brandType, brandSize, color, prices, manufactureDate);
					System.out.println("do you continue yes or no");
					stop = sc.nextLine().charAt(0);

				} while (stop == 'Y' || stop == 'y');
			case 2:
				do {
					System.out.println("Update the product");
					System.out.println("I will update current brandsize");
					int brandsize = Integer.parseInt(sc.nextLine());
					System.out.println("I will update current price");
					double price = Double.parseDouble(sc.nextLine());
					System.out.println("For this ProductId");
					int productId = Integer.parseInt(sc.nextLine());
					int i1 = productdao.updated(brandsize, price, productId);

					System.out.println("do you continue yes or no");
					stop = sc.nextLine().charAt(0);

				} while (stop == 'Y' || stop == 'y');
			case 3:
				do {
					System.out.println("Delete product id");
					int ProductId = Integer.parseInt(sc.nextLine());

					int i2 = productdao.delete(ProductId);

					System.out.println("do you continue yes or no");
					stop = sc.nextLine().charAt(0);

				} while (stop == 'Y' || stop == 'y');

			}
			break;

		case 2:
			String Name = null;
			String password = null;
			long mobileNo = 0;
			String email = null;
			String Address = null;
			Double wallet=null;
			do {
				System.out.println("enter the Username details");
				Name = sc.nextLine();
				if (Name.isEmpty()) {
					System.out.println("name cant be empty");
				}
				if (!Name.matches("[a-zA-z0-9]{3,}")) {
					System.out.println("name should be maximum 3 character");
				}
			} while (!Name.matches("[a-zA-z0-9]{3,}"));

			do {
				System.out.println("enter the Password");
				password = sc.nextLine();
				if (password.isEmpty()) {
					System.out.println("password cant be empty");
				}
				if (!password.matches("[a-zA-Z0-9@#!.&]{8,16}")) {
					System.out.println("password should be 8 character");
				}
			} while (!password.matches("[a-zA-Z0-9@#!.&]{8,16}"));

			String tempmobile = null;
			do {
				System.out.println("enter the Mobileno");
				tempmobile = sc.nextLine();
				if (tempmobile.isEmpty()) {
					System.out.println("mobilenumber cant be empty");
				}
				if (!tempmobile.matches("[6-9][0-9]{9}")) {
					System.out.println("Mobilenumber must be 10 digit");
				}
			} while (!tempmobile.matches("[6-9][0-9]{9}"));
			mobileNo = Long.parseLong(tempmobile);

			do {
				System.out.println("enter Email id");
				email = sc.nextLine();
				if (email.isEmpty()) {
					System.out.println("email cant be empty");
				}
				if (!email.matches("[a-z][a-z0-9]+[@][a-z]+[.][a-z]+")) {
					System.out.println("email must be eg:vinoth33@gmail.com");
				}
			} while (!email.matches("[a-z][a-z0-9]+[@][a-z]+[.][a-z]+"));

			do {
				System.out.println("enter the Address");
				Address = sc.nextLine();
				if (Address.isEmpty()) {
					System.out.println("Address cant be empty");
				}
			} while (Address.isEmpty());
            System.out.println("enter the wallet amount");
            wallet=sc.nextDouble();
			user = new Users(Name, password, mobileNo, email, Address,wallet);
			UserDao.inserUser(user);
		case 3:
			user = new Users();
			String email_id = null;

			int i;
			do {
				System.out.println("LOGIN");
				do {
					System.out.println("Enter the email");
					email_id = sc.nextLine();
					if (email_id.isEmpty()) {
						System.out.println("email cant be empty");
					}
					if (!email_id.matches("[a-z][a-z0-9]+[@][a-z]+[.][a-z]+")) {
						System.out.println("email must be eg:vinoth33@gmail.com");
					}
				} while (!email_id.matches("[a-z][a-z0-9]+[@][a-z]+[.][a-z]+"));

				do {
					System.out.println("Enter the password");
					password = sc.nextLine();
					if (password.isEmpty()) {
						System.out.println("password cant be empty");
					}
					if (!password.matches("[a-zA-Z0-9@#!.&]{8,16}")) {
						System.out.println("password should be 8 character");
					}
				} while (!password.matches("[a-zA-Z0-9@#!.&]{8,16}"));

				UserDao currentUser1 = new UserDao();
				i = currentUser1.validateUser(email_id, password);
				if (i != 0) {
					System.out.println("Welcome");

				} else {
					System.out.println("invalid email or password ");
				}
			
				
				System.out.println("List of Products");
				ProductDao proDao1= new ProductDao();
				System.out.println();
				List<Product> ProductList1 = proDao1.showProduct();
				for (int i1 = 0; i1 < ProductList1.size(); i1++) {
					System.out.println(ProductList1.get(i1));

				}

			} while (i == 0);
			char stop;
			do {
				boolean k=false;
				UserDao userDao = null;
//			Date orderDate=null;
			System.out.println("Order Your Purchase");
			System.out.println("enter brand name");
			String proName=sc.nextLine();
			System.out.println("enter brand type");
			String proType=sc.nextLine();
			System.out.println("enter brand size ");
			int bdSize=Integer.parseInt(sc.nextLine());
			System.out.println("enter brand color");
			String colorName=sc.nextLine();
			ProductDao proDt=new ProductDao();
			
		    Product product1=proDt.findProduct(proName, proType, bdSize, colorName);
		   
			System.out.println("enter the mailI");
			String email1=sc.nextLine();
			UserDao userdao =new UserDao();
			Users user1=userdao.findUser(email1);
			
			System.out.println("enter the quantity");
			int quantity=Integer.parseInt(sc.nextLine());
			
//			System.out.println("enter the price");
//			Double price=Double.parseDouble(sc.nextLine());			
			Date today=new Date();
//			String TempDate1=sc.nextLine();
//			 orderDate=sdf.parse(TempDate1);
			Order order=new Order(product1,user1,quantity,today);
			OrderDao orderdao=new OrderDao();
			orderdao.insertOrder(order);
			System.out.println("Order Successfull");
			if(k==true) {
			
			userDao.Walletupdate(amount, currentUser);
			System.out.println("Wallet recharge successfull");
			
			}

			System.out.println("Recharge here wallet");
			System.out.println("Enter amount");
			amount = Double.parseDouble(sc.nextLine());
			System.out.println("Enter card number");
			long cardNo = Long.parseLong(sc.nextLine());
			System.out.println("Enter cvv");
			int cvv = Integer.parseInt(sc.nextLine());
			currentUser.setWallet(amount);
			
			userDao.updateuserWallet(currentUser);
			
			System.out.println("do you continue yes or no");
			stop = sc.nextLine().charAt(0);

			}while (stop == 'Y' || stop == 'y');
			
			break;
		case 4:
			OrderDao orderdao =new OrderDao();
			List<Order> orderList=orderdao.ShowOrder();
			for(int j=0;j<orderList.size();j++) {
				System.out.println(orderList.get(j));
			} break;
		case 5:
		 CartDao cartDao = null;
//			Date orderDate=null;
			System.out.println("Display on Cart");
			System.out.println("enter brand name");
			String proName=sc.nextLine();
			System.out.println("enter brand type");
			String proType=sc.nextLine();
			System.out.println("enter brand size ");
			int bdSize=Integer.parseInt(sc.nextLine());
			System.out.println("enter brand color");
			String colorName=sc.nextLine();
			ProductDao proDt=new ProductDao();
			
		    Product product1=proDt.findProduct(proName, proType, bdSize, colorName);
		   
			System.out.println("enter the mailI");
			String email1=sc.nextLine();
			UserDao userdao =new UserDao();
			Users user1=userdao.findUser(email1);
			
			System.out.println("enter the quantity");
			int quantity=Integer.parseInt(sc.nextLine());
			
			
			//			System.out.println("enter the price");
//			Double price=Double.parseDouble(sc.nextLine());			
//			Date today=new Date();
//			String TempDate1=sc.nextLine();
//			 orderDate=sdf.parse(TempDate1);
			cart carts=new cart(product1,user1,quantity);
			CartDao cartDao1=new CartDao();
			
			cartDao1.insertCart(carts);
			System.out.println("Cart Added Successfull");
			
			System.out.println("do you continue yes or no");
			stop = sc.nextLine().charAt(0);

		case 6:
			CartDao cartDao11 =new CartDao();
			List<cart> cartList=cartDao11.showCart();
			for(int j=0;j<cartList.size();j++) {
				System.out.println(cartList.get(j));
			} break;

		}
	}
}

