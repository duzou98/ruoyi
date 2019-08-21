package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.constant.UserConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.StudentMapper;
import com.ruoyi.system.domain.Student;
import com.ruoyi.system.service.IStudentService;
import com.ruoyi.common.core.text.Convert;

/**
 * 学生基本 服务层实现
 * 
 * @author wubin
 * @date 2019-08-13
 */
@Service
public class StudentServiceImpl implements IStudentService 
{
	@Autowired
	private StudentMapper studentMapper;

	/**
     * 查询学生基本信息
     * 
     * @param id 学生基本ID
     * @return 学生基本信息
     */
    @Override
	public Student selectStudentById(String id)
	{
	    return studentMapper.selectStudentById(id);
	}
	
	/**
     * 查询学生基本列表
     * 
     * @param student 学生基本信息
     * @return 学生基本集合
     */
	@Override
	public List<Student> selectStudentList(Student student)
	{
	    return studentMapper.selectStudentList(student);
	}
	
    /**
     * 新增学生基本
     * 
     * @param student 学生基本信息
     * @return 结果
     */
	@Override
	public int insertStudent(Student student)
	{
	    return studentMapper.insertStudent(student);
	}
	
	/**
     * 修改学生基本
     * 
     * @param student 学生基本信息
     * @return 结果
     */
	@Override
	public int updateStudent(Student student)
	{
	    return studentMapper.updateStudent(student);
	}

	/**
     * 删除学生基本对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteStudentByIds(String ids)
	{
		return studentMapper.deleteStudentByIds(Convert.toStrArray(ids));
	}

	/**
	 * 学号是否唯一
	 * @param stunum 学号
	 * @return
	 */
	@Override
	public String checkStuNumUnique(String stunum) {
		int count = studentMapper.checkStuNumUnique(stunum);
		if (count > 0)
		{
			return UserConstants.STU_NUM_NOT_UNIQUE;
		}
		return UserConstants.STU_NUM_UNIQUE;
	}

	/**
	 * 手机号码是否唯一
	 * @param phonenumber 手机号码
	 * @return
	 */
	@Override
	public String checkPhoneUnique(String phonenumber) {
		int count = studentMapper.checkPhoneUnique(phonenumber);
		if (count > 0){
			return  UserConstants.USER_PHONE_NOT_UNIQUE;
		}
		return UserConstants.USER_PHONE_UNIQUE;
	}

	/**
	 * 邮箱是否唯一
	 * @param email 用户邮箱
	 * @return
	 */
	@Override
	public String checkEmailUnique(String email) {
		int count = studentMapper.checkPhoneUnique(email);
		if (count > 0){
			return  UserConstants.USER_EMAIL_NOT_UNIQUE;
		}
		return UserConstants.USER_EMAIL_UNIQUE;
	}

}
