<template>
<view class="content">
	<view :style='{"width":"100%","padding":"24rpx","position":"relative","background":"#fff","height":"100%"}' v-if="showType==1">
		<form :style='{"width":"100%","padding":"48rpx 0 48rpx","background":"none","display":"block","height":"auto"}' class="app-update-pv">
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 40rpx 0","borderColor":"#d6b8ff","alignItems":"center","borderRadius":"60rpx","borderWidth":"2rpx","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="">
				<view :style='{"padding":"0 20rpx 0 0","whiteSpace":"nowrap","color":"#666","textAlign":"right","width":"auto","lineHeight":"80rpx","fontSize":"30rpx","fontWeight":"500"}' class="title">提问</view>
				<input :style='{"border":"0","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"30rpx","height":"80rpx"}' :disabled="ro.ask" v-model="ruleForm.ask" placeholder="请输入您的问题..."  type="text"></input>
			</view>
	
			<view :style='{"width":"100%","margin":"40rpx 0","flexDirection":"column","justifyContent":"center","display":"flex","height":"auto"}' class="btn" >
				<button :style='{"border":"0","padding":"0","margin":"0 0 40rpx 0","color":"rgb(255, 255, 255)","borderRadius":"16rpx","background":"#d6b8ff","width":"100%","lineHeight":"96rpx","fontSize":"32rpx","height":"96rpx"}' @tap="onSubmitTap(null)" class="bg-red">提交提问</button>
			</view>
		</form>
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
					ask: '',
					reply: '',
					userid: '',
				},
				user: {},
				ro:{
					ask : false,
					reply : false,
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
			let res = await this.$api.session(table);
			this.user = res.data;
			
			this.cross = options.cross;
			if(options.cross){
				var obj = uni.getStorageSync('crossObj');
				for (var o in obj){
					if(o=='ask'){
						this.ruleForm.ask = obj[o];
						this.ro.ask = true;
						continue;
					}
					if(o=='reply'){
						this.ruleForm.reply = obj[o];
						this.ro.reply = true;
						continue;
					}
				}
			}

			this.ruleForm.userid = uni.getStorageSync("appUserid")
			if (options.refid) {
				this.ruleForm.refid = Number(options.refid);
				this.ruleForm.nickname = uni.getStorageSync("nickname");
			}
			if (options.id) {
				this.ruleForm.id = options.id;
				res = await this.$api.info(`chathelper`, this.ruleForm.id);
				this.ruleForm = res.data;
			}
			this.$forceUpdate()
		},
		methods: {
			getUUID () {
				return new Date().getTime();
			},
			async onSubmitTap(subMitType=null) {
				if(!this.ruleForm.ask){
					this.$utils.msg('请填写提问内容');
					return
				}
				
				let that = this
				// 跨表逻辑保持不变
				if(this.cross){
					var statusColumnName = uni.getStorageSync('statusColumnName');
					var statusColumnValue = uni.getStorageSync('statusColumnValue');
					if(statusColumnName!='') {
						var obj = uni.getStorageSync('crossObj');
						if(!statusColumnName.startsWith("[")) {
							for (var o in obj){
								if(o==statusColumnName) obj[o] = statusColumnValue;
							}
							var table = uni.getStorageSync('crossTable');
							await this.$api.update(`${table}`, obj);
						}
					}
				}

				// 提交数据
				if(this.ruleForm.id){
					await this.$api.update(`chathelper`, this.ruleForm);
				}else{
					await this.$api.add(`chathelper`, this.ruleForm);
				}
				
				if(this.cross){
					uni.setStorageSync('crossCleanType',true);
				}
				
				// 接入DeepSeek后的特殊处理：显示加载中并延迟跳转
				uni.showLoading({
					title: 'AI正在思考中...'
				});

				setTimeout(() => {
					uni.hideLoading();
					that.$utils.msgBack('提交成功');
					// 延迟跳转回列表页，这样列表接口被重新调用时就能查到 AI 的回复记录了
				}, 2500); 
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
				if(this.ro[str]) return false;
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