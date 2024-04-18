package jp.co.ogumaproject.ppok.service;

import jp.co.ogumaproject.ppok.dto.CityDto;
import jp.co.ogumaproject.ppok.utils.Pagination;
import jp.co.ogumaproject.ppok.utils.ResultDto;

/**
 * 都市サービスインターフェス
 *
 * @author ArkamaHozota
 * @since 2.33
 */
public interface ICityService {

	/**
	 * 都市名称が重複するかどうかをチェックする
	 *
	 * @param name       都市名称
	 * @param districtId 地域ID
	 * @return true:重複する; false: 重複しない;
	 */
	ResultDto<String> checkDuplicated(String name, Long districtId);

	/**
	 * キーワードによって都市情報を取得する
	 *
	 * @param pageNum ページ数
	 * @param keyword キーワード
	 * @return Pagination<Role>
	 */
	Pagination<CityDto> getCitiesByKeyword(Integer pageNum, String keyword);

	/**
	 * IDによって情報を削除する
	 *
	 * @param id 都市ID
	 * @return ResultDto<String>
	 */
	ResultDto<String> remove(Long id);

	/**
	 * 都市情報追加
	 *
	 * @param cityDto 都市情報転送クラス
	 */
	void save(CityDto cityDto);

	/**
	 * 都市情報更新
	 *
	 * @param cityDto 都市情報転送クラス
	 */
	ResultDto<String> update(CityDto cityDto);
}
