<template>
  <div class="main-content" :style='{"padding":"30px"}'>
    <!-- 列表页 -->
    <template v-if="showFlag ">
      <el-form class="center-form-pv" :style='{"width":"100%","padding":"0","margin":"0 0 10px"}' :inline="true" :model="searchForm">
        <el-row :style='{"padding":"0px","boxShadow":"none","borderRadius":"10px","flexWrap":"wrap","display":"flex","width":"100%","position":"relative"}' >
          <div  :style='{"margin":"0 1% 10px 0","display":"flex"}'>
            <label :style='{"margin":"0 10px 0 0","whiteSpace":"nowrap","color":"#666","display":"inline-block","lineHeight":"40px","fontSize":"16px","fontWeight":"500","height":"40px"}' class="item-label">工号</label>
            <el-input v-model="searchForm.gonghao" placeholder="工号" @keydown.enter.native="search()" clearable></el-input>
          </div>
          <div  :style='{"margin":"0 1% 10px 0","display":"flex"}'>
            <label :style='{"margin":"0 10px 0 0","whiteSpace":"nowrap","color":"#666","display":"inline-block","lineHeight":"40px","fontSize":"16px","fontWeight":"500","height":"40px"}' class="item-label">教师姓名</label>
            <el-input v-model="searchForm.jiaoshixingming" placeholder="教师姓名" @keydown.enter.native="search()" clearable></el-input>
          </div>
          <div :style='{"margin":"0 1% 10px 0","display":"flex"}' class="select">
            <label :style='{"margin":"0 10px 0 0","whiteSpace":"nowrap","color":"#666","display":"inline-block","lineHeight":"40px","fontSize":"16px","fontWeight":"500","height":"40px"}' class="item-label">是否通过</label>
            <el-select clearable v-model="searchForm.sfsh" placeholder="是否通过">
              <el-option v-for="(item,index) in sfshOptions" v-bind:key="index" :label="item" :value="item"></el-option>
            </el-select>
          </div>
          <el-button class="search" type="success" @click="search()">
            <span class="icon iconfont icon-fangdajing12" :style='{"margin":"0 0px","fontSize":"18px","color":"#fff","height":"40px"}'></span>
            查询
          </el-button>
        </el-row>

        <el-row class="actions" :style='{"width":"calc(100% + 8px)","margin":"0px 0 10px -4px","flexWrap":"wrap","display":"flex","alignItems":"center"}'>
          <!-- 原有教师管理操作 -->
          <el-button class="add" v-if="isAuth('jiaoshi','新增')" type="success" @click="addOrUpdateHandler()">
            <span class="icon iconfont icon-tianjia16" :style='{"margin":"0 0px","fontSize":"16px","color":"#4f7df5","height":"auto"}'></span>
            添加
          </el-button>
          <el-button class="del" v-if="isAuth('jiaoshi','删除')" :disabled="dataListSelections.length?false:true" type="danger" @click="deleteHandler()">
            <span class="icon iconfont icon-shanchu6" :style='{"margin":"0 0px","fontSize":"16px","color":"#f30","height":"auto"}'></span>
            删除
          </el-button>
          <el-button class="btn18" v-if="isAuth('jiaoshi','审核')" :disabled="dataListSelections.length?false:true" type="success" @click="shBatchDialog()">
            <span class="icon iconfont icon-shenhe9" :style='{"margin":"0 0px","fontSize":"16px","color":"#333","height":"auto"}'></span>
            审核
          </el-button>

          <!-- 分隔 -->
          <span style="display:inline-block;width:1px;height:20px;background:#e0e6ff;margin:0 10px;vertical-align:middle;"></span>
          <span style="color:#aaa;font-size:12px;margin-right:6px;line-height:36px;">学生管理：</span>

          <!-- ===== 新增：导入学生 Excel ===== -->
          <el-button
              class="import-btn"
              type="success"
              @click="openImportDialog()">
            <span class="icon iconfont icon-daoru" :style='{"margin":"0 2px 0 0","fontSize":"15px","color":"#19be6b","height":"auto"}'></span>
            导入学生Excel
          </el-button>

          <!-- ===== 新增：下载模板 ===== -->
          <el-button
              class="template-btn"
              type="success"
              @click="downloadTemplate()">
            <span class="icon iconfont icon-xiazai1" :style='{"margin":"0 2px 0 0","fontSize":"15px","color":"#2d8cf0","height":"auto"}'></span>
            下载模板
          </el-button>

          <!-- ===== 新增：批量分班 ===== -->
          <el-button
              class="banji-btn"
              type="success"
              @click="openBanjiDialog()">
            <span class="icon iconfont icon-fenzu" :style='{"margin":"0 2px 0 0","fontSize":"15px","color":"#ff9900","height":"auto"}'></span>
            批量分班
          </el-button>
        </el-row>
      </el-form>

      <div :style='{"width":"100%","padding":"0","boxShadow":"none","borderRadius":"0"}'>
        <el-table class="tables"
                  :stripe='false'
                  :style='{"padding":"0","borderColor":"#e7e8fc","borderRadius":"10px 10px 0 0","borderWidth":"0px 0 0 0px","background":"#fff","width":"100%","borderStyle":"solid"}'
                  :border='false'
                  v-if="isAuth('jiaoshi','查看')"
                  :data="dataList"
                  v-loading="dataListLoading"
                  @selection-change="selectionChangeHandler">
          <el-table-column :resizable='true' type="selection" align="center" width="50"></el-table-column>
          <el-table-column :resizable='true' :sortable='true' label="序号" type="index" width="50" />
          <el-table-column :resizable='true' :sortable='true' prop="gonghao" label="工号">
            <template slot-scope="scope">{{scope.row.gonghao}}</template>
          </el-table-column>
          <el-table-column :resizable='true' :sortable='true' prop="jiaoshixingming" label="教师姓名">
            <template slot-scope="scope">{{scope.row.jiaoshixingming}}</template>
          </el-table-column>
          <el-table-column :resizable='true' prop="touxiang" width="200" label="头像">
            <template slot-scope="scope">
              <div v-if="scope.row.touxiang">
                <img v-if="scope.row.touxiang.substring(0,4)=='http'&&scope.row.touxiang.split(',w').length>1" :src="scope.row.touxiang" width="100" height="100" style="object-fit: cover" @click="imgPreView(scope.row.touxiang)">
                <img v-else-if="scope.row.touxiang.substring(0,4)=='http'" :src="scope.row.touxiang.split(',')[0]" width="100" height="100" style="object-fit: cover" @click="imgPreView(scope.row.touxiang.split(',')[0])">
                <img v-else :src="$base.url+scope.row.touxiang.split(',')[0]" width="100" height="100" style="object-fit: cover" @click="imgPreView($base.url+scope.row.touxiang.split(',')[0])">
              </div>
              <div v-else>无图片</div>
            </template>
          </el-table-column>
          <el-table-column :resizable='true' prop="zizhizhengming" label="资质证明">
            <template slot-scope="scope">
              <el-button v-if="scope.row.zizhizhengming" type="text" size="small" @click="download(scope.row.zizhizhengming)">下载</el-button>
              <span v-else>无</span>
            </template>
          </el-table-column>
          <el-table-column :resizable='true' :sortable='true' prop="xingbie" label="性别">
            <template slot-scope="scope">{{scope.row.xingbie}}</template>
          </el-table-column>
          <el-table-column :resizable='true' :sortable='true' prop="nianling" label="年龄">
            <template slot-scope="scope">{{scope.row.nianling}}</template>
          </el-table-column>
          <el-table-column :resizable='true' :sortable='true' prop="zhicheng" label="职称">
            <template slot-scope="scope">{{scope.row.zhicheng}}</template>
          </el-table-column>
          <el-table-column :resizable='true' :sortable='true' prop="shanzhangkemu" label="擅长科目">
            <template slot-scope="scope">{{scope.row.shanzhangkemu}}</template>
          </el-table-column>
          <el-table-column :resizable='true' :sortable='true' prop="jiaoxuefengge" label="教学风格">
            <template slot-scope="scope">{{scope.row.jiaoxuefengge}}</template>
          </el-table-column>
          <el-table-column :resizable='true' :sortable='true' prop="mobile" label="手机号">
            <template slot-scope="scope">{{scope.row.mobile}}</template>
          </el-table-column>
          <el-table-column :resizable='true' :sortable='true' prop="status" label="状态">
            <template slot-scope="scope">
              <el-switch
                  v-model="scope.row.status"
                  :width="36"
                  active-icon-class="el-icon-edit"
                  inactive-icon-class="el-icon-share"
                  active-text="开"
                  inactive-text="关"
                  :active-value="1"
                  :inactive-value="0"
                  active-color="#497afa"
                  inactive-color="#ccc"
                  @change="(e)=>jiaoshistatusChange(e,scope.row)">
              </el-switch>
            </template>
          </el-table-column>
          <el-table-column :resizable='true' :sortable='true' prop="shhf" label="审核回复" show-overflow-tooltip>
            <template slot-scope="scope">
              <div style="white-space: nowrap;">{{scope.row.shhf}}</div>
            </template>
          </el-table-column>
          <el-table-column :resizable='true' :sortable='true' prop="sfsh" label="审核状态">
            <template slot-scope="scope">
              <el-tag v-if="scope.row.sfsh=='否'" type="danger">未通过</el-tag>
              <el-tag v-if="scope.row.sfsh=='待审核'" type="warning">待审核</el-tag>
              <el-tag v-if="scope.row.sfsh=='是'" type="success">通过</el-tag>
            </template>
          </el-table-column>
          <el-table-column width="300" label="操作">
            <template slot-scope="scope">
              <el-button class="view" v-if="isAuth('jiaoshi','查看')" type="success" @click="addOrUpdateHandler(scope.row.id,'info')">
                <span class="icon iconfont icon-chakan13" :style='{"margin":"0 0px","fontSize":"14px","color":"#4f7df5","height":"40px"}'></span>
                查看
              </el-button>
              <el-button class="edit" v-if="isAuth('jiaoshi','修改') && scope.row.sfsh=='待审核'" type="success" @click="addOrUpdateHandler(scope.row.id)">
                <span class="icon iconfont icon-xiugai17" :style='{"margin":"0 0px","fontSize":"14px","color":"#38acde","height":"40px"}'></span>
                修改
              </el-button>
              <el-button class="del" v-if="isAuth('jiaoshi','删除')" type="primary" @click="deleteHandler(scope.row.id)">
                <span class="icon iconfont icon-shanchu6" :style='{"margin":"0 0px","fontSize":"14px","color":"#e56060","height":"40px"}'></span>
                删除
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
      <el-pagination
          @size-change="sizeChangeHandle"
          @current-change="currentChangeHandle"
          :current-page="pageIndex"
          background
          :page-sizes="[10, 50, 100, 200]"
          :page-size="pageSize"
          :layout="layouts.join()"
          :total="totalPage"
          prev-text="< "
          next-text="> "
          :hide-on-single-page="false"
          :style='{"padding":"15px 20px","margin":"0px 0 0","whiteSpace":"nowrap","color":"#333","borderRadius":"0 0 10px 10px","background":"#fff","display":"flex","width":"100%","fontWeight":"500","justifyContent":"center"}'
      ></el-pagination>
    </template>

    <!-- 添加/修改页面 -->
    <add-or-update v-if="addOrUpdateFlag" :parent="this" ref="addOrUpdate"></add-or-update>

    <!-- 审核弹窗（原有） -->
    <el-dialog :title="this.batchIds.length>1?'批量审核':'审核'" :visible.sync="sfshBatchVisiable" width="50%">
      <el-form ref="shBatchForm" :model="shBatchForm" :rules="shRules" label-width="80px">
        <el-form-item label="审核状态" prop="sfsh">
          <el-select v-model="shBatchForm.sfsh" placeholder="审核状态">
            <el-option label="通过" value="是"></el-option>
            <el-option label="不通过" value="否"></el-option>
            <el-option label="待审核" value="待审核"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="内容" prop="shhf">
          <el-input type="textarea" :rows="8" v-model="shBatchForm.shhf"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
				<el-button @click="sfshBatchVisiable=false">取 消</el-button>
				<el-button type="primary" @click="shBatchHandler">确 定</el-button>
			</span>
    </el-dialog>

    <!-- ===== 新增：导入学生 Excel 对话框 ===== -->
    <el-dialog
        title="导入学生 Excel"
        :visible.sync="importDialogVisible"
        width="560px"
        :close-on-click-modal="false"
        @close="resetImport()">

      <!-- 导入前 -->
      <div v-if="!importResult">
        <el-alert type="info" :closable="false" style="margin-bottom:16px;line-height:1.8;">
          <div style="font-size:13px;">
            <b>模板列顺序：</b>学号、姓名、性别、年龄、目标院校、班级、备考科目、基础水平、手机号<br>
            <b>规则：</b>
            <span style="color:#e56060;">学号、姓名为必填</span>；
            密码默认 <b>123456</b>；
            学号重复时自动跳过。<br>
            <span style="color:#2d8cf0;cursor:pointer;" @click="downloadTemplate()">
							📥 点此下载标准模板
						</span>
          </div>
        </el-alert>
        <el-upload
            class="import-upload"
            drag
            action=""
            :auto-upload="false"
            :on-change="handleImportFileChange"
            :on-remove="handleImportFileRemove"
            :before-upload="() => false"
            :limit="1"
            :on-exceed="() => $message.warning('只能选择一个文件，请先移除已选文件')"
            accept=".xls,.xlsx"
            :file-list="importFileList">
          <i class="el-icon-upload" style="font-size:50px;color:#497afa;margin-top:20px;"></i>
          <div class="el-upload__text" style="font-size:15px;margin:8px 0 4px;">
            将文件拖到此处，或<em>点击选择文件</em>
          </div>
          <div class="el-upload__tip" slot="tip" style="color:#999;text-align:center;margin-top:4px;">
            仅支持 <b>.xls</b> / <b>.xlsx</b> 格式，文件大小不超过 10MB
          </div>
        </el-upload>
      </div>

      <!-- 导入结果 -->
      <div v-else style="text-align:center;">
        <div style="font-size:48px;margin:12px 0 4px;">
          <span v-if="importResult.failCount===0" style="color:#19be6b;">✔</span>
          <span v-else style="color:#ff9900;">⚠</span>
        </div>
        <div style="font-size:18px;font-weight:bold;margin-bottom:6px;">导入完成</div>
        <div style="font-size:14px;color:#555;margin-bottom:12px;">
          成功导入 <b style="color:#19be6b;font-size:18px;">{{ importResult.successCount }}</b> 条，
          失败 <b style="color:#e56060;font-size:18px;">{{ importResult.failCount }}</b> 条
        </div>
        <div v-if="importResult.failReasons && importResult.failReasons.length"
             style="max-height:200px;overflow-y:auto;background:#fff6f6;border-radius:6px;padding:10px 14px;text-align:left;">
          <p v-for="(reason,idx) in importResult.failReasons" :key="idx"
             style="color:#e56060;font-size:13px;margin:4px 0;line-height:1.6;">
            {{ reason }}
          </p>
        </div>
      </div>

      <span slot="footer">
				<template v-if="!importResult">
					<el-button @click="importDialogVisible=false">取消</el-button>
					<el-button type="primary" :loading="importLoading" :disabled="!importFile" @click="doImport()">
						开始导入
					</el-button>
				</template>
				<template v-else>
					<el-button type="primary" @click="afterImport()">完成</el-button>
				</template>
			</span>
    </el-dialog>

    <!-- ===== 新增：批量分班对话框（内嵌学生搜索表格）===== -->
    <el-dialog
        title="批量分班"
        :visible.sync="banjiDialogVisible"
        width="740px"
        :close-on-click-modal="false"
        @open="onBanjiDialogOpen()">

      <!-- 学生搜索区 -->
      <div style="display:flex;align-items:center;margin-bottom:10px;flex-wrap:wrap;gap:8px;">
        <el-input
            v-model="banjiSearch.xuehao"
            placeholder="学号"
            clearable
            style="width:150px;"
            @keydown.enter.native="searchBanjiStudents()">
        </el-input>
        <el-input
            v-model="banjiSearch.xingming"
            placeholder="姓名"
            clearable
            style="width:150px;"
            @keydown.enter.native="searchBanjiStudents()">
        </el-input>
        <el-button type="primary" size="small" @click="searchBanjiStudents()">查询</el-button>
        <span style="color:#999;font-size:12px;margin-left:auto;">
					勾选学生后填写新班级名称，点击确认分班
				</span>
      </div>

      <!-- 学生列表 -->
      <el-table
          :data="banjiStudentList"
          v-loading="banjiStudentLoading"
          @selection-change="banjiStudentSelectionChange"
          ref="banjiStudentTable"
          max-height="220"
          style="width:100%;margin-bottom:8px;"
          size="small">
        <el-table-column type="selection" width="45" align="center"></el-table-column>
        <el-table-column prop="xuehao"      label="学号"   width="120"></el-table-column>
        <el-table-column prop="xingming"    label="姓名"   width="90"></el-table-column>
        <el-table-column prop="banji"       label="当前班级" min-width="120">
          <template slot-scope="scope">
            <span v-if="scope.row.banji" style="color:#497afa;">{{ scope.row.banji }}</span>
            <span v-else style="color:#ccc;">未分班</span>
          </template>
        </el-table-column>
        <el-table-column prop="beikaokemu"  label="备考科目" min-width="100"></el-table-column>
        <el-table-column prop="mobile"      label="手机号"  width="130"></el-table-column>
      </el-table>

      <!-- 学生分页 -->
      <el-pagination
          @current-change="banjiStudentPageChange"
          :current-page="banjiPage.pageIndex"
          :page-size="banjiPage.pageSize"
          layout="total, prev, pager, next"
          :total="banjiPage.total"
          small
          style="text-align:right;margin-bottom:14px;">
      </el-pagination>

      <!-- 已选提示 -->
      <el-alert
          v-if="banjiStudentSelections.length"
          type="success"
          :closable="false"
          style="margin-bottom:12px;">
				<span>
					已选 <b style="font-size:15px;">{{ banjiStudentSelections.length }}</b> 名学生：
					<el-tag v-for="(s,i) in banjiStudentSelections.slice(0,4)" :key="i" size="mini" style="margin:0 3px;">
						{{ s.xingming || s.xuehao }}
					</el-tag>
					<span v-if="banjiStudentSelections.length>4" style="color:#999;font-size:12px;">
						+{{ banjiStudentSelections.length-4 }} 人
					</span>
				</span>
      </el-alert>

      <!-- 班级输入 -->
      <el-form :model="banjiForm" ref="banjiFormRef" label-width="90px" size="small">
        <el-form-item
            label="新班级名"
            prop="banji"
            :rules="[{required:true, message:'班级名称不能为空', trigger:'blur'}]">
          <el-input
              v-model="banjiForm.banji"
              placeholder="例如：考研数学A班-周末班"
              clearable
              maxlength="50"
              show-word-limit>
          </el-input>
        </el-form-item>
      </el-form>

      <span slot="footer">
				<el-button @click="banjiDialogVisible=false">取消</el-button>
				<el-button
            type="primary"
            :loading="banjiLoading"
            :disabled="!banjiStudentSelections.length"
            @click="doBatchBanji()">
					确认分班（{{ banjiStudentSelections.length }}人）
				</el-button>
			</span>
    </el-dialog>

    <!-- 图片预览 -->
    <el-dialog title="预览图" :visible.sync="previewVisible" width="50%">
      <img :src="previewImg" alt="" style="width: 100%;">
    </el-dialog>
  </div>
