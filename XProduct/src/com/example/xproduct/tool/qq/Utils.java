package com.example.xproduct.tool.qq;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.example.xproduct.view.MyToast;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

public class Utils {

	private Context con;
	private Tencent mTencent;

	public void initQQLoginCondition(Context con) {
		// Tencent类是SDK的主要实现类，开发者可通过Tencent类访问腾讯开放的Ope nAPI。
		// 其中APP_ID是分配给第三方应用的appid，类型为String。
		this.con = con;
		mTencent = Tencent.createInstance(QQPartnerConfig.mAppid,
				con.getApplicationContext());

	}

	public void qqlogin() {
//		mTencent.login((Activity) con, QQPartnerConfig.scope,
//				new BaseUiListener());
	}

	/**
	 * 广播的侦听，授权完成后的回调是以广播的形式将结果返回
	 * 
	 */
	private class BaseUiListener implements IUiListener {

		@Override
		public void onComplete(JSONObject response) {
			try {
				String access_token = response.getString("access_token");
				String openid = response.getString("openid");
				Log.i("---qq---", "token=" + access_token + "  |  openid="
						+ openid);
				qqloginLekan(access_token, openid);
			} catch (JSONException e) {
				Log.i("QQ_LOGIN", "BaseUiListener", e);
			}
		}

		@Override
		public void onError(UiError e) {
			Log.i("QQ_LOGIN", "BaseUiListener  errorCode=" + e.errorCode
					+ " errorMessage=" + e.errorMessage + " errorDetail"
					+ e.errorDetail);
			MyToast.makeText(con, " errorMessage=" + e.errorMessage
					+ "errorCode=" + e.errorCode + " errorDetail"
					+ e.errorDetail, 1);
		}

		@Override
		public void onCancel() {
			Log.i("QQ_LOGIN", "BaseUiListener登录取消");
			MyToast.makeText(con, "登录取消", 1);
		}
	}

	private void qqloginLekan(final String token, final String openId) {
		// new Thread(new Runnable() {// 获取用户id，
		//
		// public void run() {
		// QQLogin qq = new QQLogin();
		// qq.setOpenId(openId);
		// qq.setToken(token);
		// qq.setUrl(Globals.LeKanApiUrl);
		// qq = (QQLogin) new Load().LoadData(qq);
		// if (null != qq) {
		// Log.i("userId",
		// qq.getUserId() + "==" + qq.getStatus());
		// if (qq.getStatus() != 2 && qq.getUserId() != 0) {// 得到用户类型
		// Globals.LeKanUserId = qq.getUserId();
		// UserPayment userPayment = new UserPayment();
		// userPayment.setUrl(Globals.LeKanApiUrl);
		// userPayment.setUserId(Globals.LeKanUserId);
		// userPayment.setSite("5");
		// userPayment.setVersion("1.0");
		// userPayment = (UserPayment) new Load()
		// .LoadData(userPayment);
		// if (userPayment != null) {
		// Globals.lekanUserType = userPayment
		// .getUserType();
		// Globals.lekanStartTime = userPayment
		// .getStartTime();
		// Globals.lekanendTime = userPayment
		// .getEndTime();
		// Log.i("UserType", "UserType = "
		// + Globals.lekanUserType
		// + "StartTime = "
		// + Globals.lekanStartTime + "===="
		// + Globals.lekanendTime);
		// // dialogLogin.cancel();
		// saveUserInfo(Globals.LeKanUserId,
		// Globals.lekanUserType,
		// Globals.lekanStartTime,
		// Globals.lekanendTime, "");
		// handler.sendEmptyMessage(100);
		// } else {
		// handler.sendEmptyMessage(101);
		// }
		// } else {
		// handler.sendEmptyMessage(qq.getStatus());
		// }
		// } else {
		// handler.sendEmptyMessage(101);
		// }
		// }
		// }).start();
	}

