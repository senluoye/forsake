import { myRequest } from '../../utils/sendUtil';
import { showErrorMessage, showTextMessage } from "../../utils/feedback";

Page({

    /**
     * 页面的初始数据
     */
    data: {
        id: 0, // 动态id
        dynamic: {}, // 动态VO内容
        isLoading: false, // 是否加载
        commentList: {}, // 评论列表
    },

    /**
     * 生命周期函数--监听页面加载
     */
    async onLoad(options) {
        this.setData({ isLoading: true })

        const id = options.id === undefined ? -1 : options.id
        this.setData({ id: id })

        await this.getDynamic(id)
        await this.getCommentList(id)

        this.setData({ isLoading: false })
    },

    /**
     * 获取动态详情
     * @param {动态id} id 
     */
    getDynamic: async function (id) {
        const token = wx.getStorageSync('token')
        const res = await myRequest.getDynamic("/api/dynamic/one/" + id, token)
            .catch((e) => {
                console.log(e)
                return {}
            })
        if (JSON.stringify(res) == '{}' || res.code === -1 || res.code === null) {
            this.setData({ isLoading: false, id: -1 })
            showErrorMessage("获取学习动态失败", this)
            return
        }
        const { data } = res;
        // console.log(data)
        this.setData({ dynamic: data })
    },

    /**
     * 获取动态评论
     * @param {动态id} id 
     */
    getCommentList: async function (id) {
        const res = await myRequest.postDynamic("/api/dynamic/comment/list/" + id, 0)
            .catch((e) => {
                console.log(e)
                return {}
            })
        if (JSON.stringify(res) == '{}' || res.code === -1 || res.code === null) {
            this.setData({ id: 0 })
            showErrorMessage("获取学习动态失败", this)
            return
        }
        const { data } = res
        this.setData({ commentList: data, isLoading: false })
    },

    /**
     * 关注
     * @param {事件对象} e 
     */
    async follow(e) {
        const token = wx.getStorageSync('token')
        const isFollow = this.data.dynamic.isFollow

        const res = await myRequest.postDynamic("/api/follow", { userId: this.data.dynamic.userId }, token)
            .catch((e) => {
                console.log(e)
                return {}
            })
        if (JSON.stringify(res) == '{}' || res.code === -1 || res.code === null) {
            this.setData({ id: 0 })
            if (isFollow) showErrorMessage("取消关注失败", this)
            else showErrorMessage("关注失败", this)
            return
        }

        this.setData({
            dynamic: {
                ...this.data.dynamic,
                isFollow: !this.data.dynamic.isFollow
            }
        })

        if (isFollow) {
            showTextMessage("关注成功", this)
        } else {
            showErrorMessage("取消关注成功", this)
        }
    },

    // 预览图片
    previewImage: function (e) {
        // console.log(e);
        var current = e.currentTarget.dataset.src;
        // console.log(current);
        wx.previewImage({
            current: current, // 当前显示图片的http链接
            urls: [current]
        })
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

    }
})