import java.util.ArrayList;
import java.util.*;
import java.io.*;
public class Bank {
   private ArrayList<Account> accounts;
   private double savingsInterestRate;
   private String bankNameBrand;

   public Bank(){
      accounts=new ArrayList<>();
   }

   public void setSavingsInterestRate(double savingsInterestRate) {
      this.savingsInterestRate = savingsInterestRate;
   }

   public int numberOfAccounts(){
      return this.accounts.size();
   }
   public void addAccount(Account a){
      for(Account A:accounts){
         if(A.getNameOfUser().equalsIgnoreCase(a.getNameOfUser())){
            System.out.println("Account not added, duplicate ID");
            return;
         }
      }
      accounts.add(a);
   }

	public Account getAccount(String accountID) {
      for(int i=0;i<this.numberOfAccounts();i++) {
         if(accounts.get(i).getNameOfUser().compareTo(accountID)==0) {
            return accounts.get(i);
         }
      }
      System.out.println(accountID + " account does not exist.");
      return null;
	}
	public boolean deposit(String accountID,double amount){
      for(Account A:accounts){
         if(A.getNameOfUser().equalsIgnoreCase(accountID)){
            A.deposit(amount);
            return true;
         }
      }
      return false;
   }

   public boolean withdraw(String accountID, double Amount){
      for(Account A:accounts){
         if(A.getNameOfUser().equalsIgnoreCase(accountID)){
            return A.withdraw(Amount);
         }
      }
      return false;
   }
   
   public boolean transfer(String fromAccountID,String toAccountID,double balance){
      boolean first=false;
      boolean second=false;
      Account fromAccount=null;
      Account toAccount=null;
        
      for(Account A:accounts){
         if(A.getNameOfUser().equalsIgnoreCase(fromAccountID)){
               fromAccount=A;
               first=true;
         }
         if(A.getNameOfUser().equalsIgnoreCase(toAccountID)){
            toAccount=A;
            second=true;
         }
      }
      if(first && second){
         if(fromAccount.withdraw(balance)){
            toAccount.deposit(balance);
            return true;
         }
         else{
            System.out.println("Insufficient Balance in Account "+fromAccountID);
            return false;
         }
      }
      else{
         if(!first){
            System.out.println("Account "+fromAccountID+" not found.\n");
         }
         else{
            System.out.println("Account "+toAccountID+" not found.\n");
         }
      return false;
      }
   }

   public void addInterest(){
      SavingsAccount savAcc=null;
      for(Account A:accounts){
         if(A.getClass().getName().equalsIgnoreCase("SavingsAccount")){
            savAcc=(SavingsAccount) A;
            savAcc.addInterest(savingsInterestRate);
         }
      }
   }
   
   public void reset(){
      CheckingAccount checkAcc=null;
      for(Account A:accounts){
         if(A.getClass().getName().equalsIgnoreCase("CheckingAccount")){
            checkAcc=(CheckingAccount) A;
            checkAcc.resetChecksUsed();
         }
      }
   }

   public boolean writeCheck(String accountID,double amount){
      CheckingAccount checkAcc=null;
      for(Account A:accounts){
         if(A.getNameOfUser().equalsIgnoreCase(accountID)){
            if(A.getClass().getName().equalsIgnoreCase("CheckingAccount")){
               checkAcc=(CheckingAccount) A;
               return checkAcc.withdrawUsingCheck(amount);
            }
            else{
               System.out.println("Invalid operation for savings accounts.\n");
               return false;
            }
         }
      }
      System.out.println("Account Not Found!");
      return false;
   }

   public ArrayList<Account> getAccounts() {
      return accounts;
   }

   public void setAccounts(ArrayList<Account> accounts) {
      this.accounts = accounts;
   }
   public String getBankNameBrand() {
      return bankNameBrand;
   }

   public void setBankNameBrand(String bankNameBrand) {
      this.bankNameBrand = bankNameBrand;
   }

   public Account search(String nameOfUser){
      for(Account A:accounts){
         if(A.getNameOfUser().equalsIgnoreCase(nameOfUser)){
            return A;
         }
      }
      return null;
   }

   public void printFile() throws IOException{
      FileWriter  writer      = new FileWriter("out.txt");
      PrintWriter printWriter = new PrintWriter(writer);
      printWriter.println("Bank Name:\t"+this.bankNameBrand);
      printWriter.println("Accounts Information:");
      String h1="Account Holder";
      String h2="Balance";
      String h3="Account Type";
      String h4="Checks Used";
      printWriter.printf("%-15s %15s %15s %15s\n", h1,h2,h3,h4);

      CheckingAccount checkAcc=null;
      SavingsAccount savAcc=null;
      String Checking="Checking";
      String Savings="Savings";
      for(Account A:accounts){
         if(A.getClass().getName().equalsIgnoreCase("CheckingAccount")){
            checkAcc=(CheckingAccount) A;
            printWriter.printf("%-15s %15.2f %15s %15d\n", checkAcc.getNameOfUser(),checkAcc.getBalance(),Checking,checkAcc.getChecksUsed());  
         }
         else{
            savAcc=(SavingsAccount) A;
            printWriter.printf("%-15s %15.2f %15s\n", savAcc.getNameOfUser(),savAcc.getBalance(),Savings);  
         }
      } 
      printWriter.close();
      writer.close();
   }

   public void printTable(){
      System.out.println("Bank Name:\t"+"    "+this.bankNameBrand+"\n");
      System.out.println("Accounts Information:");
      String h1="Account Holder";
      String h2="Balance";
      String h3="Account Type";
      String h4="Checks Used        ";
      System.out.printf("%-14s %12s %16s   %1s \n", h1,h2,h3,h4);
      CheckingAccount checkAcc=null;
      SavingsAccount savAcc=null;
      String Checking="Checking";
      String Savings="Savings";
      for(Account A:accounts){
         if(A.getClass().getName().equalsIgnoreCase("CheckingAccount")){
            checkAcc=(CheckingAccount) A;
            System.out.printf("%-19s $%7.2f %12s%8d\n", checkAcc.getNameOfUser(),checkAcc.getBalance(),Checking,checkAcc.getChecksUsed());  
         }
         else{
            savAcc=(SavingsAccount) A;
            System.out.printf("%-19s $%-11.2f %s\n", savAcc.getNameOfUser(),savAcc.getBalance(),Savings);  
         }
      } 
   }
}
