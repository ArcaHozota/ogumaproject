package jp.co.ogumaproject.ppok.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import jp.co.ogumaproject.ppok.entity.EmployeeRole;

/**
 * 社員役割連携マッパー
 *
 * @author ArkamaHozota
 * @since 1.00
 */
@Mapper
public interface EmployeeRoleMapper extends CommonMapper<EmployeeRole> {

	/**
	 * IDによって情報を削除する
	 *
	 * @param id 社員役割連携ID
	 */
	void deleteById(@Param("id") Long id);

	/**
	 * 役割IDによって情報を検索する
	 *
	 * @param roleId 役割ID
	 * @return EmployeeRole
	 */
	List<EmployeeRole> selectByRoleId(@Param("roleId") Long roleId);
}
