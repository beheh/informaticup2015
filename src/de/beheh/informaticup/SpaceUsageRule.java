package de.beheh.informaticup;

/**
 *
 * @author Benedict Etzel <developer@beheh.de>
 */
public abstract class SpaceUsageRule {

	private final float latitude;
	private final float longitude;
	private final Object value;

	public SpaceUsageRule(Object value) {
		this.latitude = 0;
		this.longitude = 0;
		this.value = value;
	}

	public float getLatitude() {
		return latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public Object getValue() {
		return value;
	}

	public boolean getBoolean() {
		Boolean bool = (Boolean) value;
		return bool;
	}

	public int getInteger() {
		Integer integer = (Integer) value;
		return integer;
	}
}
