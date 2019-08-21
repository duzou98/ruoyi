package com.ruoyi.web.controller.student;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.NewIdUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.shiro.service.SysPasswordService;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.Student;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.IStudentService;
import com.ruoyi.system.service.ISysUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 学生基本 信息操作处理
 * 
 * @author wubin
 * @date 2019-08-13
 */
@Controller
@RequestMapping("/system/student")
public class StudentController extends BaseController
{
	private static final Logger log = LoggerFactory.getLogger(StudentController.class);

    private String prefix = "system/student";
	
	@Autowired
	private IStudentService studentService;

	@Autowired
	private ISysUserService userService;

	@Autowired
	private SysPasswordService passwordService;
	
	@RequiresPermissions("system:student:view")
	@GetMapping()
	public String student()
	{
	    return prefix + "/student";
	}
	
	/**
	 * 查询学生基本列表
	 */
	@RequiresPermissions("system:student:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Student student)
	{
//		List<Student> students = new ArrayList<>();
		startPage();
		List<Student> list = studentService.selectStudentList(student);
		/*for (Student student1 : list) {
			String photo =Global.getAvatarPath() + student1.getStuPhoto();
			student1.setStuPhoto(photo);
			students.add(student1);
		}*/
		return getDataTable(list);
	}
	
	
	/**
	 * 导出学生基本列表
	 */
	@RequiresPermissions("system:student:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Student student)
    {
    	List<Student> list = studentService.selectStudentList(student);
        ExcelUtil<Student> util = new ExcelUtil<Student>(Student.class);
        return util.exportExcel(list, "student");
    }
	
	/**
	 * 新增学生基本
	 */
	@GetMapping("/add")
	public String add(ModelMap map)
	{
		//map.put("sex",);
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存学生基本
	 */
	@RequiresPermissions("system:student:add")
	@Log(title = "学生基本", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(@RequestPart(name="file",required = false) MultipartFile file, Student student)
	{
		if (UserConstants.STU_NUM_NOT_UNIQUE.equals(studentService.checkStuNumUnique(student.getStuNum())))
		{
			return error("新增学生" + student.getStuName() + "失败，学号已存在");
		}
		else if (UserConstants.USER_PHONE_NOT_UNIQUE.equals(studentService.checkPhoneUnique(student.getStuTel())))
		{
			return error("新增学生" + student.getStuName() + "失败，手机号码已存在");
		}
		else if (UserConstants.USER_EMAIL_NOT_UNIQUE.equals(studentService.checkEmailUnique(student.getStuEmail())))
		{
			return error("新增学生" + student.getStuName() + "失败，邮箱账号已存在");
		}
		String id = NewIdUtils.getRandomIdByUUID();
		student.setId(id);
		// 上传文件路径
		String filePath = Global.getAvatarPath();
		// 上传并返回新文件名称
		try {
			String fileName = FileUploadUtils.upload(filePath, file);
			student.setStuPhoto(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		student.setCreateBy(ShiroUtils.getLoginName());

		SysUser user = getUser(student);
		if (UserConstants.USER_NAME_NOT_UNIQUE.equals(userService.checkLoginNameUnique(user.getLoginName())))
		{
			return error("新增用户'" + user.getLoginName() + "'失败，登录账号已存在");
		}
		else if (UserConstants.USER_PHONE_NOT_UNIQUE.equals(userService.checkPhoneUnique(user)))
		{
			return error("新增用户'" + user.getLoginName() + "'失败，手机号码已存在");
		}
		else if (UserConstants.USER_EMAIL_NOT_UNIQUE.equals(userService.checkEmailUnique(user)))
		{
			return error("新增用户'" + user.getLoginName() + "'失败，邮箱账号已存在");
		}
		AjaxResult ajaxResult = toAjax(studentService.insertStudent(student));
		System.out.println("ajaxResult = " + ajaxResult.get("msg"));
		if ("操作失败".equals(ajaxResult.get("msg"))){
			return toAjax(-1);
		}
		ajaxResult = toAjax(userService.insertUser(user));
		return ajaxResult;
	}

	/**
	 * 修改学生基本
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") String id, ModelMap mmap)
	{
		Student student = studentService.selectStudentById(id);
		mmap.put("student", student);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存学生基本
	 */
	@RequiresPermissions("system:student:edit")
	@Log(title = "学生基本", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(@RequestPart(name="file",required = false) MultipartFile file, Student student)
	{
		student.setUpdateBy(ShiroUtils.getLoginName());
		try
		{
			if (file != null && file.getSize() > 0)
			{
				String path = Global.getAvatarPath();
				String newName = FileUploadUtils.upload(path, file);
				student.setStuPhoto(newName);
				Student s = studentService.selectStudentById(student.getId());
                String stuPhoto = s.getStuPhoto();
				if (StringUtils.isNotBlank(stuPhoto)){
					String replace = stuPhoto.replace("/profile/avatar", "");
					String delPath = path + replace;
					File delF = new File(delPath);
					boolean delete = delF.delete();
					if(!delete) {//如果删除失败，有可能是此图像正在被访问中，设置为服务停止时删除
						delF.deleteOnExit();
					}
				}
			}
			if (studentService.updateStudent(student) > 0)
			{
				SysUser user = getUser(student);
				int i = userService.updateUserInfo(user);
				if (i > 0){
					return success();
				}
				return error();
			}
			return error();
		}
		catch (Exception e)
		{
			log.error("修改头像失败！", e);
			return error(e.getMessage());
		}
	}
	
	/**
	 * 删除学生基本
	 */
	@RequiresPermissions("system:student:remove")
	@Log(title = "学生基本", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{
		try {
			AjaxResult ajaxResult = toAjax(userService.deleteUserByLoginNames(ids));
			if ("操作失败".equals(ajaxResult.get("msg"))){
				return toAjax(-1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return toAjax(studentService.deleteStudentByIds(ids));
	}

	/**
	 * 校验学号
	 */
	@PostMapping("/checkStuNumUnique")
	@ResponseBody
	public String checkLoginNameUnique(Student student)
	{
		return studentService.checkStuNumUnique(student.getStuNum());
	}

	/**
	 * 校验手机号码
	 */
	@PostMapping("/checkPhoneUnique")
	@ResponseBody
	public String checkPhoneUnique(Student student)
	{
		return studentService.checkPhoneUnique(student.getStuTel());
	}

	/**
	 * 校验email邮箱
	 */
	@PostMapping("/checkEmailUnique")
	@ResponseBody
	public String checkEmailUnique(Student student)
	{
		return studentService.checkEmailUnique(student.getStuEmail());
	}

	public  SysUser getUser(Student student) {
		SysUser sysUser = new SysUser();
		sysUser.setUserName(student.getStuName());
		sysUser.setPhonenumber(student.getStuTel());
		sysUser.setEmail(student.getStuEmail());
		sysUser.setLoginName(student.getStuNum());
		sysUser.setSex(student.getStuSex());

		Long roleIds[] = {101L};
		sysUser.setRoleIds(roleIds);
		sysUser.setRemark(student.getRemark());
		sysUser.setStatus("0");
		sysUser.setCreateBy(ShiroUtils.getLoginName());
		sysUser.setSalt(ShiroUtils.randomSalt());
		sysUser.setPassword(passwordService.encryptPassword(sysUser.getLoginName(), "123456", sysUser.getSalt()));
		Long postId[] = {5L};
		sysUser.setPostIds(postId);
		return sysUser;
	}
	
}
