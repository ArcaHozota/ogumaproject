package jp.co.ogumaproject.ppok.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import jp.co.ogumaproject.ppok.entity.District;

/**
 * 地域マッパー
 *
 * @author ArkamaHozota
 * @since 2.29
 */
@Mapper
public interface DistrictMapper {

	/**
	 * キーワードによって地域情報の数を取得する
	 *
	 * @param keyword 検索キーワード
	 * @return Long
	 */
	Long countByKeyword(@Param("keyword") String keyword);

	/**
	 * キーワードによって地域情報を検索する
	 *
	 * @param keyword  キーワード
	 * @param offset   オフセット
	 * @param pageSize ページサイズ
	 * @return List<Role>
	 */
	List<District> paginationByKeyword(@Param("keyword") String keyword, @Param("offset") Integer offset,
			@Param("pageSize") Integer pageSize);

	/**
	 * 全件検索を行う
	 *
	 * @param delFlg 論理削除フラグ
	 * @return List<District>
	 */
	List<District> selectAll();

	/**
	 * IDによって情報を検索する
	 *
	 * @param district 地域エンティティ
	 * @return District
	 */
	District selectById(District district);

	/**
	 * IDによって情報を検索する
	 *
	 * @param id 地域ID
	 * @return District
	 */
	District selectById(@Param("id") Long id);

	/**
	 * IDによって情報を更新する
	 *
	 * @param district 地域エンティティ
	 */
	void updateById(District district);
}
