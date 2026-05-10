  

<template>
<view class="content">
	<view :style='{"width":"100%","padding":"24rpx","position":"relative","background":"#fff","height":"100%"}' v-if="showType==1">
		<form :style='{"width":"100%","padding":"48rpx 0 48rpx","background":"none","display":"block","height":"auto"}' class="app-update-pv">






























			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 40rpx 0","borderColor":"#d6b8ff","alignItems":"center","borderRadius":"60rpx","borderWidth":"2rpx","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="">
				<view :style='{"padding":"0 20rpx 0 0","whiteSpace":"nowrap","color":"#666","textAlign":"right","width":"auto","lineHeight":"80rpx","fontSize":"30rpx","fontWeight":"500"}' class="title">课程号</view>
				<input :style='{"border":"0","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"30rpx","height":"80rpx"}' :disabled="ro.kechenghao" v-model="ruleForm.kechenghao" placeholder="课程号"  type="text"></input>
			</view>
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 40rpx 0","borderColor":"#d6b8ff","alignItems":"center","borderRadius":"60rpx","borderWidth":"2rpx","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="">
				<view :style='{"padding":"0 20rpx 0 0","whiteSpace":"nowrap","color":"#666","textAlign":"right","width":"auto","lineHeight":"80rpx","fontSize":"30rpx","fontWeight":"500"}' class="title">课程名称</view>
				<input :style='{"border":"0","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"30rpx","height":"80rpx"}' :disabled="ro.kechengmingcheng" v-model="ruleForm.kechengmingcheng" placeholder="课程名称"  type="text"></input>
			</view>
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 40rpx 0","borderColor":"#d6b8ff","alignItems":"center","borderRadius":"60rpx","borderWidth":"2rpx","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="">
				<view :style='{"padding":"0 20rpx 0 0","whiteSpace":"nowrap","color":"#666","textAlign":"right","width":"auto","lineHeight":"80rpx","fontSize":"30rpx","fontWeight":"500"}' class="title">课程分类</view>
				<input :style='{"border":"0","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"30rpx","height":"80rpx"}' :disabled="ro.kechengfenlei" v-model="ruleForm.kechengfenlei" placeholder="课程分类"  type="text"></input>
			</view>
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 40rpx 0","borderColor":"#d6b8ff","alignItems":"center","borderRadius":"60rpx","borderWidth":"2rpx","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="" @tap="tupianTap">
				<view :style='{"padding":"0 20rpx 0 0","whiteSpace":"nowrap","color":"#666","textAlign":"right","width":"auto","lineHeight":"80rpx","fontSize":"30rpx","fontWeight":"500"}' class="title">图片</view>
				<image :style='{"width":"80rpx","borderRadius":"100%","objectFit":"cover","display":"block","height":"80rpx"}' class="avator" v-if="ruleForm.tupian" :src="baseUrl+ruleForm.tupian.split(',')[0]" mode="aspectFill"></image>
				<image :style='{"width":"80rpx","borderRadius":"100%","objectFit":"cover","display":"block","height":"80rpx"}' class="avator" v-else src="../../static/gen/upload.png" mode="aspectFill"></image>
			</view>
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 40rpx 0","borderColor":"#d6b8ff","alignItems":"center","borderRadius":"60rpx","borderWidth":"2rpx","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="">
				<view :style='{"padding":"0 20rpx 0 0","whiteSpace":"nowrap","color":"#666","textAlign":"right","width":"auto","lineHeight":"80rpx","fontSize":"30rpx","fontWeight":"500"}' class="title">难度</view>
				<input :style='{"border":"0","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"30rpx","height":"80rpx"}' :disabled="ro.nandu" v-model="ruleForm.nandu" placeholder="难度"  type="text"></input>
			</view>
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 40rpx 0","borderColor":"#d6b8ff","alignItems":"center","borderRadius":"60rpx","borderWidth":"2rpx","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="">
				<view :style='{"padding":"0 20rpx 0 0","whiteSpace":"nowrap","color":"#666","textAlign":"right","width":"auto","lineHeight":"80rpx","fontSize":"30rpx","fontWeight":"500"}' class="title">费用</view>
				<input :style='{"border":"0","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"30rpx","height":"80rpx"}' :disabled="ro.feiyong" v-model="ruleForm.feiyong" placeholder="费用" type="digit" @input="(e)=>numberChange(e,'feiyong')"></input>
			</view>
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 40rpx 0","borderColor":"#d6b8ff","alignItems":"center","borderRadius":"60rpx","borderWidth":"2rpx","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="">
				<view :style='{"padding":"0 20rpx 0 0","whiteSpace":"nowrap","color":"#666","textAlign":"right","width":"auto","lineHeight":"80rpx","fontSize":"30rpx","fontWeight":"500"}' class="title">学号</view>
				<input :style='{"border":"0","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"30rpx","height":"80rpx"}' :disabled="ro.xuehao" v-model="ruleForm.xuehao" placeholder="学号"  type="text"></input>
			</view>
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 40rpx 0","borderColor":"#d6b8ff","alignItems":"center","borderRadius":"60rpx","borderWidth":"2rpx","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="">
				<view :style='{"padding":"0 20rpx 0 0","whiteSpace":"nowrap","color":"#666","textAlign":"right","width":"auto","lineHeight":"80rpx","fontSize":"30rpx","fontWeight":"500"}' class="title">姓名</view>
				<input :style='{"border":"0","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"30rpx","height":"80rpx"}' :disabled="ro.xingming" v-model="ruleForm.xingming" placeholder="姓名"  type="text"></input>
			</view>
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 40rpx 0","borderColor":"#d6b8ff","alignItems":"center","borderRadius":"60rpx","borderWidth":"2rpx","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="">
				<view :style='{"padding":"0 20rpx 0 0","whiteSpace":"nowrap","color":"#666","textAlign":"right","width":"auto","lineHeight":"80rpx","fontSize":"30rpx","fontWeight":"500"}' class="title">工号</view>
				<input :style='{"border":"0","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"30rpx","height":"80rpx"}' :disabled="ro.gonghao" v-model="ruleForm.gonghao" placeholder="工号"  type="text"></input>
			</view>
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 40rpx 0","borderColor":"#d6b8ff","alignItems":"center","borderRadius":"60rpx","borderWidth":"2rpx","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="">
				<view :style='{"padding":"0 20rpx 0 0","whiteSpace":"nowrap","color":"#666","textAlign":"right","width":"auto","lineHeight":"80rpx","fontSize":"30rpx","fontWeight":"500"}' class="title">教师姓名</view>
				<input :style='{"border":"0","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"30rpx","height":"80rpx"}' :disabled="ro.jiaoshixingming" v-model="ruleForm.jiaoshixingming" placeholder="教师姓名"  type="text"></input>
			</view>
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 40rpx 0","borderColor":"#d6b8ff","alignItems":"center","borderRadius":"60rpx","borderWidth":"2rpx","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="">
				<view :style='{"padding":"0 20rpx 0 0","whiteSpace":"nowrap","color":"#666","textAlign":"right","width":"auto","lineHeight":"80rpx","fontSize":"30rpx","fontWeight":"500"}' class="title">申请时间</view>
				<input :disabled="ro.shenqingshijian" :style='{"border":"0","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"30rpx","height":"80rpx"}' v-model="ruleForm.shenqingshijian" placeholder="申请时间" @tap="toggleTab('shenqingshijian')"></input>
			</view>
               
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 40rpx 0","borderColor":"#d6b8ff","alignItems":"center","borderRadius":"60rpx","borderWidth":"2rpx","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="">
				<view :style='{"padding":"0 20rpx 0 0","whiteSpace":"nowrap","color":"#666","textAlign":"right","width":"auto","lineHeight":"80rpx","fontSize":"30rpx","fontWeight":"500"}' class="title">退费说明</view>
				<textarea :style='{"border":"0","padding":"24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"28rpx","height":"240rpx"}' v-model="ruleForm.tuifeishuoming" placeholder="退费说明" :maxlength="-1" :disabled="ro.tuifeishuoming"></textarea>
			</view>
			
			<view :style='{"width":"100%","margin":"40rpx 0","flexDirection":"column","justifyContent":"center","display":"flex","height":"auto"}' class="btn" >
				<button :style='{"border":"0","padding":"0","margin":"0 0 40rpx 0","color":"rgb(255, 255, 255)","borderRadius":"16rpx","background":"#d6b8ff","width":"100%","lineHeight":"96rpx","fontSize":"32rpx","height":"96rpx"}' @tap="onSubmitTap(null)" class="bg-red">提交</button>
			</view>
		</form>
		<w-picker  mode="dateTime" step="1" :current="false" :hasSecond="false" @confirm="shenqingshijianConfirm" ref="shenqingshijian" themeColor="#333333"></w-picker>
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
				kechenghao: '',
				kechengmingcheng: '',
				kechengfenlei: '',
				tupian: '',
				nandu: '',
				feiyong: '',
				xuehao: '',
				xingming: '',
				gonghao: '',
				jiaoshixingming: '',
				tuifeishuoming: '',
				shenqingshijian: '',
				shhf: '',
				ispay: '',
				},
				// 登录用户信息
				user: {},
				ro:{
				   kechenghao : false,
				   kechengmingcheng : false,
				   kechengfenlei : false,
				   tupian : false,
				   nandu : false,
				   feiyong : false,
				   xuehao : false,
				   xingming : false,
				   gonghao : false,
				   jiaoshixingming : false,
				   tuifeishuoming : false,
				   shenqingshijian : false,
				   sfsh : false,
				   shhf : false,
				   ispay : false,
				},
				virtualPay: false,
				showType: 1,
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
			this.ruleForm.shenqingshijian =  this.$utils.getCurDateTime();
			let table = uni.getStorageSync("nowTable");
			// 获取用户信息
			let res = await this.$api.session(table);
			this.user = res.data;
			
			// ss读取

			this.ro.shenqingshijian = true;
			// 跨表
			this.cross = options.cross;
			if(options.cross){
				var obj = uni.getStorageSync('crossObj');
				for (var o in obj){
					if(o=='kechenghao'){
						this.ruleForm.kechenghao = obj[o];
						this.ro.kechenghao = true;
						continue;
					}
					if(o=='kechengmingcheng'){
						this.ruleForm.kechengmingcheng = obj[o];
						this.ro.kechengmingcheng = true;
						continue;
					}
					if(o=='kechengfenlei'){
						this.ruleForm.kechengfenlei = obj[o];
						this.ro.kechengfenlei = true;
						continue;
					}
					if(o=='tupian'){
						this.ruleForm.tupian = obj[o].split(",")[0];
						this.ro.tupian = true;
						continue;
					}
					if(o=='nandu'){
						this.ruleForm.nandu = obj[o];
						this.ro.nandu = true;
						continue;
					}
					if(o=='feiyong'){
						this.ruleForm.feiyong = obj[o];
						this.ro.feiyong = true;
						continue;
					}
					if(o=='xuehao'){
						this.ruleForm.xuehao = obj[o];
						this.ro.xuehao = true;
						continue;
					}
					if(o=='xingming'){
						this.ruleForm.xingming = obj[o];
						this.ro.xingming = true;
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
					if(o=='tuifeishuoming'){
						this.ruleForm.tuifeishuoming = obj[o];
						this.ro.tuifeishuoming = true;
						continue;
					}
					if(o=='shenqingshijian'){
						this.ruleForm.shenqingshijian = obj[o];
						this.ro.shenqingshijian = true;
						continue;
					}
				}
			}


			// 如果有登录，获取登录后保存的userid
			this.ruleForm.userid = uni.getStorageSync("appUserid")
			if (options.refid) {
				// 如果上一级页面传递了refid，获取改refid数据信息
				this.ruleForm.refid = Number(options.refid);
				this.ruleForm.nickname = uni.getStorageSync("nickname");
			}
			// 如果是更新操作
			if (options.id) {
				this.ruleForm.id = options.id;
				// 获取信息
				res = await this.$api.info(`tuifeishenqing`, this.ruleForm.id);
				this.ruleForm = res.data;
			}
			this.$forceUpdate()
			if (uni.getStorageSync('raffleType') && uni.getStorageSync('raffleType') != null) {
				uni.removeStorageSync('raffleType')
				setTimeout(() => {
					this.onSubmitTap(null)
				}, 300)
			}
		},
		methods: {
			numberChange(e,name) {
				this.ruleForm[name] = Number(e.detail.value)
			},

			// 多级联动参数


			// 日长控件选择日期时间
			shenqingshijianConfirm(val) {
				this.ruleForm.shenqingshijian = val.result;
				this.$forceUpdate();
			},


			tupianTap() {
				if(this.ro.tupian){
					return false
				}
				let _this = this;
				this.$api.upload(function(res) {
					_this.ruleForm.tupian = 'upload/' + res.file;
					_this.$forceUpdate();
				});
			},

			getUUID () {
				return new Date().getTime();
			},
			async onSubmitTap(subMitType=null) {
				let that = this
				if(!this.ruleForm.id) {
					this.ruleForm.ispay = '未支付'
				}
				//跨表计算判断
				var obj;
				if(this.ruleForm.feiyong&&(!this.$validate.isNumber(this.ruleForm.feiyong))){
					this.$utils.msg(`费用应输入数字`);
					return
				}
				if((!this.ruleForm.tuifeishuoming)){
					this.$utils.msg(`退费说明不能为空`);
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
						let res = await this.$api.list(`tuifeishenqing`, params);
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
					await this.$api.update(`tuifeishenqing`, this.ruleForm);
				}else{
					oet = await this.$api.add(`tuifeishenqing`, this.ruleForm);
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
