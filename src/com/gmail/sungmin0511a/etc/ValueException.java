package com.gmail.sungmin0511a.etc;

public class ValueException extends Exception {
	public static enum ExceptionKey {
		Between, number, over, under;
	}

	private static final long serialVersionUID = 2173452193493631005L;

	ExceptionKey e;
	int[] factor;

	public ValueException(ExceptionKey e, int... factor) throws ValueException {
		super();
		this.e = e;
		switch (e) {
		case number:
		case under:
		case over:
			if (factor.length != 1)
				throw new ValueException(ExceptionKey.number, 1);
			break;
		case Between:
			if (factor.length != 2)
				throw new ValueException(ExceptionKey.number, 2);
			break;
		default:
			break;
		}
		this.factor = factor;
	}

	@Override
	public String getMessage() {
		switch (e) {
		case Between:
			return "인자의 값이 " + factor[0] + "~" + factor[1] + "사이여야 합니다.";
		case number:
			return "인자의 갯수가 " + factor[0] + "개이여야 합니다.";
		case under:
			return "인자의 값이 " + factor[0] + "이하여야 합니다.";
		case over:
			return "인자의 값이 " + factor[0] + "이상이여야 합니다.";
		default:
			return "값 입력이 잘못되었습니다";
		}
	}
}
