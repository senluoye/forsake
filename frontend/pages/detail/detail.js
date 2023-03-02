// pages/detail/detail.js
import { myRequest } from '../../utils/sendUtil';
import { showErrorMessage, showTextMessage } from "../../utils/feedback";

Page({

    /**
     * 页面的初始数据
     */
    data: {
        id: 0,
        dynamic: {},
        isLoading: false,
    },

    /**
     * 生命周期函数--监听页面加载
     */
    async onLoad(options) {
        this.setData({ isLoading: true })
        const id = options.id === undefined ? 0 : options.id

        const res = await myRequest.getDynamic("/api/dynamic/one/" + id)
            .catch((e) => {
                console.log(e)
                return {}
            })
        if (JSON.stringify(res) == '{}' || res.code === -1 || res.code === null) {
            this.setData({ isLoading: false, id: 0 })
            showErrorMessage("获取学习动态失败", this)
            return
        }

        const { data } = res;
        this.setData({ dynamic: data, id: id, isLoading: false })
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