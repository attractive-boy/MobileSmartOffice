<template>
	<view class="login">
		<block>
			<view class="container">
				<!--顶部返回按钮-->
				<text class="back-btn iconfont iconzuo" @tap="navBack"></text>
				<!--插画-->
				<view class="right-top-sign"></view>
				<!-- 设置白色背景防止软键盘把下部绝对定位元素顶上来盖住输入框等 -->
				<view class="wrapper">
					<view class="left-top-sign">LOGIN</view>
					<view class="welcome">
						欢迎回来！
					</view>
					<view class="input-content">
						<view class="input-item">
							<!-- 用户名 -->
							<text class="tit">用户名</text>
							<input name="username" v-model="loginParams.username" placeholder="请输入用户名" maxlength="11" />
						</view>
						<view class="input-item">
							<text class="tit">手机号码</text>
							<input type="number" name="mobile" v-model="loginParams.mobile" placeholder="请输入手机号码"
								maxlength="11" @blur="blurMobileChange" />
						</view>
						<view class="input-item" v-if="loginByPass">
							<text class="tit">密码</text>
							<input name="password" type="password" v-model="loginParams.password" placeholder="请输入密码"
								maxlength="20" />
						</view>
						<button class="confirm-btn" :class="'bg-' + themeColor.name" :disabled="btnLoading"
							:loading="btnLoading" @tap="toLogin">
							登录/注册
						</button>
					</view>
				</view>

			</view>
		</block>

	</view>
</template>
<script>

import {
	loginByPass,
} from '@/api/login';
import { http } from '@/api/api';
export default {
	data() {
		return {
			loginParams: {
				username: '',
				mobile: '',
				code: '',
				password: ''
			},
			btnLoading: false,
			reqBody: {},
			loginByPass: true,
			smsCodeBtnDisabled: false,
			codeSeconds: 0, // 验证码发送时间间隔
			islogin: true
		};
	},
	onShow() {
		uni.setNavigationBarColor({
			frontColor: '#ffffff',
			backgroundColor: this.themeColor.color,
			animation: {
				duration: 400,
				timingFunc: 'easeIn'
			}
		});
		uni.setNavigationBarTitle({
			title: uni.getStorageSync('siteInfo').web_site_title ? uni.getStorageSync('siteInfo')
				.web_site_title : this.$mSettingConfig.appName
		});
		uni.setTabBarStyle({
			selectedColor: this.themeColor.color,
			borderStyle: 'white'
		});
		this.themeColor.tabList && this.themeColor.tabList.forEach((selectedIconPath, index) => {
			uni.setTabBarItem({
				index,
				selectedIconPath
			});
		});
	},

	methods: {

		// 失去焦点的手机号
		blurMobileChange(e) {
			this.mobile = e.detail.value;
		},
		// 切换登录方式
		showLoginBySmsCode() {
			this.loginByPass = !this.loginByPass;
		},
		// 返回上一页
		navBack() {
			this.$mRouter.back();
		},
		// 统一跳转路由
		navTo(route) {
			this.$mRouter.push({
				route
			});
		},
		// 返回主页
		toHome() {
			this.$mRouter.reLaunch({
				route: '/pages/index/index'
			});
		},
		// 获取手机验证码
		async getSmsCode() {
			this.reqBody['mobile'] = this.loginParams['mobile'];

			if (!checkSendCode) {
				this.$mHelper.toast(this.$mGraceChecker.error);
				return;
			}
			this.$mHelper.toast(`验证码发送成功, 请在手机上查看`);
			this.smsCodeBtnDisabled = true;
			uni.setStorageSync('pwdSmsCodeTime', moment().valueOf() / 1000);
			this.handleSmsCodeTime(59);
		},
		handleSmsCodeTime(time) {
			let timer = setInterval(() => {
				if (time === 0) {
					clearInterval(timer);
					this.smsCodeBtnDisabled = false;
				} else {
					this.codeSeconds = time;
					this.smsCodeBtnDisabled = true;
					time--;
				}
			}, 1000);
		},
		// 提交表单
		async toLogin() {
			this.reqBody['username'] = this.loginParams['username'];
			this.reqBody['mobile'] = this.loginParams['mobile'];
			this.reqBody['password'] = this.loginParams['password'];
			this.btnLoading = true;
			var options = {
				url: '/api/user/login',
				data: this.reqBody,
				method: 'POST'
			};
			const BASE_URL = 'http://localhost:8081';
			uni.request({
				url: BASE_URL + options.url,
				method: options.method || 'GET',
				data: options.data || {},
				success: (res) => {
					if (res.statusCode && res.statusCode !== 200) {
						uni.showToast({
							title: res.msg || '网络异常',
							icon: 'none'
						});
						return;
					}
					if (res.data.code === 0) {
						uni.showToast({
							title: res.data.msg || '网络异常',
							icon: 'none'
						});
						return;
					}
					uni.showToast({
						title: '登录成功',
						icon: 'none'
					});
					uni.setStorageSync('token', res.data.data);
					console.log(res.data.data);
					uni.setStorageSync('userInfo', res.data.userInfo);
					uni.setStorageSync('islogin', true);
					this.$mRouter.reLaunch({
						route: '/pages/index/index'
					});
				},
				fail: (e) => {
					uni.showToast({
						title: e.errMsg || '网络异常',
						icon: 'none'
					});
				},
				complete: () => {
					this.btnLoading = false;
				}
			})
		},
	}
};
</script>
<style lang="scss">
page {
	background: $color-white;
}

