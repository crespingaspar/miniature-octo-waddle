<template>
<view class="content">
	<view class="register-box" :style="{'backgroundImage': indexBgUrl?`url(${$base.url + indexBgUrl})`:''}">
		<view :style='{"border":"2rpx solid #ffffff","padding":"48rpx","boxShadow":"0px 8rpx 8rpx #ccc","margin":"40rpx 0 0 0","textAlign":"center","display":"flex","justifyContent":"space-between","alignContent":"flex-start","borderRadius":"20rpx","flexWrap":"wrap","background":"#ffffff99","width":"100%","position":"relative","height":"auto"}'>
			<image :style='{"margin":"0 auto 24rpx auto","borderRadius":"20rpx","top":"-80rpx","left":"40%","display":"none","width":"160rpx","position":"absolute","height":"160rpx"}' :src="indexLogoUrl?($base.url + indexLogoUrl):'http://codegen.caihongy.cn/20241113/5bf823cecbb14887a864972177da2ec1.png'" mode="aspectFill"></image>
			<view :style='{"border":"0px solid #1ccb96","margin":"0 0 48rpx 0","borderRadius":"20rpx","flexWrap":"wrap","background":"none","display":"flex","width":"calc(100% - 0px)","height":"auto"}' v-if="tableName=='xuesheng'" class="uni-form-item uni-column">
				<view :style='{"color":"#333","borderRadius":"20rpx","textAlign":"left","background":"none","width":"100%","lineHeight":"60rpx","fontSize":"28rpx","minWidth":"160rpx"}' class="label">学号：</view>
				<input :style='{"border":"2rpx solid #d6b8ff","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"60rpx","textAlign":"left","flex":"1","background":"#fff","fontSize":"28rpx","lineHeight":"88rpx","height":"88rpx"}' :disabled="ro.xuehao" v-model="ruleForm.xuehao"  type="text"  class="uni-input" name="" placeholder="学号" />
			</view>
			<view :style='{"border":"0px solid #1ccb96","margin":"0 0 48rpx 0","borderRadius":"20rpx","flexWrap":"wrap","background":"none","display":"flex","width":"calc(100% - 0px)","height":"auto"}' v-if="tableName=='xuesheng'" class="uni-form-item uni-column">
				<view :style='{"color":"#333","borderRadius":"20rpx","textAlign":"left","background":"none","width":"100%","lineHeight":"60rpx","fontSize":"28rpx","minWidth":"160rpx"}' class="label">密码：</view>
				<input :style='{"border":"2rpx solid #d6b8ff","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"60rpx","textAlign":"left","flex":"1","background":"#fff","fontSize":"28rpx","lineHeight":"88rpx","height":"88rpx"}' :disabled="ro.mima" v-model="ruleForm.mima" type="password"  class="uni-input" name="" placeholder="密码" />
			</view>
			<view :style='{"border":"0px solid #1ccb96","margin":"0 0 48rpx 0","borderRadius":"20rpx","flexWrap":"wrap","background":"none","display":"flex","width":"calc(100% - 0px)","height":"auto"}' v-if="tableName=='xuesheng'" class="uni-form-item uni-column">
				<view :style='{"color":"#333","borderRadius":"20rpx","textAlign":"left","background":"none","width":"100%","lineHeight":"60rpx","fontSize":"28rpx","minWidth":"160rpx"}' class="label">确认密码：</view>
				<input :style='{"border":"2rpx solid #d6b8ff","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"60rpx","textAlign":"left","flex":"1","background":"#fff","fontSize":"28rpx","lineHeight":"88rpx","height":"88rpx"}' v-model="ruleForm.mima2" type="password" class="uni-input" name="" placeholder="确认密码" />
			</view>
			<view :style='{"border":"0px solid #1ccb96","margin":"0 0 48rpx 0","borderRadius":"20rpx","flexWrap":"wrap","background":"none","display":"flex","width":"calc(100% - 0px)","height":"auto"}' v-if="tableName=='xuesheng'" class="uni-form-item uni-column">
				<view :style='{"color":"#333","borderRadius":"20rpx","textAlign":"left","background":"none","width":"100%","lineHeight":"60rpx","fontSize":"28rpx","minWidth":"160rpx"}' class="label">姓名：</view>
				<input :style='{"border":"2rpx solid #d6b8ff","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"60rpx","textAlign":"left","flex":"1","background":"#fff","fontSize":"28rpx","lineHeight":"88rpx","height":"88rpx"}' :disabled="ro.xingming" v-model="ruleForm.xingming"  type="text"  class="uni-input" name="" placeholder="姓名" />
			</view>
			<view :style='{"border":"0px solid #1ccb96","margin":"0 0 48rpx 0","borderRadius":"20rpx","flexWrap":"wrap","background":"none","display":"flex","width":"calc(100% - 0px)","height":"auto"}' v-if="tableName=='xuesheng'" @tap="xueshengtouxiangTap" class="">
				<view :style='{"color":"#333","borderRadius":"20rpx","textAlign":"left","background":"none","width":"100%","lineHeight":"60rpx","fontSize":"28rpx","minWidth":"160rpx"}' class="label">头像：</view>
				<image :style='{"width":"120rpx","margin":"0px 0 0 10rpx","borderRadius":"20rpx","objectFit":"cover","display":"block","height":"80rpx"}' v-if="ruleForm.touxiang" class="avator" :src="baseUrl+ruleForm.touxiang" mode=""></image>
				<image :style='{"width":"120rpx","margin":"0px 0 0 10rpx","borderRadius":"20rpx","objectFit":"cover","display":"block","height":"80rpx"}' v-else class="avator" src="../../static/gen/upload.png" mode=""></image>
			</view>
			<view :style='{"border":"0px solid #1ccb96","margin":"0 0 48rpx 0","borderRadius":"20rpx","flexWrap":"wrap","background":"none","display":"flex","width":"calc(100% - 0px)","height":"auto"}' v-if="tableName=='xuesheng'" class="">
				<view :style='{"color":"#333","borderRadius":"20rpx","textAlign":"left","background":"none","width":"100%","lineHeight":"60rpx","fontSize":"28rpx","minWidth":"160rpx"}' class="label">性别：</view>
				<picker :style='{"border":"0px solid #1ccb96","margin":"0 0 24rpx 0","borderRadius":"20rpx","background":"none","display":"flex","width":"calc(100% - 0px)","height":"auto"}' v-if="tableName=='xuesheng'" :disabled="ro.xingbie" @change="xueshengxingbieChange" :value="xueshengxingbieIndex" :range="xueshengxingbieOptions">
					<view>
						<view :style='{"border":"2rpx solid #d6b8ff","padding":"0 20rpx","color":"#666","borderRadius":"60rpx","textAlign":"left","flex":"1","background":"#fff","fontSize":"28rpx","lineHeight":"88rpx","height":"88rpx"}' class="uni-input">{{ruleForm.xingbie?ruleForm.xingbie:"请选择性别"}}</view>
					</view>
				</picker>
			</view>
			<view :style='{"border":"0px solid #1ccb96","margin":"0 0 48rpx 0","borderRadius":"20rpx","flexWrap":"wrap","background":"none","display":"flex","width":"calc(100% - 0px)","height":"auto"}' v-if="tableName=='xuesheng'" class="uni-form-item uni-column">
				<view :style='{"color":"#333","borderRadius":"20rpx","textAlign":"left","background":"none","width":"100%","lineHeight":"60rpx","fontSize":"28rpx","minWidth":"160rpx"}' class="label">年龄：</view>
				<input :style='{"border":"2rpx solid #d6b8ff","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"60rpx","textAlign":"left","flex":"1","background":"#fff","fontSize":"28rpx","lineHeight":"88rpx","height":"88rpx"}' :disabled="ro.nianling" v-model="ruleForm.nianling"  type="text"  class="uni-input" name="" placeholder="年龄" />
			</view>
			<view :style='{"border":"0px solid #1ccb96","margin":"0 0 48rpx 0","borderRadius":"20rpx","flexWrap":"wrap","background":"none","display":"flex","width":"calc(100% - 0px)","height":"auto"}' v-if="tableName=='xuesheng'" class="uni-form-item uni-column">
				<view :style='{"color":"#333","borderRadius":"20rpx","textAlign":"left","background":"none","width":"100%","lineHeight":"60rpx","fontSize":"28rpx","minWidth":"160rpx"}' class="label">目标院校：</view>
				<input :style='{"border":"2rpx solid #d6b8ff","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"60rpx","textAlign":"left","flex":"1","background":"#fff","fontSize":"28rpx","lineHeight":"88rpx","height":"88rpx"}' :disabled="ro.mubiaoyuanxiao" v-model="ruleForm.mubiaoyuanxiao"  type="text"  class="uni-input" name="" placeholder="目标院校" />
			</view>
			<view :style='{"border":"0px solid #1ccb96","margin":"0 0 48rpx 0","borderRadius":"20rpx","flexWrap":"wrap","background":"none","display":"flex","width":"calc(100% - 0px)","height":"auto"}' v-if="tableName=='xuesheng'" class="uni-form-item uni-column">
				<view :style='{"color":"#333","borderRadius":"20rpx","textAlign":"left","background":"none","width":"100%","lineHeight":"60rpx","fontSize":"28rpx","minWidth":"160rpx"}' class="label">班级：</view>
				<input :style='{"border":"2rpx solid #d6b8ff","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"60rpx","textAlign":"left","flex":"1","background":"#fff","fontSize":"28rpx","lineHeight":"88rpx","height":"88rpx"}' :disabled="ro.banji" v-model="ruleForm.banji"  type="text"  class="uni-input" name="" placeholder="班级" />
			</view>
			<view :style='{"border":"0px solid #1ccb96","margin":"0 0 48rpx 0","borderRadius":"20rpx","flexWrap":"wrap","background":"none","display":"flex","width":"calc(100% - 0px)","height":"auto"}' v-if="tableName=='xuesheng'" class="uni-form-item uni-column">
				<view :style='{"color":"#333","borderRadius":"20rpx","textAlign":"left","background":"none","width":"100%","lineHeight":"60rpx","fontSize":"28rpx","minWidth":"160rpx"}' class="label">备考科目：</view>
				<input :style='{"border":"2rpx solid #d6b8ff","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"60rpx","textAlign":"left","flex":"1","background":"#fff","fontSize":"28rpx","lineHeight":"88rpx","height":"88rpx"}' :disabled="ro.beikaokemu" v-model="ruleForm.beikaokemu"  type="text"  class="uni-input" name="" placeholder="备考科目" />
			</view>
			<view :style='{"border":"0px solid #1ccb96","margin":"0 0 48rpx 0","borderRadius":"20rpx","flexWrap":"wrap","background":"none","display":"flex","width":"calc(100% - 0px)","height":"auto"}' v-if="tableName=='xuesheng'" class="uni-form-item uni-column">
				<view :style='{"color":"#333","borderRadius":"20rpx","textAlign":"left","background":"none","width":"100%","lineHeight":"60rpx","fontSize":"28rpx","minWidth":"160rpx"}' class="label">基础水平：</view>
				<input :style='{"border":"2rpx solid #d6b8ff","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"60rpx","textAlign":"left","flex":"1","background":"#fff","fontSize":"28rpx","lineHeight":"88rpx","height":"88rpx"}' :disabled="ro.jichushuiping" v-model="ruleForm.jichushuiping"  type="text"  class="uni-input" name="" placeholder="基础水平" />
			</view>
			<view :style='{"border":"0px solid #1ccb96","margin":"0 0 48rpx 0","borderRadius":"20rpx","flexWrap":"wrap","background":"none","display":"flex","width":"calc(100% - 0px)","height":"auto"}' v-if="tableName=='xuesheng'" class="uni-form-item uni-column">
				<view :style='{"color":"#333","borderRadius":"20rpx","textAlign":"left","background":"none","width":"100%","lineHeight":"60rpx","fontSize":"28rpx","minWidth":"160rpx"}' class="label">手机：</view>
				<input :style='{"border":"2rpx solid #d6b8ff","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"60rpx","textAlign":"left","flex":"1","background":"#fff","fontSize":"28rpx","lineHeight":"88rpx","height":"88rpx"}' v-model="ruleForm.mobile" type="text" class="uni-input" name="" placeholder="手机号码" />
			</view>
			<view :style='{"border":"0px solid #1ccb96","margin":"0 0 48rpx 0","borderRadius":"20rpx","flexWrap":"wrap","background":"none","display":"flex","width":"calc(100% - 0px)","height":"auto"}' v-if="tableName=='xuesheng' && true" class="uni-form-item uni-column">
				<view :style='{"color":"#333","borderRadius":"20rpx","textAlign":"left","background":"none","width":"100%","lineHeight":"60rpx","fontSize":"28rpx","minWidth":"160rpx"}' class="label">验证码：</view>
				<input :style='{"border":"2rpx solid #d6b8ff","padding":"0 24rpx","margin":"0 10rpx 0 0","color":"rgb(0, 0, 0)","borderRadius":"60rpx","textAlign":"left","background":"#fff","flex":"1","width":"calc(100% - 190rpx)","fontSize":"28rpx","lineHeight":"88rpx","height":"88rpx"}' v-model="smscode" type="text" class="uni-input" name="" placeholder="请输入短信验证码" />
				<button :style='{"border":"2rpx solid #d6b8ff","padding":"0px","margin":"0px","alignItems":"center","color":"#fff","display":"flex","justifyContent":"center","borderRadius":"60rpx","background":"#d6b8ff","borderWidth":"0px 0px 0px 0","width":"180rpx","lineHeight":"88rpx","height":"88rpx"}' type="primary" v-if="isEndFlag" @tap="sendsmscode" class="bg-red">发送验证码</button>
				<button :style='{"border":"2rpx solid #d6b8ff","padding":"0px","margin":"0px","alignItems":"center","color":"#fff","display":"flex","justifyContent":"center","borderRadius":"60rpx","background":"#d6b8ff","borderWidth":"0px 0px 0px 0","width":"180rpx","lineHeight":"88rpx","height":"88rpx"}' type="primary" v-if="!isEndFlag" disabled="disabled" class="bg-red">{{SecondToDate}}</button>
			</view>
			<view :style='{"border":"0px solid #1ccb96","margin":"0 0 48rpx 0","borderRadius":"20rpx","flexWrap":"wrap","background":"none","display":"flex","width":"calc(100% - 0px)","height":"auto"}' v-if="tableName=='xuesheng'" class="uni-form-item uni-column">
				<view :style='{"color":"#333","borderRadius":"20rpx","textAlign":"left","background":"none","width":"100%","lineHeight":"60rpx","fontSize":"28rpx","minWidth":"160rpx"}' class="label">密保问题：</view>
				<input :style='{"border":"2rpx solid #d6b8ff","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"60rpx","textAlign":"left","flex":"1","background":"#fff","fontSize":"28rpx","lineHeight":"88rpx","height":"88rpx"}' :disabled="ro.pquestion" v-model="ruleForm.pquestion"  type="text"  class="uni-input" name="" placeholder="密保问题" />
			</view>
			<view :style='{"border":"0px solid #1ccb96","margin":"0 0 48rpx 0","borderRadius":"20rpx","flexWrap":"wrap","background":"none","display":"flex","width":"calc(100% - 0px)","height":"auto"}' v-if="tableName=='xuesheng'" class="uni-form-item uni-column">
				<view :style='{"color":"#333","borderRadius":"20rpx","textAlign":"left","background":"none","width":"100%","lineHeight":"60rpx","fontSize":"28rpx","minWidth":"160rpx"}' class="label">密保答案：</view>
				<input :style='{"border":"2rpx solid #d6b8ff","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"60rpx","textAlign":"left","flex":"1","background":"#fff","fontSize":"28rpx","lineHeight":"88rpx","height":"88rpx"}' :disabled="ro.panswer" v-model="ruleForm.panswer"  type="text"  class="uni-input" name="" placeholder="密保答案" />
			</view>
			<view :style='{"border":"0px solid #1ccb96","margin":"0 0 48rpx 0","borderRadius":"20rpx","flexWrap":"wrap","background":"none","display":"flex","width":"calc(100% - 0px)","height":"auto"}' v-if="tableName=='jiaoshi'" class="uni-form-item uni-column">
				<view :style='{"color":"#333","borderRadius":"20rpx","textAlign":"left","background":"none","width":"100%","lineHeight":"60rpx","fontSize":"28rpx","minWidth":"160rpx"}' class="label">工号：</view>
				<input :style='{"border":"2rpx solid #d6b8ff","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"60rpx","textAlign":"left","flex":"1","background":"#fff","fontSize":"28rpx","lineHeight":"88rpx","height":"88rpx"}' :disabled="ro.gonghao" v-model="ruleForm.gonghao"  type="text"  class="uni-input" name="" placeholder="工号" />
			</view>
			<view :style='{"border":"0px solid #1ccb96","margin":"0 0 48rpx 0","borderRadius":"20rpx","flexWrap":"wrap","background":"none","display":"flex","width":"calc(100% - 0px)","height":"auto"}' v-if="tableName=='jiaoshi'" class="uni-form-item uni-column">
				<view :style='{"color":"#333","borderRadius":"20rpx","textAlign":"left","background":"none","width":"100%","lineHeight":"60rpx","fontSize":"28rpx","minWidth":"160rpx"}' class="label">密码：</view>
				<input :style='{"border":"2rpx solid #d6b8ff","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"60rpx","textAlign":"left","flex":"1","background":"#fff","fontSize":"28rpx","lineHeight":"88rpx","height":"88rpx"}' :disabled="ro.mima" v-model="ruleForm.mima" type="password"  class="uni-input" name="" placeholder="密码" />
			</view>
			<view :style='{"border":"0px solid #1ccb96","margin":"0 0 48rpx 0","borderRadius":"20rpx","flexWrap":"wrap","background":"none","display":"flex","width":"calc(100% - 0px)","height":"auto"}' v-if="tableName=='jiaoshi'" class="uni-form-item uni-column">
				<view :style='{"color":"#333","borderRadius":"20rpx","textAlign":"left","background":"none","width":"100%","lineHeight":"60rpx","fontSize":"28rpx","minWidth":"160rpx"}' class="label">确认密码：</view>
				<input :style='{"border":"2rpx solid #d6b8ff","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"60rpx","textAlign":"left","flex":"1","background":"#fff","fontSize":"28rpx","lineHeight":"88rpx","height":"88rpx"}' v-model="ruleForm.mima2" type="password" class="uni-input" name="" placeholder="确认密码" />
			</view>
			<view :style='{"border":"0px solid #1ccb96","margin":"0 0 48rpx 0","borderRadius":"20rpx","flexWrap":"wrap","background":"none","display":"flex","width":"calc(100% - 0px)","height":"auto"}' v-if="tableName=='jiaoshi'" class="uni-form-item uni-column">
				<view :style='{"color":"#333","borderRadius":"20rpx","textAlign":"left","background":"none","width":"100%","lineHeight":"60rpx","fontSize":"28rpx","minWidth":"160rpx"}' class="label">教师姓名：</view>
				<input :style='{"border":"2rpx solid #d6b8ff","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"60rpx","textAlign":"left","flex":"1","background":"#fff","fontSize":"28rpx","lineHeight":"88rpx","height":"88rpx"}' :disabled="ro.jiaoshixingming" v-model="ruleForm.jiaoshixingming"  type="text"  class="uni-input" name="" placeholder="教师姓名" />
			</view>
			<view :style='{"border":"0px solid #1ccb96","margin":"0 0 48rpx 0","borderRadius":"20rpx","flexWrap":"wrap","background":"none","display":"flex","width":"calc(100% - 0px)","height":"auto"}' v-if="tableName=='jiaoshi'" @tap="jiaoshitouxiangTap" class="">
				<view :style='{"color":"#333","borderRadius":"20rpx","textAlign":"left","background":"none","width":"100%","lineHeight":"60rpx","fontSize":"28rpx","minWidth":"160rpx"}' class="label">头像：</view>
				<image :style='{"width":"120rpx","margin":"0px 0 0 10rpx","borderRadius":"20rpx","objectFit":"cover","display":"block","height":"80rpx"}' v-if="ruleForm.touxiang" class="avator" :src="baseUrl+ruleForm.touxiang" mode=""></image>
				<image :style='{"width":"120rpx","margin":"0px 0 0 10rpx","borderRadius":"20rpx","objectFit":"cover","display":"block","height":"80rpx"}' v-else class="avator" src="../../static/gen/upload.png" mode=""></image>
			</view>
			<view :style='{"border":"0px solid #1ccb96","margin":"0 0 48rpx 0","borderRadius":"20rpx","flexWrap":"wrap","background":"none","display":"flex","width":"calc(100% - 0px)","height":"auto"}' v-if="tableName=='jiaoshi'" @tap="jiaoshizizhizhengmingTap" class="">
				<view :style='{"color":"#333","borderRadius":"20rpx","textAlign":"left","background":"none","width":"100%","lineHeight":"60rpx","fontSize":"28rpx","minWidth":"160rpx"}' class="label">资质证明：</view>
				<input :style='{"border":"2rpx solid #d6b8ff","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"60rpx","textAlign":"left","flex":"1","background":"#fff","fontSize":"28rpx","lineHeight":"88rpx","height":"88rpx"}' v-if="ruleForm.zizhizhengming"  v-model="baseUrl+ruleForm.zizhizhengming" placeholder="资质证明"></input>
				<image :style='{"width":"120rpx","margin":"0px 0 0 10rpx","borderRadius":"20rpx","objectFit":"cover","display":"block","height":"80rpx"}' v-else class="avator" src="../../static/gen/upload.png" mode=""></image>
			</view>
			<view :style='{"border":"0px solid #1ccb96","margin":"0 0 48rpx 0","borderRadius":"20rpx","flexWrap":"wrap","background":"none","display":"flex","width":"calc(100% - 0px)","height":"auto"}' v-if="tableName=='jiaoshi'" class="">
				<view :style='{"color":"#333","borderRadius":"20rpx","textAlign":"left","background":"none","width":"100%","lineHeight":"60rpx","fontSize":"28rpx","minWidth":"160rpx"}' class="label">性别：</view>
				<picker :style='{"border":"0px solid #1ccb96","margin":"0 0 24rpx 0","borderRadius":"20rpx","background":"none","display":"flex","width":"calc(100% - 0px)","height":"auto"}' v-if="tableName=='jiaoshi'" :disabled="ro.xingbie" @change="jiaoshixingbieChange" :value="jiaoshixingbieIndex" :range="jiaoshixingbieOptions">
					<view>
						<view :style='{"border":"2rpx solid #d6b8ff","padding":"0 20rpx","color":"#666","borderRadius":"60rpx","textAlign":"left","flex":"1","background":"#fff","fontSize":"28rpx","lineHeight":"88rpx","height":"88rpx"}' class="uni-input">{{ruleForm.xingbie?ruleForm.xingbie:"请选择性别"}}</view>
					</view>
				</picker>
			</view>
			<view :style='{"border":"0px solid #1ccb96","margin":"0 0 48rpx 0","borderRadius":"20rpx","flexWrap":"wrap","background":"none","display":"flex","width":"calc(100% - 0px)","height":"auto"}' v-if="tableName=='jiaoshi'" class="uni-form-item uni-column">
				<view :style='{"color":"#333","borderRadius":"20rpx","textAlign":"left","background":"none","width":"100%","lineHeight":"60rpx","fontSize":"28rpx","minWidth":"160rpx"}' class="label">年龄：</view>
				<input :style='{"border":"2rpx solid #d6b8ff","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"60rpx","textAlign":"left","flex":"1","background":"#fff","fontSize":"28rpx","lineHeight":"88rpx","height":"88rpx"}' :disabled="ro.nianling" v-model="ruleForm.nianling"  type="text"  class="uni-input" name="" placeholder="年龄" />
			</view>
			<view :style='{"border":"0px solid #1ccb96","margin":"0 0 48rpx 0","borderRadius":"20rpx","flexWrap":"wrap","background":"none","display":"flex","width":"calc(100% - 0px)","height":"auto"}' v-if="tableName=='jiaoshi'" class="uni-form-item uni-column">
				<view :style='{"color":"#333","borderRadius":"20rpx","textAlign":"left","background":"none","width":"100%","lineHeight":"60rpx","fontSize":"28rpx","minWidth":"160rpx"}' class="label">职称：</view>
				<input :style='{"border":"2rpx solid #d6b8ff","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"60rpx","textAlign":"left","flex":"1","background":"#fff","fontSize":"28rpx","lineHeight":"88rpx","height":"88rpx"}' :disabled="ro.zhicheng" v-model="ruleForm.zhicheng"  type="text"  class="uni-input" name="" placeholder="职称" />
			</view>
			<view :style='{"border":"0px solid #1ccb96","margin":"0 0 48rpx 0","borderRadius":"20rpx","flexWrap":"wrap","background":"none","display":"flex","width":"calc(100% - 0px)","height":"auto"}' v-if="tableName=='jiaoshi'" class="uni-form-item uni-column">
				<view :style='{"color":"#333","borderRadius":"20rpx","textAlign":"left","background":"none","width":"100%","lineHeight":"60rpx","fontSize":"28rpx","minWidth":"160rpx"}' class="label">擅长科目：</view>
				<input :style='{"border":"2rpx solid #d6b8ff","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"60rpx","textAlign":"left","flex":"1","background":"#fff","fontSize":"28rpx","lineHeight":"88rpx","height":"88rpx"}' :disabled="ro.shanzhangkemu" v-model="ruleForm.shanzhangkemu"  type="text"  class="uni-input" name="" placeholder="擅长科目" />
			</view>
			<view :style='{"border":"0px solid #1ccb96","margin":"0 0 48rpx 0","borderRadius":"20rpx","flexWrap":"wrap","background":"none","display":"flex","width":"calc(100% - 0px)","height":"auto"}' v-if="tableName=='jiaoshi'" class="uni-form-item uni-column">
				<view :style='{"color":"#333","borderRadius":"20rpx","textAlign":"left","background":"none","width":"100%","lineHeight":"60rpx","fontSize":"28rpx","minWidth":"160rpx"}' class="label">教学风格：</view>
				<input :style='{"border":"2rpx solid #d6b8ff","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"60rpx","textAlign":"left","flex":"1","background":"#fff","fontSize":"28rpx","lineHeight":"88rpx","height":"88rpx"}' :disabled="ro.jiaoxuefengge" v-model="ruleForm.jiaoxuefengge"  type="text"  class="uni-input" name="" placeholder="教学风格" />
			</view>
			<view :style='{"border":"0px solid #1ccb96","margin":"0 0 48rpx 0","borderRadius":"20rpx","flexWrap":"wrap","background":"none","display":"flex","width":"calc(100% - 0px)","height":"auto"}' v-if="tableName=='jiaoshi'" class="uni-form-item uni-column">
				<view :style='{"color":"#333","borderRadius":"20rpx","textAlign":"left","background":"none","width":"100%","lineHeight":"60rpx","fontSize":"28rpx","minWidth":"160rpx"}' class="label">手机：</view>
				<input :style='{"border":"2rpx solid #d6b8ff","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"60rpx","textAlign":"left","flex":"1","background":"#fff","fontSize":"28rpx","lineHeight":"88rpx","height":"88rpx"}' v-model="ruleForm.mobile" type="text" class="uni-input" name="" placeholder="手机号码" />
			</view>
			<view :style='{"border":"0px solid #1ccb96","margin":"0 0 48rpx 0","borderRadius":"20rpx","flexWrap":"wrap","background":"none","display":"flex","width":"calc(100% - 0px)","height":"auto"}' v-if="tableName=='jiaoshi' && true" class="uni-form-item uni-column">
				<view :style='{"color":"#333","borderRadius":"20rpx","textAlign":"left","background":"none","width":"100%","lineHeight":"60rpx","fontSize":"28rpx","minWidth":"160rpx"}' class="label">验证码：</view>
				<input :style='{"border":"2rpx solid #d6b8ff","padding":"0 24rpx","margin":"0 10rpx 0 0","color":"rgb(0, 0, 0)","borderRadius":"60rpx","textAlign":"left","background":"#fff","flex":"1","width":"calc(100% - 190rpx)","fontSize":"28rpx","lineHeight":"88rpx","height":"88rpx"}' v-model="smscode" type="text" class="uni-input" name="" placeholder="请输入短信验证码" />
				<button :style='{"border":"2rpx solid #d6b8ff","padding":"0px","margin":"0px","alignItems":"center","color":"#fff","display":"flex","justifyContent":"center","borderRadius":"60rpx","background":"#d6b8ff","borderWidth":"0px 0px 0px 0","width":"180rpx","lineHeight":"88rpx","height":"88rpx"}' type="primary" v-if="isEndFlag" @tap="sendsmscode" class="bg-red">发送验证码</button>
				<button :style='{"border":"2rpx solid #d6b8ff","padding":"0px","margin":"0px","alignItems":"center","color":"#fff","display":"flex","justifyContent":"center","borderRadius":"60rpx","background":"#d6b8ff","borderWidth":"0px 0px 0px 0","width":"180rpx","lineHeight":"88rpx","height":"88rpx"}' type="primary" v-if="!isEndFlag" disabled="disabled" class="bg-red">{{SecondToDate}}</button>
			</view>
			<view :style='{"border":"0px solid #1ccb96","margin":"0 0 48rpx 0","borderRadius":"20rpx","flexWrap":"wrap","background":"none","display":"flex","width":"calc(100% - 0px)","height":"auto"}' v-if="tableName=='jiaoshi'" class="uni-form-item uni-column">
				<view :style='{"color":"#333","borderRadius":"20rpx","textAlign":"left","background":"none","width":"100%","lineHeight":"60rpx","fontSize":"28rpx","minWidth":"160rpx"}' class="label">密保问题：</view>
				<input :style='{"border":"2rpx solid #d6b8ff","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"60rpx","textAlign":"left","flex":"1","background":"#fff","fontSize":"28rpx","lineHeight":"88rpx","height":"88rpx"}' :disabled="ro.pquestion" v-model="ruleForm.pquestion"  type="text"  class="uni-input" name="" placeholder="密保问题" />
			</view>
			<view :style='{"border":"0px solid #1ccb96","margin":"0 0 48rpx 0","borderRadius":"20rpx","flexWrap":"wrap","background":"none","display":"flex","width":"calc(100% - 0px)","height":"auto"}' v-if="tableName=='jiaoshi'" class="uni-form-item uni-column">
				<view :style='{"color":"#333","borderRadius":"20rpx","textAlign":"left","background":"none","width":"100%","lineHeight":"60rpx","fontSize":"28rpx","minWidth":"160rpx"}' class="label">密保答案：</view>
				<input :style='{"border":"2rpx solid #d6b8ff","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"60rpx","textAlign":"left","flex":"1","background":"#fff","fontSize":"28rpx","lineHeight":"88rpx","height":"88rpx"}' :disabled="ro.panswer" v-model="ruleForm.panswer"  type="text"  class="uni-input" name="" placeholder="密保答案" />
			</view>
			<button :style='{"border":"0","padding":"0 40rpx","margin":"24rpx auto","color":"rgb(255, 255, 255)","borderRadius":"20rpx","background":"#d6b8ff","display":"inline-block","width":"calc(100% - 0px)","lineHeight":"96rpx","fontSize":"36rpx","minWidth":"200rpx","height":"96rpx"}' class="btn-submit" @tap="register" type="primary">注册</button>
			
			<view class="idea1" :style='{"width":"100%","background":"red","display":"none","height":"80rpx"}'>idea1</view>
			<view class="idea2" :style='{"width":"100%","background":"red","display":"none","height":"80rpx"}'>idea2</view>
			<view class="idea3" :style='{"width":"100%","background":"red","display":"none","height":"80rpx"}'>idea3</view>
		</view>
	</view>
