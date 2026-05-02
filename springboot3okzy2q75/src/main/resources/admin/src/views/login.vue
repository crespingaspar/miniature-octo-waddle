<template>
	<div>
		<div class="login-container" :style="{'backgroundImage': indexBgUrl?`url(${$base.url + indexBgUrl})`:''}">
			<el-form class="login_form animate__animated animate__">
				<div class="login_form2">
					<div class="title-container">基于HarmonyOS智能教育辅导平台的设计与实现登录</div>
					<div v-if="loginType==1" class="list-item">
						<div class="lable">
							账号：
						</div>
						<input placeholder="请输入账号：" name="username" type="text" v-model="rulesForm.username">
					</div>
					<div v-if="loginType==4" class="list-item">
						<div class="lable">
							手机号
						</div>
						<input placeholder="请输入手机号" name="phone" type="text" v-model="phone">
					</div>
					<div class="list-code" v-if="loginType==4">
						<div class="lable">
							手机验证码
						</div>
						<input placeholder="请输入手机验证码" name="code" type="text" v-model="phonecode">
						<div v-if="isEndFlag" class="getCodeBt" @click="phoneClick">
							发送验证码
						</div>
						<div v-if="!isEndFlag" class="getCodeBt">
							{{emailText}}
						</div>
					</div>
					<div class="list-item select" v-if="phoneroles.length>1&&loginType==4">
						<div class="lable">
							角色：
						</div>
						<el-select v-model="rulesForm.role" placeholder="请选择角色：">
							<el-option v-if="item.role!='users'" v-for="item in phoneroles" :key="item.roleName" :label="item.roleName" :value="item.roleName" />
						</el-select>
					</div>
					<div v-if="loginType==1" class="list-item">
						<div class="lable">
							密码：
						</div>
						<div class="password-box">
							<input placeholder="请输入密码：" name="password" :type="showPassword?'text':'password'" v-model="rulesForm.password">
							<span class="icon iconfont" :class="showPassword?'icon-liulan13':'icon-liulan17'" @click="showPassword=!showPassword"></span>
						</div>
					</div>

					<div class="list-item select" v-if="roles.length>1&&loginType<=2">
						<div class="lable">
							角色：
						</div>
						<el-select v-model="rulesForm.role" placeholder="请选择角色：">
							<el-option v-if="loginType==1||(loginType==2&&item.role!='users')" v-for="item in roles" :key="item.roleName" :label="item.roleName" :value="item.roleName" />
						</el-select>
					</div>

		
					<div class="login-btn">
						<div class="login-btn1">
							<el-button v-if="loginType==1||loginType==3||loginType==4" type="primary" @click="login()" class="loginInBt">登录</el-button>
						</div>
						<div class="login-btn2">
							<el-button type="primary" @click="register('jiaoshi')" class="register">
								注册教师							</el-button>
						</div>
						<div class="login-btn3">
							<el-button type="primary" @click="updatePassword()" class="forget">忘记密码，点击找回?</el-button>
							<el-button v-if="loginType==1" @click="loginType=4;rulesForm.role=''" type="primary" class="forget">手机号登录</el-button>
							<el-button v-if="loginType==2||loginType==3||loginType==4" @click="changePasswordLogin" type="primary" class="forget">账号密码登录</el-button>
						</div>
					</div>
				</div>
			</el-form>
		</div>
	</div>