</template>

<script>
import * as echarts from 'echarts'
import chinaJson from "@/components/echarts/china.json";
import axios from 'axios';
import AddOrUpdate from "./add-or-update";
import { Loading } from 'element-ui';

export default {
  data() {
    return {
      indexQueryCondition: '',
      searchForm: { key: "" },
      form: {},
      sfshOptions: [],
      dataList: [],
      pageIndex: 1,
      pageSize: 10,
      totalPage: 0,
      dataListLoading: false,
      dataListSelections: [],
      showFlag: true,
      sfshVisiable: false,
      shForm: {},
      sfshBatchVisiable: false,
      shBatchForm: { sfsh: '', shhf: '' },
      shRules: {
        sfsh: [{ required: true, message: '审核状态不能为空', trigger: 'blur' }],
      },
      batchIds: [],
      shList: [],
      addOrUpdateFlag: false,
      layouts: ["total", "prev", "pager", "next", "sizes", "jumper"],
      previewImg: '',
      previewVisible: false,

      // ===== 新增：导入学生相关状态 =====
      importDialogVisible: false,
      importFileList: [],
      importFile: null,
      importLoading: false,
      importResult: null,

      // ===== 新增：批量分班相关状态 =====
      banjiDialogVisible: false,
      banjiStudentList: [],
      banjiStudentSelections: [],
      banjiStudentLoading: false,
      banjiSearch: { xuehao: '', xingming: '' },
      banjiPage: { pageIndex: 1, pageSize: 6, total: 0 },
      banjiForm: { banji: '' },
      banjiLoading: false,
    };
  },
  created() {
    if (this.statType) return false;
    this.init();
    this.getDataList();
  },
  mounted() {},
  watch: {},
  filters: {
    htmlfilter: function (val) {
      return val.replace(/<[^>]*>/g).replace(/undefined/g, '');
    }
  },
  computed: {
    tablename() { return this.$storage.get('sessionTable') },
    role()      { return this.$storage.get('role') },
  },
  components: { AddOrUpdate },
  methods: {
    queryChange(arr) {
      for (let x in arr) { if (arr[x] == this.role) return true; }
      return false;
    },
    imgPreView(url) { this.previewImg = url; this.previewVisible = true; },
    init() { this.sfshOptions = "是,否,待审核".split(','); },
    search() { this.pageIndex = 1; this.getDataList(); },

    getDataList() {
      this.dataListLoading = true;
      let params = { page: this.pageIndex, limit: this.pageSize, sort: 'id', order: 'desc' };
      if (this.searchForm.gonghao)        params['gonghao']        = '%' + this.searchForm.gonghao + '%';
      if (this.searchForm.jiaoshixingming) params['jiaoshixingming'] = '%' + this.searchForm.jiaoshixingming + '%';
      if (this.searchForm.sfsh)            params['sfsh']           = this.searchForm.sfsh;
      this.$http({ url: "jiaoshi/page", method: "get", params }).then(({ data }) => {
        if (data && data.code === 0) {
          this.dataList  = data.data.list;
          this.totalPage = data.data.total;
        } else {
          this.dataList = []; this.totalPage = 0;
        }
        this.dataListLoading = false;
      });
    },
    sizeChangeHandle(val)    { this.pageSize = val; this.pageIndex = 1; this.getDataList(); },
    currentChangeHandle(val) { this.pageIndex = val; this.getDataList(); },
    selectionChangeHandler(val) { this.dataListSelections = val; },

    addOrUpdateHandler(id, type) {
      this.showFlag = false;
      this.addOrUpdateFlag = true;
      this.crossAddOrUpdateFlag = false;
      if (type != 'info' && type != 'msg') type = 'else';
      this.$nextTick(() => { this.$refs.addOrUpdate.init(id, type); });
    },

    shBatchDialog() {
      for (let x in this.dataListSelections) {
        if (this.dataListSelections[x].sfsh && this.dataListSelections[x].sfsh != '待审核') {
          this.$message.error('存在已审核数据，不能继续操作');
          this.batchIds = [];
          return false;
        }
        this.batchIds.push(this.dataListSelections[x].id);
      }
      this.shList = this.dataListSelections;
      this.sfshBatchVisiable = true;
    },

    shBatchHandler() {
      this.$refs["shBatchForm"].validate(valid => {
        if (valid) {
          this.$confirm(`是否${this.batchIds.length > 1 ? '一键审核' : '审核'}选中数据?`, "提示", {
            confirmButtonText: "确定", cancelButtonText: "取消", type: "warning"
          }).then(async () => {
            this.$http({
              url: "jiaoshi/shBatch?sfsh=" + this.shBatchForm.sfsh + "&shhf=" + this.shBatchForm.shhf,
              method: "post", data: this.batchIds
            }).then(async ({ data }) => {
              if (data && data.code === 0) {
                this.$message({ message: "操作成功", type: "success", duration: 1500,
                  onClose: () => { this.getDataList(); this.sfshBatchVisiable = false; this.batchIds = []; }
                });
              } else { this.$message.error(data.msg); }
            });
          });
        }
      });
    },

    download(file, type = 1) {
      let arr = file.replace(new RegExp('upload/', "g"), "");
      axios.get(this.$base.url + 'file/download?fileName=' + arr, {
        headers: { token: this.$storage.get('Token') }, responseType: "blob"
      }).then(({ data }) => {
        const objectUrl = window.URL.createObjectURL(new Blob([data], { type: 'application/pdf;chartset=UTF-8' }));
        const a = document.createElement('a');
        a.href = objectUrl; a.download = arr;
        a.dispatchEvent(new MouseEvent('click', { bubbles: true, cancelable: true, view: window }));
        window.URL.revokeObjectURL(data);
      }, err => {
        axios.get((location.href.split(this.$base.name).length > 1 ? location.href.split(this.$base.name)[0] : '') + this.$base.name + 'file/download?fileName=' + arr, {
          headers: { token: this.$storage.get('Token') }, responseType: "blob"
        }).then(({ data }) => {
          const objectUrl = window.URL.createObjectURL(new Blob([data], { type: 'application/pdf;chartset=UTF-8' }));
          const a = document.createElement('a');
          a.href = objectUrl; a.download = arr;
          a.dispatchEvent(new MouseEvent('click', { bubbles: true, cancelable: true, view: window }));
          window.URL.revokeObjectURL(data);
        });
      });
    },

    jiaoshistatusChange(e, row) {
      if (row.status == 0) row.passwordwrongnum = 0;
      this.$http({ url: 'jiaoshi/update', method: 'post', data: row }).then(res => {
        if (row.status == 1) this.$message.error('该用户已锁定');
        else this.$message.success('该用户已解除锁定');
      });
    },

    async deleteHandler(id) {
      var ids = id ? [Number(id)] : this.dataListSelections.map(item => Number(item.id));
      await this.$confirm(`确定进行[${id ? "删除" : "批量删除"}]操作?`, "提示", {
        confirmButtonText: "确定", cancelButtonText: "取消", type: "warning"
      }).then(async () => {
        await this.$http({ url: "jiaoshi/delete", method: "post", data: ids }).then(async ({ data }) => {
          if (data && data.code === 0) {
            this.$message({ message: "操作成功", type: "success", duration: 1500, onClose: () => { this.search(); } });
          } else { this.$message.error(data.msg); }
        });
      });
    },

    // =====================================================================
    // ===== 新增方法：导入学生 Excel =====
    // =====================================================================

    openImportDialog() {
      this.resetImport();
      this.importDialogVisible = true;
    },
    handleImportFileChange(file) {
      const ext = file.name.split('.').pop().toLowerCase();
      if (ext !== 'xls' && ext !== 'xlsx') {
        this.$message.error('仅支持 .xls 或 .xlsx 格式文件');
        this.importFileList = []; this.importFile = null;
        return false;
      }
      if (file.size > 10 * 1024 * 1024) {
        this.$message.error('文件大小不能超过 10MB');
        this.importFileList = []; this.importFile = null;
        return false;
      }
      this.importFile = file.raw;
      this.importFileList = [file];
    },
    handleImportFileRemove() {
      this.importFile = null; this.importFileList = [];
    },
    async doImport() {
      if (!this.importFile) { this.$message.error('请先选择要导入的文件'); return; }
      this.importLoading = true;
      const formData = new FormData();
      formData.append('file', this.importFile);
      try {
        const { data } = await this.$http({
          url: 'xuesheng/importExcel', method: 'post',
          data: formData, headers: { 'Content-Type': 'multipart/form-data' }
        });
        if (data && data.code === 0) { this.importResult = data.data; }
        else { this.$message.error(data.msg || '导入失败，请检查文件内容'); }
      } catch (e) {
        this.$message.error('请求失败，请稍后重试');
      } finally {
        this.importLoading = false;
      }
    },
    resetImport() {
      this.importFileList = []; this.importFile = null;
      this.importResult = null; this.importLoading = false;
    },
    afterImport() {
      this.importDialogVisible = false;
      this.resetImport();
    },
    downloadTemplate() {
      const baseURL = this.$http.defaults.baseURL || '';
      const link = document.createElement('a');
      link.href = baseURL + 'xuesheng/importTemplate';
      link.target = '_blank';
      document.body.appendChild(link);
      link.click();
      document.body.removeChild(link);
    },

    // =====================================================================
    // ===== 新增方法：批量分班（含学生搜索）=====
    // =====================================================================

    openBanjiDialog() {
      this.banjiForm.banji = '';
      this.banjiSearch = { xuehao: '', xingming: '' };
      this.banjiStudentSelections = [];
      this.banjiPage.pageIndex = 1;
      this.banjiDialogVisible = true;
    },

    /** el-dialog @open 时触发，确保 DOM 就绪再加载数据 */
    onBanjiDialogOpen() {
      this.loadBanjiStudents();
      this.$nextTick(() => {
        if (this.$refs.banjiFormRef) this.$refs.banjiFormRef.clearValidate();
      });
    },

    searchBanjiStudents() {
      this.banjiPage.pageIndex = 1;
      this.loadBanjiStudents();
    },

    banjiStudentPageChange(val) {
      this.banjiPage.pageIndex = val;
      this.loadBanjiStudents();
    },

    loadBanjiStudents() {
      this.banjiStudentLoading = true;
      const params = {
        page: this.banjiPage.pageIndex,
        limit: this.banjiPage.pageSize,
        sort: 'id', order: 'desc',
      };
      if (this.banjiSearch.xuehao)   params['xuehao']   = '%' + this.banjiSearch.xuehao   + '%';
      if (this.banjiSearch.xingming) params['xingming'] = '%' + this.banjiSearch.xingming + '%';
      this.$http({ url: 'xuesheng/page', method: 'get', params }).then(({ data }) => {
        if (data && data.code === 0) {
          this.banjiStudentList  = data.data.list;
          this.banjiPage.total   = data.data.total;
        } else {
          this.banjiStudentList = []; this.banjiPage.total = 0;
        }
        this.banjiStudentLoading = false;
      });
    },

    banjiStudentSelectionChange(val) {
      this.banjiStudentSelections = val;
    },

    doBatchBanji() {
      if (!this.banjiStudentSelections.length) {
        this.$message.warning('请先在列表中勾选需要分班的学生');
        return;
      }
      this.$refs.banjiFormRef.validate(async valid => {
        if (!valid) return;
        this.banjiLoading = true;
        const ids = this.banjiStudentSelections.map(s => s.id);
        try {
          const { data } = await this.$http({
            url: 'xuesheng/updateBanji', method: 'post',
            data: { ids, banji: this.banjiForm.banji }
          });
          if (data && data.code === 0) {
            this.$message.success(data.msg || '分班成功');
            this.banjiDialogVisible = false;
          } else {
            this.$message.error(data.msg || '分班失败');
          }
        } catch (e) {
          this.$message.error('请求失败，请稍后重试');
        } finally {
          this.banjiLoading = false;
        }
      });
    },
  }
};
</script>

