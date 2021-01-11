public class SavingsAccount extends Account {
   
   public SavingsAccount(String nameOfUser, double balance){
      super(nameOfUser, balance);
   }
    
    
   @Override
   public boolean withdraw(double amount) {
      amount=amount+2;
      if((this.getBalance()-amount)<10){
         return false;
      }
      else{
         this.balance=this.balance-amount;
         return true;
      }
   }
   
   
   @Override
   public void deposit(double amount) {
      this.balance=this.balance+amount;
   }

   public double addInterest(double rate){
      double interest=this.balance*rate/100; 
      this.balance=this.balance+interest;
      if (this.balance>10000)
         this.balance=this.balance+200.05;
      return interest;

      }      
   }
