package com.yb.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.*;
import java.lang.*;
import java.math.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.stream.Collectors;
import com.yb.utils.*;
import com.yb.service.*;
import com.yb.entity.*;
import com.yb.entity.view.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.yb.service.TokenService;
import com.yb.entity.TokenEntity;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
import com.yb.entity.XueshengEntity;
import com.yb.entity.view.XueshengView;
import com.yb.service.XueshengService;
import com.yb.utils.PageUtils;
import com.yb.utils.R;
import com.yb.utils.MPUtil;
import com.yb.utils.MapUtils;
import com.yb.utils.CommonUtil;
import com.yb.service.SmsregistercodeService;
import com.yb.entity.SmsregistercodeEntity;

/**
 * 学生
 * 后端接口
 * @author
 * @email
 * @date 2026-04-11 14:37:53
 */
@RestController
@RequestMapping("/xuesheng")
public class XueshengController {

    @Autowired
    private XueshengService xueshengService;
    @Autowired
    private SmsregistercodeService smsregistercodeService;
    @Autowired
    private TokenService tokenService;

    // =====================================================================
    // 登录 / 注册 / 短信
    // =====================================================================

    /** 登录 */
    @IgnoreAuth
    @RequestMapping(value = "/login")
    public R login(String username, String password, String captcha, HttpServletRequest request) {
        XueshengEntity u = xueshengService.getOne(new QueryWrapper<XueshengEntity>().eq("xuehao", username));
        if (u != null && u.getStatus().intValue() == 1) {
            return R.error("账号已锁定，请联系管理员。");
        }
        if (u == null || !u.getMima().equals(password)) {
            return R.error("账号或密码不正确");
        }
        String token = tokenService.generateToken(u.getId(), username, "xuesheng", "学生");
        return R.ok().put("token", token);
    }

    /** 注册 */
    @IgnoreAuth
    @RequestMapping("/register")
    public R register(@RequestBody XueshengEntity xuesheng, @RequestParam(required = false) String smscode) {
        XueshengEntity u = xueshengService.getOne(new QueryWrapper<XueshengEntity>().eq("xuehao", xuesheng.getXuehao()));
        if (u != null) {
            return R.error("注册用户已存在");
        }
        if (xueshengService.count(new QueryWrapper<XueshengEntity>().eq("xuehao", xuesheng.getXuehao())) > 0) {
            return R.error("学号已存在");
        }
        u = xueshengService.getOne(new QueryWrapper<XueshengEntity>().eq("mobile", xuesheng.getMobile()));
        if (u != null) {
            return R.error("手机已被注册");
        }
        List<SmsregistercodeEntity> smsregistercodeList = smsregistercodeService.list(
                new QueryWrapper<SmsregistercodeEntity>().eq("role", "学生").eq("mobile", xuesheng.getMobile()).orderByDesc("addtime"));
        boolean smsValidate = smsregistercodeList != null && !smsregistercodeList.isEmpty()
                && smsregistercodeList.get(0).getCode().equals(smscode);
        if (!smsValidate) return R.error("短信验证码不正确");
        xuesheng.setId(new Date().getTime());
        xueshengService.save(xuesheng);
        return R.ok();
    }

    /** 发送短信 */
    @IgnoreAuth
    @RequestMapping("/sendsms")
    public R sendsms(@RequestParam String mobile) {
        String code = CommonUtil.getRandomNumber(6);
        SmsregistercodeEntity smsregistercode = new SmsregistercodeEntity();
        smsregistercode.setCode(code);
        smsregistercode.setRole("学生");
        smsregistercode.setMobile(mobile);
        smsregistercodeService.save(smsregistercode);
        CommonUtil.sendSMS(mobile, code);
        return R.ok().put("data", code);
    }

    /** 发送登录短信 */
    @IgnoreAuth
    @RequestMapping("/sendsms/login")
    public R sendsmsForLogin(@RequestParam String mobile) {
        XueshengEntity u = xueshengService.getOne(new QueryWrapper<XueshengEntity>().eq("mobile", mobile));
        if (u == null) return R.error("用户不存在");
        String code = CommonUtil.getRandomNumber(6);
        SmsregistercodeEntity smsregistercode = new SmsregistercodeEntity();
        smsregistercode.setCode(code);
        smsregistercode.setRole("学生");
        smsregistercode.setMobile(mobile);
        smsregistercodeService.save(smsregistercode);
        CommonUtil.sendSMS(mobile, code);
        return R.ok().put("data", code);
    }

