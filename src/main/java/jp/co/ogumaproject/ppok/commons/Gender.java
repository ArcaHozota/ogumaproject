package jp.co.ogumaproject.ppok.commons;

import lombok.Getter;

@Getter
public class Gender {

	public static Gender of(final String id, final String value, final String label) {
		return new Gender(id, value, label);
	}

	private final String id;

	private final String value;

	private final String label;

	private Gender(final String id, final String value, final String label) {
		this.id = id;
		this.value = value;
		this.label = label;
	}
}