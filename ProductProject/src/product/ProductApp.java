package product;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class ProductApp {

	private LinkedList<Product> productList = new LinkedList<>();
	private LinkedList<Integer> deletedIds = new LinkedList<>();
	private int currProductId = 0;
	
	public static void main(String[] args) {
		ProductApp app= new ProductApp();
		app.displayInstructions();
		app.processUserInput();
		
	}
	
	private void displayInstructions(){
		System.out.println("1.Adding Product");
		System.out.println("2.Modifing Product");
		System.out.println("3.Deleting Product");
		System.out.println("4.Report");
	}
	
	private void processUserInput(){
		Scanner scan=new Scanner(System.in);
		int expersion=scan.nextInt();
		switch(expersion){
		case 1:
			addProduct(scan);
			break;
		case 2:
			modifyProduct(scan);
			break;
		case 3:
			deleteProduct(scan);
			break;
		case 4:
			displayProducts();
			break;
		default:
			System.out.println("Invalid input....");
			break;
		}
		System.out.println("Again you want do other operations (Yes/No)");
		String str=scan.next();
		if(str.equalsIgnoreCase("Yes")){
			System.out.println("Continue....");
			displayInstructions();
			processUserInput();
		}else {
			System.out.println("Thank you ");			
		}
	}
	
	private void addProduct(Scanner scan){
		System.out.println("Add a Product");
		System.out.println("Enter the ProductName : ");
		String productName=scan.next();
		System.out.println("Enter the ProductPrice : ");
		double productPrice=scan.nextDouble();
		Product product = new Product();
		if (deletedIds.size()>0) {
			product.setId(deletedIds.get(0));
			deletedIds.remove(0);
		} else {
			product.setId(++currProductId);
		}
		product.setName(productName);
		product.setPrice(productPrice);
		productList.add(product);		
	}
	
	private void deleteProduct(Scanner scan){
		System.out.println("Deleting the Product");
		for (Product product: productList) {
			System.out.println("Id :"+product.getId() + "\t Name : " +product.getName()+ 
					"\t Price " + product.getPrice());
		}
		System.out.println("Enter the ProductId");
		int productId=scan.nextInt();
		Iterator<Product> itr = productList.iterator();
		while(itr.hasNext()) {
			Product temp = itr.next();
			if (temp.getId()==productId) {
				deletedIds.add(productId);
				itr.remove();
				break;
			}
		}
	}
	
	private void modifyProduct(Scanner scan){
		System.out.println("Modify the Product Details");
		System.out.println("u can modify only Name or Price of the product");
		System.out.println("a.Modified by ProductName");
		System.out.println("b.Modified by ProductPrice");
		char ch=scan.next().charAt(0);
		switch(ch){
			case 'a':
				System.out.println("Modified by ProductName");
				for (Product product: productList) {
					System.out.println("Id :"+product.getId() + "\t Name : " +product.getName()+ 
							"\t Price " + product.getPrice());
				}
				System.out.println("Enter the ProductId");
				int temp_productId=scan.nextInt();
				for(Product product : productList) {
					if(temp_productId==product.getId()){
						System.out.println("Enter the New Name of the specified product_Id");
						String newName=scan.next();
						product.setName(newName);
						break;
					}
				}
				break;
			case 'b':
				System.out.println("Modified by ProductPrice");
				for (Product product: productList) {
					System.out.println("Id :"+product.getId() + "\t Name : " +product.getName()+ 
							"\t Price " + product.getPrice());
				}
				System.out.println("Enter the ProductId");
				int productId=scan.nextInt();
				for(Product product : productList) {
					if(productId==product.getId()){
						System.out.println("Enter the New Price of the specified product_Id");
						double newPrice=scan.nextDouble();
						product.setPrice(newPrice);
					}
				}
				break;
			}
	}

	private void displayProducts(){
		System.out.println("a.Sort by ProductId");
		System.out.println("b.Sort by ProductName");
		System.out.println("c.Sort by ProductPrice");
		Scanner scan=new Scanner(System.in);
		char ch=scan.next().charAt(0);
		switch(ch){
		case 'a':
			Comparator<Product> comparator = new Comparator<Product>() {
		          public int compare(Product c1, Product c2) {
		              return (int) (c1.getId()-c2.getId())  ; 
		             }
			};
			Collections.sort(productList,comparator);
			for (Product product: productList) {
				System.out.println("Id :"+product.getId() + "\t Name : " +product.getName()+ 
						"\t Price " + product.getPrice());
			}
			break;
		case 'b':
			Comparator<Product> comparator1=new Comparator<Product>() {

				@Override
				public int compare(Product o1, Product o2) {
					// TODO Auto-generated method stub
					
					return o1.getName().compareTo(o2.getName());
				}
		
			};
			Collections.sort(productList,comparator1);
			for (Product product: productList) {
				System.out.println("Id :"+product.getId() + "\t Name : " +product.getName()+ 
						"\t Price " + product.getPrice());
			}
			break;
		case 'c':
			Comparator<Product> comparator2 = new Comparator<Product>() {
		          public int compare(Product c1, Product c2) {
		              return (int) (c1.getPrice()-c2.getPrice())  ; 
		             }
			};
			Collections.sort(productList,comparator2);
			for (Product product: productList) {
				System.out.println("Id :"+product.getId() + "\t Name : " +product.getName()+ 
						"\t Price " + product.getPrice());
			}
			break;
		}
	}
}