package jp.co.ogumaproject.ppok.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.ogumaproject.ppok.commons.OgumaProjectConstants;
import jp.co.ogumaproject.ppok.dto.CityDto;
import jp.co.ogumaproject.ppok.entity.City;
import jp.co.ogumaproject.ppok.entity.District;
import jp.co.ogumaproject.ppok.mapper.CityMapper;
import jp.co.ogumaproject.ppok.mapper.DistrictMapper;
import jp.co.ogumaproject.ppok.service.ICityService;
import jp.co.ogumaproject.ppok.utils.OgumaProjectUtils;
import jp.co.ogumaproject.ppok.utils.Pagination;
import jp.co.ogumaproject.ppok.utils.ResultDto;
import jp.co.ogumaproject.ppok.utils.SecondBeanUtils;
import jp.co.ogumaproject.ppok.utils.SnowflakeUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import oracle.jdbc.driver.OracleSQLException;

/**
 * 都市サービス実装クラス
 *
 * @author ArkamaHozota
 * @since 2.33
 */
@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Transactional(rollbackFor = OracleSQLException.class)
public class CityServiceImpl implements ICityService {

	private static final Integer PAGE_SIZE = OgumaProjectConstants.DEFAULT_PAGE_SIZE;

	/**
	 * 都市マッパー
	 */
	private final CityMapper cityMapper;

	/**
	 * 地域マッパー
	 */
	private final DistrictMapper districtMapper;

	@Override
	public ResultDto<String> checkDuplicated(final String name, final Long districtId) {
		final City city = new City();
		city.setName(name);
		city.setDistrictId(districtId);
		return this.cityMapper.checkDuplicated(city) > 0
				? ResultDto.failed(OgumaProjectConstants.MESSAGE_CITY_NAME_DUPLICATED)
				: ResultDto.successWithoutData();
	}

	@Override
	public Pagination<CityDto> getCitiesByKeyword(final Integer pageNum, final String keyword) {
		final Integer offset = (pageNum - 1) * PAGE_SIZE;
		final String searchStr = OgumaProjectUtils.getDetailKeyword(keyword);
		final Long records = this.cityMapper.countByKeyword(searchStr);
		final List<CityDto> pages = this.cityMapper.paginationByKeyword(searchStr, offset, PAGE_SIZE).stream()
				.map(item -> new CityDto(item.getId(), item.getName(), item.getDistrictId(), item.getPronunciation(),
						item.getDistrictName(), item.getPopulation(), item.getCityFlag()))
				.toList();
		return Pagination.of(pages, records, pageNum, PAGE_SIZE);
	}

	@Override
	public ResultDto<String> remove(final Long id) {
		final City city = this.cityMapper.selectById(id);
		final District district = this.districtMapper.selectById(city.getDistrictId());
		if (Objects.equals(id, district.getShutoId())) {
			return ResultDto.failed(OgumaProjectConstants.MESSAGE_STRING_FORBIDDEN3);
		}
		city.setDelFlg(OgumaProjectConstants.LOGIC_DELETE_FLG);
		this.cityMapper.removeById(city);
		return ResultDto.successWithoutData();
	}

	@Override
	public void save(final CityDto cityDto) {
		final City city = new City();
		SecondBeanUtils.copyNullableProperties(cityDto, city);
		city.setId(SnowflakeUtils.snowflakeId());
		city.setDelFlg(OgumaProjectConstants.LOGIC_DELETE_INITIAL);
		this.cityMapper.insertById(city);
	}

	@Override
	public ResultDto<String> update(final CityDto cityDto) {
		final City originalEntity = new City();
		final City city = this.cityMapper.selectById(cityDto.id());
		SecondBeanUtils.copyNullableProperties(city, originalEntity);
		SecondBeanUtils.copyNullableProperties(cityDto, city);
		if (OgumaProjectUtils.isEqual(originalEntity, city)) {
			return ResultDto.failed(OgumaProjectConstants.MESSAGE_STRING_NOCHANGE);
		}
		this.cityMapper.updateById(city);
		return ResultDto.successWithoutData();
	}
}
