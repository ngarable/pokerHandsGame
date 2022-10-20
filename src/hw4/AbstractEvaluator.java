package hw4;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import api.Card;
import api.Hand;
import api.IEvaluator;
import util.SubsetFinder;
/**
 *@author BlessingNgara
 */
/**
 * The class AbstractEvaluator includes common code for all evaluator types.
 * The OnePairEvaluator, CatchAllEvaluator, AllPrimeEvaluator, GeneralStraightEvaluator, NoOfAKindEvaluator and FullHouseEvaluator are extending AbstractEvaluator.
 * In OnePairEvaluator,the evaluator is checking if the cards are a pair (that is identical).
 * FullHouseEvaluator is checking if one set has 3 identical cards for example "AAA" and 2 identical cards for example "33" 
 * In FullHouseEvaluator, I created helper methods rearrange and sameRank
 * In FullHouseEvaluator, rearrange  method is responsible for rearranging the card by placing largest group first.
 * In FullHouseEvaluator, sameRank checks whether the cards in the array provided are all of the same rank
 * In AllPrimesEvaluator, I created helper method isPrime which was going to check if the rank of each card is a prime number.
 * In CatchAllEvaluator,the evaluator is checking if all sets of cards are being satisfied , it also checks if required cards is equal to the hand size.
 * I created another class NoOfAKind to reduce redundancy since FourOfAKind and ThreeOfAKind are literally doing the same thing except for the fact that 
 * FourOfAKind is looking for 4 identical cards and ThreeOfAKind is looking for 3 identical cards.
 * FourOfAKind and ThreeOfAKind classes  are extending NoOfAKind
 * I also created another Abstract class called GeneralStraightEvaluator.
 * StraightEvaluator and StraightFlushEvaluator are extending GeneralStraightEvaluator.
 * GeneralStraightEvaluator has two helper methods arrangeStraight and containsAce.
 * arrangeStraight method rearranges a straight hand depending on the max card rank in the case of five high straight ace is placed at the end
 * containsAce method checks if a list of cards provided contains aces
 * StraightEvaluator is checking if the cards rankings are in descending order for example "10, 9 , 8, 7 , 6" 
 * StraightFlush is checking if the cards rankings are in descending order and checks if they are of the same suit.
 * 
 * TODO: Expand this comment with an explanation of how your class hierarchy
 * is organized.
 */
public abstract class AbstractEvaluator implements IEvaluator {
	private String name;
	private int ranking;
	private int handSize;
	private int cardsRequired;
	//Protecting a constructor prevents the users from creating the instance of the class, outside the package
	protected AbstractEvaluator(String name, int ranking, int handsize, int cardsRequired) { 
		super();
		this.name = name;
		this.ranking = ranking;
		this.handSize = handsize;
		this.cardsRequired = cardsRequired;
	}
	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getRanking() {		
		return ranking;
	}

	@Override
	public int cardsRequired() {
		return cardsRequired ;
	}

	@Override
	public int handSize() {		
		return handSize;
	}

	public boolean canSubsetSatisfy(Card[] allCards) {
		if (allCards.length < cardsRequired()) {
			return false;
		}
		ArrayList<int[]> subsets = SubsetFinder.findSubsets(allCards.length, cardsRequired());
		for (int i = 0; i < subsets.size(); i++) {
			Card[] test = new Card[cardsRequired()];
			for (int j = 0; j < test.length; j++) {
				test[j] = allCards[subsets.get(i)[j]];
			}
			if (canSatisfy(test)) {
				return true;
			}
		}
		return false;
	}

	public Hand createHand(Card[] allCards, int[] subset) {
		if (allCards.length < handSize()) {
			return null;
		}
		Card[] cards = new Card[subset.length];
		for (int i = 0; i < subset.length; i++) {
			cards[i] = allCards[subset[i]];
		}

		if (canSatisfy(cards)) {
			int j = handSize() - cardsRequired();
			Card[] card2 = new Card[j];
			if (j > 0) {
				int k = 0;				
				for (int i = 0; i < allCards.length; i++) {
					Card card3 = allCards[i];
					if (Arrays.asList(cards).contains(card3)) {
						continue;
					}
					card2[k] = card3;
					k++;
					if (k == j) {
						break;
					}
				}
			}
			Hand hand_Created = new Hand(cards, card2, this);
			return hand_Created;
		}
		return null;
	}

	public Hand getBestHand(Card[] allCards) {
		if (allCards.length < handSize()) {
			return null;
		}
		ArrayList<int[]> subsets = SubsetFinder.findSubsets(allCards.length, cardsRequired());
		ArrayList<Hand> hands = new ArrayList<>();
		for (int[] sub : subsets) {
			Hand hand_Created = createHand(allCards, sub);
			if (hand_Created != null) {
				hands.add(hand_Created);
			}
		}
		if (hands.size() == 0) {
			return null;
		}
		Collections.sort(hands);
		return hands.get(0);
	}
}
