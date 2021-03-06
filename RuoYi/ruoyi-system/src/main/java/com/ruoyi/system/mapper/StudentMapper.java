package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.Student;
import com.ruoyi.system.domain.SysUser;

import java.util.List;

/**
 * 学生基本 数据层
 * 
 * @author wubin
 * @date 2019-08-13
 */
public interface StudentMapper 
{
	/**
     * 查询学生基本信息
     * 
     * @param id 学生基本ID
     * @return 学生基本信息
     */
	public Student selectStudentById(String id);
	
	/**
     * 查询学生基本列表
     * 
     * @param student 学生基本信息
     * @return 学生基本集合
     */
	public List<Student> selectStudentList(Student student);
	
	/**
     * 新增学生基本
     * 
     * @param student 学生基本信息
     * @return 结果
     */
	public int insertStudent(Student student);
	
	/**
     * 修改学生基本
     * 
     * @param student 学生基本信息
     * @return 结果
     */
	public int updateStudent(Student student);
	
	/**
     * 删除学生基本
     * 
     * @param id 学生基本ID
     * @return 结果
     */
	public int deleteStudentById(String id);
	
	/**
     * 批量删除学生基本
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteStudentByIds(String[] ids);

	/**
	 * 校验用户名称是否唯一
	 *
	 * @param stunum 学号
	 * @return 结果
	 */
	public int checkStuNumUnique(String stunum);

	/**
	 * 校验手机号码是否唯一
	 *
	 * @param phonenumber 手机号码
	 * @return 结果
	 */
	public int checkPhoneUnique(String phonenumber);

	/**
	 * 校验email是否唯一
	 *
	 * @param email 用户邮箱
	 * @return 结果
	 */
	public int checkEmailUnique(String email);
	
}