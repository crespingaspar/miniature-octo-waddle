<template>
	<div class="home-content" :style="{'backgroundImage': indexBgUrl?`url(${$base.url + indexBgUrl})`:''}">
		<!-- title -->
		<div id="home-title" class="home-title animate__animated">
			<div class="titles" >
				<span>欢迎使用</span>
				{{this.$project.projectName}}
			</div>
		</div>
		<div id="news-box" class="news-box animate__animated">
			<div class="news-title">
				公告资讯
			</div>
			<div class="news-list">
				<div class="news-item" v-for="(item,index) in newsList" :index="index" @click="newsDetailClick(item)">
					<div class="news-text">
						{{item.title}}
					</div>
					<div class="news-img-box">
						<img :src="item.picture?($base.url + item.picture.split(',')[0]):''" alt="">
					</div>
					<div class="news-desc">
						{{item.introduction}}
					</div>
					<div class="news-time">
						{{item.addtime}}
					</div>
				</div>
			</div>
		</div>
		<el-dialog :visible.sync="newsVisible" title="公告资讯" :append-to-body="true" width="60%">
			<div style="width: 100%;text-align: center;font-size: 20px;color: #000;padding: 0 0 20px;font-weight: bold;">{{newsDetail.title}}</div>
			<div style="width: 100%;">
				<div class="ql-snow ql-editor" v-html="newsDetail.content"></div>
			</div>
		</el-dialog>
	
		<!-- echarts -->
		<!-- echarts -->
	</div>
</template>
<script>
import 'animate.css'
//0
import router from '@/router/router-static'
import * as echarts from 'echarts'
export default {
	data() {
		return {
			newsList: [],
			newsDetail: {},
			newsVisible: false,
			indexBgUrl: '',
			indexLogoUrl: '',
		};
	},
	mounted(){
		this.init();
		window.addEventListener('scroll', this.handleScroll)
		setTimeout(()=>{
			this.handleScroll()
		},100)
	},
	created() {
		this.$http.get('config/info?name=bIndexBackgroundImg',).then(rs=>{this.indexBgUrl = rs.data.data?rs.data.data.value:''})
		this.$http.get('config/info?name=bHomeLogo',).then(rs=>{this.indexLogoUrl = rs.data.data?rs.data.data.value:''})
	},
	computed: {
		sessionForm() {
			return JSON.parse(this.$storage.getObj('userForm'))
		},
		avatar(){
			return this.$storage.get('headportrait')?this.$storage.get('headportrait'):''
		},
	},
	methods:{
		handleScroll() {
			let arr = [
				{id:'home-title',css:'animate__'},
				{id:'news-box',css:'animate__'},
			]
			
			for (let i in arr) {
				let doc = document.getElementById(arr[i].id)
				if (doc) {
					let top = doc.offsetTop
					let win_top = window.innerHeight + window.pageYOffset
					// console.log(top,win_top)
					if (win_top > top && doc.classList.value.indexOf(arr[i].css) < 0) {
						// console.log(doc)
						doc.classList.add(arr[i].css)
					}
				}
			}
		},
		init(){
			if(this.$storage.get('Token')){
				this.$http({
					url: `${this.$storage.get('sessionTable')}/session`,
					method: "get"
				}).then(({ data }) => {
					if (data && data.code != 0) {
						router.push({ name: 'login' })
					}
				});
			}else{
				router.push({ name: 'login' })
			}
			this.getNewsList()
		},
		getNewsList(){
			let params = {
				page: 1,
				limit: 6,
				sort: 'addtime',
				order: 'desc',
			}
			this.$http({
				url: `news/list`,
				method: "get",
				params: params
			}).then(res => {
				if(res.data&&res.data.code==0){
					this.newsList = res.data.data.list
				}
			});
		},
		newsDetailClick(row){
			this.newsDetail = row
			this.newsVisible = true
		},
	}
};
</script>
<style lang="scss" scoped>
	.home-content {
		padding: 0 30px 30px;
		background: url(http://codegen.caihongy.cn/20250919/7058017bf9e344c6abc61eb11595c5a9.png) no-repeat center top / cover;
		display: flex;
		width: 100%;
		min-height: 100vh;
		flex-wrap: wrap;
		.home-title {
			padding: 0;
			box-shadow: none;
			margin: 20px 0 0;
			display: flex;
			width: 100%;
			justify-content: center;
			align-items: center;
			transition: 0.3s;
			.titles {
				padding: 0;
				color: #333;
				font-size: 28px;
				line-height: 60px;
				span {
				}
			}
		}
		.home-title:hover {
			transform: translate3d(0, 0px, 0);
		}
		.news-box {
			padding: 10px 20px;
			margin: 10px;
			display: flex;
			transition: 0.3s;
			border-radius: 10px;
			box-shadow: inset 0px 2px 3px 0px  #497afa30,inset 0px 0px 3px 0px  #497afa50;
			flex-direction: column;
			flex: 1;
			background: #fff;
			width: calc(50% - 20px);
			justify-content: center;
			align-items: center;
			order: 11;
			.news-title {
				color: #000;
				font-weight: 600;
				width: 100%;
				font-size: 18px;
				line-height: 50px;
			}
			.news-list {
				display: flex;
				width: 100%;
				justify-content: space-between;
				align-items: center;
				flex-wrap: wrap;
			}
			.news-item {
				cursor: pointer;
				padding: 0 0 10px;
				margin: 0 0 10px 0;
				display: flex;
				width: calc(100% - 0px);
				border-color: #eee;
				border-width: 0 0 1px 0;
				justify-content: space-between;
				align-items: center;
				border-style: solid;
				.news-text {
					overflow: hidden;
					color: #000;
					white-space: nowrap;
					flex: 1;
					font-weight: 500;
					width: 100%;
					font-size: 15px;
					line-height: 2;
					text-overflow: ellipsis;
				}
				.news-img-box {
					margin: 0 10px 0 0;
					overflow: hidden;
					display: block;
					width: auto;
					height: auto;
					order: -1;
					img {
						object-fit: cover;
						width: 60px;
						height: 60px;
					}
				}
				.news-desc {
					overflow: hidden;
					color: #666;
					white-space: nowrap;
					flex: 1;
					display: none;
					width: 100%;
					text-overflow: ellipsis;
				}
				.news-time {
					color: #999;
					font-size: 12px;
				}
			}
		}
		.news-box:hover {
			transform: translate3d(3px, 0px, 0);
			z-index: 1;
		}
	}
	
	.echarts-flag-2 {
		display: flex;
		flex-wrap: wrap;
		justify-content: space-between;
		padding: 10px 20px;
		background: rebeccapurple;
	
		&>div {
			width: 32%;
			height: 300px;
			margin: 10px 0;
			background: rgba(255,255,255,.1);
			border-radius: 8px;
			padding: 10px 20px;
		}
	}
	.animate__animated {
		animation-fill-mode: none;
	}
</style>
