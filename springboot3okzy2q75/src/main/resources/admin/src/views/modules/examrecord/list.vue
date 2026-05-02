<template>
	<div class="main-content" :style='{"padding":"30px"}'>
		<!-- 列表页 -->
		<template v-if="!showFlag">
			<el-form :style='{"width":"100%","padding":"0","margin":"0 0 10px"}' :inline="true" :model="searchForm" class="center-form-pv">
				<el-row :style='{"padding":"0px","boxShadow":"none","borderRadius":"10px","flexWrap":"wrap","display":"flex","width":"100%","position":"relative"}'>
					<div :style='{"margin":"0 1% 10px 0","display":"flex"}'>
						<label :style='{"margin":"0 10px 0 0","whiteSpace":"nowrap","color":"#666","display":"inline-block","lineHeight":"40px","fontSize":"16px","fontWeight":"500","height":"40px"}' class="item-label">试卷名称</label>
						<el-input v-model="searchForm.papername" placeholder="试卷名称" clearable></el-input>
					</div>
					<el-button class="search" :style='{"border":"0","cursor":"pointer","padding":"0 12px 0 10px","outline":"none","color":"#fff","borderRadius":"6px","background":"#497afa","width":"auto","fontSize":"16px","height":"36px"}' type="success" @click="search()">
						<span class="icon iconfont icon-fangdajing12" :style='{"margin":"0 0px","fontSize":"18px","color":"#fff","height":"40px"}'></span>
						查询
					</el-button>
				</el-row>
				<el-row class="actions" :style='{"width":"calc(100% + 8px)","margin":"0px 0 10px -4px","flexWrap":"wrap","display":"flex"}'>
					<download-excel v-if="isAuth('examrecord','导出')" class = "export-excel-wrapper" :data = "dataList" :fields = "json_fields" name = "考试记录.xls">
						<!-- 导出excel -->
						<el-button class="btn18" type="success">
							<span class="icon iconfont icon-xiazai7" :style='{"margin":"0 0px","fontSize":"16px","color":"#333","height":"auto"}'></span>
							导出
						</el-button>
					</download-excel>
					<el-button class="btn18" v-if="isAuth('examrecord','打印')" type="success" @click="printJson">
						<span class="icon iconfont icon-dayin2" :style='{"margin":"0 0px","fontSize":"16px","color":"#333","height":"auto"}'></span>
						打印
					</el-button>
				</el-row>
			</el-form>

			<div :style='{"width":"100%","padding":"0","boxShadow":"none","borderRadius":"0"}'>
				<el-table
					:stripe='false'
					:style='{"padding":"0","borderColor":"#e7e8fc","borderRadius":"10px 10px 0 0","borderWidth":"0px 0 0 0px","background":"#fff","width":"100%","borderStyle":"solid"}'
					:data="dataList"
					:border='false'
					v-loading="dataListLoading"
					@selection-change="selectionChangeHandler"
					style="width: 100%;"
				>
					<el-table-column :resizable='true' :sortable='true' prop="examno" header-align="center" align="center" sortable label="考试编号"></el-table-column>
					<el-table-column :resizable='true' :sortable='true' prop="username" header-align="center" align="center" sortable label="姓名"></el-table-column>
					<el-table-column
						:resizable='true' :sortable='true'
						prop="papername"
						header-align="center"
						align="center"
						sortable
						label="试卷名称"
					></el-table-column>
					<el-table-column
						:resizable='true' :sortable='true'
						prop="myscore"
						header-align="center"
						align="center"
						sortable
						label="测试得分"
					>
						<template slot-scope="scope">
							<el-tag v-if="scope.row.myscore==0&&scope.row.ismark==0" type="danger">{{scope.row.myscore}}</el-tag>
							<el-tag v-else-if="scope.row.myscore>0&&scope.row.ismark==0" type="success">{{scope.row.myscore}}</el-tag>
							<el-tag v-else-if="scope.row.ismark>0" type="warning">批阅中</el-tag>
						</template>
					</el-table-column>
					<el-table-column
						:resizable='true' :sortable='true'
						prop="accuracy"
						header-align="center"
						align="center"
						sortable
						label="准确率"
					>
						<template slot-scope="scope">
							<el-tag type="success" v-if="scope.row.ismark==0">{{(scope.row.accuracy * 100).toFixed(0)}}%</el-tag>
							<el-tag type="warning" v-if="scope.row.ismark>0">/</el-tag>
						</template>
					</el-table-column>
					<el-table-column
						:resizable='true' :sortable='true'
						prop="accuracy"
						header-align="center"
						align="center"
						sortable
						label="错误率"
					>
						<template slot-scope="scope">
							<el-tag type="danger" v-if="scope.row.ismark==0">{{((1 - Number(scope.row.accuracy)) * 100).toFixed(0)}}%</el-tag>
							<el-tag type="warning" v-if="scope.row.ismark>0">/</el-tag>
						</template>
					</el-table-column>
					<el-table-column
						header-align="center"
						align="center"
						width="150"
						label="操作"
					>
						<template slot-scope="scope">
							<el-button class="view" :style='{"border":"0","cursor":"pointer","padding":"0 0px","margin":"0 5px 5px 0","outline":"none","color":"#4f7df5","borderRadius":"6px","background":"none","width":"auto","fontSize":"14px","height":"32px","order":"3"}' type="success" @click="addOrUpdateHandler(scope.row)">
								<span class="icon iconfont icon-chakan13" :style='{"margin":"0 0px","fontSize":"14px","color":"#4f7df5","height":"40px"}'></span>
								查看
							</el-button>
							<el-button class="del" :style='{"border":"0","cursor":"pointer","padding":"0 0px","margin":"0 5px 5px 0","outline":"none","color":"#e56060","borderRadius":"6px","background":"none","width":"auto","fontSize":"14px","height":"32px"}' v-if="isAuth('examrecord','删除')" type="success" @click="deleteHandler(scope.row)">
								<span class="icon iconfont icon-shanchu6" :style='{"margin":"0 0px","fontSize":"14px","color":"#e56060","height":"40px"}'></span>
								删除
							</el-button>
							<el-button class="edit" :style='{"border":"0","cursor":"pointer","padding":"0 0px","margin":"0 5px 5px 0","outline":"none","color":"#38acde","borderRadius":"6px","background":"none","width":"auto","fontSize":"14px","height":"32px","order":"-1"}' v-if="isAuth('examrecord','批卷')&&scope.row.ismark>0" type="primary" @click="gradeClick(scope.row)">
								<span class="icon iconfont icon-xiugai17" :style='{"margin":"0 0px","fontSize":"14px","color":"#38acde","height":"40px"}'></span>
								批卷
							</el-button>
						</template>
					</el-table-column>
				</el-table>
			</div>
			<el-pagination
				@size-change="sizeChangeHandle"
				@current-change="currentChangeHandle"
				:current-page="pageIndex"
				:page-sizes="[10, 50, 100, 200]"
				:page-size="pageSize"
				:total="totalPage"
				:layout="layouts.join()"
				prev-text="<"
				next-text=">"
				:hide-on-single-page="false"
				:style='{"padding":"15px 20px","margin":"0px 0 0","whiteSpace":"nowrap","color":"#333","borderRadius":"0 0 10px 10px","background":"#fff","display":"flex","width":"100%","fontWeight":"500","justifyContent":"center"}'
			></el-pagination>
		</template>
		<add-or-update v-if="showFlag" :parent="this" ref="addOrUpdate"></add-or-update>
		<el-dialog title="批卷" :visible.sync="gradeVisible" fullscreen>
			<el-card v-for="(item,index) in gradeList" :key="index" style="width: 90%;margin: 10px auto">
				<div style="padding: 20px;box-sizing:border-box;border-bottom:1px solid #eee">
					{{index + 1}}、<span class="ql-snow ql-editor" v-html="item.questionname"></span> （ {{item.score}} ）					<el-tag type="success" v-if="item.type==0">单选题</el-tag>
					<el-tag type="warning" v-if="item.type==1">多选题</el-tag>
					<el-tag type="info" v-if="item.type==2">判断题</el-tag>
					<el-tag type="primary" v-if="item.type==3">填空题</el-tag>
					<el-tag type="danger" v-if="item.type==4">主观题</el-tag>
				</div>
				<div style="padding: 10px;box-sizing:border-box">
					考生答案：{{item.myanswer}}
				</div>
				<div style="padding: 10px;box-sizing:border-box" v-if="item.type!=4">
					正确答案：{{item.answer}}
				</div>
				<div style="padding: 20px;box-sizing:border-box">
					解析：{{item.analysis}}
				</div>
				<div style="padding: 20px;box-sizing:border-box;display:flex;align-items:center" v-if="item.type==4">
					评分：<el-input-number v-model="item.myscore" :min="0" :max="item.score"></el-input-number>
				</div>
			</el-card>
			<span slot="footer" class="dialog-footer">
				<el-button @click="gradeVisible=false">取 消</el-button>
				<el-button type="primary" @click="gradeSave">确 定</el-button>
			</span>
		</el-dialog>
	</div>
