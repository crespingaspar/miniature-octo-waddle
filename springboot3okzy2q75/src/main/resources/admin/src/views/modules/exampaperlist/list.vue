<template>
	<div class="main-content" :style='{"padding":"30px"}'>
		<!-- 列表页 -->
		<template v-if="!showFlag">
			<el-form :style='{"width":"100%","padding":"0","margin":"0 0 10px"}' :inline="true" :model="searchForm" class="center-form-pv">
				<el-row :style='{"padding":"0px","boxShadow":"none","borderRadius":"10px","flexWrap":"wrap","display":"flex","width":"100%","position":"relative"}'>
					<div :style='{"margin":"0 1% 10px 0","display":"flex"}'>
						<label :style='{"margin":"0 10px 0 0","whiteSpace":"nowrap","color":"#666","display":"inline-block","lineHeight":"40px","fontSize":"16px","fontWeight":"500","height":"40px"}' class="item-label">试卷名称</label>
						<el-input v-model="searchForm.name" placeholder="试卷名称" clearable></el-input>
					</div>
					<el-button class="search" :style='{"border":"0","cursor":"pointer","padding":"0 12px 0 10px","outline":"none","color":"#fff","borderRadius":"6px","background":"#497afa","width":"auto","fontSize":"16px","height":"36px"}' type="success" @click="search()">
						<span class="icon iconfont icon-fangdajing12" :style='{"margin":"0 0px","fontSize":"18px","color":"#fff","height":"40px"}'></span>
						查询
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
					<el-table-column :resizable='true' type="selection" header-align="center" align="center" width="50"></el-table-column>
					<el-table-column :resizable='true' :sortable='true' prop="name" header-align="center" align="center" sortable label="试卷名称">
						<template slot-scope="scope">{{scope.row.name}}</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true' prop="status" header-align="center" align="center" sortable label="试卷状态">
					</el-table-column>
					<el-table-column
						:resizable='true' :sortable='true'
						prop="time"
						header-align="center"
						align="center"
						sortable
						label="考试时长(分钟)"
					>
						<template slot-scope="scope">{{scope.row.time}}</template>
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
								测试
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
	</div>
</template>
<script>
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
				user: {}
			};
		},
		mounted() {
			this.$http({
				url: `${this.$storage.get("sessionTable")}/session`,
				method: "get"
			}).then(({ data }) => {
				if (data && data.code === 0) {
					this.user = data.data;
				} else {
					this.$message.error(data.msg);
				}
			});
			this.init();
			this.getDataList();
		},
		components: {},
		computed: {
			role(){
				return this.$storage.get('role')
			},
		},
		methods: {
			queryChange(arr){
				for(let x in arr) {
					if(arr[x] == this.role) {
						return true
					}
				}
				return false
			},
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
					limit: this.pageSize,
					sort: "id",
					status: "1"
				};
				if (this.searchForm.name) {
					params["name"] = `%${this.searchForm.name}%`;
				}
				let user = JSON.parse(this.$storage.getObj('userForm'))
				this.$http({
					url: this.$api.exampaperpage,
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
			// 添加/修改
			async addOrUpdateHandler(row) {
				// this.$http({
				// 	url: `examrecord/deleteRecords?userid=${this.user.id}&paperid=${row.id}`,
				// 	method: "post",
				// 	data: {}
				// }).then(({ data }) => {});
				let num = 0
				if(row.examnum>0) {
					await this.$http({
						url: this.$api.examrecordgroupby,
						method: 'get',
						params: {
							paperid: row.id,
							userid: this.user.id
						}
					}).then(rs=>{
						num = rs.data.data.list.length
					})
					if(num>=row.examnum) {
						this.$message.error('超过当前考试最大次数！')
						return false
					}
				}
				this.$router.push({
					name: "adminexam",
					params: {
						id: row.id
					}
				});
			},
			// 删除
			deleteHandler(id) {
				var ids = id
					? [Number(id)]
					: this.dataListSelections.map(item => {
						return Number(item.id);
					});
				this.$confirm(`确定进行[${id ? "删除" : "批量删除"}]操作?`, "提示", {
					confirmButtonText: "确定",
					cancelButtonText: "取消",
					type: "warning"
				}).then(() => {
					this.$http({
						url: this.$api.exampaperdelete,
						method: "post",
						data: ids
					}).then(({ data }) => {
						if (data && data.code === 0) {
							this.$message({
								message: "操作成功",
								type: "success",
								duration: 1500,
								onClose: () => {
									this.search();
								}
							});
						} else {
							this.$message.error(data.msg);
						}
					});
				});
			}
		}
	};
</script>
<style lang="scss" scoped>
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
