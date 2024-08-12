package jp.co.ogumaproject.ppok.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import jp.co.ogumaproject.ppok.entity.Employee;

/**
 * 社員マッパー
 *
 * @author ArkamaHozota
 * @since 1.00
 */
@Mapper
public interface EmployeeMapper extends CommonMapper<Employee> {

	/**
	 * ログインアカウントを重複するかどうかを確認する
	 *
	 * @param loginAccount ログインアカウント
	 */
	Integer checkDuplicated(@Param("loginAccount") String loginAccount, @Param("delFlg") String delFlg);

	/**
	 * アカウントによって社員情報を検索する
	 *
	 * @param loginAccout アカウント
	 * @param delFlg      論理削除フラグ
	 * @return Employee
	 */
	Employee selectByLoginAcct(@Param("loginAccout") String loginAccout, @Param("delFlg") String delFlg);

	/**
	 * アカウント、メールと生年月日によって社員情報を取得する
	 *
	 * @param employee 社員エンティティ
	 * @return Employee
	 */
	Employee selectByLoginAcctAndEmail(Employee employee);
}
