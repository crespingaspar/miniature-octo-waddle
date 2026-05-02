<template>
	<view class="content">
		<view :style='{"minHeight":"100vh","width":"100%","padding":"0","position":"relative","background":"#ffffff","height":"auto"}'>
			<view v-if="user&&user.id" :style='{"minHeight":"360rpx","padding":"48rpx 24rpx 24rpx 24rpx","margin":"0 0 20rpx 0","borderRadius":"0","background":"url(http://codegen.caihongy.cn/20251215/901e0f2e318e4d2280186eb927db78bd.jpg) no-repeat center top / 100% 100%","display":"flex","width":"100%","position":"relative","height":"auto"}' @tap="onPageTap('../user-info/user-info')" class="header" v-bind:class="{'status':isH5Plus}">
				<view :style='{"padding":"0px 0","flexWrap":"wrap","flexDirection":"row","display":"flex","width":"100%","position":"relative","height":"auto"}' v-if="tableName=='xuesheng'" class="userinfo">
					<image :style='{"width":"100rpx","padding":"0","margin":"0 20rpx 0px","borderRadius":"100%","float":"left","height":"100rpx"}' :src="user.touxiang?baseUrl+user.touxiang:require('../../static/gen/upload.png')"></image>
					<view :style='{"width":"calc(100% - 140rpx)","flexDirection":"column","justifyContent":"center","display":"flex"}' class="info">
						<view :style='{"width":"100%","padding":"0 40rpx 0 0","lineHeight":"48rpx","fontSize":"28rpx","color":"#333","textAlign":"left"}'>{{user.xuehao}}<text v-if="user.vip&& user.vip=='是'">(VIP)</text></view>
					</view>
					
					<view :style='{"padding":"20rpx 40rpx 0","margin":"20rpx 0 0","color":"#333","borderRadius":"20rpx","flexWrap":"wrap","background":"none","display":"block","width":"100%","clear":"both","fontSize":"28rpx","justifyContent":"space-between","order":"5"}' class="info">
						<view :style='{"width":"auto","margin":"0 20rpx 0 0","lineHeight":"48rpx","fontSize":"inherit","display":"flex","order":"2"}'>
							<span :style='{"color":"inherit","whiteSpace":"nowrap"}'>手机号：</span>
							<span :style='{"color":"inherit"}'>{{user.mobile}}</span>
						</view>
						<view :style='{"width":"auto","lineHeight":"48rpx","fontSize":"inherit","display":"flex","order":"3"}' v-if="user.jf||user.jf==0">
							<span :style='{"color":"inherit","whiteSpace":"nowrap"}'>积分：</span>
							<span :style='{"color":"inherit"}'>{{user.jf}}</span>
						</view>
					</view>
					<view :style='{"border":"0px solid #ffffff80","padding":"0 40rpx","margin":"30rpx 0","borderRadius":"20rpx","background":"rgba(255,255,255,0)","display":"flex","width":"calc(100% + 120rpx) ","order":"10"}'>
					  <view :style='{"border":"0px solid #9a9a9a","padding":"10rpx","margin":"0 20rpx 0 0","borderRadius":"40rpx","background":"#d6b8ff","display":"flex"}'>
					    <text class="icon iconfont icon-shoucang10" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"28rpx","color":"#fff","order":"2"}'></text>
					    <text :style='{"margin":"0 20rpx 0 0","lineHeight":"1.5","fontSize":"28rpx","color":"#fff"}'>收藏 {{user.storenum}}</text>
					  </view>
					</view>
				</view>
				<view :style='{"padding":"0px 0","flexWrap":"wrap","flexDirection":"row","display":"flex","width":"100%","position":"relative","height":"auto"}' v-if="tableName=='jiaoshi'" class="userinfo">
					<view :style='{"width":"calc(100% - 140rpx)","flexDirection":"column","justifyContent":"center","display":"flex"}' class="info">
						<view :style='{"width":"100%","padding":"0 40rpx 0 0","lineHeight":"48rpx","fontSize":"28rpx","color":"#333","textAlign":"left"}'>{{user.gonghao}}<text v-if="user.vip&& user.vip=='是'">(VIP)</text></view>
					</view>
					
					<view :style='{"padding":"20rpx 40rpx 0","margin":"20rpx 0 0","color":"#333","borderRadius":"20rpx","flexWrap":"wrap","background":"none","display":"block","width":"100%","clear":"both","fontSize":"28rpx","justifyContent":"space-between","order":"5"}' class="info">
						<view :style='{"width":"auto","margin":"0 20rpx 0 0","lineHeight":"48rpx","fontSize":"inherit","display":"flex","order":"2"}'>
							<span :style='{"color":"inherit","whiteSpace":"nowrap"}'>手机号：</span>
							<span :style='{"color":"inherit"}'>{{user.mobile}}</span>
						</view>
						<view :style='{"width":"auto","lineHeight":"48rpx","fontSize":"inherit","display":"flex","order":"3"}' v-if="user.jf||user.jf==0">
							<span :style='{"color":"inherit","whiteSpace":"nowrap"}'>积分：</span>
							<span :style='{"color":"inherit"}'>{{user.jf}}</span>
						</view>
					</view>
					<view :style='{"border":"0px solid #ffffff80","padding":"0 40rpx","margin":"30rpx 0","borderRadius":"20rpx","background":"rgba(255,255,255,0)","display":"flex","width":"calc(100% + 120rpx) ","order":"10"}'>
					  <view :style='{"border":"0px solid #9a9a9a","padding":"10rpx","margin":"0 20rpx 0 0","borderRadius":"40rpx","background":"#d6b8ff","display":"flex"}'>
					    <text class="icon iconfont icon-shoucang10" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"28rpx","color":"#fff","order":"2"}'></text>
					    <text :style='{"margin":"0 20rpx 0 0","lineHeight":"1.5","fontSize":"28rpx","color":"#fff"}'>收藏 {{user.storenum}}</text>
					  </view>
					</view>
				</view>
				<view :style='{"padding":"40rpx 40rpx 0 10rpx","alignItems":"center","borderRadius":"40rpx 0 0 40rpx","background":"none","flexDirection":"column","display":"flex","width":"120rpx","justifyContent":"center","height":"120rpx"}' class="setting">
					<text class="icon iconfont icon-qita6" :style='{"border":"0","margin":"0 10rpx 0 0","color":"#333","borderRadius":"0","width":"40rpx","lineHeight":"52rpx","fontSize":"52rpx"}'></text>
					<text :style='{"color":"#333","lineHeight":"60rpx","fontSize":"28rpx"}'>设置</text>
				</view>
			</view>
			
			<view v-else :style='{"minHeight":"360rpx","padding":"48rpx 24rpx 24rpx 24rpx","margin":"0 0 20rpx 0","borderRadius":"0","background":"url(http://codegen.caihongy.cn/20251215/901e0f2e318e4d2280186eb927db78bd.jpg) no-repeat center top / 100% 100%","display":"flex","width":"100%","position":"relative","height":"auto"}' @tap="loginClick" class="header" v-bind:class="{'status':isH5Plus}">
				<view :style='{"alignItems":"center","color":"#333","background":"none","display":"flex","width":"100%","fontSize":"36rpx","justifyContent":"center","height":"300rpx"}'>登录/注册</view>
			</view>
			
			<view :style='{"width":"100%","background":"none","height":"auto"}' class="list">
				
				<view :style='{"width":"calc(100% - 48rpx)","padding":"0px 0 0","margin":"40rpx auto","borderRadius":"20rpx","background":"#fff","height":"auto"}'>
					<view :style='{"padding":"0 0 0 80rpx","margin":"0 0 40rpx","borderColor":"#23baf7","color":"#000","textAlign":"left","display":"none","background":"url(http://codegen.caihongy.cn/20241110/8dff7d1201304c20b53f3530844a8261.png) no-repeat left center","borderWidth":"0 0 0 0px","width":"100%","fontSize":"44rpx","position":"relative","borderStyle":"solid","fontWeight":"600"}'>
						<view :style='{"color":"#000","fontSize":"44rpx"}'>我的服务</view>
					</view>
					<view class="me-menu-view">
						<block v-for="item in menuList" v-bind:key="item.roleName">
							<block v-if="role==item.roleName" v-bind:key="menu.menu" v-for="(menu,index) in item.backMenu">
								<block v-bind:key="child.tableName" v-for=" (child,sort) in menu.child">
									<view class="me-menu-item" v-if="hasBack(child.tableName)" @tap="onPageTap('../'+child.tableName+'/list?userid='+user.id+'&menuJump='+child.menuJump)" hover-class="hover">
										<view class="me-menu-icon" :class="child.appFrontIcon" :style="{'color': meMenuColor[index]}"></view>
										<view class="text">{{child.menu}}</view>
										<view class="icon iconfont icon-jinru"></view>
									</view>
								</block>
							</block>
						</block>


						<view v-if="user&&user.id" @tap="onPageTap('../chat/chat')" class="me-menu-item" hover-class="hover">
							<view class="cuIcon-service me-menu-icon" :style="{'color': meMenuColor[3]}"></view>
							<view class="text">智能客服</view>
							<view class="icon iconfont icon-jinru"></view>
						</view>
						<view v-if="user&&user.id" @tap="passwordShow()" class="me-menu-item" hover-class="hover">
							<view class="cuIcon-lock me-menu-icon" :style="{'color': meMenuColor[6]}"></view>
							<view class="text">修改密码</view>
							<view class="icon iconfont icon-jinru"></view>
						</view>
					</view>
				</view>
			</view>
			
			<!-- 推荐 -->
			<view class="recommend" :style='{"width":"100%","padding":"0 20rpx 0 20rpx","margin":"20rpx auto","background":"none","height":"auto"}' v-if="recommendList.length">
				<view :style='{"padding":"0 0 0 20rpx","margin":"0 auto 40rpx auto","borderColor":"#a879e8","color":"#fff","textAlign":"left","outline":"2rpx solid #D6B8FF80","outlineOffset":"8rpx","background":"linear-gradient(45deg, #d6b8ff 0%, #f1e7fe 100%)","borderWidth":"0 0 0 8rpx","width":"96%","fontSize":"32rpx","lineHeight":"88rpx","position":"relative","borderStyle":"solid","fontWeight":"600"}'>为你推荐</view>
				<view :style='{"padding":"0","borderRadius":"20rpx","flexWrap":"wrap","background":"none","display":"flex","width":"100%","justifyContent":"space-between","height":"auto"}'>
					<view v-for="(item,index) in recommendList" :key="index" :style='{"border":"0px solid #174add60","padding":"20rpx","margin":"0 0 40rpx 0","borderRadius":"20rpx","flexWrap":"wrap","background":"none","display":"flex","width":"48%","height":"auto"}' @click="recommendDetail(item.id)">
						<image v-if="preHttp(item[recommendPicture])" :style='{"width":"100%","margin":"0","objectFit":"cover","borderRadius":"12rpx","display":"block","height":"260rpx"}' :src="item[recommendPicture]"></image>
						<image v-else :style='{"width":"100%","margin":"0","objectFit":"cover","borderRadius":"12rpx","display":"block","height":"260rpx"}' :src="item[recommendPicture]?(baseUrl + item[recommendPicture].split(',')[0]):''"></image>
						<view :style='{"padding":"0 8rpx 0 8rpx","overflow":"hidden","whiteSpace":"nowrap","color":"#333","width":"100%","lineHeight":"48rpx","fontSize":"28rpx","textOverflow":"ellipsis"}'>{{item[recommendTitle]}}</view>
						<view :style='{"width":"100%","padding":"0 8rpx","lineHeight":"48rpx","fontSize":"28rpx","color":"#999","textAlign":"center"}'>{{item.addtime}}</view>
					</view>
				</view>
			</view>
			<view style="width: 100%;height: 80px"></view>
		</view>
		<uni-popup class="popup-content" ref="passwordPopup" type="bottom">
			<view class="passwordForm">
				<view class="passwordInput">
					<input type="password" v-model="passwordForm.mima" placeholder="原密码" />
				</view>
				<view class="passwordInput">
					<input type="password" v-model="passwordForm.newmima" placeholder="新密码" />
				</view>
				<view class="passwordInput">
					<input type="password" v-model="passwordForm.newmima1" placeholder="确认密码" />
				</view>
				<view class="passwordBtnView">
					<button class="passwordBtn" @click="updatePassword">更新</button>
				</view>
			</view>
		</uni-popup>
	</view>
