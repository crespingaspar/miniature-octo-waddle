  

<template>
<view class="content">
	<view :style='{"width":"100%","padding":"24rpx","position":"relative","background":"#fff","height":"100%"}' v-if="showType==1">
		<form :style='{"width":"100%","padding":"48rpx 0 48rpx","background":"none","display":"block","height":"auto"}' class="app-update-pv">














			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 40rpx 0","borderColor":"#d6b8ff","alignItems":"center","borderRadius":"60rpx","borderWidth":"2rpx","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class=" select">
				<view :style='{"padding":"0 20rpx 0 0","whiteSpace":"nowrap","color":"#666","textAlign":"right","width":"auto","lineHeight":"80rpx","fontSize":"30rpx","fontWeight":"500"}' class="title">类型</view>
				<picker :disabled="ro.type" :style='{"width":"100%","flex":"1","height":"auto"}' @change="typeChange" :value="typeIndex" :range="typeOptions">
					<view :style='{"width":"100%","lineHeight":"80rpx","fontSize":"30rpx","color":"#d6b8ff"}' class="uni-input">{{ruleForm.type?ruleForm.type:"请选择类型"}}</view>
				</picker>
			</view>
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 40rpx 0","borderColor":"#d6b8ff","alignItems":"center","borderRadius":"60rpx","borderWidth":"2rpx","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class=" select" v-if="ruleForm.type=='个人'">
				<view :style='{"padding":"0 20rpx 0 0","whiteSpace":"nowrap","color":"#666","textAlign":"right","width":"auto","lineHeight":"80rpx","fontSize":"30rpx","fontWeight":"500"}' class="title">角色</view>
				<picker :style='{"width":"100%","flex":"1","height":"auto"}' @change="roleChange" :value="roleIndex" :range="roleList" range-key="tableName">
					<view :style='{"width":"100%","lineHeight":"80rpx","fontSize":"30rpx","color":"#d6b8ff"}' class="uni-input">{{ruleForm.role?roleList[roleIndex].tableName:"请选择角色"}}</view>
				</picker>
			</view>
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 40rpx 0","borderColor":"#d6b8ff","alignItems":"center","borderRadius":"60rpx","borderWidth":"2rpx","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class=" select" v-if="ruleForm.type=='个人'">
				<view :style='{"padding":"0 20rpx 0 0","whiteSpace":"nowrap","color":"#666","textAlign":"right","width":"auto","lineHeight":"80rpx","fontSize":"30rpx","fontWeight":"500"}' class="title">用户</view>
				<picker :style='{"width":"100%","flex":"1","height":"auto"}' @change="userChange" :value="typeIndex" :range="userList" range-key="account">
					<view :style='{"width":"100%","lineHeight":"80rpx","fontSize":"30rpx","color":"#d6b8ff"}' class="uni-input">{{ruleForm.userid?userList[userIndex].account:"请选择用户"}}</view>
				</picker>
			</view>
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 40rpx 0","borderColor":"#d6b8ff","alignItems":"center","borderRadius":"60rpx","borderWidth":"2rpx","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="">
				<view :style='{"padding":"0 20rpx 0 0","whiteSpace":"nowrap","color":"#666","textAlign":"right","width":"auto","lineHeight":"80rpx","fontSize":"30rpx","fontWeight":"500"}' class="title">标题</view>
				<input :style='{"border":"0","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"30rpx","height":"80rpx"}' :disabled="ro.title" v-model="ruleForm.title" placeholder="标题"  type="text"></input>
			</view>
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 40rpx 0","borderColor":"#d6b8ff","alignItems":"center","borderRadius":"60rpx","borderWidth":"2rpx","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="">
				<view :style='{"padding":"0 20rpx 0 0","whiteSpace":"nowrap","color":"#666","textAlign":"right","width":"auto","lineHeight":"80rpx","fontSize":"30rpx","fontWeight":"500"}' class="title">提醒时间</view>
				<input :disabled="ro.remindtime" :style='{"border":"0","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"30rpx","height":"80rpx"}' v-model="ruleForm.remindtime" placeholder="提醒时间" @tap="toggleTab('remindtime')"></input>
			</view>
       
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 40rpx 0","borderColor":"#d6b8ff","alignItems":"center","borderRadius":"60rpx","borderWidth":"2rpx","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="">
				<view :style='{"padding":"0 20rpx 0 0","whiteSpace":"nowrap","color":"#666","textAlign":"right","width":"auto","lineHeight":"80rpx","fontSize":"30rpx","fontWeight":"500"}' class="title">简介</view>
				<textarea :style='{"border":"0","padding":"24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"28rpx","height":"240rpx"}' v-model="ruleForm.brief" placeholder="简介" :maxlength="-1" :disabled="ro.brief"></textarea>
			</view>
			<view :style='{"padding":"10rpx 20rpx","margin":"0","borderColor":"#D6B8FF","borderRadius":"40rpx","borderWidth":"2rpx","background":"#fff","width":"100%","borderStyle":"solid","height":"auto"}' class="">
				<view :style='{"width":"100%","lineHeight":"80rpx","fontSize":"30rpx","color":"#666","fontWeight":"500"}' class="title">内容</view>
				<xia-editor ref="content" :style='{"minHeight":"300rpx","border":"0px solid #efefef","width":"100%","fontSize":"30rpx","height":"auto"}' v-model="ruleForm.content" placeholder="内容" @editorChange="contentChange"></xia-editor>
			</view>
			
			<view :style='{"width":"100%","margin":"40rpx 0","flexDirection":"column","justifyContent":"center","display":"flex","height":"auto"}' class="btn" >
				<button :style='{"border":"0","padding":"0","margin":"0 0 40rpx 0","color":"rgb(255, 255, 255)","borderRadius":"16rpx","background":"#d6b8ff","width":"100%","lineHeight":"96rpx","fontSize":"32rpx","height":"96rpx"}' @tap="onSubmitTap(null)" class="bg-red">提交</button>
			</view>
		</form>
		<w-picker  mode="dateTime" step="1" :current="false" :hasSecond="false" @confirm="remindtimeConfirm" ref="remindtime" themeColor="#333333"></w-picker>
	</view>
