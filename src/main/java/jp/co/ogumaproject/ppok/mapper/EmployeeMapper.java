package jp.co.ogumaproject.ppok.mapper;

import java.util.List;

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
public interface EmployeeMapper {

	/**
	 * ログインアカウントを重複するかどうかを確認する
	 *
	 * @param loginAccount ログインアカウント
	 */
	Integer checkDuplicated(@Param("loginAccount") String loginAccount);

	/**
	 * キーワードによって社員情報の数を取得する
	 *
	 * @param keyword 検索キーワード
	 * @return Integer
	 */
	Long countByKeyword(@Param("keyword") String keyword);

	/**
	 * IDによって情報を挿入する
	 *
	 * @param employee 社員エンティティ
	 */
	void insertById(Employee employee);

	/**
	 * キーワードによって社員情報を検索する
	 *
	 * @param keyword  検索キーワード
	 * @param offset   オフセット
	 * @param pageSize ページサイズ
	 * @return List<Employee>
	 */
	List<Employee> paginationByKeyword(@Param("keyword") String keyword, @Param("offset") Integer offset,
			@Param("pageSize") Integer pageSize);

	/**
	 * IDによって論理削除を行う
	 *
	 * @param employee 社員エンティティ
	 */
	void removeById(Employee employee);

	/**
	 * アカウント、メールと生年月日によって社員情報を取得する
	 *
	 * @param employee 社員エンティティ
	 * @return Employee
	 */
	Employee selectByAccountAndEmail(Employee employee);

	/**
	 * IDによって情報を検索する
	 *
	 * @param employee 社員エンティティ
	 * @return Employee
	 */
	Employee selectById(Employee employee);

	/**
	 * IDによって情報を検索する
	 *
	 * @param id 社員ID
	 * @return Employee
	 */
	Employee selectById(@Param("id") Long id);

	/**
	 * アカウントによって社員情報を検索する
	 *
	 * @param loginAccout アカウント
	 * @return Employee
	 */
	Employee selectByLoginAcct(@Param("loginAccout") String loginAccout);

	/**
	 * IDによって情報を更新する
	 *
	 * @param employee 社員エンティティ
	 */
	void updateById(Employee employee);
}