</template>
<script>
	import AddOrUpdate from "./add-or-update";
	export default {
		data() {
			return {
				layouts: ["total","prev","pager","next","sizes","jumper"],
				searchForm: {
					key: ""
				},
				dataList: [],
				pageIndex: 1,
				pageSize: 10,
				totalPage: 0,
				dataListLoading: false,
				dataListSelections: [],
				showFlag: false,
				//导出excel
				json_fields: {
					"姓名": "username",    //常规字段
					"试卷名称": "papername",    //常规字段
					"总分": "myscore",    //常规字段
				},
				json_meta: [
					[
						{
							" key ": " charset ",
							" value ": " utf- 8 "
						}
					]
				],
				gradeList:[],
				gradeVisible:false
			};
		},
		mounted() {
			this.init();
			this.getDataList();
		},
		components: {
			AddOrUpdate
		},
		methods: {
			init() {},
			search() {
				this.pageIndex = 1;
				this.getDataList();
			},
			// 获取数据列表
			getDataList() {
				this.dataListLoading = true;
				var params = {
					page: this.pageIndex,
					limit: this.pageSize
				};
				if (this.searchForm.papername) {
					params["papername"] = `%${this.searchForm.papername}%`;
				}
			  this.$http({
					url: this.$api.examrecordgroupby,
					method: "get",
					params: params
				}).then(({ data }) => {
					if (data && data.code === 0) {
						this.dataList = data.data.list;
						this.totalPage = data.data.total;
					} else {
						this.dataList = [];
						this.totalPage = 0;
					}
					this.dataListLoading = false;
				});
			},
			// 每页数
			sizeChangeHandle(val) {
				this.pageSize = val;
				this.pageIndex = 1;
				this.getDataList();
			},
			// 当前页
			currentChangeHandle(val) {
				this.pageIndex = val;
				this.getDataList();
			},
			// 多选
			selectionChangeHandler(val) {
				this.dataListSelections = val;
			},
			addOrUpdateHandler(row) {
				this.showFlag = true;
				this.$nextTick(() => {
					this.$refs.addOrUpdate.init(row);
				});
			},
			async printJson() {
				//通过getdata调用后台接口获取数据封装到res
				this.list = this.dataList;
				let data = []
				for (let i=0; i < this.list.length; i++) {
					data.push({
						username: this.list[i].username,
						papername: this.list[i].papername,
						myscore: this.list[i].myscore,
					})
				}
				printJS({
					printable: data,
					properties: [
						{
							field: 'username',
							displayName: '姓名',
							columnSize: 1
						},
						{
							field: 'papername',
							displayName: '试卷名称',
							columnSize: 1
						},
						{
							field: 'myscore',
							displayName: '总分',
							columnSize: 1
						},
					],
					type: 'json',
					header: '测试记录',
					// 样式设置
					gridStyle: 'border: 2px solid #3971A5;',
					gridHeaderStyle: 'color: red;  border: 2px solid #3971A5;'
				})
			},
			// 批卷
			gradeClick(row) {
				this.$http({
					url: `${this.$api.examrecordpage}`,
					method: 'get',
					params: {
						page:1,
						limit: 100,
						paperid: row.paperid,
						userid: row.userid,
						sort: 'addtime',
						order: 'asc',
						examno: row.examno
					}
				}).then(({data})=>{
					if(data&&data.code==0){
						for(let x in data.data.list){
							if(data.data.list[x].type==4){
								data.data.list[x].myscore = 0
							}
							data.data.list[x].questionname = data.data.list[x].questionname.replace(/img src/gi,"img style=\"width:100%;\" src");
						}
						this.gradeList = data.data.list
						this.gradeVisible = true
					}
				})
			},
			async gradeSave(){
				for(let i in this.gradeList){
					await this.updaterecord(this.gradeList[i])
				}
				this.$message({
					message: "批卷成功",
					type: "success",
					duration: 1500,
					onClose: () => {
						this.getDataList()
						this.gradeVisible = false
					}
				});
			},
			updaterecord(item){
				item.ismark = 1
				this.$http({
					url: `${this.$api.examrecordupdate}`,
					method: 'post',
					data: item
				}).then(({data})=>{})
			},
			deleteHandler(row){
				this.$confirm(`确定删除此考试记录？`, "提示", {
					confirmButtonText: "确定",
					cancelButtonText: "取消",
					type: "warning"
				}).then(()=>{
					this.$http({
						url: `examrecord/deleteRecords?userid=${row.userid}&paperid=${row.paperid}&examno=${row.examno}`,
						method: "post",
						data: {}
					}).then(({ data }) => {
						this.$message.success('删除成功')
						this.getDataList()
					});
				})
			},
		}
	};
