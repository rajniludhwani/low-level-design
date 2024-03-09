package com.java.low.level.design.VendingStates.impl;

import com.java.low.level.design.Coin;
import com.java.low.level.design.Item;
import com.java.low.level.design.VendingMachine;
import com.java.low.level.design.VendingStates.State;
import java.util.List;

public class HasMoneyState implements State {

  public HasMoneyState() {
    System.out.println("Currently VM is in HasMoneyState");
  }

  @Override
  public void clickOnInsertCoinButton(VendingMachine machine) throws Exception {
    return;
  }

  @Override
  public void clickOnStartProductSelectionButton(VendingMachine machine) throws Exception {
    machine.setVendingMachineState(new SelectionState());
  }

  @Override
  public void insertCoin(VendingMachine machine, Coin coin) throws Exception {
    System.out.println("Accepted the coin");
    machine.getCoins().add(coin);
  }

  @Override
  public void chooseProduct(VendingMachine machine, int codeNumber) throws Exception {
    throw new Exception("you need to click on start product selection button first");
  }

  @Override
  public Item dispenseProduct(VendingMachine machine, int codeNumber) throws Exception {
    throw new Exception("Product cannot be dispensed in hasMoney state");
  }

  @Override
  public List<Coin> refundFullMoney(VendingMachine machine) throws Exception {
    System.out.println("Returned the full amount back in the coin dispense tray");
    machine.setVendingMachineState(new IdleState(machine));
    return machine.getCoins();
  }

  @Override
  public int getChange(int returnChangeMoney) throws Exception {
    throw new Exception("you cannot get change in hasMoney state");
  }

  @Override
  public void updateInventory(VendingMachine vendingMachine, Item item, int codeNumber)
      throws Exception {
    throw new Exception("you cannot update inventory in hasMoney state");
  }
}
