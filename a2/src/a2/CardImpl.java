package a2;

public class CardImpl implements Card {
	private int rank;
	private Suit suite;
	
	public CardImpl(int rank, Card.Suit suite) {
		if((rank!=2)&&(rank!=3)&&(rank!=4)&&(rank!=5)&&(rank!=6)&&(rank!=7)&&(rank!=8)&&(rank!=9)&&(rank!=10)&&(rank!=11)&&(rank!=12)&&(rank!=13)&&(rank!=14)){
			throw new RuntimeException();			
		}
		this.rank = rank;
		this.suite =suite ;
	}
	
	public int getRank() {
		return this.rank;
	}
	public Card.Suit getSuit(){
		return this.suite;
	}

	public String toString() {
		String Rank = null;
//		String Suit = suitToString(suite);
		if (rank == 14) {
			Rank = "Ace";
		}else if(rank == 2) {
			Rank = "Two";
		}else if (rank ==3) {
			Rank = "Three";
		}else if (rank == 4) {
			Rank = "Four";
		}else if (rank == 5) {
			Rank = "Five";
		}else if (rank == 6) {
			Rank = "Six";
		}else if (rank == 7) {
			Rank = "Seven";
		}else if (rank == 8) {
			Rank = "Eight";
		}else if (rank == 9) {
			Rank = "Nine";
		}else if (rank == 10) {
			Rank = "Ten";
		}else if (rank == 11) {
			Rank = "Jack";
		}else if (rank == 12) {
			Rank = "Queen";
		}else if (rank == 13) {
			Rank = "King";
		}
		
		return Rank +" of " + Card.suitToString(suite);
	
	}
/*
	public boolean equals(Card other) {
		int thisRank = this.getRank();
		int otherRank = other.getRank();
		Card.Suit thisSuit = this.getSuit();
		Card.Suit otherSuit = other.getSuit();
		
		if((thisRank == otherRank) && (thisSuit == otherSuit)) {
		return true;
		}
		else return false;
	}

	*/
	public boolean equals(Card other) {
		if((this.rank == other.getRank()) && (this.suite == other.getSuit())) {
			return true;
		}
		return false;
	}
	
	
}






