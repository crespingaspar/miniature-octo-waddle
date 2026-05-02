
















<template>
	<div class="addEdit-block">
		<el-form
			class="add-update-preview"
			ref="ruleForm"
			:model="ruleForm"
			:rules="rules"
			label-width="180px"
		>
			<template >
				<el-form-item class="input" v-if="type!='info'"  label="课程名称" prop="kechengmingcheng" >
					<el-input v-model="ruleForm.kechengmingcheng" placeholder="课程名称" clearable  :readonly="ro.kechengmingcheng"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="课程名称" prop="kechengmingcheng" >
					<el-input v-model="ruleForm.kechengmingcheng" placeholder="课程名称" readonly></el-input>
				</el-form-item>
				<el-form-item class="select" v-if="type!='info'"  label="节数" prop="sectionnum" >
					<el-select :disabled="ro.sectionnum" v-model="ruleForm.sectionnum" placeholder="请选择节数" multiple filterable>
						<el-option
							v-for="(item,index) in sectionnumOptions"
							v-bind:key="index"
							:label="item"
							:value="item">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item v-else class="input" label="节数" prop="sectionnum" >
					<el-input v-model="ruleForm.sectionnum"
						placeholder="节数" readonly></el-input>
				</el-form-item>
				<el-form-item class="date" v-if="type!='info'" label="日期" prop="riqi" >
					<el-date-picker
						format="yyyy 年 MM 月 dd 日"
						value-format="yyyy-MM-dd"
						v-model="ruleForm.riqi" 
						type="date"
						:readonly="ro.riqi"
						:picker-options="riqiPickerOptions"
						placeholder="日期"
					></el-date-picker> 
				</el-form-item>
				<el-form-item class="input" v-else-if="ruleForm.riqi" label="日期" prop="riqi" >
					<el-input v-model="ruleForm.riqi" placeholder="日期" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="星期" prop="week" >
					<el-input v-model.number="ruleForm.week" placeholder="星期" clearable  :readonly="ro.week"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="星期" prop="week" >
					<el-input v-model="ruleForm.week" placeholder="星期" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="班级" prop="banji" >
					<el-input v-model="ruleForm.banji" placeholder="班级" clearable  :readonly="ro.banji"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="班级" prop="banji" >
					<el-input v-model="ruleForm.banji" placeholder="班级" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="教室" prop="jiaoshi" >
					<el-input v-model="ruleForm.jiaoshi" placeholder="教室" clearable  :readonly="ro.jiaoshi"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="教室" prop="jiaoshi" >
					<el-input v-model="ruleForm.jiaoshi" placeholder="教室" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="工号" prop="gonghao" >
					<el-input v-model="ruleForm.gonghao" placeholder="工号" clearable  :readonly="ro.gonghao"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="工号" prop="gonghao" >
					<el-input v-model="ruleForm.gonghao" placeholder="工号" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="教师姓名" prop="jiaoshixingming" >
					<el-input v-model="ruleForm.jiaoshixingming" placeholder="教师姓名" clearable  :readonly="ro.jiaoshixingming"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="教师姓名" prop="jiaoshixingming" >
					<el-input v-model="ruleForm.jiaoshixingming" placeholder="教师姓名" readonly></el-input>
				</el-form-item>
			</template>
			<el-form-item class="btn">
				<el-button class="btn3"  v-if="type!='info'" type="success" @click="onSubmit">
					<span class="icon iconfont icon-queren15"></span>
					保存
				</el-button>
				<el-button class="btn4" v-if="type!='info'" type="success" @click="back()">
					<span class="icon iconfont icon-guanbi2"></span>
					取消
				</el-button>
				<el-button class="btn5" v-if="type=='info'" type="success" @click="back()">
					<span class="icon iconfont icon-fanhui13"></span>
					返回
				</el-button>
			</el-form-item>
		</el-form>
    

	</div>
