import java.util.*;

class Stock {
    String name;
    double price;

    Stock(String name, double price) {
        this.name=name;
        this.price=price;
    }
}

class PortfolioItem {
    String name;
    int quantity;
    double price;

    PortfolioItem(String name, int quantity, double price) {
        this.name=name;
        this.quantity=quantity;
        this.price=price;
    }
}

public class Stocktrading {
    static Map<String, Stock> marketData=new HashMap<>();
    static Map<String, PortfolioItem> portfolio=new HashMap<>();
    static Scanner sc=new Scanner(System.in);
    static double balance=10000.0;

    public static void main(String[] args) {
        loadMarketData();
        int choice;

        do {
            System.out.println("\n--- Stock Trading Platform ---");
            System.out.println("1. View Market Data");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice=sc.nextInt();

            switch(choice) {
                case 1: viewMarket(); break;
                case 2: buyStock(); break;
                case 3: sellStock(); break;
                case 4: viewPortfolio(); break;
                case 5: System.out.println("Exiting..."); break;
                default: System.out.println("Invalid choice.");
            }
        } while(choice!=5);
    }

    static void loadMarketData() {
       marketData.put("APPLE",new Stock("APPLE",175.50));
       marketData.put("GOOGLE",new Stock("GOOGLE",2850.3));
       marketData.put("TESLA",new Stock("TESLA",720.10));
       marketData.put("AMAZON",new Stock("AMAZON",3300.8));
       marketData.put("INFOSYS",new Stock("INFOSYS",1420.7));
    }

    static void viewMarket() {
        System.out.println("\n--- Market Data ---");
        for(Stock stock:marketData.values()) {
            System.out.println(stock.name+" - ₹"+stock.price);
        }
    }

    static void buyStock() {
        System.out.print("Enter stock symbol to buy: ");
        String symbol=sc.next().toUpperCase();

        if(!marketData.containsKey(symbol)) {
            System.out.println("Stock not found.");
            return;
        }

        System.out.print("Enter quantity: ");
        int qty=sc.nextInt();
        Stock stock=marketData.get(symbol);
        double totalCost=stock.price*qty;

        if(totalCost>balance) {
            System.out.println("Insufficient balance.");
            return;
        }

        balance-=totalCost;
        if(portfolio.containsKey(symbol)) {
            PortfolioItem item=portfolio.get(symbol);
            item.quantity+=qty;
        } else {
            portfolio.put(symbol,new PortfolioItem(symbol,qty,stock.price));
        }

        System.out.println("Bought "+qty+" shares of "+symbol);
    }

    static void sellStock() {
        System.out.print("Enter stock symbol to sell: ");
        String symbol=sc.next().toUpperCase();

        if(!portfolio.containsKey(symbol)) {
            System.out.println("You do not own this stock.");
            return;
        }

        System.out.print("Enter quantity to sell: ");
        int qty=sc.nextInt();
        PortfolioItem item=portfolio.get(symbol);

        if(qty>item.quantity) {
            System.out.println("You don't have that many shares.");
            return;
        }

        double saleAmount=qty*marketData.get(symbol).price;
        item.quantity-=qty;
        balance+=saleAmount;

        if(item.quantity==0) {
            portfolio.remove(symbol);
        }

        System.out.println("Sold "+qty+" shares of "+symbol+" for ₹"+saleAmount);
    }

    static void viewPortfolio() {
        System.out.println("\n--- Your Portfolio ---");
        double totalValue=0.0;

        if(portfolio.isEmpty()) {
            System.out.println("No stocks owned.");
        } else {
            for(PortfolioItem item:portfolio.values()) {
                double currentPrice=marketData.get(item.name).price;
                double value=currentPrice*item.quantity;
                totalValue+=value;
                System.out.println(item.name+" - Qty: "+item.quantity+" | Current Price: ₹"+currentPrice+" | Value: ₹"+value);
            }
        }

        System.out.println("Cash Balance: ₹"+balance);
        System.out.println("Total Portfolio Value: ₹"+(balance+totalValue));
    }
}
