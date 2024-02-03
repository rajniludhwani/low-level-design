package com.java.low.level.design.VendingStates.impl;

import com.java.low.level.design.Coin;
import com.java.low.level.design.Item;
import com.java.low.level.design.VendingMachine;
import com.java.low.level.design.VendingStates.State;

import java.util.List;

public class SelectionState implements State {
    public SelectionState() {
        System.out.println("Currently VM is in Selection State");
    }

    @Override
    public void clickOnInsertCoinButton(VendingMachine machine) throws Exception {
        throw new Exception("You cannot click on insert coin button in selection state");
    }

    @Override
    public void clickOnStartProductSelectionButton(VendingMachine machine) throws Exception {
        return;
    }

    @Override
    public void insertCoin(VendingMachine machine, Coin coin) throws Exception {
        throw new Exception("You cannot insert coin in selection state");
    }

    @Override
    public void chooseProduct(VendingMachine machine, int codeNumber) throws Exception {
        //1. get item of the codeNumber
        Item item = machine.getInventory().getItem(codeNumber);

        //2. total amount paid by user
        int paidByUser = 0;
        for(Coin coin : machine.getCoins()) {
            paidByUser += coin.value;
        }

        //3. compare product price and amount paid by user
        if(paidByUser < item.getPrice()) {
            System.out.println("Insufficient Amount, Product you selected is for price: " + item.getPrice() + " and you paid: " + paidByUser);
            refundFullMoney(machine);
            throw new Exception("Insufficient Amount");
        } else {
            if(paidByUser > item.getPrice()) {
                getChange(paidByUser - item.getPrice());
            }
            machine.setVendingMachineState(new DispenseState(machine, codeNumber));
        }
    }

    @Override
    public Item dispenseProduct(VendingMachine machine, int codeNumber) throws Exception {
        throw new Exception("Product cannot be dispensed in selection state");
    }

    @Override
    public List<Coin> refundFullMoney(VendingMachine machine) throws Exception {
        System.out.println("Return the full amount back in Coin Dispense Tray");
        machine.setVendingMachineState(new IdleState(machine));
        return machine.getCoins();
    }

    @Override
    public int getChange(int returnChangeMoney) throws Exception {
        System.out.println("return the change in the Coin Dispense Tray");
        return returnChangeMoney;
    }

    @Override
    public void updateInventory(VendingMachine vendingMachine, Item item, int codeNumber) throws Exception {
        throw new Exception("Inventory cannot be updated in Selection State");
    }
}