    /** 手机号登录 */
    @IgnoreAuth
    @RequestMapping("/sms/login")
    public R emailLogin(@RequestParam String mobile, @RequestParam(required = false) String smscode) {
        XueshengEntity u = xueshengService.getOne(new QueryWrapper<XueshengEntity>().eq("mobile", mobile));
        if (u == null) return R.error("用户不存在");
        List<SmsregistercodeEntity> smsregistercodeList = smsregistercodeService.list(
                new QueryWrapper<SmsregistercodeEntity>().eq("role", "学生").eq("mobile", mobile).orderByDesc("addtime"));
        boolean smsValidate = smsregistercodeList != null && !smsregistercodeList.isEmpty()
                && smsregistercodeList.get(0).getCode().equals(smscode);
        if (!smsValidate) return R.error("短信验证码不正确");
        if (u.getStatus().intValue() == 1) {
            return R.error(510, "该账号已被锁定，是否恢复？").put("username", u.getXuehao());
        }
        String token = tokenService.generateToken(u.getId(), u.getXuehao(), "xuesheng", "学生");
        return R.ok().put("token", token).put("username", u.getXuehao());
    }

    /** 退出 */
    @RequestMapping("/logout")
    public R logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return R.ok("退出成功");
    }

    /** 获取 session 用户信息 */
    @RequestMapping("/session")
    public R getCurrUser(HttpServletRequest request) {
        Long id = (Long) request.getSession().getAttribute("userId");
        XueshengEntity u = xueshengService.getById(id);
        return R.ok().put("data", u);
    }

    /** 密码重置 */
    @IgnoreAuth
    @RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request) {
        XueshengEntity u = xueshengService.getOne(new QueryWrapper<XueshengEntity>().eq("xuehao", username));
        if (u == null) {
            return R.error("账号不存在");
        }
        u.setMima("123456");
        xueshengService.updateById(u);
        return R.ok("密码已重置为：123456");
    }

    /** 获取账号列表 */
    @RequestMapping("/accountList")
    public R getAccountList(@RequestParam Map<String, Object> params, XueshengEntity xuesheng) {
        QueryWrapper<XueshengEntity> ew = new QueryWrapper<XueshengEntity>();
        QueryWrapper<XueshengEntity> wrapper = MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, xuesheng), params), params);
        List<Map> list = xueshengService.list(wrapper).stream().map(v -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", v.getId());
            map.put("account", v.getXuehao());
            return map;
        }).collect(Collectors.toList());
        return R.ok().put("data", list);
    }

    // =====================================================================
    // 查询 / 分页
    // =====================================================================

    /** 后台列表 */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, XueshengEntity xuesheng, HttpServletRequest request) {
        QueryWrapper<XueshengEntity> ew = new QueryWrapper<XueshengEntity>();
        PageUtils page = xueshengService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, xuesheng), params), params));
        Map<String, String> deSens = new HashMap<>();
        DeSensUtil.desensitize(page, deSens);
        return R.ok().put("data", page);
    }

    /** 前台列表 */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, XueshengEntity xuesheng,
                  @RequestParam(required = false) Double nianlingstart,
                  @RequestParam(required = false) Double nianlingend,
                  @RequestParam(required = false) Double statusstart,
                  @RequestParam(required = false) Double statusend,
                  HttpServletRequest request) {
        QueryWrapper<XueshengEntity> ew = new QueryWrapper<XueshengEntity>();
        if (nianlingstart != null) ew.ge("nianling", nianlingstart);
        if (nianlingend != null) ew.le("nianling", nianlingend);
        if (statusstart != null) ew.ge("status", statusstart);
        if (statusend != null) ew.le("status", statusend);
        PageUtils page = xueshengService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, xuesheng), params), params));
        Map<String, String> deSens = new HashMap<>();
        DeSensUtil.desensitize(page, deSens);
        return R.ok().put("data", page);
    }

    /** lists */
    @RequestMapping("/lists")
    public R list(XueshengEntity xuesheng) {
        QueryWrapper<XueshengEntity> ew = new QueryWrapper<XueshengEntity>();
        ew.allEq(MPUtil.allEQMapPre(xuesheng, "xuesheng"));
        return R.ok().put("data", xueshengService.selectListView(ew));
    }

    /** query */
    @RequestMapping("/query")
    public R query(XueshengEntity xuesheng) {
        QueryWrapper<XueshengEntity> ew = new QueryWrapper<XueshengEntity>();
        ew.allEq(MPUtil.allEQMapPre(xuesheng, "xuesheng"));
        XueshengView xueshengView = xueshengService.selectView(ew);
        return R.ok("查询学生成功").put("data", xueshengView);
    }

    /** 后台详情 */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        XueshengEntity xuesheng = xueshengService.getById(id);
        Map<String, String> deSens = new HashMap<>();
        DeSensUtil.desensitize(xuesheng, deSens);
        return R.ok().put("data", xuesheng);
    }

    /** 前台详情 */
    @IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id) {
        XueshengEntity xuesheng = xueshengService.getById(id);
        Map<String, String> deSens = new HashMap<>();
        DeSensUtil.desensitize(xuesheng, deSens);
        return R.ok().put("data", xuesheng);
    }

    // =====================================================================
    // 新增 / 修改 / 删除
    // =====================================================================

    /** 后台保存 */
    @RequestMapping("/save")
    public R save(@RequestBody XueshengEntity xuesheng, HttpServletRequest request) {
        if (xueshengService.count(new QueryWrapper<XueshengEntity>().eq("xuehao", xuesheng.getXuehao())) > 0) {
            return R.error("学号已存在");
        }
        XueshengEntity u = xueshengService.getOne(new QueryWrapper<XueshengEntity>().eq("xuehao", xuesheng.getXuehao()));
        if (u != null) {
            return R.error("用户已存在");
        }
        xuesheng.setId(new Date().getTime());
        xueshengService.save(xuesheng);
        return R.ok().put("data", xuesheng.getId());
    }

    /** 前台保存 */
    @RequestMapping("/add")
    public R add(@RequestBody XueshengEntity xuesheng, HttpServletRequest request) {
        if (xueshengService.count(new QueryWrapper<XueshengEntity>().eq("xuehao", xuesheng.getXuehao())) > 0) {
            return R.error("学号已存在");
        }
        XueshengEntity u = xueshengService.getOne(new QueryWrapper<XueshengEntity>().eq("xuehao", xuesheng.getXuehao()));
        if (u != null) {
            return R.error("用户已存在");
        }
        xuesheng.setId(new Date().getTime());
        xueshengService.save(xuesheng);
        return R.ok().put("data", xuesheng.getId());
    }

    /** 获取用户密保 */
    @RequestMapping("/security")
    @IgnoreAuth
    public R security(@RequestParam String username) {
        XueshengEntity xuesheng = xueshengService.getOne(new QueryWrapper<XueshengEntity>().eq("xuehao", username));
        return R.ok().put("data", xuesheng);
    }

    /** 修改 */
    @RequestMapping("/update")
    @Transactional
    @IgnoreAuth
    public R update(@RequestBody XueshengEntity xuesheng, HttpServletRequest request) {
        if (xueshengService.count(new QueryWrapper<XueshengEntity>().ne("id", xuesheng.getId()).eq("xuehao", xuesheng.getXuehao())) > 0) {
            return R.error("学号已存在");
        }
        xueshengService.updateById(xuesheng);
        if (null != xuesheng.getXuehao()) {
            TokenEntity tokenEntity = new TokenEntity();
            tokenEntity.setUsername(xuesheng.getXuehao());
            tokenService.update(tokenEntity, new UpdateWrapper<TokenEntity>().eq("userid", xuesheng.getId()));
        }
        return R.ok();
    }

    /** 删除 */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids) {
        xueshengService.removeBatchByIds(Arrays.asList(ids));
        return R.ok();
    }

    // =====================================================================
    // ===== 新增：Excel 导入 / 模板下载 / 批量分班 =====
    // =====================================================================

    /**
     * 下载学生导入模板
     * 无需登录，直接触发文件下载
     */
    @IgnoreAuth
    @RequestMapping("/importTemplate")
    public void downloadTemplate(HttpServletResponse response) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("学生导入模板");

        // ---- 列标题行 ----
        Row headerRow = sheet.createRow(0);
        String[] headers = {
                "学号 *必填", "姓名 *必填", "性别", "年龄",
                "目标院校", "班级", "备考科目", "基础水平", "手机号"
        };
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            sheet.setColumnWidth(i, 5200);
        }

        // ---- 示例数据行 ----
        Row sampleRow = sheet.createRow(1);
        String[] samples = {
                "2024001", "张三", "男", "22",
                "北京大学", "考研数学A班", "数学,英语", "中级", "13800138000"
        };
        for (int i = 0; i < samples.length; i++) {
            sampleRow.createCell(i).setCellValue(samples[i]);
        }

        // ---- 说明行（空一行后写备注）----
        Row noteRow = sheet.createRow(3);
        noteRow.createCell(0).setCellValue(
                "说明：带 * 号为必填项；密码默认 123456；性别填'男'或'女'；年龄填整数；学号重复时该行自动跳过");

        // ---- 输出文件 ----
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        String encodedName = URLEncoder.encode("学生导入模板.xlsx", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-Disposition", "attachment;filename*=UTF-8''" + encodedName);
        workbook.write(response.getOutputStream());
        workbook.close();
    }

    /**
     * Excel 批量导入学生
     * 规则：
     *   1. 学号、姓名为必填，否则跳过并记录原因
     *   2. 学号重复（已存在于 DB）则跳过并记录原因
     *   3. 密码字段为空时默认写入 123456
     *   4. 返回成功条数、失败条数及失败详情列表
     */
    @RequestMapping("/importExcel")
    public R importExcel(@RequestParam("file") MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return R.error("请选择要导入的文件");
        }
        String filename = file.getOriginalFilename();
        if (filename == null
                || (!filename.toLowerCase().endsWith(".xls")
                && !filename.toLowerCase().endsWith(".xlsx"))) {
            return R.error("仅支持 .xls 或 .xlsx 格式文件");
        }

        int successCount = 0;
        int failCount = 0;
        List<String> failReasons = new ArrayList<>();

        try (InputStream is = file.getInputStream()) {
            Workbook workbook = filename.toLowerCase().endsWith(".xlsx")
                    ? new XSSFWorkbook(is)
                    : new HSSFWorkbook(is);
            Sheet sheet = workbook.getSheetAt(0);
            int lastRow = sheet.getLastRowNum();

            for (int rowIdx = 1; rowIdx <= lastRow; rowIdx++) {
                Row row = sheet.getRow(rowIdx);
                if (row == null) continue;

                String xuehao   = getCellStringValue(row.getCell(0));
                String xingming = getCellStringValue(row.getCell(1));

                // 跳过完全空行
                if (StringUtils.isBlank(xuehao) && StringUtils.isBlank(xingming)) continue;

                // 必填校验
                if (StringUtils.isBlank(xuehao)) {
                    failCount++;
                    failReasons.add("第 " + (rowIdx + 1) + " 行：学号不能为空");
                    continue;
                }
                if (StringUtils.isBlank(xingming)) {
                    failCount++;
                    failReasons.add("第 " + (rowIdx + 1) + " 行（学号: " + xuehao + "）：姓名不能为空");
                    continue;
                }

                // 学号去重
                if (xueshengService.count(new QueryWrapper<XueshengEntity>().eq("xuehao", xuehao)) > 0) {
                    failCount++;
                    failReasons.add("第 " + (rowIdx + 1) + " 行（学号: " + xuehao + "）：学号已存在，已跳过");
                    continue;
                }

                try {
                    XueshengEntity xuesheng = new XueshengEntity();
                    // 使用时间戳 + 行偏移保证 ID 不重复
                    xuesheng.setId(System.currentTimeMillis() * 100 + rowIdx);
                    xuesheng.setXuehao(xuehao);
                    xuesheng.setXingming(xingming);
                    xuesheng.setMima("123456");              // 默认密码
                    xuesheng.setStatus(0);

                    String xingbie = getCellStringValue(row.getCell(2));
                    if (StringUtils.isNotBlank(xingbie)) xuesheng.setXingbie(xingbie);

                    String nianlingStr = getCellStringValue(row.getCell(3));
                    if (StringUtils.isNotBlank(nianlingStr)) {
                        try { xuesheng.setNianling(Integer.parseInt(nianlingStr)); }
                        catch (NumberFormatException ignored) { /* 非数字则忽略 */ }
                    }

                    String mubiao = getCellStringValue(row.getCell(4));
                    if (StringUtils.isNotBlank(mubiao)) xuesheng.setMubiaoyuanxiao(mubiao);

                    String banji = getCellStringValue(row.getCell(5));
                    if (StringUtils.isNotBlank(banji)) xuesheng.setBanji(banji);

                    String beikao = getCellStringValue(row.getCell(6));
                    if (StringUtils.isNotBlank(beikao)) xuesheng.setBeikaokemu(beikao);

                    String jichu = getCellStringValue(row.getCell(7));
                    if (StringUtils.isNotBlank(jichu)) xuesheng.setJichushuiping(jichu);

                    String mobile = getCellStringValue(row.getCell(8));
                    if (StringUtils.isNotBlank(mobile)) xuesheng.setMobile(mobile);

                    xueshengService.save(xuesheng);
                    successCount++;

                } catch (Exception e) {
                    failCount++;
                    failReasons.add("第 " + (rowIdx + 1) + " 行（学号: " + xuehao + "）：保存失败 - " + e.getMessage());
                }
            }
            workbook.close();

        } catch (Exception e) {
            return R.error("文件解析失败，请检查文件格式：" + e.getMessage());
        }

        Map<String, Object> result = new HashMap<>();
        result.put("successCount", successCount);
        result.put("failCount", failCount);
        result.put("failReasons", failReasons);
        return R.ok().put("data", result);
    }

    /**
     * 批量分班
     * 说明：管理员在学生列表勾选学生后，统一更新 banji 字段。
     * banji 字段与课表信息（kebiaoxinix）关联，决定学生所属上课时间段和教师。
     * 参数：ids（学生ID列表），banji（班级名称）
     */
    @RequestMapping("/updateBanji")
    public R updateBanji(@RequestBody Map<String, Object> params) {
        Object idsObj = params.get("ids");
        String banji = (String) params.get("banji");

        if (idsObj == null) {
            return R.error("请选择需要分班的学生");
        }
        if (StringUtils.isBlank(banji)) {
            return R.error("班级名称不能为空");
        }

        // JSON 反序列化时数字默认为 Integer，需统一转 Long
        List<Long> ids = new ArrayList<>();
        if (idsObj instanceof List) {
            for (Object id : (List<?>) idsObj) {
                ids.add(Long.valueOf(id.toString()));
            }
        }
        if (ids.isEmpty()) {
            return R.error("请选择需要分班的学生");
        }

        xueshengService.update(
                new UpdateWrapper<XueshengEntity>()
                        .in("id", ids)
                        .set("banji", banji.trim())
        );
        return R.ok("分班成功，共更新 " + ids.size() + " 名学生到班级【" + banji.trim() + "】");
    }

    // =====================================================================
    // 内部工具方法
    // =====================================================================

    /**
     * 读取 Excel Cell 的字符串值，兼容文本/数值/公式/布尔类型
     */
    private String getCellStringValue(Cell cell) {
        if (cell == null) return "";
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue().trim();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) return "";
                double num = cell.getNumericCellValue();
                // 去掉 .0 后缀（如 22.0 → "22"）
                return (num == Math.floor(num) && !Double.isInfinite(num))
                        ? String.valueOf((long) num)
                        : String.valueOf(num);
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                try {
                    return cell.getStringCellValue().trim();
                } catch (Exception e) {
                    double n = cell.getNumericCellValue();
                    return (n == Math.floor(n)) ? String.valueOf((long) n) : String.valueOf(n);
                }
            default:
                return "";
        }
    }

}