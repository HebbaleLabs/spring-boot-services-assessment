package com.echidna.assessment.service;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class TestCreditScoreUtility {

  private static int COUNTER = 0;

  @EventListener
  public void handleCreditScoreRequest(CreditScoreRequestEvent creditScoreRequestEvent) {
    System.out.println("Event captured");
    COUNTER++;
  }

  public int getCOUNTER() {
    return COUNTER;
  }
}
