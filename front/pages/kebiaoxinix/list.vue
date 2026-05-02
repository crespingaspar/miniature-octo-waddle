  <template>
	<mescroll-uni @init="mescrollInit" :up="upOption" :down="downOption" @down="downCallback" @up="upCallback" @scroll="scrollChange">
		<view class="content">
			<view :style='{"width":"100%","padding":"0 20rpx 48rpx","position":"relative","background":"#fff","height":"auto"}'>
				<view class="cu-bar bg-white search" :style='{"width":"100%","padding":"20rpx 0 20rpx 0","background":"none","display":"flex","height":"auto"}'>
					<view  :style='{"margin":"0 12rpx 0 12rpx","flex":"1","position":"relative"}' class="search-form round">
						<text class="icon iconfont icon-fangdajing07" :style='{"color":"#d6b8ff","left":"0px","textAlign":"center","display":"none","width":"80rpx","fontSize":"40rpx","lineHeight":"80rpx","position":"absolute","right":"0px"}'></text>
						<input placeholder-style="color: #999" :style='{"border":"0px solid #174add","padding":"12rpx 20rpx 12rpx 20rpx","color":"#333","borderRadius":"12rpx","background":"#ebdcff","width":"100%","lineHeight":"56rpx","fontSize":"28rpx","height":"80rpx"}' v-model="searchForm.kechengmingcheng" type="text" placeholder="课程名称" ></input>
					</view>
					<button :style='{"border":"0","padding":"0px","margin":"0","color":"#fff","borderRadius":"12rpx","background":"#8599fd","width":"136rpx","lineHeight":"80rpx","fontSize":"28rpx","height":"80rpx"}' @tap="search" class="cu-btn shadow-blur round">搜索</button>
				</view>
			

				<view :style='{"alignContent":"flex-start","alignItems":"flex-start","flexWrap":"wrap","background":"none","display":"flex","width":"100%","height":"auto"}'>
					<timetable style="width: 100%;" v-if="show" :timetables="timetables" :timetableType="timetableType"
						:jiaoshi="jiaoshi" :ids="ids" @courseClick="onDetailTap"></timetable>

			
			
			
			
				</view>
				<button :style='{"border":"0","padding":"0","whiteSpace":"nowrap","color":"#fff","alignItems":"center","display":"flex","justifyContent":"center","outline":"none","borderRadius":"8rpx","top":"120rpx","left":"10rpx","background":"#EF7061","width":"80rpx","lineHeight":"80rpx","fontSize":"28rpx","position":"absolute","height":"80rpx","zIndex":"999"}' v-if="userid && isAuth('kebiaoxinix','新增')" class="add-btn" @click="onAddTap()">新增</button>
				<button :style='{"border":"0","padding":"0","whiteSpace":"nowrap","color":"#fff","alignItems":"center","display":"flex","justifyContent":"center","outline":"none","borderRadius":"8rpx","top":"120rpx","left":"10rpx","background":"#EF7061","width":"80rpx","lineHeight":"80rpx","fontSize":"28rpx","position":"absolute","height":"80rpx","zIndex":"999"}' v-if="!userid && isAuthFront('kebiaoxinix','新增')" class="add-btn" @click="onAddTap()">新增</button>
			</view>
		</view>
		<!-- <view v-if="scrollTop>200" @tap="scrollTopClick" :style='{"boxShadow":"0 8rpx 16rpx rgba(0,0,0,.3)","borderRadius":"50%","textAlign":"center","bottom":"20%","background":"#ff000030","width":"60rpx","lineHeight":"60rpx","position":"fixed","right":"20rpx","height":"60rpx","zIndex":"9999"}'>
			<span class="icon iconfont icon-jiantou07" :style='{"color":"#fff"}'></span>
		</view> -->
	</mescroll-uni>
</template>

