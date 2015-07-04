package mathe.strukturen;

public interface Kategorie<OBJ, MOR> {
	Menge<OBJ> objecte();

	Menge<MOR> morphismen(OBJ from, OBJ to);

}
