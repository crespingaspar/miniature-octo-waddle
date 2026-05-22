package com.yb.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.*;
import java.lang.*;
import java.math.*;
import com.yb.utils.*;
import com.yb.service.*;
import com.yb.entity.*;
import com.yb.entity.view.*;
import java.util.stream.Collectors;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.yb.annotation.IgnoreAuth;
import com.yb.entity.XuankexinxiEntity;
import com.yb.entity.view.XuankexinxiView;

import com.yb.service.XuankexinxiService;
import com.yb.service.XueshengService;
import com.yb.service.KechengxinxiService;
import com.yb.utils.PageUtils;
import com.yb.utils.R;
import com.yb.utils.MPUtil;
import com.yb.utils.MapUtils;
import com.yb.utils.CommonUtil;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.nio.charset.StandardCharsets;
/**
 * 选课信息
 * 后端接口
 * @author 
 * @email 
 * @date 2026-04-11 14:37:54
 */
@RestController
@RequestMapping("/xuankexinxi")
public class XuankexinxiController {
    @Autowired
    private XuankexinxiService xuankexinxiService;
    @Autowired
    private XueshengService xueshengService;
    @Autowired
    private KechengxinxiService kechengxinxiService;








    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,XuankexinxiEntity xuankexinxi,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("xuesheng")) {
			xuankexinxi.setXuehao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("jiaoshi")) {
			xuankexinxi.setGonghao((String)request.getSession().getAttribute("username"));
		}
        //设置查询条件
        QueryWrapper<XuankexinxiEntity> ew = new QueryWrapper<XuankexinxiEntity>();


        //查询结果
		PageUtils page = xuankexinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, xuankexinxi), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }


    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,XuankexinxiEntity xuankexinxi,
                @RequestParam(required = false) Double keshistart,
                @RequestParam(required = false) Double keshiend,
                @RequestParam(required = false) Double feiyongstart,
                @RequestParam(required = false) Double feiyongend,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date xuankeshijianstart,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date xuankeshijianend,
		HttpServletRequest request){
        //设置查询条件
        QueryWrapper<XuankexinxiEntity> ew = new QueryWrapper<XuankexinxiEntity>();
        if(keshistart!=null) ew.ge("keshi", keshistart);
        if(keshiend!=null) ew.le("keshi", keshiend);
        if(feiyongstart!=null) ew.ge("feiyong", feiyongstart);
        if(feiyongend!=null) ew.le("feiyong", feiyongend);
        if(xuankeshijianstart!=null) ew.ge("xuankeshijian", xuankeshijianstart);
        if(xuankeshijianend!=null) ew.le("xuankeshijian", xuankeshijianend);

        //查询结果
		PageUtils page = xuankexinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, xuankexinxi), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }




	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( XuankexinxiEntity xuankexinxi){
       	QueryWrapper<XuankexinxiEntity> ew = new QueryWrapper<XuankexinxiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( xuankexinxi, "xuankexinxi"));
        return R.ok().put("data", xuankexinxiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(XuankexinxiEntity xuankexinxi){
        QueryWrapper< XuankexinxiEntity> ew = new QueryWrapper< XuankexinxiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( xuankexinxi, "xuankexinxi"));
		XuankexinxiView xuankexinxiView =  xuankexinxiService.selectView(ew);
		return R.ok("查询选课信息成功").put("data", xuankexinxiView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        XuankexinxiEntity xuankexinxi = xuankexinxiService.getById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(xuankexinxi,deSens);
        return R.ok().put("data", xuankexinxi);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        XuankexinxiEntity xuankexinxi = xuankexinxiService.getById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(xuankexinxi,deSens);
        return R.ok().put("data", xuankexinxi);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody XuankexinxiEntity xuankexinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(xuankexinxi);
        xuankexinxiService.save(xuankexinxi);
        return R.ok().put("data",xuankexinxi.getId());
    }

    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody XuankexinxiEntity xuankexinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(xuankexinxi);
        xuankexinxiService.save(xuankexinxi);
        return R.ok().put("data",xuankexinxi.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody XuankexinxiEntity xuankexinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(xuankexinxi);
        //全部更新
        xuankexinxiService.updateById(xuankexinxi);
        return R.ok();
    }

    /**
     * 审核
     */
    @RequestMapping("/shBatch")
    @Transactional
    public R update(@RequestBody Long[] ids, @RequestParam String sfsh, @RequestParam String shhf){
        List<XuankexinxiEntity> list = new ArrayList<XuankexinxiEntity>();
        for(Long id : ids) {
            XuankexinxiEntity xuankexinxi = xuankexinxiService.getById(id);
            xuankexinxi.setSfsh(sfsh);
            xuankexinxi.setShhf(shhf);
            list.add(xuankexinxi);
        }
        xuankexinxiService.updateBatchById(list);
        return R.ok();
    }




    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        xuankexinxiService.removeBatchByIds(Arrays.asList(ids));
        return R.ok();
    }
    /**
     * 教师端一键导入学生选课（Excel）
     */
    @RequestMapping("/importStudents")
    @Transactional
    public R importStudents(@RequestParam("file") MultipartFile file,
                            @RequestParam Long courseId,
                            @RequestParam(required = false, defaultValue = "30") Integer classSize,
                            @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date startTime,
                            @RequestParam(required = false, defaultValue = "120") Integer intervalMinutes,
                            HttpServletRequest request) throws IOException {
        if(file==null || file.isEmpty()) {
            return R.error("请上传Excel文件");
        }
        String filename = file.getOriginalFilename()==null?"":file.getOriginalFilename().toLowerCase();
        if(!(filename.endsWith(".xls") || filename.endsWith(".xlsx"))) {
            return R.error("仅支持Excel文件（.xls/.xlsx）");
        }
        KechengxinxiEntity course = kechengxinxiService.getById(courseId);
        if(course == null){
            return R.error("课程不存在");
        }
        String tableName = String.valueOf(request.getSession().getAttribute("tableName"));
        String username = String.valueOf(request.getSession().getAttribute("username"));
        if("jiaoshi".equals(tableName) && !StringUtils.equals(course.getGonghao(), username)) {
            return R.error("只能导入自己开设的课程");
        }

        List<Map<String, Object>> rows;
        try {
            rows = CommonUtil.importExcelAsMap(file);
        } catch (Exception ex) {
            // 兼容“伪Excel”(文本制表符文件改后缀为 xls/xlsx)导入
            rows = parseTabTextRows(file);
        }
        if(rows==null || rows.isEmpty()) {
            return R.error("Excel中没有可导入的数据");
        }

        List<XuankexinxiEntity> saveList = new ArrayList<>();
        List<Map<String, Object>> existedStudents = new ArrayList<>();
        LocalDateTime baseTime = startTime==null ? LocalDateTime.now() : LocalDateTime.ofInstant(startTime.toInstant(), ZoneId.systemDefault());
        int idx = 0;
        for (Map<String, Object> row : rows) {
            String xuehao = Objects.toString(row.get("学号"), "").trim();
            String xingming = Objects.toString(row.get("姓名"), "").trim();
            String mima = Objects.toString(row.get("密码"), "").trim();
            String xingbie = Objects.toString(row.get("性别"), "").trim();
            String banji = Objects.toString(row.get("班级"), "").trim();
            String mobile = Objects.toString(row.get("手机号"), "").trim();
            String beikaokemu = Objects.toString(row.get("备考科目"), "").trim();
            String jichushuiping = Objects.toString(row.get("基础水平"), "").trim();
            String mubiaoyuanxiao = Objects.toString(row.get("目标院校"), "").trim();
            Integer nianling = null;
            Object ageObj = row.get("年龄");
            if(ageObj!=null && StringUtils.isNotBlank(ageObj.toString())) {
                try {
                    nianling = Integer.parseInt(ageObj.toString().trim().replace(".0",""));
                } catch (Exception ignored) {}
            }
            if(StringUtils.isBlank(xuehao) || StringUtils.isBlank(xingming) || StringUtils.isBlank(banji)) {
                continue;
            }
            XueshengEntity student = xueshengService.getOne(new QueryWrapper<XueshengEntity>().eq("xuehao", xuehao));
            if(student==null) {
                student = new XueshengEntity();
                student.setId(new Date().getTime() + idx);
                student.setXuehao(xuehao);
                student.setMima(StringUtils.isBlank(mima) ? "123456" : mima);
                student.setXingming(xingming);
                student.setXingbie(StringUtils.isBlank(xingbie) ? "" : xingbie);
                student.setNianling(nianling);
                student.setMubiaoyuanxiao(StringUtils.isBlank(mubiaoyuanxiao) ? "无" : mubiaoyuanxiao);
                student.setBanji(banji);
                student.setBeikaokemu(StringUtils.isBlank(beikaokemu) ? "无" : beikaokemu);
                student.setJichushuiping(StringUtils.isBlank(jichushuiping) ? "无" : jichushuiping);
                student.setMobile(StringUtils.isBlank(mobile) ? "无" : mobile);
                student.setStatus(0);
                xueshengService.save(student);
            } else {
                Map<String, Object> existedItem = new HashMap<>();
                existedItem.put("xuehao", xuehao);
                existedItem.put("xingming", xingming);
                existedItem.put("banji", banji);
                existedItem.put("msg", xingming + "（学号:" + xuehao + "）已存在账号");
                existedStudents.add(existedItem);
                if(StringUtils.isBlank(student.getMima())) {
                    student.setMima(StringUtils.isBlank(mima) ? "123456" : mima);
                }
                if(StringUtils.isNotBlank(xingming)) student.setXingming(xingming);
                student.setXingbie(StringUtils.isBlank(xingbie) ? "" : xingbie);
                if(nianling!=null) student.setNianling(nianling);
                student.setMubiaoyuanxiao(StringUtils.isBlank(mubiaoyuanxiao) ? "无" : mubiaoyuanxiao);
                student.setBanji(banji);
                student.setBeikaokemu(StringUtils.isBlank(beikaokemu) ? "无" : beikaokemu);
                student.setJichushuiping(StringUtils.isBlank(jichushuiping) ? "无" : jichushuiping);
                student.setMobile(StringUtils.isBlank(mobile) ? "无" : mobile);
                xueshengService.updateById(student);
            }
            XuankexinxiEntity entity = new XuankexinxiEntity();
            entity.setKechenghao(course.getKechenghao());
            entity.setKechengmingcheng(course.getKechengmingcheng());
            entity.setKechengfenlei(course.getKechengfenlei());
            entity.setTupian(course.getTupian());
            entity.setNandu(course.getNandu());
            entity.setKeshi(course.getKeshi());
            entity.setFeiyong(course.getFeiyong());
            entity.setXuehao(xuehao);
            entity.setXingming(xingming);
            entity.setGonghao(course.getGonghao());
            entity.setJiaoshixingming(course.getJiaoshixingming());
            entity.setSfsh("已审核");
            int classIndex = (idx / Math.max(classSize, 1)) + 1;
            entity.setShhf("第" + classIndex + "班");
            LocalDateTime courseTime = baseTime.plusMinutes((long)(classIndex - 1) * Math.max(intervalMinutes, 0));
            entity.setXuankeshijian(Date.from(courseTime.atZone(ZoneId.systemDefault()).toInstant()));
            saveList.add(entity);
            idx++;
        }

        if(saveList.isEmpty()){
            return R.error("模板中必须包含“学号、姓名、班级”三列且至少有一行数据");
        }
        xuankexinxiService.saveBatch(saveList);
        return R.ok("导入成功")
                .put("count", saveList.size())
                .put("existedCount", existedStudents.size())
                .put("existedStudents", existedStudents);
    }

    /**
     * 解析制表符文本（首行为表头）
     */
    private List<Map<String, Object>> parseTabTextRows(MultipartFile file) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            String headerLine = reader.readLine();
            if (StringUtils.isBlank(headerLine)) {
                return list;
            }
            String[] headers = headerLine.replace("\uFEFF", "").split("\t", -1);
            String line;
            while ((line = reader.readLine()) != null) {
                if (StringUtils.isBlank(line)) {
                    continue;
                }
                String[] cols = line.split("\t", -1);
                Map<String, Object> row = new HashMap<>();
                for (int i = 0; i < headers.length; i++) {
                    row.put(headers[i].trim(), i < cols.length ? cols[i].trim() : "");
                }
                list.add(row);
            }
        }
        return list;
    }










    // hasAlipay:否




}
