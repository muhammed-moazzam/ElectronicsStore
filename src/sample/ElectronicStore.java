package sample;

//Class representing an electronic store
//Has an array of products that represent the items the store can sell
import java.util.ArrayList;
import java.util.Scanner;
public class ElectronicStore{
  public final int MAX_PRODUCTS = 10; //Maximum number of products the store can have
  private int num_sales;
  private int curProducts;
  String name;
  Product[] stock; //Array to hold all products
  double revenue;
  ArrayList<Product> cart;
  
  public ElectronicStore(String initName){
    revenue = 0.0;
    name = initName;
    num_sales = 0;
    stock = new Product[MAX_PRODUCTS];
    curProducts = 0;
    cart = new ArrayList<>();
  }
  
  public String getName(){
    return name;
  }
  
  //Adds a product and returns true if there is space in the array
  //Returns false otherwise
  public boolean addProduct(Product newProduct){
    if(curProducts < MAX_PRODUCTS){
     stock[curProducts] = newProduct;
     curProducts++;
     return true;
    }
    return false;
  }
  
  //Sells 'amount' of the product at 'index' in the stock array
  //Updates the revenue of the store
  //If no sale occurs, the revenue is not modified
  //If the index is invalid, the revenue is not modified
  public void sellProducts(Product p, int amount){
    revenue += p.sellUnits(amount);
  }

  public int getNum_sales(){
    return num_sales;
  }
  
  public double getRevenue(){
    return revenue;
  }
  
  //Prints the stock of the store
  public void printStock(){
    for(int i = 0; i < curProducts; i++){
      System.out.println(i + ". " + stock[i] + " (" + stock[i].getPrice() + " dollars each, " + stock[i].getStockQuantity() + " in stock, " + stock[i].getSoldQuantity() + " sold)");
    }
  }

  public ArrayList<Product> getStock() {
    ArrayList<Product> ActualStock = new ArrayList<>();
    for(int i = 0; i < stock.length;i++){
      if(stock[i] != null && stock[i].getStockQuantity() != 0){
        ActualStock.add(stock[i]);
      }
    }
    return ActualStock;
  }

  public Product[] getProducts(){
    return stock;
  }

  public ArrayList<Product> getCart() {
    return cart;
  }

  public void addToCart(int productIndex){
    if(stock[productIndex].getStockQuantity() > 0){
      cart.add(stock[productIndex]);
      stock[productIndex].addCart();
    }
  }

  public double cartTotal(){
    double total = 0;

    for(int i = 0; i < cart.size(); i++){
      total += cart.get(i).getPrice();
    }

    return total;
  }

  public void removeCart(int productIndex){
    cart.get(productIndex).removeCart();
    cart.remove(productIndex);
  }

  public void sellCart(){
    for(int i=0; i < cart.size(); i++){
      sellProducts(cart.get(i), 1);
    }
    num_sales += 1;
  }

  public void emptyCart(){
    cart = new ArrayList<>();
  }

  public ArrayList<Product> mostPopular(){
    ArrayList<Product> popularProducts = new ArrayList<>();
    Product popular1 = stock[0];
    Product popular2 = stock[1];
    Product popular3 = stock[2];
    popularProducts.add(popular1);
    popularProducts.add(popular2);
    popularProducts.add(popular3);

    for(int i = 0; i < getProducts().length; i++){
      if(stock[i] != null) {
        if (stock[i].getSoldQuantity() > popular1.getSoldQuantity()) {
          popular1 = stock[i];
        } else if (stock[i].getSoldQuantity() > popular2.getSoldQuantity()) {
          popular2 = stock[i];
        } else if (stock[i].getSoldQuantity() > popular3.getSoldQuantity()) {
          popular3 = stock[i];
        }
      }
    }

    popularProducts.set(0, popular1);
    popularProducts.set(1, popular2);
    popularProducts.set(2, popular3);

    return popularProducts;
  }

  public static ElectronicStore createStore(){
    ElectronicStore store1 = new ElectronicStore("Watts Up Electronics");
    Desktop d1 = new Desktop(100, 10, 3.0, 16, false, 250, "Compact");
    Desktop d2 = new Desktop(200, 10, 4.0, 32, true, 500, "Server");
    Laptop l1 = new Laptop(150, 10, 2.5, 16, true, 250, 15);
    Laptop l2 = new Laptop(250, 10, 3.5, 24, true, 500, 16);
    Fridge f1 = new Fridge(500, 10, 250, "White", "Sub Zero", 15.5, false);
    Fridge f2 = new Fridge(750, 10, 125, "Stainless Steel", "Sub Zero", 23, true);
    ToasterOven t1 = new ToasterOven(25, 10, 50, "Black", "Danby", 8, false);
    ToasterOven t2 = new ToasterOven(75, 10, 50, "Silver", "Toasty", 12, true);
    store1.addProduct(d1);
    store1.addProduct(d2);
    store1.addProduct(l1);
    store1.addProduct(l2);
    store1.addProduct(f1);
    store1.addProduct(f2);
    store1.addProduct(t1);
    store1.addProduct(t2);
    return store1;
  }
} 