</template>
<script>
	import { 
		isIntNumer,
	} from "@/utils/validate";
	export default {
		data() {
			var validateIntNumber = (rule, value, callback) => {
				if(!value){
					callback();
				} else if (!isIntNumer(value)) {
					callback(new Error("请输入整数"));
				} else {
					callback();
				}
			};
			return {
				id: '',
				type: '',
			
			
				ro:{
					kechengmingcheng : false,
					sectionnum : false,
					riqi : false,
					week : false,
					banji : false,
					jiaoshi : false,
					gonghao : false,
					jiaoshixingming : false,
				},
			
				ruleForm: {
					kechengmingcheng: '',
					sectionnum: [],
					riqi: '',
					week: '',
					banji: '',
					jiaoshi: '',
					gonghao: '',
					jiaoshixingming: '',
				},
				sectionnumOptions: [],

				riqiPickerOptions: {
					disabledDate(time) {
						return time.getTime() < Date.now() - 8.64e7;
					}
				},
				rules: {
					kechengmingcheng: [
						{ required: true, message: '课程名称不能为空', trigger: 'blur' },
					],
					sectionnum: [
						{ required: true, message: '节数不能为空', trigger: 'blur' },
					],
					riqi: [
						{ required: true, message: '日期不能为空', trigger: 'blur' },
					],
					week: [
						{ required: true, message: '星期不能为空', trigger: 'blur' },
						{ validator: validateIntNumber, trigger: 'blur' },
					],
					banji: [
						{ required: true, message: '班级不能为空', trigger: 'blur' },
					],
					jiaoshi: [
						{ required: true, message: '教室不能为空', trigger: 'blur' },
					],
					gonghao: [
					],
					jiaoshixingming: [
					],
				},
			};
		},
		props: ["parent"],
		computed: {
			sessionForm() {
				return JSON.parse(this.$storage.getObj('userForm'))
			},
			sessionTable() {
				return this.$storage.get('sessionTable')
			},



		},
		components: {
		},
		created() {
		},
		methods: {
			imgPreView(url){
				this.$parent.imgPreView(url)
			},
			// 下载
			download(file){
				window.open(`${file}`)
			},
			// 初始化
			init(id,type ) {
				if (id) {
					this.id = id;
					this.type = type;
				}
				if(this.type=='info'||this.type=='else'||this.type=='msg'){
					this.info(id);
				}else if(this.type=='logistics'){
					for(let x in this.ro) {
						this.ro[x] = true
					}
					this.logistics=false;
					this.info(id);
				}else if(this.type=='cross'){
					var obj = this.$storage.getObj('crossObj');
					for (var o in obj){
						if(o=='kechengmingcheng'){
							this.ruleForm.kechengmingcheng = obj[o];
							this.ro.kechengmingcheng = true;
							continue;
						}
						if(o=='sectionnum'){
							this.ruleForm.sectionnum = obj[o];
							this.ro.sectionnum = true;
							continue;
						}
						if(o=='riqi'){
							this.ruleForm.riqi = obj[o];
							this.ro.riqi = true;
							continue;
						}
						if(o=='week'){
							this.ruleForm.week = obj[o];
							this.ro.week = true;
							continue;
						}
						if(o=='banji'){
							this.ruleForm.banji = obj[o];
							this.ro.banji = true;
							continue;
						}
						if(o=='jiaoshi'){
							this.ruleForm.jiaoshi = obj[o];
							this.ro.jiaoshi = true;
							continue;
						}
						if(o=='gonghao'){
							this.ruleForm.gonghao = obj[o];
							this.ro.gonghao = true;
							continue;
						}
						if(o=='jiaoshixingming'){
							this.ruleForm.jiaoshixingming = obj[o];
							this.ro.jiaoshixingming = true;
							continue;
						}
					}
				}

				// 获取用户信息
				this.$http({
					url: `${this.sessionTable}/session`,
					method: "get"
				}).then(({ data }) => {
					if (data && data.code === 0) {
						var json = data.data;
						if(((json.gonghao!=''&&json.gonghao) || json.gonghao==0) && this.sessionTable!="users"){
							this.ruleForm.gonghao = json.gonghao
							this.ro.gonghao = true;
						}
						if(((json.jiaoshixingming!=''&&json.jiaoshixingming) || json.jiaoshixingming==0) && this.sessionTable!="users"){
							this.ruleForm.jiaoshixingming = json.jiaoshixingming
							this.ro.jiaoshixingming = true;
						}
					} else {
						this.$message.error(data.msg);
					}
				});
				this.$http({
					url: `option/scheduletime/sectionnum`,
					method: "get",
				}).then(({ data }) => {
					if (data && data.code === 0) {
						this.sectionnumOptions = data.data;
					} else {
						this.$message.error(data.msg);
					}
				});
			
			},
			// 多级联动参数

			async info(id) {
				await this.$http({
					url: `kebiaoxinix/info/${id}`,
					method: "get"
				}).then(({ data }) => {
					if (data && data.code === 0) {
						this.ruleForm = data.data;
						//解决前台上传图片后台不显示的问题
						let reg=new RegExp('../../../upload','g')//g代表全部
						this.ruleForm.sectionnum = this.ruleForm.sectionnum.split(",");
					} else {
						this.$message.error(data.msg);
					}
				});
			},

			hasDuplicate(arr1, arr2) {
				for(let i = 0; i < arr1.length; i++) {
					for(let j = 0; j < arr2.length; j++) {
						if(arr1[i] === arr2[j]) { // 判断arr1的元素和arr2的元素是否相等
							return true; // 存在重复值，返回true
						}
					}
				}
				return false; // 不存在重复值，返回false
			},
			// 提交
			async onSubmit() {
				await this.$http.get('kebiaoxinix/page',{
					params: {
						week: this.ruleForm.week,
						gonghao: this.ruleForm.gonghao,
					}
				}).then(async rs=>{
					let arr = []
					let brr = []
					let crr = JSON.parse(JSON.stringify(this.ruleForm.sectionnum))
					let drr = []
					for(let x in rs.data.data.list){
						if((this.ruleForm.id&&this.ruleForm.id!=rs.data.data.list[x].id)||!this.ruleForm.id){
							if(rs.data.data.list[x].sectionnum){
								brr = rs.data.data.list[x].sectionnum.split(',')
								if(brr.length>1){
									for(let i = Number(brr[0]);i<=Number(brr[brr.length - 1]);i++){
										arr.push(i)
									}
								}else {
									arr.push(Number(brr[0]))
								}
							}
						}
					}
					if(crr.length>1){
						for(let z = Number(crr[0]);z<=Number(crr[crr.length - 1]);z++){
							drr.push(z)
						}
					}else {
						drr.push(Number(crr[0]))
					}
					if(this.hasDuplicate(arr,drr)){
						this.$message.error("课程设置与其他课程冲突");
						return false
					}
					if(this.ruleForm.week>7||this.ruleForm.week<1) {
						this.$message.error("星期范围在1-7之间");
						return
					}
					var objcross = this.$storage.getObj('crossObj');
					if(!this.ruleForm.id) {
						delete this.ruleForm.userid
					}
					await this.$refs["ruleForm"].validate(async valid => {
						if (valid) {
							if(this.type=='cross'){
								var statusColumnName = this.$storage.get('statusColumnName');
								var statusColumnValue = this.$storage.get('statusColumnValue');
								if(statusColumnName!='') {
									var obj = this.$storage.getObj('crossObj');
									if(statusColumnName && !statusColumnName.startsWith("[")) {
										for (var o in obj){
											if(o==statusColumnName){
												obj[o] = statusColumnValue;
											}
										}
										var table = this.$storage.get('crossTable');
										await this.$http({
											url: `${table}/update`,
											method: "post",
											data: obj
										}).then(({ data }) => {});
									}
								}
							}
							this.ruleForm.sectionnum = this.ruleForm.sectionnum.sort((a, b) => a-b)
							this.ruleForm.sectionnum = this.ruleForm.sectionnum.join(",");
							await this.$http({
								url: `kebiaoxinix/${!this.ruleForm.id ? "save" : "update"}`,
								method: "post",
								data: this.ruleForm
							}).then(async ({ data }) => {
								if (data && data.code === 0) {
									this.$message({
										message: "操作成功",
										type: "success",
										duration: 1500,
										onClose: () => {
											this.parent.showFlag = true;
											this.parent.addOrUpdateFlag = false;
											this.parent.kebiaoxinixCrossAddOrUpdateFlag = false;
											this.parent.search();
										}
									});
								} else {
									this.$message.error(data.msg);
								}
							});
						}
					});
				})
			},
			// 获取uuid
			getUUID () {
				return new Date().getTime();
			},
			// 返回
			back() {
				this.parent.showFlag = true;
				this.parent.addOrUpdateFlag = false;
				this.parent.kebiaoxinixCrossAddOrUpdateFlag = false;
			},
		}
	};
