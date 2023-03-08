import { myRequest } from '../../utils/sendUtil';
import { showErrorMessage, showTextMessage } from "../../utils/feedback";

Page({

    /**
     * 页面的初始数据
     */
    data: {
        starList: [],
        currentPage: 0,
        pageVO: {},

        isRefresh: false,
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad(options) {
        this.setData({ isLoading: true })
        this.getStarList()
        this.setData({ isLoading: false })
    },

    /**
     * 获取点赞列表
     */
    async getStarList() {
        const token = wx.getStorageSync('token')
        const currentPage = this.data.currentPage
        const res = await myRequest.getStar("/api/star/list/" + currentPage, token)
            .catch((e) => {
                console.log(e)
                return {}
            })
        if (JSON.stringify(res) == '{}' || res.code === -1 || res.code === null) {
            this.setData({ isLoading: false, id: -1 })
            showErrorMessage("获取点赞列表失败", this)
            return
        }
        const { data } = res;
        console.log(data)
        this.setData({ starList: data.data, pageVO: data })
    },

    // 下拉刷新
    async pullDownRefresh() {
        this.setData({ isRefresh: true, currentPage: 0 })
        await this.getStarList()
        this.setData({ isRefresh: false })
    },

    /**
     * 取消点赞
     * @param {*} e 
     */
    async unFollow(e) {
        const token = wx.getStorageSync('token')

        const user = e.target.dataset.user
        const isFollow = user.isFollow
        const index = e.target.dataset.index

        const res = await myRequest.postDynamic("/api/follow", { userId: user.userId }, token)
            .catch((e) => {
                console.log(e)
                return {}
            })
        if (JSON.stringify(res) == '{}' || res.code === -1 || res.code === null) {
            this.setData({ id: 0 })
            if (isFollow) showErrorMessage("取消点赞失败", this)
            else showErrorMessage("点赞失败", this)
            return
        }

        let starList = { ...this.data.starList }
        starList[index].isFollow = !starList[index].isFollow
        console.log(starList)

        this.setData({
            starList
        })

        if (!isFollow) {
            showTextMessage("点赞成功", this)
        } else {
            showErrorMessage("取消点赞成功", this)
        }
    },

    // 上拉触底加载
    loadMore(e) {
        console.log(e)
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