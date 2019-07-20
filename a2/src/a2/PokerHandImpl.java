package a2;

import a2.Card.Suit;

public class PokerHandImpl implements PokerHand {
	
//	private int num_cards = 5;
	private  Card[] cards ;
	private int hand_value;
	private int hand_rank;

	public PokerHandImpl(Card[] cards) {
		if(cards == null) throw new RuntimeException();
		if(cards.length != 5)
			throw new RuntimeException();
		for (int i=0; i<cards.length; i++) {
			  for (int j=i+1; j<cards.length; j++) {
			    if (cards[i].getRank() > cards[j].getRank()) {
			      Card tmp = cards[i];
			      cards[i] = cards[j];
			      cards[j] = tmp;	
			    }
			 }
		  }
		this.cards = cards.clone();

		}
	public Card[] getCards() {
		
		return cards.clone();
	}
	public boolean contains(Card c) {
		for(int q=0; q<cards.length; q++) {
			if (c.equals(cards[q])) {
				return true;
			}	
		}
		return false;
	}
	/*public boolean isOnePair() {
		for(int i = 0; i < 5; i++) {
			if(cards[i].getRank() == cards[i + 1].getRank()) {
				for(int j = i ; j < 5; j ++)
			}
				
		}
	}
	*/
	public boolean isHighCard() {
		if((isOnePair() == false) && (isTwoPair() == false)
			&&(isThreeOfAKind() == false) && (isStraight()==false)
			&&(isFlush()== false) && (isFullHouse()==false)
			&&(isFourOfAKind() == false) && (isStraightFlush()==false)) {
			return true;
		}else return false;
	}
	
	public boolean isOnePair() {
		int counter = 0;
		for(int i=0; i<4; i++) {
			if(cards[i].getRank() == cards[i+1].getRank()) {
				counter ++;
			}
		}
		if(counter == 1) {
			return true;
	}else return false;
	}
	
	public int rankOnePair() {
		if(isOnePair()) {
			if(cards[0].getRank() == cards[1].getRank()) {
				hand_rank = cards[0].getRank();
			}else if(cards[1].getRank() == cards[2].getRank()) {
				hand_rank = cards[1].getRank();
			}else if(cards[2].getRank() == cards[3].getRank()) {
				hand_rank = cards[2].getRank();
			}else if(cards[3].getRank() == cards[4].getRank()) {
				hand_rank = cards[3].getRank();
			}
		}
		return hand_rank;
	}
	
	/*public int rankOnePair() {
		if(isOnePair()) {
			for(int i=0; i<4; i++) {
				if(cards[i].getRank() == cards[i+1].getRank()) {
					hand_rank = cards[i].getRank();
				}
			}	
		}
		return hand_rank;
	}
	
		*/	
	/*
	public boolean isTwoPair() {
		int counter = 0;
		for(int i=0; i<4; i++) {
			if(cards[i].getRank() == cards[i+1].getRank()) {
				counter ++;
			}
		}
		if((counter == 2) && (isThreeOfAKind() == false) && (isFourOfAKind() == false)) {
			return true;
		}else return false;
	}
	*/
	public boolean isTwoPair() {
		boolean T = false;
		if(((cards[0].getRank() == cards[1].getRank()) && (cards[2].getRank() == cards[3].getRank()))
			||((cards[0].getRank() == cards[1].getRank()) && (cards[3].getRank() == cards[4].getRank()))
			||((cards[1].getRank() == cards[2].getRank()) && (cards[3].getRank() == cards[4].getRank()))) 
		{
			T = true;
		}
		else T = false;
		
		if((isFullHouse())||(isFourOfAKind()) ){
			T=false;
		}
		return T;
				
		
	}
	
	
	
	public int rankTwoPair() {
		if(isTwoPair())
		if((cards[0].getRank() == cards[1].getRank())
			&& (cards[2].getRank() == cards[3].getRank()))
		{
			hand_rank = cards[2].getRank();
		}else if((cards[0].getRank() == cards[1].getRank())
				&& (cards[3].getRank() == cards[4].getRank()))
		{
			hand_rank = cards[3].getRank();
		}else if((cards[1].getRank() == cards[2].getRank())
				&& (cards[3].getRank() == cards[4].getRank()))
		{
			hand_rank = cards[3].getRank();
		}
		return hand_rank;
	}
	
	public boolean isThreeOfAKind() {
		int counter = 0;
		for(int i=0; i<4; i++) {
			if(cards[i].getRank() == cards[i+1].getRank()) {
				counter ++;
			}
		}
		if( (counter == 2) && (isTwoPair() == false)) {
			return true;
		}else return false;
	}
	

		
	
	public int rankThreeOfAKind() {
		if(isThreeOfAKind()) {
		if(cards[0].getRank() == cards[1].getRank()) {
			hand_rank = cards[0].getRank();
		}else if(cards[3].getRank() == cards[4].getRank()) {
			hand_rank = cards[4].getRank();
		}else hand_rank = cards[2].getRank();
	}
		return hand_rank;
	}
	
	
	public boolean isStraight() {
		//if(cards[0].getRank()==1 && cards[1].getRank()==2
			//	&& cards[2].getRank()==3 && cards[3].getRank()==4
			//	&& cards[4].getRank()==5)
		if( (cards[0].getRank()==2) && (cards[1].getRank()==3) && (cards[2].getRank()==4) && (cards[3].getRank() == 5) && (cards[4].getRank() == 14)) {
			return true;
		}
		int first_card = cards[0].getRank();
		int flag = 0;
		for(int i=1; i<5; i++) {
			if (cards[i].getRank() == first_card + i) {
				flag++;
			}
		}
		if(flag == 4) {
			return true;
		}
		return false;
			
		}
	
	
	
