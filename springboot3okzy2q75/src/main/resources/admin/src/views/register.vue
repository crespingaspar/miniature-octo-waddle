<template>
	<div>
		<div class="register-container" :style="{'backgroundImage': indexBgUrl?`url(${$base.url + indexBgUrl})`:''}">
			<el-form v-if="pageFlag=='security1'" class="rgs-form animate__animated animate__" :model="forgetForm">
				<div class="rgs-form2">
					<div class="title">基于HarmonyOS智能教育辅导平台的设计与实现找回密码</div>
					<el-form-item class="list-item">
						<div class="lable">账号</div>
						<el-input v-model="forgetForm.username" autocomplete="off" placeholder="请您输入账号" />
					</el-form-item>
					<div class="register-btn">
						<div class="register-btn1">
							<button type="button" class="r-btn" @click="getSecurity()">下一步</button>
						</div>
						<div class="register-btn2">
							<div class="r-login" @click="close()">已有账号，直接登录</div>
						</div>
					</div>
				</div>
            </el-form>
			<el-form v-if="pageFlag=='security2'" class="rgs-form animate__animated animate__" :model="ruleForm">
				<div class="rgs-form2">
					<div class="title">基于HarmonyOS智能教育辅导平台的设计与实现密码重置</div>
					<el-form-item class="list-item">
						<div class="lable">密保问题</div>
						<el-input v-model="ruleForm.pquestion" readOnly autocomplete="off" placeholder="密保问题" />
					</el-form-item>
					<el-form-item class="list-item">
						<div class="lable">密保答案</div>
						<el-input v-model="ruleForm.myanswer" autocomplete="off" placeholder="请您输入密保答案" />
					</el-form-item>
					<div class="register-btn">
						<div class="register-btn1">
							<button type="button" class="r-btn" @click="validateSecurity()">下一步</button>
						</div>
						<div class="register-btn2">
							<div class="r-login" @click="close()">已有账号，直接登录</div>
						</div>
					</div>
				</div>
            </el-form>
			<el-form v-if="pageFlag=='security3'" class="rgs-form animate__animated animate__" :model="ruleForm">
				<div class="rgs-form2">
					<div class="title">基于HarmonyOS智能教育辅导平台的设计与实现密码重置</div>
					<el-form-item class="list-item">
						<div class="lable">新密码</div>
						<el-input v-model="ruleForm.newpassword" autocomplete="off" type="password" placeholder="请您输入新密码" />
					</el-form-item>
					<el-form-item class="list-item">
						<div class="lable">确认密码</div>
						<el-input v-model="ruleForm.repassword" autocomplete="off" type="password" placeholder="请您确认新密码" />
					</el-form-item>
					<div class="register-btn">
						<div class="register-btn1">
							<button type="button" class="r-btn" @click="updatePassword()">修改密码</button>
						</div>
						<div class="register-btn2">
							<div class="r-login" @click="close()">已有账号，直接登录</div>
						</div>
					</div>
				</div>
			</el-form>
			<el-form v-if="pageFlag=='register'" ref="ruleForm" class="rgs-form animate__animated animate__" :model="ruleForm" :rules="rules">
				<div class="rgs-form2">
					<div class="title">基于HarmonyOS智能教育辅导平台的设计与实现</div>
					<el-form-item class="list-item" v-if="tableName=='xuesheng'">
						<div class="lable" :class="changeRules('xuehao')?'required':''">学号：</div>
						<el-input  v-model="ruleForm.xuehao" :readonly="ro.xuehao" autocomplete="off" placeholder="学号"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='xuesheng'">
						<div class="lable" :class="changeRules('mima')?'required':''">密码：</div>
						<el-input  v-model="ruleForm.mima" :readonly="ro.mima" autocomplete="off" placeholder="密码"  type="password"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='xuesheng'">
						<div class="lable" :class="changeRules('mima')?'required':''">确认密码：</div>
						<el-input  v-model="ruleForm.mima2" autocomplete="off" placeholder="确认密码" type="password" :readonly="ro.mima" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='xuesheng'">
						<div class="lable" :class="changeRules('xingming')?'required':''">姓名：</div>
						<el-input  v-model="ruleForm.xingming" :readonly="ro.xingming" autocomplete="off" placeholder="姓名"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='xuesheng'">
						<div class="lable" :class="changeRules('touxiang')?'required':''">头像：</div>
						<file-upload
							tip="点击上传头像"
							action="file/upload"
							:limit="3"
							:multiple="true"
							:fileUrls="ruleForm.touxiang?ruleForm.touxiang:''"
							@change="xueshengtouxiangUploadChange"
						></file-upload>
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='xuesheng'">
						<div class="lable" :class="changeRules('xingbie')?'required':''">性别：</div>
						<el-select filterable v-model="ruleForm.xingbie" placeholder="请选择性别" :disabled="ro.xingbie">
							<el-option
								v-for="(item,index) in xueshengxingbieOptions"
								v-bind:key="index"
								:label="item"
								:value="item">
							</el-option>
						</el-select>
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='xuesheng'">
						<div class="lable" :class="changeRules('nianling')?'required':''">年龄：</div>
						<el-input  v-model.number="ruleForm.nianling" :readonly="ro.nianling" autocomplete="off" placeholder="年龄"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='xuesheng'">
						<div class="lable" :class="changeRules('mubiaoyuanxiao')?'required':''">目标院校：</div>
						<el-input  v-model="ruleForm.mubiaoyuanxiao" :readonly="ro.mubiaoyuanxiao" autocomplete="off" placeholder="目标院校"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='xuesheng'">
						<div class="lable" :class="changeRules('banji')?'required':''">班级：</div>
						<el-input  v-model="ruleForm.banji" :readonly="ro.banji" autocomplete="off" placeholder="班级"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='xuesheng'">
						<div class="lable" :class="changeRules('beikaokemu')?'required':''">备考科目：</div>
						<el-input  v-model="ruleForm.beikaokemu" :readonly="ro.beikaokemu" autocomplete="off" placeholder="备考科目"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='xuesheng'">
						<div class="lable" :class="changeRules('jichushuiping')?'required':''">基础水平：</div>
						<el-input  v-model="ruleForm.jichushuiping" :readonly="ro.jichushuiping" autocomplete="off" placeholder="基础水平"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item email" v-if="tableName=='xuesheng'">
						<div class="lable" :class="changeRules('mobile')?'required':''">手机号码：</div>
						<div style="display: flex;flex: 1;">
							<input v-model="ruleForm.mobile" autocomplete="off" placeholder="手机号"/>
							<button v-if="isEndFlag" type="success" class="register-code" @click="sendsmscode()">发送验证码</button>
							<button v-if="!isEndFlag" type="success" class="register-code" disabled="disabled">{{SecondToDate}}</button>
						</div>
					</el-form-item>
					<el-form-item class="list-item email-code" v-if="tableName=='xuesheng'">
						<div class="lable" :class="changeRules('mobile')?'required':''">验证码：</div>
						<el-input  v-model="smscode" autocomplete="off" placeholder="验证码" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='xuesheng'">
						<div class="lable" :class="changeRules('pquestion')?'required':''">密保问题：</div>
						<el-input  v-model="ruleForm.pquestion" :readonly="ro.pquestion" autocomplete="off" placeholder="密保问题"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='xuesheng'">
						<div class="lable" :class="changeRules('panswer')?'required':''">密保答案：</div>
						<el-input  v-model="ruleForm.panswer" :readonly="ro.panswer" autocomplete="off" placeholder="密保答案"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='jiaoshi'">
						<div class="lable" :class="changeRules('gonghao')?'required':''">工号：</div>
						<el-input  v-model="ruleForm.gonghao" :readonly="ro.gonghao" autocomplete="off" placeholder="工号"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='jiaoshi'">
						<div class="lable" :class="changeRules('mima')?'required':''">密码：</div>
						<el-input  v-model="ruleForm.mima" :readonly="ro.mima" autocomplete="off" placeholder="密码"  type="password"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='jiaoshi'">
						<div class="lable" :class="changeRules('mima')?'required':''">确认密码：</div>
						<el-input  v-model="ruleForm.mima2" autocomplete="off" placeholder="确认密码" type="password" :readonly="ro.mima" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='jiaoshi'">
						<div class="lable" :class="changeRules('jiaoshixingming')?'required':''">教师姓名：</div>
						<el-input  v-model="ruleForm.jiaoshixingming" :readonly="ro.jiaoshixingming" autocomplete="off" placeholder="教师姓名"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='jiaoshi'">
						<div class="lable" :class="changeRules('touxiang')?'required':''">头像：</div>
						<file-upload
							tip="点击上传头像"
							action="file/upload"
							:limit="3"
							:multiple="true"
							:fileUrls="ruleForm.touxiang?ruleForm.touxiang:''"
							@change="jiaoshitouxiangUploadChange"
						></file-upload>
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='jiaoshi'">
						<div class="lable" :class="changeRules('zizhizhengming')?'required':''">资质证明：</div>
						<file-upload
							tip="点击上传资质证明"
							action="file/upload"
							:limit="3"
							:type="3"
							:multiple="true"
							:fileUrls="ruleForm.zizhizhengming?ruleForm.zizhizhengming:''"
							@change="jiaoshizizhizhengmingUploadChange"
						></file-upload>
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='jiaoshi'">
						<div class="lable" :class="changeRules('xingbie')?'required':''">性别：</div>
						<el-select filterable v-model="ruleForm.xingbie" placeholder="请选择性别" :disabled="ro.xingbie">
							<el-option
								v-for="(item,index) in jiaoshixingbieOptions"
								v-bind:key="index"
								:label="item"
								:value="item">
							</el-option>
						</el-select>
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='jiaoshi'">
						<div class="lable" :class="changeRules('nianling')?'required':''">年龄：</div>
						<el-input  v-model.number="ruleForm.nianling" :readonly="ro.nianling" autocomplete="off" placeholder="年龄"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='jiaoshi'">
						<div class="lable" :class="changeRules('zhicheng')?'required':''">职称：</div>
						<el-input  v-model="ruleForm.zhicheng" :readonly="ro.zhicheng" autocomplete="off" placeholder="职称"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='jiaoshi'">
						<div class="lable" :class="changeRules('shanzhangkemu')?'required':''">擅长科目：</div>
						<el-input  v-model="ruleForm.shanzhangkemu" :readonly="ro.shanzhangkemu" autocomplete="off" placeholder="擅长科目"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='jiaoshi'">
						<div class="lable" :class="changeRules('jiaoxuefengge')?'required':''">教学风格：</div>
						<el-input  v-model="ruleForm.jiaoxuefengge" :readonly="ro.jiaoxuefengge" autocomplete="off" placeholder="教学风格"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item email" v-if="tableName=='jiaoshi'">
						<div class="lable" :class="changeRules('mobile')?'required':''">手机号码：</div>
						<div style="display: flex;flex: 1;">
							<input v-model="ruleForm.mobile" autocomplete="off" placeholder="手机号"/>
							<button v-if="isEndFlag" type="success" class="register-code" @click="sendsmscode()">发送验证码</button>
							<button v-if="!isEndFlag" type="success" class="register-code" disabled="disabled">{{SecondToDate}}</button>
						</div>
					</el-form-item>
					<el-form-item class="list-item email-code" v-if="tableName=='jiaoshi'">
						<div class="lable" :class="changeRules('mobile')?'required':''">验证码：</div>
						<el-input  v-model="smscode" autocomplete="off" placeholder="验证码" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='jiaoshi'">
						<div class="lable" :class="changeRules('pquestion')?'required':''">密保问题：</div>
						<el-input  v-model="ruleForm.pquestion" :readonly="ro.pquestion" autocomplete="off" placeholder="密保问题"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='jiaoshi'">
						<div class="lable" :class="changeRules('panswer')?'required':''">密保答案：</div>
						<el-input  v-model="ruleForm.panswer" :readonly="ro.panswer" autocomplete="off" placeholder="密保答案"  type="text"  />
					</el-form-item>
					<div class="register-btn">
						<div class="register-btn1">
							<button type="button" class="r-btn" @click="login()">注册</button>
						</div>
						<div class="register-btn2">
							<div class="r-login" @click="close()">已有账号，直接登录</div>
						</div>
					</div>
				</div>
			</el-form>
		</div>
	</div>
