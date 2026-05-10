<template>
	<view>
		<view class="cu-chat" :style="{'padding-bottom': tabBarHeight + 'px'}">
			<view v-for="(item,index) in list" v-bind:key="index" v-if="item.type!=4">
				<view v-if="item.addtime" style="width: 100%;text-align: center;font-size: 10px;color: #666;">{{timeFormat(item.addtime)}}</view>
				<view v-if="item.ask" class="cu-item self">
					<view class="main">
						<view class="content bg-green shadow">
							<text v-if="item.type==1">{{item.ask}}</text>
							<image :src="baseUrl + item.ask" v-else-if="item.type==2" mode="aspectFill" style="width: 150px;" @click="imgView(item.ask)"></image>
							<video :src="baseUrl + item.ask" v-else-if="item.type==3" style="width: 150px;" controls></video>
						</view>
					</view>
					<img class="cu-avatar radius" :src="baseUrl+item.uimage" />
				</view>
				<view v-if="item.reply" class="cu-item">
					<img v-if="item.uimage" class="cu-avatar radius" :src="baseUrl+item.uimage" />
					<img v-else class="cu-avatar radius" src="@/static/service.png" />
					<view class="main">
						<view class="content shadow">
							<text v-if="item.type==1">{{item.reply}}</text>
							<image :src="baseUrl + item.reply" v-else-if="item.type==2" mode="aspectFill" style="width: 150px;" @click="imgView(item.reply)"></image>
							<video :src="baseUrl + item.reply" v-else-if="item.type==3" style="width: 150px;" controls></video>
						</view>
					</view>
				</view>
			</view>
		</view>
		<view style="width: 100%;height: 45px;"></view>
		<view class="cu-bar foot input" :style="{'bottom':showType?((45 + tabBarHeight) + 'px'):(tabBarHeight + 'px')}">
			<image class="jiahao" src="/static/jiahao.png" mode="widthFix" @click="showClick"></image>
			<input v-model="ask" class="solid-bottom" :adjust-position="false" :focus="false" maxlength="300"
				cursor-spacing="10" placeholder="请输入您的问题..."></input>
			<button @tap="onSendTap" class="cu-btn bg-green shadow">发送</button>
		</view>
		<view class="showView" v-if="showType" :style='{"bottom":tabBarHeight + "px"}'>
			<view class="showBox" @click="changeImg">选择图片</view>
			<view class="showBox" @click="changeVideo">选择视频</view>
			<view class="showBox" @click="changeClick">转{{askType==1?'人工服务':'智能回复'}}</view>
		</view>
	</view>
</template>

<script>
	import timeMethod from '@/utils/timeMethod'
	export default {
		data() {
			return {
				list: [],
				ask: '',
				showType: false,
				tabBarHeight: 0,
				userid: 0,
				askType: 1 // 1为智能回复 2为人工
			};
		},
		async onShow(){
			let _this = this;
			uni.getSystemInfo({
				success: function(info) {
					_this.tabBarHeight = info.windowBottom
					_this.$forceUpdate()
				}
			});
			this.userid = uni.getStorageSync("appUserid")
			await this.init()
			if(this.list.length==0) {
				this.initWelcome();
			}
		},
		onHide() {
			uni.closeSocket()
		},
		computed: {
			baseUrl() { return this.$base.url; },
		},
		methods: {
			async initWelcome() {
				await this.$api.save('chat', {
					reply: "您好！我是DeepSeek智能助手，我可以为您制定学习计划、推荐课程。请问有什么可以帮您？",
					userid: Number(this.userid),
					type: 1
				});
				this.init();
			},
			formatMessages(messages) {
				let lastTime = null;
				messages.forEach((message) => {
					const currentTime = new Date(message.addtime).getTime();
					if (lastTime !== null) {
						if ((currentTime - lastTime) / 1000 / 60 < 3) {
							message.addtime = ''; 
						}
					}
					lastTime = currentTime;
				});
				return messages;
			},
			timeFormat(time) {
				if(!time) return '';
				const Time = timeMethod.getTime(time).split("T");
				return Time[1].slice(0, 5);
			},
			showClick() {
				this.showType = !this.showType
				this.scrollBottom()
			},
			changeImg(){
				let _this = this;
				this.$api.upload(function(res) { _this.save('upload/' + res.file,2) });
			},
			changeVideo(){
				let _this = this;
				this.$api.uploadMedia(function(res) { _this.save('upload/' + res.file,3) });
			},
			changeClick() {
				this.showType = !this.showType
				this.askType = this.askType == 1 ? 2 : 1
				if(this.askType==1) { uni.closeSocket() }
				if(this.askType==2) {
					uni.connectSocket({ url: 'ws' + this.$base.url.split('http')[1] + '/ws?user_id=' + this.userid + '&to_id=1' })
					uni.onSocketMessage(() => { this.init() })
				}
			},
			imgView(img){ uni.previewImage({ urls: [this.baseUrl + img] }); },
			async onSendTap() { await this.save() },
			
			// --- 原本地匹配逻辑已弃用并注释 ---
			// async intelligenceGet(ask) { ... }
			// async intelligenceSave(ask) { ... }

			async save(asks = null, type = 1) {
				const content = asks ? asks : this.ask;
				if(!content) return;

				// 1. 发送提问给后端
				await this.$api.add('chat', {
					ask: content,
					uimage: uni.getStorageSync('frontHeadportrait'),
					uname: uni.getStorageSync('nickname'),
					type: type,
					userid: this.userid,
					isreply: 0 
				});

				this.ask = '';
				
				// 2. 立即刷新，显示用户的话
				await this.init();

				// 3. 针对智能模式开启多重自动刷新
				if (this.askType == 1 && !asks) {
					uni.showLoading({ title: 'AI 正在思考中...' });
					
					// 第一次刷新：3秒后（应对短回答）
					setTimeout(async () => {
						await this.init();
					}, 3000);

					// 第二次刷新：7秒后（应对长计划、长回答）
					setTimeout(async () => {
						await this.init();
						uni.hideLoading();
					}, 7000);
				}
			},
			async init() {
				this.userid = uni.getStorageSync("appUserid")
				let res = await this.$api.page('chat', {
					sort: 'addtime',
					order: 'asc',
					limit: 1000,
				});
				this.list = this.formatMessages(res.data.list);
				this.scrollBottom()
			},
			scrollBottom(){
				this.$nextTick(() => {
					setTimeout(() => {
						uni.pageScrollTo({ scrollTop: 99999, duration: 0 })
					}, 200)
				})
			},
		}
	}
</script>

<style lang="scss">
	page { padding-bottom: 100upx; }
	.jiahao { width: 50upx; margin: 0 10upx; }
	.showView {
		width: 100%; background: #fff; position: fixed; bottom: 0; left: 0; display: flex; align-items: center;
		.showBox {
			width: 100%; line-height: 80upx; color: #fff; background: #007AFF;
			text-align: center; font-size: 28upx; margin: 0 5upx 10upx;
		}
	}
</style>