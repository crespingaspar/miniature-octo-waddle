<template>
	<view :style='{"minHeight":"100vh","padding":"0px","flexWrap":"wrap","background":"#fff","display":"flex","width":"100%","position":"relative","height":"auto"}'>
		<view class="title" :style='{"padding":"0","margin":"0","color":"#000","borderRadius":"20rpx 20rpx 0 0","textAlign":"center","background":"#ffffff","width":"100%","lineHeight":"80rpx","fontSize":"36rpx"}'>{{detail.title}}</view>
		
		<view :style='{"alignItems":"center","color":"#999","flexWrap":"wrap","background":"#ffffff","display":"flex","width":"100%","fontSize":"28rpx","justifyContent":"center"}'>
			<view :style='{"width":"100%","padding":"0 20rpx","margin":"20rpx 0","textAlign":"right","order":"2"}'>
				<text class="icon iconfont icon-shijian21" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"28rpx","color":"#999"}'></text>
				<text :style='{"color":"#999","lineHeight":"1.5","fontSize":"28rpx"}'>发布时间：{{detail.addtime}}</text>
			</view>
			<view :style='{"padding":"0","margin":"0 20rpx 0 0"}'>
				<text class="icon iconfont icon-geren16" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"28rpx","color":"#999"}'></text>
				<text :style='{"color":"#999","lineHeight":"1.5","fontSize":"28rpx"}'>{{detail.name}}</text>
			</view>
		</view>
		
		<view class="content" :style='{"border":"0px solid #dddddd","padding":"24rpx","margin":"0","background":"#fff","width":"100%","lineHeight":"1.5","height":"auto"}'>
			<rich-text class="ql-snow ql-editor" :nodes="detail.content"></rich-text>
		</view>
		
		<div :style='{"padding":"0 20rpx 0 20rpx","margin":"0","borderRadius":"0px","background":"#fff","display":"flex","width":"100%","justifyContent":"space-between","height":"auto","order":"6"}'>
			<div @click="prepDetailClick" :style='{"padding":"0 20rpx 0 20rpx","margin":"0 40rpx 0 0","color":"#fff","borderRadius":"20rpx","textAlign":"center","background":"#ccaafa","width":"auto","lineHeight":"72rpx","fontSize":"28rpx"}'>上一篇：prev</div>
			<div @click="nextDetailClick" :style='{"padding":"0 20rpx 0 20rpx","color":"#fff","borderRadius":"20rpx","textAlign":"center","background":"#8599fd","width":"auto","lineHeight":"72rpx","fontSize":"28rpx"}'>下一篇：next</div>
		</div>
		


	</view>
</template>
<script>
	export default {
		data() {
			return {
				detail: {},
				id: '',
				allList: [],
				currentIndex: 0,
			}
		},
		async onLoad(options) {
			this.id = options.id;
			this.getInfo()
		},
		computed:{
			baseUrl() {
				return this.$base.url;
			},
			userid(){
				return uni.getStorageSync('appUserid')
			}
		},
		// #ifdef MP-WEIXIN
		onShareAppMessage(){
			var obj = {
				title: this.detail.title,
				imageUrl: this.detail.picture?this.baseUrl + this.detail.picture.split(',')[0]: ''
			}
			return obj
		},
		// #endif
		methods: {
			async getInfo(){
				let res = await this.$api.info('news', this.id)
				res.data.content = res.data.content.replace(/<img/g,'<img style="width: 100%;"');
				this.detail = res.data;
				uni.pageScrollTo({
					scrollTop: 0,
					duration: 100,
				})
				res = await this.$api.list('news',{page:1,limit:100,sort:'addtime',order:'desc'})
				for(let x in res.data.list){
					if(res.data.list[x].id == this.id){
						this.currentIndex = Number(x)
						break
					}
				}
				this.allList = res.data.list
				
			},
			// 上一篇
			prepDetailClick(){
				if(this.currentIndex == 0){
					this.$utils.msg('已经是第一篇了')
					return false
				}
				this.currentIndex--
				this.id = this.allList[this.currentIndex].id
				this.getInfo()
			},
			// 下一篇
			nextDetailClick(){
				if(this.currentIndex == this.allList.length - 1){
					this.$utils.msg('已经是最后一篇了')
					return false
				}
				this.currentIndex++
				this.id = this.allList[this.currentIndex].id
				this.getInfo()
			},
			toDetail(id){
				this.id = id
				this.getInfo()
			},
		}
	}
</script>

<style scoped lang="scss">
	page {
		background: #FFFFFF;
	}
</style>