.container {
	padding-top: 115px;
	position: relative;
	width: 100vw;
	overflow: hidden;
	background: #fff;

	.wrapper {
		position: relative;
		z-index: 90;
		background: #fff;
		padding-bottom: 40upx;
	}

	.back-btn {
		position: absolute;
		left: 40upx;
		z-index: 9999;
		padding-top: var(--status-bar-height);
		top: 40upx;
		font-size: 40upx;
		color: $font-color-dark;
	}

	.left-top-sign {
		font-size: 120upx;
		color: $page-color-base;
		position: relative;
		left: -16upx;
	}

	.right-top-sign {
		position: absolute;
		top: 80upx;
		right: -30upx;
		z-index: 95;

		&:before,
		&:after {
			display: block;
			content: '';
			width: 400upx;
			height: 80upx;
			background: #b4f3e2;
		}

		&:before {
			transform: rotate(50deg);
			border-radius: 0 50px 0 0;
		}

		&:after {
			position: absolute;
			right: -198upx;
			top: 0;
			transform: rotate(-50deg);
			border-radius: 50px 0 0 0;
			/* background: pink; */
		}
	}

	.left-bottom-sign {
		position: absolute;
		left: -270upx;
		bottom: -320upx;
		border: 100upx solid #d0d1fd;
		border-radius: 50%;
		padding: 180upx;
	}

	.welcome {
		position: relative;
		left: 50upx;
		top: -90upx;
		font-size: 46upx;
		color: #555;
		text-shadow: 1px 0px 1px rgba(0, 0, 0, 0.3);
	}

	.input-content {
		padding: 0 60upx;
	}

	.input-item {
		display: flex;
		flex-direction: column;
		align-items: flex-start;
		justify-content: center;
		padding: 0 30upx;
		background: $page-color-light;
		height: 120upx;
		border-radius: 4px;
		margin-bottom: 50upx;

		&:last-child {
			margin-bottom: 0;
		}

		.tit {
			height: 50upx;
			line-height: 56upx;
			font-size: $font-sm + 2upx;
			color: $font-color-base;
		}

		input {
			height: 60upx;
			font-size: $font-base + 2upx;
			color: $font-color-dark;
			width: 100%;
		}
	}

	.input-item-sms-code {
		position: relative;
		width: 100%;

		.sms-code-btn {
			position: absolute;
			color: #111;
			right: 20upx;
			bottom: 20upx;
			font-size: 28upx;
		}

		.sms-code-resend {
			color: #999;
		}

		.sms-code-btn:after {
			border: none;
			background-color: transparent;
		}
	}

	.forget-section {
		font-size: $font-sm + 2upx;
		color: $font-color-spec;
		text-align: center;
		margin-top: 40upx;
	}

	.register-section {
		margin: 30upx 0 50upx 0;
		width: 100%;
		font-size: $font-sm + 2upx;
		color: $font-color-base;
		text-align: center;

		text {
			color: $font-color-spec;
			margin-left: 10upx;
		}

		text:first-child {
			margin-right: 10upx;
		}
	}

	.btn-group {
		display: flex;
		margin-bottom: 20upx;
	}
}

.login-type-2 {
	width: 100%;
	position: relative;

	.back-btn {
		position: absolute;
		left: 40upx;
		z-index: 9999;
		padding-top: var(--status-bar-height);
		top: 40upx;
		font-size: 48upx;
		color: $color-white;
	}

	.login-top {
		height: 460upx;
		position: relative;

		.desc {
			position: absolute;
			top: 200upx;
			left: 40upx;
			font-size: 48upx;

			.title {
				font-size: 48upx;
			}
		}

		.login-pic {
			position: absolute;
			width: 220upx;
			height: 270upx;
			right: 30upx;
			top: 100upx;
		}
	}

	.login-type-content {
		position: relative;
		top: -72upx;

		.login-bg {
			width: 94vw;
			height: 94vw;
			margin: 0 3vw;
		}

		.main {
			width: 94vw;
			position: absolute;
			top: 0;
			left: 3vw;

			.nav-bar {
				display: flex;
				height: 100upx;
				justify-content: center;
				align-items: center;
				position: relative;
				z-index: 10;

				.nav-bar-item {
					flex: 1;
					display: flex;
					height: 100%;
					line-height: 96upx;
					font-size: $font-lg;
					display: flex;
					margin: 0 120upx;
					justify-content: center;
				}

				.nav-bar-item-active {
					border-bottom: 5upx solid;
				}
			}

			.login-type-form {
				width: 80%;
				margin: 50upx auto;

				.input-item {
					position: relative;
					height: 90upx;
					line-height: 90upx;
					margin-bottom: $spacing-lg;

					.iconfont {
						font-size: 50upx;
						position: absolute;
						left: 0;
					}

					.login-type-input {
						height: 90upx;
						padding-left: 80upx;
						border-bottom: 1upx solid rgba(0, 0, 0, .1);
					}

					.sms-code-btn,
					sms-code-resend {
						width: 240upx;
						font-size: $font-base - 2upx;
					}
				}
			}

			.login-type-tips {
				padding: 0 50upx;
				display: flex;
				justify-content: space-between;
			}

			.confirm-btn {
				height: 80upx;
				line-height: 80upx;
			}
		}
	}

	.login-type-bottom {
		width: 100%;
		padding-bottom: 30upx;
		text-align: center;
		font-size: $font-lg;
	}
}

.register-a {
	color: #999;
	font-size: 28upx;
	text-align: center;
	margin-top: 20upx;
	display: block;
}
</style>
