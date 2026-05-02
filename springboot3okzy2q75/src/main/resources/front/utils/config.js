const config = {
	list() {
		return [
			{
				name: '课程信息',
				value: 'kechengxinxi'
			},
			{
				name: '电子讲义',
				value: 'dianzijiangyi'
			},
			{
				name: '课程信息评论',
				value: 'discusskechengxinxi'
			},
			{
				name: '电子讲义评论',
				value: 'discussdianzijiangyi'
			},
		]
	},
	roleList() {
		return [
			{
				roleName: 'xuesheng',
				tableName: '学生',
			},
			{
				roleName: 'jiaoshi',
				tableName: '教师',
			},
		]
	},
}
export default config;
