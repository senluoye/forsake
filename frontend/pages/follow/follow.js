import { myRequest } from '../../utils/sendUtil';
import { showErrorMessage, showTextMessage } from "../../utils/feedback";

Page({

    /**
     * 页面的初始数据
     */
    data: {
        followList: [],
        currentPage: 0,
        pageVO: {},

        isRefresh: false,
    },

    /**
     * 生命周期函数--监听页面加载
     */
    async onLoad() {
        this.setData({ isLoading: true })
        this.getFollowList()
        this.setData({ isLoading: false })
    },

    async getFollowList() {
        const token = wx.getStorageSync('token')
        const currentPage = this.data.currentPage
        const res = await myRequest.postFollow("/api/follow/list", currentPage, token)
            .catch((e) => {
                console.log(e)
                return {}
            })
        if (JSON.stringify(res) == '{}' || res.code === -1 || res.code === null) {
            this.setData({ isLoading: false, id: -1 })
            showErrorMessage("获取关注列表失败", this)
            return
        }
        const { data } = res;
        console.log(data)
        this.setData({ followList: data.data, pageVO: data })
    },

    // 下拉刷新
    async pullDownRefresh() {
        this.setData({ isRefresh: true, currentPage: 0 })
        await this.getFollowList()
        this.setData({ isRefresh: false })
    },

    /**
     * 取消关注
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
            if (isFollow) showErrorMessage("取消关注失败", this)
            else showErrorMessage("关注失败", this)
            return
        }

        let followList = { ...this.data.followList }
        followList[index].isFollow = !followList[index].isFollow
        console.log(followList)

        this.setData({
            followList
        })

        if (!isFollow) {
            showTextMessage("关注成功", this)
        } else {
            showErrorMessage("取消关注成功", this)
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
        if (typeof this.getTabBar === 'function' && this.getTabBar()) {
            this.getTabBar().setData({
                value: 1
            })
        }
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