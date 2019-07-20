package a2;


public class classTest {
	public static void main(String args[]) {
		Card two_of_hearts = new CardImpl(4, Card.Suit.HEARTS);
		Card three_of_clubs = new CardImpl(5, Card.Suit.HEARTS);
		Card four_of_spades = new CardImpl(6, Card.Suit.HEARTS);
		Card five_of_diamonds = new CardImpl(7, Card.Suit.HEARTS);
		Card six_of_hearts = new CardImpl(8, Card.Suit.HEARTS);
		Card ten_of_spades = new CardImpl(8, Card.Suit.SPADES);
		
		Card.Suit a = Card.Suit.HEARTS;
		
		PokerHand p1 = new PokerHandImpl(new Card[] {two_of_hearts, three_of_clubs, four_of_spades, five_of_diamonds, six_of_hearts});
		PokerHand p2 = new PokerHandImpl(new Card[] {two_of_hearts, ten_of_spades, three_of_clubs, five_of_diamonds, six_of_hearts});

		boolean is_p1_a_straight = p1.isStraight();   // Result is true because p1 is in fact a straight
		boolean is_p2_a_straight = p2.isStraight();   // Result is false because p2 is not a straight
		
		
		boolean test = p1.isStraightFlush();
		System.out.println(test);
		int ranktest = p1.getHandRank();	//最大的一张牌 
		System.out.println(ranktest);
		int handtest = p1.getHandTypeValue(); //级别
		System.out.println(handtest);
	
		
	}
}

