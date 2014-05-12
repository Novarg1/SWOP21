package vehicle.parts;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a this of parts. In this set it is impossible to add two different
 * parts of the same type. This set does not permit the null element.
 */
public class PartsSet extends HashSet<Part> {

	private static final long serialVersionUID = 1L;

	/**
	 * Creates a new, empty partsSet
	 */
	public PartsSet() {
		super();
	}

	/**
	 * Creates a new partsSet containing the elements of the given collection.
	 * If the collection contains multiple parts of the same type, one of these
	 * parts will be randomly selected and the other parts will not be contained
	 * by this set.
	 */
	public PartsSet(Collection<Part> collection) {
		this.addAll(collection);
	}

	/**
	 * Creates a new partsSet containing the elements of the given array. If the
	 * array contains multiple parts of the same type, one of these parts will
	 * be randomly selected and the other parts will not be contained by this
	 * set.
	 */
	public PartsSet(Part[] array) {
		this(Arrays.asList(array));
	}

	/**
	 * @return the part of the given type in this set, or null if no such part
	 *         exists.
	 */
	public Part get(Class<? extends Part> type) {
		for (Part part : this) {
			if (part.getClass().equals(type)) {
				return part;
			}
		}
		return null;
	}

	/**
	 * @return A set containing all the types of which this set contains a part.
	 */
	public Set<Class<? extends Part>> getTypes() {
		Set<Class<? extends Part>> result = new HashSet<>();
		for (Part part : this) {
			result.add(part.getClass());
		}
		return result;
	}

	/**
	 * Adds the given part to this set. If this set already contains an element
	 * of the same type, or if the given part equals null, the call leaves the
	 * set unchanged and returns false.
	 */
	@Override
	public boolean add(Part part) {
		if (part == null || contains(part.getClass())) {
			return false;
		}
		super.add(part);
		return true;
	}

	/**
	 * Removes the part of the given type from this set, if one is present.
	 * 
	 * @return true if this set contained a part of the given type.
	 */
	public boolean remove(Class<? extends Part> type) {
		return this.remove(get(type));
	}

	/**
	 * @return true if this set contains a part of the given type.
	 */
	public boolean contains(Class<? extends Part> type) {
		return get(type) != null;
	}

	/**
	 * @return Another partsSet containing the same parts.
	 */
	@Override
	public PartsSet clone() {
		return new PartsSet(this);
	}

	/**
	 * @return A string showing all parts this set contains.
	 */
	@Override
	public String toString() {
		String result = "";
		for (Part part : this) {
			result += part.getClass().getName() + ": " + part + "\n";
		}
		return result;
	}
}
