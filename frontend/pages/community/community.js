import { myRequest } from "../../utils/sendUtil";
import { showErrorMessage } from "../../utils/feedback";

Page({

    /**
     * 页面的初始数据
     */
    data: {
        navState: 0,//导航状态
        tabPanelCustomStyle: 'min-height: 82vh',
        isLoading: false,

        dynamic: {
            list: [],
            current: null,
            total: 0,
            size: null,
        },

        searchValue: '',

        isRefresh: false,
    },

    bindchange(e) {
        let index = e.detail.current;
        this.setData({
            navState: index
        })
    },

    navSwitch: function (e) {
        let index = e.currentTarget.dataset.index;
        this.setData({
            navState: index
        })
    },

    addCommunity(e) {
        wx.navigateTo({
            url: '/pages/publish/publish',
        })
    },

    // 上拉刷新
    async pullDownRefresh(e) {
        this.setData({ isRefresh: true })
        await this.getDynamicList()
        this.setData({ isRefresh: false })
    },

    // 下拉触底加载
    loadMore(e) {
        console.log(e)
    },

    async init() {
        this.setData({ isLoading: true })
        this.getDynamicList()
        this.setData({ isLoading: false })
    },

    async getDynamicList() {
        let dynamicCurrent = 0
        const token = wx.getStorageSync('token')
        const res = await myRequest.getDynamic("/api/dynamic/list/" + dynamicCurrent, token)
            .catch((e) => {
                console.log(e)
                return {}
            })
        if (JSON.stringify(res) == '{}' || res.code === -1 || res.code === null) {
            this.setData({ isLoading: false, isRefresh: false })
            showErrorMessage("获取学习动态失败", this)
            return
        }

        this.setData({
            dynamic: {
                list: res.data.data,
                current: res.data.current,
                total: res.data.total,
                size: res.data.size,
            },
        })

        if (res.code !== 0) {
            showErrorMessage(res.msg, this)
        }

        return 0
    },

    search() {
        console.log("??")
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad() {
        this.init()
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
                value: 0
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
        if (this.data.page * this.data.pageSize >= this.data.total) {
            // 证明没有下一页的数据了
            return wx.showToast({
                title: '数据加载完毕！',
                icon: 'none'
            })
        }

        // 判断是否正在加载其他数据
        if (this.data.isloading)
            return

        // 页码值 +1
        this.setData({
            page: this.data.page + 1
        })

        // 获取下一页数据

    },

    /**
     * 用户点击右上角分享
     */
    onShareAppMessage() {

    }
})