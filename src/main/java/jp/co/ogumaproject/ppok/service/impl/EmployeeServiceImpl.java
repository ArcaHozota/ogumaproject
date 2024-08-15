package jp.co.ogumaproject.ppok.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.ogumaproject.ppok.commons.OgumaProjectConstants;
import jp.co.ogumaproject.ppok.config.OgumaPasswordEncoder;
import jp.co.ogumaproject.ppok.dto.EmployeeDto;
import jp.co.ogumaproject.ppok.entity.Employee;
import jp.co.ogumaproject.ppok.entity.EmployeeRole;
import jp.co.ogumaproject.ppok.mapper.EmployeeMapper;
import jp.co.ogumaproject.ppok.mapper.EmployeeRoleMapper;
import jp.co.ogumaproject.ppok.service.IEmployeeService;
import jp.co.ogumaproject.ppok.utils.OgumaProjectUtils;
import jp.co.ogumaproject.ppok.utils.Pagination;
import jp.co.ogumaproject.ppok.utils.ResultDto;
import jp.co.ogumaproject.ppok.utils.SecondBeanUtils;
import jp.co.ogumaproject.ppok.utils.SnowflakeUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import oracle.jdbc.driver.OracleSQLException;

/**
 * 社員サービス実装クラス
 *
 * @author ArkamaHozota
 * @since 1.00
 */
@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Transactional(rollbackFor = OracleSQLException.class)
public class EmployeeServiceImpl implements IEmployeeService {

	/**
	 * ページサイズ
	 */
	private static final Integer PAGE_SIZE = OgumaProjectConstants.DEFAULT_PAGE_SIZE;

	/**
	 * 日時フォマーター
	 */
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	/**
	 * パスワードエンコーダ
	 */
	private static final OgumaPasswordEncoder ENCODER = new OgumaPasswordEncoder();

	/**
	 * Randomナンバー
	 */
	private static final Random RANDOM = new Random();

	/**
	 * 社員管理マッパー
	 */
	private final EmployeeMapper employeeMapper;

	/**
	 * 社員役割連携マッパー
	 */
	private final EmployeeRoleMapper employeeRoleMapper;

	@Override
	public ResultDto<String> checkDuplicated(final String loginAccount) {
		return this.employeeMapper.checkDuplicated(loginAccount, OgumaProjectConstants.LOGIC_DELETE_INITIAL) > 0
				? ResultDto.failed(OgumaProjectConstants.MESSAGE_STRING_DUPLICATED)
				: ResultDto.successWithoutData();
	}

	@Override
	public EmployeeDto getEmployeeById(final Long id) {
		final Employee employeeEntity = new Employee();
		employeeEntity.setDelFlg(OgumaProjectConstants.LOGIC_DELETE_INITIAL);
		employeeEntity.setId(id);
		final Employee employee = this.employeeMapper.selectById(employeeEntity);
		return new EmployeeDto(employee.getId(), employee.getLoginAccount(), employee.getUsername(),
				OgumaProjectConstants.DEFAULT_ROLE_NAME, employee.getEmail(),
				FORMATTER.format(employee.getDateOfBirth()), null);
	}

	@Override
	public Pagination<EmployeeDto> getEmployeesByKeyword(final Integer pageNum, final String keyword, final Long userId,
			final String authChkFlag) {
		final Integer offset = (pageNum - 1) * PAGE_SIZE;
		if (Boolean.FALSE.equals(Boolean.valueOf(authChkFlag))) {
			final Employee employeeEntity = new Employee();
			employeeEntity.setDelFlg(OgumaProjectConstants.LOGIC_DELETE_INITIAL);
			employeeEntity.setId(userId);
			final Employee employee = this.employeeMapper.selectById(employeeEntity);
			final EmployeeDto employeeDto = new EmployeeDto(employee.getId(), employee.getLoginAccount(),
					employee.getUsername(), employee.getPassword(), employee.getEmail(),
					FORMATTER.format(employee.getDateOfBirth()), null);
			final List<EmployeeDto> employeeDtos = new ArrayList<>();
			employeeDtos.add(employeeDto);
			return Pagination.of(employeeDtos, employeeDtos.size(), pageNum, PAGE_SIZE);
		}
		final String searchStr = OgumaProjectUtils.getDetailKeyword(keyword);
		final Long records = this.employeeMapper.countByKeyword(searchStr, OgumaProjectConstants.LOGIC_DELETE_INITIAL);
		final List<EmployeeDto> pages = this.employeeMapper
				.paginationByKeyword(searchStr, offset, PAGE_SIZE, OgumaProjectConstants.LOGIC_DELETE_INITIAL).stream()
				.map(item -> new EmployeeDto(item.getId(), item.getLoginAccount(), item.getUsername(),
						item.getPassword(), item.getEmail(), FORMATTER.format(item.getDateOfBirth()), null))
				.toList();
		return Pagination.of(pages, records, pageNum, PAGE_SIZE);
	}

