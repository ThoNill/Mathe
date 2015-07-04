package mathe.grothendieck;

import mathe.strukturen.Menge;

public interface  GrothendieckPaarMenge<R, GP extends GrothendieckPaar<R>> extends Menge<GP> {

	public GP neuesPaar(R li, R re);


}
