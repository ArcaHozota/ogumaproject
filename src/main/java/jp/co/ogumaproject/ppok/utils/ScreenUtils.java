package jp.co.ogumaproject.ppok.utils;

import java.util.LinkedHashMap;
import java.util.Map;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * ラジオボタン（性別）
 *
 * @param flg 「すべて」有無のフラグ
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ScreenUtils {

	/**
	 * 初期化
	 *
	 * @param flg
	 * @return Map<String, String>
	 */
	public static Map<String, String> initSexyRadios(final boolean flg) {
		final Map<String, String> radio = new LinkedHashMap<>();
		radio.put("0", "男性");
		radio.put("1", "女性");
		if (flg) {
			radio.put("2", "すべて");
		}
		return radio;
	}
}
