<template>
	<view class="content">
		<view class="login-box" :style="{'backgroundImage': indexBgUrl?`url(${$base.url + indexBgUrl})`:''}">
			<view :style='{"border":"2rpx solid #ffffff","padding":"48rpx","boxShadow":"0px 8rpx 8rpx #ccc","margin":"40rpx 0 0 0","textAlign":"center","display":"flex","justifyContent":"space-between","alignContent":"flex-start","borderRadius":"20rpx","flexWrap":"wrap","background":"#ffffff99","width":"100%","position":"relative","height":"auto"}'>
				<image :style='{"margin":"0 auto 24rpx auto","borderRadius":"20rpx","top":"-80rpx","left":"40%","display":"none","width":"160rpx","position":"absolute","height":"160rpx"}' :src="indexLogoUrl?($base.url + indexLogoUrl):'http://codegen.caihongy.cn/20241113/1569fd4127b64f1f8111e3d2417297db.png'" mode="aspectFill"></image>
				<view v-if="loginType==1" :style='{"border":"0px solid #1ccb96","margin":"0 0 48rpx 0","borderRadius":"20rpx","flexWrap":"wrap","background":"none","display":"flex","width":"calc(100% - 0px)","height":"auto"}' class="uni-form-item uni-column">
					<view :style='{"color":"#333","borderRadius":"20rpx","textAlign":"left","background":"none","width":"100%","lineHeight":"60rpx","fontSize":"28rpx","minWidth":"160rpx"}' class="label">账号：</view>
					<input v-model="username" :style='{"border":"2rpx solid #d6b8ff","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"100rpx","textAlign":"left","flex":"1","background":"#fff","fontSize":"28rpx","lineHeight":"88rpx","height":"88rpx"}' type="text" class="uni-input" name="" placeholder="请输入账号" />
				</view>
				<view v-if="loginType==1" :style='{"border":"0px solid #1ccb96","margin":"0 0 48rpx 0","borderRadius":"20rpx","flexWrap":"wrap","background":"none","display":"flex","width":"calc(100% - 0px)","height":"auto"}' class="uni-form-item uni-column">
					<view :style='{"color":"#333","borderRadius":"20rpx","textAlign":"left","background":"none","width":"100%","lineHeight":"60rpx","fontSize":"28rpx","minWidth":"160rpx"}' class="label">密码：</view>
					<input v-model="password" password :style='{"border":"2rpx solid #d6b8ff","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"100rpx","textAlign":"left","flex":"1","background":"#fff","fontSize":"28rpx","lineHeight":"88rpx","height":"88rpx"}' type="password" class="uni-input" name="" placeholder="请输入密码" />
				</view>
				<view v-if="loginType==4" :style='{"border":"0px solid #1ccb96","margin":"0 0 48rpx 0","borderRadius":"20rpx","flexWrap":"wrap","background":"none","display":"flex","width":"calc(100% - 0px)","height":"auto"}' class="uni-form-item uni-column">
					<view :style='{"color":"#333","borderRadius":"20rpx","textAlign":"left","background":"none","width":"100%","lineHeight":"60rpx","fontSize":"28rpx","minWidth":"160rpx"}' class="label">手机号：</view>
					<input v-model="phone" :style='{"border":"2rpx solid #d6b8ff","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"100rpx","textAlign":"left","flex":"1","background":"#fff","fontSize":"28rpx","lineHeight":"88rpx","height":"88rpx"}' type="text" class="uni-input" name="" placeholder="请输入手机号" />
				</view>
				<view v-if="loginType==4" class="code" :style='{"border":"0px solid #1ccb96","margin":"0 0 48rpx 0","borderRadius":"20rpx","flexWrap":"wrap","background":"none","display":"flex","width":"calc(100% - 0px)","height":"auto"}'>
					<view :style='{"color":"#333","borderRadius":"20rpx","textAlign":"left","background":"none","width":"100%","lineHeight":"60rpx","fontSize":"28rpx","minWidth":"160rpx"}' class="label">验证码：</view>
					<input :style='{"border":"2rpx solid #d6b8ff","padding":"0 24rpx","margin":"0 10rpx 0 0","color":"rgb(0, 0, 0)","borderRadius":"60rpx","textAlign":"left","background":"#fff","flex":"1","width":"calc(100% - 190rpx)","fontSize":"28rpx","lineHeight":"88rpx","height":"88rpx"}' type="text" v-model="phonecode" placeholder="请输入验证码" />
					<view v-if="isEndFlag" class="getCodeBt" :style='{"border":"0px solid #ddd","padding":"0px","margin":"0 0 0 0px","alignItems":"center","borderRadius":"60rpx","background":"#d6b8ff","borderWidth":"0px 0px 0px 0","display":"flex","width":"180rpx","lineHeight":"88rpx","justifyContent":"center","height":"88rpx"}' @click="phoneClick()">
						发送验证码
					</view>
					<view v-if="!isEndFlag" class="getCodeBt" :style='{"border":"0px solid #ddd","padding":"0px","margin":"0 0 0 0px","alignItems":"center","borderRadius":"60rpx","background":"#d6b8ff","borderWidth":"0px 0px 0px 0","display":"flex","width":"180rpx","lineHeight":"88rpx","justifyContent":"center","height":"88rpx"}'>
						{{emailText}}
					</view>
				</view>
				<view v-if="phoneroles.length>2&&loginType==4" :style='{"border":"0px solid #1ccb96","margin":"0 0 24rpx 0","borderRadius":"20rpx","flexWrap":"wrap","background":"none","display":"flex","width":"calc(100% - 0px)","height":"auto"}'>
					<view :style='{"color":"#333","borderRadius":"20rpx","textAlign":"left","background":"none","width":"100%","lineHeight":"60rpx","fontSize":"28rpx","minWidth":"160rpx"}' class="label">用户类型：</view>
					<picker @change="phonerolesChange" :value="phonerolesIndex" :range="phoneroles" range-key="roleName" :style='{"border":"2rpx solid #d6b8ff","padding":"0 20rpx","color":"#666","borderRadius":"60rpx","textAlign":"left","flex":"1","background":"#fff","fontSize":"28rpx","lineHeight":"88rpx","height":"88rpx"}'>
						<view class="uni-picker-type">{{phoneroles[phonerolesIndex].roleName}}</view>
					</picker>
				</view>
				<view v-if="roleNum>1&&loginType<=2" :style='{"border":"0px solid #1ccb96","margin":"0 0 24rpx 0","borderRadius":"20rpx","flexWrap":"wrap","background":"none","display":"flex","width":"calc(100% - 0px)","height":"auto"}'>
					<view :style='{"color":"#333","borderRadius":"20rpx","textAlign":"left","background":"none","width":"100%","lineHeight":"60rpx","fontSize":"28rpx","minWidth":"160rpx"}' class="label">用户类型：</view>
					<picker @change="optionsChange" :value="index" :range="options" :style='{"border":"2rpx solid #d6b8ff","padding":"0 20rpx","color":"#666","borderRadius":"60rpx","textAlign":"left","flex":"1","background":"#fff","fontSize":"28rpx","lineHeight":"88rpx","height":"88rpx"}'>
						<view class="uni-picker-type">{{options[index]}}</view>
					</picker>
				</view>
				

				
				<button v-if="loginType==1||loginType==3||loginType==4" class="btn-submit" @tap="onLoginTap" type="primary" :style='{"border":"0","padding":"0 40rpx","margin":"24rpx 0 0","color":"rgb(255, 255, 255)","display":"inline-block","minWidth":"100%","borderRadius":"20rpx","background":"#d6b8ff","width":"auto","lineHeight":"96rpx","fontSize":"32rpx","fontWeight":"600","height":"96rpx"}'>登录</button>
				<button v-if="loginType==2" class="btn-submit" @tap="onFaceLoginTap" type="primary" :style='{"border":"0","padding":"0 40rpx","margin":"24rpx 0 0","color":"rgb(255, 255, 255)","display":"inline-block","minWidth":"100%","borderRadius":"20rpx","background":"#d6b8ff","width":"auto","lineHeight":"96rpx","fontSize":"32rpx","fontWeight":"600","height":"96rpx"}'>人脸识别登录</button>
				<view class="links" :style='{"padding":"0","margin":"24rpx 0 0","flexWrap":"wrap","display":"flex","width":"100%","justifyContent":"space-between","height":"auto"}'>
					<view class="link-highlight" @tap="onRegisterTap('xuesheng')" :style='{"border":"0px solid #1ccb96","padding":"0px","margin":"24rpx 0 24rpx","color":"#7d54b6","borderRadius":"16rpx","background":"#f6f0ff","display":"inline-block","width":"calc(50% - 20rpx)","fontSize":"32rpx","lineHeight":"88rpx","height":"88rpx"}'>注册学生</view>
					<view @tap="onForgetTap" :style='{"border":"0px solid #1ccb96","padding":"16rpx 0","color":"#000","borderRadius":"16rpx","textAlign":"center","width":"calc(100% - 0px)","fontSize":"30rpx","textDecoration":"underline"}'>忘记密码，点击找回</view>
					<view v-if="loginType==1" @tap="changeLogin(4)" :style='{"border":"0px solid #1ccb96","padding":"16rpx 0","color":"#000","borderRadius":"16rpx","textAlign":"center","width":"calc(100% - 0px)","fontSize":"30rpx","textDecoration":"underline"}'>手机号登录</view>
					<view v-if="loginType==2||loginType==3||loginType==4" @tap="changeLogin(1)" :style='{"border":"0px solid #1ccb96","padding":"16rpx 0","color":"#000","borderRadius":"16rpx","textAlign":"center","width":"calc(100% - 0px)","fontSize":"30rpx","textDecoration":"underline"}'>用户密码登录</view>
				</view>
				
				<view class="idea1" :style='{"width":"100%","background":"red","display":"none","height":"80rpx"}'>idea1</view>
				<view class="idea2" :style='{"width":"100%","background":"red","display":"none","height":"80rpx"}'>idea2</view>
				<view class="idea3" :style='{"width":"100%","background":"red","display":"none","height":"80rpx"}'>idea3</view>
			</view>
		</view>
	</view>
