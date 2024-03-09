package com.java.low.level.design.VendingStates;

import com.java.low.level.design.Coin;
import com.java.low.level.design.Item;
import com.java.low.level.design.VendingMachine;
import java.util.List;

public interface State {

  void clickOnInsertCoinButton(VendingMachine machine) throws Exception;

  void clickOnStartProductSelectionButton(VendingMachine machine) throws Exception;

  void insertCoin(VendingMachine machine, Coin coin) throws Exception;

  void chooseProduct(VendingMachine machine, int codeNumber) throws Exception;

  Item dispenseProduct(VendingMachine machine, int codeNumber) throws Exception;

  List<Coin> refundFullMoney(VendingMachine machine) throws Exception;

  int getChange(int returnChangeMoney) throws Exception;

  void updateInventory(VendingMachine vendingMachine, Item item, int codeNumber) throws Exception;


}
