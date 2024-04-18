package jp.co.ogumaproject.ppok.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jp.co.ogumaproject.ppok.dto.CityDto;
import jp.co.ogumaproject.ppok.dto.DistrictDto;
import jp.co.ogumaproject.ppok.service.ICityService;
import jp.co.ogumaproject.ppok.service.IDistrictService;
import jp.co.ogumaproject.ppok.utils.OgumaProjectUtils;
import jp.co.ogumaproject.ppok.utils.Pagination;
import jp.co.ogumaproject.ppok.utils.ResultDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

/**
 * 都市コントローラ
 *
 * @author ArkamaHozota
 * @since 1.00beta
 */
@RestController
@RequestMapping("/pgcrowd/city")
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class CityCountroller {

	/**
	 * 都市サービスインターフェス
	 */
	private final ICityService iCityService;

	/**
	 * 地域サービスインターフェス
	 */
	private final IDistrictService iDistrictService;

	/**
	 * 都市名称重複チェック
	 *
	 * @param cityDto 都市情報転送クラス
	 * @return ResultDto<String>
	 */
	@GetMapping("/check")
	public ResultDto<String> checkDuplicated(
			@RequestParam(value = "name", defaultValue = OgumaProjectUtils.EMPTY_STRING) final String name,
			@RequestParam("districtId") final Long districtId) {
		return this.iCityService.checkDuplicated(name, districtId);
	}

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
	 * 都市情報削除
	 *
	 * @param cityId 都市ID
	 * @return ResultDto<String>
	 */
	@DeleteMapping("/delete/{cityId}")
	public ResultDto<String> deleteInfo(@PathVariable("cityId") final Long cityId) {
		return this.iCityService.remove(cityId);
	}

	/**
	 * 地域情報初期表示
	 *
	 * @param cityId 都市ID
	 * @return ResultDto<String>
	 */
	@GetMapping("/districtlist")
	public ResultDto<List<DistrictDto>> getDistrictList(@RequestParam(value = "cityId") final String cityId) {
		final List<DistrictDto> districtDtos = this.iDistrictService.getDistrictsByCityId(cityId);
		return ResultDto.successWithData(districtDtos);
	}

	/**
	 * キーワードによってページング検索
	 *
	 * @param pageNum ページ数
	 * @param keyword キーワード
	 * @return ResultDto<Pagination<CityDto>>
	 */
	@GetMapping("/pagination")
	public ResultDto<Pagination<CityDto>> pagination(
			@RequestParam(name = "pageNum", defaultValue = "1") final Integer pageNum,
			@RequestParam(name = "keyword", defaultValue = OgumaProjectUtils.EMPTY_STRING) final String keyword) {
		final Pagination<CityDto> cities = this.iCityService.getCitiesByKeyword(pageNum, keyword);
		return ResultDto.successWithData(cities);
	}

	/**
	 * 情報追加
	 *
	 * @param cityDto 都市情報DTO
	 * @return ResultDto<String>
	 */
	@PostMapping("/infosave")
	public ResultDto<String> saveInfo(@RequestBody final CityDto cityDto) {
		this.iCityService.save(cityDto);
		return ResultDto.successWithoutData();
	}

	/**
	 * 情報更新
	 *
	 * @param cityDto 都市情報DTO
	 * @return ResultDto<String>
	 */
	@PutMapping("/infoupd")
	public ResultDto<String> updateInfo(@RequestBody final CityDto cityDto) {
		return this.iCityService.update(cityDto);
	}
}
