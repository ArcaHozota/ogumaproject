package jp.co.ogumaproject.ppok.exception;

/**
 * 業務ロジック例外
 *
 * @author Administrator
 */
public class BusinessException extends RuntimeException {

	/**
	 * シリアルナンバー
	 */
	private static final long serialVersionUID = 5232345305091286375L;

	public BusinessException(final Exception e) {
		super();
		this.errDetail = e;
	}

	public BusinessException(final String message, final Throwable cause, final Exception e) {
		super(message, cause);
		this.errDetail = e;
	}

	public BusinessException(final Throwable cause, final Exception e) {
		super(cause);
		this.errDetail = e;
	}

	public BusinessException(final String message, final Exception e) {
		super(message);
		this.errDetail = e;
	}

	// エラー詳細情報
	private final Exception errDetail;

	public Exception getErrDetail() {
		return this.errDetail;
	}
}
