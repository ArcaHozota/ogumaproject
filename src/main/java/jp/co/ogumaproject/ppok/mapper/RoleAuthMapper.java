package jp.co.ogumaproject.ppok.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import jp.co.ogumaproject.ppok.entity.RoleAuth;

/**
 * 役割権限連携マッパー
 *
 * @author ArkamaHozota
 * @since 6.73
 */
@Mapper
public interface RoleAuthMapper extends CommonMapper<RoleAuth> {

	/**
	 * 役割IDによってバッチ削除を行う
	 *
	 * @param roleId 役割ID
	 */
	void batchDeleteByRoleId(@Param("roleId") Long roleId);

	/**
	 * IDによってバッチ挿入を行う
	 *
	 * @param roleAuths 役割権限連携エンティティList
	 */
	void batchInsertByIds(@Param("roleAuths") List<RoleAuth> roleAuths);

	/**
	 * 役割IDによって検索する
	 *
	 * @param roleId
	 * @return List<RoleAuth>
	 */
	List<RoleAuth> selectByRoleId(Long roleId);
}
