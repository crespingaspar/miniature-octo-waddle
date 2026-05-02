	import Vue from 'vue';
//配置路由
	import VueRouter from 'vue-router'
	Vue.use(VueRouter);
//1.创建组件
	import Index from '@/views/index'
	import Home from '@/views/home'
	import Login from '@/views/login'
	import NotFound from '@/views/404'
	import UpdatePassword from '@/views/update-password'
	import pay from '@/views/pay'
	import register from '@/views/register'
	import center from '@/views/center'
	import adminexam from '@/views/modules/exampaperlist/exam'
	import exampaperlist from '@/views/modules/exampaperlist/list'
	import xuesheng from '@/views/modules/xuesheng/list'
	import jiaoshi from '@/views/modules/jiaoshi/list'
	import kechengfenlei from '@/views/modules/kechengfenlei/list'
	import kechengxinxi from '@/views/modules/kechengxinxi/list'
	import xuankexinxi from '@/views/modules/xuankexinxi/list'
	import xuexijindu from '@/views/modules/xuexijindu/list'
	import tuifeishenqing from '@/views/modules/tuifeishenqing/list'
	import zuoyexinxi from '@/views/modules/zuoyexinxi/list'
	import zuoyetijiao from '@/views/modules/zuoyetijiao/list'
	import zuoyepigai from '@/views/modules/zuoyepigai/list'
	import jiangyifenlei from '@/views/modules/jiangyifenlei/list'
	import dianzijiangyi from '@/views/modules/dianzijiangyi/list'
	import tousufankui from '@/views/modules/tousufankui/list'
	import kehoufudao from '@/views/modules/kehoufudao/list'
	import scheduletime from '@/views/modules/scheduletime/list'
	import kebiaoxinix from '@/views/modules/kebiaoxinix/list'
	import news from '@/views/modules/news/list'
	import exampaper from '@/views/modules/exampaper/list'
	import examquestion from '@/views/modules/examquestion/list'
	import examquestionbank from '@/views/modules/examquestionbank/list'
	import examrecord from '@/views/modules/examrecord/list'
	import chat from '@/views/modules/chat/list'
	import chathelper from '@/views/modules/chathelper/list'
	import popupremind from '@/views/modules/popupremind/list'
	import smsregistercode from '@/views/modules/smsregistercode/list'
	import users from '@/views/modules/users/list'
	import discusskechengxinxi from '@/views/modules/discusskechengxinxi/list'
	import discussdianzijiangyi from '@/views/modules/discussdianzijiangyi/list'
	import examfailrecord from '@/views/modules/examfailrecord/list'
import config from '@/views/modules/config/list'

//2.配置路由   注意：名字
export const routes = [{
	path: '/',
	name: '系统首页',
	component: Index,
	children: [{
		// 这里不设置值，是把main作为默认页面
		path: '/',
		name: '系统首页',
		component: Home,
		meta: {icon:'', title:'center', affix: true}
	}, {
		path: '/updatePassword',
		name: '修改密码',
		component: UpdatePassword,
		meta: {icon:'', title:'updatePassword'}
	}, {
		path: '/pay',
		name: '支付',
		component: pay,
		meta: {icon:'', title:'pay'}
	}, {
		path: '/center',
		name: '个人信息',
		component: center,
		meta: {icon:'', title:'center'}
	}
	,{
		path: '/xuesheng',
		name: '学生',
		component: xuesheng
	}
	,{
		path: '/jiaoshi',
		name: '教师',
		component: jiaoshi
	}
	,{
		path: '/kechengfenlei',
		name: '课程分类',
		component: kechengfenlei
	}
	,{
		path: '/kechengxinxi',
		name: '课程信息',
		component: kechengxinxi
	}
	,{
		path: '/xuankexinxi',
		name: '选课信息',
		component: xuankexinxi
	}
	,{
		path: '/xuexijindu',
		name: '学习进度',
		component: xuexijindu
	}
	,{
		path: '/tuifeishenqing',
		name: '退费申请',
		component: tuifeishenqing
	}
	,{
		path: '/zuoyexinxi',
		name: '作业信息',
		component: zuoyexinxi
	}
	,{
		path: '/zuoyetijiao',
		name: '作业提交',
		component: zuoyetijiao
	}
	,{
		path: '/zuoyepigai',
		name: '作业批改',
		component: zuoyepigai
	}
	,{
		path: '/jiangyifenlei',
		name: '讲义分类',
		component: jiangyifenlei
	}
	,{
		path: '/dianzijiangyi',
		name: '电子讲义',
		component: dianzijiangyi
	}
	,{
		path: '/tousufankui',
		name: '投诉反馈',
		component: tousufankui
	}
	,{
		path: '/kehoufudao',
		name: '课后辅导',
		component: kehoufudao
	}
	,{
		path: '/scheduletime',
		name: '课表时间',
		component: scheduletime
	}
	,{
		path: '/kebiaoxinix',
		name: '课表信息',
		component: kebiaoxinix
	}
	,{
		path: '/news',
		name: '公告资讯',
		component: news
	}
	,{
		path: '/exampaper',
		name: '在线测试',
		component: exampaper
	}
	,{
		path: '/examquestion',
		name: '试题',
		component: examquestion
	}
	,{
		path: '/examquestionbank',
		name: '试题库',
		component: examquestionbank
	}
	,{
		path: '/examrecord',
		name: '测试记录',
		component: examrecord
	}
	,{
		path: '/chat',
		name: '智能客服',
		component: chat
	}
	,{
		path: '/chathelper',
		name: '聊天助手表',
		component: chathelper
	}
	,{
		path: '/popupremind',
		name: '弹窗提醒',
		component: popupremind
	}
	,{
		path: '/smsregistercode',
		name: '短信验证码',
		component: smsregistercode
	}
	,{
		path: '/users',
		name: '管理员',
		component: users
	}
	,{
		path: '/discusskechengxinxi',
		name: '课程信息评论',
		component: discusskechengxinxi
	}
	,{
		path: '/discussdianzijiangyi',
		name: '电子讲义评论',
		component: discussdianzijiangyi
	}
	,{
		path: '/config/:type',
		name: '配置管理',
		component: config
	}
	,{
		path: '/examfailrecord',
		name: '错题本',
		component: examfailrecord
	}
	,{
		path: '/exampaperlist',
		name: '试卷列表',
		component: exampaperlist
	}
	]
	},
	{
		path: '/adminexam',
		name: 'adminexam',
		component: adminexam,
		meta: {icon:'', title:'adminexam'}
	},
	{
		path: '/login',
		name: 'login',
		component: Login,
		meta: {icon:'', title:'login'}
	},
	{
		path: '/register',
		name: 'register',
		component: register,
		meta: {icon:'', title:'register'}
	},
	{
		path: '*',
		component: NotFound
	}
]
//3.实例化VueRouter  注意：名字
const router = new VueRouter({
	mode: 'hash',
	/*hash模式改为history*/
	routes // （缩写）相当于 routes: routes
})
const originalPush = VueRouter.prototype.push
//修改原型对象中的push方法
VueRouter.prototype.push = function push(location) {
	return originalPush.call(this, location).catch(err => err)
}
export default router;
