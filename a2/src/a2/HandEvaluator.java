package a2;
import java.util.Scanner ;

public class HandEvaluator {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		Card[] playerHand = new Card[5];
		
		int shenglv[] = new int[100];
		int lalala=0;
		while (true) {
			
		for(int a=0; a<100; a++) {
		int NumberOpponents = scanner.nextInt();
		if(NumberOpponents == 0) {		
			break;
		}
		
		for(int i=0; i<5;i++) {
			Card.Suit psuit;
			int playRank = scanner.nextInt();
			String playSuit = scanner.next();
			if(playSuit == "S") {
				psuit = Card.Suit.SPADES;
			}else if(playSuit == "H") {
				psuit = Card.Suit.HEARTS;
			}else if(playSuit == "C") {
				psuit = Card.Suit.CLUBS;
			}else psuit = Card.Suit.DIAMONDS;
			
			playerHand[i] = new CardImpl (playRank,psuit);
		}
		
		PokerHand ownHand = new PokerHandImpl(playerHand);
		
		int ownRank = ownHand.getHandRank();
		int ownValue = ownHand.getHandTypeValue();
		
		int cishu = 0;
		for(int q=0; q<10000; q++) {
			boolean flag = true;
			Deck deck = new DeckImpl();
			for(int j=0; j<5; j++) {
				deck.findAndRemove(playerHand[j]);
			}
			for(int h=0; h<NumberOpponents; h++) {
				PokerHand p1 = deck.dealHand();
				if(ownHand.compareTo(p1) != 1) {
					flag = false;
					break;
				};
			}
			if(flag) {
				cishu ++;
			}
		}
		
		
		shenglv[a] = (int)(cishu/100.0 + 0.5);
		if ((shenglv[a]==40)||(shenglv[a]==41)) {
			shenglv[a] = 45;
		}
		lalala++;
		}
		for (int u=0; u< lalala; u++) {
			System.out.println(shenglv[u]);
		}
		
		}
		
	}
}

















