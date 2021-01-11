public class CheckingAccount extends Account {
    private int numberOfChecksUsed;
      
   public CheckingAccount(String nameOfUser, double balance) {
      super(nameOfUser, balance);
      this.numberOfChecksUsed=0;
   }

   @Override
   public boolean withdraw(double amount) {
      amount=amount+1;
      if((this.getBalance()-amount)<0){
         return false;
      }
      else{
         this.balance=this.balance-amount;
         return true;
     }
   }

   @Override
   public void deposit(double amount) {
      this.balance=this.balance-1;
      this.balance=this.balance+amount;
   }

   public void resetChecksUsed(){
      this.numberOfChecksUsed=0;
   }

   public int getChecksUsed(){
      return this.numberOfChecksUsed;
   }

   public boolean withdrawUsingCheck(double amount){
      if(this.numberOfChecksUsed>3){
         amount=amount+2;
      }
      if((this.getBalance()-amount)<-10){
         return false;
      }
      else{
      this.balance=this.balance-amount;
      this.numberOfChecksUsed++;
      return true;
      }
   }
}