</template>
<script>
	import menu from '@/utils/menu'
	export default {
		data() {
			return {
				isH5Plus: true,
				user: {},
				tableName:'',
				role: '',
				menuList: [],
				iconArr: [
					'cuIcon-same',
					'cuIcon-deliver',
					'cuIcon-evaluate',
					'cuIcon-shop',
					'cuIcon-ticket',
					'cuIcon-cascades',
					'cuIcon-discover',
					'cuIcon-question',
					'cuIcon-pic',
					'cuIcon-filter',
					'cuIcon-footprint',
					'cuIcon-pulldown',
					'cuIcon-pullup',
					'cuIcon-moreandroid',
					'cuIcon-refund',
					'cuIcon-qrcode',
					'cuIcon-remind',
					'cuIcon-profile',
					'cuIcon-home',
					'cuIcon-message',
					'cuIcon-link',
					'cuIcon-lock',
					'cuIcon-unlock',
					'cuIcon-vip',
					'cuIcon-weibo',
					'cuIcon-activity',
					'cuIcon-friendadd',
					'cuIcon-friendfamous',
					'cuIcon-friend',
					'cuIcon-goods',
					'cuIcon-selection'
				],
				recommendList: [],
				recommendTable: '',
				recommendTitle: '',
				recommendPicture: '',
				passwordForm: {
					mima: '',
					newmima: '',
					newmima1: '',
				},
				meMenuColor: '#6580a6,#548aff,#ffc431,#ff6161,#4f91ff,#ffbd65,#0fd38c,#52c9e1,#6580a6,#548aff,#ffc431,#ff6161'.split(','),
			};
		},
		computed: {
			baseUrl() {
				return this.$base.url;
			},
		},
		async onLoad(){
			let menus = menu.list();
			this.menuList = menus;
			this.meMenuColor = this.meMenuColor.sort(()=> {
				return (0.5-Math.random());
			});
		},
		async onShow(){
            uni.removeStorageSync("useridTag");
			this.role = uni.getStorageSync("appRole");
			await this.getSession()
			this.getStoreup()
			this.tableName = uni.getStorageSync("nowTable");
			let menus = menu.list();
			this.menuList = menus;
			this.getRecommendList()
			this.$forceUpdate()
		},
		methods: {
			hasBack(tableName) {
				if(tableName == 'hasBoard') {
					return false
				}
				if(tableName == 'exampaper'||tableName == 'examquestion') {
					return false
				}
				if(tableName == 'exampaperlist') {
					return false
				}
				if(tableName.indexOf('chapter')!=-1) {
					return false
				}
				return true
			},
			async getSession(){
				let table = uni.getStorageSync("nowTable");
				let res = await this.$api.session(table);
				this.user = res.data;
				this.$forceUpdate()
			},
			async getStoreup(){
				if(!this.user||!this.user.id){
					return false
				}
				let res = await this.$api.page('storeup',{userid: this.user.id,type: 1})
				this.user.storenum = Number(res.data.total)
				this.$forceUpdate()
			},
			loginClick(){
				uni.navigateTo({
					url: '../login/login'
				});
			},
			onPageTap(url) {
                uni.setStorageSync("useridTag",1);
				uni.navigateTo({
					url: url,
					fail: function() {
						uni.switchTab({
							url: url
						});
					}
				});
			},
			hasTranslate(){
				for(let x in this.menuList){
					if(this.menuList[x].roleName == this.role){
						for(let i in this.menuList[x].backMenu){
							if(this.menuList[x].backMenu[i].child.length) {
								for(let j in this.menuList[x].backMenu[i].child) {
									if(this.menuList[x].backMenu[i].child[j].tableName=='hasTranslate'){
										return true
									}
								}
							}
						}
					}
				}
				return false
			},
			
			preHttp(str) {
			    return str && str.substr(0,4)=='http';
			},
			async getRecommendList(){
				let params = {
					page: 1,
					limit: 4,
				}
				let res;
				if(uni.getStorageSync("appUserid")) {
					res = await this.$api.recommend2('kechengxinxi', params);
				} else {
					res = await this.$api.recommend('kechengxinxi', params);
				}
				this.recommendList = res.data.list
				this.recommendTable = 'kechengxinxi'
				this.recommendTitle = 'kechengmingcheng'
				this.recommendPicture = 'tupian'
				this.$forceUpdate()
			},
			recommendDetail(id) {
				uni.navigateTo({
					url: `../${this.recommendTable}/detail?id=${id}`,
					fail: function() {
						uni.switchTab({
							url: `../${this.recommendTable}/detail?id=${id}`
						});
					}
				});
			},
			passwordShow() {
				this.passwordForm = {
					mima: '',
					newmima: '',
					newmima1: '',
				}
				this.$forceUpdate()
				this.$refs.passwordPopup.open()
			},
			async updatePassword() {
				if (this.passwordForm.mima == ''){
					this.$utils.msg('原密码不能为空')
					return false
				}
				if (this.passwordForm.newmima == ''){
					this.$utils.msg('新密码不能为空')
					return false
				}
				if (this.passwordForm.newmima1 == ''){
					this.$utils.msg('确认密码不能为空')
					return false
				}
				let password = ''
				if (this.user.mima) {
					password = this.user.mima;
				} else if (this.user.password) {
					password = this.user.password;
				}
				if (this.tableName == 'xuesheng') {
				}
				let newpassword = this.passwordForm.mima
				if(password != newpassword){
					this.$utils.msg('原密码不正确')
					return false
				}
				if (this.passwordForm.newmima != this.passwordForm.newmima1){
					this.$utils.msg('两次密码不一致')
					return false
				}
				if (this.passwordForm.mima == this.passwordForm.newmima){
					this.$utils.msg('新密码与原密码相同！')
					return false
				}
				this.user.password = this.passwordForm.newmima
				this.user.mima = this.passwordForm.newmima
				await this.$api.update(this.tableName,this.user)
				this.$utils.msg('修改密码成功,下次登录系统生效')
				this.$refs.passwordPopup.close()
				this.getSession()
			},
		}
	}
