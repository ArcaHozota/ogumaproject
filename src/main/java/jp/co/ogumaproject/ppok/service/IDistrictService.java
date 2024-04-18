package jp.co.ogumaproject.ppok.service;

import java.util.List;

import jp.co.ogumaproject.ppok.dto.DistrictDto;
import jp.co.ogumaproject.ppok.utils.Pagination;
import jp.co.ogumaproject.ppok.utils.ResultDto;

/**
 * 地域サービスインターフェス
 *
 * @author ArkamaHozota
 * @since 2.26
 */
public interface IDistrictService {

	/**
	 * IDによって地域情報を取得する
	 *
	 * @param cityId 都市ID
	 * @return List<District>
	 */
	List<DistrictDto> getDistrictsByCityId(String cityId);

	/**
	 * キーワードによって地域情報を取得する
	 *
	 * @param pageNum ページ数
	 * @param keyword キーワード
	 * @return Pagination<Role>
	 */
	Pagination<DistrictDto> getDistrictsByKeyword(Integer pageNum, String keyword);

	/**
	 * 地域情報更新
	 *
	 * @param districtDto 地域情報転送クラス
	 */
	ResultDto<String> update(DistrictDto districtDto);
}
