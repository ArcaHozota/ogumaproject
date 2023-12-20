package jp.co.ogumaproject.ppok.exception;

/**
 * システムエラー
 *
 * @author Administrator
 */
public class SystemException extends RuntimeException {

	/**
	 * シリアルナンバー
	 */
	private static final long serialVersionUID = -6288407935976604050L;

	public SystemException(final String message) {
		super(message);
	}

}
