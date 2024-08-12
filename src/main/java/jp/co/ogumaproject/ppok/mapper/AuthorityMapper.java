package jp.co.ogumaproject.ppok.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import jp.co.ogumaproject.ppok.entity.Authority;

/**
 * 権限マッパー
 *
 * @author ArkamaHozota
 * @since 1.00
 */
@Mapper
public interface AuthorityMapper extends CommonMapper<Authority> {

	/**
	 * IDリストによって検索を行う
	 *
	 * @param authIds 権限ID集合
	 * @return List<Authority>
	 */
	List<Authority> selectByIds(@Param("ids") List<Long> authIds, @Param("delFlg") String delFlg);
}