</script>
<style lang="scss" scoped>
	//导出excel
	.export-excel-wrapper{
		display: inline-block;
	}
	.form-content {
		background: transparent;
	}
	.table-content {
		background: transparent;
	}
	// form
	.center-form-pv .el-input {
				width: 100%;
			}
	
	.center-form-pv .el-input /deep/ .el-input__inner {
				border: 0px solid #ddd;
				border-radius: 6px;
				padding: 0 12px;
				box-shadow: inset 0px 2px 3px 0px  #497afa30,inset 0px 0px 3px 0px  #497afa50;
				outline: none;
				color: #333;
				width: 100%;
				font-size: 16px;
				height: 36px;
			}
	
	
	// table
	.el-table /deep/ .el-table__header-wrapper thead {
				color: #999;
				font-weight: 500;
				width: 100%;
			}
	
	.el-table /deep/ .el-table__header-wrapper thead tr {
				background: #497afa;
			}
	
	.el-table /deep/ .el-table__header-wrapper thead tr th {
				padding: 12px 0;
				background: none;
				border-color: #e0e6ff;
				border-width: 0 0px 1px 0;
				border-style: solid;
				text-align: center;
			}
	
	.el-table /deep/ .el-table__header-wrapper thead tr th .cell {
				padding: 0 0 0 5px;
				word-wrap: normal;
				color: #fff;
				white-space: normal;
				font-weight: bold;
				display: flex;
				vertical-align: middle;
				font-size: 14px;
				line-height: 24px;
				text-overflow: ellipsis;
				word-break: break-all;
				width: 100%;
				justify-content: flex-start;
				align-items: center;
				position: relative;
				min-width: 110px;
			}
	
	
	.el-table /deep/ .el-table__body-wrapper {
				position: relative;
			}
	.el-table /deep/ .el-table__body-wrapper tbody {
				width: 100%;
			}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr {
				background: #fff;
			}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td {
				padding: 4px 0;
				color: #555;
				background: none;
				border-color: #e0e6ff;
				border-width: 0 0px 1px 0;
				border-style: solid;
				text-align: left;
			}
	
		
	.el-table /deep/ .el-table__body-wrapper tbody tr:hover td {
				padding: 4px 0;
				color: #000;
				background: #fff;
				border-color: #e0e6ff;
				border-width: 0 0px 1px 0;
				border-style: solid;
				text-align: left;
			}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td {
				padding: 4px 0;
				color: #555;
				background: none;
				border-color: #e0e6ff;
				border-width: 0 0px 1px 0;
				border-style: solid;
				text-align: left;
			}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .cell {
				padding: 0 0 0 5px;
				overflow: hidden;
				word-break: break-all;
				white-space: normal;
				font-size: inherit;
				line-height: 24px;
				text-overflow: ellipsis;
			}
	
	// pagination
	.main-content .el-pagination /deep/ .el-pagination__total {
				margin: 0 auto 0 0;
				color: #666;
				font-weight: 400;
				display: inline-block;
				vertical-align: top;
				font-size: 15px;
				line-height: 28px;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .btn-prev {
				border: none;
				border-radius: 2px;
				padding: 0;
				margin: 0 5px;
				color: #666;
				background: #fff;
				display: inline-block;
				vertical-align: top;
				font-size: 15px;
				line-height: 28px;
				min-width: 35px;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .btn-next {
				border: none;
				border-radius: 2px;
				padding: 0;
				margin: 0 5px;
				color: #666;
				background: #fff;
				display: inline-block;
				vertical-align: top;
				font-size: 15px;
				line-height: 28px;
				min-width: 35px;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .btn-prev:disabled {
				border: none;
				cursor: not-allowed;
				border-radius: 2px;
				padding: 0;
				margin: 0 5px;
				color: #666;
				background: #eee;
				display: inline-block;
				vertical-align: top;
				font-size: 15px;
				line-height: 28px;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .btn-next:disabled {
				border: none;
				cursor: not-allowed;
				border-radius: 2px;
				padding: 0;
				margin: 0 5px;
				color: #666;
				background: #eee;
				display: inline-block;
				vertical-align: top;
				font-size: 15px;
				line-height: 28px;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .el-pager {
				padding: 0;
				margin: 0;
				display: inline-block;
				vertical-align: top;
			}
	
	.main-content .el-pagination /deep/ .el-pager .number {
				cursor: pointer;
				padding: 0 4px;
				margin: 0 5px;
				color: #666;
				display: inline-block;
				vertical-align: top;
				font-size: 15px;
				line-height: 28px;
				border-radius: 2px;
				background: #fff;
				text-align: center;
				min-width: 30px;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .el-pager .number:hover {
				cursor: pointer;
				padding: 0 4px;
				margin: 0 5px;
				color: #fff;
				display: inline-block;
				vertical-align: top;
				font-size: 15px;
				line-height: 28px;
				border-radius: 2px;
				background: #497afa;
				text-align: center;
				min-width: 30px;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .el-pager .number.active {
				cursor: default;
				padding: 0 4px;
				margin: 0 5px;
				color: #fff;
				display: inline-block;
				vertical-align: top;
				font-size: 15px;
				line-height: 28px;
				border-radius: 2px;
				background: #497afa;
				text-align: center;
				min-width: 30px;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .el-pagination__sizes {
				display: inline-block;
				vertical-align: top;
				font-size: 15px;
				line-height: 28px;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .el-pagination__sizes .el-input {
				margin: 0 5px;
				width: 100px;
				position: relative;
			}
	
	.main-content .el-pagination /deep/ .el-pagination__sizes .el-input .el-input__inner {
				border: 0px solid #DCDFE6;
				cursor: pointer;
				padding: 0 25px 0 8px;
				color: #606266;
				display: inline-block;
				font-size: 15px;
				line-height: 28px;
				border-radius: 0px;
				outline: 0;
				background: #FFF;
				width: 100%;
				border-width: 0 0 1px;
				text-align: center;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .el-pagination__sizes .el-input span.el-input__suffix {
				top: 0;
				position: absolute;
				right: 0;
				height: 100%;
			}
	
	.main-content .el-pagination /deep/ .el-pagination__sizes .el-input .el-input__suffix .el-select__caret {
				cursor: pointer;
				color: #C0C4CC;
				width: 25px;
				font-size: 15px;
				line-height: 28px;
				text-align: center;
			}
	
	.main-content .el-pagination /deep/ .el-pagination__jump {
				margin: 0 0 0 24px;
				color: #606266;
				display: inline-block;
				vertical-align: top;
				font-size: 15px;
				line-height: 28px;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .el-pagination__jump .el-input {
				border-radius: 3px;
				padding: 0 2px;
				margin: 0 2px;
				display: inline-block;
				width: 50px;
				font-size: 15px;
				line-height: 18px;
				position: relative;
				text-align: center;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .el-pagination__jump .el-input .el-input__inner {
				border: 0px solid #DCDFE6;
				cursor: pointer;
				padding: 0 3px;
				color: #606266;
				display: inline-block;
				font-size: 15px;
				line-height: 28px;
				border-radius: 0px;
				outline: 0;
				background: #FFF;
				width: 100%;
				border-width: 0 0 1px;
				text-align: center;
				height: 28px;
			}
	
	.center-form-pv .search {
				border: 0;
				cursor: pointer;
				border-radius: 6px;
				padding: 0 12px 0 10px;
				outline: none;
				color: #fff;
				background: #497afa;
				width: auto;
				font-size: 16px;
				height: 36px;
			}
	
	.center-form-pv .search:hover {
				opacity: 0.8;
			}
	
	.center-form-pv .actions .btn18 {
				border: 0;
				cursor: pointer;
				border-radius: 6px;
				padding: 0 0px;
				margin: 4px;
				outline: none;
				color: #666;
				background: none;
				width: auto;
				font-size: 14px;
				height: 36px;
			}
	
	.center-form-pv .actions .btn18:hover {
				opacity: 0.8;
			}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .view {
				border: 0;
				cursor: pointer;
				border-radius: 6px;
				padding: 0 0px;
				margin: 0 5px 5px 0;
				outline: none;
				color: #4f7df5;
				background: none;
				width: auto;
				font-size: 14px;
				height: 32px;
				order: 3;
			}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .view:hover {
				opacity: 0.8;
			}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .edit {
				border: 0;
				cursor: pointer;
				border-radius: 6px;
				padding: 0 0px;
				margin: 0 5px 5px 0;
				outline: none;
				color: #38acde;
				background: none;
				width: auto;
				font-size: 14px;
				height: 32px;
				order: -1;
			}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .edit:hover {
				opacity: 0.8;
			}
	// list one
	.one .list1-view {
				border: 0;
				cursor: pointer;
				border-radius: 4px;
				padding: 0 10px;
				margin: 0 5px 5px 0;
				outline: none;
				color: #fff;
				background: #6d96ff;
				width: auto;
				font-size: 14px;
				height: 32px;
				order: 3;
			}
	
	.one .list1-view:hover {
				opacity: 0.8;
			}
</style>
