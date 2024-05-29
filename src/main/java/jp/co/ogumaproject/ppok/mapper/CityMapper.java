package jp.co.ogumaproject.ppok.mapper;

import org.apache.ibatis.annotations.Mapper;

import jp.co.ogumaproject.ppok.entity.City;

/**
 * 都市マッパー
 *
 * @author ArkamaHozota
 * @since 2.40
 */
@Mapper
public interface CityMapper extends CommonMapper<City> {

	/**
	 * 都市名称を重複するかどうかを確認する
	 *
	 * @param city 都市エンティティ
	 * @return Integer
	 */
	Integer checkDuplicated(City city);
}
