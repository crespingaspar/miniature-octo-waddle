  

<template>
<view class="content">
	<view :style='{"width":"100%","padding":"24rpx","position":"relative","background":"#fff","height":"100%"}' v-if="showType==1">
		<form :style='{"width":"100%","padding":"48rpx 0 48rpx","background":"none","display":"block","height":"auto"}' class="app-update-pv">








































			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 40rpx 0","borderColor":"#d6b8ff","alignItems":"center","borderRadius":"60rpx","borderWidth":"2rpx","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="">
				<view :style='{"padding":"0 20rpx 0 0","whiteSpace":"nowrap","color":"#666","textAlign":"right","width":"auto","lineHeight":"80rpx","fontSize":"30rpx","fontWeight":"500"}' class="title">课程号</view>
				<view :style='{"padding":"0px 24rpx","margin":"0px","lineHeight":"80rpx","fontSize":"28rpx","color":"#000","flex":"1"}' class="right-input">
					{{ruleForm.kechenghao}}
				</view>
			</view>
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 40rpx 0","borderColor":"#d6b8ff","alignItems":"center","borderRadius":"60rpx","borderWidth":"2rpx","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="">
				<view :style='{"padding":"0 20rpx 0 0","whiteSpace":"nowrap","color":"#666","textAlign":"right","width":"auto","lineHeight":"80rpx","fontSize":"30rpx","fontWeight":"500"}' class="title">课程名称</view>
				<input :style='{"border":"0","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"30rpx","height":"80rpx"}' :disabled="ro.kechengmingcheng" v-model="ruleForm.kechengmingcheng" placeholder="课程名称"  type="text"></input>
			</view>
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 40rpx 0","borderColor":"#d6b8ff","alignItems":"center","borderRadius":"60rpx","borderWidth":"2rpx","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class=" select">
				<view :style='{"padding":"0 20rpx 0 0","whiteSpace":"nowrap","color":"#666","textAlign":"right","width":"auto","lineHeight":"80rpx","fontSize":"30rpx","fontWeight":"500"}' class="title">课程分类</view>
				<picker :disabled="ro.kechengfenlei" :style='{"width":"100%","flex":"1","height":"auto"}' @change="kechengfenleiChange" :value="kechengfenleiIndex" :range="kechengfenleiOptions">
					<view :style='{"width":"100%","lineHeight":"80rpx","fontSize":"30rpx","color":"#d6b8ff"}' class="uni-input">{{ruleForm.kechengfenlei?ruleForm.kechengfenlei:"请选择课程分类"}}</view>
				</picker>
			</view>
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 40rpx 0","borderColor":"#d6b8ff","alignItems":"center","borderRadius":"60rpx","borderWidth":"2rpx","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="" @tap="tupianTap">
				<view :style='{"padding":"0 20rpx 0 0","whiteSpace":"nowrap","color":"#666","textAlign":"right","width":"auto","lineHeight":"80rpx","fontSize":"30rpx","fontWeight":"500"}' class="title">图片</view>
				<image :style='{"width":"80rpx","borderRadius":"100%","objectFit":"cover","display":"block","height":"80rpx"}' class="avator" v-if="ruleForm.tupian" :src="baseUrl+ruleForm.tupian.split(',')[0]" mode="aspectFill"></image>
				<image :style='{"width":"80rpx","borderRadius":"100%","objectFit":"cover","display":"block","height":"80rpx"}' class="avator" v-else src="../../static/gen/upload.png" mode="aspectFill"></image>
			</view>
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 40rpx 0","borderColor":"#d6b8ff","alignItems":"center","borderRadius":"60rpx","borderWidth":"2rpx","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="">
				<view :style='{"padding":"0 20rpx 0 0","whiteSpace":"nowrap","color":"#666","textAlign":"right","width":"auto","lineHeight":"80rpx","fontSize":"30rpx","fontWeight":"500"}' class="title">上课时间</view>
				<input :style='{"border":"0","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"30rpx","height":"80rpx"}' :disabled="ro.shangkeshijian" v-model="ruleForm.shangkeshijian" placeholder="上课时间"  type="text"></input>
			</view>
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 40rpx 0","borderColor":"#d6b8ff","alignItems":"center","borderRadius":"60rpx","borderWidth":"2rpx","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="">
				<view :style='{"padding":"0 20rpx 0 0","whiteSpace":"nowrap","color":"#666","textAlign":"right","width":"auto","lineHeight":"80rpx","fontSize":"30rpx","fontWeight":"500"}' class="title">费用</view>
				<input :style='{"border":"0","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"30rpx","height":"80rpx"}' :disabled="ro.feiyong" v-model="ruleForm.feiyong" placeholder="费用" type="digit" @input="(e)=>numberChange(e,'feiyong')"></input>
			</view>
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 40rpx 0","borderColor":"#d6b8ff","alignItems":"center","borderRadius":"60rpx","borderWidth":"2rpx","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="">
				<view :style='{"padding":"0 20rpx 0 0","whiteSpace":"nowrap","color":"#666","textAlign":"right","width":"auto","lineHeight":"80rpx","fontSize":"30rpx","fontWeight":"500"}' class="title">难度</view>
				<input :style='{"border":"0","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"30rpx","height":"80rpx"}' :disabled="ro.nandu" v-model="ruleForm.nandu" placeholder="难度"  type="text"></input>
			</view>
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 40rpx 0","borderColor":"#d6b8ff","alignItems":"center","borderRadius":"60rpx","borderWidth":"2rpx","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="" @tap="kechengshipinTap">
				<view :style='{"padding":"0 20rpx 0 0","whiteSpace":"nowrap","color":"#666","textAlign":"right","width":"auto","lineHeight":"80rpx","fontSize":"30rpx","fontWeight":"500"}' class="title">课程视频</view>
				<input :style='{"border":"0","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"30rpx","height":"80rpx"}' v-if="ruleForm.kechengshipin"  v-model="baseUrl+ruleForm.kechengshipin" placeholder="课程视频"></input>
				<image :style='{"width":"80rpx","borderRadius":"100%","objectFit":"cover","display":"block","height":"80rpx"}' class="avator" v-else src="../../static/gen/upload.png" mode="aspectFill"></image>
			</view>
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 40rpx 0","borderColor":"#d6b8ff","alignItems":"center","borderRadius":"60rpx","borderWidth":"2rpx","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="">
				<view :style='{"padding":"0 20rpx 0 0","whiteSpace":"nowrap","color":"#666","textAlign":"right","width":"auto","lineHeight":"80rpx","fontSize":"30rpx","fontWeight":"500"}' class="title">课时</view>
				<input :style='{"border":"0","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"30rpx","height":"80rpx"}' :disabled="ro.keshi" v-model="ruleForm.keshi" placeholder="课时" type="digit" @input="(e)=>numberChange(e,'keshi')"></input>
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
				<view :style='{"padding":"0 20rpx 0 0","whiteSpace":"nowrap","color":"#666","textAlign":"right","width":"auto","lineHeight":"80rpx","fontSize":"30rpx","fontWeight":"500"}' class="title">课程大纲</view>
				<textarea :style='{"border":"0","padding":"24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"28rpx","height":"240rpx"}' v-model="ruleForm.kechengdagang" placeholder="课程大纲" :maxlength="-1" :disabled="ro.kechengdagang"></textarea>
			</view>
			<view :style='{"padding":"10rpx 20rpx","margin":"0","borderColor":"#D6B8FF","borderRadius":"40rpx","borderWidth":"2rpx","background":"#fff","width":"100%","borderStyle":"solid","height":"auto"}' class="">
				<view :style='{"width":"100%","lineHeight":"80rpx","fontSize":"30rpx","color":"#666","fontWeight":"500"}' class="title">课程内容</view>
				<xia-editor ref="kechengneirong" :style='{"minHeight":"300rpx","border":"0px solid #efefef","width":"100%","fontSize":"30rpx","height":"auto"}' v-model="ruleForm.kechengneirong" placeholder="课程内容" @editorChange="kechengneirongChange"></xia-editor>
			</view>
			
			<view :style='{"width":"100%","margin":"40rpx 0","flexDirection":"column","justifyContent":"center","display":"flex","height":"auto"}' class="btn" >
				<button :style='{"border":"0","padding":"0","margin":"0 0 40rpx 0","color":"rgb(255, 255, 255)","borderRadius":"16rpx","background":"#d6b8ff","width":"100%","lineHeight":"96rpx","fontSize":"32rpx","height":"96rpx"}' @tap="onSubmitTap(null)" class="bg-red">提交</button>
			</view>
		</form>
		<w-picker  mode="dateTime" step="1" :current="false" :hasSecond="false" @confirm="clicktimeConfirm" ref="clicktime" themeColor="#333333"></w-picker>
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
				kechenghao: this.getUUID(),
				kechengmingcheng: '',
				kechengfenlei: '',
				tupian: '',
				shangkeshijian: '',
				feiyong: '',
				nandu: '',
				kechengshipin: '',
				keshi: '',
				kechengdagang: '',
				kechengneirong: '',
				gonghao: '',
				jiaoshixingming: '',
				shhf: '',
				discussnum: '',
				totalscore: '',
				storeupnum: '',
				},
				kechengfenleiOptions: [],
				kechengfenleiIndex: 0,
				// 登录用户信息
				user: {},
				ro:{
				   kechenghao : false,
				   kechengmingcheng : false,
				   kechengfenlei : false,
				   tupian : false,
				   shangkeshijian : false,
				   feiyong : false,
				   nandu : false,
				   kechengshipin : false,
				   keshi : false,
				   kechengdagang : false,
				   kechengneirong : false,
				   gonghao : false,
				   jiaoshixingming : false,
				   sfsh : false,
				   shhf : false,
				   clicktime : false,
				   clicknum : false,
				   discussnum : false,
				   totalscore : false,
				   storeupnum : false,
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
			let table = uni.getStorageSync("nowTable");
			// 获取用户信息
			let res = await this.$api.session(table);
			this.user = res.data;
			
			// ss读取
			this.ruleForm.gonghao = this.user.gonghao
			this.ro.gonghao = true;
			this.ruleForm.jiaoshixingming = this.user.jiaoshixingming
			this.ro.jiaoshixingming = true;

			this.ro.gonghao = true;
			this.ro.jiaoshixingming = true;
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
						for(let x in this.kechengfenleiOptions) {
							if(this.kechengfenleiOptions[x] == this.ruleForm.kechengfenlei) {
								this.kechengfenleiIndex = Number(x)
							}
						}
						this.ro.kechengfenlei = true;
						continue;
					}
					if(o=='tupian'){
						this.ruleForm.tupian = obj[o].split(",")[0];
						this.ro.tupian = true;
						continue;
					}
					if(o=='shangkeshijian'){
						this.ruleForm.shangkeshijian = obj[o];
						this.ro.shangkeshijian = true;
						continue;
					}
					if(o=='feiyong'){
						this.ruleForm.feiyong = obj[o];
						this.ro.feiyong = true;
						continue;
					}
					if(o=='nandu'){
						this.ruleForm.nandu = obj[o];
						this.ro.nandu = true;
						continue;
					}
					if(o=='kechengshipin'){
						this.ruleForm.kechengshipin = obj[o];
						this.ro.kechengshipin = true;
						continue;
					}
					if(o=='keshi'){
						this.ruleForm.keshi = obj[o];
						this.ro.keshi = true;
						continue;
					}
					if(o=='kechengdagang'){
						this.ruleForm.kechengdagang = obj[o];
						this.ro.kechengdagang = true;
						continue;
					}
					if(o=='kechengneirong'){
						this.ruleForm.kechengneirong = obj[o];
						this.ro.kechengneirong = true;
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
					if(o=='clicktime'){
						this.ruleForm.clicktime = obj[o];
						this.ro.clicktime = true;
						continue;
					}
					if(o=='clicknum'){
						this.ruleForm.clicknum = obj[o];
						this.ro.clicknum = true;
						continue;
					}
					if(o=='discussnum'){
						this.ruleForm.discussnum = obj[o];
						this.ro.discussnum = true;
						continue;
					}
					if(o=='totalscore'){
						this.ruleForm.totalscore = obj[o];
						this.ro.totalscore = true;
						continue;
					}
					if(o=='storeupnum'){
						this.ruleForm.storeupnum = obj[o];
						this.ro.storeupnum = true;
						continue;
					}
				}
			}

			var kechengfenleiRefParams = {}
			// 下拉框
			res = await this.$api.option(`kechengfenlei`,`kechengfenlei`,kechengfenleiRefParams);
			this.kechengfenleiOptions = res.data;
			this.kechengfenleiOptions.unshift("请选择课程分类");
			if(this.ruleForm.kechengfenlei) {
				for(let x in this.kechengfenleiOptions) {
					if(this.kechengfenleiOptions[x] == this.ruleForm.kechengfenlei) {
						this.kechengfenleiIndex = Number(x)
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
				res = await this.$api.info(`kechengxinxi`, this.ruleForm.id);
				if(res.data.kechengfenlei) {
					for(let x in this.kechengfenleiOptions) {
						if(this.kechengfenleiOptions[x] == res.data.kechengfenlei) {
							this.kechengfenleiIndex = Number(x)
						}
					}
				}
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
			kechengneirongChange(e) {
				this.ruleForm.kechengneirong = e
			},

			// 多级联动参数


			// 日长控件选择日期时间
			clicktimeConfirm(val) {
				this.ruleForm.clicktime = val.result;
				this.$forceUpdate();
			},

			// 下拉变化
			kechengfenleiChange(e) {
				this.kechengfenleiIndex = e.target.value
				this.ruleForm.kechengfenlei = this.kechengfenleiOptions[this.kechengfenleiIndex]
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
			kechengshipinTap () {
				let _this = this;
				if(this.ro.kechengshipin){
					return false
				}
				this.$api.uploadMedia(function(res) {
					_this.ruleForm.kechengshipin = 'upload/' + res.file;
					_this.$forceUpdate();
				});
			},

			getUUID () {
				return new Date().getTime();
			},
			async onSubmitTap(subMitType=null) {
				let that = this
				if(this.ruleForm.kechenghao){
					this.ruleForm.kechenghao = String(this.ruleForm.kechenghao)
				}
				//跨表计算判断
				var obj;
				if((!this.ruleForm.kechenghao)){
					this.$utils.msg(`课程号不能为空`);
					return
				}
				if((!this.ruleForm.kechengmingcheng)){
					this.$utils.msg(`课程名称不能为空`);
					return
				}
				if((!this.ruleForm.kechengfenlei)){
					this.$utils.msg(`课程分类不能为空`);
					return
				}
				if((!this.ruleForm.feiyong)){
					this.$utils.msg(`费用不能为空`);
					return
				}
				if(this.ruleForm.feiyong&&(!this.$validate.isNumber(this.ruleForm.feiyong))){
					this.$utils.msg(`费用应输入数字`);
					return
				}
				if((!this.ruleForm.keshi)){
					this.$utils.msg(`课时不能为空`);
					return
				}
				if(this.ruleForm.keshi&&(!this.$validate.isNumber(this.ruleForm.keshi))){
					this.$utils.msg(`课时应输入数字`);
					return
				}
				if(this.ruleForm.clicknum&&(!this.$validate.isIntNumer(this.ruleForm.clicknum))){
					this.$utils.msg(`点击次数应输入整数`);
					return
				}
				if(this.ruleForm.discussnum&&(!this.$validate.isIntNumer(this.ruleForm.discussnum))){
					this.$utils.msg(`评论数应输入整数`);
					return
				}
				if(this.ruleForm.totalscore&&(!this.$validate.isNumber(this.ruleForm.totalscore))){
					this.$utils.msg(`评分应输入数字`);
					return
				}
				if(this.ruleForm.storeupnum&&(!this.$validate.isIntNumer(this.ruleForm.storeupnum))){
					this.$utils.msg(`收藏数应输入整数`);
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
						let res = await this.$api.list(`kechengxinxi`, params);
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
					await this.$api.update(`kechengxinxi`, this.ruleForm);
				}else{
					oet = await this.$api.add(`kechengxinxi`, this.ruleForm);
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
