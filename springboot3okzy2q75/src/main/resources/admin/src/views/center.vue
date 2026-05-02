<template>
	<div :style='{"padding":"30px"}'>
		<el-form
			:style='{"border":"0px solid #ddd","padding":"30px 60px 30px 30px","boxShadow":"inset 0px -2px 6px 0px  #497afa60","borderRadius":"0 0 10px 10px","flexWrap":"wrap","background":"#ffffff","display":"flex"}'
			class="add-update-preview"
			ref="ruleForm"
			:model="ruleForm"
			label-width="180px"
		>
				<el-form-item :style='{"width":"48%","margin":"0 0 20px 0"}'   v-if="flag=='xuesheng'"  label="学号" prop="xuehao">
					<el-input v-model="ruleForm.xuehao" :readonly="ro.xuehao" placeholder="学号" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"width":"48%","margin":"0 0 20px 0"}'   v-if="flag=='xuesheng'"  label="姓名" prop="xingming">
					<el-input v-model="ruleForm.xingming" :readonly="ro.xingming" placeholder="姓名" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"width":"48%","margin":"0 0 20px 0"}' v-if="flag=='xuesheng'" label="头像" prop="touxiang">
					<file-upload
						tip="点击上传头像"
						action="file/upload"
						:limit="1"
						:multiple="false"
						:fileUrls="ruleForm.touxiang?ruleForm.touxiang:''"
						@change="xueshengtouxiangUploadChange"
					></file-upload>
				</el-form-item>
				<el-form-item :style='{"width":"48%","margin":"0 0 20px 0"}' v-if="flag=='xuesheng'"  label="性别" prop="xingbie">
					<el-select filterable v-model="ruleForm.xingbie" :disabled="ro.xingbie" placeholder="请选择性别">
						<el-option
							v-for="(item,index) in xueshengxingbieOptions"
							v-bind:key="index"
							:label="item"
							:value="item">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item :style='{"width":"48%","margin":"0 0 20px 0"}'   v-if="flag=='xuesheng'"  label="年龄" prop="nianling">
					<el-input v-model="ruleForm.nianling" :readonly="ro.nianling" placeholder="年龄" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"width":"48%","margin":"0 0 20px 0"}'   v-if="flag=='xuesheng'"  label="目标院校" prop="mubiaoyuanxiao">
					<el-input v-model="ruleForm.mubiaoyuanxiao" :readonly="ro.mubiaoyuanxiao" placeholder="目标院校" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"width":"48%","margin":"0 0 20px 0"}'   v-if="flag=='xuesheng'"  label="班级" prop="banji">
					<el-input v-model="ruleForm.banji" :readonly="ro.banji" placeholder="班级" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"width":"48%","margin":"0 0 20px 0"}'   v-if="flag=='xuesheng'"  label="备考科目" prop="beikaokemu">
					<el-input v-model="ruleForm.beikaokemu" :readonly="ro.beikaokemu" placeholder="备考科目" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"width":"48%","margin":"0 0 20px 0"}'   v-if="flag=='xuesheng'"  label="基础水平" prop="jichushuiping">
					<el-input v-model="ruleForm.jichushuiping" :readonly="ro.jichushuiping" placeholder="基础水平" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"width":"48%","margin":"0 0 20px 0"}'   v-if="flag=='xuesheng'"  label="手机号" prop="mobile">
					<el-input v-model="ruleForm.mobile" :readonly="ro.mobile" placeholder="手机号" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"width":"48%","margin":"0 0 20px 0"}'   v-if="flag=='xuesheng'"  label="密保问题" prop="pquestion">
					<el-input v-model="ruleForm.pquestion" :readonly="ro.pquestion" placeholder="密保问题" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"width":"48%","margin":"0 0 20px 0"}'   v-if="flag=='xuesheng'"  label="密保答案" prop="panswer">
					<el-input v-model="ruleForm.panswer" :readonly="ro.panswer" placeholder="密保答案" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"width":"48%","margin":"0 0 20px 0"}'   v-if="flag=='jiaoshi'"  label="工号" prop="gonghao">
					<el-input v-model="ruleForm.gonghao" :readonly="ro.gonghao" placeholder="工号" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"width":"48%","margin":"0 0 20px 0"}'   v-if="flag=='jiaoshi'"  label="教师姓名" prop="jiaoshixingming">
					<el-input v-model="ruleForm.jiaoshixingming" :readonly="ro.jiaoshixingming" placeholder="教师姓名" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"width":"48%","margin":"0 0 20px 0"}' v-if="flag=='jiaoshi'" label="头像" prop="touxiang">
					<file-upload
						tip="点击上传头像"
						action="file/upload"
						:limit="1"
						:multiple="false"
						:fileUrls="ruleForm.touxiang?ruleForm.touxiang:''"
						@change="jiaoshitouxiangUploadChange"
					></file-upload>
				</el-form-item>
				<el-form-item :style='{"width":"48%","margin":"0 0 20px 0"}' v-if="flag=='jiaoshi'" label="资质证明" prop="zizhizhengming">
					<file-upload
						tip="点击上传资质证明"
						action="file/upload"
						:limit="1"
						:type="3"
						:multiple="false"
						:fileUrls="ruleForm.zizhizhengming?ruleForm.zizhizhengming:''"
						@change="jiaoshizizhizhengmingUploadChange"
					></file-upload>
				</el-form-item>
				<el-form-item :style='{"width":"48%","margin":"0 0 20px 0"}' v-if="flag=='jiaoshi'"  label="性别" prop="xingbie">
					<el-select filterable v-model="ruleForm.xingbie" :disabled="ro.xingbie" placeholder="请选择性别">
						<el-option
							v-for="(item,index) in jiaoshixingbieOptions"
							v-bind:key="index"
							:label="item"
							:value="item">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item :style='{"width":"48%","margin":"0 0 20px 0"}'   v-if="flag=='jiaoshi'"  label="年龄" prop="nianling">
					<el-input v-model="ruleForm.nianling" :readonly="ro.nianling" placeholder="年龄" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"width":"48%","margin":"0 0 20px 0"}'   v-if="flag=='jiaoshi'"  label="职称" prop="zhicheng">
					<el-input v-model="ruleForm.zhicheng" :readonly="ro.zhicheng" placeholder="职称" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"width":"48%","margin":"0 0 20px 0"}'   v-if="flag=='jiaoshi'"  label="擅长科目" prop="shanzhangkemu">
					<el-input v-model="ruleForm.shanzhangkemu" :readonly="ro.shanzhangkemu" placeholder="擅长科目" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"width":"48%","margin":"0 0 20px 0"}'   v-if="flag=='jiaoshi'"  label="教学风格" prop="jiaoxuefengge">
					<el-input v-model="ruleForm.jiaoxuefengge" :readonly="ro.jiaoxuefengge" placeholder="教学风格" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"width":"48%","margin":"0 0 20px 0"}'   v-if="flag=='jiaoshi'"  label="手机号" prop="mobile">
					<el-input v-model="ruleForm.mobile" :readonly="ro.mobile" placeholder="手机号" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"width":"48%","margin":"0 0 20px 0"}'   v-if="flag=='jiaoshi'"  label="密保问题" prop="pquestion">
					<el-input v-model="ruleForm.pquestion" :readonly="ro.pquestion" placeholder="密保问题" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"width":"48%","margin":"0 0 20px 0"}'   v-if="flag=='jiaoshi'"  label="密保答案" prop="panswer">
					<el-input v-model="ruleForm.panswer" :readonly="ro.panswer" placeholder="密保答案" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"width":"48%","margin":"0 0 20px 0"}' v-if="flag=='jiaoshi'" label="辅导成果" prop="fudaochengguo">
					<editor
						:disabled="ro.fudaochengguo"
						myQuillEditor="fudaochengguo"
						style="min-width: 200px; max-width: 600px;"
						:style='{"width":"100%","height":"auto","maxWidth":"100% !important"}'
						v-model="ruleForm.fudaochengguo" 
						class="editor" 
						action="file/upload">
					</editor>
				</el-form-item>
				<el-form-item :style='{"width":"48%","margin":"0 0 20px 0"}' v-if="flag=='users'" label="用户名" prop="username">
					<el-input v-model="ruleForm.username" placeholder="用户名"></el-input>
				</el-form-item>
				<el-form-item :style='{"width":"48%","margin":"0 0 20px 0"}' v-if="flag=='users'" label="头像" prop="image">
					<file-upload
						tip="点击上传头像"
						action="file/upload"
						:limit="1"
						:multiple="false"
						:fileUrls="ruleForm.image?ruleForm.image:''"
						@change="usersimageUploadChange"
					></file-upload>
				</el-form-item>
				<el-form-item :style='{"width":"100%","margin":"10px auto","justifyContent":"flex-start","display":"flex"}'>
					<el-button class="btn3" type="primary" @click="onUpdateHandler">
						<span class="icon iconfont icon-queren15"></span>
						保存
					</el-button>
				</el-form-item>
		</el-form>
	</div>
