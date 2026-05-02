<template>
<view class="content">
	<view class="box" :style='{"minHeight":"100vh","width":"100%","padding":"48rpx 24rpx 24rpx","position":"relative","background":"#fff","height":"auto"}'>
		<view :style='{"width":"100%","padding":"0","background":"none","display":"block","height":"auto"}'>
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 40rpx 0","borderColor":"#d6b8ff","alignItems":"center","borderRadius":"60rpx","borderWidth":"2rpx","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' v-if="tableName=='xuesheng'" class="">
				<view class="title" :style='{"width":"auto","padding":"0 20rpx 0 0","lineHeight":"80rpx","fontSize":"30rpx","color":"#333","textAlign":"right"}'>学号</view>
				<input :style='{"border":"0","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"30rpx","height":"80rpx"}' :disabled="ro.xuehao"  v-model="ruleForm.xuehao" placeholder="学号"></input>
			</view>
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 40rpx 0","borderColor":"#d6b8ff","alignItems":"center","borderRadius":"60rpx","borderWidth":"2rpx","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' v-if="tableName=='xuesheng'" class="">
				<view class="title" :style='{"width":"auto","padding":"0 20rpx 0 0","lineHeight":"80rpx","fontSize":"30rpx","color":"#333","textAlign":"right"}'>姓名</view>
				<input :style='{"border":"0","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"30rpx","height":"80rpx"}' :disabled="ro.xingming"  v-model="ruleForm.xingming" placeholder="姓名"></input>
			</view>
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 40rpx 0","borderColor":"#d6b8ff","alignItems":"center","borderRadius":"60rpx","borderWidth":"2rpx","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' v-if="tableName=='xuesheng'" @tap="xueshengtouxiangTap" class="">
				<view class="title" :style='{"width":"auto","padding":"0 20rpx 0 0","lineHeight":"80rpx","fontSize":"30rpx","color":"#333","textAlign":"right"}'>头像</view>
				<image :style='{"width":"80rpx","borderRadius":"100%","objectFit":"cover","display":"block","height":"80rpx"}' v-if="ruleForm.touxiang" style="margin: 0;" class="avator" :src="baseUrl+ruleForm.touxiang" mode=""></image>
				<image :style='{"width":"80rpx","borderRadius":"100%","objectFit":"cover","display":"block","height":"80rpx"}' v-else class="avator" style="margin: 0;" src="../../static/gen/upload.png" mode=""></image>
			</view>
			<view v-if="tableName=='xuesheng'" :style='{"padding":"4rpx 40rpx","margin":"0 0 40rpx 0","borderColor":"#d6b8ff","alignItems":"center","borderRadius":"60rpx","borderWidth":"2rpx","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class=" select">
				<view :style='{"width":"auto","padding":"0 20rpx 0 0","lineHeight":"80rpx","fontSize":"30rpx","color":"#333","textAlign":"right"}' class="title">性别</view>
				<picker :style='{"width":"100%","flex":"1","height":"auto"}' :disabled="ro.xingbie" @change="xueshengxingbieChange" :value="xueshengxingbieIndex" :range="xueshengxingbieOptions">
					<view :style='{"width":"100%","lineHeight":"80rpx","fontSize":"30rpx","color":"#d6b8ff"}' class="uni-input picker-select-input">{{ruleForm.xingbie?ruleForm.xingbie:"请选择性别"}}</view>
				</picker>
			</view>
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 40rpx 0","borderColor":"#d6b8ff","alignItems":"center","borderRadius":"60rpx","borderWidth":"2rpx","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' v-if="tableName=='xuesheng'" class="">
				<view class="title" :style='{"width":"auto","padding":"0 20rpx 0 0","lineHeight":"80rpx","fontSize":"30rpx","color":"#333","textAlign":"right"}'>年龄</view>
				<input :style='{"border":"0","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"30rpx","height":"80rpx"}' :disabled="ro.nianling"  v-model="ruleForm.nianling" placeholder="年龄"></input>
			</view>
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 40rpx 0","borderColor":"#d6b8ff","alignItems":"center","borderRadius":"60rpx","borderWidth":"2rpx","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' v-if="tableName=='xuesheng'" class="">
				<view class="title" :style='{"width":"auto","padding":"0 20rpx 0 0","lineHeight":"80rpx","fontSize":"30rpx","color":"#333","textAlign":"right"}'>目标院校</view>
				<input :style='{"border":"0","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"30rpx","height":"80rpx"}' :disabled="ro.mubiaoyuanxiao"  v-model="ruleForm.mubiaoyuanxiao" placeholder="目标院校"></input>
			</view>
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 40rpx 0","borderColor":"#d6b8ff","alignItems":"center","borderRadius":"60rpx","borderWidth":"2rpx","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' v-if="tableName=='xuesheng'" class="">
				<view class="title" :style='{"width":"auto","padding":"0 20rpx 0 0","lineHeight":"80rpx","fontSize":"30rpx","color":"#333","textAlign":"right"}'>班级</view>
				<input :style='{"border":"0","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"30rpx","height":"80rpx"}' :disabled="ro.banji"  v-model="ruleForm.banji" placeholder="班级"></input>
			</view>
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 40rpx 0","borderColor":"#d6b8ff","alignItems":"center","borderRadius":"60rpx","borderWidth":"2rpx","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' v-if="tableName=='xuesheng'" class="">
				<view class="title" :style='{"width":"auto","padding":"0 20rpx 0 0","lineHeight":"80rpx","fontSize":"30rpx","color":"#333","textAlign":"right"}'>备考科目</view>
				<input :style='{"border":"0","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"30rpx","height":"80rpx"}' :disabled="ro.beikaokemu"  v-model="ruleForm.beikaokemu" placeholder="备考科目"></input>
			</view>
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 40rpx 0","borderColor":"#d6b8ff","alignItems":"center","borderRadius":"60rpx","borderWidth":"2rpx","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' v-if="tableName=='xuesheng'" class="">
				<view class="title" :style='{"width":"auto","padding":"0 20rpx 0 0","lineHeight":"80rpx","fontSize":"30rpx","color":"#333","textAlign":"right"}'>基础水平</view>
				<input :style='{"border":"0","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"30rpx","height":"80rpx"}' :disabled="ro.jichushuiping"  v-model="ruleForm.jichushuiping" placeholder="基础水平"></input>
			</view>
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 40rpx 0","borderColor":"#d6b8ff","alignItems":"center","borderRadius":"60rpx","borderWidth":"2rpx","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' v-if="tableName=='xuesheng'" class="">
				<view class="title" :style='{"width":"auto","padding":"0 20rpx 0 0","lineHeight":"80rpx","fontSize":"30rpx","color":"#333","textAlign":"right"}'>手机号</view>
				<input :style='{"border":"0","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"30rpx","height":"80rpx"}' :disabled="ro.mobile"  v-model="ruleForm.mobile" placeholder="手机号"></input>
			</view>
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 40rpx 0","borderColor":"#d6b8ff","alignItems":"center","borderRadius":"60rpx","borderWidth":"2rpx","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' v-if="tableName=='xuesheng'" class="">
				<view class="title" :style='{"width":"auto","padding":"0 20rpx 0 0","lineHeight":"80rpx","fontSize":"30rpx","color":"#333","textAlign":"right"}'>密保问题</view>
				<input :style='{"border":"0","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"30rpx","height":"80rpx"}' :disabled="ro.pquestion"  v-model="ruleForm.pquestion" placeholder="密保问题"></input>
			</view>
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 40rpx 0","borderColor":"#d6b8ff","alignItems":"center","borderRadius":"60rpx","borderWidth":"2rpx","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' v-if="tableName=='xuesheng'" class="">
				<view class="title" :style='{"width":"auto","padding":"0 20rpx 0 0","lineHeight":"80rpx","fontSize":"30rpx","color":"#333","textAlign":"right"}'>密保答案</view>
				<input :style='{"border":"0","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"30rpx","height":"80rpx"}' :disabled="ro.panswer"  v-model="ruleForm.panswer" placeholder="密保答案"></input>
			</view>
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 40rpx 0","borderColor":"#d6b8ff","alignItems":"center","borderRadius":"60rpx","borderWidth":"2rpx","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' v-if="tableName=='jiaoshi'" class="">
				<view class="title" :style='{"width":"auto","padding":"0 20rpx 0 0","lineHeight":"80rpx","fontSize":"30rpx","color":"#333","textAlign":"right"}'>工号</view>
				<input :style='{"border":"0","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"30rpx","height":"80rpx"}' :disabled="ro.gonghao"  v-model="ruleForm.gonghao" placeholder="工号"></input>
			</view>
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 40rpx 0","borderColor":"#d6b8ff","alignItems":"center","borderRadius":"60rpx","borderWidth":"2rpx","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' v-if="tableName=='jiaoshi'" class="">
				<view class="title" :style='{"width":"auto","padding":"0 20rpx 0 0","lineHeight":"80rpx","fontSize":"30rpx","color":"#333","textAlign":"right"}'>教师姓名</view>
				<input :style='{"border":"0","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"30rpx","height":"80rpx"}' :disabled="ro.jiaoshixingming"  v-model="ruleForm.jiaoshixingming" placeholder="教师姓名"></input>
			</view>
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 40rpx 0","borderColor":"#d6b8ff","alignItems":"center","borderRadius":"60rpx","borderWidth":"2rpx","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' v-if="tableName=='jiaoshi'" @tap="jiaoshitouxiangTap" class="">
				<view class="title" :style='{"width":"auto","padding":"0 20rpx 0 0","lineHeight":"80rpx","fontSize":"30rpx","color":"#333","textAlign":"right"}'>头像</view>
				<image :style='{"width":"80rpx","borderRadius":"100%","objectFit":"cover","display":"block","height":"80rpx"}' v-if="ruleForm.touxiang" style="margin: 0;" class="avator" :src="baseUrl+ruleForm.touxiang" mode=""></image>
				<image :style='{"width":"80rpx","borderRadius":"100%","objectFit":"cover","display":"block","height":"80rpx"}' v-else class="avator" style="margin: 0;" src="../../static/gen/upload.png" mode=""></image>
			</view>
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 40rpx 0","borderColor":"#d6b8ff","alignItems":"center","borderRadius":"60rpx","borderWidth":"2rpx","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' v-if="tableName=='jiaoshi'" @tap="jiaoshizizhizhengmingTap" class="">
				<view class="title" :style='{"width":"auto","padding":"0 20rpx 0 0","lineHeight":"80rpx","fontSize":"30rpx","color":"#333","textAlign":"right"}'>资质证明</view>
				<input :style='{"border":"0","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"30rpx","height":"80rpx"}' v-if="ruleForm.zizhizhengming" v-model="ruleForm.zizhizhengming" placeholder="资质证明"></input>
				<image :style='{"width":"80rpx","borderRadius":"100%","objectFit":"cover","display":"block","height":"80rpx"}' v-else class="avator" src="../../static/gen/upload.png" mode=""></image>
			</view>
			<view v-if="tableName=='jiaoshi'" :style='{"padding":"4rpx 40rpx","margin":"0 0 40rpx 0","borderColor":"#d6b8ff","alignItems":"center","borderRadius":"60rpx","borderWidth":"2rpx","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class=" select">
				<view :style='{"width":"auto","padding":"0 20rpx 0 0","lineHeight":"80rpx","fontSize":"30rpx","color":"#333","textAlign":"right"}' class="title">性别</view>
				<picker :style='{"width":"100%","flex":"1","height":"auto"}' :disabled="ro.xingbie" @change="jiaoshixingbieChange" :value="jiaoshixingbieIndex" :range="jiaoshixingbieOptions">
					<view :style='{"width":"100%","lineHeight":"80rpx","fontSize":"30rpx","color":"#d6b8ff"}' class="uni-input picker-select-input">{{ruleForm.xingbie?ruleForm.xingbie:"请选择性别"}}</view>
				</picker>
			</view>
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 40rpx 0","borderColor":"#d6b8ff","alignItems":"center","borderRadius":"60rpx","borderWidth":"2rpx","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' v-if="tableName=='jiaoshi'" class="">
				<view class="title" :style='{"width":"auto","padding":"0 20rpx 0 0","lineHeight":"80rpx","fontSize":"30rpx","color":"#333","textAlign":"right"}'>年龄</view>
				<input :style='{"border":"0","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"30rpx","height":"80rpx"}' :disabled="ro.nianling"  v-model="ruleForm.nianling" placeholder="年龄"></input>
			</view>
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 40rpx 0","borderColor":"#d6b8ff","alignItems":"center","borderRadius":"60rpx","borderWidth":"2rpx","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' v-if="tableName=='jiaoshi'" class="">
				<view class="title" :style='{"width":"auto","padding":"0 20rpx 0 0","lineHeight":"80rpx","fontSize":"30rpx","color":"#333","textAlign":"right"}'>职称</view>
				<input :style='{"border":"0","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"30rpx","height":"80rpx"}' :disabled="ro.zhicheng"  v-model="ruleForm.zhicheng" placeholder="职称"></input>
			</view>
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 40rpx 0","borderColor":"#d6b8ff","alignItems":"center","borderRadius":"60rpx","borderWidth":"2rpx","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' v-if="tableName=='jiaoshi'" class="">
				<view class="title" :style='{"width":"auto","padding":"0 20rpx 0 0","lineHeight":"80rpx","fontSize":"30rpx","color":"#333","textAlign":"right"}'>擅长科目</view>
				<input :style='{"border":"0","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"30rpx","height":"80rpx"}' :disabled="ro.shanzhangkemu"  v-model="ruleForm.shanzhangkemu" placeholder="擅长科目"></input>
			</view>
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 40rpx 0","borderColor":"#d6b8ff","alignItems":"center","borderRadius":"60rpx","borderWidth":"2rpx","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' v-if="tableName=='jiaoshi'" class="">
				<view class="title" :style='{"width":"auto","padding":"0 20rpx 0 0","lineHeight":"80rpx","fontSize":"30rpx","color":"#333","textAlign":"right"}'>教学风格</view>
				<input :style='{"border":"0","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"30rpx","height":"80rpx"}' :disabled="ro.jiaoxuefengge"  v-model="ruleForm.jiaoxuefengge" placeholder="教学风格"></input>
			</view>
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 40rpx 0","borderColor":"#d6b8ff","alignItems":"center","borderRadius":"60rpx","borderWidth":"2rpx","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' v-if="tableName=='jiaoshi'" class="">
				<view class="title" :style='{"width":"auto","padding":"0 20rpx 0 0","lineHeight":"80rpx","fontSize":"30rpx","color":"#333","textAlign":"right"}'>手机号</view>
				<input :style='{"border":"0","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"30rpx","height":"80rpx"}' :disabled="ro.mobile"  v-model="ruleForm.mobile" placeholder="手机号"></input>
			</view>
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 40rpx 0","borderColor":"#d6b8ff","alignItems":"center","borderRadius":"60rpx","borderWidth":"2rpx","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' v-if="tableName=='jiaoshi'" class="">
				<view class="title" :style='{"width":"auto","padding":"0 20rpx 0 0","lineHeight":"80rpx","fontSize":"30rpx","color":"#333","textAlign":"right"}'>密保问题</view>
				<input :style='{"border":"0","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"30rpx","height":"80rpx"}' :disabled="ro.pquestion"  v-model="ruleForm.pquestion" placeholder="密保问题"></input>
			</view>
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 40rpx 0","borderColor":"#d6b8ff","alignItems":"center","borderRadius":"60rpx","borderWidth":"2rpx","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' v-if="tableName=='jiaoshi'" class="">
				<view class="title" :style='{"width":"auto","padding":"0 20rpx 0 0","lineHeight":"80rpx","fontSize":"30rpx","color":"#333","textAlign":"right"}'>密保答案</view>
				<input :style='{"border":"0","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"30rpx","height":"80rpx"}' :disabled="ro.panswer"  v-model="ruleForm.panswer" placeholder="密保答案"></input>
			</view>
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 40rpx 0","borderColor":"#d6b8ff","alignItems":"center","borderRadius":"60rpx","borderWidth":"2rpx","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' v-if="tableName=='jiaoshi'" class="">
				<view :style='{"width":"auto","padding":"0 20rpx 0 0","lineHeight":"80rpx","fontSize":"30rpx","color":"#333","textAlign":"right"}' class="title">辅导成果</view>
				<xia-editor :disabled="ro.fudaochengguo" ref="editor" :style='{"minHeight":"300rpx","border":"0px solid #efefef","width":"100%","fontSize":"30rpx","height":"auto"}' v-model="ruleForm.fudaochengguo" placeholder="辅导成果" @editorChange="fudaochengguoChange"></xia-editor>
			</view>
			<view :style='{"width":"100%","margin":"40rpx 0 0","flexDirection":"column","justifyContent":"center","display":"flex","height":"auto"}' class="btn">
				<button @tap="update()" class="cu-btn lg" :style='{"border":"0","padding":"0px","margin":"0 0 40rpx","color":"rgb(255, 255, 255)","borderRadius":"16rpx","background":"#d6b8ff","width":"100%","lineHeight":"96rpx","fontSize":"32rpx","height":"96rpx"}'>保存</button>
				<button @tap="logout()" class="cu-btn lg" :style='{"border":"0px solid #1ccb96","padding":"0px","margin":"0 0 20rpx","color":"#666","borderRadius":"16rpx","background":"#e7e7e7","width":"100%","lineHeight":"96rpx","fontSize":"30rpx","height":"96rpx"}'>退出登录</button>
			</view>
		</view>
	</view>