<style lang="scss" scoped>
.center-form-pv {
  .el-date-editor.el-input { width: auto; }
}
.el-input { width: auto; }

.center-form-pv .el-input { width: 100%; }
.center-form-pv .el-input /deep/ .el-input__inner {
  border: 0px solid #ddd; border-radius: 6px; padding: 0 12px;
  box-shadow: inset 0px 2px 3px 0px #497afa30, inset 0px 0px 3px 0px #497afa50;
  outline: none; color: #333; width: 100%; font-size: 16px; height: 36px;
}
.center-form-pv .el-select { width: 100%; }
.center-form-pv .el-select /deep/ .el-input__inner {
  border: 0px solid #ddd; border-radius: 6px; padding: 0 10px;
  box-shadow: inset 0px 2px 3px 0px #497afa30, inset 0px 0px 3px 0px #497afa50;
  outline: none; color: #333; width: 100%; font-size: 16px; height: 36px;
}
.center-form-pv .el-date-editor { width: 100%; }
.center-form-pv .el-date-editor /deep/ .el-input__inner {
  border: 0px solid #ddd; border-radius: 6px; padding: 0 10px 0 30px;
  box-shadow: inset 0px 2px 3px 0px #497afa30, inset 0px 0px 3px 0px #497afa50;
  outline: none; color: #333; width: 100%; font-size: 16px; height: 36px;
}
.center-form-pv .search {
  border: 0; cursor: pointer; border-radius: 6px; padding: 0 12px 0 10px;
  outline: none; color: #fff; background: #497afa; width: auto; font-size: 16px; height: 36px;
}
.center-form-pv .search:hover { opacity: 0.8; }

