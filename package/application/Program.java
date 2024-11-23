package Exercicios01.ex02.Application;

import Exercicios01.ex02.Entities.Products;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        List<Products> listProduct = new LinkedList<>();

        System.out.println("Quantities of products to put in stock? ");
        int number = sc.nextInt();

        for (int i = 0; i < number; i++) {
            System.out.println("---------------------------");
            System.out.println("    REGISTRATION PRODUCT   ");

            System.out.print("Name products: ");
            sc.nextLine();
            String name = sc.nextLine();

            while(checkProduct(listProduct, name)){
                System.out.println("This name is not on the list, please add another one");
                name = sc.nextLine();
                sc.nextLine();
            }

            System.out.print("Price products: ");
            double price = sc.nextDouble();

            System.out.print("Quantity products: ");
            int quantity = sc.nextInt();

            listProduct.add(new Products(name, price, quantity));
        }


        System.out.println("\n---------------------------");
        System.out.println("All products registered!");
        listProduct.forEach(System.out::println); // Mostra os produtos cadastrados

        // Parte de busca de produtos
        boolean keepRunning = true;
        while (keepRunning) {
            System.out.println("\n---------------------------");
            System.out.println("Options:");
            System.out.println("1 - Search for a product by name");
            System.out.println("2 - Exit");
            System.out.print("Enter your choice: ");
            int option = sc.nextInt();
            sc.nextLine(); // Consumir a quebra de linha

            switch (option) {
                case 1:
                    System.out.print("Enter the name of the product to search: ");
                    String searchName = sc.nextLine();
                    Products foundProduct = searchProduct(listProduct, searchName);
                    if (foundProduct != null) {
                        System.out.println("Product found: " + foundProduct);
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;
                case 2:
                    System.out.println("Exiting the program...");
                    keepRunning = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        sc.close();





    }

    public static boolean checkProduct(List<Products> listProduct, String name){
        Products product = listProduct.stream().filter(products -> products.getName() == name).findFirst().orElse(null);
        return product != null;
    }

    public static Products searchProduct(List<Products> productsList, String name){
        for (Products productsValidos : productsList){
            if (productsValidos.getName().equalsIgnoreCase(name)){
                return productsValidos;
            }
        }
        return null;
    }

}