</template>
<script>
	import 'animate.css'
	import menu from "@/utils/menu";
	export default {
		data() {
			return {
				verifyCheck2: false,
				flag: false,
				baseUrl:this.$base.url,
				loginType: 1,
				rulesForm: {
					username: "",
					password: "",
					role: "",
				},
				phone: '',
				phonecode: '',
				phoneroles: [
					{
						tableName: 'jiaoshi',
						roleName: '教师'
					},
				],
				// 倒计时时间
				count: 60,
				// 倒计时定时器
				inter: null,
				// 倒计时是否结束
				isEndFlag: true,
				menus: [],
				roles: [],
				tableName: "",
				showPassword: false,
				indexBgUrl: '',
			};
		},
		mounted() {
			let menus = menu.list();
			this.menus = menus;

			for (let i = 0; i < this.menus.length; i++) {
				if (this.menus[i].hasBackLogin=='是') {
					this.roles.push(this.menus[i])
				}
			}

		},
		created() {
			this.$http.get('config/info?name=bLoginBackgroundImg',).then(rs=>{this.indexBgUrl = rs.data.data?rs.data.data.value:''})
		},
		destroyed() {
		},
		components: {
		},
		computed: {
			emailText: function() {
				var time = this.count;
				if (null != time && "" != time) {
					time = parseInt(time) + "秒后重发";
				}
				return time;
			}
		},
		methods: {
			phoneClick() {
				if(!this.phone){
					this.$message.error(`手机号码不能为空`);
					return
				}
				if(this.phone&&(!this.$validate.isMobile(this.phone))){
					this.$message.error(`请输入正确的手机格式`);
					return
				}
				if(this.phoneroles.length>1) {
					if (!this.rulesForm.role) {
						this.$message.error("请选择角色");
						return;
					}
				
					for (let i = 0; i < this.phoneroles.length; i++) {
						if (this.phoneroles[i].roleName == this.rulesForm.role) {
							this.tableName = this.phoneroles[i].tableName;
						}
					}
				} else {
					this.tableName = this.phoneroles[0].tableName;
					this.rulesForm.role = this.phoneroles[0].roleName;
				}
				var _this = this;
				this.$http({
					url: `${this.tableName}/sendsms/login?mobile=`+this.phone,
					method: "get",
					params: {}
				}).then(({ data }) => {
					if (data && data.code === 0) {
						this.isEndFlag = false;
						this.inter = window.setInterval(function() {
							_this.count = _this.count - 1;
							if (_this.count <= 0) {
								window.clearInterval(_this.inter);
								_this.isEndFlag = true;
								_this.count=60;
							}
						}, 1000);
						this.$message.success(`发送成功`);
					} else {
						this.$message.error(data.msg)
					}
				});
			},

			//重置密码
			updatePassword(){
				this.$storage.set("loginTable", "");
				this.$router.push({path:'/register',query:{pageFlag:'security1'}})
			},
			//注册
			register(tableName){
				this.$storage.set("loginTable", tableName);
				this.$router.push({path:'/register',query:{pageFlag:'register'}})
			},
			// 登陆
			login() {
				if(this.loginType==1) {

					if (!this.rulesForm.username) {
						this.$message.error("请输入用户名");
						return;
					}
					if (!this.rulesForm.password) {
						this.$message.error("请输入密码");
						return;
					}
					if(this.roles.length>1) {
						if (!this.rulesForm.role) {
							this.$message.error("请选择角色");
							return;
						}
					
						for (let i = 0; i < this.roles.length; i++) {
							if (this.roles[i].roleName == this.rulesForm.role) {
								this.tableName = this.roles[i].tableName;
							}
						}
					} else {
						this.tableName = this.roles[0].tableName;
						this.rulesForm.role = this.roles[0].roleName;
					}
				}
				if(this.loginType==4) {
					if (!this.phone) {
						this.$message.error("请输入手机号");
						return;
					}
					if (!this.phonecode) {
						this.$message.error("请输入手机验证码");
						return;
					}
					if(this.phoneroles.length>1) {
						if (!this.rulesForm.role) {
							this.$message.error("请选择角色");
							return;
						}
					
						for (let i = 0; i < this.phoneroles.length; i++) {
							if (this.phoneroles[i].roleName == this.rulesForm.role) {
								this.tableName = this.phoneroles[i].tableName;
							}
						}
					} else {
						this.tableName = this.phoneroles[0].tableName;
						this.rulesForm.role = this.phoneroles[0].roleName;
					}
				}
		
				this.loginPost()
			},
			loginPost() {
				if(this.loginType==4) {
					this.phoneLogin()
					return false
				}
				this.$http({
					url: `${this.tableName}/login?username=${this.rulesForm.username}&password=${this.rulesForm.password}`,
					method: "post"
				}).then(({ data }) => {
					if (data && data.code === 0) {
						this.$storage.set("Token", data.token);
						this.$storage.set("role", this.rulesForm.role);
						this.$storage.set("sessionTable", this.tableName);
						this.$storage.set("adminName", this.rulesForm.username);
						this.$nextTick(()=>{
							this.$http({
								url: this.tableName + '/session',
								method: "get"
							}).then(({
								data
							}) => {
								if (data && data.code === 0) {
									if(this.tableName == 'xuesheng') {
										this.$storage.set('examName',data.data.xingming)
									}
									if(this.tableName == 'xuesheng') {
										this.$storage.set('headportrait',data.data.touxiang)
									}
									if(this.tableName == 'jiaoshi') {
										this.$storage.set('headportrait',data.data.touxiang)
									}
									if(this.tableName == 'users') {
										this.$storage.set('headportrait',data.data.image)
									}
									this.$storage.set('userForm',JSON.stringify(data.data))
									this.$storage.set('userid',data.data.id);
								} else {
									let message = this.$message
									message.error(data.msg);
								}
								this.$router.replace({ path: "/" });
							});
						})
					}
					else {
						this.$message.error(data.msg);
					}
				});
			},
			phoneLogin() {
				this.$http({
					url: `${this.tableName}/sms/login?mobile=${this.phone}&&smscode=${this.phonecode}`,
					method: "post"
				}).then(({data}) => {
					if(data && data.code === 0) {

						this.$storage.set("Token", data.token);
						this.$storage.set("role", this.rulesForm.role);
						this.$storage.set("sessionTable", this.tableName);
						this.$storage.set("adminName", data.username);
						this.$nextTick(()=>{
							this.$http({
								url: this.tableName + '/session',
								method: "get"
							}).then(({
								data
							}) => {
								if (data && data.code === 0) {
									if(this.tableName == 'xuesheng') {
										this.$storage.set('examName',data.data.xingming)
									}
									if(this.tableName == 'xuesheng') {
										this.$storage.set('headportrait',data.data.touxiang)
									}
									if(this.tableName == 'jiaoshi') {
										this.$storage.set('headportrait',data.data.touxiang)
									}
									if(this.tableName == 'users') {
										this.$storage.set('headportrait',data.data.image)
									}
									this.$storage.set('userForm',JSON.stringify(data.data))
									this.$storage.set('userid',data.data.id);
								} else {
									let message = this.$message
									message.error(data.msg);
								}
								this.$router.replace({ path: "/" });
							});
						})
					}
					else {
						this.$message.error(data.msg);
					}
				});
			},
			changePasswordLogin() {
				this.loginType = 1
				this.ruleForm.role = ''
			},
		}
	}
