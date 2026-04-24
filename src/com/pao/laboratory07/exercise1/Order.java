package com.pao.laboratory07.exercise1;

import com.pao.laboratory07.exercise1.exceptions.CannotCancelFinalOrderException;
import com.pao.laboratory07.exercise1.exceptions.CannotRevertInitialOrderStateException;
import com.pao.laboratory07.exercise1.exceptions.OrderIsAlreadyFinalException;
import java.util.List;
import java.util.ArrayList;

public class Order {
  private String state;
  private static final String[] states = {
    "PLACED",
    "PROCESSED",
    "SHIPPED",
    "DELIVERED"
  };
  private Integer currentState;
  private List<Integer> history = new ArrayList<Integer>();

  public Order(OrderState state) {
    this.state = state.toString();
    currentState = 0;
    for (int i = 0; i < 4; ++i) {
      if (states[i].equals(state.toString())) {
        currentState = i;
        break;
      }
    }
    history.add(currentState);
  }

  public void nextState() {
    if (state.equals("CANCELED") || 
    state.equals("DELIVERED")) {
      throw new OrderIsAlreadyFinalException();
    }
    currentState++;
    history.add(currentState);
    state = new String(states[currentState]);
    System.out.println("Order state updated to: " + state);
  }

  public void cancel() {
    if (state.equals("CANCELED") || 
    state.equals("DELIVERED")) {
      throw new CannotCancelFinalOrderException();
    }
    state = "CANCELED";
    System.out.println("Order state updated to: " + state);
  }

  public void undoState() {
    if (history.size() == 1) {
      throw new CannotRevertInitialOrderStateException();
    }
    currentState = history.get(history.size() - 2);
    history.remove(history.size() - 1);
    state = new String(states[currentState]);
    System.out.println("Order state updated to: " + state);
  }
}