</template>
<script>
// 校验引入
	import { 
		isIntNumer,
		isMobile,
	} from "@/utils/validate";

	export default {
		data() {
			return {
				ruleForm: {},
				ro: {},
				flag: '',
				usersFlag: false,
				xueshengxingbieOptions: [],
				jiaoshixingbieOptions: [],
			};
		},
		mounted() {
			var table = this.$storage.get("sessionTable");
			this.flag = table;
			this.$http({
				url: `${this.$storage.get("sessionTable")}/session`,
				method: "get"
			}).then(({ data }) => {
				if (data && data.code === 0) {
					if(table == 'xuesheng') {
						this.ro = {
							xuehao: false,
							mima: false,
							xingming: false,
							touxiang: false,
							xingbie: false,
							nianling: false,
							mubiaoyuanxiao: false,
							banji: false,
							beikaokemu: false,
							jichushuiping: false,
							mobile: false,
							pquestion: false,
							panswer: false,
							status: false,
						}
					}
					if(table == 'jiaoshi') {
						this.ro = {
							gonghao: false,
							mima: false,
							jiaoshixingming: false,
							touxiang: false,
							zizhizhengming: false,
							xingbie: false,
							nianling: false,
							zhicheng: false,
							shanzhangkemu: false,
							jiaoxuefengge: false,
							fudaochengguo: false,
							sfsh: false,
							shhf: false,
							mobile: false,
							pquestion: false,
							panswer: false,
							status: false,
						}
					}

					this.ruleForm = data.data;
				} else {
					this.$message.error(data.msg);
				}
			});
			this.xueshengxingbieOptions = "男,女".split(',')
			this.jiaoshixingbieOptions = "男,女".split(',')
		},
		methods: {
			xueshengtouxiangUploadChange(fileUrls) {
				this.ruleForm.touxiang = fileUrls;
			},
			jiaoshitouxiangUploadChange(fileUrls) {
				this.ruleForm.touxiang = fileUrls;
			},
			jiaoshizizhizhengmingUploadChange(fileUrls) {
				this.ruleForm.zizhizhengming = fileUrls;
			},
			usersimageUploadChange(fileUrls) {
				this.ruleForm.image = fileUrls;
			},
			onUpdateHandler() {
				if((!this.ruleForm.xuehao)&& 'xuesheng'==this.flag){
					this.$message.error('学号不能为空');
					return
				}
				if((!this.ruleForm.mima)&& 'xuesheng'==this.flag){
					this.$message.error('密码不能为空');
					return
				}
				if((!this.ruleForm.xingming)&& 'xuesheng'==this.flag){
					this.$message.error('姓名不能为空');
					return
				}
				if(this.ruleForm.touxiang!=null) {
					this.ruleForm.touxiang = this.ruleForm.touxiang.replace(new RegExp(this.$base.url,"g"),"");
				}
				if('xuesheng' ==this.flag && this.ruleForm.nianling&&(!isIntNumer(this.ruleForm.nianling))){
					this.$message.error(`年龄应输入整数`);
					return
				}
				if('xuesheng' ==this.flag && this.ruleForm.mobile&&(!isMobile(this.ruleForm.mobile))){
					this.$message.error(`手机号应输入手机格式`);
					return
				}
				if('xuesheng' ==this.flag && this.ruleForm.status&&(!isIntNumer(this.ruleForm.status))){
					this.$message.error(`状态应输入整数`);
					return
				}
				if((!this.ruleForm.gonghao)&& 'jiaoshi'==this.flag){
					this.$message.error('工号不能为空');
					return
				}
				if((!this.ruleForm.mima)&& 'jiaoshi'==this.flag){
					this.$message.error('密码不能为空');
					return
				}
				if((!this.ruleForm.jiaoshixingming)&& 'jiaoshi'==this.flag){
					this.$message.error('教师姓名不能为空');
					return
				}
				if(this.ruleForm.touxiang!=null) {
					this.ruleForm.touxiang = this.ruleForm.touxiang.replace(new RegExp(this.$base.url,"g"),"");
				}
				if((!this.ruleForm.zizhizhengming)&& 'jiaoshi'==this.flag){
					this.$message.error('资质证明不能为空');
					return
				}
				if(this.ruleForm.zizhizhengming!=null) {
					this.ruleForm.zizhizhengming = this.ruleForm.zizhizhengming.replace(new RegExp(this.$base.url,"g"),"");
				}
				if('jiaoshi' ==this.flag && this.ruleForm.nianling&&(!isIntNumer(this.ruleForm.nianling))){
					this.$message.error(`年龄应输入整数`);
					return
				}
				if((!this.ruleForm.zhicheng)&& 'jiaoshi'==this.flag){
					this.$message.error('职称不能为空');
					return
				}
				if('jiaoshi' ==this.flag && this.ruleForm.mobile&&(!isMobile(this.ruleForm.mobile))){
					this.$message.error(`手机号应输入手机格式`);
					return
				}
				if('jiaoshi' ==this.flag && this.ruleForm.status&&(!isIntNumer(this.ruleForm.status))){
					this.$message.error(`状态应输入整数`);
					return
				}
				if('users'==this.flag && this.ruleForm.username.trim().length<1) {
					this.$message.error(`用户名不能为空`);
					return	
				}
				if(this.flag=='users'){
					this.ruleForm.image = this.ruleForm.image.replace(new RegExp(this.$base.url,"g"),"")
				}
				this.$http({
					url: `${this.$storage.get("sessionTable")}/update`,
					method: "post",
					data: this.ruleForm
				}).then(({ data }) => {
					if (data && data.code === 0) {
						if(this.flag=='users'){
							this.$storage.set('headportrait',this.ruleForm.image)
						}else {
							if(this.flag == 'xuesheng') {
								this.$storage.set('headportrait',this.ruleForm.touxiang)
							}
							if(this.flag == 'jiaoshi') {
								this.$storage.set('headportrait',this.ruleForm.touxiang)
							}
						}
						this.$message({
							message: "修改信息成功",
							type: "success",
							duration: 1500,
							onClose: () => {
								window.location.reload();
							}
						});
					} else {
						this.$message.error(data.msg);
					}
				});
			},
		}
	};
</script>
<style lang="scss" scoped>
	.el-date-editor.el-input {
		width: auto;
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
	
	.add-update-preview /deep/ .el-upload--picture-card {
		background: transparent;
		border: 0;
		border-radius: 0;
		width: auto;
		height: auto;
		line-height: initial;
		vertical-align: middle;
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
	
	.add-update-preview .btn3 {
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
	
	.add-update-preview .btn3:hover {
				opacity: 0.8;
			}
	.editor>.avatar-uploader {
		line-height: 0;
		height: 0;
	}
</style>
