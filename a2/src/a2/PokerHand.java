package a2;

public interface PokerHand {
	
	Card[] getCards();
	boolean contains(Card c);
	
	boolean isOnePair();
	boolean isTwoPair();
	boolean isThreeOfAKind();
	boolean isStraight();
	boolean isFlush();
	boolean isFullHouse();
	boolean isFourOfAKind();
	boolean isStraightFlush();

	int getHandTypeValue();
	int getHandRank();
	
	int compareTo(PokerHand other);
	
}

/*
 * 
 * Rank sorting a card array
   Here is a snippet of code that sorts an array of Card objects by rank that you might find helpful to pattern your code after. 
   The code snippet below assumes that cards is an array of Card objects.



  for (int j=i+1; j<cards.length; j++) {
    if (cards[i].getRank() > cards[j].getRank()) {
      Card tmp = cards[i];
      cards[i] = cards[j];
      cards[j] = tmp;
    }
  }
}
*/