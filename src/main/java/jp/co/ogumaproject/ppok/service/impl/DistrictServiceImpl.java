package jp.co.ogumaproject.ppok.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.ogumaproject.ppok.commons.OgumaProjectConstants;
import jp.co.ogumaproject.ppok.dto.DistrictDto;
import jp.co.ogumaproject.ppok.entity.District;
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
	 * 都市マッパー
	 */
	private final CityMapper cityMapper;

	/**
	 * 地域マッパー
	 */
	private final DistrictMapper districtMapper;

	@Override
	public List<DistrictDto> getDistrictsByCityId(final String cityId) {
		final List<DistrictDto> list = new ArrayList<>();
		final List<DistrictDto> districtDtos = this.districtMapper.selectAll().stream().map(item -> {
			final DistrictDto districtDto = new DistrictDto();
			SecondBeanUtils.copyNullableProperties(item, districtDto);
			return districtDto;
		}).collect(Collectors.toList());
		if (!OgumaProjectUtils.isDigital(cityId)) {
			final DistrictDto districtDto = new DistrictDto();
			districtDto.setId(0L);
			districtDto.setName(OgumaProjectConstants.DEFAULT_ROLE_NAME);
			list.add(districtDto);
			list.addAll(districtDtos);
			return list;
		}
		final Long districtId = this.cityMapper.selectById(Long.parseLong(cityId)).getDistrictId();
		final List<DistrictDto> collect = districtDtos.stream().filter(a -> Objects.equals(districtId, a.getId()))
				.collect(Collectors.toList());
		list.addAll(collect);
		list.addAll(districtDtos);
		return list.stream().distinct().collect(Collectors.toList());
	}

	@Override
	public Pagination<DistrictDto> getDistrictsByKeyword(final Integer pageNum, final String keyword) {
		final Integer pageSize = OgumaProjectConstants.DEFAULT_PAGE_SIZE;
		final Integer offset = (pageNum - 1) * pageSize;
		final String searchStr = OgumaProjectUtils.getDetailKeyword(keyword);
		final Long records = this.districtMapper.countByKeyword(searchStr);
		final List<DistrictDto> pages = this.districtMapper.paginationByKeyword(searchStr, offset, pageSize);
		return Pagination.of(pages, records, pageNum, pageSize);
	}

	@Override
	public ResultDto<String> update(final DistrictDto districtDto) {
		final District entity = new District();
		entity.setId(districtDto.getId());
		final District district = this.districtMapper.selectById(entity);
		SecondBeanUtils.copyNullableProperties(district, entity);
		SecondBeanUtils.copyNullableProperties(districtDto, district);
		if (district.equals(entity)) {
			return ResultDto.failed(OgumaProjectConstants.MESSAGE_STRING_NOCHANGE);
		}
		this.districtMapper.updateById(district);
		return ResultDto.successWithoutData();
	}
}
