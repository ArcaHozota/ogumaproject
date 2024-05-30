package jp.co.ogumaproject.ppok.listener;

import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import jp.co.ogumaproject.ppok.commons.OgumaProjectConstants;
import jp.co.ogumaproject.ppok.dto.EmployeeDto;
import jp.co.ogumaproject.ppok.entity.Employee;
import jp.co.ogumaproject.ppok.entity.EmployeeRole;
import jp.co.ogumaproject.ppok.entity.Role;
import jp.co.ogumaproject.ppok.entity.RoleAuth;
import jp.co.ogumaproject.ppok.mapper.AuthorityMapper;
import jp.co.ogumaproject.ppok.mapper.EmployeeMapper;
import jp.co.ogumaproject.ppok.mapper.EmployeeRoleMapper;
import jp.co.ogumaproject.ppok.mapper.RoleMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import oracle.jdbc.driver.OracleSQLException;

/**
 * ログインコントローラ(SpringSecurity関連)
 *
 * @author ArkamaHozota
 * @since 1.00beta
 */
@Component
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Transactional(rollbackFor = OracleSQLException.class)
public class OgumaProjectUserDetailsService implements UserDetailsService {

	/**
	 * 権限マッパー
	 */
	private final AuthorityMapper authorityMapper;

	/**
	 * 社員管理マッパー
	 */
	private final EmployeeMapper employeeMapper;

	/**
	 * 社員役割連携マッパー
	 */
	private final EmployeeRoleMapper employeeRoleMapper;

	/**
	 * 役割マッパー
	 */
	private final RoleMapper roleMapper;

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		final Employee employee = this.employeeMapper.selectByLoginAcct(username);
		if (employee == null) {
			throw new DisabledException(OgumaProjectConstants.MESSAGE_SPRINGSECURITY_LOGINERROR1);
		}
		final EmployeeRole employeeRole = this.employeeRoleMapper.selectById(employee.getId());
		if (employeeRole == null) {
			throw new InsufficientAuthenticationException(OgumaProjectConstants.MESSAGE_SPRINGSECURITY_LOGINERROR2);
		}
		final Role role = this.roleMapper.selectById(employeeRole.getRoleId());
		if (role.getRoleAuths().isEmpty()) {
			throw new AuthenticationCredentialsNotFoundException(
					OgumaProjectConstants.MESSAGE_SPRINGSECURITY_LOGINERROR3);
		}
		final List<Long> authIds = role.getRoleAuths().stream().map(RoleAuth::getAuthId).toList();
		final EmployeeDto employeeDto = new EmployeeDto(employee.getId(), employee.getLoginAccount(),
				employee.getUsername(), employee.getPassword(), employee.getEmail(),
				DateTimeFormatter.ofPattern("yyyy-MM-dd").format(employee.getDateOfBirth()), employeeRole.getRoleId());
		final List<SimpleGrantedAuthority> authorities = this.authorityMapper.selectByIds(authIds).stream()
				.map(item -> new SimpleGrantedAuthority(item.getName())).toList();
		return new SecurityAdmin(employeeDto, authorities);
	}

}
