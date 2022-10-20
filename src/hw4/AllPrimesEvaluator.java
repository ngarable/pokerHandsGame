package hw4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import api.Card;
import api.Hand;
import util.SubsetFinder;

/**
 *@author BlessingNgara
 */
/**
 * Evaluator for a hand in which the rank of each card is a prime number.
 * The number of cards required is equal to the hand size. 
 * 
 * The name of this evaluator is "All Primes".
 */
//Note: You must edit this declaration to extend AbstractEvaluator
//or to extend some other class that extends AbstractEvaluator
public class AllPrimesEvaluator extends AbstractEvaluator
{
	/**
	 * Constructs the evaluator.
	 * @param ranking
	 *   ranking of this hand
	 * @param handSize
	 *   number of cards in a hand
	 */
	public AllPrimesEvaluator(int ranking, int handSize) {
		super("All Primes", ranking, handSize, handSize);
	}

	public boolean canSatisfy(Card[] mainCards) {
		if (mainCards.length < cardsRequired() || mainCards.length > cardsRequired()) {
			return false;
		}
		for (Card card : mainCards) {
			if (!isPrime(card.getRank())) {
				return false;
			}
		}
		return true;
	}

	private boolean isPrime(int num) {
		for (int i = 2; i < num / 2; i++) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}

	public boolean canSubsetSatisfy(Card[] allCards) {
		if (allCards.length < cardsRequired()) {
			return false;
		}
		ArrayList<int[]> subsetArray = SubsetFinder.findSubsets(allCards.length, cardsRequired());
		for (int i = 0; i < subsetArray.size(); i++) {
			if (canSatisfy(new Card[] { allCards[subsetArray.get(i)[0]], allCards[subsetArray.get(i)[1]] })) {
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
		if (canSubsetSatisfy(cards)) {
			int j = handSize() - cardsRequired();
			Card[] cardSubset1 = new Card[j];
			int b = 0;
			if (j > 0) {
				for (int i = 0; i < allCards.length; i++) {
					Card cardSubset2 = allCards[i];
					if (Arrays.asList(cards).contains(cardSubset2)) {
						continue;
					}
					cardSubset1[b] = cardSubset2;
					b++;
					if (b == j) {
						break;
					}
				}
			}
			Hand hand_Created = new Hand(cards, cardSubset1, this);
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