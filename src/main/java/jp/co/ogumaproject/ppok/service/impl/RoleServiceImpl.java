package jp.co.ogumaproject.ppok.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.ogumaproject.ppok.commons.OgumaProjectConstants;
import jp.co.ogumaproject.ppok.dto.AuthorityDto;
import jp.co.ogumaproject.ppok.dto.RoleDto;
import jp.co.ogumaproject.ppok.entity.EmployeeRole;
import jp.co.ogumaproject.ppok.entity.Role;
import jp.co.ogumaproject.ppok.entity.RoleAuth;
import jp.co.ogumaproject.ppok.mapper.AuthorityMapper;
import jp.co.ogumaproject.ppok.mapper.EmployeeRoleMapper;
import jp.co.ogumaproject.ppok.mapper.RoleAuthMapper;
import jp.co.ogumaproject.ppok.mapper.RoleMapper;
import jp.co.ogumaproject.ppok.service.IRoleService;
import jp.co.ogumaproject.ppok.utils.OgumaProjectUtils;
import jp.co.ogumaproject.ppok.utils.Pagination;
import jp.co.ogumaproject.ppok.utils.ResultDto;
import jp.co.ogumaproject.ppok.utils.SecondBeanUtils;
import jp.co.ogumaproject.ppok.utils.SnowflakeUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import oracle.jdbc.driver.OracleSQLException;

/**
 * 役割サービス実装クラス
 *
 * @author ArkamaHozota
 * @since 1.00
 */
@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Transactional(rollbackFor = OracleSQLException.class)
public class RoleServiceImpl implements IRoleService {

	/**
	 * ページサイズ
	 */
	private static final Integer PAGE_SIZE = OgumaProjectConstants.DEFAULT_PAGE_SIZE;

	/**
	 * 役割マッパー
	 */
	private final RoleMapper roleMapper;

	/**
	 * 役割権限連携マッパー
	 */
	private final RoleAuthMapper roleAuthMapper;

	/**
	 * 権限マッパー
	 */
	private final AuthorityMapper authorityMapper;

	/**
	 * 社員役割連携マッパー
	 */
	private final EmployeeRoleMapper employeeRoleMapper;

	@Override
	public ResultDto<String> checkDuplicated(final String name) {
		return this.roleMapper.checkDuplicated(name) > 0
				? ResultDto.failed(OgumaProjectConstants.MESSAGE_ROLE_NAME_DUPLICATED)
				: ResultDto.successWithoutData();
	}

	@Override
	public ResultDto<String> doAssignment(final Map<String, List<Long>> paramMap) {
		final Long roleId = paramMap.get("roleIds").get(0);
		final Long[] authIdArray = { 1L, 5L, 9L, 12L };
		final List<Long> authIds = paramMap.get("authIds").stream().filter(a -> !Arrays.asList(authIdArray).contains(a))
				.toList();
		final List<Long> list = this.roleAuthMapper.selectByRoleId(roleId).stream().map(RoleAuth::getAuthId).toList();
		if (OgumaProjectUtils.isEqual(list, authIds)) {
			return ResultDto.failed(OgumaProjectConstants.MESSAGE_STRING_NOCHANGE);
		}
		this.roleAuthMapper.batchDeleteByRoleId(roleId);
		final List<RoleAuth> roleAuths = authIds.stream().map(item -> {
			final RoleAuth roleAuth = new RoleAuth();
			roleAuth.setRoleId(roleId);
			roleAuth.setAuthId(item);
			return roleAuth;
		}).toList();
		this.roleAuthMapper.batchInsertByIds(roleAuths);
		return ResultDto.successWithoutData();
	}

	@Override
	public List<Long> getAuthIdsById(final Long id) {
		final Role aRoleEntity = new Role();
		aRoleEntity.setDelFlg(OgumaProjectConstants.LOGIC_DELETE_INITIAL);
		aRoleEntity.setId(id);
		final Role role = this.roleMapper.selectById(aRoleEntity);
		return role.getRoleAuths().stream().map(RoleAuth::getAuthId).toList();
	}

	@Override
	public List<AuthorityDto> getAuthList() {
		return this.authorityMapper.selectAll(OgumaProjectUtils.EMPTY_STRING).stream()
				.map(item -> new AuthorityDto(item.getId(), item.getName(), item.getTitle(), item.getCategoryId()))
				.toList();
	}

