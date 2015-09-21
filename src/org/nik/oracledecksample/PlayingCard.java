package org.nik.oracledecksample;

public class PlayingCard implements Card {

	private Card.Rank rank;
	private Card.Suit suit;

	public PlayingCard(Card.Rank rank, Card.Suit suit) {
		this.rank = rank;
		this.suit = suit;

	}

	public Card.Suit getSuit() {
		return this.suit;
	}

	public Card.Rank getRank() {
		return this.rank;
	}

	// Implement the equals method
	public boolean equals(Object obj) {
		if (obj instanceof Card) {
			if (((Card) obj).getRank() == this.getRank()
					&& ((Card) obj).getSuit() == this.getSuit()) {
				return true;
			} else {
				return false;
			}

		} else {
			return false;
		}
	}

	// Override the hashcode method.
	public int hashCode() {
		return ((suit.value() - 1) * 13) + rank.value();
	}

	@Override
	public int compareTo(Card o) {
		return this.hashCode() - o.hashCode();
	}

	public String toString() {
		return this.rank.text() + " of " + this.suit.text();
	}

	public static void main(String[] args) {
		PlayingCard p1 = new PlayingCard(Rank.ACE, Suit.DIAMONDS);
		PlayingCard p2 = new PlayingCard(Rank.QUEEN, Suit.SPADES);

		// Use the toString Method
		System.out.println(p1.toString());
		System.out.println(p2.toString());
		
		// Use the equals method
		if (p1.equals(p2)) {
			System.out.println("Match");
		} else {
			System.out.println("No Match");
		}
		
		// Print  the hashCode of a playing card
		System.out.println(p1.hashCode());
		
		// Compare 2 cards (Read more about this)
		System.out.println(p1.compareTo(p1));
		

	}

}
