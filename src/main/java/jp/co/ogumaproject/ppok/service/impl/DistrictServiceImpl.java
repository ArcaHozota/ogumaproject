package jp.co.ogumaproject.ppok.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.ogumaproject.ppok.commons.OgumaProjectConstants;
import jp.co.ogumaproject.ppok.dto.CityDto;
import jp.co.ogumaproject.ppok.dto.DistrictDto;
import jp.co.ogumaproject.ppok.entity.Chiho;
import jp.co.ogumaproject.ppok.entity.City;
import jp.co.ogumaproject.ppok.entity.District;
import jp.co.ogumaproject.ppok.mapper.ChihoMapper;
import jp.co.ogumaproject.ppok.mapper.CityMapper;
import jp.co.ogumaproject.ppok.mapper.DistrictMapper;
import jp.co.ogumaproject.ppok.service.IDistrictService;
import jp.co.ogumaproject.ppok.utils.OgumaProjectUtils;
import jp.co.ogumaproject.ppok.utils.Pagination;
import jp.co.ogumaproject.ppok.utils.ResultDto;
import jp.co.ogumaproject.ppok.utils.SecondBeanUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import oracle.jdbc.driver.OracleSQLException;

/**
 * 地域サービス実装クラス
 *
 * @author ArkamaHozota
 * @since 2.29
 */
@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Transactional(rollbackFor = OracleSQLException.class)
public class DistrictServiceImpl implements IDistrictService {

	/**
	 * ページサイズ
	 */
	private static final Integer PAGE_SIZE = OgumaProjectConstants.DEFAULT_PAGE_SIZE;

	/**
	 * 都市マッパー
	 */
	private final CityMapper cityMapper;

	/**
	 * 都市マッパー
	 */
	private final ChihoMapper chihoMapper;

	/**
	 * 地域マッパー
	 */
	private final DistrictMapper districtMapper;

	@Override
	public List<Chiho> getChihos(final String chihoName) {
		final List<Chiho> chihos = new ArrayList<>();
		final List<Chiho> list = this.chihoMapper.selectAll();
		chihos.add(list.stream().filter(a -> OgumaProjectUtils.isEqual(a.getName(), chihoName)).findFirst().get());
		chihos.addAll(list);
		return chihos.stream().distinct().toList();
	}

	@Override
	public List<DistrictDto> getDistrictsByCityId(final String cityId) {
		final List<District> districts = this.districtMapper.selectAll();
		if (!OgumaProjectUtils.isDigital(cityId)) {
			return districts.parallelStream().map(item -> new DistrictDto(item.getId(), item.getName(), null, null,
					null, item.getChiho().getName(), null, null)).toList();
		}
		final List<District> aDistricts = new ArrayList<>();
		final City city = this.cityMapper.selectById(Long.parseLong(cityId));
		aDistricts.add(districts.parallelStream()
				.filter(a -> OgumaProjectUtils.isEqual(a.getId(), city.getDistrictId())).findFirst().get());
		aDistricts.addAll(districts);
		return aDistricts.parallelStream().distinct().map(item -> new DistrictDto(item.getId(), item.getName(), null,
				null, null, item.getChiho().getName(), null, null)).toList();
	}

	@Override
	public Pagination<DistrictDto> getDistrictsByKeyword(final Integer pageNum, final String keyword) {
		final Integer offset = (pageNum - 1) * PAGE_SIZE;
		final String searchStr = OgumaProjectUtils.getDetailKeyword(keyword);
		final Long records = this.districtMapper.countByKeyword(searchStr);
		final List<DistrictDto> districtDtos = this.districtMapper.paginationByKeyword(searchStr, offset, PAGE_SIZE)
				.stream().map(item -> {
					final City shuto = item.getCities().parallelStream()
							.filter(a -> OgumaProjectUtils.isEqual(a.getId(), item.getShutoId())).findFirst().get();
					final Long population = item.getCities().parallelStream().map(City::getPopulation)
							.reduce((a, v) -> (a + v)).get();
					return new DistrictDto(item.getId(), item.getName(), item.getShutoId(), shuto.getName(),
							item.getChihoId(), item.getChiho().getName(), population, item.getDistrictFlag());
				}).toList();
		return Pagination.of(districtDtos, records, pageNum, PAGE_SIZE);
	}

	@Override
	public List<CityDto> getShutos(final DistrictDto districtDto) {
		final List<CityDto> cityDtos = new ArrayList<>();
		final List<City> cities = this.districtMapper.selectById(districtDto.id()).getCities();
		cityDtos.add(
				cities.parallelStream().filter(a -> OgumaProjectUtils.isEqual(a.getName(), districtDto.shutoName()))
						.map(item -> new CityDto(item.getId(), item.getName(), null, null, null, null, null))
						.findFirst().get());
		cityDtos.addAll(cities.stream().sorted(Comparator.comparingLong(City::getId))
				.map(item -> new CityDto(item.getId(), item.getName(), null, null, null, null, null)).toList());
		return cityDtos.parallelStream().distinct().toList();
	}

	@Override
	public ResultDto<String> update(final DistrictDto districtDto) {
		final District originalEntity = new District();
		final District district = this.districtMapper.selectById(districtDto.id());
		SecondBeanUtils.copyNullableProperties(district, originalEntity);
		SecondBeanUtils.copyNullableProperties(districtDto, district);
		if (OgumaProjectUtils.isEqual(originalEntity, district)) {
			return ResultDto.failed(OgumaProjectConstants.MESSAGE_STRING_NOCHANGE);
		}
		this.districtMapper.updateById(district);
		return ResultDto.successWithoutData();
	}
}