	@Override
	public RoleDto getRoleById(final Long id) {
		final Role aRoleEntity = new Role();
		aRoleEntity.setDelFlg(OgumaProjectConstants.LOGIC_DELETE_INITIAL);
		aRoleEntity.setId(id);
		final Role role = this.roleMapper.selectById(aRoleEntity);
		return new RoleDto(role.getId(), role.getName());
	}

	@Override
	public List<RoleDto> getRolesByEmployeeId(final Long employeeId) {
		final List<Role> roleDtos = new ArrayList<>();
		final List<Role> roles = this.roleMapper.selectAll(OgumaProjectConstants.LOGIC_DELETE_INITIAL);
		if (employeeId == null) {
			final Role role = new Role();
			role.setId(0L);
			role.setName(OgumaProjectConstants.DEFAULT_ROLE_NAME);
			roleDtos.add(role);
		} else {
			final EmployeeRole employeeRole = this.employeeRoleMapper.selectById(employeeId);
			if (employeeRole == null) {
				final Role role = new Role();
				role.setId(0L);
				role.setName(OgumaProjectConstants.DEFAULT_ROLE_NAME);
				roleDtos.add(role);
			} else {
				final Role selectedRole = roles.stream()
						.filter(a -> OgumaProjectUtils.isEqual(a.getId(), employeeRole.getRoleId())).findFirst().get();
				roleDtos.add(selectedRole);
			}
		}
		roleDtos.addAll(roles);
		return roleDtos.stream().distinct().map(item -> new RoleDto(item.getId(), item.getName())).toList();
	}

	@Override
	public Pagination<RoleDto> getRolesByKeyword(final Integer pageNum, final String keyword) {
		final Integer offset = (pageNum - 1) * PAGE_SIZE;
		final String searchStr = OgumaProjectUtils.getDetailKeyword(keyword);
		final Long records = this.roleMapper.countByKeyword(searchStr, OgumaProjectConstants.LOGIC_DELETE_INITIAL);
		final List<RoleDto> roleDtos = this.roleMapper
				.paginationByKeyword(searchStr, offset, PAGE_SIZE, OgumaProjectConstants.LOGIC_DELETE_INITIAL).stream()
				.map(item -> new RoleDto(item.getId(), item.getName())).toList();
		return Pagination.of(roleDtos, records, pageNum, PAGE_SIZE);
	}

	@Override
	public ResultDto<String> remove(final Long id) {
		final List<EmployeeRole> list = this.employeeRoleMapper.selectByRoleId(id);
		if (!list.isEmpty()) {
			return ResultDto.failed(OgumaProjectConstants.MESSAGE_STRING_FORBIDDEN);
		}
		final Role entity = new Role();
		entity.setId(id);
		entity.setDelFlg(OgumaProjectConstants.LOGIC_DELETE_FLG);
		this.roleMapper.removeById(entity);
		return ResultDto.successWithoutData(OgumaProjectConstants.MESSAGE_STRING_DELETED);
	}

	@Override
	public void save(final RoleDto roleDto) {
		final Role role = new Role();
		SecondBeanUtils.copyNullableProperties(roleDto, role);
		role.setId(SnowflakeUtils.snowflakeId());
		role.setDelFlg(OgumaProjectConstants.LOGIC_DELETE_INITIAL);
		this.roleMapper.insertById(role);
	}

	@Override
	public ResultDto<String> update(final RoleDto roleDto) {
		final Role originalEntity = new Role();
		originalEntity.setDelFlg(OgumaProjectConstants.LOGIC_DELETE_INITIAL);
		originalEntity.setId(roleDto.id());
		final Role role = this.roleMapper.selectById(originalEntity);
		SecondBeanUtils.copyNullableProperties(role, originalEntity);
		SecondBeanUtils.copyNullableProperties(roleDto, role);
		if (OgumaProjectUtils.isEqual(originalEntity, role)) {
			return ResultDto.failed(OgumaProjectConstants.MESSAGE_STRING_NOCHANGE);
		}
		this.roleMapper.updateById(role);
		return ResultDto.successWithoutData();
	}
}
