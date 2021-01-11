import java.text.DecimalFormat;
abstract public class Account {

   protected String nameOfUser;

   protected double balance;

   public Account(String nameOfUser, double balance) {
      this.nameOfUser = nameOfUser;
       this.balance = balance;
   }
   public String getNameOfUser() {
      return nameOfUser;
   }
   public double getBalance() {
      return balance;
   }
   
   @Override
   public String toString() {
      double bal=Math.round(balance*100.0)/100.0;
      return "Name: " + nameOfUser + "," + "\tBalance: $"+ String.format("%-6.2f", bal);
   }
   
   public abstract boolean withdraw(double amount);
   public abstract void deposit(double amount);
}