	public int rankisStraight() {
		if(isStraight()) {
		if((cards[0].getRank()==2) && (cards[1].getRank()==3)
					&& (cards[2].getRank()==4) && (cards[3].getRank()==5)
					&& (cards[4].getRank()==14)) {
			hand_rank = 5;
		}
		else hand_rank = cards[4].getRank();
		}
		return hand_rank;
	}
	
	public boolean isFlush() {
		
		Suit sameSuit = cards[0].getSuit();
		int counter = 0;
		for(int i=1; i<5; i++) {
			if(cards[i].getSuit() == sameSuit)
				counter ++;
		}
		if (counter == 4){
			return true;
		}else return false;
	}
	
	/*public boolean isFlush() {
		Suit sameSuit = cards[0].getSuit();
		if ((cards[1].getSuit() == sameSuit) && (cards[2].getSuit() == sameSuit)
			&& (cards[3].getSuit() == sameSuit) && (cards[4].getSuit() == sameSuit)) {
			return true;
		}else return false;
	}
	*/
	
	public int rankisFlush() {
		if(isFlush()) {
		hand_rank = cards[4].getRank();
		}
		return hand_rank;
	}
	
	/*
	public boolean isFullHouse() {
		if((isThreeOfAKind() == true) && (isOnePair() == true)) {
			return true;
		}else return false;
	}
	*/
	
	public boolean isFullHouse() {
		boolean flag = false;
		if(((cards[0].getRank() == cards[1].getRank()) && (cards[2].getRank() == cards[3].getRank()) && (cards[3].getRank() == cards[4].getRank()))
		 || ((cards[0].getRank() == cards[1].getRank()) && (cards[1].getRank() == cards[2].getRank()) && (cards[3].getRank() == cards[4].getRank()))) {
			flag = true;}
		if (isFourOfAKind()) {
			flag = false;
		}
		return flag;
	}
	
	
	
	public int rankisFullHouse() {
		if(isFullHouse()) {
		if(cards[1].getRank() == cards[2].getRank()) {
			hand_rank = cards[0].getRank();
		}else if (cards[1].getRank() != cards[2].getRank()) {
			hand_rank = cards[4].getRank();
		}
		}
		
		return hand_rank;
	}
	
	
	public boolean isFourOfAKind() {
		int sameRank = cards[2].getRank();
		int counter = 0;
		
		for(int i=0; i<5; i++) {
			if(cards[i].getRank() == sameRank) {
				counter ++;
			}
		}
		if (counter == 4) {
			return true;
		}else return false;
	}
	
	/*
	public boolean isFourOfAKind() {
		if(((cards[0].getRank() == cards[1].getRank()) && (cards[1].getRank() == cards[2].getRank()) && (cards[2].getRank() == cards[3].getRank()))
				 || ((cards[1].getRank() == cards[2].getRank()) && (cards[2].getRank() == cards[3].getRank()) && (cards[3].getRank() == cards[4].getRank()))) {
					return true;
				}else return false;
	}
	*/
	
	
	public int rankisFourOfAKind() {
		if(isFourOfAKind()) {
		if(cards[3].getRank() == cards[4].getRank()) {
			hand_rank = cards[4].getRank();
		}else if(cards[3].getRank() != cards[4].getRank()) {
				hand_rank = cards[0].getRank();
		}
	}
		return hand_rank;
	}
	
	public boolean isStraightFlush() {
		if((isStraight() == true) && (isFlush() == true)){
			return true;
		}else return false;
	}
	
	public int rankisStraightFlush() {
		hand_rank = cards[4].getRank();
		return hand_rank;
	}

	
	
	
	
	public int getHandTypeValue() {
		if(isHighCard()) {
			hand_value = 1;
		}else if(isOnePair()) {
			hand_value = 2;
		}else if(isTwoPair()) {
			hand_value = 3;
		}else if(isThreeOfAKind()) {
			hand_value = 4;
		}else if((isStraight()) && (isStraightFlush()==false)) {
			hand_value = 5;
		}else if((isFlush()) && (isStraightFlush()==false)){
			hand_value = 6;
		}else if(isFullHouse() ) {
			hand_value = 7;
		}else if(isFourOfAKind()) {
			hand_value = 8;
		}else if(isStraightFlush()){
			hand_value = 9;
		}
		return hand_value;
		
	
	}
	public int getHandRank() {
		int rrank= 0;
		if(isHighCard()) {
			rrank = cards[4].getRank();
		}else if (isOnePair()) {
			rrank = rankOnePair();
		}else if (isTwoPair()) {
			rrank = rankTwoPair();
		}else if (isThreeOfAKind()) {
			rrank = rankThreeOfAKind();
		}else if (isStraight()) {
			rrank = rankisStraight();
		}else if (isFlush()) {
			rrank = rankisFlush();
		}else if (isFullHouse()) {
			rrank = rankisFullHouse();
		}else if (isFourOfAKind()) {
			rrank = rankisFourOfAKind();
		}else if (isStraightFlush()) {
			rrank = rankisStraightFlush();
		}
		return rrank;
		
	}
	public int compareTo(PokerHand other) {
		int result = 0;
		
		int thisHandValue = this.getHandTypeValue();
		int otherHandValue = other.getHandTypeValue();
		int thisHandRank = this.getHandRank();
		int otherHandRank = other.getHandRank();
		
		if(thisHandValue < otherHandValue) {
			result = -1;
		}else if(thisHandValue > otherHandValue){
			result = 1;
		}else {
			if(thisHandRank < otherHandRank) {
				result = -1;
			}else if(thisHandRank > otherHandRank) {
				result = 1;
			}
		}
		if((thisHandValue == otherHandValue) && (thisHandRank == otherHandRank)) 
			result = 0;
			
		return result;
		
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
		