// action buttons (all ghost-style)
.center-form-pv .actions .add,
.center-form-pv .actions .del,
.center-form-pv .actions .btn18,
.center-form-pv .actions .import-btn,
.center-form-pv .actions .template-btn,
.center-form-pv .actions .banji-btn {
  border: 0; cursor: pointer; border-radius: 6px; padding: 0;
  margin: 4px; outline: none; background: none; width: auto; font-size: 14px; height: 36px;
}
.center-form-pv .actions .add         { color: #4f7df5; }
.center-form-pv .actions .del         { color: #f30; }
.center-form-pv .actions .btn18       { color: #666; }
.center-form-pv .actions .import-btn   { color: #19be6b; }
.center-form-pv .actions .template-btn { color: #2d8cf0; }
.center-form-pv .actions .banji-btn    { color: #ff9900; }
.center-form-pv .actions .add:hover,
.center-form-pv .actions .del:hover,
.center-form-pv .actions .btn18:hover,
.center-form-pv .actions .import-btn:hover,
.center-form-pv .actions .template-btn:hover,
.center-form-pv .actions .banji-btn:hover { opacity: 0.8; }

// import upload area
.import-upload /deep/ .el-upload { width: 100%; }
.import-upload /deep/ .el-upload-dragger {
  width: 100%; height: 140px; border-radius: 8px;
  border: 2px dashed #497afa40; background: #f8f9ff;
  display: flex; flex-direction: column; align-items: center; justify-content: center;
  transition: border-color .2s;
}
.import-upload /deep/ .el-upload-dragger:hover { border-color: #497afa; }

// table
.el-table /deep/ .el-table__header-wrapper thead { color: #999; font-weight: 500; }
.el-table /deep/ .el-table__header-wrapper thead tr { background: #497afa; }
.el-table /deep/ .el-table__header-wrapper thead tr th {
  padding: 12px 0; background: none;
  border-color: #e0e6ff; border-width: 0 0 1px 0; border-style: solid; text-align: center;
}
.el-table /deep/ .el-table__header-wrapper thead tr th .cell {
  padding: 0 0 0 5px; color: #fff; font-weight: bold; display: flex;
  font-size: 14px; line-height: 24px; word-break: break-all; width: 100%;
  justify-content: flex-start; align-items: center; min-width: 80px;
}
.el-table /deep/ .el-table__body-wrapper tbody tr { background: #fff; }
.el-table /deep/ .el-table__body-wrapper tbody tr td {
  padding: 4px 0; color: #555; background: none;
  border-color: #e0e6ff; border-width: 0 0 1px 0; border-style: solid; text-align: left;
}
.el-table /deep/ .el-table__body-wrapper tbody tr:hover td { color: #000; background: #fff; }
.el-table /deep/ .el-table__body-wrapper tbody tr td .cell {
  padding: 0 0 0 5px; overflow: hidden; word-break: break-all; font-size: inherit; line-height: 24px;
}
.el-table /deep/ .el-table__body-wrapper tbody tr td .view  { border:0;cursor:pointer;border-radius:6px;padding:0;margin:0 5px 5px 0;outline:none;color:#4f7df5;background:none;font-size:14px;height:32px;order:3; }
.el-table /deep/ .el-table__body-wrapper tbody tr td .edit  { border:0;cursor:pointer;border-radius:6px;padding:0;margin:0 5px 5px 0;outline:none;color:#38acde;background:none;font-size:14px;height:32px;order:-1; }
.el-table /deep/ .el-table__body-wrapper tbody tr td .del   { border:0;cursor:pointer;border-radius:6px;padding:0;margin:0 5px 5px 0;outline:none;color:#e56060;background:none;font-size:14px;height:32px; }
.el-table /deep/ .el-table__body-wrapper tbody tr td .view:hover,
.el-table /deep/ .el-table__body-wrapper tbody tr td .edit:hover,
.el-table /deep/ .el-table__body-wrapper tbody tr td .del:hover { opacity: 0.8; }

// pagination
.main-content .el-pagination /deep/ .el-pagination__total { margin:0 auto 0 0;color:#666;font-weight:400;display:inline-block;vertical-align:top;font-size:15px;line-height:28px;height:28px; }
.main-content .el-pagination /deep/ .btn-prev,
.main-content .el-pagination /deep/ .btn-next { border:none;border-radius:2px;padding:0;margin:0 5px;color:#666;background:#fff;display:inline-block;vertical-align:top;font-size:15px;line-height:28px;min-width:35px;height:28px; }
.main-content .el-pagination /deep/ .btn-prev:disabled,
.main-content .el-pagination /deep/ .btn-next:disabled { cursor:not-allowed;background:#eee; }
.main-content .el-pagination /deep/ .el-pager { padding:0;margin:0;display:inline-block;vertical-align:top; }
.main-content .el-pagination /deep/ .el-pager .number { cursor:pointer;padding:0 4px;margin:0 5px;color:#666;display:inline-block;vertical-align:top;font-size:15px;line-height:28px;border-radius:2px;background:#fff;text-align:center;min-width:30px;height:28px; }
.main-content .el-pagination /deep/ .el-pager .number:hover,
.main-content .el-pagination /deep/ .el-pager .number.active { color:#fff;background:#497afa; }
.main-content .el-pagination /deep/ .el-pagination__sizes { display:inline-block;vertical-align:top;font-size:15px;line-height:28px;height:28px; }
.main-content .el-pagination /deep/ .el-pagination__sizes .el-input { margin:0 5px;width:100px;position:relative; }
.main-content .el-pagination /deep/ .el-pagination__sizes .el-input .el-input__inner { border:0px solid #DCDFE6;cursor:pointer;padding:0 25px 0 8px;color:#606266;font-size:15px;line-height:28px;border-radius:0;outline:0;background:#FFF;width:100%;border-width:0 0 1px;text-align:center;height:28px; }
.main-content .el-pagination /deep/ .el-pagination__jump { margin:0 0 0 24px;color:#606266;display:inline-block;vertical-align:top;font-size:15px;line-height:28px;height:28px; }
.main-content .el-pagination /deep/ .el-pagination__jump .el-input { border-radius:3px;padding:0 2px;margin:0 2px;display:inline-block;width:50px;font-size:15px;position:relative;text-align:center;height:28px; }
.main-content .el-pagination /deep/ .el-pagination__jump .el-input .el-input__inner { border:0px solid #DCDFE6;cursor:pointer;padding:0 3px;color:#606266;font-size:15px;line-height:28px;border-radius:0;outline:0;background:#FFF;width:100%;border-width:0 0 1px;text-align:center;height:28px; }

.main-content .el-table .el-switch { display:inline-flex;vertical-align:middle;line-height:30px;position:relative;align-items:center;height:30px; }
.main-content .el-table .el-switch /deep/ .el-switch__core { border:1px solid #ff8b00;cursor:pointer;border-radius:15px;margin:0;outline:0;background:#ff8b00;display:inline-block;width:36px;box-sizing:border-box;transition:border-color .3s,background-color .3s;height:18px; }
.main-content .el-table .el-switch /deep/ .el-switch__core::after { border-radius:100%;top:1px;left:2px;background:#FFF;width:14px;position:absolute;transition:all .3s;height:14px; }
.main-content .el-table .el-switch.is-checked /deep/ .el-switch__core::after { margin:0 0 0 -16px;left:100%; }
.chartDialog /deep/ .el-dialog { background: #fff; }
</style>