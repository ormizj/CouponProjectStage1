package scrap.enumToString;

public enum ToStringTest {

	HELLO, BYE, HI;

	@Override
	public String toString() {
		switch (this) {
		case HELLO:
			return "hello1";
		case BYE:
			return "bye2";
		case HI:
			return "hi3";
		default:
			throw new IllegalArgumentException();
		}
	}

}
