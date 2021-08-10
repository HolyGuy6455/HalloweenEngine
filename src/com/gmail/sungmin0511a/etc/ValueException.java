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
			return "������ ���� " + factor[0] + "~" + factor[1] + "���̿��� �մϴ�.";
		case number:
			return "������ ������ " + factor[0] + "���̿��� �մϴ�.";
		case under:
			return "������ ���� " + factor[0] + "���Ͽ��� �մϴ�.";
		case over:
			return "������ ���� " + factor[0] + "�̻��̿��� �մϴ�.";
		default:
			return "�� �Է��� �߸��Ǿ����ϴ�";
		}
	}
}
