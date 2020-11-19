package space.harbour.java.hw8;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;

public class ATMMachine {
    private Map<Integer, Integer> bill;

    public ATMMachine(Map<Integer, Integer> bills){
        bill = bills;
    }

    private boolean isCorrectAmt(int amt){
        return amt%10==0 ? true : false;
    }

    private synchronized void reduceBalance(int denomination, int noToReduce){
        int amt = bill.get(denomination);
        bill.remove(denomination);
        bill.put(denomination, amt-noToReduce);
    }

    public synchronized Integer getATMBalance(){
        int balance = 0;
        for(Integer denominator: bill.keySet()){
            balance = balance + (denominator * bill.get(denominator));
        }
        return balance;
    }

    public synchronized Map<Integer, Integer> withdrawAmt(int amt){
        Map<Integer, Integer> returnedMap = new HashMap<Integer, Integer>();
        TreeSet<Integer> denominations = new TreeSet<Integer>(bill.keySet());
        Iterator<Integer> iter = denominations.descendingIterator();

        while(amt > 0 && amt < getATMBalance()){
            int denomination = iter.next();
            int noOfNotes = amt<denomination ? 0 : amt/denomination;
            returnedMap.put(denomination, noOfNotes);
            amt = amt - (denomination * noOfNotes);
            reduceBalance(denomination, noOfNotes);
        }
        return returnedMap;
    }

    public static void main(String agrs[]){
        Map<Integer, Integer> bills = new HashMap<Integer, Integer>();
        bills.put(5, 20);
        bills.put(10, 20);
        bills.put(20, 20);
        bills.put(50, 20);

        ATMMachine atm = new ATMMachine(bills);
        ATMUser user = new ATMUser(atm, 1900, 1800);
        Thread userThread1 = new Thread(user);
        userThread1.start();
    }
}