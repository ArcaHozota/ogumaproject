package jp.co.ogumaproject.ppok.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * 共通マッパー
 *
 * @author ArkamaHozota
 * @since 1.72
 */
public interface CommonMapper<T> {

	/**
	 * キーワードによって情報の数を取得する
	 *
	 * @param keyword 検索キーワード
	 * @return Integer
	 */
	Long countByKeyword(@Param("keyword") String keyword, @Param("delFlg") String delFlg);

	/**
	 * IDによって情報を挿入する
	 *
	 * @param aEntity エンティティ
	 */
	void insertById(T aEntity);

	/**
	 * キーワードによって情報を検索する
	 *
	 * @param keyword  キーワード
	 * @param offset   オフセット
	 * @param pageSize ページサイズ
	 * @return List<T>
	 */
	List<T> paginationByKeyword(@Param("keyword") String keyword, @Param("offset") Integer offset,
			@Param("pageSize") Integer pageSize, @Param("delFlg") String delFlg);

	/**
	 * IDによって論理削除を行う
	 *
	 * @param aEntity エンティティ
	 */
	void removeById(T aEntity);

	/**
	 * 全件検索を行う
	 *
	 * @return List<T>
	 */
	List<T> selectAll(@Param("delFlg") String delFlg);

	/**
	 * IDによって情報を検索する
	 *
	 * @param id ID
	 * @return T
	 */
	T selectById(@Param("id") Long id);

	/**
	 * IDによって情報を検索する
	 *
	 * @param T エンティティ
	 * @return T
	 */
	T selectById(T aEntity);

	/**
	 * IDによって情報を更新する
	 *
	 * @param aEntity エンティティ
	 */
	void updateById(T aEntity);
}
