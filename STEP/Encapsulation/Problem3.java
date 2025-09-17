package Encapsulation;
public class Problem3 {
    static class SecureBankAccount {
        private final String accountNumber;
        private double balance;
        private int pin;
        private boolean isLocked;
        private int failedAttempts;

        private static final int MAX_FAILED_ATTEMPTS = 3;
        private static final double MIN_BALANCE = 0.0;

        public SecureBankAccount(String acc, double initial) {
            accountNumber = acc;
            balance = Math.max(initial, MIN_BALANCE);
            pin = 0; isLocked = false; failedAttempts = 0;
        }

        public String getAccountNumber() { return accountNumber; }
        public double getBalance() {
            if (isLocked) throw new IllegalStateException("Locked!");
            return balance;
        }
        public void setPin(int oldPin, int newPin) {
            if (pin == oldPin) { pin = newPin; failedAttempts = 0; }
            else incrementFailed();
        }
        public boolean validatePin(int entered) {
            if (entered == pin) { failedAttempts = 0; return true; }
            incrementFailed(); return false;
        }
        public void deposit(double amt, int p) {
            if (validatePin(p) && !isLocked) balance += amt;
        }
        public void withdraw(double amt, int p) {
            if (validatePin(p) && !isLocked && balance-amt>=MIN_BALANCE) balance -= amt;
        }
        public void transfer(SecureBankAccount t,double amt,int p) {
            if (validatePin(p) && !isLocked) { withdraw(amt,p); t.deposit(amt,p); }
        }
        private void incrementFailed() {
            failedAttempts++; if (failedAttempts>=MAX_FAILED_ATTEMPTS) isLocked=true;
        }
    }

    public static void main(String[] args) {
        SecureBankAccount acc1=new SecureBankAccount("A1",1000);
        SecureBankAccount acc2=new SecureBankAccount("A2",500);
        acc1.setPin(0,1234); acc2.setPin(0,5678);
        acc1.deposit(200,1234);
        acc1.withdraw(100,1234);
        acc1.transfer(acc2,50,1234);
        System.out.println("Acc1 Balance: "+acc1.getBalance());
        System.out.println("Acc2 Balance: "+acc2.getBalance());
    }
}