</script>

<style lang="scss" scoped>
.login-container {
	min-height: 100vh;
	position: relative;
	background-repeat: no-repeat;
	background-position: center center;
	background-size: cover;
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

	.login_form {
		border-radius: 10px;
		padding: 40px 0px 20px;
		margin: 0 auto;
		z-index: 1;
		background: none;
		display: flex;
		width: 680px;
		flex-wrap: wrap;
		height: auto;
		.login_form2 {
			border-radius: 30px;
			box-shadow: inset -20px -20px 5px 0px #A1B6F3, 60px 60px 60px 0px rgba(62,113,228,0.5);
			padding: 10px 15%;
			backdrop-filter: blur(0px);
			margin: 20px auto;
			background: linear-gradient( 133deg, #F1F4FB 0%, #EBEFFA 100%);
			width: 100%;
		}
		.title-container {
			margin: 10px 0 20px -30%;
			text-shadow: none;
			color: #5893FA;
			font-weight: 600;
			width: 160%;
			font-size: 20px;
			line-height: 44px;
			text-align: center;
		}
		.list-item {
			border-radius: 30px;
			padding: 0 20px;
			box-shadow: inset 0px -4px 4px 0px #8aa3ed;
			margin: 0 auto 25px;
			background: #fff;
			display: flex;
			width: 100%;
			align-items: center;
			.lable {
				color: #333;
				width: 100px;
				font-size: 16px;
				border-color: #d1d1d1;
				border-width: 0 0 0px;
				line-height: 44px;
				border-style: solid;
				text-align: right;
				height: 44px;
			}
			input {
				border-radius: 0px;
				padding: 0 10px;
				color: #333;
				background: none;
				flex: 1;
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
			.password-box {
				flex: 1;
				display: flex;
				width: 100%;
				position: relative;
				align-items: center;
				input {
					border-radius: 0px;
					padding: 0 10px;
					color: #333;
					background: none;
					flex: 1;
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
				.iconfont {
					cursor: pointer;
					z-index: 1;
					color: #000;
					top: 0;
					font-size: 16px;
					line-height: 44px;
					position: absolute;
					right: 5px;
				}
			}
			input::placeholder {
				color: #999;
				font-size: 16px;
			}
			/deep/ .el-select {
				flex: 1;
				width: 100%;
			}
			/deep/ .el-select .el-input__inner {
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
			/deep/ .el-select .is-focus .el-input__inner {
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
			/deep/ .el-select .el-input__inner::placeholder{
				color: #999;
				font-size: 16px;
			}
		}
		.list-code {
			border-radius: 30px;
			padding: 0 20px;
			box-shadow: inset 0px -4px 4px 0px #8aa3ed;
			margin: 0 auto 25px;
			background: #fff;
			display: flex;
			width: 100%;
			align-items: center;
			.lable {
				color: #333;
				width: 100px;
				font-size: 16px;
				border-color: #d1d1d1;
				border-width: 0 0 0px;
				line-height: 44px;
				border-style: solid;
				text-align: right;
				height: 44px;
			}
			input {
				border-radius: 0px 0 0 0px;
				padding: 0 10px;
				outline: none;
				color: #333;
				flex: 1;
				background: none;
				width: calc(100% - 90px);
				font-size: 16px;
				border-color: #d1d1d1;
				border-width: 0 0 0px;
				border-style: solid;
				height: 44px;
			}
			input:focus {
				border-radius: 0px 0 0 0px;
				padding: 0 10px;
				outline: none;
				color: #4c7bf5;
				flex: 1;
				background: none;
				width: calc(100% - 90px);
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
			.getCodeBt {
				cursor: pointer;
				border-radius: 0 0px 0px 0;
				background: none;
				display: flex;
				width: 90px;
				border-color: #d1d1d1;
				border-width: 0 0 0px;
				justify-content: center;
				align-items: center;
				border-style: solid;
				height: 44px;
			}
		}
		.login-btn {
			margin: 20px auto;
			display: flex;
			width: 100%;
			justify-content: center;
			align-items: center;
			flex-wrap: wrap;
			.login-btn1 {
				width: 100%;
				order: 2;
			}
			.login-btn2 {
				width: 100%;
				text-align: center;
				order: 1;
			}
			.login-btn3 {
				margin: 0 auto;
				width: 100%;
				text-align: center;
				order: 3;
			}
			.loginInBt {
				border: 0;
				cursor: pointer;
				border-radius: 8px;
				padding: 0 24px;
				box-shadow: inset 0px -4px 4px 0px #8aa3ed;
				margin: 10px auto;
				outline: none;
				color: #fff;
				background: #497afa;
				width: 100%;
				font-size: 22px;
				height: 50px;
			}
			.loginInBt:hover {
				opacity: 0.8;
			}
			.register {
				border: 0px solid #333;
				cursor: pointer;
				border-radius: 20px;
				padding: 0 10px;
				margin: 0;
				outline: none;
				color: #497afa;
				background: none;
				width: auto;
				font-size: 16px;
				height: 44px;
			}
			.register:hover {
				opacity: 0.8;
			}
			.forget {
				border: 0;
				cursor: pointer;
				border-radius: 20px;
				padding: 0 10px;
				margin: 0;
				outline: none;
				color: #497afa;
				background: none;
				width: auto;
				font-size: 15px;
				height: 44px;
			}
			.forget:hover {
				opacity: 0.8;
			}
		}
	}
}

</style>