<script>
	import Timetable from '@/components/lpx-timetable/lpx-timetable'
	export default {
		data() {
			return {
				btnColor: ['#409eff','#67c23a','#909399','#e6a23c','#f56c6c','#356c6c','#351c6c','#f093a9','#a7c23a','#104eff','#10441f','#a21233','#503319'],
				list: [],
				lists: [],
                userid: '',
				mescroll: null, //mescroll实例对象
				downOption: {
					auto: false //是否在初始化后,自动执行下拉回调callback; 默认true
				},
				upOption: {
					noMoreSize: 5, //如果列表已无数据,可设置列表的总数量要大于半页才显示无更多数据;避免列表数据过少(比如只有一条数据),显示无更多数据会不好看; 默认5
					textNoMore: '~ 没有更多了 ~',
					onScroll: true,
					toTop: true
				},
				hasNext: true,
				searchForm:{
					kechengmingcheng: '',
				},
				CustomBar: '0',
				listSort: 'id',
				listOrder: 'desc',
				timetables: [],
				timetableType: [],
				jiaoshi: [],
				ids: [],
				show: false,
				screenBoxShow: false,
				scrollTop: 0,
			};
		},
		watch: {
		},
		mounted() {
		},
		computed: {
			baseUrl() {
				return this.$base.url;
			},
			username() {
				return uni.getStorageSync("nickname")
			},
			role() {
				return uni.getStorageSync("appRole")
			},
		},
		onUnload() {
			uni.removeStorageSync("useridTag");
		},
		async onShow() {
			if(uni.getStorageSync("useridTag")==1){
				this.userid=uni.getStorageSync("useridTag");
				let remindRes = null
			} else {
				this.userid = "";
			}
			this.btnColor = this.btnColor.sort(()=> {
				return (0.5-Math.random());
			});
			this.hasNext = true
			// 重新加载数据
			if (this.mescroll) this.mescroll.resetUpScroll()
			this.$forceUpdate()
		},
		async onLoad(options) {
			this.hasNext = true
		},
		components: {
			Timetable,
		},
		methods: {
			queryChange(arr){
				for(let x in arr) {
					if(arr[x] == this.role) {
						return true
					}
				}
				return false
			},
			scrollChange(e){
				this.scrollTop = e.scrollTop
			},
			scrollTopClick(){
				uni.pageScrollTo({
					scrollTop: 0
				})
			},
			screenReset(){
				this.searchForm = {}
				this.search()
				this.$forceUpdate()
			},
			uGetRect(selector, all) {
				return new Promise(resolve => {
					uni.createSelectorQuery()
					.in(this)
					[all ? 'selectAll' : 'select'](selector)
					.boundingClientRect(rect => {
						if (all && Array.isArray(rect) && rect.length) {
							resolve(rect);
						}
						if (!all && rect) {
							resolve(rect);
						}
					})
					.exec();
				});
			},
			cloneData(data) {
				return JSON.parse(JSON.stringify(data));
			},
			sortClick(type){
				if(this.listSort==type){
					if(this.listOrder == 'desc'){
						this.listOrder = 'asc'
					}else{
						this.listOrder = 'desc'
					}
				}else{
					this.listSort = type
					this.listOrder = 'desc'
				}
				this.search()
			},
            priceChange(price) {
                return Number(price).toFixed(2);
            },
            preHttp(str) {
                return str && str.substr(0,4)=='http';
            },
			//类别搜索
			// mescroll组件初始化的回调,可获取到mescroll对象
			mescrollInit(mescroll) {
				this.mescroll = mescroll;
			},
			/*下拉刷新的回调 */
			downCallback(mescroll) {
				this.hasNext = true
				// 重置分页参数页数为1
				mescroll.resetUpScroll()
			},
			async getTimeList() {
				let res = await this.$api.list('scheduletime', {
					page: 1,
					limit: 100
				})
				for (let i = 0; i < 7; i++) {
					this.timetables.push([])
					this.jiaoshi.push([])
					this.ids.push([])
				}
				for (let x in res.data.list) {
					this.timetableType.push({
						index: res.data.list[x].sectionnum,
						name: `${res.data.list[x].starttime}\n${res.data.list[x].endtime}`
					})
					for (let j in this.timetables) {
						this.timetables[j].push('')
						this.jiaoshi[j].push('')
						this.ids[j].push('')
					}
				}
			},
			/*上拉加载的回调: mescroll携带page的参数, 其中num:当前页 从1开始, size:每页数据条数,默认10 */
			async upCallback(mescroll) {
				if (mescroll.num > 1||this.list.length>0) {
					return false
				}
				let params = {
					page: mescroll.num,
					limit: 100,
				}
				params['sort'] = this.listSort;
				params['order'] = this.listOrder;

				if(this.searchForm.kechengmingcheng){
					params['kechengmingcheng'] = '%' + this.searchForm.kechengmingcheng + '%'
				}
				let user = uni.getStorageSync("appUserid")?JSON.parse(uni.getStorageSync('userSession')):{}
				if(user.banji){
					params['banji'] = user.banji
				}
                let res = {}
                if(this.userid) {
                    res = await this.$api.page(`kebiaoxinix`, params);
                } else {
                    res = await this.$api.list(`kebiaoxinix`, params);
                }

				// 如果是第一页数据置空
				if (mescroll.num == 1) this.list = [];
				this.list = this.list.concat(res.data.list);
				this.$forceUpdate()
				
				await this.getTimeList()
				for (let x in res.data.list) {
					let arr = res.data.list[x].sectionnum.split(',')
					for (let i in arr) {
						this.timetables[Number(res.data.list[x].week) - 1][arr[i] - 1] = res.data.list[x].kechengmingcheng
						
						this.jiaoshi[Number(res.data.list[x].week) - 1][arr[i] - 1] = 
							res.data.list[x].riqi
							+ '<br>' + res.data.list[x].banji
							+ '<br>' + res.data.list[x].jiaoshi
						this.ids[Number(res.data.list[x].week) - 1][arr[i] - 1] = res.data.list[x].id
					}
				}
				this.show = true
				let length = Math.ceil(this.list.length/6)
				let arr = [];
				for (let i = 0; i<length; i++){
					arr[i] = this.list.slice(i*6, (i+1)*6)
				}
				this.lists = arr
				this.hasNext = false;
				mescroll.endSuccess(mescroll.size, this.hasNext);
			},
			// 详情
			onDetailTap(item) {
				if(!item.id){
					return false
				}
                uni.setStorageSync("useridTag",this.userid);
				this.$utils.jump(`./detail?id=${item.id}&userid=`+this.userid)
			},
			onUpdate(e){
				this.onUpdateTap(e.currentTarget.dataset.row)
			},
			// 修改
			onUpdateTap(row){
				uni.setStorageSync("useridTag",this.userid);
				this.$utils.jump(`./add-or-update?id=${row.id}`)
			},
			// 添加
			onAddTap(){
                uni.setStorageSync("useridTag",this.userid);
				this.$utils.jump(`./add-or-update`)
			},
			onDelete(e){
				this.onDeleteTap(e.currentTarget.dataset.row.id)
			},
			onDeleteTap(id){
				var that = this;
				uni.showModal({
					title: '提示',
					content: '是否确认删除',
					success: async function(res) {
						if (res.confirm) {
							await that.$api.del('kebiaoxinix', JSON.stringify([id]));
							that.$utils.msg('删除成功');
							that.hasNext = true
							// 重置分页参数页数为1
							that.search()
						}
					}
				});
			},
			// 搜索
			async search(){
				this.show = false
				this.timetables = []
				this.jiaoshi = []
				this.ids = []
				this.mescroll.num = 1
				let searchForm = {
					page: this.mescroll.num,
					limit: 100,
				}
				searchForm['sort'] = this.listSort;
				searchForm['order'] = this.listOrder;

				if(this.searchForm.kechengmingcheng){
					searchForm['kechengmingcheng'] = '%' + this.searchForm.kechengmingcheng + '%'
				}
				let user = uni.getStorageSync("appUserid")?JSON.parse(uni.getStorageSync('userSession')):{}
				if(user.banji){
					params['banji'] = user.banji
				}
				let res = {};
				if(this.userid) {
					res = await this.$api.page(`kebiaoxinix`, searchForm);
				} else {
					res = await this.$api.list(`kebiaoxinix`, searchForm);
				}
				// 如果是第一页数据置空
				if (this.mescroll.num == 1) this.list = [];
				this.list = this.list.concat(res.data.list);
				
				await this.getTimeList()
				for (let x in res.data.list) {
					let arr = res.data.list[x].sectionnum.split(',')
					for (let i in arr) {
						this.timetables[Number(res.data.list[x].week) - 1][arr[i] - 1] = res.data.list[x].kechengmingcheng
						
						this.jiaoshi[Number(res.data.list[x].week) - 1][arr[i] - 1] = 
							res.data.list[x].riqi
							+ '<br>' + res.data.list[x].banji
							+ '<br>' + res.data.list[x].jiaoshi
						this.ids[Number(res.data.list[x].week) - 1][arr[i] - 1] = res.data.list[x].id
					}
				}
				this.show = true
				let length = Math.ceil(this.list.length/6)
				let arr = [];
				for (let i = 0; i<length; i++){
					arr[i] = this.list.slice(i*6, (i+1)*6)
				}
				this.lists = arr
				if (res.data.list.length == 0) this.hasNext = false;
				this.mescroll.endSuccess(this.mescroll.size, this.hasNext);
				this.screenBoxShow = false
			},
		}
	};
</script>

<style lang="scss" scoped>
	.content {
		min-height: calc(100vh - 44px);
		box-sizing: border-box;
	}
	.category-one .tab {
		cursor: pointer;
		padding: 0;
		margin: 0 40rpx 0 0;
		color: #333;
		background: #fff;
		display: inline-block;
		width: auto;
		font-size: 30rpx;
		border-color: #fff;
		border-width: 0 0 6rpx;
		line-height: 80rpx;
		border-style: solid;
	}
	
	.category-one .tab.active {
		cursor: pointer;
		padding: 0;
		margin: 0 40rpx 0 0;
		color: #d6b8ff;
		background: #fff;
		display: inline-block;
		width: auto;
		font-size: 30rpx;
		border-color: #d6b8ff;
		border-width: 0 0 6rpx;
		line-height: 80rpx;
		border-style: solid;
	}
</style>