</template>

<script>
	import 'animate.css'
export default {
	data() {
		return {
			ruleForm: {
			},
			forgetForm: {},
            pageFlag : '',
			tableName:"",
			rules: {},
			ro: {},
            smscode:'',
			// 倒计时时间
			count: 60,
			// 倒计时定时器
			inter: null,
			// 倒计时是否结束
			isEndFlag: true,
            xueshengxingbieOptions: [],
            jiaoshixingbieOptions: [],
			indexBgUrl: '',
		};
	},
	computed: {
		SecondToDate: function() {
			var time = this.count;
			if (null != time && "" != time) {
				time = parseInt(time) + "秒后重发";
			}
			return time;
		}
	},
	mounted(){
		this.pageFlag = this.$route.query.pageFlag
		if(this.$route.query.pageFlag=='register'){
			
			let table = this.$storage.get("loginTable");
			this.tableName = table;
			if(this.tableName=='xuesheng'){
				this.ruleForm = {
					xuehao: '',
					mima: '',
					xingming: '',
					touxiang: '',
					xingbie: '',
					nianling: '',
					mubiaoyuanxiao: '',
					banji: '',
					beikaokemu: '',
					jichushuiping: '',
					mobile: '',
					pquestion: '',
					panswer: '',
					status: '',
				}
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
			if(this.tableName=='jiaoshi'){
				this.ruleForm = {
					gonghao: '',
					mima: '',
					jiaoshixingming: '',
					touxiang: '',
					zizhizhengming: '',
					xingbie: '',
					nianling: '',
					zhicheng: '',
					shanzhangkemu: '',
					jiaoxuefengge: '',
					fudaochengguo: '',
					sfsh: '',
					shhf: '',
					mobile: '',
					pquestion: '',
					panswer: '',
					status: '',
				}
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
			if ('xuesheng' == this.tableName) {
				this.rules.xuehao = [{ required: true, message: '请输入学号', trigger: 'blur' }]
			}
			if ('xuesheng' == this.tableName) {
				this.rules.mima = [{ required: true, message: '请输入密码', trigger: 'blur' }]
			}
			if ('xuesheng' == this.tableName) {
				this.rules.xingming = [{ required: true, message: '请输入姓名', trigger: 'blur' }]
			}
			if ('jiaoshi' == this.tableName) {
				this.rules.gonghao = [{ required: true, message: '请输入工号', trigger: 'blur' }]
			}
			if ('jiaoshi' == this.tableName) {
				this.rules.mima = [{ required: true, message: '请输入密码', trigger: 'blur' }]
			}
			if ('jiaoshi' == this.tableName) {
				this.rules.jiaoshixingming = [{ required: true, message: '请输入教师姓名', trigger: 'blur' }]
			}
			if ('jiaoshi' == this.tableName) {
				this.rules.zizhizhengming = [{ required: true, message: '请输入资质证明', trigger: 'blur' }]
			}
			if ('jiaoshi' == this.tableName) {
				this.rules.zhicheng = [{ required: true, message: '请输入职称', trigger: 'blur' }]
			}
			this.xueshengxingbieOptions = "男,女".split(',')
			this.jiaoshixingbieOptions = "男,女".split(',')
		}
	},
	created() {
		this.$http.get('config/info?name=bRegisterBackgroundImg',).then(rs=>{this.indexBgUrl = rs.data.data?rs.data.data.value:''})
	},
	destroyed() {
		  	},
	methods: {
		changeRules(name){
			if(this.rules[name]){
				return true
			}
			if(name == 'pquestion'||name=='panswer') {
				return true
			}
			return false
		},
		// 获取uuid
		getUUID () {
			return new Date().getTime();
		},
		close(){
			this.$router.push({ path: "/login" });
		},
        xueshengtouxiangUploadChange(fileUrls) {
            this.ruleForm.touxiang = fileUrls;
        },
        jiaoshitouxiangUploadChange(fileUrls) {
            this.ruleForm.touxiang = fileUrls;
        },
        jiaoshizizhizhengmingUploadChange(fileUrls) {
            this.ruleForm.zizhizhengming = fileUrls;
        },

        // 多级联动参数

		sendsmscode() {
			if(!this.ruleForm.mobile){
				this.$message.error(`手机号码不能为空`);
				return
			}
			if(this.ruleForm.mobile&&(!this.$validate.isMobile(this.ruleForm.mobile))){
				this.$message.error(`请输入正确的手机格式`);
				return
			}
			this.isEndFlag = false;
			var _this = this;
			this.inter = window.setInterval(function() {
				_this.count = _this.count - 1;
				if (_this.count <= 0) {
					window.clearInterval(_this.inter);
					_this.isEndFlag = true;
					_this.count=60;
				}
			}, 1000);
			this.$http({
				url: `${this.tableName}/sendsms?mobile=`+this.ruleForm.mobile,
				method: "get",
				params: {}
			}).then(({ data }) => {
				if (data && data.code === 0) {
					this.$message.success(`发送成功`);
				} else {
					this.$message.error(data.msg)
				}
			});
		},

        //获取密保问题
        getSecurity(){
            this.tableName = 'jiaoshi'
            if(!this.tableName) {
                this.$message.error("请选择角色");
                return false
            }
            if(!this.forgetForm.username){
                this.$message.error("请输入账号");
                return false
            }
            this.$http({
                url: this.tableName+`/security?username=` + this.forgetForm.username,
                method: "get",
            }).then(({
                data
            }) => {
                if (data.data && data.code === 0) {
                    this.ruleForm = data.data;
                    this.pageFlag="security2"
                } else {
                    if(!data.data) {
                        this.$message.error("用户不存在");
                    } else {
                        this.$message.error(data.msg);
                    }
                }
            });
        },
        //验证密保问题
        validateSecurity(){
            let that = this
            if(this.ruleForm.panswer!=this.ruleForm.myanswer){
                this.$message.error("答案输入不正确");
                return false
            }
            this.$message.success("答案正确");
            setTimeout(()=>{
                this.pageFlag="security3";
            },1000)
        },
        // 修改密码
        updatePassword() {
            if (this.ruleForm.newpassword != this.ruleForm.repassword) {
                this.$message.error("两次密码输入不一致");
                return;
            }
            this.ruleForm.mima = this.ruleForm.newpassword;
            this.ruleForm.password = this.ruleForm.newpassword;
            this.$http({
                url: this.tableName+`/update`,
                method: "post",
                data: this.ruleForm
            }).then(({
                data
            }) => {
                if (data && data.code === 0) {
                    this.$message({
                        message: "修改密码成功",
                        type: "success",
                        duration: 1500,
                        onClose: () => {}
                    });
                    this.close()
                } else {
                    this.$message.error(data.msg);
                }
            });
        },
		// 注册
		login() {
			var url=this.tableName+"/register";
			if((!this.ruleForm.xuehao) && `xuesheng` == this.tableName){
				this.$message.error(`学号不能为空`);
				return
			}
			if((!this.ruleForm.mima) && `xuesheng` == this.tableName){
				this.$message.error(`密码不能为空`);
				return
			}
			if((this.ruleForm.mima!=this.ruleForm.mima2) && `xuesheng` == this.tableName){
				this.$message.error(`两次密码输入不一致`);
				return
			}
			if((!this.ruleForm.xingming) && `xuesheng` == this.tableName){
				this.$message.error(`姓名不能为空`);
				return
			}
            if(this.ruleForm.touxiang!=null) {
                this.ruleForm.touxiang = this.ruleForm.touxiang.replace(new RegExp(this.$base.url,"g"),"");
            }
			if(`xuesheng` == this.tableName && this.ruleForm.nianling &&(!this.$validate.isIntNumer(this.ruleForm.nianling))){
				this.$message.error(`年龄应输入整数`);
				return
			}
			if(`xuesheng` == this.tableName && this.ruleForm.mobile &&(!this.$validate.isMobile(this.ruleForm.mobile))){
				this.$message.error(`手机号应输入手机格式`);
				return
			}
			if(`xuesheng` == this.tableName ){
				url=this.tableName+"/register?smscode="+this.smscode;
				if(this.ruleForm.mobile&&(!this.$validate.isMobile(this.ruleForm.mobile))){
					this.$message.error(`请输入正确的手机格式`);
					return
				}
			}
			if((!this.smscode) && `xuesheng` == this.tableName){
				this.$message.error(`验证码不能为空`);
				return
			}
			if(`xuesheng` == this.tableName && this.ruleForm.pquestion==''){
				this.$message.error(`密保问题不能为空`);
				return
			}
			if(`xuesheng` == this.tableName && this.ruleForm.panswer==''){
				this.$message.error(`密保答案不能为空`);
				return
			}
			  if(this.tableName=='jiaoshi'){
				  this.ruleForm.sfsh = '待审核'
			  }
			if((!this.ruleForm.gonghao) && `jiaoshi` == this.tableName){
				this.$message.error(`工号不能为空`);
				return
			}
			if((!this.ruleForm.mima) && `jiaoshi` == this.tableName){
				this.$message.error(`密码不能为空`);
				return
			}
			if((this.ruleForm.mima!=this.ruleForm.mima2) && `jiaoshi` == this.tableName){
				this.$message.error(`两次密码输入不一致`);
				return
			}
			if((!this.ruleForm.jiaoshixingming) && `jiaoshi` == this.tableName){
				this.$message.error(`教师姓名不能为空`);
				return
			}
            if(this.ruleForm.touxiang!=null) {
                this.ruleForm.touxiang = this.ruleForm.touxiang.replace(new RegExp(this.$base.url,"g"),"");
            }
            if(this.ruleForm.zizhizhengming!=null) {
                this.ruleForm.zizhizhengming = this.ruleForm.zizhizhengming.replace(new RegExp(this.$base.url,"g"),"");
            }
			if((!this.ruleForm.zizhizhengming) && `jiaoshi` == this.tableName){
				this.$message.error(`资质证明不能为空`);
				return
			}
			if(`jiaoshi` == this.tableName && this.ruleForm.nianling &&(!this.$validate.isIntNumer(this.ruleForm.nianling))){
				this.$message.error(`年龄应输入整数`);
				return
			}
			if((!this.ruleForm.zhicheng) && `jiaoshi` == this.tableName){
				this.$message.error(`职称不能为空`);
				return
			}
			if(`jiaoshi` == this.tableName && this.ruleForm.mobile &&(!this.$validate.isMobile(this.ruleForm.mobile))){
				this.$message.error(`手机号应输入手机格式`);
				return
			}
			if(`jiaoshi` == this.tableName ){
				url=this.tableName+"/register?smscode="+this.smscode;
				if(this.ruleForm.mobile&&(!this.$validate.isMobile(this.ruleForm.mobile))){
					this.$message.error(`请输入正确的手机格式`);
					return
				}
			}
			if((!this.smscode) && `jiaoshi` == this.tableName){
				this.$message.error(`验证码不能为空`);
				return
			}
			if(`jiaoshi` == this.tableName && this.ruleForm.pquestion==''){
				this.$message.error(`密保问题不能为空`);
				return
			}
			if(`jiaoshi` == this.tableName && this.ruleForm.panswer==''){
				this.$message.error(`密保答案不能为空`);
				return
			}
			this.$http({
				url: url,
				method: "post",
				data:this.ruleForm
			}).then(({ data }) => {
				if (data && data.code === 0) {
					this.$message({
						message: "注册成功",
						type: "success",
						duration: 1500,
						onClose: () => {
							this.$router.replace({ path: "/login" });
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
.register-container {
	position: relative;
	background: url(http://codegen.caihongy.cn/20250728/e9d2bf554354459eb9cd5cd17b0ea7ec.jpg);
	background-repeat: no-repeat !important;
	background-size: cover !important;
	background: url(http://codegen.caihongy.cn/20250728/e9d2bf554354459eb9cd5cd17b0ea7ec.jpg);
	display: flex;
	width: 100%;
	min-height: 100vh;
	justify-content: center;
	align-items: center;
	background-position: center bottom;
	.rgs-form {
		.rgs-form2 {
		border-radius: 30px;
		box-shadow: inset -20px -20px 5px 0px #A1B6F3, 60px 60px 60px 0px rgba(62,113,228,0.5);
		padding: 10px 15% 30px;
		backdrop-filter: blur(0px);
		margin: 20px auto;
		background: linear-gradient( 133deg, #F1F4FB 0%, #EBEFFA 100%);
		width: 100%;
		}
		border-radius: 10px;
		padding: 40px 0px 20px;
		margin: 0 auto;
		z-index: 1;
		background: none;
		display: flex;
		width: 680px;
		flex-wrap: wrap;
		height: auto;
		.title {
			margin: 10px 0 20px -20%;
			text-shadow: none;
			color: #5893FA;
			font-weight: 600;
			width: 140%;
			font-size: 20px;
			line-height: 44px;
			text-align: center;
		}
		.list-item {
			border-radius: 30px;
			padding: 0 0 0 130px;
			box-shadow: inset 0px -3px 2px 0px #b9cafb;
			margin: 0 auto 15px;
			background: #fff;
			width: 100%;
			position: relative;
			height: auto;
			/deep/ .el-form-item__content {
				display: block;
			}
			.lable {
				padding: 0 10px 0 0;
				color: #333;
				left: -130px;
				width: 130px;
				font-size: 16px;
				line-height: 44px;
				position: absolute !important;
				text-align: right;
			}
			.el-input {
				width: 100%;
			}
			.el-input /deep/ .el-input__inner {
				border-radius: 0px;
				padding: 0 10px;
				color: #333;
				background: none;
				width: 100%;
				font-size: 16px;
				border-color: #d1d1d1;
				border-width: 0 0 0px;
				border-style: solid;
				height: 44px;
			}
			.el-input /deep/ .el-input__inner:focus {
				border-radius: 0px;
				padding: 0 10px;
				color: #4c7bf5;
				background: none;
				width: 100%;
				font-size: 16px;
				border-color: #4c7bf5;
				border-width: 0 0 0px;
				border-style: solid;
				height: 44px;
			}
			.el-input-number {
				width: 100%;
			}
			.el-input-number /deep/ .el-input__inner {
				text-align: center;
				border-radius: 0px;
				padding: 0 10px;
				color: #333;
				background: none;
				width: 100%;
				font-size: 16px;
				border-color: #d1d1d1;
				border-width: 0 0 0px;
				border-style: solid;
				height: 44px;
			}
			.el-input-number /deep/ .el-input__inner:focus {
				border-radius: 0px;
				padding: 0 10px;
				color: #4c7bf5;
				background: none;
				width: 100%;
				font-size: 16px;
				border-color: #4c7bf5;
				border-width: 0 0 0px;
				border-style: solid;
				height: 44px;
			}
			.el-input-number /deep/ .el-input-number__decrease {
				display: none;
			}
			.el-input-number /deep/ .el-input-number__increase {
				display: none;
			}
			.el-select {
				width: 100%;
			}
			.el-select /deep/ .el-input__inner {
				border-radius: 0px;
				padding: 0 10px;
				color: #999;
				background: none;
				width: 100%;
				font-size: 16px;
				border-color: #d1d1d1;
				border-width: 0 0 0px;
				border-style: solid;
				height: 44px;
			}
			.el-select /deep/ .el-input__inner:focus {
				border-radius: 0px;
				padding: 0 10px;
				color: #4c7bf5;
				background: none;
				width: 100%;
				font-size: 16px;
				border-color: #4c7bf5;
				border-width: 0 0 0px;
				border-style: solid;
				height: 44px;
			}
			.el-date-editor {
				width: 100%;
			}
			.el-date-editor /deep/ .el-input__inner {
				border-radius: 0px;
				padding: 0 10px 0 30px;
				color: #999;
				background: none;
				width: 100%;
				font-size: 16px;
				border-color: #d1d1d1;
				border-width: 0 0 0px;
				border-style: solid;
				height: 44px;
			}
			.el-date-editor /deep/ .el-input__inner:focus {
				border-radius: 0px;
				padding: 0 10px 0 30px;
				color: #4c7bf5;
				background: none;
				width: 100%;
				font-size: 16px;
				border-color: #4c7bf5;
				border-width: 0 0 0px;
				border-style: solid;
				height: 44px;
			}
			.el-date-editor.el-input {
				width: 100%;
			}
			/deep/ .el-upload--picture-card {
				background: transparent;
				border: 0;
				border-radius: 0;
				width: auto;
				height: auto;
				line-height: initial;
				vertical-align: middle;
			}
			/deep/ .upload .upload-img {
				border: 0px solid #ddd;
				cursor: pointer;
				border-radius: 4px;
				box-shadow: inset 0px 2px 3px 0px  #497afa30,inset 0px 0px 3px 0px  #497afa50;
				margin: 5px 0 0;
				color: #5893FA;
				width: 80px;
				font-size: 26px;
				line-height: 60px;
				text-align: center;
				height: 60px;
			}
			/deep/ .el-upload-list .el-upload-list__item {
				border: 0px solid #ddd;
				cursor: pointer;
				border-radius: 4px;
				box-shadow: inset 0px 2px 3px 0px  #497afa30,inset 0px 0px 3px 0px  #497afa50;
				margin: 5px 0 0;
				color: #5893FA;
				width: 80px;
				font-size: 26px;
				line-height: 60px;
				text-align: center;
				height: 60px;
			}
			/deep/ .el-upload .el-icon-plus {
				border: 0px solid #ddd;
				cursor: pointer;
				border-radius: 4px;
				box-shadow: inset 0px 2px 3px 0px  #497afa30,inset 0px 0px 3px 0px  #497afa50;
				margin: 5px 0 0;
				color: #5893FA;
				width: 80px;
				font-size: 26px;
				line-height: 60px;
				text-align: center;
				height: 60px;
			}
			/deep/ .el-upload__tip {
				color: #666;
				font-size: 16px;
			}
			/deep/ .el-input__inner::placeholder {
				color: #999;
				font-size: 16px;
			}
			.required {
				position: relative;
			}
			.required::after{
				color: red;
				left: 120px;
				position: absolute;
				content: "*";
			}
			.editor {
				margin: 10px 20px 20px;
				width: calc(100% - 40px);
				height: auto;
			}
			.editor>.avatar-uploader {
				line-height: 0;
				height: 0;
			}
		}
		.list-item.email {
			input {
				border-radius: 0px;
				padding: 0 10px;
				color: #333;
				flex: 1;
				background: none;
				width: 100%;
				font-size: 16px;
				border-color: #d1d1d1;
				border-width: 0 0 0px;
				border-style: solid;
				height: 44px;
			}
			input:focus {
				border-radius: 0px;
				padding: 0 10px;
				color: #4c7bf5;
				flex: 1;
				background: none;
				width: 100%;
				font-size: 16px;
				border-color: #4c7bf5;
				border-width: 0 0 0px;
				border-style: solid;
				height: 44px;
			}
			input::placeholder {
				color: #999;
				font-size: 16px;
			}
			button {
				border: 0;
				cursor: pointer;
				padding: 0 10px 0 0;
				margin: 0;
				color: #333;
				font-size: 16px;
				border-color: #d1d1d1;
				border-radius: 0 0px 0px 0;
				box-shadow: none;
				outline: none;
				background: none;
				width: 130px;
				border-width: 0 0 0px;
				border-style: solid;
				height: 44px;
			}
			button:hover {
				color: #4c7bf5;
			}
		}
		.register-btn {
			width: 100%;
		}
		.register-btn1 {
			padding: 0 0 0 0px;
			width: 100%;
		}
		.register-btn2 {
			padding: 0;
			margin: 10px 0;
			width: 100%;
			text-align: center;
		}
		.r-btn {
			border: 0;
			cursor: pointer;
			border-radius: 8px;
			padding: 0 24px;
			box-shadow: inset 0px -4px 4px 0px #8aa3ed;
			margin: 0 0 10px;
			outline: none;
			color: #fff;
			background: #497afa;
			width: 100%;
			font-size: 22px;
			height: 50px;
		}
		.r-btn:hover {
			opacity: 0.5;
		}
		.r-login {
			cursor: pointer;
			padding: 0 10%;
			color: #333;
			display: inline-block;
			font-size: 16px;
			line-height: 2;
		}
		.r-login:hover {
			opacity: 0.8;
		}
	}
}
	
	::-webkit-scrollbar {
	  display: none;
	}
</style>
