package car;

public class PaintJob extends CustomsSpec {

	private static final String name = "Spray painting job";
	
	public PaintJob() {
		super(name);
	}

	@Override
	protected CarPart[] getAllSupportedParts() {
		return Color.values();
	}
}
