package jp.co.ogumaproject.ppok.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import jp.co.ogumaproject.ppok.entity.Role;

/**
 * 役割マッパー
 *
 * @author ArkamaHozota
 * @since 1.00
 */
@Mapper
public interface RoleMapper extends CommonMapper<Role> {

	/**
	 * 役割名称を重複するかどうかを確認する
	 *
	 * @param name 役割名称
	 * @return Integer
	 */
	Integer checkDuplicated(@Param("name") String name);

	/**
	 * IDによって情報を検索する(権限情報含め)
	 *
	 * @param id 役割ID
	 * @return Role
	 */
	Role selectByIdWithAuth(@Param("id") Long id);
}