	// ///////////////////////////////////////////////////////////////sina
	// 登陆
	// start/////////////////////////////////////////////////////////////////
	// private void sinalogin() {
	// Weibo weibo = Weibo.getInstance();
	// weibo.setupConsumerConfig(SinaPartnerConfig.CONSUMER_KEY,
	// SinaPartnerConfig.CONSUMER_SECRET);
	//
	// // Oauth2.0
	// // 隐式授权认证方式
	// weibo.setRedirectUrl("http://statistic.lekan.com:9090/");//
	// 此处回调页内容应该替换为与appkey对应的应用回调页
	// // 对应的应用回调页可在开发者登陆新浪微博开发平台之后，
	// // 进入我的应用--应用详情--应用信息--高级信息--授权设置--应用回调页进行设置和查看，
	// // 应用回调页不可为空
	//
	// weibo.authorize(con, new AuthDialogListener());
	// }

	// class AuthDialogListener implements WeiboDialogListener {
	//
	// public void onComplete(Bundle values) {
	// Log.i("sina", values.toString());
	// String access_token = values.getString("access_token");
	// String expires_in = values.getString("expires_in");
	// String uid = values.getString("uid");
	//
	// AccessToken accessToken = new AccessToken(access_token,
	// SinaPartnerConfig.CONSUMER_SECRET);
	// String as = accessToken.getSecret();
	// accessToken.setExpiresIn(expires_in);
	// Weibo.getInstance().setAccessToken(accessToken);
	//
	// sinaLoginLekan(access_token, uid);
	// }
	//
	// private void sinaLoginLekan(final String access_token, final String uid)
	// {
	//
	// new Thread(new Runnable() {// 获取用户id，
	//
	// public void run() {
	// SinaLogin sina = new SinaLogin();
	// sina.setUid(uid);
	// sina.setToken(access_token);
	// sina.setUrl(Globals.LeKanApiUrl);
	// sina = (SinaLogin) new Load().LoadData(sina);
	// if (null != sina) {
	// Log.i("userId",
	// sina.getUserId() + "=="
	// + sina.getStatus());
	// if (sina.getStatus() != 2
	// && sina.getUserId() != 0) {// 得到用户类型
	// Globals.LeKanUserId = sina.getUserId();
	// UserPayment userPayment = new UserPayment();
	// userPayment.setUrl(Globals.LeKanApiUrl);
	// userPayment.setUserId(Globals.LeKanUserId);
	// userPayment.setSite("5");
	// userPayment.setVersion("1.0");
	// userPayment = (UserPayment) new Load()
	// .LoadData(userPayment);
	// if (userPayment != null) {
	// Globals.lekanUserType = userPayment
	// .getUserType();
	// Globals.lekanStartTime = userPayment
	// .getStartTime();
	// Globals.lekanendTime = userPayment
	// .getEndTime();
	// Log.i("UserType", "UserType = "
	// + Globals.lekanUserType
	// + "StartTime = "
	// + Globals.lekanStartTime
	// + "====" + Globals.lekanendTime);
	// // dialogLogin.cancel();
	// saveUserInfo(Globals.LeKanUserId,
	// Globals.lekanUserType,
	// Globals.lekanStartTime,
	// Globals.lekanendTime, "");
	// handler.sendEmptyMessage(100);
	// } else {
	// handler.sendEmptyMessage(101);
	// }
	// } else {
	// handler.sendEmptyMessage(sina.getStatus());
	// }
	// } else {
	// handler.sendEmptyMessage(101);
	// }
	// }
	// }).start();
	//
	// }
	//
	// public void onError(DialogError e) {
	// MyToast.makeText(con, "Auth error : " + e.getMessage(),
	// Toast.LENGTH_LONG);
	// }
	//
	// public void onCancel() {
	// MyToast.makeText(con, "Auth cancel", Toast.LENGTH_LONG);
	// }
	//
	// public void onWeiboException(WeiboException e) {
	// MyToast.makeText(con, "Auth exception : " + e.getMessage(),
	// Toast.LENGTH_LONG);
	// }
	// }
}
