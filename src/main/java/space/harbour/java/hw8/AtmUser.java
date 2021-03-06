package space.harbour.java.hw8;

public class AtmUser implements Runnable {

    private final int balance;
    AtmMachine atm;
    int amountToWithdraw;

    public AtmUser(AtmMachine atm, int amountToWithdraw, int balance) {
        this.atm = atm;
        this.amountToWithdraw = amountToWithdraw;
        this.balance = balance;
    }

    @Override
    public void run() {
        System.out.println("Current Balance: £" + balance);
        System.out.println("Withdrawing: £" + amountToWithdraw);
        if (amountToWithdraw < balance && amountToWithdraw < atm.getAtmBalance()) {
            System.out.println("Amount Withdrawn: " + atm.withdrawAmt(amountToWithdraw));
        }
        if (amountToWithdraw > atm.getAtmBalance() && amountToWithdraw > balance) {
            System.out.println("Invalid Amount");
        } else {
            System.out.println("New Balance : " + (balance - amountToWithdraw));
        }
    }
}
