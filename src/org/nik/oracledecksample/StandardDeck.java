package org.nik.oracledecksample;

import java.util.ArrayList;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.*;

public class StandardDeck implements Deck {

	// Variables and Constants
	private List<Card> entireDeck;

	// Constructor-1
	public StandardDeck(List<Card> existingList) {
		this.entireDeck = existingList;
	}

	// Constructor-2 (Default)
	public StandardDeck() {
		this.entireDeck = new ArrayList<Card>();
		for (Card.Suit s : Card.Suit.values()) {
			for (Card.Rank r : Card.Rank.values()) {
				this.entireDeck.add(new PlayingCard(r, s));
			}
		}
	}

	@Override
	public List<Card> getCards() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Deck deckFactory() {
		return new StandardDeck(new ArrayList<Card>());
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return entireDeck.size();
	}

	@Override
	public void addCard(Card card) {
		this.entireDeck.add(card);

	}

	@Override
	public void addCards(List<Card> cards) {
		this.entireDeck.addAll(cards);

	}

	@Override
	public void addDeck(Deck deck) {
		List<Card> listToAdd = deck.getCards();
		entireDeck.addAll(listToAdd);

	}

	@Override
	public void shuffle() {
		Collections.shuffle(entireDeck);

	}

	@Override
	public void sort() {
		// TODO Auto-generated method stub
		Collections.sort(entireDeck);

	}

	@Override
	public void sort(Comparator<Card> c) {
		// TODO Auto-generated method stub

	}



	@Override
	public Map<Integer, Deck> deal(int players, int numberOfCards)
			throws IllegalArgumentException {

		int cardsDealt = players * numberOfCards;
		int sizeOfDeck = entireDeck.size();
		if (cardsDealt > sizeOfDeck) {
			throw new IllegalArgumentException("Number of players ( " + players
					+ ") times number of " + "cards to be dealt ("
					+ numberOfCards
					+ ") is greater than the number of cards in the deck ("
					+ " " + sizeOfDeck + ").");
		}
		
		Map<Integer, List<Card>> dealtDeck = entireDeck
		           .stream()
		           .collect(
		               Collectors.groupingBy(
		                   card -> {
		                       int cardIndex = entireDeck.indexOf(card);
		                       if (cardIndex >= cardsDealt) return (players + 1);
		                       else return (cardIndex % players) + 1;
		                   }));
		
		// Convert Map<Integer, Lits<Card>> to Map<Integer, Deck>
		Map<Integer, Deck> mapToReturn = new HashMap<>();
		
		for(int i=1; i<= (players+1); i++){
			Deck currentDeck = deckFactory();
			currentDeck.addCards(dealtDeck.get(i));
			mapToReturn.put(i, currentDeck);
		}
				

		return mapToReturn;
	}
	
	@Override
	public String deckToString(){
		return this.entireDeck
				.stream()
				.map(Card::toString)
				.collect(Collectors.joining("\n"));
	}
	
	public static void main(String[] args)
	{
		StandardDeck myDeck = new StandardDeck();
		System.out.println("Creating a deck :");
		myDeck.sort();
		System.out.println("Sorted Deck");
		System.out.println(myDeck.toString());
		myDeck.shuffle();
		myDeck.sort(new SortByRankThenSuit());
		System.out.println("Sorted by rank then Suit");
		System.out.println(myDeck.toString());
		myDeck.shuffle();
		myDeck.sort(Comparator.comparing(Card::getRank).thenComparing(Comparator.comparing(Card::getSuit)));
		System.out.println("Sorted by rank and then Suit " + "with static and default methods");
		System.out.println(myDeck.deckToString());
		myDeck.sort(Comparator.comparing(Card::getRank).reversed().thenComparing(Comparator.comparing(Card::getSuit)));
		System.out.println("Stored by rank reversed, then by Suit" + "with static and default methods");
		System.out.println(myDeck.deckToString());
		
	}

}