	/**
	 * ランダムのストリングを生成する
	 *
	 * @return ランダムストリング
	 */
	private String getRandomStr() {
		final String stry = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		final char[] cr1 = stry.toCharArray();
		final char[] cr2 = stry.toLowerCase().toCharArray();
		final StringBuilder builder = new StringBuilder();
		builder.append(cr1[RANDOM.nextInt(cr1.length)]);
		for (int i = 0; i < 7; i++) {
			builder.append(cr2[RANDOM.nextInt(cr2.length)]);
		}
		return builder.toString();
	}

	@Override
	public Boolean register(final EmployeeDto employeeDto) {
		final Employee selectByLoginAcct = this.employeeMapper.selectByLoginAcct(employeeDto.email(),
				OgumaProjectConstants.LOGIC_DELETE_INITIAL);
		if (selectByLoginAcct != null) {
			return Boolean.FALSE;
		}
		final String password = ENCODER.encode(employeeDto.password());
		final Employee employee = new Employee();
		SecondBeanUtils.copyNullableProperties(employeeDto, employee);
		employee.setId(SnowflakeUtils.snowflakeId());
		employee.setLoginAccount(this.getRandomStr());
		employee.setPassword(password);
		employee.setDelFlg(OgumaProjectConstants.LOGIC_DELETE_INITIAL);
		employee.setCreatedTime(LocalDateTime.now());
		employee.setDateOfBirth(LocalDate.parse(employeeDto.dateOfBirth(), EmployeeServiceImpl.FORMATTER));
		this.employeeMapper.insertById(employee);
		return Boolean.TRUE;
	}

	@Override
	public void remove(final Long id) {
		final Employee entity = new Employee();
		entity.setId(id);
		entity.setDelFlg(OgumaProjectConstants.LOGIC_DELETE_FLG);
		this.employeeMapper.removeById(entity);
		this.employeeRoleMapper.deleteById(id);
	}

	@Override
	public Boolean resetPassword(final EmployeeDto employeeDto) {
		final Employee employee = new Employee();
		SecondBeanUtils.copyNullableProperties(employeeDto, employee);
		final Employee selectByAccountAndEmail = this.employeeMapper.selectByLoginAcctAndEmail(employee);
		if (selectByAccountAndEmail == null) {
			return Boolean.FALSE;
		}
		selectByAccountAndEmail.setPassword(ENCODER.encode(OgumaProjectConstants.DEFAULT_PASSWORD));
		this.employeeMapper.insertById(selectByAccountAndEmail);
		return Boolean.TRUE;
	}

	@Override
	public void save(final EmployeeDto employeeDto) {
		final Employee employee = new Employee();
		final String password = ENCODER.encode(employeeDto.password());
		SecondBeanUtils.copyNullableProperties(employeeDto, employee);
		employee.setId(SnowflakeUtils.snowflakeId());
		employee.setPassword(password);
		employee.setDateOfBirth(LocalDate.parse(employeeDto.dateOfBirth(), EmployeeServiceImpl.FORMATTER));
		employee.setCreatedTime(LocalDateTime.now());
		employee.setDelFlg(OgumaProjectConstants.LOGIC_DELETE_INITIAL);
		this.employeeMapper.insertById(employee);
		if ((employeeDto.roleId() != null) && !Objects.equals(Long.valueOf(0L), employeeDto.roleId())) {
			final EmployeeRole employeeRole = new EmployeeRole();
			employeeRole.setEmployeeId(employee.getId());
			employeeRole.setRoleId(employeeDto.roleId());
			this.employeeRoleMapper.insertById(employeeRole);
		}
	}

	@Override
	public ResultDto<String> update(final EmployeeDto employeeDto) {
		final Employee originalEntity = new Employee();
		originalEntity.setDelFlg(OgumaProjectConstants.LOGIC_DELETE_INITIAL);
		originalEntity.setId(employeeDto.id());
		final Employee employee = this.employeeMapper.selectById(originalEntity);
		SecondBeanUtils.copyNullableProperties(employee, originalEntity);
		final String password = employeeDto.password();
		boolean passwordMatch = false;
		if (OgumaProjectUtils.isNotEmpty(password)) {
			passwordMatch = ENCODER.matches(password, employee.getPassword());
		} else {
			passwordMatch = true;
		}
		SecondBeanUtils.copyNullableProperties(employeeDto, employee);
		originalEntity.setPassword(OgumaProjectUtils.EMPTY_STRING);
		employee.setPassword(OgumaProjectUtils.EMPTY_STRING);
		final EmployeeRole employeeRole = this.employeeRoleMapper.selectById(employee.getId());
		if (OgumaProjectUtils.isEqual(originalEntity, employee) && passwordMatch) {
			if ((employeeRole == null) || OgumaProjectUtils.isEqual(employeeRole.getRoleId(), employeeDto.roleId())) {
				return ResultDto.failed(OgumaProjectConstants.MESSAGE_STRING_NOCHANGE);
			}
		}
		if (!passwordMatch) {
			employee.setPassword(ENCODER.encode(password));
		}
		employeeRole.setRoleId(employeeDto.roleId());
		this.employeeRoleMapper.updateById(employeeRole);
		this.employeeMapper.updateById(employee);
		return ResultDto.successWithoutData();
	}
}
