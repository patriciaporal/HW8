package space.harbour.java.hw8;

public class ATMUser implements Runnable {

    private final int balance;
    ATMMachine atm;
    int amountToWithdraw;

    public ATMUser(ATMMachine atm, int amountToWithdraw, int balance) {
        this.atm = atm;
        this.amountToWithdraw = amountToWithdraw;
        this.balance = balance;
    }

    @Override
    public void run() {
        System.out.println("Current Balance: £" + balance);
        System.out.println("Withdrawing: £" + amountToWithdraw);
        if (amountToWithdraw < balance) {
            System.out.println("Amount Withdrawn: " + atm.withdrawAmt(amountToWithdraw));
        }
        if (amountToWithdraw > balance) {
            System.out.println("Invalid Amount");
        } else {
            System.out.println("New Balance : " + ( balance - amountToWithdraw ));
        }
    }
}
