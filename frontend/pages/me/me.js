import { formatTime } from "../../utils/util";
import { myRequest } from "../../utils/sendUtil";
import { showErrorMessage, showTextMessage } from "../../utils/feedback";

Page({
    data: {
        userInfo: {},
        isLogin: false,
        user: {},
        currAuthStep: 1,

        operator: [
            {
                id: 0,
                image: "https://guaduation.oss-cn-guangzhou.aliyuncs.com/operator/image/%E6%B5%8F%E8%A7%88%E5%8E%86%E5%8F%B2.png",
                name: "浏览历史"
            },
            {
                id: 1,
                image: "https://guaduation.oss-cn-guangzhou.aliyuncs.com/operator/image/%E7%82%B9%E8%B5%9E.png",
                name: "点赞"
            },
            {
                id: 2,
                image: "https://guaduation.oss-cn-guangzhou.aliyuncs.com/operator/image/%E6%94%B6%E8%97%8F.png",
                name: "收藏"
            },
            {
                id: 3,
                image: "https://guaduation.oss-cn-guangzhou.aliyuncs.com/operator/image/%E5%8F%91%E5%B8%83.png",
                name: "我的发布"
            },
        ]
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

    operatorHandler(e) {
        const operatorId = e.currentTarget.dataset.operator.id
        console.log(operatorId)
        switch (operatorId) {
            case 0:
                this.goBrowser()
                break
            case 1:
                this.goStar()
                break
            case 2:
                this.goCollect()
                break
            case 3:
                this.goPublish()
                break
            default:
        }
    },

    goBrowser() { },
    goStar() {
        wx.navigateTo({
            url: '/pages/star/star',
        })
    },
    goCollect() { },
    goPublish() { },

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