</view>
</template>

<script>
	import wPicker from "@/components/w-picker/w-picker.vue";
	import xiaEditor from '@/components/xia-editor/xia-editor';
	import multipleSelect from "@/components/momo-multipleSelect/momo-multipleSelect";
	export default {
		data() {
			return {
				cross:'',
				ruleForm: {
				userid: '',
				role: '',
				title: '',
				type: '',
				brief: '',
				content: '',
				remindtime: '',
				},
				typeOptions: [],
				typeIndex: 0,
				// 登录用户信息
				user: {},
				ro:{
				   userid : false,
				   role : false,
				   title : false,
				   type : false,
				   brief : false,
				   content : false,
				   remindtime : false,
				},
				virtualPay: false,
				showType: 1,
				roleList: [],
  				roleIndex: 0,
				userList: [],
				userIndex: 0,
			}
		},
		components: {
			wPicker,
			xiaEditor,
			multipleSelect,
		},
		computed: {
			baseUrl() {
				return this.$base.url;
			},
			sessionForm() {
				return uni.getStorageSync("appUserid")?JSON.parse(uni.getStorageSync('userSession')):{}
			},



		},
		async onLoad(options) {
			if(options.virtualPay){
				this.virtualPay = true
			}
			let table = uni.getStorageSync("nowTable");
			// 获取用户信息
			let res = await this.$api.session(table);
			this.user = res.data;
			

			// 跨表
			this.cross = options.cross;
			if(options.cross){
				var obj = uni.getStorageSync('crossObj');
				for (var o in obj){
					if(o=='userid'){
						this.ruleForm.userid = obj[o];
						this.ro.userid = true;
						continue;
					}
					if(o=='role'){
						this.ruleForm.role = obj[o];
						this.ro.role = true;
						continue;
					}
					if(o=='title'){
						this.ruleForm.title = obj[o];
						this.ro.title = true;
						continue;
					}
					if(o=='type'){
						this.ruleForm.type = obj[o];
						for(let x in this.typeOptions) {
							if(this.typeOptions[x] == this.ruleForm.type) {
								this.typeIndex = Number(x)
							}
						}
						this.ro.type = true;
						continue;
					}
					if(o=='brief'){
						this.ruleForm.brief = obj[o];
						this.ro.brief = true;
						continue;
					}
					if(o=='content'){
						this.ruleForm.content = obj[o];
						this.ro.content = true;
						continue;
					}
					if(o=='remindtime'){
						this.ruleForm.remindtime = obj[o];
						this.ro.remindtime = true;
						continue;
					}
				}
			}

			// 自定义下拉框值
			this.typeOptions = "个人,全局".split(',')
			this.typeOptions.unshift("请选择类型");
			if(this.ruleForm.type) {
				for(let x in this.typeOptions) {
					if(this.typeOptions[x] == this.ruleForm.type) {
						this.typeIndex = Number(x)
					}
				}
			}

			if (options.refid) {
				// 如果上一级页面传递了refid，获取改refid数据信息
				this.ruleForm.refid = Number(options.refid);
				this.ruleForm.nickname = uni.getStorageSync("nickname");
			}
			// 如果是更新操作
			if (options.id) {
				this.ruleForm.id = options.id;
				// 获取信息
				res = await this.$api.info(`popupremind`, this.ruleForm.id);
				if(res.data.type) {
					for(let x in this.typeOptions) {
						if(this.typeOptions[x] == res.data.type) {
							this.typeIndex = Number(x)
						}
					}
				}
				this.ruleForm = res.data;
			}
			this.$forceUpdate()
			this.roleList = this.$config.roleList()
			if (uni.getStorageSync('raffleType') && uni.getStorageSync('raffleType') != null) {
				uni.removeStorageSync('raffleType')
				setTimeout(() => {
					this.onSubmitTap(null)
				}, 300)
			}
		},
		methods: {
			async roleChange(e){
				this.roleIndex = e.detail.value
				this.ruleForm.role = this.roleList[this.roleIndex].roleName
				let res = await this.$api.getPublic(this.roleList[this.roleIndex].roleName + '/accountList')
				this.userList = res.data
				this.$forceUpdate()
			},
			userChange(e) {
				this.userIndex = e.detail.value
				this.ruleForm.userid = this.userList[this.userIndex].id
				this.$forceUpdate()
			},
			typeChange(e) {
				this.typeIndex = e.target.value
				this.ruleForm.type = this.typeOptions[this.typeIndex]
				if(this.ruleForm.type=='全局'){
					this.ruleForm.role = ''
					this.ruleForm.userid = ''
				}
				this.$forceUpdate()
			},
			numberChange(e,name) {
				this.ruleForm[name] = Number(e.detail.value)
			},
			contentChange(e) {
				this.ruleForm.content = e
			},

			// 多级联动参数


			// 日长控件选择日期时间
			remindtimeConfirm(val) {
				this.ruleForm.remindtime = val.result;
				this.$forceUpdate();
			},



			getUUID () {
				return new Date().getTime();
			},
			async onSubmitTap(subMitType=null) {
				let that = this
				//跨表计算判断
				var obj;
				if((!this.ruleForm.userid)){
					this.$utils.msg(`发布人id不能为空`);
					return
				}
				if((!this.ruleForm.title)){
					this.$utils.msg(`标题不能为空`);
					return
				}
				if((!this.ruleForm.content)){
					this.$utils.msg(`内容不能为空`);
					return
				}
				//更新跨表属性
				var crossuserid;
				var crossrefid;
				var crossoptnum;
				if(this.cross){
					var statusColumnName = uni.getStorageSync('statusColumnName');
					var statusColumnValue = uni.getStorageSync('statusColumnValue');
					if(statusColumnName!='') {
						if(!obj) {
							obj = uni.getStorageSync('crossObj');
						}
						if(!statusColumnName.startsWith("[")) {
							for (var o in obj){
								if(o==statusColumnName){
									obj[o] = statusColumnValue;
								}
							}
							var table = uni.getStorageSync('crossTable');
							await this.$api.update(`${table}`, obj);
						} else {
								crossuserid=Number(uni.getStorageSync('appUserid'));
								crossrefid=obj['id'];
								crossoptnum=uni.getStorageSync('statusColumnName');
								crossoptnum=crossoptnum.replace(/\[/,"").replace(/\]/,"");
						}
					}
					if(crossrefid && crossuserid) {
						this.ruleForm.crossuserid=crossuserid;
						this.ruleForm.crossrefid=crossrefid;
						let params = {
							page: 1,
							limit:10,
							crossuserid:crossuserid,
							crossrefid:crossrefid,
						}
						let res = await this.$api.list(`popupremind`, params);
						if (res.data.total >= crossoptnum) {
							this.$utils.msg(uni.getStorageSync('tips'));
							uni.removeStorageSync('crossCleanType');
							return false;
						}
					}
					//跨表计算
				}
				let oet = {}
				if(this.ruleForm.id){
					await this.$api.update(`popupremind`, this.ruleForm);
				}else{
					oet = await this.$api.add(`popupremind`, this.ruleForm);
				}
				if(this.cross){
					uni.setStorageSync('crossCleanType',true);
				}
				that.$utils.msgBack('提交成功');
			},
			optionsChange(e) {
				this.index = e.target.value
			},
			bindDateChange(e) {
				this.date = e.target.value
			},
			getDate(type) {
				const date = new Date();
				let year = date.getFullYear();
				let month = date.getMonth() + 1;
				let day = date.getDate();
				if (type === 'start') {
					year = year - 60;
				} else if (type === 'end') {
					year = year + 2;
				}
				month = month > 9 ? month : '0' + month;;
				day = day > 9 ? day : '0' + day;
				return `${year}-${month}-${day}`;
			},
			toggleTab(str) {
				if(this.ro[str]){
					return false
				}
				this.$refs[str].show();
			},
		}
	}
</script>

<style lang="scss" scoped>
	.content {
		min-height: calc(100vh - 44px);
		box-sizing: border-box;
	}
</style>
