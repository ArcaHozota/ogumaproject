package jp.co.ogumaproject.ppok.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jp.co.ogumaproject.ppok.commons.OgumaProjectURLConstants;
import jp.co.ogumaproject.ppok.dto.DistrictDto;
import jp.co.ogumaproject.ppok.service.IDistrictService;
import jp.co.ogumaproject.ppok.utils.OgumaProjectUtils;
import jp.co.ogumaproject.ppok.utils.Pagination;
import jp.co.ogumaproject.ppok.utils.ResultDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

/**
 * 地域コントローラ
 *
 * @author ArkamaHozota
 * @since 1.00beta
 */
@RestController
@RequestMapping(OgumaProjectURLConstants.URL_DISTRICT_COMMON)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class DistrictController {

	/**
	 * 地域サービスインターフェス
	 */
	private final IDistrictService iDistrictService;

	/**
	 * 編集権限チェック
	 *
	 * @return ResultDto<String>
	 */
	@GetMapping("/checkEdition")
	public ResultDto<String> checkEdition() {
		return ResultDto.successWithoutData();
	}

	/**
	 * キーワードによってページング検索
	 *
	 * @param pageNum ページ数
	 * @param keyword キーワード
	 * @return ResultDto<Pagination<DistrictDto>>
	 */
	@GetMapping(OgumaProjectURLConstants.URL_PAGINATION)
	public ResultDto<Pagination<DistrictDto>> pagination(
			@RequestParam(name = "pageNum", defaultValue = "1") final Integer pageNum,
			@RequestParam(name = "keyword", defaultValue = OgumaProjectUtils.EMPTY_STRING) final String keyword) {
		final Pagination<DistrictDto> districts = this.iDistrictService.getDistrictsByKeyword(pageNum, keyword);
		return ResultDto.successWithData(districts);
	}

	/**
	 * 情報更新
	 *
	 * @param districtDto 地域情報転送クラス
	 * @return ResultDto<String>
	 */
	@PutMapping(OgumaProjectURLConstants.URL_INFO_UPDATE)
	public ResultDto<String> updateInfo(@RequestBody final DistrictDto districtDto) {
		return this.iDistrictService.update(districtDto);
	}
}