</script>

<style lang="scss" scoped>
	.content {
		height: calc(100vh - 94px);
		box-sizing: border-box;
	}
	.passwordForm {
		width: 100%;
		padding: 10px 20px 60px;
		background: #fff;
	
		.passwordInput {
			background: #eee;
			margin: 0 0 10px;
			padding: 0 10px;
	
			input {
				height: 36px;
				line-height: 36px;
				font-size: 14px;
			}
		}
	
		.passwordBtnView {
			width: 100%;
			display: flex;
			align-items: center;
			justify-content: center;
			padding: 10px 0;
	
			.passwordBtn {
				padding: 0 20px;
				width: auto;
				height: 20px;
				line-height: 20px;
				font-size: 14px;
				color: #fff;
				background: #007aff;
				border: none;
				border-radius: 5px;
	
			}
		}
	}
	.me-menu-view {
		padding: 0;
		align-content: flex-start;
		background: none;
		display: flex;
		gap: 20rpx;
		width: 100%;
		justify-content: center;
		align-items: flex-start;
		flex-wrap: wrap;
		height: auto;
		.me-menu-item {
			padding: 20rpx 0 10rpx;
			margin: 0 0 20rpx 0;
			display: flex;
			border-color: #e7e7e7;
			border-radius: 20rpx;
			flex-direction: column;
			background: none;
			width: 31%;
			border-width: 2rpx;
			align-items: center;
			position: relative;
			border-style: solid;
			height: auto;
			.me-menu-icon {
				color: rgba(0, 186, 189, 1);
				font-size: 60rpx;
				line-height: 1;
			}
			.text {
				padding: 0 20rpx;
				color: #333;
				width: 100%;
				font-size: 28rpx;
				line-height: 60rpx;
				text-align: center;
			}
			.icon {
				color: #c5c5c5;
				top: 35%;
				font-weight: 600;
				display: none;
				width: 28rpx;
				font-size: 28rpx;
				line-height: 28rpx;
				position: absolute;
				right: 10rpx;
			}
		}
	}
</style>