</script>
<style lang="scss" scoped>
	.addEdit-block {
		padding: 0 30px 30px;
	}
	.add-update-preview {
		border: 0px solid #ddd;
		border-radius: 0 0 10px 10px;
		padding: 30px 60px 30px 30px;
		box-shadow: inset 0px -2px 6px 0px  #497afa60;
		background: #ffffff;
		display: flex;
		flex-wrap: wrap;
	}
	.amap-wrapper {
		width: 100%;
		height: 500px;
	}
	
	.search-box {
		position: absolute;
	}
	
	.el-date-editor.el-input {
		width: auto;
	}
	.add-update-preview /deep/ .el-form-item {
		margin: 0 0 20px 0;
		width: 48%;
	}
	.add-update-preview .el-form-item /deep/ .el-form-item__label {
		padding: 0 10px 0 0;
		color: #666;
		white-space: nowrap;
		font-weight: 500;
		width: 180px;
		font-size: 16px;
		line-height: 40px;
		text-align: right;
	}
	
	.add-update-preview .el-form-item /deep/ .el-form-item__content {
		margin: 0 0 0 80px;
		display: flex;
		align-items: center;
	}
	.add-update-preview /deep/ .el-form-item.editorBox {
		margin: 0 0 20px 0;
		width: 100%;
	}
	.add-update-preview .el-form-item.editorBox /deep/ .el-form-item__label {
		padding: 0 10px 0 0;
		color: #666;
		font-weight: 500;
		width: 180px;
		font-size: 14px;
		line-height: 40px;
		text-align: right;
	}
	
	.add-update-preview .el-form-item.editorBox /deep/ .el-form-item__content {
		margin: 0;
		display: flex;
		align-items: center;
	}
	.add-update-preview /deep/.el-form-item.editorBox .editor {
		max-width: 100% !important;
		width: 100%;
		height: auto;
	}
	.add-update-preview /deep/.el-form-item.editorBox .editor .ql-toolbar {
		border-radius: 6px 6px 0 0;
		box-shadow: inset 0px 2px 3px 0px  #497afa50;
		background: none;
		border-color: #e0e6ff;
		border-width: 0 0 1px;
		border-style: solid;
	}
	.add-update-preview /deep/.el-form-item.editorBox .editor .ql-container {
		border: none;
		border-radius: 0 0 6px 6px;
		box-shadow: inset 0px -2px 3px 0px  #497afa50;
		background: none;
		min-height: 200px;
	}
	.add-update-preview /deep/.el-form-item.editorBox .editor .ql-container .ql-blank::before {
		color: #999;
	}
	
	.add-update-preview /deep/ .el-form-item.textBox {
		margin: 0 0 20px 0;
		width: 100%;
	}
	.add-update-preview .el-form-item.textBox /deep/ .el-form-item__label {
		padding: 0 10px 0 0;
		color: #666;
		font-weight: 500;
		width: 180px;
		font-size: 16px;
		line-height: 40px;
		text-align: right;
	}
	
	.add-update-preview .el-form-item.textBox /deep/ .el-form-item__content {
		margin: 0 0 0 80px;
		display: flex;
		align-items: center;
	}
	.add-update-preview /deep/.el-form-item.textBox span.text {
		padding: 0;
		color: #666;
		font-weight: 500;
		display: inline-block;
		font-size: 16px;
		line-height: 40px;
	}
	
	.add-update-preview .el-input {
		width: 100%;
	}
	.add-update-preview .el-input /deep/ .el-input__inner {
		border-radius: 6px;
		padding: 0 12px;
		box-shadow: inset 0px 2px 3px 0px  #497afa30,inset 0px 0px 3px 0px  #497afa50;
		outline: none;
		color: #333;
		width: 100%;
		font-size: 16px;
		border-color: #e1e1e1;
		border-width: 0 0 0px;
		border-style: solid;
		height: 40px;
	}
	.add-update-preview .el-input /deep/ .el-input__inner[readonly="readonly"] {
		border: 0px solid #ddd;
		cursor: not-allowed;
		border-radius: 4px;
		padding: 0 12px;
		box-shadow: none;
		outline: none;
		color: #999;
		width: 100%;
		font-size: 16px;
		height: 40px;
	}
	.add-update-preview .el-input-number {
		text-align: left;
		width: 100%;
	}
	.add-update-preview .el-input-number /deep/ .el-input__inner {
		text-align: left;
		border-radius: 6px;
		padding: 0 12px;
		box-shadow: inset 0px 2px 3px 0px  #497afa30,inset 0px 0px 3px 0px  #497afa50;
		outline: none;
		color: #333;
		width: 100%;
		font-size: 16px;
		border-color: #e1e1e1;
		border-width: 0 0 0px;
		border-style: solid;
		height: 40px;
	}
	.add-update-preview .el-input-number /deep/ .is-disabled .el-input__inner {
		text-align: left;
		border: 0px solid #ddd;
		cursor: not-allowed;
		border-radius: 4px;
		padding: 0 12px;
		box-shadow: none;
		outline: none;
		color: #999;
		width: 100%;
		font-size: 16px;
		height: 40px;
	}
	.add-update-preview .el-input-number /deep/ .el-input-number__decrease {
		display: none;
	}
	.add-update-preview .el-input-number /deep/ .el-input-number__increase {
		display: none;
	}
	.add-update-preview .el-select {
		width: 100%;
	}
	.add-update-preview .el-select /deep/ .el-input__inner {
		border-radius: 6px;
		padding: 0 10px;
		box-shadow: inset 0px 2px 3px 0px  #497afa30,inset 0px 0px 3px 0px  #497afa50;
		outline: none;
		color: #333;
		width: 100%;
		font-size: 16px;
		border-color: #e1e1e1;
		border-width: 0 0 0px;
		border-style: solid;
		height: 40px;
	}
	.add-update-preview .el-select /deep/ .is-disabled .el-input__inner {
		border: 0;
		cursor: not-allowed;
		border-radius: 4px;
		padding: 0 10px;
		box-shadow: none;
		outline: none;
		color: #999;
		background: #f8f8f8;
		width: 100%;
		font-size: 16px;
		height: 40px;
	}
	.add-update-preview .el-date-editor {
		width: 100%;
	}
	.add-update-preview .el-date-editor /deep/ .el-input__inner {
		border-radius: 6px;
		padding: 0 10px 0 30px;
		box-shadow: inset 0px 2px 3px 0px  #497afa30,inset 0px 0px 3px 0px  #497afa50;
		outline: none;
		color: #333;
		width: 100%;
		font-size: 16px;
		border-color: #e1e1e1;
		border-width: 0 0 1px;
		border-style: solid;
		height: 40px;
	}
	.add-update-preview .el-date-editor /deep/ .el-input__inner[readonly="readonly"] {
		border: 0;
		cursor: not-allowed;
		border-radius: 4px;
		padding: 0 10px 0 30px;
		box-shadow: none;
		outline: none;
		color: #999;
		background: #f8f8f8;
		width: 100%;
		font-size: 16px;
		height: 40px;
	}
	.add-update-preview .viewBtn {
		border: 1px solid #497afa50;
		cursor: pointer;
		border-radius: 4px;
		padding: 0 15px;
		margin: 0 20px 0 0;
		outline: none;
		color: #497afa;
		background: #fff;
		width: auto;
		font-size: 14px;
		line-height: 40px;
		height: 40px;
		.iconfont {
			margin: 0 2px;
			color: #fff;
			display: none;
			font-size: 14px;
			height: 40px;
		}
	}
	.add-update-preview .viewBtn:hover {
		opacity: 0.8;
	}
	.add-update-preview .downBtn {
		border: 1px solid #497afa50;
		cursor: pointer;
		border-radius: 4px;
		padding: 0 15px;
		margin: 0 20px 0 0;
		outline: none;
		color: #497afa;
		background: #fff;
		width: auto;
		font-size: 14px;
		line-height: 40px;
		height: 40px;
		.iconfont {
			margin: 0 2px;
			color: #fff;
			display: none;
			font-size: 14px;
			height: 40px;
		}
	}
	.add-update-preview .downBtn:hover {
		opacity: 0.8;
	}
	.add-update-preview .unBtn {
		border: 0px solid #ff7f0050;
		cursor: pointer;
		border-radius: 4px;
		padding: 0 15px;
		margin: 0 20px 0 0;
		outline: none;
		color: #333;
		background: #eee;
		width: auto;
		font-size: 14px;
		line-height: 40px;
		height: 40px;
		.iconfont {
			margin: 0 2px;
			color: #fff;
			display: none;
			font-size: 14px;
			height: 40px;
		}
	}
	.add-update-preview .unBtn:hover {
		opacity: 0.8;
	}
	.add-update-preview /deep/ .el-upload--picture-card {
		background: transparent;
		border: 0;
		border-radius: 0;
		width: auto;
		height: auto;
		line-height: initial;
		vertical-align: middle;
	}
	
	.add-update-preview /deep/ .upload .upload-img {
		border: 0px solid #e1e1e1;
		cursor: pointer;
		border-radius: 6px;
		box-shadow: inset 0px 2px 3px 0px  #497afa30,inset 0px 0px 3px 0px  #497afa50;
		color: #5893FA;
		width: 100px;
		font-size: 26px;
		line-height: 100px;
		text-align: center;
		height: 100px;
	}
	
	.add-update-preview /deep/ .el-upload-list .el-upload-list__item {
		border: 0px solid #e1e1e1;
		cursor: pointer;
		border-radius: 6px;
		box-shadow: inset 0px 2px 3px 0px  #497afa30,inset 0px 0px 3px 0px  #497afa50;
		color: #5893FA;
		width: 100px;
		font-size: 26px;
		line-height: 100px;
		text-align: center;
		height: 100px;
	}
	
	.add-update-preview /deep/ .el-upload .el-icon-plus {
		border: 0px solid #e1e1e1;
		cursor: pointer;
		border-radius: 6px;
		box-shadow: inset 0px 2px 3px 0px  #497afa30,inset 0px 0px 3px 0px  #497afa50;
		color: #5893FA;
		width: 100px;
		font-size: 26px;
		line-height: 100px;
		text-align: center;
		height: 100px;
	}
	.add-update-preview /deep/ .el-upload__tip {
		color: #666;
		font-size: 15px;
	}
	.add-update-preview /deep/ .el-form-item.fileupload {
		margin: 0 0 20px 0;
	}
	.add-update-preview .el-form-item.fileupload /deep/ .el-form-item__label {
		padding: 0 10px 0 0;
		color: #666;
		white-space: nowrap;
		font-weight: 500;
		width: 180px;
		font-size: 16px;
		line-height: 40px;
		text-align: right;
	}
	
	.add-update-preview .el-form-item.fileupload /deep/ .el-form-item__content {
		margin: 0 0 0 80px;
		display: flex;
		align-items: center;
	}
	.add-update-preview .el-form-item.fileupload /deep/ .el-upload-dragger {
		border: 0px dashed #d9d9d9;
		cursor: pointer;
		background-color: #fff;
		border-radius: 6px;
		padding: 5px 40px 20px;
		box-shadow: inset 0px 2px 3px 0px  #497afa30,inset 0px 0px 3px 0px  #497afa50;
		overflow: hidden;
		width: auto;
		box-sizing: border-box;
		text-align: center;
		height: auto;
	}
	.add-update-preview .el-form-item.fileupload .el-upload-dragger /deep/ .el-icon-upload {
		margin: 0;
		color: #497afa;
		font-size: 48px;
		line-height: 1;
	}
	.add-update-preview .el-form-item.fileupload .el-upload-dragger /deep/ .el-upload__text {
		color: #666;
		textalign: center;
		fontsize: 14px;
		line-height: 1;
	}
	.add-update-preview .el-form-item.fileupload .el-upload-dragger /deep/ .el-upload__text em {
		fontstyle: normal;
		color: #497afa;
	}
	
	.add-update-preview .el-textarea /deep/ .el-textarea__inner {
		border-radius: 6px;
		padding: 12px;
		box-shadow: inset 0px 2px 3px 0px  #497afa30,inset 0px 0px 3px 0px  #497afa50;
		outline: none;
		color: #666;
		width: 100%;
		font-size: 16px;
		border-color: #e1e1e1;
		border-width: 0 0 0px;
		border-style: solid;
		height: auto;
	}
	.add-update-preview .el-textarea /deep/ .el-textarea__inner[readonly="readonly"] {
		border: 0;
		cursor: not-allowed;
		border-radius: 4px;
		padding: 12px;
		box-shadow: none;
		outline: none;
		color: #666;
		width: 100%;
		font-size: 16px;
		height: auto;
	}
	.add-update-preview /deep/ .el-form-item.btn {
		margin: 10px auto;
		display: flex;
		width: 100%;
		justify-content: flex-start;
		.btn1 {
			border: 1px solid #497afa50;
			cursor: pointer;
			border-radius: 4px;
			padding: 0 30px;
			margin: 4px;
			outline: none;
			color: #497afa;
			background: #fff;
			width: auto;
			font-size: 16px;
			height: 40px;
			.iconfont {
				margin: 0 2px;
				color: #fff;
				display: none;
				font-size: 16px;
				height: 40px;
			}
		}
		.btn1:hover {
			opacity: 0.8;
		}
		.btn2 {
			border: 1px solid #6ad2ff50;
			cursor: pointer;
			border-radius: 4px;
			padding: 0 24px;
			margin: 4px;
			outline: none;
			color: #3e97bd;
			background: #fff;
			width: auto;
			font-size: 16px;
			height: 40px;
			.iconfont {
				margin: 0 2px;
				color: #fff;
				display: none;
				font-size: 16px;
				height: 40px;
			}
		}
		.btn2:hover {
			opacity: 0.8;
		}
		.btn3 {
			border: 0;
			cursor: pointer;
			border-radius: 4px;
			padding: 0 24px;
			margin: 4px;
			outline: none;
			color: #fff;
			background: #497afa;
			width: auto;
			font-size: 16px;
			height: 40px;
			.iconfont {
				margin: 0 2px;
				color: #fff;
				display: none;
				font-size: 16px;
				height: 40px;
			}
		}
		.btn3:hover {
			opacity: 0.8;
		}
		.btn4 {
			border: 1px solid #e0e6ff;
			cursor: pointer;
			border-radius: 4px;
			padding: 0 30px;
			margin: 4px;
			outline: none;
			color: #6f85bc;
			background: #fff;
			width: auto;
			font-size: 16px;
			height: 40px;
			.iconfont {
				margin: 0 2px;
				color: #333;
				display: none;
				font-size: 16px;
				height: 40px;
			}
		}
		.btn4:hover {
			opacity: 0.8;
		}
		.btn5 {
			border: 1px solid #ddd;
			cursor: pointer;
			border-radius: 4px;
			padding: 0 30px;
			margin: 4px;
			outline: none;
			color: #666;
			background: #eee;
			width: auto;
			font-size: 16px;
			height: 40px;
			.iconfont {
				margin: 0 2px;
				color: #fff;
				display: none;
				font-size: 16px;
				height: 40px;
			}
		}
		.btn5:hover {
			opacity: 0.8;
		}
	}
	.add-update-preview .el-form-item.btn /deep/ .el-form-item__label {
		padding: 0 10px 0 0;
		color: #666;
		white-space: nowrap;
		font-weight: 500;
		width: 180px;
		font-size: 16px;
		line-height: 40px;
		text-align: right;
	}
	
	.add-update-preview .el-form-item.btn /deep/ .el-form-item__content {
	}
</style>
