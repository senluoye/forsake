import { formatTime } from "../../utils/util";
import { myRequest } from "../../utils/sendUtil";
import { showErrorMessage, showTextMessage } from "../../utils/feedback";

Page({
    data: {
        userInfo: {},
        isLogin: false,
        user: {},
        currAuthStep: 1,
    },

    async login() {
        // 先获取用户信息
        await this.getUserProfile()

        wx.showLoading({
            title: '登录中...',
            mask: true,
        })

        // 发送登陆请求
        const data = await wx.p.login()
            .then(async res => {
                const data = await myRequest.postUser('/api/user/login', {
                    code: res.code,
                    user: this.data.userInfo
                })
                if (data.code === 0) {
                    wx.setStorageSync('token', data.data.token);
                }
                return { isLogin: data.code === 0, msg: data.msg }
            }).catch(e => {
                wx.hideLoading()
                showErrorMessage(e, this)
                return { isLogin: false }
            })
        console.log(data)

        // 是否登陆成功
        if (data.isLogin) {
            this.setData({ isLogin: data.isLogin })
        } else {
            this.setData({ userInfo: {} })
            showErrorMessage("登录失败: " + data.msg, this);
        }

        wx.hideLoading()
    },

    // 获取用户信息
    async getUserProfile(e) {
        await wx.p.getUserProfile({
            desc: '请求获取用户信息'
        }).then((res) => {
            this.setData({
                userInfo: res.userInfo
            })
            wx.setStorageSync('userInfo', res.userInfo)
        })
    },

    logout() {
        wx.clearStorageSync();
        this.setData({
            userInfo: {},
            isLogin: false,
        })
        showTextMessage("退出登陆成功", this)
    },

    /**
     * 生命周期函数--监听页面加载
     */
    async onLoad() {
        // 查看是否已经登陆过
        const token = wx.getStorageSync('token')
        const isLogin = token !== null && token !== ""
        this.setData({ isLogin: isLogin })
        if (!isLogin) return

        wx.showLoading({
            title: '获取用户数据中',
            mask: true,
        })

        const res = await myRequest.getUser("/api/user/info", token)
            .catch(() => {
                wx.hideLoading({
                    success: (res) => { },
                })
                showErrorMessage("获取用户信息失败", this)
                return {}
            })
        console.log(res)


        wx.hideLoading({
            success: (res) => { },
        })
        if (JSON.stringify(res) == '{}' || res.code === -1 || res.code === null) {
            showErrorMessage(res.msg, this)
            this.setData({
                isLogin: false

            })
            return
        }

        this.setData({ userInfo: res.data })
    },

    /**
     * 生命周期函数--监听页面初次渲染完成
     */
    onReady() {

    },

    /**
     * 生命周期函数--监听页面显示
     */
    onShow() {
        if (typeof this.getTabBar === 'function' && this.getTabBar()) {
            this.getTabBar().setData({
                value: 2
            })
        }
        const token = wx.getStorageSync('token')
        const isLogin = token !== null && token !== ""
        this.setData({ isLogin: isLogin })
    },

    /**
     * 生命周期函数--监听页面隐藏
     */
    onHide() {

    },

    /**
     * 生命周期函数--监听页面卸载
     */
    onUnload() {

    },

    /**
     * 页面相关事件处理函数--监听用户下拉动作
     */
    onPullDownRefresh() {

    },

    /**
     * 页面上拉触底事件的处理函数
     */
    onReachBottom() {

    },

    /**
     * 用户点击右上角分享
     */
    onShareAppMessage() {

    },


})