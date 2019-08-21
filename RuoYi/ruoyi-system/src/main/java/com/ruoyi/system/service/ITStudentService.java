package com.ruoyi.system.service;

import com.ruoyi.system.domain.Student;
import java.util.List;

/**
 * 学生基本 服务层
 * 
 * @author ruoyi
 * @date 2019-08-13
 */
public interface ITStudentService 
{
	/**
     * 查询学生基本信息
     * 
     * @param id 学生基本ID
     * @return 学生基本信息
     */
	public Student selectTStudentById(String id);
	
	/**
     * 查询学生基本列表
     * 
     * @param tStudent 学生基本信息
     * @return 学生基本集合
     */
	public List<Student> selectTStudentList(Student tStudent);
	
	/**
     * 新增学生基本
     * 
     * @param tStudent 学生基本信息
     * @return 结果
     */
	public int insertTStudent(Student tStudent);
	
	/**
     * 修改学生基本
     * 
     * @param tStudent 学生基本信息
     * @return 结果
     */
	public int updateTStudent(Student tStudent);
		
	/**
     * 删除学生基本信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTStudentByIds(String ids);
	
}
