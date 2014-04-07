package edu.wpi.cs.wpisuitetcw.modules.planningpoker.models;

import java.util.ArrayList;

import com.google.gson.Gson;

import edu.wpi.cs.wpisuitetng.modules.AbstractModel;

/**
 * This class represents the deck for voting
 * 
 * @author Manny DeMaio, Louie MIstretta
 * 
 */

public class PlanningPokerDeck extends AbstractModel {
	private ArrayList<int[]> deck;
	public String deckName;
	private int id;

	
	
	/**
	 * This constructor creates the default deck with the fibonacci values
	 */
	public PlanningPokerDeck() {
		int[] defaultDeck = {0,1,1,2,3,5,8,13};
		this.deckName = "Default Deck";
		deck = new ArrayList<int[]>();
		this.deck.add(defaultDeck);
	}
	
	/**
	 * This constructor creates the deck from the imported arrayList of values
	 * @param deck_in
	 * 			the inputed deck
	 */
	public PlanningPokerDeck(String name_in, ArrayList<int[]> deck_in){
		this.deckName = name_in;
		deck.addAll(deck_in);
	}

	
	
	
	@Override
	public void save() {
		

	}

	@Override
	public void delete() {
		

	}

	@Override
	public String toJSON() {
		return new Gson().toJson(this, PlanningPokerDeck.class);
	}

	
	/**
	 * Returns an instance of PlanningPokerRequirement constructed using the
	 * given Requirement encoded as a JSON string.
	 * 
	 * @param json
	 *            JSON-encoded Requirement to deserialize
	 * 
	 * @return the Requirement contained in the given JSON
	 */
	public static PlanningPokerDeck fromJson(String json) {
		final Gson parser = new Gson();
		return parser.fromJson(json, PlanningPokerDeck.class);
	}
	
	@Override
	public Boolean identify(Object o) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
