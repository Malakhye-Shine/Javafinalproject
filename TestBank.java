import java.util.*;
import java.io.*;

public class TestBank {
   
   public static ArrayList<Account> ReadAccounts() {
      ArrayList<Account> newList = new ArrayList<>();
      Scanner scr = openFile();
      while (scr.hasNext()) {
         String Name = scr.nextLine();
         String[] next = scr.nextLine().split(" ");
         char type = next[0].charAt(0);
         double price = Double.parseDouble(next[1]);
            if (type == 'C') {
               Account cA = new CheckingAccount(Name, price);
               newList.add(cA);
            } 
            else {
               Account sA = new SavingsAccount(Name, price);
               newList.add(sA);
            }
        }
      return newList;
   }

   public static Scanner openFile() {
      Scanner sc = new Scanner(System.in);
      String fileName = "finalp2.txt";
      try {
         sc = new Scanner(new File(fileName));
         return sc;
      } catch (FileNotFoundException ex) {
         System.out.println("File Could Not Be Opened!");
      }
      System.exit(0);
      return null;

   }
   public static void main(String[] args) throws IOException {
      ArrayList<Account> accounts = new ArrayList<>();
      Account temp;
      Bank bank = new Bank();
      bank.setBankNameBrand("CSUDH Credit Union");
      bank.setSavingsInterestRate(0.025);
      accounts = ReadAccounts();
      bank.setAccounts(accounts);
      Account a3 = new SavingsAccount("Bob The Builder", 50.00);
      bank.addAccount(a3);
      Account a4 = new CheckingAccount("Bob The Builder", 50.00);
      bank.addAccount(a4);
      System.out.println("Number of accounts: " + bank.numberOfAccounts());
      boolean result = bank.withdraw("Alice Jones", 20.00);
      System.out.println(result);
      temp = bank.search("Tom Halwin");
      System.out.println(temp);
      temp = bank.search("None");
      if (temp == null) {
         System.out.println("Account not found.\n");
      }
      result = bank.deposit("Bob The Builder", 20.00);
      System.out.println(result);
      bank.addInterest();
      System.out.println(bank.getAccount("Alice Jones"));
      System.out.println(bank.getAccount("bob"));
      bank.addAccount(a3);
      bank.deposit("Alice Jones", 100);
      bank.withdraw("Bob the Builder", 5000);
      bank.writeCheck("Jose Pedia", 130.25);
      bank.writeCheck("Nancy Wilson", 150);
      System.out.println(bank.getAccount("Bob The Builder"));
      System.out.println(bank.getAccount("Alice Jones"));
      bank.transfer("Alice Jones", "Bob The Builder", 100);
      bank.transfer("Alice Jones", "ABC inc.", 1000);
      bank.printTable();
      bank.printFile();
   }
}
