package com.java.low.level.design.VendingStates.impl;

import com.java.low.level.design.Coin;
import com.java.low.level.design.Item;
import com.java.low.level.design.VendingMachine;
import com.java.low.level.design.VendingStates.State;

import java.util.List;

public class DispenseState implements State {
    public DispenseState(VendingMachine machine, int codeNumber) throws Exception {
        System.out.println("Currently VM is in DispenseState");
        dispenseProduct(machine, codeNumber);
    }

    @Override
    public void clickOnInsertCoinButton(VendingMachine machine) throws Exception {
        throw new Exception("Insert coin button cannot clicked on Dispense State");
    }

    @Override
    public void clickOnStartProductSelectionButton(VendingMachine machine) throws Exception {
        throw new Exception("Product selection button cannot be clicked in Dispense State");
    }

    @Override
    public void insertCoin(VendingMachine machine, Coin coin) throws Exception {
        throw new Exception("Coin cannot be inserted in Dispense State");
    }

    @Override
    public void chooseProduct(VendingMachine machine, int codeNumber) throws Exception {
        throw new Exception("Product cannot be chosen in Dispense State");
    }

    @Override
    public Item dispenseProduct(VendingMachine machine, int codeNumber) throws Exception {
        System.out.println("Product has been dispensed");
        Item item = machine.getInventory().getItem(codeNumber);
        machine.getInventory().updateSoldOutItem(codeNumber);
        machine.setVendingMachineState(new IdleState(machine));
        return item;
    }

    @Override
    public List<Coin> refundFullMoney(VendingMachine machine) throws Exception {
        throw new Exception("Refund cannot be done in Dispense State");
    }

    @Override
    public int getChange(int returnChangeMoney) throws Exception {
        throw new Exception("refund cannot be happen");
    }

    @Override
    public void updateInventory(VendingMachine vendingMachine, Item item, int codeNumber) throws Exception {
        throw new Exception("Inventory cannot be updated in Dispense State");
    }
}