</view>
</template>

<script>
	import multipleSelect from "@/components/momo-multipleSelect/momo-multipleSelect";
	export default {
		data() {
			return {
				xueshengxingbieOptions: [],
				xueshengxingbieIndex: 0,
				jiaoshixingbieOptions: [],
				jiaoshixingbieIndex: 0,
				ruleForm: {
				xuehao: '',
				mima: '',
				xingming: '',
				touxiang: '',
				xingbie: '',
				nianling: '',
				mubiaoyuanxiao: '',
				banji: '',
				beikaokemu: '',
				jichushuiping: '',
				mobile: '',
				pquestion: '',
				panswer: '',
				status: '',
				gonghao: '',
				mima: '',
				jiaoshixingming: '',
				touxiang: '',
				zizhizhengming: '',
				xingbie: '',
				nianling: '',
				zhicheng: '',
				shanzhangkemu: '',
				jiaoxuefengge: '',
				fudaochengguo: '',
				shhf: '',
				mobile: '',
				pquestion: '',
				panswer: '',
				status: '',
				},
				ro: {},
				smscode: "",
				// 倒计时定时器
				inter: null,
				// 倒计时
				count: 10,
				//倒计时结束标识
				isEndFlag: true,
				tableName:"",
				indexBgUrl: '',
				indexLogoUrl: '',
			}
		},
		components: {
			multipleSelect
		},
		computed: {
			baseUrl() {
				return this.$base.url;
			},
			SecondToDate: function() {
				var time = this.count;
				time = parseInt(time) + "秒后重发";
				return time;
			}
		},
		async onLoad() {
			let res = [];
			let table = uni.getStorageSync("loginTable");
			this.tableName = table;

			if(this.tableName=='xuesheng'){
				// 自定义下拉框值
				this.xueshengxingbieOptions = "男,女".split(',');
				this.ruleForm.xingbie=this.xueshengxingbieOptions[0]
				this.ro = {
					xuehao: false,
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
				this.ruleForm.xingbie=this.jiaoshixingbieOptions[0]
				this.ro = {
					gonghao: false,
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
		},
		methods: {
			// 发送邮箱验证码
			async sendsmscode(){
				if(!this.ruleForm.mobile){
					this.$utils.msg(`手机号码不能为空`);
					return
				}
				if(this.ruleForm.mobile&&(!this.$validate.isMobile(this.ruleForm.mobile))){
					this.$utils.msg(`请输入正常的手机格式`);
					return
				}
				if (this.count > 0) {
					this.isEndFlag = false;
					var _this = this;
					this.inter = setInterval(function() {
						_this.count = _this.count - 1;
						if (_this.count <= 0) {
							clearInterval(_this.inter);
							_this.count=60;
							_this.isEndFlag = true;
						}
					}, 1000);
				}
				let res = await this.$api.sendsms(`${this.tableName}`, this.ruleForm.mobile);
				if (res.code == 0) {
					this.$utils.msg(`发送成功`);
				} else {
					this.$utils.errMsg(`服务器异常，请稍后重试`);
					return;
				}
			},

			xueshengtouxiangTap() {
				if(this.ro.touxiang) {
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
				if(this.ro.touxiang) {
					return false
				}
				let _this = this;
				this.$api.upload(function(res) {
					_this.ruleForm.touxiang = 'upload/' + res.file;
					_this.$forceUpdate();
				});
			},
			jiaoshizizhizhengmingTap() {
				if(this.ro.zizhizhengming) {
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

			async styleChange() {
				let rs = await this.$api.getPublic('config/info?name=appRegisterBackgroudImg')
				this.indexBgUrl = rs.data?rs.data.value:''
				rs = await this.$api.getPublic('config/info?name=appRegLogo')
				this.indexLogoUrl = rs.data?rs.data.value:''
			},
			// 获取uuid
			getUUID () {
				return new Date().getTime();
			},
			// 注册
			async register() {
				
				if((!this.ruleForm.xuehao) && `xuesheng` == this.tableName){
					this.$utils.msg(`学号不能为空`);
					return
				}
				if((!this.ruleForm.mima) && `xuesheng` == this.tableName){
					this.$utils.msg(`密码不能为空`);
					return
				}
				if(`xuesheng` == this.tableName && (this.ruleForm.mima!=this.ruleForm.mima2)){
					this.$utils.msg(`两次密码输入不一致`);
					return
				}
				if((!this.ruleForm.xingming) && `xuesheng` == this.tableName){
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
				if(`xuesheng` == this.tableName && this.ruleForm.pquestion==''){
					this.$utils.msg(`密保问题不能为空`);
					return
				}
				if(`xuesheng` == this.tableName && this.ruleForm.panswer==''){
					this.$utils.msg(`密保答案不能为空`);
					return
				}
				if((!this.ruleForm.gonghao) && `jiaoshi` == this.tableName){
					this.$utils.msg(`工号不能为空`);
					return
				}
				if((!this.ruleForm.mima) && `jiaoshi` == this.tableName){
					this.$utils.msg(`密码不能为空`);
					return
				}
				if(`jiaoshi` == this.tableName && (this.ruleForm.mima!=this.ruleForm.mima2)){
					this.$utils.msg(`两次密码输入不一致`);
					return
				}
				if((!this.ruleForm.jiaoshixingming) && `jiaoshi` == this.tableName){
					this.$utils.msg(`教师姓名不能为空`);
					return
				}
				if((!this.ruleForm.zizhizhengming) && `jiaoshi` == this.tableName){
					this.$utils.msg(`资质证明不能为空`);
					return
				}
				if(`jiaoshi` == this.tableName && this.ruleForm.nianling&&(!this.$validate.isIntNumer(this.ruleForm.nianling))){
					this.$utils.msg(`年龄应输入整数`);
					return
				}
				if((!this.ruleForm.zhicheng) && `jiaoshi` == this.tableName){
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
				if(`jiaoshi` == this.tableName && this.ruleForm.pquestion==''){
					this.$utils.msg(`密保问题不能为空`);
					return
				}
				if(`jiaoshi` == this.tableName && this.ruleForm.panswer==''){
					this.$utils.msg(`密保答案不能为空`);
					return
				}
				await this.$api.registerSms(`${this.tableName}`, this.ruleForm, this.smscode);
				this.$utils.msgBack('注册成功');;
			}
		}
	}
</script>

<style lang="scss" scoped>
	.content {
		min-height: calc(100vh - 44px);
		box-sizing: border-box;
	}
	.register-box{
		padding: 48rpx 48rpx 24rpx;
		background: url(http://codegen.caihongy.cn/20251215/d7acdcdfd32e43debb3a6bba35107421.jpg) no-repeat center top / cover,#fff;
		width: 100%;
		min-height: 100vh;
		height: auto;
	}
</style>