</view>
</template>

<script>

	import xiaEditor from '@/components/xia-editor/xia-editor';
	import multipleSelect from "@/components/momo-multipleSelect/momo-multipleSelect";
	export default {
		data() {
			return {
				ruleForm: {
				},
				tableName:"",
				ro: {},
				xueshengxingbieOptions: [],
				xueshengxingbieIndex: 0,
				jiaoshixingbieOptions: [],
				jiaoshixingbieIndex: 0,
			}
		},
		components: {
			multipleSelect,
			xiaEditor
		},
		computed: {
			baseUrl() {
				return this.$base.url;
			}
		},
		async onLoad() {
			let table = uni.getStorageSync("nowTable");
			let that = this
			let res = {}
			this.tableName = table;
			this.getSession()
			if(this.tableName=='xuesheng'){
				// 自定义下拉框值
				this.xueshengxingbieOptions = "男,女".split(',');
				this.xueshengxingbieOptions.forEach((item, index) => {
					if(item==this.ruleForm.xingbie) {
						this.xueshengxingbieIndex = index;
					}
				});
				this.ro = {
					xuehao: true,
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
				// 自定义下拉框值
				this.jiaoshixingbieOptions = "男,女".split(',');
				this.jiaoshixingbieOptions.forEach((item, index) => {
					if(item==this.ruleForm.xingbie) {
						this.jiaoshixingbieIndex = index;
					}
				});
				this.ro = {
					gonghao: true,
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
			this.styleChange()
			this.$forceUpdate()
		},
		methods: {
			async getSession(){
				let res = await this.$api.session(this.tableName)
				this.ruleForm = res.data;
				uni.setStorageSync('userSession',JSON.stringify(res.data))
			},
			xueshengtouxiangTap() {
				if(this.ro.touxiang){
					return false
				}
				let _this = this;
				this.$api.upload(function(res) {
					_this.ruleForm.touxiang = 'upload/' + res.file;
					_this.$forceUpdate();
				});
			},
			// 下拉变化
			xueshengxingbieChange(e) {
				this.xueshengxingbieIndex = e.target.value
				this.ruleForm.xingbie = this.xueshengxingbieOptions[this.xueshengxingbieIndex]
			},
			jiaoshitouxiangTap() {
				if(this.ro.touxiang){
					return false
				}
				let _this = this;
				this.$api.upload(function(res) {
					_this.ruleForm.touxiang = 'upload/' + res.file;
					_this.$forceUpdate();
				});
			},
			jiaoshizizhizhengmingTap() {
				if(this.ro.zizhizhengming){
					return false
				}
				let _this = this;
				this.$api.upload(function(res) {
					_this.ruleForm.zizhizhengming = 'upload/' + res.file;
					_this.$forceUpdate();
				});
			},
			// 下拉变化
			jiaoshixingbieChange(e) {
				this.jiaoshixingbieIndex = e.target.value
				this.ruleForm.xingbie = this.jiaoshixingbieOptions[this.jiaoshixingbieIndex]
			},
			fudaochengguoChange(e) {
				this.ruleForm.fudaochengguo = e
			},

			toggleTab(str,str1) {
				if(this.ro[str1]){
					return false
				}
				this.$refs[str + str1].show();
			},

			styleChange() {
				this.$nextTick(()=>{
					// document.querySelectorAll('. .uni-input-input').forEach(el=>{
					//   el.style.backgroundColor = this.userInfoForm.list.input.backgroundColor
					// })
				})
			},
			// 获取uuid
			getUUID () {
				return new Date().getTime();
			},
			logout() {
				uni.clearStorageSync()
				this.$utils.jump('../login/login');
			},
			// 注册
			async update() {
				if(`xuesheng` == this.tableName&&(!this.ruleForm.xuehao)){
					this.$utils.msg(`学号不能为空`);
					return
				}
				if(`xuesheng` == this.tableName&&(!this.ruleForm.mima)){
					this.$utils.msg(`密码不能为空`);
					return
				}
				if(`xuesheng` == this.tableName&&(!this.ruleForm.xingming)){
					this.$utils.msg(`姓名不能为空`);
					return
				}
				if(`xuesheng` == this.tableName && this.ruleForm.nianling&&(!this.$validate.isIntNumer(this.ruleForm.nianling))){
					this.$utils.msg(`年龄应输入整数`);
					return
				}
				if(`xuesheng` == this.tableName && this.ruleForm.mobile&&(!this.$validate.isMobile(this.ruleForm.mobile))){
					this.$utils.msg(`手机号应输入手机格式`);
					return
				}
				if(`xuesheng` == this.tableName && this.ruleForm.status&&(!this.$validate.isIntNumer(this.ruleForm.status))){
					this.$utils.msg(`状态应输入整数`);
					return
				}
				if(`jiaoshi` == this.tableName&&(!this.ruleForm.gonghao)){
					this.$utils.msg(`工号不能为空`);
					return
				}
				if(`jiaoshi` == this.tableName&&(!this.ruleForm.mima)){
					this.$utils.msg(`密码不能为空`);
					return
				}
				if(`jiaoshi` == this.tableName&&(!this.ruleForm.jiaoshixingming)){
					this.$utils.msg(`教师姓名不能为空`);
					return
				}
				if(`jiaoshi` == this.tableName&&(!this.ruleForm.zizhizhengming)){
					this.$utils.msg(`资质证明不能为空`);
					return
				}
				if(`jiaoshi` == this.tableName && this.ruleForm.nianling&&(!this.$validate.isIntNumer(this.ruleForm.nianling))){
					this.$utils.msg(`年龄应输入整数`);
					return
				}
				if(`jiaoshi` == this.tableName&&(!this.ruleForm.zhicheng)){
					this.$utils.msg(`职称不能为空`);
					return
				}
				if(`jiaoshi` == this.tableName && this.ruleForm.mobile&&(!this.$validate.isMobile(this.ruleForm.mobile))){
					this.$utils.msg(`手机号应输入手机格式`);
					return
				}
				if(`jiaoshi` == this.tableName && this.ruleForm.status&&(!this.$validate.isIntNumer(this.ruleForm.status))){
					this.$utils.msg(`状态应输入整数`);
					return
				}
				let table = uni.getStorageSync("nowTable");
				await this.$api.update(table, this.ruleForm);
				this.$utils.msgBack('修改成功');;
				this.getSession()
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