</template>

<script>
	import menu from '@/utils/menu'
	export default {
		data() {
			return {
				username: '',
				password: '',
				loginType:1,
				phone: '',
				phonecode: '',
				phoneroles: [
					{
						tableName: '',
						roleName: '请选择登录用户类型'
					},
					{
						tableName: 'xuesheng',
						roleName: '学生'
					},
				],
				phonerolesIndex: 0,
				// 倒计时时间
				count: 60,
				// 倒计时定时器
				inter: null,
				// 倒计时是否结束
				isEndFlag: true,
				codes: [{
					num: 1,
					color: '#000',
					rotate: '10deg',
					size: '16px'
				}, {
					num: 2,
					color: '#000',
					rotate: '10deg',
					size: '16px'
				}, {
					num: 3,
					color: '#000',
					rotate: '10deg',
					size: '16px'
				}, {
					num: 4,
					color: '#000',
					rotate: '10deg',
					size: '16px'
				}],
				options: [
					'请选择登录用户类型',
				],
				optionsValues: [
					'',
					'xuesheng',
				],
				index: 0,
				roleNum:0,

				indexBgUrl: '',
				indexLogoUrl: '',
			}
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
		onLoad() {
			let options = ['请选择登录用户类型'];
			let menus = menu.list();
			this.menuList = menus;
			for(let i=0;i<this.menuList.length;i++){
				if(this.menuList[i].hasFrontLogin=='是'){
					options.push(this.menuList[i].roleName);
					this.roleNum++;
				}
			}
			if(this.roleNum==1) {
				this.index = 1;
			}	
			this.options = options;
			this.styleChange()
			
		},
		onShow() {
		},
		mounted() {
		},
		methods: {
			async phoneClick() {
				var roles = ''
				if(!this.phone){
					this.$utils.msg(`手机号不能为空`);
					return
				}
				if(this.phone&&(!this.$validate.isMobile(this.phone))){
					this.$utils.msg(`请输入正确的手机号格式`);
					return
				}
				if (this.phoneroles.length>2) {
					if (!this.phoneroles[this.phonerolesIndex].tableName) {
						this.$utils.msg("请选择登录用户类型");
						return false;
					}
					roles = this.phoneroles[this.phonerolesIndex].tableName
				}else {
					roles = this.phoneroles[1].tableName
				}
				var _this = this;
				let res = await this.$api.getPublic(`${roles}/sendsms/login?mobile=${this.phone}`);
				if(res.code==0) {
					this.isEndFlag = false;
					this.inter = setInterval(function() {
						_this.count = _this.count - 1;
						if (_this.count <= 0) {
							clearInterval(_this.inter);
							_this.isEndFlag = true;
							_this.count=60;
						}
					}, 1000);
					this.$utils.msg("发送成功！");
				}else {
					this.$utils.msg(res.msg);
				}
			},
			async phoneLogin() {
				var roles = ''
				var rolesName = ''
				if (this.phoneroles.length>2) {
					roles = this.phoneroles[this.phonerolesIndex].tableName
					rolesName = this.phoneroles[this.phonerolesIndex].roleName
				}else {
					roles = this.phoneroles[1].tableName
					rolesName = this.phoneroles[1].roleName
				}
				let res = await this.$api.postPublic(`${roles}/sms/login?mobile=${this.phone}&&smscode=${this.phonecode}`);
				uni.removeStorageSync("useridTag");
				uni.setStorageSync("appToken", res.token);
				uni.setStorageSync("nickname",res.username);
				uni.setStorageSync("nowTable", `${roles}`);
				res = await this.$api.session(`${roles}`);
				if(res.data.touxiang) {
					uni.setStorageSync('frontHeadportrait', res.data.touxiang);
				} else if(res.data.headportrait) {
					uni.setStorageSync('frontHeadportrait', res.data.headportrait);
				}
				if(roles== 'xuesheng') {
					uni.setStorageSync('appExamName', res.data.xingming);
				}
				uni.setStorageSync('userSession',JSON.stringify(res.data))
				// 保存用户id
				uni.setStorageSync("appUserid", res.data.id);
				if(res.data.vip) {
					uni.setStorageSync("vip", res.data.vip);
				}
				uni.setStorageSync("appRole", `${rolesName}`);
				this.$utils.tab('../index/index');
			},
			phonerolesChange(e){
				this.phonerolesIndex = e.target.value
			},
			async styleChange() {
				let rs = await this.$api.getPublic('config/info?name=appLoginBackgroundImg')
				this.indexBgUrl = rs.data?rs.data.value:''
				rs = await this.$api.getPublic('config/info?name=appLoginLogo')
				this.indexLogoUrl = rs.data?rs.data.value:''
			},
			onRegisterTap(tableName) {
				uni.setStorageSync("loginTable", tableName);
				this.$utils.jump('../register/register')
			},
			onForgetTap() {
				this.$utils.jump('../forget/forget')
			},
			async onLoginTap() {
				if(this.loginType==1) {
					if (!this.username) {
						this.$utils.msg('请输入用户名')
						return
					}
					if (!this.password) {
						this.$utils.msg('请输入用户密码')
						return
					}
					if (!this.optionsValues[this.index]) {
						this.$utils.msg('请选择登录用户类型')
						return
					}
				}
				if(this.loginType==4) {
					if (!this.phone) {
						this.$utils.msg('请输入手机号')
						return
					}
					if (!this.phonecode) {
						this.$utils.msg('请输入手机验证码')
						return
					}
					if (this.phoneroles.length>2&&!this.phoneroles[this.phonerolesIndex].tableName) {
						this.$utils.msg('请选择登录用户类型')
						return
					}
				}

				this.loginPost()

			},
			async loginPost() {
				let that = this
				if(this.loginType==4) {
					this.phoneLogin()
					return false
				}
				let res = await this.$api.login(`${this.optionsValues[this.index]}`, {
					username: this.username,
					password: this.password
				});
				uni.removeStorageSync("useridTag");
				uni.setStorageSync("appToken", res.token);
				uni.setStorageSync("nickname",this.username);
				uni.setStorageSync("nowTable", `${this.optionsValues[this.index]}`);
				res = await this.$api.session(`${this.optionsValues[this.index]}`);
				if(res.data.touxiang) {
					uni.setStorageSync('frontHeadportrait', res.data.touxiang);
				} else if(res.data.headportrait) {
					uni.setStorageSync('frontHeadportrait', res.data.headportrait);
				}
				if(that.optionsValues[that.index]== 'xuesheng') {
					uni.setStorageSync('appExamName', res.data.xingming);
				}
				uni.setStorageSync('userSession',JSON.stringify(res.data))
				// 保存用户id
				uni.setStorageSync("appUserid", res.data.id);
				if(res.data.vip) {
					uni.setStorageSync("vip", res.data.vip);
				}
				uni.setStorageSync("appRole", `${this.options[this.index]}`);
				this.$utils.tab('../index/index');
			},
			changeLogin(type){
				this.loginType = type
			},
			optionsChange(e) {
				this.index = e.target.value
			}
		}
	}
</script>

<style lang="scss" scoped>
	page {
		height: 100%;
	}
	
	.content {
		height: 100%;
		box-sizing: border-box;
	}
	.login-box {
		padding: 48rpx 48rpx 24rpx;
		background: url(http://codegen.caihongy.cn/20251215/d7acdcdfd32e43debb3a6bba35107421.jpg) no-repeat center top / cover,#fff;
		width: 100%;
		min-height: 100vh;
		height: auto;
	}
</style>
