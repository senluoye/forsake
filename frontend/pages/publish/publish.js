import { myRequest } from "../../utils/sendUtil";
import { showTextMessage, showErrorMessage } from "../../utils/feedback";
import { uuid } from "../../utils/util";

Page({

    /**
     * 页面的初始数据
     */
    data: {
        // 动态数据
        title: "",
        content: "",
        userName: "",

        // 文件组件数组
        originFiles: [],
        gridConfig: {
            column: 3,
            width: 230,
            height: 230,
        },
        config: {
            count: 1,
        },
    },

    handleSuccess(e) {
        const { files } = e.detail;
        this.setData({
            originFiles: files,
        });
    },

    handleRemove(e) {
        const { index } = e.detail;
        const { originFiles } = this.data;
        originFiles.splice(index, 1);
        this.setData({
            originFiles,
        });
    },

    handleClick(e) {
        console.log(e.detail.file);
    },

    async publish() {
        const token = wx.getStorageSync('token')

        wx.showLoading({
            title: '发布动态中',
            mask: true,
        })

        // 获取签名信息
        const policyRes = await myRequest.getOss('/api/oss/policy', token)
            .catch(() => {
                wx.hideLoading({
                    success: (res) => { },
                })
                showErrorMessage("获取签名失败", this)
                return {}
            })
        const policyData = policyRes.data
        let dynamicFileList = []

        // 微信直传oss
        let index = 0;
        for (let file of this.data.originFiles) {
            const fileName = uuid() + "-" + file.name
            wx.uploadFile({
                url: policyData.host, // oss服务器的URL。 
                filePath: file.url, // 上传文件的本地完整路径
                name: 'file', // 必须填file。
                formData: {
                    key: policyData.dir + fileName, // 文件名
                    policy: policyData.policy, // policy信息
                    OSSAccessKeyId: policyData.accesskeyid, // oss用户的accesskeyid
                    signature: policyData.signature, // signature信息
                },
                success: (res) => {
                    if (res.statusCode === 204) {
                        console.log('上传成功');
                    }
                },
                fail: err => {
                    wx.hideLoading({
                        success: (res) => { },
                    })
                    showErrorMessage("上传文件失败", this)
                    console.log(err);
                }
            });

            const path = "https://guaduation.oss-cn-guangzhou.aliyuncs.com/" + policyData.dir + fileName;
            console.log(path)
            console.log(file.name)
            dynamicFileList[index++] = {
                path: path,
                suffix: file.name.split('.')[1],
            }
        }

        // 发送动态数据
        const dynamicVO = {
            title: this.data.title,
            content: this.data.content,
            dynamicFileList: dynamicFileList,
            userName: this.data.userName,
        }
        console.log(dynamicVO)
        const res = await myRequest.postDynamic('/api/dynamic', dynamicVO, token)
            .catch(() => {
                wx.hideLoading({
                    success: (res) => { },
                })
                showErrorMessage("发布动态失败", this)
                return {}
            })

        wx.hideLoading({
            success: (res) => { },
        })

        showTextMessage("发布成功", this);
    },

    uploadFile() {
        const res = myRequest.postOss('/api/oss/dynamic', dynamicVO, token)
            .catch(() => {
                wx.hideLoading({
                    success: (res) => { },
                })
                showErrorMessage("发布动态失败", this)
                return {}
            })
    },

    titleDataHandle: function (e) {
        this.setData({ title: e.detail.value })
    },

    contentDataHandle: function (e) {
        this.setData({ content: e.detail.value })
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad(options) {

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