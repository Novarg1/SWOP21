package car.parts;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Represents a set of carParts. In this set it is impossible to add two
 * different parts of the same type. This class does not permit the null
 * element.
 */
public class CarpartsSet implements Iterable<Carpart> {

	private Set<Carpart> set;

	/**
	 * Creates a new carpartsSet.
	 */
	public CarpartsSet() {
		set = new HashSet<>();
	}

	/**
	 * Creates a new carpartsSet containing the same carparts as the given set.
	 * 
	 * @param other
	 */
	public CarpartsSet(CarpartsSet other) {
		set = new HashSet<>(other.set);
	}

	/**
	 * Creates a new carpartsSet containing the elements of the given
	 * collection. If the collection contains multiple parts of the same type,
	 * one of these parts will be randomly selected and the other parts will not
	 * be contained by this set.
	 */
	public CarpartsSet(Collection<Carpart> collection) {
		this();
		for (Carpart part : collection) {
			add(part);
		}
		set.remove(null);
	}

	/**
	 * Creates a new carpartsSet containing the elements of the given array. If
	 * the array contains multiple parts of the same type, one of these parts
	 * will be randomly selected and the other parts will not be contained by
	 * this set.
	 */
	public CarpartsSet(Carpart[] array) {
		this(Arrays.asList(array));
	}

	/**
	 * @return the carpart of the given type in this set, or null if no such
	 *         carpart exists.
	 */
	public Carpart get(Class<? extends Carpart> type) {
		for (Carpart part : set) {
			if (part.getClass().equals(type)) {
				return part;
			}
		}
		return null;
	}

	/**
	 * Adds the given part to the set and removes possible previous part of the
	 * same type. If the given part == null, this method does nothing.
	 */
	public void add(Carpart part) {
		if (part != null) {
			set.remove(get(part.getClass()));
			set.add(part);
		}
	}

	/**
	 * Removes the part of the given type from this set, if one is present.
	 * 
	 * @return true if this set contained a part of the given type.
	 */
	public boolean remove(Class<? extends Carpart> type) {
		return set.remove(get(type));
	}

	/**
	 * Removes the given part from this set if it is present.
	 * 
	 * @return true if this set contained the given carpart.
	 */
	public boolean remove(Carpart part) {
		return set.remove(part);
	}

	/**
	 * @return true if this set contains a part of the given type.
	 */
	public boolean containsType(Class<? extends Carpart> type) {
		return get(type) != null;
	}

	/**
	 * @return true if this set contains the given part.
	 */
	public boolean contains(Carpart part) {
		return set.contains(part);
	}

	/**
	 * @return true if this set is empty.
	 */
	public boolean isEmpty() {
		return set.isEmpty();
	}

	/**
	 * @return the amount of parts this set contains.
	 */
	public int size() {
		return set.size();
	}

	/**
	 * @return true if this set contains the same carParts as the given set.
	 */
	public boolean equals(CarpartsSet other) {
		return this.set.equals(other.set);
	}

	/**
	 * @return Another carpartsSet containing the same carparts.
	 */
	@Override
	public CarpartsSet clone() {
		return new CarpartsSet(this);
	}

	/**
	 * @return A string showing all carparts this set contains.
	 */
	@Override
	public String toString() {
		String result = "";
		for (Carpart part : set) {
			result += part.getClass().getName() + ": " + part + "\n";
		}
		return result;
	}

	/**
	 * @return an iterator over the carparts this set contains.
	 */
	@Override
	public Iterator<Carpart> iterator() {
		return set.iterator();
	}
}
